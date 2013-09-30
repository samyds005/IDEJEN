package models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

/**
 * Décrivez votre classe Articles ici.
 *
 * @author (IDEJEN)
 * @version (un numéro de version ou une date)
 */
public class Articles
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private String nom;
    private String cat;
    //private String fi;
    private String et;
    private int qte;
    private Connexion conn;
    private ResultSet rs;
    
    /**
     * Constructeur d'objets de classe Articles
     */
    public Articles()
    {
        // initialisation des variables d'instance
        this.nom = null;
        this.cat = null;
        //this.fi = null;
        this.et = null;
        this.qte = 0;
        this.conn = new Connexion();
        
    }
    
    /**
     * Constructeur d'objets de classe Articles
     */
    public Articles(String nom, String cat, String et,int qte)
    {
        // initialisation des variables d'instance
        this.nom = nom;
        this.cat = cat;
        //this.fi = fi;
        this.et = et;
        this.qte = qte;
        this.conn = new Connexion();
        
    }
    // les accesseurs
    public String getNom(){
        return this.nom;
    }
    
     public String getcat(){
        return this.cat;
    }
    
    /**
     public String getfi(){
        return this.fi;
    }*/
     public String getet(){
        return this.et;
    }
    
     public int getqte(){
        return this.qte;
    }
    //les mutateurs
    public void setNom(String nom){
        this.nom = nom;
    }
    
     public void setCategorie(String cat){
        this.cat = cat;
    }
    
    /**
     public void setFiliere(String fi){
        this.fi = fi;
    }*/
    
    public void setEtat(String et){
        this.et = et;
    }
    
    public void setEtat(int qte){
        this.qte = qte;
    }
    
    
    public String generer_code(String art)
    {
       String code = null;
       String ss_art = art.substring(0,3);
       Random r = new Random();
       int valeur = 1000 + r.nextInt(10000 );
       return code = Integer.toString(valeur)+ ss_art.toUpperCase();
    }
    
    
    public boolean tester_code(String code)
    {
       boolean rep = false;
       String tst = "";
       String req = "select * from \"Articles\" where code = '"+code+"'";
        try{
            String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
            ResultSet test = this.conn.Req_Select(req);
            if (test.next())
            {
                tst = test.getString("code");
                if (code.equals(tst)){
                    rep = true;
                }else{
                    rep = false;
                }
                
            }else{
                    rep = false;
                }
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
       this.conn.Fermer_Connexion();
        return rep;
    }
    
    public boolean tester_nom(String nom)
    {
       boolean rep = false;
       String tst = "";
       String req = "select * from \"Articles\" where nom = '"+nom+"'";
        try{
            String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
            ResultSet test = this.conn.Req_Select(req);
            if (test.next())
            {
                tst = test.getString("nom");
                if (nom.equals(tst)){
                    rep = true;
                }else{
                    rep = false;
                }
                
            }else{
                    rep = false;
                }
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
       this.conn.Fermer_Connexion();
        return rep;
    }
    
    
    public String enregistrer_Articles()
    {
      String ouvr;
      String test;
      String req;
      String code = generer_code(this.nom);
      ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
      if (!ouvr.equalsIgnoreCase("succes")){
          test = "ouverture nok";
        }else{
          req = "insert into \"Articles\" values('"+code+"','"+this.nom.toLowerCase()+"','"+this.cat.toLowerCase()+"','"+this.et.toLowerCase()+"',"+this.qte+","+this.qte+")";
          test = this.conn.Req_Update(req);
          if (! test.equalsIgnoreCase("succes")){
              test = "echec";
            }
            
        }
        
      this.conn.Fermer_Connexion();
      return test;
    }
    
    public String modifier_Articles(String code)
    {
       boolean test = tester_code(code);
       String t;
       if (!test){
           t = "kod la pa bon !";
        }else{
            String req=" update \"Articles\" set nom = '"+ this.nom+"', categorie = '"+this.cat+"', etat = '"+this.et+"', quantite = quantite + "+this.qte+" , \"Quantite_init\" = \"Quantite_init\" + "+this.qte+" where code = '"+code+"'";
            String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
            if (!ouvr.equalsIgnoreCase("succes")){
                t = "ouveti a pa bon ";
            }else{
                t = this.conn.Req_Update(req);
                if (! t.equalsIgnoreCase("succes")){
                    t = "echoue";
                }
            
            }
            
        }
        
        this.conn.Fermer_Connexion();
        return t;
    }
   
    
    
    public String supprimer_Articles(String code)
    {
       boolean test = tester_code(code);
       String rest = "";
       if (!test){
           rest = "kod la pa bon !";
        }else{
            String req=" delete from \"Articles\" where code = '"+code+"'";
            String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
            if (!ouvr.equalsIgnoreCase("succes")){
                rest = "ouveti a pa bon ";
            }else{
                String t = this.conn.Req_Update(req);
                if (! t.equalsIgnoreCase("succes")){
                    rest = "echoue";
                }else{
                    rest = "succes";
                }
            
            }
            
        }
    
       this.conn.Fermer_Connexion();
       return rest;
    }
   
    
     
    public  Object[][] lister_Articles()
    {
       Object liste[][] = null;
       String req1 = "select * from \"Articles\" ";
       String req2 = "select count(*) from \"Articles\" ";
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
                                liste = new Object[compt][5];
                                this.rs = this.conn.Req_Select(req1);
                                int i = 0;
                               while(this.rs.next()){
                                        liste[i][0] = rs.getString("code");
                                        liste[i][1] = rs.getString("nom");
                                        liste[i][2] = rs.getString("categorie");
                                        //liste[i][3] = rs.getString("filiere");
                                        liste[i][3] = rs.getString("etat");
                                        liste[i][4] = rs.getInt("quantite");
                                        //liste[i][5] = rs.getInt("Quantite_init");
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

    public ArrayList rechecher_Articles(String code)
    {
       boolean t = tester_code(code);
       ArrayList rech = new ArrayList();
       if (!t){
           rech.add("kod la pa bon !");
        }else{
            String req = "select * from \"Articles\" where code = '"+code+"'";
            String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
            if (!ouvr.equalsIgnoreCase("succes")){
                rech.add("ouveti a pa bon ");
            }else{
                try{
                       ResultSet lou = this.conn.Req_Select(req);
                       while(lou.next()){
                           rech.add(lou.getString("code"));
                           rech.add(lou.getString("nom"));
                           rech.add(lou.getString("categorie"));
                           //rech.add(lou.getString("filiere"));
                           rech.add(lou.getString("etat"));
                           rech.add(lou.getInt("quantite"));
                           rech.add(lou.getInt("Quantite_init"));
                        }
                }catch(Exception e){
                   e.printStackTrace();   
                }
            
            }
            
        }
        
        this.conn.Fermer_Connexion();
        return rech;
    }
    
    public String[] rechecher_code()
    {
       String liste[] = null;
       String req1 = "select nom from \"Articles\" ";
       String req2 = "select count(*) from \"Articles\" ";
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
                                liste = new String[compt];
                                this.rs = this.conn.Req_Select(req1);
                                int i = 0;
                               while(this.rs.next()){
                                        liste[i] = rs.getString("nom");
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
    
    public  Object[][] resumer_Articles()
    {
       Object liste[][] = null;
       String req1 = "select \"Articles\".nom, \"Articles\".\"Quantite_init\", \"Articles\".quantite, SUM(retour.qte_def) as somme from deplacement, \"Articles\", retour where \"Articles\".code = deplacement.code_art and deplacement.code = retour.code_art_dpl GROUP BY \"Articles\".nom, \"Articles\".\"Quantite_init\", \"Articles\".quantite;";
       String req2 = "select count(*) as compt from deplacement, \"Articles\", retour where \"Articles\".code = deplacement.code_art and deplacement.code = retour.code_art_dpl;";
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
                                                    liste[i][0] = rst.getString("nom");
                                                    liste[i][1] = rst.getString("Quantite_init");
                                                    liste[i][2] = rst.getInt("quantite");
                                                    liste[i][3] = rst.getInt("somme");
                                                                                                       
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
}
