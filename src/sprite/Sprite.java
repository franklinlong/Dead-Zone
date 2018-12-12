/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import utilities.Assets;

/**
 *
 * @author giova
 */
public abstract class Sprite {

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

    ;
    
    //Utile per le collisioni
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
