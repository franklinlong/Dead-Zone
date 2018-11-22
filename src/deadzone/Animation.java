/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.image.BufferedImage;

/**
 *
 * @author giova
 */
public class Animation {
    
    private final BufferedImage[] frames;
    private final int velocity;
    private int index;
    private long time, lastTime;

    public Animation(BufferedImage[] frames, int velocity){
            this.frames = frames;
            this.velocity = velocity;

            time = 0;
            lastTime = 0;
            index = 0;

    }

    public void update(){

            time += System.currentTimeMillis() - lastTime;

            lastTime = System.currentTimeMillis();

            if(time > velocity){
                    time = 0;
                    index ++;
                    if(index == frames.length){
                            index = 0;
                    }

            }

    }

    public BufferedImage getCurrentFrame(){
            return frames[index];
    }

    public void setIndex(){
            index = 0;
    }

    public int getIndex(){
            return index;
    }

    public int getLenght(){
            return frames.length;
    }

    public int getVelocity(){
            return velocity;
    }
}
