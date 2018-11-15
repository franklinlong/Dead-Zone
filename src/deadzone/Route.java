/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Route {
    
    private Player player;
    private Zombie zombie;
    private Handler handler;
    
    public Route(Player player, Zombie zombie, Handler handler){
        this.player = player;
        this.zombie = zombie;
        this.handler = handler;
        
    }
    
    public double[] seek(){

        double[] a;
        a = new double[2];
        
        double x = (player.getX() + player.getWidth()/2 ) - (zombie.getDoubleX() + zombie.getWidth()/2);
        double y  = (player.getY() + player.getHeight()/2 ) - (zombie.getDoubleY() + zombie.getHeight()/2);
        
        x = (x/ Math.sqrt(x*x + y*y));
        y = (y/ Math.sqrt(x*x + y*y));
        
        x = x * zombie.getVelX();
        y = y * zombie.getVelY();
        
        a[0] = x;
        a[1] = y;
        
        return a; 
    }
    
}
