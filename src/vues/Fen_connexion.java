package vues;

import javax.swing.*;
import models.Utilisateur;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Samy
 */
public class Fen_connexion extends javax.swing.JFrame {

    /**
     * Creates new form Fen_connexion
     */
    public Fen_connexion() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nom = new java.awt.TextField();
        mdp = new javax.swing.JPasswordField();
        btn_connecter = new javax.swing.JButton();
        btn_annuler = new javax.swing.JButton();
        erreur = new javax.swing.JLabel();
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Connexion a IDEJEN");
        setResizable(false);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/iedejen/ressource/LOGO-icone3.png")).getImage());
        setFocusable(true);
                
        erreur.setVisible(false);
        
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iedejen/ressource/LOGO-con.png"))); // NOI18N
        jLabel1.setToolTipText("");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iedejen/ressource/login2.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nom d'utilisateur");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Mot de passe");

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel5.setText("Connexion");

        btn_connecter.setText("Connecter");
        //btn_connecter.requestFocusInWindow();
        btn_connecter.setDefaultCapable(true);  //celle-ci a été appliquée sur mes 3 boutons 
        this.getRootPane().setDefaultButton(btn_connecter);   
        btn_connecter.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_connecterActionPerformed(evt);
            }
        });
        
        btn_connecter.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_connecterKeyPressed(evt);
            }
        });
        
        btn_annuler.setText("Annuler");
        btn_annuler.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_annulerActionPerformed(evt);
            }
        });

        erreur.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 51, 0), null));
        erreur.setHorizontalTextPosition(JLabel.CENTER);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_connecter)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mdp, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_annuler))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(erreur, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_connecter, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(btn_annuler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(erreur, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );
        //nom.requestFocus();
        
        pack();
        //btn_connecter.requestFocusInWindow(); 
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-432)/2, (screenSize.height-290)/2, 432, 290);
    }// </editor-fold>

    private void btn_connecterActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String n = nom.getText();
        char[] m = mdp.getPassword();
        String pwd = new String(m);
        Utilisateur usr = new Utilisateur();
        String resultat;
        resultat = usr.connecter(n,pwd);
        
        if("connexion nok".equals(resultat)){
            JOptionPane.showMessageDialog(this,"Votre nom d'utilisateur et mot de passe  sont incorrects!!");
        }else{
            
            if(resultat.equals("ADM")){
                new Fen_Principale().setVisible(true);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this,"Simple utilisateur!!");
            }
            
        }
        
    }

    private void btn_annulerActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        nom.setText("");
        mdp.setText("");
    }

     private void btn_connecterKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        // TODO add your handling code here:
        String n = nom.getText();
        char[] m = mdp.getPassword();
        String pwd = new String(m);
        Utilisateur usr = new Utilisateur();
        String resultat = usr.connecter(n,pwd);
        
        if("connexion nok".equals(resultat)){
            JOptionPane.showMessageDialog(this,"Votre nom d'utilisateur et mot de passe  sont incorrects!!");
        }else{
            if(resultat.equals("ADM")){
                new Fen_Principale().setVisible(true);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this,"Simple utilisateur!!");
            }
        }
    }
    
     public static void main(String args[]) {
          Splash_Screen wind = null; 
          try {
              wind = new Splash_Screen();  
              wind.setVisible(true);
              wind.setAlwaysOnTop(true);
              Thread.sleep(2000);
              
            } catch (InterruptedException e) {
            }
            
            try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fen_connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
             Fen_connexion fen_con = new Fen_connexion();
             fen_con.setVisible(true);
             fen_con.requestFocusInWindow();
                
            }
        });
           wind.dispose();
           
        
    } 
    
    
        /**
     * @param args the command line arguments
     */
  
    
    // Variables declaration - do not modify
    private javax.swing.JButton btn_annuler;
    private javax.swing.JButton btn_connecter;
    private javax.swing.JLabel erreur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
     private javax.swing.JPasswordField mdp;
    private java.awt.TextField nom;
    // End of variables declaration
}