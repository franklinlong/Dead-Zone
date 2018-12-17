/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.menu;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import deadzone.sprite.animated.PlayerFactory;
import deadzone.utilities.Assets;
import deadzone.utilities.Database;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author niko
 */
public class PauseMenu extends javax.swing.JDialog {

    /**
     * Creates new form PauseMenu
     */
    private static boolean pause;
    public static final Object PAUSELOCK = new Object();
    public static boolean end;
    private final PlayerFactory player;

    public PauseMenu(java.awt.Frame parent, boolean modal, PlayerFactory player) {
        super(parent, modal);
        this.player = player;
        initComponents();
        setPause(true);
        end = false;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() / 2 - this.getWidth() / 2;
        int y = (int) rect.getMaxY() / 2 - this.getHeight() / 2;
        this.setLocation(x, y);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                setPause(false);
            }
        });

    }

    public static boolean isPause() {
        return pause;
    }

    public static void setPause(boolean pause) {
        synchronized (PAUSELOCK) {
            PauseMenu.pause = pause;
            PAUSELOCK.notifyAll();
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

        jLabel1 = new javax.swing.JLabel();
        Resume = new javax.swing.JButton();
        Settings = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        onlineButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 0, 0));
        setIconImage(new ImageIcon(getClass().getResource("/images/icona_frame.png")).getImage());
        setMaximumSize(new java.awt.Dimension(469, 346));
        setMinimumSize(new java.awt.Dimension(469, 346));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(469, 346));
        setResizable(false);
        setSize(new java.awt.Dimension(469, 346));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PAUSE");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(170, 20, 120, 34);

        Resume.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Resume.setText("Resume");
        Resume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResumeActionPerformed(evt);
            }
        });
        getContentPane().add(Resume);
        Resume.setBounds(170, 110, 122, 27);

        Settings.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Settings.setText("Settings");
        Settings.setMaximumSize(new java.awt.Dimension(89, 27));
        Settings.setMinimumSize(new java.awt.Dimension(89, 27));
        Settings.setPreferredSize(new java.awt.Dimension(89, 27));
        Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsActionPerformed(evt);
            }
        });
        getContentPane().add(Settings);
        Settings.setBounds(170, 160, 122, 27);

        Exit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Exit.setText("Exit");
        Exit.setMaximumSize(new java.awt.Dimension(89, 27));
        Exit.setMinimumSize(new java.awt.Dimension(89, 27));
        Exit.setPreferredSize(new java.awt.Dimension(89, 27));
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        getContentPane().add(Exit);
        Exit.setBounds(200, 300, 60, 27);

        onlineButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        onlineButton.setText("Online info");
        onlineButton.setMaximumSize(new java.awt.Dimension(89, 27));
        onlineButton.setMinimumSize(new java.awt.Dimension(89, 27));
        onlineButton.setPreferredSize(new java.awt.Dimension(89, 27));
        onlineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onlineButtonActionPerformed(evt);
            }
        });
        getContentPane().add(onlineButton);
        onlineButton.setBounds(170, 210, 122, 27);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(310, 210, 110, 25);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sfondo_senza_spari.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(-10, 0, 550, 380);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResumeActionPerformed
        // TODO add your handling code here:
        setPause(false);
        this.dispose();
    }//GEN-LAST:event_ResumeActionPerformed

    private void SettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsActionPerformed
        // TODO add your handling code here:
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(SwingUtilities.getWindowAncestor(this));
        Settings set = new Settings(topFrame, true, true);
        set.setVisible(true);
    }//GEN-LAST:event_SettingsActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
        end = true;
        setPause(false);
        if (Database.online) {
            Assets.ThreadOttieniScoreboard t2 = new Assets.ThreadOttieniScoreboard();
            t2.start();
            System.out.println(player.getOnlineID());
            Database.CancellaOnline(player.getOnlineID());
        }

        synchronized (Assets.ThreadOttieniScoreboard.TOS) {
            if (Assets.ThreadOttieniScoreboard.occupato) {
                System.out.println("SE ESCE PRIMA DI Disconnesso contattare Ciccio");
                try {
                    System.out.println("Sono occupato");
                    Assets.ThreadOttieniScoreboard.TOS.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(PauseMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.dispose();
        Menu menu = new Menu();
        MapFrame.gameMusic.stopSound();
        Menu.gameMusic.loopSound();
        menu.setVisible(true);
    }//GEN-LAST:event_ExitActionPerformed

    private void onlineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onlineButtonActionPerformed
        // TODO add your handling code here:
        if (Database.online) {
            new OnlinePause(this, player).setVisible(true);
            this.setVisible(false);
        }else{
            int w = new ImageIcon(getClass().getResource("/images/LogoBiancoENero.png")).getIconWidth() * 1 / 6;
            int h = new ImageIcon(getClass().getResource("/images/LogoBiancoENero.png")).getIconHeight() * 1 / 6;
            Image i2 = new ImageIcon(getClass().getResource("/images/LogoBiancoENero.png")).getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT);
            ImageIcon i = new ImageIcon(i2);
            JOptionPane.showConfirmDialog(rootPane, "Not connected...", "I can't do it!", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION, i);
        }
    }//GEN-LAST:event_onlineButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Exit;
    private javax.swing.JButton Resume;
    private javax.swing.JButton Settings;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton onlineButton;
    // End of variables declaration//GEN-END:variables

}
