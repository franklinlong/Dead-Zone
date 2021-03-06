/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.menu;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author USER
 */
public class LoadingScreen extends javax.swing.JFrame{
    private static LoadingScreen instanza = null;
    /**
     * Creates new form LoadingScreen
     */
    private ImageIcon ridimensionaImageIcon(URL url, int nuovaW, int nuovaH) {
        ImageIcon image = new ImageIcon(url);
        Image immagineScalata = image.getImage().getScaledInstance(nuovaW, nuovaH, Image.SCALE_DEFAULT);
        return new ImageIcon(immagineScalata);
    }
    
    
    public static synchronized LoadingScreen getLoadingScreen(){
        if (instanza == null)
            instanza = new LoadingScreen();
        return instanza;
    }
    
    private LoadingScreen(){
        Image iconaFrame;
        iconaFrame = new ImageIcon(getClass().getResource("/images/icona_frame.png")).getImage();
        this.setIconImage(iconaFrame);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dim.setSize(1920 * 2 / 7, 400);
        this.setPreferredSize(dim.getSize());
        
        int w = new ImageIcon(getClass().getResource("/images/LogoBiancoENero.png")).getIconWidth() * 1 / 3;
        int h = new ImageIcon(getClass().getResource("/images/LogoBiancoENero.png")).getIconHeight() * 1 / 3;
        ImageIcon immagineLogo = ridimensionaImageIcon(getClass().getResource("/images/LogoBiancoENero.png"), w, h);
        
        w = new ImageIcon(getClass().getResource("/images/mano_zombie3.gif")).getIconWidth() * 1 / 3;
        h = new ImageIcon(getClass().getResource("/images/mano_zombie3.gif")).getIconHeight() * 1 / 3;
        ImageIcon gif = ridimensionaImageIcon(getClass().getResource("/images/mano_zombie3.gif"), w, h);
        
        initComponents(); 
        jLabel4.setIcon(immagineLogo);
        jLabel5.setIcon(gif);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DEAD ZONE");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(195, 10, 180, 170);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(270, 270, 140, 70);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Loading...");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(240, 240, 120, 50);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(200, 220, 180, 210);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sfondo_senza_spari.png")));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-10, -10, 680, 450);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables


}
