/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import sprite.animated.Player;

/**
 *
 * @author USER
 */
public abstract class DropItem extends Sprite{

    private final float probAmmo = 50;
    private final float probNuke = 20;
    private final float probMK = 30;
    
    public DropItem(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public boolean isCollected(Handler handler){
        float xPlayer = handler.getPlayer().getX();        
        float yPlayer = handler.getPlayer().getY();

        for(float k = xPlayer; k < xPlayer+Player.PLAYERSIZE; k++){
            for(float j = yPlayer; j < yPlayer+Player.PLAYERSIZE; j++){
                if((this.getX() <= k && k < this.getX()+width) && (j >= this.getY() && j < this.getY()+height) ){
                    return true;
                }
            }
        }
        return false;
    }

}
