/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.geom.AffineTransform;


/**
 *
 * @author giova
 */
public abstract class AnimatedSprite extends Sprite{
    protected double angle;
    protected int health;
    protected AffineTransform at;

    public AnimatedSprite(int x, int y,int health) {
        super(x, y);
        this.health=health;
        angle=0;
    }
    
    public AnimatedSprite(int x, int y, int width, int height, int velX, int velY, int healt){
        super(x, y, width, height, velX, velY);
        this.health = health;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void setHealth(int health){
        this.health=health;
    }
}
