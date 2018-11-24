package sprite.animated;

import deadzone.Handler;
import java.awt.Color;
import java.awt.Graphics;
import sprite.Sprite;

public class Projectile extends AnimatedSprite{

    private final static int BULLETDIAMETER = 6;
    private Handler handler;
    
    public Projectile(float x, float y, float velX, float velY, int velocita, int health, Handler handler){
            super(x, y, BULLETDIAMETER, BULLETDIAMETER, velocita, health);
            
            this.velX = velocita*velX;
            this.velY = velocita*velY;
            this.handler = handler;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        g.setColor(Color.RED);
        g.fillOval((int) (getX() - offsetX), (int) (getY() - offsetY), BULLETDIAMETER, BULLETDIAMETER);
    }

    @Override
    public void animationCycle() {
        setX(getX() + velX);
        setY(getY() + velY);
        
        if(dye()){
            this.handler.getProiettili().remove(this);
        }
    }
    
    //Collisioni statiche implementate con la mappa RBG
    public boolean dye(){
        //Se è uscito dalla mappa
        if(getX()>3200 || getX()<0 || getY()<0 || getY()>3200)
            return true;

        
        //se è andato contro un muro
        int pixel = mapRGB.getRGB((int)getX(),(int)getY());
        int red = (pixel >> 16) & 0xff;
        if(red==255)
            return true;
        
        //se ha colpito uno zombie
        for(Sprite x : handler.getZombies())
            if(x.getBounds().contains(getX(),getY())){
                Zombie y = (Zombie)x;
                y.hit(20);
                return true;
            }
        return false;
    }
}