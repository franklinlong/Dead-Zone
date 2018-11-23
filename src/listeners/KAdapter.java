/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author giova
 */
public class KAdapter extends KeyAdapter{
	
    public static boolean[] keys;
    public static boolean up,left,right, down;
    public static boolean one,two,three,reload;

    public KAdapter(){
        keys = new boolean[256];
        up = false;
        left = false;
        right = false;
        down = false;
    }
    
    public void update(){
        
        up = keys[KeyEvent.VK_W];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        down = keys[KeyEvent.VK_S];
        one = keys[KeyEvent.VK_1];
        two = keys[KeyEvent.VK_2];
        three = keys[KeyEvent.VK_3];
        reload = keys[KeyEvent.VK_R];
    }


    @Override
    public void keyPressed(KeyEvent e) {   
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
