/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.trap;

import deadzone.Handler;
import java.awt.Graphics;
import deadzone.sprite.animated.Player;
import deadzone.utilities.Assets;
import deadzone.utilities.Sound;

/**
 *
 * @author USER
 */
public class HoleTrap extends Trap{
    private int index;
    
    public HoleTrap(float x, float y, int width, int height, Handler handler, int durata, Sound sound, int index) {
        super(x, y, width, height, handler, durata, sound);
        this.setX(x);
        this.setY(y);
        this.damage=20;
        this.index = index;
        this.damage=5000;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        g.drawImage(Assets.hole, (int) (getX()-40 - offsetX), (int) (getY()-40 - offsetY), null);
    }
    
    @Override
    public void animationCycle(){
        if(durata>0){
            super.animationCycle();
        }else{
            handler.removeSprite(this);
            this.sound.stopSound();
            Player p = handler.getPlayer();
            switch(index){
                case 1:
                    p.setHoleTrapActive1(false);
                    break;
                case 2:
                    p.setHoleTrapActive2(false);
                    break;
            }
        }
        
    }
}
