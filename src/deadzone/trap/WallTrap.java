/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.trap;

import deadzone.Handler;
import deadzone.menu.PauseMenu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import deadzone.sprite.animated.Player;
import deadzone.utilities.Assets;
import deadzone.utilities.Sound;
/**
 *
 * @author USER
 */
public class WallTrap extends Trap{
    private final boolean orizzontale;
    private final Graphics2D g2dRGB;
    private final int index;
    
    public WallTrap(float x, float y, int width, int height, Handler handler, boolean orizzontale, int durata, Sound sound, int index) {
        super(x, y, width, height, handler, durata, sound);
        this.orizzontale = orizzontale;
        this.index = index;
        g2dRGB = (Graphics2D) mapRGB.getGraphics();
        g2dRGB.setColor(new Color(255,0,0));
        if(!this.orizzontale){
            g2dRGB.fillRect((int) getX(),(int) getY(), height, width);
        }else{
            g2dRGB.fillRect((int) getX(),(int) getY(), width, height);
        }
        this.sound.playSound();
        loopSound=false;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
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
        //Se è finito il timer della trappola
        if(!PauseMenu.isPause())
            durata--;
        if(durata==0){
            g2dRGB.drawImage(Assets.mapRGB2,0,0,null); //Sembra funzionare bene
            handler.removeSprite(this);
            Player p = handler.getPlayer();
            switch(index){
                case 1:
                    p.setWallTrapActive1(false);
                    break;
                case 2:
                    p.setWallTrapActive2(false);
                    break;
            }
            sound.playSound();
        }
    }
}
