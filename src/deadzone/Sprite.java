/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author giova
 */
public abstract class Sprite {
    
    protected int x,y;
    protected int velX=0,velY=0;
    protected int width,height;
    public static BufferedImage mapRGB = Sprite.caricaMappaRGB();
    
    boolean R=false,L=false,U=false,D=false;

    public Sprite(int x,int y){
        this.x=x;
        this.y=y;
        
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public Sprite(int x, int y, int width, int height, int velX, int velY){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;
    
}
    
    public abstract void drawImage(Graphics g, int offsetX, int offsetY);
    
    public abstract void animationCycle();

    public int getX() {
        return x;
    }
    
    public void setX(int x){
        this.x=x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y){
        this.y=y;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public boolean isR() {
        return R;
    }

    public void setR(boolean R) {
        this.R = R;
    }

    public boolean isL() {
        return L;
    }

    public void setL(boolean L) {
        this.L = L;
    }

    public boolean isU() {
        return U;
    }

    public void setU(boolean U) {
        this.U = U;
    }

    public boolean isD() {
        return D;
    }

    public void setD(boolean D) {
        this.D = D;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }
    
     public int getCenterY() {
        return y + height/2;
    }
      public int getCenterX() {
        return x + width/2;
    }
    
    
    private static BufferedImage caricaMappaRGB() {
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(new File("mapRGB.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        
        return image;
    }
    
    public boolean collision(){
        int xx, yy;
        for(xx = x; xx < x+width ; xx++){
            for(yy = y; yy < y+height ; yy++){
                int pixel = mapRGB.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green  = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                
                if (red == 255)
                    return true;
            }
        }
        return false;
    }
    
}
