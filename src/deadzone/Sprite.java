/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Graphics;
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
    protected int initialVelocity=0;
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
    
    public int collision() {
        int xx, yy;
        boolean destra = false;
        boolean sinistra = false;
        boolean sopra = false;
        boolean sotto = false;
        for (xx = x; xx < x + width; xx++) {
            for (yy = y; yy < y + height; yy++) {
                if ((xx < x+5 && yy < y+5) || (xx > x + width - 6 && yy < y + 5) || (xx < x + 5 && yy > y + height - 6) || (xx > x + width - 6 && yy > y + height - 6)) {}
                else if(xx >= x + 6 && yy >= y + 6 && xx <=x + width -6 && yy <= y + height -6) {}
                else{
                    //System.out.println(x);
                    int pixel = mapRGB.getRGB(xx, yy);
                    int red = (pixel >> 16) & 0xff;

                    if (red == 255) {
                        if(xx >= x + 6 && yy < y + 6 && xx <= x + width -6 && xx <= x + width -6)
                            sopra = true;
                        else if(xx < x + 6 && yy >= y +6 && yy <= y + height -6)
                            sinistra = true;
                        else if(xx >= x+ 6 && yy >y + height -6 && xx <= x + width -6)
                            sotto = true;
                        else if(xx > x + width -6 && yy >= y+6 && yy <= y + height -6)
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
