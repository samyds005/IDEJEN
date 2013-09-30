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
 * Décrivez votre classe Retour ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Retour
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private String code_art_dpl;
    private String date_retour;
    private String nom;
    private int qte_rt;
    private int qte_ut;
    private int qte_def;
    private String comment;
    private Articles art;
    private Connexion conn;
    private ResultSet rs;
    
    /**
     * Constructeur d'objets de classe Retour
     */
    public Retour()
    {
        // initialisation des variables d'instance
        code_art_dpl = "";
        date_retour = "";
        nom = "";
        qte_rt = 0;
        qte_ut = 0;
        qte_def = 0;
        comment = "";
        this.conn = new Connexion();
    }
    
    /**
     * Constructeur d'objets de classe Retour
     */
    public Retour(String code, String date_, String n, int qte_r, int qte_u, int qte_d, String com)
    {
        // initialisation des variables d'instanc;
        code_art_dpl = code;
        date_retour = date_;
        nom = n;
        qte_rt = qte_r;
        qte_ut = qte_u; 
        qte_def = qte_d;
        comment = com;
        this.conn = new Connexion();
    }

    
    
    public String generer_code(String dte)
    {
       String code = null;
       //String ss_anx = annexe.substring(0,3);
       Random r = new Random();
       int valeur = 1000 + r.nextInt(10000 );
       return code = Integer.toString(valeur)+"-"+ dte;
    }
    
    public ArrayList verifier_Art_Deplacer(String code)
    {
         
       ArrayList rech = new ArrayList();;
       String req = "select \"Articles\".nom, deplacement.code, deplacement.date_dpl, deplacement.date_retour, deplacement.magasin, deplacement.qte_depl from deplacement, \"Articles\" where \"Articles\".code = deplacement.code_art and deplacement.etat = 'deplace' and deplacement.code = '"+code+"';" ;
        try{
            String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
            ResultSet test = this.conn.Req_Select(req);
            if (test.next())
            {
                rech.add(test.getString("code"));
                rech.add(test.getString("nom"));
                rech.add(test.getString("magasin"));
                rech.add(test.getString("date_dpl"));
                rech.add(test.getString("date_retour"));
                rech.add(test.getInt("qte_depl"));
                            
            }else{
                    rech.add("");
                }
        
        }catch(Exception e){
            rech.add(1);
        }
        
       this.conn.Fermer_Connexion();
       return rech;
    }
    
    public String retour_Article(){
        String rest = "";
        String code_art = "";
        String code = generer_code(this.date_retour);
        String req = "select \"Articles\".code from deplacement, \"Articles\" where \"Articles\".code = deplacement.code_art and deplacement.code = '"+this.code_art_dpl+"';" ;
        String req1 = "insert into retour values('"+code+"','"+this.code_art_dpl+"','"+this.date_retour+"','"+this.nom.toLowerCase()+"',"+this.qte_rt+","+this.qte_ut+","+this.qte_def+", '"+this.comment+"')";
        String req2 = "update deplacement set etat = 'retourne'  where code = '"+this.code_art_dpl+"'";
        
        try{
              String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
            if (!ouvr.equalsIgnoreCase("succes")){
                rest = "ouveti a pa bon ";
            }else{
                    this.rs = this.conn.Req_Select(req);
                        if (this.rs.next()){
                            code_art = this.rs.getString("code");
                            String req3 = "update \"Articles\" set quantite = quantite + "+this.qte_ut+" where code = '"+code_art+"';";
                            String t = this.conn.Req_Update(req1);
                            if (! t.equalsIgnoreCase("succes")){
                                rest = "echec";
                            }else{
                               String tst = this.conn.Req_Update(req2);
                               tst = this.conn.Req_Update(req3);
                               rest = "succes";
                                          
                                    
                            }
                                
                        }else{
                                rest = "echoue";
                         }
                            
                             
            
            }
        
        }catch(SQLException e){
        
        }
        
        
        this.conn.Fermer_Connexion();
        return rest;
    }
    
    public boolean tester_code(String code)
    {
       boolean rep = false;
       String req = "select * from retour where code = '"+code+"'";
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
    
    public String annuler_Retour(String code)
    {
       boolean test = tester_code(code);
       String rest = "";
       int q = 0;
       String code_article = "";
       String code_dpl = "";
       if (!test){
           rest = "kod la pa bon !";
        }else{
            String req = " delete from retour where code = '"+code+"'";
            String req2 = "select \"Articles\".code, retour.qte_ut, deplacement.code as code_dpl from deplacement, retour, \"Articles\" where \"Articles\".code = deplacement.code_art and deplacement.code = retour.code_art_dpl and retour.code = '"+code+"';";
            String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
            if (!ouvr.equalsIgnoreCase("succes")){
                rest = "ouveti a pa bon ";
            }else{
             try{
                      this.rs = this.conn.Req_Select(req2);
                        if (this.rs.next()){
                            code_article = this.rs.getString("code");
                            q = this.rs.getInt("qte_ut");
                            code_dpl = this.rs.getString("code_dpl");
                            if (! code_article.equalsIgnoreCase("")){
                                    String req3 = "update \"Articles\" set quantite = quantite - "+q+" where code = '"+code_article+"';";
                                    String req4 = "update deplacement set etat = 'deplace' where code = '"+code_dpl+"';";
                                    String t = this.conn.Req_Update(req);
                                      if (! t.equalsIgnoreCase("succes")){
                                             rest = "echec";
                                        }else{
                                            String tst = this.conn.Req_Update(req3);
                                             if(tst.equalsIgnoreCase("succes")){
                                                    tst = this.conn.Req_Update(req4);
                                                  rest = "succes";
                                              }
                                              
                                       }
                                    
                            }else{
                                rest = "echec";
                            }
                                
                        }
                            
               }catch(SQLException e){
                            
              }
                
            
            }
               
            }
            
        this.conn.Fermer_Connexion();
        return rest;
    }
    
    public  Object[][] lister_Retour()
    {
       Object liste[][] = null;
       String req1 = "select retour.code, \"Articles\".nom, retour.date_retour, retour.qte_rt, retour.qte_ut, retour.qte_def, retour.personne, retour.comment from deplacement, retour, \"Articles\" where \"Articles\".code = deplacement.code_art and deplacement.code = retour.code_art_dpl ORDER BY retour.date_retour;";
       String req2 = "select count(*) from  retour, deplacement, \"Articles\" where \"Articles\".code = deplacement.code_art and deplacement.code = retour.code_art_dpl; ";
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
                                liste = new Object[compt][8];
                                ResultSet rs = this.conn.Req_Select(req1);
                                int i = 0;
                               while(rs.next()){
                                        liste[i][0] = rs.getString("code");
                                        liste[i][1] = rs.getString("nom");
                                        liste[i][2] = rs.getString("date_retour");
                                        liste[i][3] = rs.getInt("qte_rt");
                                        liste[i][4] = rs.getInt("qte_ut");
                                        liste[i][5] = rs.getInt("qte_def");
                                        liste[i][6] = rs.getString("personne");
                                        liste[i][7] = rs.getString("comment");
                                        
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
    
     public  Object[][] lister_Retour(String nom)
    {
       Object liste[][] = null;
       String req1 = "select retour.code, \"Articles\".nom, retour.date_retour, retour.qte_rt, retour.qte_ut, retour.qte_def, retour.personne, retour.comment from deplacement, retour, \"Articles\" where \"Articles\".code = deplacement.code_art and deplacement.code = retour.code_art_dpl and \"Articles\".nom = '"+nom+"' ORDER BY retour.date_retour; ";
       String req2 = "select count(*) from  retour, deplacement, \"Articles\" where \"Articles\".code = deplacement.code_art and deplacement.code = retour.code_art_dpl and \"Articles\".nom = '"+nom+"';";
       int compt = 0;
       String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
       if (!ouvr.equalsIgnoreCase("succes")){
           //liste[][] = null;
       }
       else{
           
           try{
                        this.rs = this.conn.Req_Select(req2);
                        if(this.rs.next()){
                                compt = this.rs.getInt(1);
                                //System.out.print(compt);
                                liste = new Object[compt][8];
                                ResultSet rst = this.conn.Req_Select(req1);
                                   int i = 0;
                                           while(rst.next()){
                                                liste[i][0] = rst.getString("code");
                                                liste[i][1] = rst.getString("nom");
                                                liste[i][2] = rst.getString("date_retour");
                                                liste[i][3] = rst.getInt("qte_rt");
                                                liste[i][4] = rst.getInt("qte_ut");
                                                liste[i][5] = rst.getInt("qte_def");
                                                liste[i][6] = rst.getString("personne");
                                                liste[i][7] = rst.getString("comment");
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
     
     public void imprimer_dpl(String [] dt){
        // Récupère un PrinterJob
      PrinterJob job = PrinterJob.getPrinterJob();
      // Définit son contenu à imprimer
      job.setPrintable(new test_imp2(dt));
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
