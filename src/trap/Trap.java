/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trap;

import deadzone.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import sprite.Sprite;
import sprite.animated.Player;
import sprite.animated.Zombie;

/**
 *
 * @author giova
 */
public abstract class Trap extends Sprite{
    protected Handler handler;
    protected Rectangle rectangle;
    protected int damage;
    
    public Trap(float x, float y, int width, int height, Handler handler){
        super(x, y, width, height);
        this.handler = handler;
        this.rectangle = new Rectangle((int) x,(int) y, width, height);
    }
    
    @Override
    public abstract void drawImage(Graphics g, float offsetX, float offsetY);

    @Override
    public void animationCycle() {
        Player p;
        for (Sprite s : handler.getZombies()) {
            if (s instanceof Zombie) {
                Rectangle z = s.getBoundsTrap();
                if (z.intersects(getBounds())) {
                    ((Zombie) s).hit(damage);
                }
            }
        }
        p = handler.getPlayer();
        if (p.getBoundsTrap().intersects(getBounds())) {
            p.hit(damage);
        }
    }
    
    public int getDamage(){
        return damage;
    }
    
}
