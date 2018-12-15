/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.sprite;

import deadzone.Gun;
import deadzone.Handler;
import java.awt.Graphics;
import deadzone.utilities.Assets;
import deadzone.utilities.Sound;

/**
 *
 * @author USER
 */
public class Ammo extends DropItem {

    private final Handler handler;
    private Gun gun;
    private final Sound sound;

    public Ammo(float x, float y, int width, int height, Handler handler) {
        super(x, y, width, height);
        this.handler = handler;
        this.sound = new Sound(Assets.ammoPickUp);
        this.sound.changeVolume(-10);
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        g.drawImage(Assets.ammo, (int) (getX() - offsetX - 20), (int) (getY() - offsetY - 20), null);
    }

    @Override
    public void animationCycle() {
        if (this.isCollected(handler)) {
            sound.playSound();
            Gun[] allGuns = this.handler.getPlayer().getAllGuns();
            allGuns[0].setTotalBullets(allGuns[0].getTotalBullets() + 45);
            allGuns[1].setTotalBullets(allGuns[1].getTotalBullets() + 105);
            allGuns[2].setTotalBullets(allGuns[2].getTotalBullets() + 24);
            allGuns[3].setTotalBullets(allGuns[3].getTotalBullets() + 1);
            handler.removeSprite(this);
        }
    }


}
