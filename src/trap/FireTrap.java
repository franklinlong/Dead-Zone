/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trap;

import deadzone.Handler;
import java.awt.Graphics;
import sprite.animated.Player;
import utilities.Animation;
import utilities.Assets;
import utilities.Sound;

/**
 *
 * @author USER
 */
public class FireTrap extends Trap{
    private final Animation animazione;
    Player p;
    
    public FireTrap(float x, float y, int width, int height, Handler handler, boolean orizzontale, int durata, Sound sound) {
        super(x, y, width, height, handler, durata, sound);
        if(orizzontale)
            this.animazione = new Animation(Assets.fuochi, 100);
        else
            this.animazione = new Animation(Assets.fuochiR, 100);
        this.damage = 30;
        p = handler.getPlayer();
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        g.drawImage(animazione.getCurrentFrame(), (int) (getX() - offsetX), (int) (getY() - offsetY), null);        
    }
    
    @Override
    public void animationCycle(){
        if (durata>0) {
            super.animationCycle();
        } else {
            handler.removeSprite(this);
            this.sound.stopSound();
            p.setFireTrapActive(false);
        }

        animazione.update();
    }
}
