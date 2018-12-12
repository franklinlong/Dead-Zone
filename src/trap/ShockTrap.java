/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trap;

import deadzone.Handler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import javax.swing.Timer;
import utilities.Animation;
import utilities.Assets;

/**
 *
 * @author giova
 */
public class ShockTrap extends Trap{
    private final Animation animazione;
    private final boolean orizzontale;
    private final Timer durata;
    
    public ShockTrap(float x, float y, int width, int height, Handler handler, boolean orizzontale, Timer durata) {
        super(x, y, width, height, handler);
        this.durata = durata;
        this.animazione = new Animation(Assets.fulmini, 50);
        this.orizzontale = orizzontale;
        this.damage=1;
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
        if (durata.isRunning()) {
            super.animationCycle();
        } else {
            handler.removeSprite(this);
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
