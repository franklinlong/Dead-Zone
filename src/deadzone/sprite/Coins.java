/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.sprite;

import deadzone.Handler;
import java.awt.Graphics;
import deadzone.utilities.Assets;
import deadzone.utilities.Sound;

/**
 *
 * @author franc
 */
public class Coins extends DropItem {
    
    private final Handler handler;
    private final Sound coinsDrop;
    private final int value;

    public Coins(float x, float y, int width, int height, Handler handler) {
        super(x, y, width, height);
        this.handler = handler;
        this.value = 2;
        this.coinsDrop = new Sound(Assets.coinsDrop);
        this.coinsDrop.changeVolume(-10);
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
