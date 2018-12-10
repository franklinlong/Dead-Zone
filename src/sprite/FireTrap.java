/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import utilities.Animation;
import utilities.Assets;
import java.awt.geom.AffineTransform;
import sprite.animated.Zombie;

/**
 *
 * @author USER
 */
public class FireTrap extends Trap{
    private int durata;
    private final Animation animazione;
    private Rectangle r1;    
    private Rectangle r2;
    private Rectangle r3;
    private Rectangle r4;

    
    public FireTrap(float x, float y, int width, int height, Handler handler) {
        super(x, y, width, height, handler);
        this.durata=600;
        this.animazione = new Animation(Assets.fuochi, 200);
        this.r1 = new Rectangle((int)x, (int)y, width, 40);
        this.r2 = new Rectangle((int)x, (int)y+560, width, 40);
        this.r3 = new Rectangle((int)x, (int) y + 40, 30, 560);
        this.r4 = new Rectangle((int)x + 1290, (int)y + 40, 30,560);
        
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
//        g.setColor(Color.yellow);
//        g.fillRect((int) (getX() - offsetX),(int) (getY() - offsetY), width, height);
         
        g.drawImage(animazione.getCurrentFrame(), (int) (getX() - offsetX), (int) (getY() - offsetY), null);        
    }
    
    @Override
    public void animationCycle(){
        if(durata>0){
            for (Sprite s : handler.getZombies()){
                if(s instanceof Zombie){
                    Rectangle zombie = s.getBounds();
                    if(zombie.intersects(r1) || zombie.intersects(r2) || zombie.intersects(r3) || zombie.intersects(r4))
                        ((Zombie) s).hit(500);
                }
            }
            durata--;
        }else
            handler.removeSprite(this);
        
        animazione.update();
    }
}
