package models;

import java.lang.*;
import java.util.Random;
import java.util.ArrayList;
import java.sql.ResultSet;
/**
 * Décrivez votre classe Utilisateur ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Utilisateur
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private String nom;
    private String mdp;
    private String type;
    private String desc;
    private Connexion conn;
    private ResultSet rs;

    /**
     * Constructeur d'objets de classe Utilisateur
     */
    public Utilisateur()
    {
        // initialisation des variables d'instance
        nom = "";
        mdp = "";
        type = "";
        desc = "";
        this.conn = new Connexion();
    }

    public Utilisateur(String nom, String mdp, String type, String desc)
    {
        // initialisation des variables d'instance
        nom = nom;
        mdp = mdp;
        type = type;
        desc = desc;
        this.conn = new Connexion();
    }
    
    public String generer_code(String nom)
    {
       String code = null;
       String ss_nom = nom.substring(0,3);
       Random r = new Random();
       int valeur = 1000 + r.nextInt(10000 );
       return code = Integer.toString(valeur)+ ss_nom.toUpperCase();
    }
    
    
    public boolean tester_nom(String nom)
    {
       boolean rep = false;
       String req = "select * from \"Utilisateur\" where nom = '"+nom+"'";
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
    
    /**
     * Un exemple de méthode - remplacez ce commentaire par le vôtre
     *
     * @param  y   le paramètre de la méthode
     * @return     la somme de x et de y
     */
    public String ajouter_Utilisateur()
    {
        // Insérez votre code ici
        String code = generer_code(this.nom);
        String eng = "";
        String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
        if (tester_nom(this.nom)){
            eng = "ce nom existe";
        }else{
            if(!ouvr.equalsIgnoreCase("succes")){
                eng = "ouverture nok";
            }else{
               String req = "insert into \"Utilisateur\" values('"+code+"','"+this.nom+"','"+this.mdp+"','"+this.desc+"')";
               eng = this.conn.Req_Update(req);
               if (!eng.equalsIgnoreCase("succes")){
                    eng = "echec";
                }
            }
        
        }
        this.conn.Fermer_Connexion();
        return eng;
    }
    
    public String connecter(String nom, String mdp)
    {
        // Insérez votre code ici
       String rep = "";
       String req = "select * from \"Utilisateur\" where nom = '"+nom+"' and mdp = '"+mdp+"'";
        try{
            String ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
            ResultSet test = this.conn.Req_Select(req);
            if (test.next())
            {
                rep = test.getString("type");
            }else{
                    rep = "connexion nok";
                }
        
        }catch(Exception e){
            rep = "ouverture nok";
        }
        
       this.conn.Fermer_Connexion();
       return rep;
        
    }
    
    public  Object[][] lister_Utilisateur()
    {
       Object liste[][] = null;
       String req1 = "select * from \"Utilisateur\" ";
       String req2 = "select count(*) from \"Utilisateur\" ";
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
                                liste = new Object[compt][4];
                                this.rs = this.conn.Req_Select(req1);
                                int i = 0;
                               while(this.rs.next()){
                                        liste[i][0] = rs.getString("code");
                                        liste[i][1] = rs.getString("nom");
                                        liste[i][3] = rs.getString("desc");
                                        liste[i][4] = rs.getString("type");
                                        
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
