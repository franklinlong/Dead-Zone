/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.Timer;

/**
 *
 * @author USER
 */
public class Zombie extends AnimatedSprite{

    public static final int ZOMBIESIZE = 60;
    
    private double distanceToPlayerX;
    private double distanceToPlayerY;
    
    private Route route;
    private Player player;
    private Handler handler;
    private double x;
    private double y;
    
    private Animation walkAnimation, attackAnimation;
    private Animation currentAnimation;
    
    private Sound biteSound;
    
    private Timer attackDelay;
    private boolean attacking = false;
      
        
    private int w_map = 3200;
    private int h_map = 3200;
    
    public Zombie(int x, int y, int velX, int velY, int health, Player player, Handler handler) {
        super(x, y, ZOMBIESIZE, ZOMBIESIZE, velX, velY, health);
        this.x = x;
        this.y = y;
        this.player = player;
        this.handler = handler;
        this.initialVelocity = velX;
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
    }


    public double getDoubleX() {
        return x;
    }

    public double getDoubleY() {
        return y;
    }

    @Override
    public void drawImage(Graphics g, int offsetX, int offsetY) {
        double xx,yy;
        xx=x-offsetX;
        yy=y-offsetY;

                
        at = AffineTransform.getTranslateInstance(xx,yy);
        at.rotate(angle,ZOMBIESIZE/2,ZOMBIESIZE/2);
        
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(currentAnimation.getCurrentFrame(), at, null);
        
    }

    @Override
    public void animationCycle() {
        
        double[] a = new Route(player, this, handler).seek();
        			
        angle = Math.acos(a[0]);
        if(a[1] < 0)
                angle *= -1;
        
        double toPlayerX = player.getX() - this.getDoubleX();
        double toPlayerY = player.getY() - this.getDoubleY();
	distanceToPlayerX = (Math.sqrt(toPlayerX*toPlayerX + toPlayerY*toPlayerY));
        distanceToPlayerY = (Math.sqrt(toPlayerX*toPlayerX + toPlayerY*toPlayerY));
        
        if(distanceToPlayerX < Player.PLAYERSIZE/2 && distanceToPlayerY < Player.PLAYERSIZE/2 && !attackDelay.isRunning())
        {
            attacking = true;
            attackDelay.start();
            currentAnimation = attackAnimation;
            currentAnimation.setIndex();
        }
        
        if(attacking){
            a[0] = 0;
            a[1] = 0;
        }
        
        this.x += a[0];
        this.y += a[1];
        
        this.setX((int) this.x);
        this.setY((int) this.y);
        
        //Se c'è una collisione non posso passare
        int k = collision();
        switch (k) {
            case 1:
                this.x -= a[0] ;
                this.setX((int) this.x);
                break;
            case 2:
                this.y -= a[1];
                this.setY((int) this.y);
                break;
            case 3:
                this.x -= a[0] ;
                this.y -= a[1];
                this.setX((int) this.x);
                this.setY((int) this.y);
                break;
            default:
                break;
             }
                
        currentAnimation.update();
    }
    
    
}
