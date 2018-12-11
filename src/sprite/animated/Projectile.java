package sprite.animated;

import deadzone.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import sprite.Circle;
import sprite.Sprite;
import static sprite.animated.StandardZombie.ZOMBIESIZE;
import utilities.Animation;
import utilities.Assets;

public class Projectile extends AnimatedSprite {

    private final static int BULLETDIAMETER = 6;
    protected Handler handler;
    protected final int damage;
    private boolean rpgEquip;
    
    public Projectile(float x, float y, float velX, float velY, int velocita, int health, Handler handler, int damage) {
        super(x, y, BULLETDIAMETER, BULLETDIAMETER, (float)velocita, health);
        this.damage = damage;
        this.velX = velocita * velX;
        this.velY = velocita * velY;
        this.handler = handler;
        rpgEquip=this.handler.getPlayer().getCurrentGun().getSkin() == Assets.rpgSkin;
        //QUI FUNZIONA MA VA RIFATTO MEGLIO, da qui-->
        if (velX == 0) {
                if (velY > 0) {
                    this.setAngle((float) -Math.PI / 2);
                } else if (velY < 0) {
                    this.setAngle((float) +Math.PI / 2);
                } else {
                    System.err.println("Penso sia tutto ok... ma non lo è, nella drow del proeittile dell'rpg");
                }
            } else {
                this.setAngle((float) Math.atan(velY / velX));
                if (velX < 0) {
                    this.setAngle((float) -Math.PI + this.getAngle());
                }
            }
        //<--fino a qui
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        if (rpgEquip) {
            float xx, yy;
            xx = getX() - offsetX;
            yy = getY() - offsetY;
            at = AffineTransform.getTranslateInstance(xx, yy);
            at.rotate(angle, BULLETDIAMETER / 2, BULLETDIAMETER / 2);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(Assets.rocketbull, at, null);
        } else {
            g.setColor(Color.RED);
            g.fillOval((int) (getX() - offsetX), (int) (getY() - offsetY), BULLETDIAMETER, BULLETDIAMETER);
        }
    }

    @Override
    public void animationCycle() {
        setX(getX() + velX);
        setY(getY() + velY);
        this.setHealth(this.getHealth() - 1);
        if (dye()) {
            this.handler.getProiettili().remove(this);
            if(rpgEquip){
                //ventaglio a 360 di proiettili
                Circle circle = new Circle(this.getX(),this.getY(),200,200,1000,this.handler);
                this.handler.addSprite(circle);
                }
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
        
        //se ha colpito uno zombie
        for (Sprite x : handler.getZombies()) {
            if (x.getBounds().contains(getX(), getY())) {
                Zombie y = (Zombie) x;
                y.hit(damage);
                return true;
            }
        }

        //I proiettili hanno una portata limitata
        if (this.getHealth() == 0) {
            return true;
        }
        
        return false;
    }
}
