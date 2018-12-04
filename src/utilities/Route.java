/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.List;
import sprite.Sprite;
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
    
    //Restituisce la velocitaX e velocitaY per avvicinarsi al giocatore
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
    
    public float[] evitaZombie(float x, float y, List<Sprite> zombies){
        float[] a;
        a = new float[2];
       
        
        //aggiorno le variabili dello zombie in modo da vedere se nella nuova posizione ci sono atri zombie
        this.zombie.setX(this.zombie.getX() + x);
        this.zombie.setY(this.zombie.getY() + y);
        
        a[0] = x;
        a[1] = y;
        
        for(int i=0;i<zombies.size();i++){
            Sprite s = zombies.get(i);
            
            if(this.zombie.getBounds().contains((int)(s.getX() + s.width),(int) (s.getHeight() + s.getY()))){
                System.out.println("SESESE");
                a[0] = 0;
                a[1] = 0;
            }
            
        }
        
        //Faccio tornare lo zombie alla posizione iniziale perchè vanno fatti tutti i vari controlli nella classe zombie
        this.zombie.setX(this.zombie.getX() - x);
        this.zombie.setY(this.zombie.getY() - y);
        
        return a;
    }
    
}
