package models;

import java.sql.ResultSet;
/**
 * Décrivez votre classe Ajout_art_fil ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Ajout_art_fil
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private String code_art;
    private String code_fil;
    private Connexion conn;
    
    /**
     * Constructeur d'objets de classe Ajout_art_fil
     */
    public Ajout_art_fil(String art, String fil)
    {
        // initialisation des variables d'instance
        this.code_art = art;
        this.code_fil =  fil;
        this.conn =  new Connexion();
    }

    /**
     * Un exemple de méthode - remplacez ce commentaire par le vôtre
     *
     * @param  y   le paramètre de la méthode
     * @return     la somme de x et de y
     */
    public String ajouter_Art_Fil()
    {
      String ouvr;
      String test = "";
      String code1 = "";
      String code2 = "";
      String req;
      Articles art = new Articles(); 
      Filiere fil = new Filiere();
      boolean c_art = art.tester_nom(this.code_art);
      boolean c_fil = fil.tester_code(this.code_fil);
      
      try{
              ouvr = this.conn.Ouvrir_Connexion("postgres", "samy123", "IDEJEN");
              if (!ouvr.equalsIgnoreCase("succes")){
                  test = "ouverture nok";
                }else
                    if(!c_art) {
                         test = "nom articles nok";   
                    }else if(!c_fil){
                        test = "nom filiere nok";
                    }else{
                        String req1 = "select code from \"Articles\" where nom = '"+this.code_art+"'";
                        String req2 = "select code from filiere where nom = '"+this.code_fil+"'";
                        ResultSet rest = this.conn.Req_Select(req1);
                        if(rest.next()){
                            code1 =  rest.getString("code");
                        
                        }
                        rest = this.conn.Req_Select(req2);
                        if(rest.next()){
                            code2 =  rest.getString("code");
                        
                        }
                                
                        req = "insert into ajouter_fil_art values('"+code1+"','"+code2+"')";
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
}
