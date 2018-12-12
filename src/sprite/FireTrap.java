/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import utilities.Animation;
import utilities.Assets;
import javax.swing.Timer;
import sprite.animated.Player;
import sprite.animated.Zombie;

/**
 *
 * @author USER
 */
public class FireTrap extends Trap{
    private final Animation animazione;
    private final Rectangle r1;    
    private final Rectangle r2;
    private final Rectangle r3;
    private final Rectangle r4;
    private final Timer durata;
    
    public FireTrap(float x, float y, int width, int height, Handler handler, Timer durata) {
        super(x, y, width, height, handler);
        this.durata = durata;
        this.animazione = new Animation(Assets.fuochi, 200);
        this.r1 = new Rectangle((int)x, (int)y, width, 40);
        this.r2 = new Rectangle((int)x, (int)y+560, width, 40);
        this.r3 = new Rectangle((int)x, (int) y + 40, 30, 560);
        this.r4 = new Rectangle((int)x + 1290, (int)y + 40, 30,560);
        
        this.damage = 30;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        g.drawImage(animazione.getCurrentFrame(), (int) (getX() - offsetX), (int) (getY() - offsetY), null);        
    }
    
    @Override
    public void animationCycle(){
        Player p;
        if(durata.isRunning()){
            for (Sprite s : handler.getZombies()){
                if(s instanceof Zombie){
                    Rectangle zombie = s.getBoundsTrap();
                    if(zombie.intersects(r1) || zombie.intersects(r2) || zombie.intersects(r3) || zombie.intersects(r4))
                        ((Zombie) s).hit(damage);
                }
            }
            p = handler.getPlayer();
            if(p.getBoundsTrap().intersects(r1) || p.getBoundsTrap().intersects(r2) || p.getBoundsTrap().intersects(r3) || p.getBoundsTrap().intersects(r4))
                p.hit(damage);
        }else
            handler.removeSprite(this);
        
        animazione.update();
    }
}
