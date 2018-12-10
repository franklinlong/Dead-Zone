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
import utilities.Assets;
/**
 *
 * @author USER
 */
public class WallTrap extends Trap{
    private int durata;
    private boolean orizzontale;
    
    public WallTrap(float x, float y, int width, int height, Handler handler, boolean orizzontale) {
        super(x, y, width, height, handler);
        this.durata=3000;
        this.orizzontale =orizzontale;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
//        g.setColor(Color.yellow);
//        g.fillRect((int) (getX() - offsetX),(int) (getY() - offsetY), width, height);
        if(!this.orizzontale){
            AffineTransform at = AffineTransform.getTranslateInstance((int) (getX() - offsetX), (int) (getY() - offsetY));
            at.rotate(Math.PI/2);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(Assets.wall, at, null);
        }
        else{   
            g.drawImage(Assets.wall, (int) (getX() - offsetX), (int) (getY() - offsetY), null);

        }
    }
    
    @Override
    public void animationCycle(){
        if(durata>0){
            durata--;
        }else
            handler.removeSprite(this);
        
    }
}
