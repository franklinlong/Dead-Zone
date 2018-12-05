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
import javax.swing.Timer;
import utilities.Animation;
import utilities.Route;
import utilities.Sound;
/**
 *
 * @author casang
 */
public class SpittleZombie extends Zombie{
    
    public static final int ZOMBIESIZE = 60;
    
    //Danni inflitti dallo zombie quando attacca
    private final int damage = 15;
    
    //Timer
    Timer attackDelay, hitZombie;
    
    public SpittleZombie(float x, float y, int vel, int health, Player player, Handler handler, 
            float probabilityDrop, int width, int height, int score, Animation walkAnimation, Animation
                    attackAnimation, Sound biteSound, Sound hitSound) {
        super(x, y, vel, health, player, handler, probabilityDrop, width, height, score, walkAnimation,attackAnimation,biteSound,hitSound);

        attackDelay = new Timer(5000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                biteSound.playSound();
                attackDelay.stop();
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
        			
            //Codice per ricalcolare la direzione in base alla presenza di zombie vicini ... DA FARE
            //a = new Route(player, this).evitaZombie(a[0],a[1], this.handler.getZombies());
        
        
            angle = (float) Math.acos(a[0]);
            if(a[1] < 0)
                    angle *= -1;
        
            float toPlayerX = player.getX() - this.getX();
            float toPlayerY = player.getY() - this.getY();
            distanceToPlayerX = (float)(Math.sqrt(toPlayerX*toPlayerX + toPlayerY*toPlayerY));
            distanceToPlayerY = (float)(Math.sqrt(toPlayerX*toPlayerX + toPlayerY*toPlayerY));
            
            float xbullet = (float)Math.cos(angle);
            float ybullet = (float)Math.sin(angle);
            float spittledirectionX = xbullet/(float)Math.sqrt(xbullet*xbullet+ybullet*ybullet);
            float spittledirectionY = ybullet/(float)Math.sqrt(ybullet*ybullet+xbullet*xbullet);
            
            //Se lo zombie è nelle vicinanze del player lo attacca
            if(distanceToPlayerX < player.width*5 && distanceToPlayerY < player.height*5 && !attackDelay.isRunning() && !player.isDeath()){
                Spittle s = new Spittle(this.getX(),this.getY(),spittledirectionX, 
                                        spittledirectionY, 3, 100, this.handler, damage ,
                                        this.handler.getPlayer());
                this.handler.addSprite(s);
                attacking = true;
                attackDelay.start();
            }
            
            if (distanceToPlayerX > player.width*5 && distanceToPlayerY > player.height*5 && !player.isDeath()){
                attacking = false;
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
            x += a[0];
            y += a[1];
        
            //aggiorno le variabili dello sprite per come funziona collision
            setX(x);
            setY(y);
        
            //Se c'è una collisione non posso passare
            int k = collision(this.initialVelocity, this.initialVelocity);
            switch (k) {
                case 1:
                    x -= a[0] ;
                    break;
                case 2:
                    y -= a[1];
                    break;
                case 3:
                    x -= a[0] ;
                    y -= a[1];
                    break;
                default:
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
