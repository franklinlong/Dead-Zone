/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import deadzone.menu.Settings;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author giova
 */
public class KAdapter extends KeyAdapter {

    public static boolean[] keys;
    public static boolean up, left, right, down;
    public static boolean one, two, three,four, action;
    public KAdapter() {
        keys = new boolean[256];
        up = false;
        left = false;
        right = false;
        down = false;
        action = false;
    }

    public void update() {

        if (Settings.padArrows) {
            up = keys[KeyEvent.VK_UP];
            left = keys[KeyEvent.VK_LEFT];
            right = keys[KeyEvent.VK_RIGHT];
            down = keys[KeyEvent.VK_DOWN];
        } else {
            up = keys[KeyEvent.VK_W];
            left = keys[KeyEvent.VK_A];
            right = keys[KeyEvent.VK_D];
            down = keys[KeyEvent.VK_S];
        }

        one = keys[KeyEvent.VK_1];
        two = keys[KeyEvent.VK_2];
        three = keys[KeyEvent.VK_3];
        four = keys[KeyEvent.VK_4];
        action = keys[KeyEvent.VK_SPACE];
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
