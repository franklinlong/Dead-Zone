package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private static String s1 = "jdbc:postgresql://deadzone.cpt3ir9rzkfp.us-west-2.rds.amazonaws.com/deadzone";
    private static String user = "enricosammarco";
    private static String pass = "giovannifenomeno";

    public static boolean online = true;

    public static ResultSet dbQuery(String q) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(s1, user, pass);
            System.out.println("Connesso");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(q);
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
        System.out.println("Disconnesso");
        return rs;
    }

    public static void dbAction(String q) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(s1, user, pass);
            System.out.println("Connesso");
            stmt = conn.createStatement();
            stmt.executeUpdate(q);

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
        System.out.println("Disconnesso");
    }

    public static void InserisciPunteggio(String nome, int punteggio) {
        String query = "SELECT MAX(id) FROM scoreboard";
        ResultSet rs = Database.dbQuery(query);
        int id;
        if (online){
        try {
            rs.next();
            id = (int) rs.getInt(1) + 1;
            String sql = "INSERT INTO scoreboard VALUES ('" + id + "','" + nome + "','" + punteggio + "')";
            Database.dbAction(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    }

    public static int InserisciOnline(String nome) {
        String query = "SELECT MAX(id) FROM online";
        ResultSet rs = Database.dbQuery(query);
        int id = 0;
        if (online){
        try {
            if (rs.next()) {
                id = (int) rs.getInt(1) + 1;
            }
            String sql = "INSERT INTO online VALUES ('" + id + "','" + nome + "')";
            Database.dbAction(sql);

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
         return id;
        }
        return -1;
       
    }

    public static void CancellaOnline(int id) {
        if (online && id!=-1){
        String sql = "DELETE FROM online WHERE id='" + id + "'";
        Database.dbAction(sql);
    }

    }

    public static ResultSet OttieniScoreboard() {
        String query = " SELECT * FROM scoreboard ORDER BY punteggio DESC LIMIT 10";
        return Database.dbQuery(query);
    }

    public static ResultSet OttieniOnline() {
        String query = " SELECT * FROM online ";
        return Database.dbQuery(query);
    }
}
