/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.trap;

import deadzone.Handler;
import deadzone.SpriteVisitor;
import deadzone.menu.PauseMenu;
import java.awt.Graphics;
import java.awt.Rectangle;
import deadzone.sprite.Sprite;
import deadzone.sprite.SpriteInterface;
import deadzone.sprite.animated.PlayerFactory;
import deadzone.sprite.animated.Zombie;
import deadzone.utilities.Sound;
import java.util.Iterator;

/**
 *
 * @author giova
 */
public abstract class Trap extends Sprite{
    protected Handler handler;
    protected Rectangle rectangle;
    protected int damage;
    protected int durata;
    protected final Sound sound;
    private boolean state;
    protected boolean loopSound=true;
    
    //Abstract product
    public Trap(float x, float y, int width, int height, Handler handler, int durata, Sound sound){
        super(x, y, width, height);
        this.handler = handler;
        this.rectangle = new Rectangle((int) x,(int) y, width, height);
        this.durata = durata;
        this.sound = sound;
        this.sound.changeVolume(-10);
        state = true;
    }
    
    @Override
    public abstract void drawImage(Graphics g, float offsetX, float offsetY);

    @Override
    public void animationCycle() {
        if (!PauseMenu.isPause()) {
            if(!state){
                if(loopSound)
                    this.sound.loopSound();
                state = true;
            }
            PlayerFactory p;
            for (Iterator<SpriteInterface> it = handler.getZombies().iterator(); it.hasNext();) {
                Zombie s = (Zombie)it.next();
                Rectangle z = s.getBoundsTrap();
                if (z.intersects(getBounds())) {
                    s.hit(damage);
                }
            }
            p = handler.getPlayer();
            if (p.getBoundsTrap().intersects(getBounds())) {
                p.hit(damage);
            }
            durata--;
        }
        else if(PauseMenu.isPause()){
            this.sound.stopSound();
            state = false;
        }
    }
    
    public int getDamage(){
        return damage;
    }
    
    public Sound getSound(){
        return sound;
    }
    
    public void accept(SpriteVisitor visitor){
        visitor.visit(this);
    }
}
