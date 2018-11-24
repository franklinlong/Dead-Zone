/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Color;
import java.awt.Graphics;
import utilities.Assets;

/**
 *
 * @author USER
 */
public class Nuke extends DropItem{

    private Handler handler;
    
    public Nuke(float x, float y, int width, int height, Handler handler) {
        super(x, y, width, height);
        this.handler = handler;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        g.drawImage(Assets.nuke, (int)(getX() - offsetX -20), (int)(getY() - offsetY -20), null);
    }

    @Override
    public void animationCycle() {
        if(this.isCollected(handler)){
            handler.getSprite().clear();
            handler.removeSprite(this);
        }
    };
    
}
