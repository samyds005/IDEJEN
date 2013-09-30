package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.*;
/**
 * Write a description of class Connexion here.
 * 
 * @author (Rose Louvia Paul) 
 * @version (02/09/2013)
 */
public class Connexion
{
    // instance variables - replace the example below with your own
    private Connection con;
    private Statement st;
    private ResultSet rs;
    

    /**
     * Constructor for objects of class Connexion
     */
    public Connexion()
    {
        // initialise instance variables
        this.con = null;
        this.st = null;
        this.rs = null;
    }
    
    public String Ouvrir_Connexion(String usr, String pwd, String bd)
    {
            
        String r;
        String url = "jdbc:postgresql://localhost:5432/"+bd;
        String user = usr;
        String passwd = pwd;
        
        try {
                Class.forName("org.postgresql.Driver");
                //Création d'un objet connection
                this.con = DriverManager.getConnection(url, user, passwd);
                //Création d'un objet Statement
                this.st = con.createStatement();
                r = "succes";
                                
            } catch (Exception e) {
                    r = e.toString();
                }
                
        return r;
    }

    public void Fermer_Connexion()
    {
        try
        {
            this.con.close();
            this.st.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet Req_Select(String req) throws SQLException
    {
       this.rs = this.st.executeQuery(req);
       return this.rs;
    
    }

    public String Req_Update(String req)
    {
        String resultat;
        
      try{
          this.st.executeUpdate(req);
          resultat = "succes";
        
        }
        catch(Exception ex)
        {
            resultat = ex.toString();
        
        }
        
        return resultat;
    }

    
    
}
