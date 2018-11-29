/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite.animated;

import java.awt.geom.AffineTransform;
import sprite.Sprite;

/**
 *
 * @author giova
 */
public abstract class AnimatedSprite extends Sprite{
    //essendo protected, non ho bisono di get e set
    protected float angle;
    private int health;
    protected final int initialVelocity;
    public float velX, velY;
    protected AffineTransform at;
    
    public AnimatedSprite(float x, float y, int width, int height, int vel, int health){
        super(x, y, width, height);
        this.health = health;
        this.initialVelocity = vel;
        this.velX = 0;
        this.velY = 0;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void setHealth(int health){
        this.health=health;
    }

    public float getAngle() {
        return angle;
    }
    
    public int collision(float velx, float vely) {
        int xx, yy; //erano float
        boolean destra = false;
        boolean sinistra = false;
        boolean sopra = false;
        boolean sotto = false;
        int avanzamentoX = (int) velx;
        int avanzamentoY = (int) vely;
        
        Float fx = this.getX();
        Float fy = this.getY();
        int x = fx.intValue();
        int y = fy.intValue();
                
        for (xx = x; xx < x + width; xx++) {
            for (yy = y; yy < y + height; yy++) {
                if ((xx < x+avanzamentoX+1 && yy < y+avanzamentoY+1) || (xx > x + width - avanzamentoX -1 && yy < y + avanzamentoY+1) || (xx < x + avanzamentoX+1 && yy > y + height - avanzamentoY-1) || (xx > x + width - avanzamentoX-1 && yy > y + height - avanzamentoY-1)) {}
                else if(xx >= x + avanzamentoX+1 && yy >= y + avanzamentoY+1 && xx <=x + width -avanzamentoX-1 && yy <= y + height -avanzamentoY-1) {}
                else{
                    //System.out.println(x);
                    int pixel = mapRGB.getRGB((int)xx, (int) yy);
                    int red = (pixel >> 16) & 0xff;

                    if (red == 255) {
                        if(xx >= x + avanzamentoX+1 && yy < y + avanzamentoY+1 && xx <= x + width -avanzamentoX-1)
                            sopra = true;
                        else if(xx < x + avanzamentoX+1 && yy >= y +avanzamentoY+1 && yy <= y + height -avanzamentoY-1)
                            sinistra = true;
                        else if(xx >= x+ avanzamentoX+1 && yy >y + height -avanzamentoY-1 && xx <= x + width -avanzamentoX-1)
                            sotto = true;
                        else if(xx > x + width -avanzamentoX-1 && yy >= y+avanzamentoY+1 && yy <= y + height -avanzamentoY-1)
                            destra = true;
                    }
                }
            }
        }
        if ((sopra==true && destra == true) || (sopra==true && sinistra == true) || (sotto==true && sinistra == true) || (sotto==true && destra == true) || (sopra==true && sinistra == true && destra == true) || (sopra==true && sinistra == true && destra == true))
            return 3;
        else if( sopra==true || sotto == true)
            return 2;
        else if (sinistra == true || destra == true)
            return 1;
        else
            return 0;
    }
    
}
