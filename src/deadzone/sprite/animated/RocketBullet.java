/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.sprite.animated;

import deadzone.Handler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import deadzone.sprite.Circle;
import deadzone.utilities.Assets;

/**
 *
 * @author USER
 */
public class RocketBullet extends Projectile{

    private static int BULLETDIAMETER;
    
    public RocketBullet(float x, float y, float velX, float velY, int velocita, int health, Handler handler, int damage) {
        super(x, y, velX, velY, velocita, health, handler, damage);
        
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
    }
    
    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY){
        float xx, yy;
            xx = getX() - offsetX;
            yy = getY() - offsetY;
            at = AffineTransform.getTranslateInstance(xx, yy);
            at.rotate(angle, BULLETDIAMETER/2, BULLETDIAMETER/2);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(Assets.rocketbull, at, null);
    }
    
    @Override
    public void animationCycle() {
        setX(getX() + velX);
        setY(getY() + velY);
        this.setHealth(this.getHealth() - 1);
        if (dye()) {
            this.handler.getProiettili().remove(this);
            Circle circle = new Circle(this.getX(),this.getY(),40,this.handler);
            this.handler.addSprite(circle);
        }
    }
    
}
