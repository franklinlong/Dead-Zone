/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author USER
 */
public class MapFrame extends javax.swing.JFrame {

    SinglePlayer sp;
    /**
     * Creates new form MapFrame
     */
    
    private ImageIcon ridimensionaImageIcon(URL url, int nuovaW, int nuovaH){
        ImageIcon image = new ImageIcon(url);
        Image immagineScalata = image.getImage().getScaledInstance(nuovaW, nuovaH, Image.SCALE_DEFAULT);
        return new ImageIcon(immagineScalata);
    }
    
    public MapFrame(SinglePlayer sp) {
        this.sp = sp;
        
        Image iconaFrame;
        iconaFrame = new ImageIcon(getClass().getResource("icona_frame.png")).getImage();
        this.setIconImage(iconaFrame);  
        
        System.out.println("Single Player");
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dim.setSize(1920*2/5, 575);
        this.setPreferredSize(dim.getSize());
        
        
        initComponents();
        
        ImageIcon mappaSTD = ridimensionaImageIcon(getClass().getResource("map_standard.png"), jButtonStandardMap.getWidth(), jButtonStandardMap.getHeight());
        jButtonStandardMap.setIcon(mappaSTD);
        
        ImageIcon mappaFisciano = ridimensionaImageIcon(getClass().getResource("fisciano_map.png"), jButtonFisciano.getWidth(), jButtonFisciano.getHeight());
        jButtonFisciano.setIcon(mappaFisciano);
        jButtonFisciano.setBackground(new Color(0,0,0,0));
        jButtonStandardMap.setBackground(new Color(0,0,0,0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonBack = new javax.swing.JButton();
        jButtonFisciano = new javax.swing.JButton();
        jButtonStandardMap = new javax.swing.JButton();
        jButtonPlay = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DEAD ZONE");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel3.setFont(new java.awt.Font("Papyrus", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Fisciano");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(520, 90, 120, 39);

        jLabel4.setFont(new java.awt.Font("Papyrus", 1, 28)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Coming soon...");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(460, 40, 216, 39);

        jLabel2.setFont(new java.awt.Font("Papyrus", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Standard Map");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(120, 80, 170, 39);

        jButtonBack.setText("Back");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonBack);
        jButtonBack.setBounds(60, 490, 59, 25);

        jButtonFisciano.setText("jButton1");
        jButtonFisciano.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButtonFisciano.setBorderPainted(false);
        jButtonFisciano.setMaximumSize(new java.awt.Dimension(200, 200));
        jButtonFisciano.setPreferredSize(new java.awt.Dimension(200, 200));
        jButtonFisciano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFiscianoActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonFisciano);
        jButtonFisciano.setBounds(400, 140, 350, 250);

        jButtonStandardMap.setText("jButton1");
        jButtonStandardMap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButtonStandardMap.setBorderPainted(false);
        jButtonStandardMap.setMaximumSize(new java.awt.Dimension(200, 200));
        jButtonStandardMap.setPreferredSize(new java.awt.Dimension(200, 200));
        jButtonStandardMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStandardMapActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonStandardMap);
        jButtonStandardMap.setBounds(80, 130, 270, 270);

        jButtonPlay.setText("PLAY!");
        jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlayActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPlay);
        jButtonPlay.setBounds(350, 490, 65, 25);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/deadzone/sfondo_senza_spari.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-10, -10, 930, 640);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        // TODO add your handling code here:
        this.sp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlayActionPerformed
        // TODO add your handling code here:
        new Window().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonPlayActionPerformed

    private void jButtonStandardMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStandardMapActionPerformed
        // TODO add your handling code here:
        jButtonFisciano.setBorderPainted(false);
        jButtonStandardMap.setBorderPainted(true);
    }//GEN-LAST:event_jButtonStandardMapActionPerformed

    private void jButtonFiscianoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiscianoActionPerformed
        // TODO add your handling code here:
        jButtonStandardMap.setBorderPainted(false);
        jButtonFisciano.setBorderPainted(true);
    }//GEN-LAST:event_jButtonFiscianoActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonFisciano;
    private javax.swing.JButton jButtonPlay;
    private javax.swing.JButton jButtonStandardMap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
