/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import static sprite.Sprite.mapRGB;
import sprite.animated.Zombie;
import utilities.Assets;

/**
 *
 * @author genna
 */
public class Circle extends Sprite{
    private final static int BULLETDIAMETER = 130;
    private Handler handler;
    private final int damage;
    private int health=50;
    //Shape circle = new Ellipse2D.Float(100.0f, 100.0f, 100.0f, 100.0f);
    public Circle(float x, float y, int width, int height, int damage, Handler handler) {
        super(x, y, BULLETDIAMETER, BULLETDIAMETER);
        this.damage = damage;
        this.handler = handler;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        
    }
    

    @Override
    public void animationCycle() {
        health-=1;
        for (Sprite sprite : handler.getZombies()) {
            for (float xx = getX(); xx < getX() + BULLETDIAMETER; xx++) {
                for (float yy = getY(); yy < getY() + BULLETDIAMETER; yy++) {
                    if (sprite.getBounds().contains(xx, yy)) {
                        Zombie y = (Zombie) sprite;
                        y.hit(damage);
                    }
                }
            }
        }
        if (dye()) {
            System.out.println("m");
            this.handler.removeSprite(this);
        }
    }
    
    public boolean dye() {
        if (health == 0) {
            return true;
        }
        return false;
    }
    
}


