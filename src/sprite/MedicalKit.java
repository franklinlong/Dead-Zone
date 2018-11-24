/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author USER
 */
public class MedicalKit extends DropItem{

    private Handler handler;
    
    public MedicalKit(float x, float y, int width, int height, Handler handler) {
        super(x, y, width, height);
        this.handler = handler;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        g.setColor(Color.BLUE);
        g.fillRect((int) (getX() - offsetX), (int) (getY() - offsetY), width, height);
    }

    @Override
    public void animationCycle() {
        if(this.isCollected(handler)){
            handler.getPlayer().setHealth(100);
            handler.removeSprite(this);
        }
    };
    
}
