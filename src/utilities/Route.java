/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import sprite.animated.Player;
import sprite.animated.Zombie;

/**
 *
 * @author USER
 */

//Definisce il percorso tra uno zombie e un player
public class Route {
    
    private final Player player;
    private final Zombie zombie;
    
    public Route(Player player, Zombie zombie){
        this.player = player;
        this.zombie = zombie;
        
    }
    
    //Restituisce la velocitaX e velocitaY
    public float[] seek(){

        float[] a;
        a = new float[2];
        
        float x = (player.getX() + player.width/2 ) - (zombie.getX() + zombie.width/2);
        float y  = (player.getY() + player.height/2 ) - (zombie.getY() + zombie.height/2);
        
        x = (x/ (float)Math.sqrt(x*x + y*y));
        y = (y/ (float)Math.sqrt(x*x + y*y));
        
        x = x * zombie.velX;
        y = y * zombie.velY;
        
        a[0] = x;
        a[1] = y;
        
        return a; 
    }
    
}