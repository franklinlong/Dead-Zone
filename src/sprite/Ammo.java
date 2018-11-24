/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Gun;
import deadzone.Handler;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author USER
 */
public class Ammo extends DropItem{

    private Handler handler;
    private Gun gun;
    private int incremento; //colpi aggiuntivi nel caricatore se raccolto
    private int incrementoTot; //colpi aggiuntivi totali se raccolto
    
    public Ammo(float x, float y, int width, int height, Handler handler) {
        super(x, y, width, height);
        this.handler = handler;
        this.gun = handler.getPlayer().getCurrentGun();
        this.incremento = 3;
        this.incrementoTot = 100;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        g.setColor(Color.YELLOW);
        g.fillRect((int) (getX() - offsetX), (int) (getY() - offsetY), width, height);
    }

    @Override
    public void animationCycle() {
        if(this.isCollected(handler)){
            gun.setTotalBullets(gun.getTotalBullets() + 10);
            gun.setRound(gun.getRound() + 3);
            handler.removeSprite(this);
        }
    };
    
}
