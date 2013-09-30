package models;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.print.PrinterJob;
import java.awt.print.Printable;
import java.awt.print.PageFormat;
/**
 * Décrivez votre classe test_imp ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class test_imp extends JPanel implements Printable 
{
    private String[] data; 
    
    public test_imp(String [] dt) { 
        this.data = dt;
    }
     
    public int print( Graphics g, PageFormat pf, int pi){
         if(pi>=1) return NO_SUCH_PAGE;
         int X = (int) pf.getImageableX(), Y = (int) pf.getImageableY();
         int W = (int) pf.getImageableWidth(), H = (int) pf.getImageableHeight();
        Font ft_g = new Font("TimesRoman",Font.PLAIN+Font.BOLD,32);
        Font ft_p = new Font("TimesRoman",Font.PLAIN,18);
        Font ft_t = new Font("TimesRoman",Font.PLAIN,12);
       
       g.setColor(Color.black);
       g.setFont(ft_g);
       g.drawString("IDEJEN",250,100);
       
       g.setColor(Color.black);
       g.setFont(ft_p);
       g.drawString("Fiche de deplacement d'articles",200,115);
       
       g.drawLine( 50,120, 600, 120);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString("Date de deplacement : ",80,180);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString(this.data[1],190,180);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString("Date retour : ",350,180);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString(this.data[2],430,180);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString("Article deplacé : ",80,240);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString(this.data[6],190,240);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString("Code Article deplacé : ",350,240);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString(this.data[0],460,240);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString("Quantité deplacée : ",80,300);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString(this.data[4],190,300);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString("Annexe : ",350,300);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString(this.data[3],400,300);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString("Nom de la personne : ",80,360);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString(this.data[5],190,360);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString("Etat de l'article : ",350,360);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString(this.data[7],430,360);
       
       g.drawLine( 50,480, 250, 480);
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString("Signature de la personne ",90,490);
       
       g.drawLine( 350,480, 550, 480);
       g.setColor(Color.black);
       g.setFont(ft_t);
       g.drawString("Signature de la caisse ",380,490);
       
       g.setColor(Color.black);
       g.setFont(ft_t);
       String nb = "N.B- Nous vous recommandons de conserver cette fiche pusqu'elle obligatoir pour le retour de l'article. Sans elle, nous pourrions pas accepter le retour";
       g.drawString(nb,80,530);
       
        return PAGE_EXISTS;
    }
    
    
}
