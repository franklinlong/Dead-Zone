/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Graphics;
import javax.swing.Timer;
import utilities.Assets;

/**
 *
 * @author USER
 */
public class HoleTrap extends Trap{
    private final Timer durata;
    
    public HoleTrap(float x, float y, int width, int height, Handler handler, Timer durata) {
        super(x, y, width, height, handler);
        this.durata=durata;
        this.setX(x);
        this.setY(y);
        this.damage=20;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        g.drawImage(Assets.hole, (int) (getX()-40 - offsetX), (int) (getY()-40 - offsetY), null);
    }
    
    @Override
    public void animationCycle(){
        if(durata.isRunning()){
            super.animationCycle();
        }else
            handler.removeSprite(this);
        
    }
}
