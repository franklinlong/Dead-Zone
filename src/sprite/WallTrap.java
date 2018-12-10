/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Graphics;
import utilities.Assets;
/**
 *
 * @author USER
 */
public class WallTrap extends Trap{
    private int durata;
    
    public WallTrap(float x, float y, int width, int height, Handler handler) {
        super(x, y, width, height, handler);
        this.durata=3000;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
//        g.setColor(Color.yellow);
//        g.fillRect((int) (getX() - offsetX),(int) (getY() - offsetY), width, height);
        
        g.drawImage(Assets.wall, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
    }
    
    @Override
    public void animationCycle(){
        if(durata>0){
            durata--;
        }else
            handler.removeSprite(this);
        
    }
}
