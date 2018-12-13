/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.menu;

import java.awt.Image;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import deadzone.utilities.Sound;
import deadzone.utilities.Utilities;

/**
 *
 * @author giova
 */
public class GameOver extends javax.swing.JFrame {

    JFrame parent;
    public static Sound soundEndGame;
    public static Clip clipEndGame;
    /**
     * Creates new form GameOver
     *
     * @param parent
     */
    public GameOver(JFrame parent) {
        Image iconaFrame;
        iconaFrame = new ImageIcon(getClass().getResource("/images/icona_frame.png")).getImage();
        this.setIconImage(iconaFrame);

        this.parent = parent;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        GameOver.clipEndGame = Utilities.LoadSound("/sound/endGame.wav");
        GameOver.soundEndGame = new Sound(clipEndGame);
        GameOver.soundEndGame.playSound();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DEAD ZONE");
        setPreferredSize(new java.awt.Dimension(768, 575));
        setResizable(false);
        getContentPane().setLayout(null);

        jButton1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButton1.setText("Back to home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(260, 470, 250, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/game-over.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-10, 0, 780, 580);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        parent.dispose();
        Menu menu = new Menu();
        menu.setVisible(true);
        GameOver.soundEndGame.stopSound();
        Menu.gameMusic.loopSound();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
