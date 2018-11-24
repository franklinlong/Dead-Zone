/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
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

    //Gestisce lo spawn dello zombie
    private final Timer spawn = new Timer(2000, new ActionListener(){
        int spawnX;
        int spawnY;
        @Override
        public void actionPerformed(ActionEvent e) {
            int n = (int) (Math.random()*5);
            switch(n){
                case 0:                     //Fosso
                    spawnX=2150;
                    spawnY=2540;
                    break;
                case 1:                     //Tomba 11
                    spawnX=2224;
                    spawnY=242;
                    break;
                case 2:                     //Tomba 21
                    spawnX=2700;
                    spawnY=242;
                    break;
                case 3:                     //Tomba 12
                    spawnX=2420;
                    spawnY=470;
                    break;
                case 4:                     //Tomba 22
                    spawnX=2800;
                    spawnY=470;
                    break;
            }
            createZombie(spawnX,spawnY);
        }});
    
    public Handler(){
        player = new Player(60,60,2,100,this);
        camera = new Camera(player);
        sprite.add(player);
        spawn.start();
        
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
    private void createZombie(float x, float y) {         
        System.out.println(x + " " + y);
        addSprite((new Zombie(x, y, 1, 100, this.player, this, (float)99)));

    }

    public Camera getCamera() {
        return camera;
    }

    public Player getPlayer() {
        return player;
    }
    
}
