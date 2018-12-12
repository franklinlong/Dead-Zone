/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trap;

import deadzone.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import utilities.Animation;
import utilities.Assets;
import javax.swing.Timer;
import sprite.Sprite;
import sprite.animated.Player;
import sprite.animated.Zombie;

/**
 *
 * @author USER
 */
public class FireTrap extends Trap{
    private final Animation animazione;
    private final Timer durata;
    private boolean orizzontale;
    
    public FireTrap(float x, float y, int width, int height, Handler handler, Timer durata, boolean orizzontale) {
        super(x, y, width, height, handler);
        this.durata = durata;
        if(orizzontale)
            this.animazione = new Animation(Assets.fuochi, 100);
        else
            this.animazione = new Animation(Assets.fuochiR, 100);
        this.orizzontale = orizzontale;
        this.damage = 30;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        g.drawImage(animazione.getCurrentFrame(), (int) (getX() - offsetX), (int) (getY() - offsetY), null);        
    }
    
    @Override
    public void animationCycle(){
        if (durata.isRunning()) {
            super.animationCycle();
        } else {
            handler.removeSprite(this);
        }

        animazione.update();
    }
}
