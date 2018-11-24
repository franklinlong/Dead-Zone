/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

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
    
    private float x,y;
    public int width,height;
    public static BufferedImage mapRGB = Sprite.caricaMappaRGB();
    
    public Sprite(float x, float y, int width, int height){
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
    
    public void setX(float x){
        this.x=x;
    }
    
    public float getY() {
        return y;
    }
    
    public void setY(float y){
        this.y=y;
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
        float xx, yy;
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
                    int pixel = mapRGB.getRGB((int)xx, (int) yy);
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
    
    //Utile per le collisioni
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, width,height);
    }
}
