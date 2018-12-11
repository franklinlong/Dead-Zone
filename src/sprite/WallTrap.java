/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Color;
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
    private final boolean orizzontale;
    private final Graphics2D g2dRGB;
    
    public WallTrap(float x, float y, int width, int height, Handler handler, boolean orizzontale) {
        super(x, y, width, height, handler);
        this.durata=300;
        this.orizzontale = orizzontale;

        g2dRGB = (Graphics2D) mapRGB.getGraphics();
        g2dRGB.setColor(new Color(255,0,0));
        
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        if(!this.orizzontale){
            AffineTransform at = AffineTransform.getTranslateInstance((int) (getX() - offsetX), (int) (getY() - offsetY));
            at.rotate(Math.PI/2);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(Assets.wall, at, null);
            g2dRGB.fillRect((int) getX(),(int) getY(), height, width);
        }
        else{   
            g.drawImage(Assets.wall, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
            g2dRGB.fillRect((int) getX(),(int) getY(), width, height);
        }
    }
    
    @Override
    public void animationCycle(){
        if(durata>0){
            durata--;
        }else{
            g2dRGB.drawImage(Assets.mapRGB2,0,0,null); //Sembra funzionare bene
            handler.removeSprite(this);
        }
    }   
}
