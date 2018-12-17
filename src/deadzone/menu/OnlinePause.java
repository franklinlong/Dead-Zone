/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.menu;

import deadzone.sprite.animated.Player;
import deadzone.utilities.Database;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author franc
 */
public class OnlinePause extends JFrame {

    private PauseMenu pm;
    ResultSet scoreboard;
    ResultSet online;
    Player player;
    LinkedList<JLabel> sc = new LinkedList();
    LinkedList<JLabel> punt = new LinkedList();
    LinkedList<JLabel> on = new LinkedList();
    JLabel jPlayer;

    /**
     * Creates new form OnlinePause
     *
     * @param pm
     * @param player
     */
    public OnlinePause(PauseMenu pm, Player player) {
        this.pm = pm;
        this.player = player;
        initComponents();
        jPlayer = new JLabel(player.getName(), SwingConstants.CENTER);
        jPlayer.setForeground(Color.yellow);
        jPlayer.setFont(new java.awt.Font("Comic Sans MS", 1, 20));
        jPlayer.setSize(240, 40);
        jPlayer.setText(player.getName());
        jOnlinePanel.add(jPlayer);
        this.jScorePanel.setBackground(new Color(0, 0, 0, 0));
        this.jOnlinePanel.setBackground(new Color(0, 0, 0, 0));
        this.jOnlinePanel.setBackground(new Color(0, 0, 0, 0));
        this.jOnlinePane.getViewport().setOpaque(false);
        this.jOnlinePane.setViewportBorder(null);
        this.jOnlinePane.setBorder(null);
        this.scoreboard = Database.OttieniScoreboard();
        this.online = Database.OttieniOnline();
        this.aggiornaLabel();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOnlinePane = new javax.swing.JScrollPane();
        jOnlinePanel = new javax.swing.JPanel();
        back = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScorePanel = new javax.swing.JPanel();
        refresh = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(768, 575));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(768, 575));
        getContentPane().setLayout(null);

        jOnlinePane.setOpaque(false);

        jOnlinePanel.setOpaque(false);
        jOnlinePanel.setLayout(new javax.swing.BoxLayout(jOnlinePanel, javax.swing.BoxLayout.Y_AXIS));
        jOnlinePane.setViewportView(jOnlinePanel);

        getContentPane().add(jOnlinePane);
        jOnlinePane.setBounds(550, 100, 240, 400);

        back.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        back.setText("BACK");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        getContentPane().add(back);
        back.setBounds(30, 520, 100, 35);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(510, 70, 240, 25);

        jScorePanel.setOpaque(false);
        jScorePanel.setLayout(null);
        getContentPane().add(jScorePanel);
        jScorePanel.setBounds(65, 70, 310, 400);

        refresh.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        refresh.setText("REFRESH");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        getContentPane().add(refresh);
        refresh.setBounds(324, 520, 120, 35);

        jLabel1.setFont(new java.awt.Font("Papyrus", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Scoreboard");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(35, 16, 370, 50);

        jLabel2.setFont(new java.awt.Font("Papyrus", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Online Players");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(510, 16, 240, 50);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sfondo_senza_spari.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(-10, 0, 800, 590);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.pm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        scoreboard = Database.OttieniScoreboard();
        online = Database.OttieniOnline();
        this.sc.clear();
        this.on.clear();
        this.punt.clear();
        this.jOnlinePanel.removeAll();
        this.jScorePanel.removeAll();
        this.aggiornaLabel();
    }//GEN-LAST:event_refreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jOnlinePane;
    private javax.swing.JPanel jOnlinePanel;
    private javax.swing.JPanel jScorePanel;
    private javax.swing.JButton refresh;
    // End of variables declaration//GEN-END:variables

    public void aggiornaLabel() {
        java.awt.Font font = new java.awt.Font("Papyrus", 1, 20);
        int j = 0;
        try {
            while (scoreboard.next()) {
                sc.add(new JLabel());
                sc.get(j).setFont(font);
                sc.get(j).setLocation(0, j * 40);
                sc.get(j).setHorizontalAlignment(SwingConstants.LEFT);
                sc.get(j).setSize(200, 40);
                sc.get(j).setForeground(Color.white);
                sc.get(j).setText((j + 1) + ") " + scoreboard.getString("nome"));
                System.out.println(scoreboard.getString("nome"));
                jScorePanel.add(sc.get(j));
                punt.add(new JLabel());
                punt.get(j).setFont(font);
                punt.get(j).setLocation(200, j * 40);
                punt.get(j).setHorizontalAlignment(SwingConstants.RIGHT);
                punt.get(j).setSize(110, 40);
                punt.get(j).setForeground(Color.white);
                punt.get(j).setText(Integer.toString(scoreboard.getInt("punteggio")));

                System.out.println(scoreboard.getString("punteggio"));
                jScorePanel.add(punt.get(j));
                j++;
            }
            int i = 0;
            while (online.next()) {
                if ((online.getInt("id") != player.getOnlineID())) {
                    on.add(new JLabel(online.getString("nome"), SwingConstants.CENTER));
                    on.get(i).setFont(new java.awt.Font("Comic Sans MS", 1, 20));
                    on.get(i).setHorizontalAlignment(SwingConstants.CENTER);
                    on.get(i).setSize(240, 40);
                    on.get(i).setForeground(new Color(0, 204, 0));
                    jOnlinePanel.add(on.get(i));
                    i++;
                }
                this.jLabel4.setText("Count: " + Integer.toString(i + 1));
                this.jOnlinePanel.repaint();
                this.jScorePanel.repaint();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OnlinePause.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
