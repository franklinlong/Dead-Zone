/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author giova
 */
public class FListener implements FocusListener{

    @Override
    public void focusGained(FocusEvent fe) {
    }

    @Override
    public void focusLost(FocusEvent fe) {
        KAdapter.keys[KeyEvent.VK_UP] = false;
        KAdapter.keys[KeyEvent.VK_DOWN] = false;
        KAdapter.keys[KeyEvent.VK_LEFT] = false;
        KAdapter.keys[KeyEvent.VK_RIGHT] = false;
        KAdapter.keys[KeyEvent.VK_W] = false;
        KAdapter.keys[KeyEvent.VK_S] = false;
        KAdapter.keys[KeyEvent.VK_A] = false;
        KAdapter.keys[KeyEvent.VK_D] = false;
    }
    
}
