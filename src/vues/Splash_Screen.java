package vues;

import javax.swing.*;
import java.awt.*;
public class Splash_Screen extends JWindow{
    
        public Splash_Screen(){
                    setSize(500, 298);
                    setLocationRelativeTo(null);
                    JLabel img = new JLabel();
                    img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iedejen/ressource/LOGO-spl.png")));
                    //img.setVerticalAlignment(JLabel.CENTER);
                    //img.setHorizontalAlignment(JLabel.CENTER);
                    getContentPane().add(img);
        }
        
    
}