/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import utilities.Animation;
import utilities.Assets;

/**
 *
 * @author giova
 */
public class ShockTrap extends Trap{
    private int durata;
    private final Animation animazione;
    private boolean orizzontale;
    
    public ShockTrap(float x, float y, int width, int height, Handler handler, boolean orizzontale) {
        super(x, y, width, height, handler);
        this.durata=300;
        this.animazione = new Animation(Assets.fulmini, 50);
        this.orizzontale = orizzontale;
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
        if(durata>0){
            super.animationCycle();
            durata--;
        }else
            handler.removeSprite(this);
        
        animazione.update();
    }
    
    
    
}
