package models;

import java.sql.ResultSet;
import java.util.Random;

public class Filiere{
    private String code;
    private String nom;
    private String desc;
    private Connexion conn;
    private ResultSet rs;
    
    public Filiere(){
        this.nom = "";
        this.desc = "";
        this.conn = new Connexion();
    }
    
    public Filiere(String n, String d){
        this.nom = n;
        this.desc = d;
        this.conn = new Connexion();

    }
    
    public String generer_code(String fil)
    {
       String code = null;
       String ss_fil = fil.substring(0,3);
       Random r = new Random();
       int valeur = 1000 + r.nextInt(10000 );
       return code = Integer.toString(valeur)+ ss_fil.toUpperCase();
    }
    
    public String enregistrer_Filiere()
    {
      String ouvr;
      String test = "";
      String req;
      String code = generer_code(this.nom);
      try{
              ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
              if (!ouvr.equalsIgnoreCase("succes")){
                  test = "ouverture nok";
                }else{
                  req = "insert into filiere values('"+code+"','"+this.nom.toLowerCase()+"','"+this.desc+"')";
                  test = this.conn.Req_Update(req);
                  if (! test.equalsIgnoreCase("succes")){
                      test = "echec";
                    }
                    
                }
                
      }catch(Exception e){
         e.printStackTrace();
        
      }finally{
        this.conn.Fermer_Connexion();
      }
        
      
      return test;
    }
    
    public String[] rechecher_code()
    {
       String liste[] = null;
       String req1 = "select nom from filiere ";
       String req2 = "select count(*) from filiere ";
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
    
    public boolean tester_code(String code)
    {
       boolean rep = false;
       String tst = "";
       String req = "select * from Filiere where nom = '"+code+"'";
        try{
            String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
            ResultSet test = this.conn.Req_Select(req);
            if (test.next())
            {
                tst = test.getString("nom");
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
    
    
}