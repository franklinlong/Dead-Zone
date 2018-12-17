/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.trap;

import deadzone.Handler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import deadzone.sprite.animated.PlayerFactory;
import deadzone.utilities.Animation;
import deadzone.utilities.Assets;
import deadzone.utilities.Sound;

/**
 *
 * @author giova
 */
public class ShockTrap extends Trap{
    private final Animation animazione;
    private final boolean orizzontale;
    private int index;
    
    public ShockTrap(float x, float y, int width, int height, Handler handler, boolean orizzontale, int durata, Sound sound, int index) {
        super(x, y, width, height, handler, durata, sound);
        this.animazione = new Animation(Assets.fulmini, 50);
        this.orizzontale = orizzontale;
        this.index = index;
        this.damage=10;
        this.sound.loopSound();
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        if(!this.orizzontale){
            AffineTransform at = AffineTransform.getTranslateInstance((int) (getX() - offsetX), (int) (getY() - offsetY));
            at.rotate(Math.PI/2);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(animazione.getCurrentFrame(), at, null);
        }
        else{   
            g.drawImage(animazione.getCurrentFrame(), (int) (getX() - offsetX), (int) (getY() - offsetY), null);

        }
    }
    
    @Override
    public void animationCycle(){
        if (durata>0) {
            super.animationCycle();
        } else {
            handler.removeSprite(this);
            this.sound.stopSound();
            PlayerFactory p = handler.getPlayer();
            switch(index){
                case 1:
                    p.setShockTrapActive1(false);
                    break;
                case 2:
                    p.setShockTrapActive2(false);
                    break;
                case 3:
                    p.setShockTrapActive3(false);
                    break;
            }
        }

        animazione.update();
    }
    
        
    @Override
    public Rectangle getBounds(){
        if(orizzontale)
            return new Rectangle((int) getX(), (int) getY() + height/3, width, height/3);
        else{
            return new Rectangle((int) getX()- height, (int) getY(), height/3, width);
        }
    }
    
}
