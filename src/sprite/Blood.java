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
 * @author giova
 */
public class Blood extends Sprite {

    private final Handler handler;
    private int alfa;

    public Blood(float x, float y, int width, int height, Handler handler) {
        super(x, y, width, height);
        this.handler = handler;
        this.alfa = 200;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        g.drawImage(Assets.blood, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
    }

    @Override
    public void animationCycle() {
        //a tentativi.. Circa 2 secondi
        alfa -= 1;
        if (alfa < 0) {
            handler.getitemsAndBlood().remove(this);
        }
    }

}
