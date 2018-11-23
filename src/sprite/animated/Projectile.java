package sprite.animated;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends AnimatedSprite{

    private final static int BULLETDIAMETER = 6;
    
    
    public Projectile(float x, float y, float velX, float velY, int velocita, int health){
            super(x, y, BULLETDIAMETER, BULLETDIAMETER, velocita, health);
            
            this.velX = velocita*velX;
            this.velY = velocita*velY;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        g.setColor(Color.RED);
        g.fillOval((int) getX(), (int) getY(), BULLETDIAMETER, BULLETDIAMETER);
    }

    @Override
    public void animationCycle() {
        setX(getX() + velX);
        setY(getY() + velY);
    }
}