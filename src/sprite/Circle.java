/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import sprite.animated.Zombie;
import utilities.Animation;
import utilities.Assets;
import utilities.Sound;

/**
 *
 * @author genna
 */
public class Circle extends Sprite{
    private final static int BULLETDIAMETER = 60;
    private Handler handler;
    private final int damage;
    private boolean next=false;
    private  Sound hitSound=new Sound(Assets.rpgExplosionSound);
    private Animation deathAnimation=new Animation(Assets.explosion,30);
    
    public Circle(float x, float y, int damage, Handler handler) {
        super(x-30, y-30, BULLETDIAMETER, BULLETDIAMETER);
        this.damage = damage;
        this.handler = handler;
        this.deathAnimation.setIndex();
        this.hitSound.playSound();
    }
    
    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(deathAnimation.getCurrentFrame(),(int)(this.getX()-offsetX -30), (int) (this.getY()-offsetY -30), null);
    }
    

    @Override
    public void animationCycle() {
        
        for (Sprite sprite : handler.getZombies()) {
            if (sprite.getBounds().intersects(this.getBounds())) {
                Zombie y = (Zombie) sprite;
                y.hit(damage);
            }                                   
        }
        
        if (dye()) 
            this.handler.removeSprite(this);
    }
    
    public boolean dye() {
        deathAnimation.update();
        if(deathAnimation.getIndex()==22) return true;
        return false;
    }
    
}


