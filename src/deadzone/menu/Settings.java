/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.menu;

import java.awt.*;
import javax.swing.*;
import sprite.animated.Player;

/**
 *
 * @author niko
 */
public class Settings extends javax.swing.JDialog {

    public static boolean soundEffects = true;
    public static boolean soundMusic = true;
    public static boolean padArrows = false;

    /**
     * Creates new form Settings
     */
    public Settings(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ButtonGroup bg = new ButtonGroup();
        bg.add(this.awsd);
        bg.add(this.arrows);

        if (!Menu.gameMusic.isRunning()) {
            this.music.setText("OFF");
        }

        if (!soundEffects) {
            this.effects.setText("OFF");
        }

        if (padArrows) {
            this.arrows.setSelected(true);
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() / 2 - this.getWidth() / 2;
        int y = (int) rect.getMaxY() / 2 - this.getHeight() / 2;
        this.setLocation(x, y);

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
        jLabel2 = new javax.swing.JLabel();
        effects = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        awsd = new javax.swing.JRadioButton();
        music = new javax.swing.JButton();
        arrows = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(500, 500));
        setResizable(false);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Settings");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel2.setText("Effects");

        effects.setText("ON");
        effects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                effectsActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settings/awsd.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel1.setText("Music");

        awsd.setSelected(true);
        awsd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                awsdActionPerformed(evt);
            }
        });

        music.setText("ON");
        music.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musicActionPerformed(evt);
            }
        });

        arrows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrowsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabel3)))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(music, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(effects, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(awsd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(arrows)
                .addGap(132, 132, 132))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(music, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(effects, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(awsd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(arrows, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void musicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musicActionPerformed
        // TODO add your handling code here:
        String text = this.music.getText();
        if (text.compareTo("ON") == 0) {
            Menu.gameMusic.stopSound();
            soundMusic = false;
            this.music.setText("OFF");
        } else {
            Menu.gameMusic.loopSound();
            soundMusic = true;
            this.music.setText("ON");
        }
    }//GEN-LAST:event_musicActionPerformed

    private void awsdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_awsdActionPerformed
        // TODO add your handling code here:
        padArrows = false;
    }//GEN-LAST:event_awsdActionPerformed

    private void effectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_effectsActionPerformed
        // TODO add your handling code here:
        String text = this.effects.getText();
        if (text.compareTo("ON") == 0) {
            soundEffects = false;
            this.effects.setText("OFF");
        } else {
            soundEffects = true;
            this.effects.setText("ON");
        }
    }//GEN-LAST:event_effectsActionPerformed

    private void arrowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrowsActionPerformed
        // TODO add your handling code here:
        padArrows = true;
    }//GEN-LAST:event_arrowsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton arrows;
    private javax.swing.JRadioButton awsd;
    private javax.swing.JButton effects;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton music;
    // End of variables declaration//GEN-END:variables
}