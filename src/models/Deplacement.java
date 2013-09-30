package models;

import java.lang.*;
import java.util.Random;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.sql.SQLException;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
/**
 * Décrivez votre classe Deplacement ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Deplacement
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private String code_art;
    private String date_depl;
    private String date_retour;
    private String annexe;
    private int qte_depl;
    private String pers;
    private String etat;
    private Articles art;
    private Connexion conn;
    private ResultSet rs;
    /**
     * Constructeur d'objets de classe Deplacement
     */
    public Deplacement()
    {
        // initialisation des variables d'instance
        code_art = "";
        date_depl = "";
        date_retour = "";
        annexe = "";
        qte_depl = 0;
        pers = "";
        etat = "deplace";
        art = new Articles();
        this.conn = new Connexion();
    }
    
    public Deplacement(String code, String date_d, String date_r, String anx, int qte, String pers)
    {
        // initialisation des variables d'instance
        this.code_art = code;
        this.date_depl = date_d;
        this.date_retour = date_r;
        this.annexe = anx;
        this.qte_depl = qte;
        this.pers = pers;
        this.etat = "deplace";
        this.art = new Articles();
        this.conn = new Connexion();
    }

    public String generer_code(String annexe)
    {
       String code = null;
       String ss_anx = annexe.substring(0,3);
       Random r = new Random();
       int valeur = 1000 + r.nextInt(10000 );
       return code = Integer.toString(valeur)+ ss_anx.toUpperCase();
    }
    
    public ArrayList Disponibilite_art(String code)
    {
       
       int rest = 0;
       ArrayList rech = new ArrayList();;
       String req = "select * from \"Articles\" where nom = '"+code+"'";
        try{
            String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
            ResultSet test = this.conn.Req_Select(req);
            if (test.next())
            {
                rest = test.getInt("quantite");
                if (rest == 0){
                    rech = null;
                }else{
                           rech.add(test.getString("code"));
                           rech.add(test.getString("nom"));
                           rech.add(test.getString("categorie"));
                           rech.add(test.getString("etat"));
                           rech.add(test.getInt("quantite"));
                       
                }
            
            }else{
                    rech.add("");
                }
        
        }catch(Exception e){
            rech.add(1);
        }
        
       this.conn.Fermer_Connexion();
       return rech;
    }
    
    public int modifier_qte(int qte, String code){
     String req = "select quantite from \"Articles\" where code = '"+code+"'";
     int rep = 0;
     int diff = 0;
     try{
            String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
            ResultSet test = this.conn.Req_Select(req);
            if (test.next())
            {
                    rep = test.getInt("quantite");
                    if(qte <= rep){
                        diff = rep - qte;
                    }else{
                        diff = -1;
                    }
                    
            }else{
                    diff = -2;
                }
        
        }catch(Exception e){
            e.printStackTrace();
        }
     
     this.conn.Fermer_Connexion();
     return diff;
    }
    
    public Date convert_String_date(String chaine){
        Date d = null;
        try{
            SimpleDateFormat date_st = new SimpleDateFormat("dd/MM/yyyy");
            d = date_st.parse(chaine);
            
       }catch(ParseException e){ 
           e.toString();
        }
        
       return d;
    }
    
    public boolean test_date(String date_dpl, String date_rtr){
        boolean d = false;
        Date d1 = null;
        Date d2 = null;
        try{
            SimpleDateFormat date_st = new SimpleDateFormat("dd/MM/yyyy");
            d1 = date_st.parse(date_dpl);
            d2 = date_st.parse(date_rtr);
            
            if (d1.before(d2)){
                d = true; 
            }else{
                d = false;
            }
       }catch(ParseException e){ 
           e.toString();
        }
        
       return d;
    }
    
    /**
     * Un exemple de méthode - remplacez ce commentaire par le vôtre
     *
     * @param  y   le paramètre de la méthode
     * @return     la somme de x et de y
     */
    public String deplacer_Article()
    {
        // Insérez votre code ici
        String rest = "";
        String code = generer_code(this.annexe);
        int qte_mod = modifier_qte(this.qte_depl, this.code_art);
        boolean dt = test_date(this.date_depl, this.date_retour);
        String rep = "insert into deplacement values('"+code+"','"+this.code_art+"','"+this.annexe.toLowerCase()+"',"+this.qte_depl+",'"+this.pers.toLowerCase()+"','"+this.etat.toLowerCase()+"','"+this.date_depl+"', '"+this.date_retour+"')";
        String rep2 = "update \"Articles\" set quantite = "+qte_mod+"  where code = '"+this.code_art+"'";
        boolean test = art.tester_code(code_art);
        if (!test){
            rest = "code invalid";
        }else
            if(qte_mod == -1){
                rest = "qte deplace superieur";
           }else
            if(!dt){
                rest = "date invalide";
            }else{
                
                String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
                if (!ouvr.equalsIgnoreCase("succes")){
                    rest = "ouverture nok";
                }else{
                   rest = this.conn.Req_Update(rep);
                   if (! rest.equalsIgnoreCase("succes")){
                        
                    }else{
                        rest = this.conn.Req_Update(rep2);
                    }
            
                }
            }
        
        this.conn.Fermer_Connexion();
        return rest;
    }
    
    public  Object[][] lister_Deplacement()
    {
       Object liste[][] = null;
       String req1 = "select \"Articles\".nom,  \"Articles\".quantite, deplacement.code, deplacement.date_dpl, deplacement.date_retour, deplacement.magasin, deplacement.qte_depl, deplacement.pers, deplacement.etat from \"deplacement\", \"Articles\" where \"Articles\".code = deplacement.code_art ORDER BY deplacement.date_dpl;";
       String req2 = "select count(*) from \"deplacement\", \"Articles\" where \"Articles\".code = deplacement.code_art; ";
       int compt = 0;
       String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
       if (!ouvr.equalsIgnoreCase("succes")){
           //liste[][] = null;
       }
       else{
           
           try{
                        this.rs = this.conn.Req_Select(req2);
                        if(rs.next()){
                                compt = rs.getInt(1);
                                liste = new Object[compt][9];
                                this.rs = this.conn.Req_Select(req1);
                                int i = 0;
                               while(this.rs.next()){
                                        liste[i][0] = rs.getString("code");
                                        liste[i][1] = rs.getString("nom");
                                        liste[i][2] = rs.getString("magasin");
                                        liste[i][3] = rs.getInt("quantite");
                                        liste[i][4] = rs.getInt("qte_depl");
                                        liste[i][5] = rs.getString("pers");
                                        liste[i][6] = rs.getString("etat");
                                        liste[i][7] = rs.getString("date_dpl");
                                        liste[i][8] = rs.getString("date_retour");
                                        i++;
                                    }
                           }
                   
                }catch(Exception e){
                      e.printStackTrace();       
                }
                
             }
                
     this.conn.Fermer_Connexion();
     return liste;      
     }
     
     public  Object[][] lister_Deplacement( String nom)
    {
       Object liste[][] = null;
       String req1 = "select \"Articles\".nom,  \"Articles\".quantite, deplacement.code, deplacement.date_dpl, deplacement.date_retour, deplacement.magasin, deplacement.qte_depl, deplacement.pers, deplacement.etat from deplacement, \"Articles\" where \"Articles\".code = deplacement.code_art and \"Articles\".nom = '"+nom+"' ORDER BY deplacement.date_dpl; ";
       String req2 = "select count(*) as compt from deplacement, \"Articles\" where \"Articles\".code = deplacement.code_art and \"Articles\".nom = '"+nom+"';";
       int compt = 0;
       String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
       if (!ouvr.equalsIgnoreCase("succes")){
           //liste[][] = null;
       }
       else{
           
           try{
                        this.rs = this.conn.Req_Select(req2);
                        if(this.rs.next()){
                                compt = this.rs.getInt("compt");
                                //System.out.print(compt);
                                liste = new Object[compt][9];
                                ResultSet rst = this.conn.Req_Select(req1);
                                 int i = 0;
                                       while(rst.next()){             
                                                    liste[i][0] = rst.getString("code");
                                                    liste[i][1] = rst.getString("nom");
                                                    liste[i][2] = rst.getString("magasin");
                                                    liste[i][3] = rst.getInt("quantite");
                                                    liste[i][4] = rst.getInt("qte_depl");
                                                    liste[i][5] = rst.getString("pers");
                                                    liste[i][6] = rst.getString("etat");
                                                    liste[i][7] = rst.getString("date_dpl");
                                                    liste[i][8] = rst.getString("date_retour");
                                                    i++;
                                                }
                                   
                                
                           }

                   
                }catch(Exception e){
                      e.printStackTrace();       
                }
                
             }
                
       this.conn.Fermer_Connexion();
       return liste;      
     }
     
     public boolean tester_code(String code)
    {
       boolean rep = false;
       String req = "select * from \"deplacement\" where code = '"+code+"'";
        try{
            String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
            ResultSet test = this.conn.Req_Select(req);
            if (test.next())
            {
                rep = true;
            }else{
                    rep = false;
                }
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
       this.conn.Fermer_Connexion();
        return rep;
    }
    
     public String annuler_Deplacement(String code)
    {
       boolean test = tester_code(code);
       String rest = "";
       int q = 0;
       String code_article = "";
       if (!test){
           rest = "kod la pa bon !";
        }else{
            String req = " delete from deplacement where code = '"+code+"'";
            String req2 = "select qte_depl, code_art from deplacement where code = '"+code+"';";
            String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
            if (!ouvr.equalsIgnoreCase("succes")){
                rest = "ouveti a pa bon ";
            }else{
             try{
                      this.rs = this.conn.Req_Select(req2);
                        if (this.rs.next()){
                            q = this.rs.getInt("qte_depl");
                            code_article = this.rs.getString("code_art");
                            if (q != 0){
                                    String req3 = "update \"Articles\" set quantite = quantite + "+q+" where code = '"+code_article+"';";
                                    //String tst = this.conn.Req_Update(req3);
                                    String t = this.conn.Req_Update(req);
                                      if (! t.equalsIgnoreCase("succes")){
                                             rest = "echoue";
                                        }else{
                                            String tst = this.conn.Req_Update(req3);
                                             if(tst.equalsIgnoreCase("succes")){
                                                  rest = "succes";
                                              }
                                              
                                       }
                                    
                            }else{
                                rest = "echoue";
                            }
                                
                        }
                            
               }catch(SQLException e){
                            
              }
                
            
            }
               
            }
            
        this.conn.Fermer_Connexion();
        return rest;
    }
    
      
    public void imprimer_dpl(String [] dt){
        // Récupère un PrinterJob
      PrinterJob job = PrinterJob.getPrinterJob();
      // Définit son contenu à imprimer
      job.setPrintable(new test_imp(dt));
      // Affiche une boîte de choix d'imprimante
      if (job.printDialog()){
         try {
            // Effectue l'impression
            job.print();
         } catch (PrinterException ex) {
            ex.printStackTrace();
         }
      }
      
    }
    
}
