/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author giova
 */
public class Handler {
    private List<Sprite> sprite = new ArrayList<>();
    
    public void animationCycle(){
        for(int i=0;i<sprite.size();i++){
            Sprite s = sprite.get(i);
            s.animationCycle();
        }
    }
    
    public void drawImage(Graphics g){
        for(int i=0;i<sprite.size();i++){
            Sprite s = sprite.get(i);
            s.drawImage(g);
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
}
