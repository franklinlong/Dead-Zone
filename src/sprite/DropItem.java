/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;

/**
 *
 * @author USER
 */
public abstract class DropItem extends Sprite {

    public DropItem(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public boolean isCollected(Handler handler) {
        return handler.getPlayer().getBounds().intersects(this.getBounds());
    }

}
