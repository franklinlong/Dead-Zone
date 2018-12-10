/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import sprite.animated.Zombie;

/**
 *
 * @author giova
 */
public abstract class Trap extends Sprite{
    protected Handler handler;
    protected Rectangle rectangle;
    
    public Trap(float x, float y, int width, int height, Handler handler){
        super(x, y, width, height);
        this.handler = handler;
        this.rectangle = new Rectangle((int) x,(int) y, width, height);
    }
    
    @Override
    public abstract void drawImage(Graphics g, float offsetX, float offsetY);

    @Override
    public void animationCycle() {
        for (Sprite s : handler.getZombies()){
            if(s instanceof Zombie){
                if(s.getBounds().intersects(rectangle))
                    ((Zombie) s).hit(500);
            }
        }
    }
    
}
