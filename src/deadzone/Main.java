/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import deadzone.utilities.Database;
import static deadzone.utilities.Database.online;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class Main {
    
    public static void main (String[] args){
        System.out.println("Mi sto cancellando dagli online");
        String sql = "DELETE FROM online";
        Database.dbAction(sql);

    }
}
