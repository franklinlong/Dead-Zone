/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite.animated;

import deadzone.Handler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.Timer;
import utilities.Animation;
import utilities.Assets;
import utilities.Route;
import utilities.Sound;
import sprite.DropItem;

/**
 *
 * @author USER
 */
public class Zombie extends AnimatedSprite{

    public static final int ZOMBIESIZE = 60;
    
    private float distanceToPlayerX;
    private float distanceToPlayerY;
    
    private final Player player;
    
    private final Animation walkAnimation, attackAnimation;
    private Animation currentAnimation;
    
    private final Sound biteSound;
    
    private Timer attackDelay;
    private boolean attacking = false;
    
    private Handler handler;
    private float probabilityDrop; //probabilità percentuale di rilascio oggetto dello zombie
    
    public Zombie(float x, float y, int vel, int health, Player player, Handler handler, float probabilityDrop) {
        super(x, y, ZOMBIESIZE, ZOMBIESIZE, vel, health);
        this.velX = vel;
        this.velY = vel;
        this.player = player;
        this.handler = handler;
        this.probabilityDrop = probabilityDrop;
        biteSound = new Sound(Assets.zombieBite);
        
        attackDelay = new Timer(350, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(distanceToPlayerX < Player.PLAYERSIZE && distanceToPlayerY < Player.PLAYERSIZE)
                {
                    biteSound.playSound();
//                    player.hit();
                }

                attackDelay.stop();
                attacking = false;
                currentAnimation = walkAnimation;
            }

        });
        
        walkAnimation = new Animation(Assets.zombie, 20);
        attackAnimation = new Animation(Assets.zombieAttack, 35);
        currentAnimation = walkAnimation;
        this.death();
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
        
        //in base al percorso che deve seguire lo zombie, a[] avrà la velocitaX e la velocitaY
        float[] a = new Route(player, this).seek();
        			
        angle = (float) Math.acos(a[0]);
        if(a[1] < 0)
                angle *= -1;
        
        float toPlayerX = player.getX() - this.getX();
        float toPlayerY = player.getY() - this.getY();
	distanceToPlayerX = (float)(Math.sqrt(toPlayerX*toPlayerX + toPlayerY*toPlayerY));
        distanceToPlayerY = (float)(Math.sqrt(toPlayerX*toPlayerX + toPlayerY*toPlayerY));
        
        //Se lo zombie è vicino al player lo attacca e quindi non si deve muovere
        if(distanceToPlayerX < player.width/2 && distanceToPlayerY < player.height/2 && !attackDelay.isRunning())
        {
            attacking = true;
            attackDelay.start();
            currentAnimation = attackAnimation;
            currentAnimation.setIndex();
        }
        
        //Se è in corso l'animazione dell'attacco lo zombie non si muove
        if(attacking){
            a[0] = 0;
            a[1] = 0;
        }
        
        float x = getX();
        float y = getY();
        
        //Aggiorno la posizione dello zombie in base ai calcoli sul percorso
        x += a[0];
        y += a[1];
        
        //Se c'è una collisione non posso passare
        int k = collision();
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
    
    //Metodo chiamato alla morte dello zombie per un possibile drop di un item
    private void death(){    
        
        //Variabile vera se lo zombie rilascia un item
        boolean drop = (Math.random() *100) <= probabilityDrop;
        if(drop){
            this.handler.addSprite(new DropItem(this.getX(), this.getY(), 30, 30, handler));
        }
      
        this.handler.removeSprite(this);
    }
    
}
