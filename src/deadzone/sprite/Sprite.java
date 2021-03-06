/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.sprite;

import deadzone.SpriteVisitor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import deadzone.utilities.Assets;

/**
 *
 * @author giova
 */
public abstract class Sprite implements SpriteInterface{

    private float x, y;
    public int width, height;
    public static BufferedImage mapRGB = Assets.mapRGB;

    public Sprite(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void drawImage(Graphics g, float offsetX, float offsetY);

    public abstract void animationCycle();

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void death() {
    }

    public abstract void accept(SpriteVisitor visitor);
    
    //Utile per le collisioni
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
    
    public Rectangle getBoundsTrap(){
        Rectangle z = getBounds();
        z.y = z.y + z.height/3;
        z.height = z.height/3;
        z.x = z.x + z.width/3;
        z.width = z.width/3;
        return z;
    }
}
