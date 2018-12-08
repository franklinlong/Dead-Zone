/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite.animated;

import deadzone.Handler;
import java.awt.Graphics;
import static sprite.Sprite.mapRGB;
import utilities.Animation;
import utilities.Assets;
import utilities.Sound;

/**
 *
 * @author genna
 */

public class SpawnSpittle extends AnimatedSprite {

    private Handler handler;
    //private final int damage;
    private String angle;
    
    //a che serve il player??
    private Player p;
    private float zombie_x;
    private float zombie_y;

    public SpawnSpittle(float x, float y, float velX, float velY, int velocita, int health, Handler handler,
            Player p) {
        super(x, y, 3, 3, velocita, health);
        //this.damage = damage;
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
          g.drawImage(Assets.spawnSpittle, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
//        switch (angle) {
//            case "nordovest":
//                g.drawImage(Assets.spittle_no, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
//                break;
//            case "nordest":
//                g.drawImage(Assets.spittle_ne, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
//                break;
//            case "nord":
//                g.drawImage(Assets.spittle_n, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
//                break;
//            case "sudovest":
//                g.drawImage(Assets.spittle_so, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
//                break;
//            case "sudest":
//                g.drawImage(Assets.spittle_se, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
//                break;
//            case "sud":
//                g.drawImage(Assets.spittle_s, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
//                break;
//            case "ovest":
//                g.drawImage(Assets.spittle_o, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
//                break;
//            default:
//                g.drawImage(Assets.spittle_e, (int) (getX() - offsetX), (int) (getY() - offsetY), null);
//        }
    }

    @Override
    public void animationCycle() {
        setX(getX() + velX);
        setY(getY() + velY);
        this.setHealth(this.getHealth() - 1);
        
        if (dye()) {
            this.handler.getspawnSpittles().remove(this);
            }
    }

    //Collisioni statiche implementate con la mappa RBG
    public boolean dye() {
        if (getX() > 3200 || getX() < 0 || getY() < 0 || getY() > 3200) {
            return true;
        }
        int pixel = mapRGB.getRGB((int) getX(), (int) getY());
        int red = (pixel >> 16) & 0xff;
        if(red==255){this.handler.getWaves().removeEnemy(); return true;}
        else{zombie_x = getX(); zombie_y = getY();}
        //I proiettili hanno una portata limitata o se ha colpito il player o se è uscito dalla mappa o se è andato contro un muro
        if ((this.getHealth() == 0) ||  (p.getBounds().contains(getX(), getY()))) {
            int n = (int) (Math.random() * 10);
            if(n>1) n=2;
            switch (n) {
                case 2:
                    this.handler.addSprite(new StandardZombie(zombie_x, zombie_y, 1, 200, 25, handler.getPlayer(), this.handler, 0, 60, 60, 5, new Animation(Assets.zombie, 20), new Animation(Assets.zombieAttack, 35), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
                    break;
                case 0:
                    this.handler.addSprite(new StandardZombie(zombie_x, zombie_y, 1, 70, 40, handler.getPlayer(), this.handler, 0, 60, 60, 5, new Animation(Assets.zombie2, 15), new Animation(Assets.zombie2Attack, 15), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
                    break;
                case 1:
                    this.handler.addSprite(new SpittleZombie(zombie_x, zombie_y, 1, 500, 50, handler.getPlayer(), this.handler, 0, 60, 60, 5, new Animation(Assets.zombie3, 15), new Animation(Assets.zombie3Attack, 15), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
                    break;
            }
            return true;
        }
        
        return false;
    }
    
//    private void control(){
//        System.out.println("w");
//        switch (angle) {
//            case "nordovest":
//                zombie_x = this.getX()+60;
//                zombie_y = this.getY() +60;
//            case "nordest":
//                zombie_x = this.getX()-60;
//                zombie_y = this.getY() +60;
//            case "nord":
//                zombie_x = this.getX();
//                zombie_y = this.getY() +60;
//            case "sudovest":
//                zombie_x = this.getX()+60;
//                zombie_y = this.getY() -60;
//            case "sudest":
//                zombie_x = this.getX()-60;
//                zombie_y = this.getY() -60;
//            case "sud":
//                zombie_x = this.getX();
//                zombie_y = this.getY() -60;
//            case "ovest":
//                zombie_x = this.getX()+60;
//                zombie_y = this.getY();
//            default:
//                zombie_x = this.getX()+60;
//                zombie_y = this.getY();
//        }
//    }
}
