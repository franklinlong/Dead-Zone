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
        
            this.zona.aggiorna();
            
            Route traiettoria = new Route(player,this,handler);
            
            //Velocità dello zombie per raggiungere la zona corretta
            float[] velStandard = traiettoria.raggiungiZona();
            
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
                velStandard[0] = 0;
                velStandard[1] = 0;
            }       
            
            //posizione futura dello zombie considerando gli ostacoli
            float[] pos = traiettoria.gestisciOstacoli(velStandard[0], velStandard[1]);
        
            setX(pos[0]);
            setY(pos[1]);
            
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
