/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Color;
import java.awt.Graphics;
import utilities.Animation;
import utilities.Assets;

/**
 *
 * @author giova
 */
public class ShockTrap extends Trap{
    private int durata;
    private Animation animazione;
    
    public ShockTrap(float x, float y, int width, int height, Handler handler) {
        super(x, y, width, height, handler);
        this.durata=300;
        this.animazione = new Animation(Assets.fulmini, 50);
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
//        g.setColor(Color.yellow);
//        g.fillRect((int) (getX() - offsetX),(int) (getY() - offsetY), width, height);
        
        g.drawImage(animazione.getCurrentFrame(), (int) (getX() - offsetX), (int) (getY() - offsetY), null);
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
