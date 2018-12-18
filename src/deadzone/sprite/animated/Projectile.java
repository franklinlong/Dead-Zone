package deadzone.sprite.animated;

import deadzone.Handler;
import deadzone.SpriteVisitor;
import java.awt.Color;
import java.awt.Graphics;
import deadzone.sprite.Sprite;
import deadzone.sprite.SpriteInterface;
import java.util.List;


public class Projectile extends AnimatedSprite {

    private final static int BULLETDIAMETER = 6;
    protected Handler handler;
    protected final int damage;
    
    public Projectile(float x, float y, float velX, float velY, int velocita, int health, Handler handler, int damage) {
        super(x, y, BULLETDIAMETER, BULLETDIAMETER, (float)velocita, health);
        this.damage = damage;
        this.velX = velocita * velX;
        this.velY = velocita * velY;
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
        this.setHealth(this.getHealth() - 1);
        if (dye()) {
            this.handler.getProjectiles().remove(this);
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
        List<SpriteInterface> list = handler.getZombies();
        for(int i=0; i < list.size(); i++){
            Sprite x =(Sprite) list.get(i);
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
    
    public void accept(SpriteVisitor visitor){
        visitor.visit(this);
    }
}
