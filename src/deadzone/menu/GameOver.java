/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.menu;

import deadzone.sprite.animated.Player;
import deadzone.sprite.animated.PlayerDemo;
import deadzone.utilities.Assets;
import deadzone.utilities.Database;
import static deadzone.utilities.Database.online;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import deadzone.utilities.Sound;
import deadzone.utilities.Utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author giova
 */
public class GameOver extends javax.swing.JFrame {

    JFrame parent;
    public static Sound soundEndGame;
    public static Clip clipEndGame;
    private Player player;

    /**
     * Creates new form GameOver
     *
     * @param parent
     */
    public GameOver(JFrame parent, Player player) {

        this.player = player;

        Image iconaFrame;
        iconaFrame = new ImageIcon(getClass().getResource("/images/icona_frame.png")).getImage();
        this.setIconImage(iconaFrame);

        this.parent = parent;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.jButton1.setVisible(false);
        this.jLabel2.setVisible(true);

        GameOver.clipEndGame = Utilities.LoadSound("/sound/endGame.wav");
        GameOver.soundEndGame = new Sound(clipEndGame);
        GameOver.soundEndGame.loopSound();

        if (Database.online) {
            this.aggiornaDB();
            //this.jLabel2.setVisible(false);
        } else {
            jLabel2.setText("OFFLINE! Impossible to send your score to Database");
            Assets.ThreadOttieniScoreboard t = new Assets.ThreadOttieniScoreboard();
            t.start();
        }

        this.jButton1.setVisible(true);
        Database.online = true;

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
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DEAD ZONE");
        setMaximumSize(new java.awt.Dimension(768, 575));
        setMinimumSize(new java.awt.Dimension(768, 575));
        setPreferredSize(new java.awt.Dimension(768, 575));
        setResizable(false);
        getContentPane().setLayout(null);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Century", 1, 24)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blood.png"))); // NOI18N
        jButton1.setText("Back to home");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(290, 430, 200, 50);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(220, 430, 320, 100);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Chiller", 0, 150)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GAME OVER");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(90, 120, 600, 220);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sfondo_senza_spari.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 780, 590);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        parent.dispose();
//        synchronized(connectionThread.TOS){
//            try {
//                if(connectionThread.occupato)
//                connectionThread.TOS.wait();
//            } catch (InterruptedException ex) {
//                Logger.getLogger(GameOver.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        Menu menu = new Menu();
        menu.setVisible(true);
        GameOver.soundEndGame.stopSound();
        Menu.gameMusic.loopSound();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    public void aggiornaDB() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            this.jLabel2.setText("Connection...");
            conn = DriverManager.getConnection(Database.s1, Database.user, Database.pass);
            this.jLabel2.setText("Connection estabilished...");
            stmt = conn.createStatement();
            if (!(player instanceof PlayerDemo)) {
                System.out.println("Database connection");
                this.jLabel2.setText("Sending your score to the database");
                String query = "SELECT MAX(id) FROM scoreboard";
                rs = stmt.executeQuery(query);
                rs.next();
                int id = (int) rs.getInt(1) + 1;
                String sql = "INSERT INTO scoreboard VALUES ('" + id + "','" + player.getName() + "','" + player.getPunteggioAttuale() + "')";
                stmt.executeUpdate(sql);
            }

            this.jLabel2.setText("Done! Downloading scoreboard...");
            String query = " SELECT * FROM scoreboard ORDER BY punteggio DESC LIMIT 10";
            Assets.rs = stmt.executeQuery(query);
            Database.online = true;

        } catch (org.postgresql.util.PSQLException ex) {
            online = false;
            System.out.println("Non connesso");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println(" aggiungi score + scarica scoreboard fatto");

    }
}
