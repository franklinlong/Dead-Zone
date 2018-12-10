/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Graphics;
import utilities.Assets;
import utilities.Sound;

/**
 *
 * @author franc
 */
public class Coins extends DropItem {
    
    private Handler handler;
    private Sound coinsDrop;
    private int value;

    public Coins(float x, float y, int width, int height, Handler handler) {
        super(x, y, width, height);
        this.handler = handler;
        this.value = 10;
        this.coinsDrop = new Sound(Assets.coinsDrop);
    }
    
    

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        g.drawImage(Assets.coins, (int) (this.getX() - offsetX), (int) (this.getY() - offsetY), null);
    }

    @Override
    public void animationCycle() {
        if(this.isCollected(handler)){
            coinsDrop.playSound();
            handler.getPlayer().updateCoins(value);
            handler.removeSprite(this);
        }
    }
    
}
