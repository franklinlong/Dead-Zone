/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.menu;

import deadzone.Context;
import deadzone.ModalityDemo;
import deadzone.utilities.Utilities;
import deadzone.utilities.Assets;
import deadzone.utilities.Sound;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class Menu extends javax.swing.JFrame {

    Dimension dim;
    public static Sound gameMusic;
    public static Clip gameClip;
    private boolean back = false;
    private SinglePlayer sp;

    private ImageIcon ridimensionaImageIcon(URL url, int nuovaW, int nuovaH) {
        ImageIcon image = new ImageIcon(url);
        Image immagineScalata = image.getImage().getScaledInstance(nuovaW, nuovaH, Image.SCALE_DEFAULT);
        return new ImageIcon(immagineScalata);
    }

    public Menu() {
        Image iconaFrame;
        iconaFrame = new ImageIcon(getClass().getResource("/images/icona_frame.png")).getImage();
        this.setIconImage(iconaFrame);

        dim = Toolkit.getDefaultToolkit().getScreenSize();
        dim.setSize(1920 * 2 / 5, 575);
        this.setPreferredSize(dim.getSize());
        ImageIcon immagineSfondo = ridimensionaImageIcon(getClass().getResource("/images/sfondo_senza_logo.png"), dim.width, dim.height);

        int w = new ImageIcon(getClass().getResource("/images/mano_zombie3.gif")).getIconWidth() * 1 / 3;
        int h = new ImageIcon(getClass().getResource("/images/mano_zombie3.gif")).getIconHeight() * 1 / 3;
        ImageIcon gif = ridimensionaImageIcon(getClass().getResource("/images/mano_zombie3.gif"), w, h);

        w = new ImageIcon(getClass().getResource("/images/LogoBiancoENero.png")).getIconWidth() * 1 / 3;
        h = new ImageIcon(getClass().getResource("/images/LogoBiancoENero.png")).getIconHeight() * 1 / 3;
        ImageIcon immagineLogo = ridimensionaImageIcon(getClass().getResource("/images/LogoBiancoENero.png"), w, h);

        initComponents();

        PauseMenu.end = false;

        sfondo.setPreferredSize(dim.getSize());
        sfondo.setIcon(immagineSfondo);
        gifMano.setIcon(gif);
        logo.setIcon(immagineLogo);

        if (gameMusic == null) {
            this.gameClip = Utilities.LoadSound("/sound/MenuSong.wav");
            this.gameMusic = new Sound(gameClip);
            this.gameMusic.loopSound();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        gifMano = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        sfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DEAD ZONE");
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setAlignmentX(0.0F);
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(dim.getSize());
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Single Player");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 171, 15, 80);
        jPanel1.add(jButton1, gridBagConstraints);

        jButton2.setText("Multiplayer");
        jButton2.setMaximumSize(new java.awt.Dimension(107, 25));
        jButton2.setMinimumSize(new java.awt.Dimension(107, 25));
        jButton2.setPreferredSize(new java.awt.Dimension(107, 25));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 171, 15, 80);
        jPanel1.add(jButton2, gridBagConstraints);

        jButton3.setText("Demo");
        jButton3.setMaximumSize(new java.awt.Dimension(107, 25));
        jButton3.setMinimumSize(new java.awt.Dimension(107, 25));
        jButton3.setPreferredSize(new java.awt.Dimension(107, 25));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 171, 15, 80);
        jPanel1.add(jButton3, gridBagConstraints);

        jButton4.setText("Settings");
        jButton4.setMaximumSize(new java.awt.Dimension(107, 25));
        jButton4.setMinimumSize(new java.awt.Dimension(107, 25));
        jButton4.setPreferredSize(new java.awt.Dimension(107, 25));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 171, 40, 80);
        jPanel1.add(jButton4, gridBagConstraints);

        gifMano.setMaximumSize(new java.awt.Dimension(200, 200));
        gifMano.setPreferredSize(new java.awt.Dimension(200, 200));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(13, 50, 0, 5);
        jPanel1.add(gifMano, gridBagConstraints);

        logo.setMaximumSize(new java.awt.Dimension(200, 200));
        logo.setPreferredSize(new java.awt.Dimension(200, 200));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(30, 130, 30, 80);
        jPanel1.add(logo, gridBagConstraints);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 930, 530);

        sfondo.setMaximumSize(new java.awt.Dimension(1000, 1000));
        sfondo.setPreferredSize(getPreferredSize());
        getContentPane().add(sfondo);
        sfondo.setBounds(0, -20, 1020, 610);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int w = new ImageIcon(getClass().getResource("/images/LogoBiancoENero.png")).getIconWidth() * 1 / 6;
        int h = new ImageIcon(getClass().getResource("/images/LogoBiancoENero.png")).getIconHeight() * 1 / 6;
        ImageIcon i = ridimensionaImageIcon(getClass().getResource("/images/LogoBiancoENero.png"), w, h);

        JOptionPane.showConfirmDialog(rootPane, "   Coming soon...", "Multiplayer DeadZone", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION, i);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(!isBack()){
            synchronized(Assets.ThreadOttieniScoreboard.TOS){
                System.out.println("Sto nel synch");
                if(Assets.ThreadOttieniScoreboard.occupato){
                    System.out.println("Sono occupato e quindi aspetto");
                    try {
                        Assets.ThreadOttieniScoreboard.TOS.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            sp = new SinglePlayer(this);
            sp.setVisible(true);
            this.setVisible(false);
        }else{
            sp.setVisible(true);
            this.setVisible(false);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        LoadingScreen ls = LoadingScreen.getLoadingScreen();
        ls.setVisible(true);
        new LoadingThread(sp,ls).start();
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Settings set = new Settings(this, true, false);
        set.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Assets.init();
                new Menu().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel gifMano;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel sfondo;
    // End of variables declaration//GEN-END:variables

    public boolean isBack() {
        return back;
    }

    public void setBack(boolean back) {
        this.back = back;
    }


}
