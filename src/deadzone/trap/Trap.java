/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.trap;

import deadzone.Handler;
import deadzone.menu.PauseMenu;
import java.awt.Graphics;
import java.awt.Rectangle;
import deadzone.sprite.Sprite;
import deadzone.sprite.animated.Player;
import deadzone.sprite.animated.Zombie;
import deadzone.utilities.Sound;

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
    
    public Trap(float x, float y, int width, int height, Handler handler, int durata, Sound sound){
        super(x, y, width, height);
        this.handler = handler;
        this.rectangle = new Rectangle((int) x,(int) y, width, height);
        this.durata = durata;
        this.sound = sound;
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
    
}
