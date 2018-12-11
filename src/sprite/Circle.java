/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.Timer;
import static sprite.Sprite.mapRGB;
import sprite.animated.Zombie;
import utilities.Animation;
import utilities.Assets;
import utilities.Sound;

/**
 *
 * @author genna
 */
public class Circle extends Sprite{
    private final static int BULLETDIAMETER = 90;
    private Handler handler;
    private final int damage;
    private int health=50;
    private boolean next=false;
    //private Timer hitExplosion;
    private  Sound hitSound=new Sound(Assets.rpgExplosionSound);
    private Animation deathAnimation=new Animation(Assets.explosion,50);
    public Circle(float x, float y, int width, int height, int damage, Handler handler) {
        super(x, y, BULLETDIAMETER, BULLETDIAMETER);
        this.damage = damage;
        this.handler = handler;
        this.deathAnimation=deathAnimation;
        this.deathAnimation.setIndex();
        this.hitSound.playSound();
    }
    
    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(deathAnimation.getCurrentFrame(),(int)(this.getX()-offsetX-60), (int) (this.getY()-offsetY-60 ), null);
            
    }
    

    @Override
    public void animationCycle() {
        health-=1;
        try{
            for (Sprite sprite : handler.getZombies()) {
                next=false;
                for (float xx = getX(); xx < getX() + BULLETDIAMETER && next==false; xx++) {
                    for (float yy = getY(); yy < getY() + BULLETDIAMETER && next==false; yy++) {
                        if (sprite.getBounds().contains(xx, yy)) {
                            Zombie y = (Zombie) sprite;
                            y.hit(damage);
                            next=true;
                        }
                    }
                }
            }
        }catch(Exception ex){
            //vai avanti
            System.out.println("Errore causa rpg");
        }
        if (dye()) this.handler.removeSprite(this);
    }
    
    public boolean dye() {
        deathAnimation.update();
        if(deathAnimation.getIndex()==22) return true;
        return false;
    }
    
}


