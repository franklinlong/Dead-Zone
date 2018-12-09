/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite.animated;

import deadzone.Handler;
import java.awt.Graphics;
import static sprite.Sprite.mapRGB;
import utilities.Assets;

/**
 *
 * @author casang
 */
public class Spittle extends AnimatedSprite {

    private Handler handler;
    private final int damage;
    private Player p;
    private String angle;

    public Spittle(float x, float y, float velX, float velY, float velocita, int health, Handler handler,
            int damage, Player p) {
        super(x, y, 3, 3, velocita, health);
        this.damage = damage;
        this.velX = velocita * velX;
        this.velY = velocita * velY;
        this.handler = handler;
        this.p = p;
        addAngle();
    }

    private void addAngle() {
        float playerx = p.getX();
        float playery = p.getY();

        if (playery < this.getY()) {
            if (playerx < this.getX()) {
                angle = "nordovest";
            } else if (playerx > this.getX()) {
                angle = "nordest";
            } else {
                angle = "nord";
            }
        } else if (playery > this.getY()) {
            if (playerx < this.getX()) {
                angle = "sudovest";
            } else if (playerx > this.getX()) {
                angle = "sudest";
            } else {
                angle = "sud";
            }
        } else {
            if (playerx < this.getX()) {
                angle = "ovest";
            } else {
                angle = "est";
            }
        }

    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {

        switch (angle) {
            case "nordovest":
                g.drawImage(Assets.spittle_no, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
                break;
            case "nordest":
                g.drawImage(Assets.spittle_ne, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
                break;
            case "nord":
                g.drawImage(Assets.spittle_n, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
                break;
            case "sudovest":
                g.drawImage(Assets.spittle_so, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
                break;
            case "sudest":
                g.drawImage(Assets.spittle_se, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
                break;
            case "sud":
                g.drawImage(Assets.spittle_s, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
                break;
            case "ovest":
                g.drawImage(Assets.spittle_o, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
                break;
            default:
                g.drawImage(Assets.spittle_e, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
        }
    }

    @Override
    public void animationCycle() {
        setX(getX() + velX);
        setY(getY() + velY);
        this.setHealth(this.getHealth() - 1);
        if (dye()) {
            this.handler.getSpittles().remove(this);
        }
    }

    //Collisioni statiche implementate con la mappa RBG
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
        if (p.getBounds().contains(getX(), getY())) {
            Player y = p;
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
