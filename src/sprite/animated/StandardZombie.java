/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite.animated;

import deadzone.Handler;
import gameMenu.PauseMenu;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.Timer;
import utilities.Animation;
import utilities.Route;
import utilities.Sound;

/**
 *
 * @author giova
 */
public class StandardZombie extends Zombie{

    public static final int ZOMBIESIZE = 60;
    
    //Danni inflitti dallo zombie quando attacca
    private final int damage = 20;
    
    //Timer
    Timer attackDelay, hitZombie;
    
    public StandardZombie(float x, float y, int vel, int health, Player player, Handler handler, 
            float probabilityDrop, int width, int height, int score, Animation walkAnimation, Animation
                    attackAnimation, Sound biteSound, Sound hitSound) {
        super(x, y, vel, health, player, handler, probabilityDrop, width, height, score, walkAnimation,attackAnimation,biteSound,hitSound);

        attackDelay = new Timer(350, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(distanceToPlayerX < Player.PLAYERSIZE && distanceToPlayerY < Player.PLAYERSIZE && player.isDeath() == false){ 
                    biteSound.playSound();
                    player.hit(damage);
                }

                attackDelay.stop();
                attacking = false;
                currentAnimation = walkAnimation;
            }

        });
        
        hitZombie = new Timer(300,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hitSound.playSound();
                hitZombie.stop();
            }
        });
           
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        double xx,yy;
        xx=getX()-offsetX;
        yy=getY()-offsetY;

                
        at = AffineTransform.getTranslateInstance(xx,yy);
        at.rotate(angle,this.width/2,this.height/2);
        
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(currentAnimation.getCurrentFrame(), at, null);
        
    }

    @Override
    public void animationCycle() {
        if (!PauseMenu.pause){
            //Controllo che sia vivo        
            if(getHealth()<=0)
                death();
        
        
            //in base al percorso che deve seguire lo zombie, a[] avrà la velocitaX e la velocitaY
            float[] a = new Route(player, this).seek();
        	
            //Velocita fittizie per capire se ci sono ostacoli
            int vx=0;
            int vy=0;
            if(a[0] < 0)
                vx = -1;
            else if(a[0] > 0)
                vx = +1;
            if(a[1] < 0)
                vy = -1;
            else if(a[1] > 0)
                vy = +1;
            
            //Codice per ricalcolare la direzione in base alla presenza di zombie vicini ... DA FARE
            ArrayList<Zombie> vicino = new Route(player, this).evitaZombies(vx,vy, this.handler.getZombies());
            if(!vicino.isEmpty()){
                for(int i=0; i<vicino.size(); i++){
                    Route r2 = new Route(this,vicino.get(i));
                    a[0] = (a[0] + r2.seek()[0]);
                    a[1] = (a[1] + r2.seek()[1]);
                }
            }
            else{
                angle = (float) Math.acos(a[0]);
                if(a[1] < 0)
                    angle *= -1;
            }
        
            float toPlayerX = player.getX() - this.getX();
            float toPlayerY = player.getY() - this.getY();
            distanceToPlayerX = (float)(Math.sqrt(toPlayerX*toPlayerX + toPlayerY*toPlayerY));
            distanceToPlayerY = (float)(Math.sqrt(toPlayerX*toPlayerX + toPlayerY*toPlayerY));
        
            //Se lo zombie è vicino al player lo attacca e quindi non si deve muovere
            if(distanceToPlayerX < player.width/2 && distanceToPlayerY < player.height/2 && !attackDelay.isRunning() && !player.isDeath())
            {
                attacking = true;
                attackDelay.start();
                currentAnimation = attackAnimation;
                currentAnimation.setIndex();
            }
        
            //Se è in corso l'animazione dell'attacco lo zombie non si muove
            //Se il player è morto lo zombie non s muove
            if(attacking || player.isDeath()){
                a[0] = 0;
                a[1] = 0;
            }
        
            float x = getX();
            float y = getY();
        
            //Aggiorno la posizione dello zombie in base ai calcoli sul percorso
            x += vx;
            y += vy;
        
            //aggiorno le variabili dello sprite per come funziona collision
            setX(x);
            setY(y);
        
            //Se c'è una collisione non posso passare
            int k = collision(vx,vy,x,y);
            switch (k) {
                case 1:
                    x -= vx;
                    break;
                case 2:
                    y -= vy;
                    break;
                case 3:
                    x -= vy ;
                    y -= vy;
                    break;
                default:
                    x = x - vx + a[0];
                    y = y - vy + a[1];
                    break;
                }
                
            setX(x);
            setY(y);
        
            //Aggiorno l'animazione
            currentAnimation.update();
        }
    }
    
    
    @Override
    public void sound_hit(){
        if(!hitZombie.isRunning())
        hitZombie.start();
    }
}
