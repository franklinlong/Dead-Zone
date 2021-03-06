/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import deadzone.utilities.Score;
import deadzone.utilities.Scoreboard;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import deadzone.utilities.Assets;
import deadzone.utilities.Database;

/**
 *
 * @author franc
 */
public class SinglePlayer extends javax.swing.JFrame {

    private boolean male;
    Menu menu;

    private ImageIcon ridimensionaImageIcon(URL url, int nuovaW, int nuovaH) {
        ImageIcon image = new ImageIcon(url);
        Image immagineScalata = image.getImage().getScaledInstance(nuovaW, nuovaH, Image.SCALE_DEFAULT);
        return new ImageIcon(immagineScalata);
    }

    /**
     * Creates new form SinglePlayer
     * @param menu
     */
    public SinglePlayer(Menu menu) {
        this.menu = menu;
        Image iconaFrame;
        iconaFrame = new ImageIcon(getClass().getResource("/images/icona_frame.png")).getImage();
        this.setIconImage(iconaFrame);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dim.setSize(1920 * 2 / 5, 575);
        this.setPreferredSize(dim.getSize());

        initComponents();
        if (!Database.online) {
            int w = new ImageIcon(getClass().getResource("/images/LogoBiancoENero.png")).getIconWidth() * 1 / 6;
            int h = new ImageIcon(getClass().getResource("/images/LogoBiancoENero.png")).getIconHeight() * 1 / 6;
            ImageIcon i = ridimensionaImageIcon(getClass().getResource("/images/LogoBiancoENero.png"), w, h);
            JOptionPane.showConfirmDialog(rootPane, "Not connected... local scoreboard loaded", "Warning", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION, i);
            this.jLabel4.setIcon(new ImageIcon(getClass().getResource("/images/onlinerosso.png")));
            this.jLabel5.setText("offline");
        }else{
            this.jLabel4.setIcon(new ImageIcon(getClass().getResource("/images/onlineverde.png")));
            this.jLabel5.setText("online");
        }
        List<javax.swing.JLabel> p = new java.util.ArrayList();
        p.add(jScore1);
        p.add(jScore2);
        p.add(jScore3);
        p.add(jScore4);
        p.add(jScore5);
        p.add(jScore6);
        p.add(jScore7);
        p.add(jScore8);
        p.add(jScore9);
        p.add(jScore10);

        List<javax.swing.JLabel> s = new java.util.ArrayList();
        s.add(jPunt1);
        s.add(jPunt2);
        s.add(jPunt3);
        s.add(jPunt4);
        s.add(jPunt5);
        s.add(jPunt6);
        s.add(jPunt7);
        s.add(jPunt8);
        s.add(jPunt9);
        s.add(jPunt10);

        sfondo.setPreferredSize(dim.getSize());
        jTextField1.setBackground(new Color(0, 0, 0, 0));
        jButtonUomo.setBackground(new Color(0, 0, 0, 0));
        jButtonDonna.setBackground(new Color(0, 0, 0, 0));

        ImageIcon i = ridimensionaImageIcon(getClass().getResource("/images/soldato_uomo.png"), jButtonUomo.getWidth(), jButtonUomo.getHeight());
        jButtonUomo.setIcon(i);

        ImageIcon i2 = ridimensionaImageIcon(getClass().getResource("/images/soldato_donna.png"), jButtonDonna.getWidth(), jButtonDonna.getHeight());
        jButtonDonna.setIcon(i2);

        if (Database.online) {
            int j = 0;           
            try {
                while (Assets.rs.next() && j < 10) {
                    p.get(j).setText((j+1) + ")" + Assets.rs.getString("nome"));
                    s.get(j).setText(Integer.toString(Assets.rs.getInt("punteggio")));
                    j++;
                }

            } catch (SQLException ex) {
                Logger.getLogger(SinglePlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Scoreboard scoreboard = new Scoreboard();
            List<Score> scoreList = scoreboard.getScoreboard();

            for (int j = 0; j < scoreList.size(); j++) {
                p.get(j).setText((j + 1) + ")" + scoreList.get(j).getPlayer());
                s.get(j).setText(Integer.toString(scoreList.get(j).getScore()));
            }
        }
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (jTextField1.getText().length() >= 10 || e.getKeyChar() == ' ') {
                    e.consume();
                }
            }
        });

    }

    public String getPlayerName() {
        return this.jTextField1.getText();
    }

    public boolean isMale() {
        return male;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScoreboard = new javax.swing.JLabel();
        jScore1 = new javax.swing.JLabel();
        jScore3 = new javax.swing.JLabel();
        jScore10 = new javax.swing.JLabel();
        jScore2 = new javax.swing.JLabel();
        jScore4 = new javax.swing.JLabel();
        jScore5 = new javax.swing.JLabel();
        jScore6 = new javax.swing.JLabel();
        jScore7 = new javax.swing.JLabel();
        jScore8 = new javax.swing.JLabel();
        jScore9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPunt1 = new javax.swing.JLabel();
        jPunt2 = new javax.swing.JLabel();
        jPunt3 = new javax.swing.JLabel();
        jPunt4 = new javax.swing.JLabel();
        jPunt5 = new javax.swing.JLabel();
        jPunt6 = new javax.swing.JLabel();
        jPunt7 = new javax.swing.JLabel();
        jPunt8 = new javax.swing.JLabel();
        jPunt9 = new javax.swing.JLabel();
        jPunt10 = new javax.swing.JLabel();
        jButtonDonna = new javax.swing.JButton();
        jButtonUomo = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        jButtonPlay = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sfondo = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DEAD ZONE");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Papyrus", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Select your avatar:");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(90, 140, 216, 39);

        jTextField1.setBackground(new java.awt.Color(255, 51, 51));
        jTextField1.setFont(new java.awt.Font("Papyrus", 1, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        jTextField1.setMinimumSize(new java.awt.Dimension(193, 39));
        jTextField1.setOpaque(false);
        jTextField1.setPreferredSize(new java.awt.Dimension(193, 39));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(100, 60, 193, 39);

        jScoreboard.setFont(new java.awt.Font("Papyrus", 1, 24)); // NOI18N
        jScoreboard.setForeground(new java.awt.Color(255, 255, 255));
        jScoreboard.setText("          Scoreboard:");
        jScoreboard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jScoreboard);
        jScoreboard.setBounds(450, 30, 290, 30);

        jScore1.setBackground(new java.awt.Color(255, 255, 255));
        jScore1.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jScore1.setForeground(new java.awt.Color(255, 255, 255));
        jScore1.setText("1)");
        getContentPane().add(jScore1);
        jScore1.setBounds(440, 90, 200, 30);

        jScore3.setBackground(new java.awt.Color(255, 153, 255));
        jScore3.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jScore3.setForeground(new java.awt.Color(255, 255, 255));
        jScore3.setText("3)");
        getContentPane().add(jScore3);
        jScore3.setBounds(440, 170, 200, 30);

        jScore10.setBackground(new java.awt.Color(255, 153, 255));
        jScore10.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jScore10.setForeground(new java.awt.Color(255, 255, 255));
        jScore10.setText("10)");
        getContentPane().add(jScore10);
        jScore10.setBounds(440, 450, 200, 30);

        jScore2.setBackground(new java.awt.Color(255, 153, 255));
        jScore2.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jScore2.setForeground(new java.awt.Color(255, 255, 255));
        jScore2.setText("2)");
        getContentPane().add(jScore2);
        jScore2.setBounds(440, 130, 200, 30);

        jScore4.setBackground(new java.awt.Color(255, 153, 255));
        jScore4.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jScore4.setForeground(new java.awt.Color(255, 255, 255));
        jScore4.setText("4)");
        getContentPane().add(jScore4);
        jScore4.setBounds(440, 210, 200, 30);

        jScore5.setBackground(new java.awt.Color(255, 153, 255));
        jScore5.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jScore5.setForeground(new java.awt.Color(255, 255, 255));
        jScore5.setText("5)");
        getContentPane().add(jScore5);
        jScore5.setBounds(440, 250, 200, 30);

        jScore6.setBackground(new java.awt.Color(255, 153, 255));
        jScore6.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jScore6.setForeground(new java.awt.Color(255, 255, 255));
        jScore6.setText("6)");
        getContentPane().add(jScore6);
        jScore6.setBounds(440, 290, 200, 30);

        jScore7.setBackground(new java.awt.Color(255, 153, 255));
        jScore7.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jScore7.setForeground(new java.awt.Color(255, 255, 255));
        jScore7.setText("7)");
        getContentPane().add(jScore7);
        jScore7.setBounds(440, 330, 200, 30);

        jScore8.setBackground(new java.awt.Color(255, 153, 255));
        jScore8.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jScore8.setForeground(new java.awt.Color(255, 255, 255));
        jScore8.setText("8)");
        getContentPane().add(jScore8);
        jScore8.setBounds(440, 370, 200, 30);

        jScore9.setBackground(new java.awt.Color(255, 153, 255));
        jScore9.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jScore9.setForeground(new java.awt.Color(255, 255, 255));
        jScore9.setText("9)");
        getContentPane().add(jScore9);
        jScore9.setBounds(440, 410, 200, 30);

        jLabel3.setFont(new java.awt.Font("Papyrus", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Insert your name:");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(100, 20, 189, 39);

        jPunt1.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jPunt1.setForeground(new java.awt.Color(255, 255, 255));
        jPunt1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jPunt1);
        jPunt1.setBounds(645, 90, 110, 30);

        jPunt2.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jPunt2.setForeground(new java.awt.Color(255, 255, 255));
        jPunt2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jPunt2);
        jPunt2.setBounds(645, 130, 110, 30);

        jPunt3.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jPunt3.setForeground(new java.awt.Color(255, 255, 255));
        jPunt3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jPunt3);
        jPunt3.setBounds(645, 170, 110, 30);

        jPunt4.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jPunt4.setForeground(new java.awt.Color(255, 255, 255));
        jPunt4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jPunt4);
        jPunt4.setBounds(645, 210, 110, 30);

        jPunt5.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jPunt5.setForeground(new java.awt.Color(255, 255, 255));
        jPunt5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jPunt5);
        jPunt5.setBounds(645, 250, 110, 30);

        jPunt6.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jPunt6.setForeground(new java.awt.Color(255, 255, 255));
        jPunt6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jPunt6);
        jPunt6.setBounds(645, 290, 110, 30);

        jPunt7.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jPunt7.setForeground(new java.awt.Color(255, 255, 255));
        jPunt7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jPunt7);
        jPunt7.setBounds(645, 330, 110, 30);

        jPunt8.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jPunt8.setForeground(new java.awt.Color(255, 255, 255));
        jPunt8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jPunt8);
        jPunt8.setBounds(645, 370, 110, 30);

        jPunt9.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jPunt9.setForeground(new java.awt.Color(255, 255, 255));
        jPunt9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jPunt9);
        jPunt9.setBounds(645, 410, 110, 30);

        jPunt10.setFont(new java.awt.Font("Papyrus", 1, 20)); // NOI18N
        jPunt10.setForeground(new java.awt.Color(255, 255, 255));
        jPunt10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jPunt10);
        jPunt10.setBounds(645, 450, 110, 30);

        jButtonDonna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButtonDonna.setBorderPainted(false);
        jButtonDonna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDonna.setOpaque(false);
        jButtonDonna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDonnaActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonDonna);
        jButtonDonna.setBounds(220, 190, 130, 230);

        jButtonUomo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButtonUomo.setBorderPainted(false);
        jButtonUomo.setOpaque(false);
        jButtonUomo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUomoActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonUomo);
        jButtonUomo.setBounds(60, 190, 130, 230);

        jButtonBack.setText("Back");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonBack);
        jButtonBack.setBounds(60, 490, 59, 25);

        jButtonPlay.setText("Select Map");
        jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlayActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPlay);
        jButtonPlay.setBounds(330, 490, 100, 25);

        jLabel4.setMaximumSize(new java.awt.Dimension(20, 20));
        jLabel4.setMinimumSize(new java.awt.Dimension(20, 20));
        jLabel4.setPreferredSize(new java.awt.Dimension(20, 20));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(660, 490, 20, 20);

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
        jLabel5.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel5.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel5.setPreferredSize(new java.awt.Dimension(60, 16));
        getContentPane().add(jLabel5);
        jLabel5.setBounds(690, 490, 60, 16);

        sfondo.setIcon(new ImageIcon(getClass().getResource("/images/sfondo_senza_spari.png")));
        getContentPane().add(sfondo);
        sfondo.setBounds(-10, -10, 930, 640);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButtonUomoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUomoActionPerformed
        // TODO add your handling code here:
        jButtonDonna.setBorderPainted(false);
        jButtonUomo.setBorderPainted(true);
        this.male = true;
    }//GEN-LAST:event_jButtonUomoActionPerformed

    private void jButtonDonnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDonnaActionPerformed
        // TODO add your handling code here:
        jButtonUomo.setBorderPainted(false);
        jButtonDonna.setBorderPainted(true);
        this.male = false;
    }//GEN-LAST:event_jButtonDonnaActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        // TODO add your handling code here:
        this.menu.setBack(true);
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlayActionPerformed
        // TODO add your handling code here:
        if (!jTextField1.getText().isEmpty() && (jButtonUomo.isBorderPainted() || jButtonDonna.isBorderPainted())) {
            new MapFrame(this).setVisible(true);
            this.setVisible(false);
        } else {
            int w = new ImageIcon(getClass().getResource("/images/LogoBiancoENero.png")).getIconWidth() * 1 / 6;
            int h = new ImageIcon(getClass().getResource("/images/LogoBiancoENero.png")).getIconHeight() * 1 / 6;
            ImageIcon i = ridimensionaImageIcon(getClass().getResource("/images/LogoBiancoENero.png"), w, h);
            JOptionPane.showConfirmDialog(rootPane, "Please select the properties of the character", "Player selection DeadZone", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION, i);

        }

    }//GEN-LAST:event_jButtonPlayActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonDonna;
    private javax.swing.JButton jButtonPlay;
    private javax.swing.JButton jButtonUomo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jPunt1;
    private javax.swing.JLabel jPunt10;
    private javax.swing.JLabel jPunt2;
    private javax.swing.JLabel jPunt3;
    private javax.swing.JLabel jPunt4;
    private javax.swing.JLabel jPunt5;
    private javax.swing.JLabel jPunt6;
    private javax.swing.JLabel jPunt7;
    private javax.swing.JLabel jPunt8;
    private javax.swing.JLabel jPunt9;
    private javax.swing.JLabel jScore1;
    private javax.swing.JLabel jScore10;
    private javax.swing.JLabel jScore2;
    private javax.swing.JLabel jScore3;
    private javax.swing.JLabel jScore4;
    private javax.swing.JLabel jScore5;
    private javax.swing.JLabel jScore6;
    private javax.swing.JLabel jScore7;
    private javax.swing.JLabel jScore8;
    private javax.swing.JLabel jScore9;
    private javax.swing.JLabel jScoreboard;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel sfondo;
    // End of variables declaration//GEN-END:variables
}
