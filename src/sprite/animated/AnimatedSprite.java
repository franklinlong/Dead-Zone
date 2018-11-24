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
    
    
    
}
