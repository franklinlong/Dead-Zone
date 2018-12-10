/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite.animated;

import deadzone.Handler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import static sprite.Sprite.mapRGB;
import utilities.Assets;

/**
 *
 * @author casang
 */
public class Spittle extends Projectile {

    public Spittle(float x, float y, float velX, float velY, int velocita, int health, Handler handler, int damage) {
        super(x, y, velX, velY, velocita, health, handler, damage);
        
        angle = (float) Math.atan(velY / velX);
        if (velX < 0) {
            angle = (float) -Math.PI + angle;
        }
        
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        float xx, yy;
        xx = getX() - offsetX;
        yy = getY() - offsetY;

        at = AffineTransform.getTranslateInstance(xx, yy);
        at.rotate(angle, this.width/2, this.height/2);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(Assets.spittle_e, at, null);
        
    }

    //Collisioni statiche implementate con la mappa RBG
    @Override
    public boolean dye() {
        //Se è uscito dalla mappa
        if (getX() > 3200 || getX() < 0 || getY() < 0 || getY() > 3200) {
            return true;
        }

        //se è andato contro un muro
        int pixel = mapRGB.getRGB((int) getX(), (int) getY());
        int red = (pixel >> 16) & 0xff;
        if (red == 255) {
            return true;
        }

        //se ha colpito il player
        if (handler.getPlayer().getBounds().contains(getX(), getY())) {
            Player y = handler.getPlayer();
            y.hit(damage);
            return true;
        }
        
        //I proiettili hanno una portata limitata
        if (this.getHealth() == 0) {
            return true;
        }

        return false;
    }
}
