/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.sprite;

import deadzone.Handler;
import java.awt.Graphics;
import deadzone.sprite.animated.Boss;
import deadzone.sprite.animated.Zombie;
import deadzone.utilities.Assets;
import deadzone.utilities.Sound;

/**
 *
 * @author USER
 */
public class Nuke extends DropItem {

    private Handler handler;
    private Sound explosion;
    
    public Nuke(float x, float y, int width, int height, Handler handler) {
        super(x, y, width, height);
        this.handler = handler;
        this.explosion = new Sound(Assets.nukeExplosion);
        this.explosion.changeVolume(-10);
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        g.drawImage(Assets.nuke, (int) (getX() - offsetX - 20), (int) (getY() - offsetY - 20), null);
    }

    @Override
    public void animationCycle() {
        if (this.isCollected(handler)) {
            explosion.playSound();
            for (int i = 0; i < handler.getZombies().size(); i++) {
                Zombie z = (Zombie) handler.getZombies().get(i);
                if (!(z.getInitialVelocity() == 1)) {   //1 velocity of the boss
                    z.death();
                }

            }
            handler.removeSprite(this);
        }
    }
;

}
