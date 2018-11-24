/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import sprite.Sprite;
import sprite.animated.Player;
import sprite.animated.Zombie;


/**
 *
 * @author giova
 */
public class Handler {
    private final List<Sprite> sprite = new ArrayList<>();
    private static Camera camera;
    private final Player player;
    
    public Handler(){
        player = new Player(60,60,2,100,this);
        camera = new Camera(player);
        sprite.add(player);
        createZombie(); //Sara un thread probabilmente
    }
    
    public void animationCycle(){
        for(int i=0;i<sprite.size();i++){
            Sprite s = sprite.get(i);
            s.animationCycle();
        }
    }
    
    public void drawImage(Graphics g){
        float offsetX = camera.getOffset_x();
        float offsetY = camera.getOffset_y();
        for(int i=0;i<sprite.size();i++){
            Sprite s = sprite.get(i);
            s.drawImage(g,offsetX,offsetY);
        }
    }
    
    public void addSprite(Sprite s){
        sprite.add(s);
    }
    
    public void removeSprite(Sprite s){
        sprite.remove(s);
    }
    
    public List<Sprite> getSprite(){
        return sprite;
    }

    //DA FARE PER BENE BENE
    private void createZombie() {         
        addSprite((new Zombie(60, 500, 1, 100, this.player, this, (float)99)));
        addSprite((new Zombie(50, 500, 1, 100, this.player, this, (float)99)));
        addSprite((new Zombie(60, 400, 1, 100, this.player, this, (float)99)));
        addSprite((new Zombie(60, 300, 1, 100, this.player, this, (float)99)));
        addSprite((new Zombie(60, 200, 1, 100, this.player, this, (float)99)));
        addSprite((new Zombie(60, 100, 1, 100, this.player, this, (float)99)));
        addSprite((new Zombie(60, 700, 1, 100, this.player, this, (float)99)));
        addSprite((new Zombie(60, 800, 1, 100, this.player, this, (float)99)));
        addSprite((new Zombie(60, 900, 1, 100, this.player, this, (float)99)));

    }

    public Camera getCamera() {
        return camera;
    }

    public Player getPlayer() {
        return player;
    }
    
}
