/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import sprite.Sprite;
import sprite.animated.Zombie;

/**
 *
 * @author USER
 */

//Definisce il percorso tra uno zombie e un player
public class Route {
    
    private final Sprite target;
    private final Zombie zombie;
    
    public Route(Sprite target, Zombie zombie){
        this.target = target;
        this.zombie = zombie;
        
    }
    
    //Restituisce la velocitaX e velocitaY per avvicinarsi al giocatore
    public float[] seek(){

        float[] a;
        a = new float[2];
        
        float x = (target.getX() + target.width/2 ) - (zombie.getX() + zombie.width/2);
        float y  = (target.getY() + target.height/2 ) - (zombie.getY() + zombie.height/2);
        
        x = (x/ (float)Math.sqrt(x*x + y*y));
        y = (y/ (float)Math.sqrt(x*x + y*y));
        
        x = x * zombie.velX;
        y = y * zombie.velY;
        
        a[0] = x;
        a[1] = y;
        
        return a; 
    }
    
    public ArrayList<Zombie> evitaZombies(int x, int y, List<Sprite> zombies){
        ArrayList<Zombie> vicini = new ArrayList<Zombie>();

        //aggiorno le variabili dello zombie in modo da vedere se nella nuova posizione ci sono atri zombie
        this.zombie.setX(this.zombie.getX() + x);
        this.zombie.setY(this.zombie.getY() + y);
        
        for(int i=0;i<zombies.size();i++){
            Zombie s = (Zombie) zombies.get(i);
            
            if(!s.equals(this.zombie)){
                Rectangle r = s.getBounds();
                r.setRect(s.getX()+s.width*5/12, s.getY()+s.height*5/12, s.width/6, s.height/6);
                if(this.zombie.getBounds().intersects(r)){
                    this.zombie.setX(this.zombie.getX() - x);
                    this.zombie.setY(this.zombie.getY() - y);
                    vicini.add(s);
                }
            }
        }
        
        //Faccio tornare o zombie alla posizione iniziale perchè vanno fatti tutti i vari controlli nella classe zombie
        this.zombie.setX(this.zombie.getX() - x);
        this.zombie.setY(this.zombie.getY() - y);
        
        return vicini;
    }
    
}
