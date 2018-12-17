/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.menu;

import deadzone.utilities.Assets;

import deadzone.utilities.Database;
import static deadzone.utilities.Database.online;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author giova
 */
public class connectionThread extends Thread{
   private LoadingScreen ls; 
   private String nome;
   private int punteggio;
   
   public static final Object TOS = new Object();
   public static boolean occupato = false;
   
   public connectionThread(String nome, int punteggio){
       ls = LoadingScreen.getLoadingScreen();
       ls.setVisible(true);
       this.nome = nome;
       this.punteggio = punteggio;
   }
   
   @Override
   public void run(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        synchronized (TOS) {
                occupato = true;
            try {
                ls.jLabel2.setText("Connection...");
                conn = DriverManager.getConnection(Database.s1, Database.user, Database.pass);
                ls.jLabel2.setText("Connection estabilished...");
                stmt = conn.createStatement();
                ls.jLabel2.setText("Sending your score to the database");
                String query = "SELECT MAX(id) FROM scoreboard";
                rs = stmt.executeQuery(query);
                rs.next();
                int id = (int) rs.getInt(1) + 1;
                String sql = "INSERT INTO scoreboard VALUES ('" + id + "','" + nome + "','" + punteggio + "')";
                stmt.executeUpdate(sql);
                ls.jLabel2.setText("Done! Downloading scoreboard...");
                query = " SELECT * FROM scoreboard ORDER BY punteggio DESC LIMIT 10";
                Assets.rs = stmt.executeQuery(query);
                Database.online = true;
                ls.setVisible(false);
                ls.dispose();

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
            System.out.println("Fine thread aggiungi score + scarcia scoreboard");
            occupato = false;
            TOS.notifyAll();
            }
            
            

   }
}
