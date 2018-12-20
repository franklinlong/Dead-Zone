/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.sprite.animated;

import deadzone.Handler;
import deadzone.SpriteVisitor;
import deadzone.menu.Menu;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import deadzone.utilities.Animation;
import deadzone.utilities.Assets;
import deadzone.utilities.Sound;

/**
 *
 * @author genna
 */

public class SpawnSpittle extends Spittle {
    
    private float zombie_x;
    private float zombie_y;
    
    public SpawnSpittle(float x, float y, float velX, float velY, float velocita, int health, Handler handler) {
        super(x, y, 3, 3, (int)velocita, health, handler, 0);
        this.width = 60;
        this.height = 60;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        float xx, yy;
        xx = getX() - offsetX;
        yy = getY() - offsetY;

        at = AffineTransform.getTranslateInstance(xx, yy);
        at.rotate(angle, this.width/2, this.height/2);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(Assets.spawnSpittle, at, null);
    }
    
    //Collisioni statiche implementate con la mappa RBG
    @Override
    public boolean dye() {
        if (getX() > 3100 || getX() < 10 || getY() < 10 || getY() > 3100) {
            return true;
        }
//        int pixel = mapRGB.getRGB((int) getX(), (int) getY());
//        int red = (pixel >> 16) & 0xff;
//        if(red==255){this.handler.getWaves().removeEnemy(); return true;}
        int k = collision(velX, velY, this.getX(), this.getY());
        if(k!=0) {
            this.handler.getWaves().removeEnemy(); 
            return true;
        }
        else{
            zombie_x = getX(); 
            zombie_y = getY();
        }
        //I proiettili hanno una portata limitata o se ha colpito il player o se è uscito dalla mappa o se è andato contro un muro
        if ((this.getHealth() == 0) ||  (handler.getPlayer().getBounds().contains(getX(), getY()))) {
            int vel=3;
            if(Menu.demo)
                vel = 3;
            else
                vel=2;
            
            int n = (int) (Math.random() * 10);                
            if(n>1) n=2;
            switch (n) {
                case 2:
                    this.handler.addSprite(new StandardZombie(zombie_x, zombie_y, vel, 200, 25, handler.getPlayer(), this.handler, 0, 60, 60, 5, new Animation(Assets.zombie, 20), new Animation(Assets.zombieAttack, 35), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
                    break;
                case 0:
                    this.handler.addSprite(new StandardZombie(zombie_x, zombie_y, 4, 70, 50, handler.getPlayer(), this.handler, 0, 60, 60, 20, new Animation(Assets.zombie2, 15), new Animation(Assets.zombie2Attack, 15), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
                    break;
                case 1:
                    this.handler.addSprite(new SpittleZombie(zombie_x, zombie_y, vel, 500, 40, handler.getPlayer(), this.handler, 0, 60, 60, 45, new Animation(Assets.zombie3, 15), new Animation(Assets.zombie3Attack, 15), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
                    break;
            }
            return true;
        }
        
        return false;
    }
    
    @Override
    public void accept(SpriteVisitor visitor){
        visitor.visit(this);
    }
}
