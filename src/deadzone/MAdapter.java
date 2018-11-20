/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author giova
 */
public class MAdapter implements MouseMotionListener,MouseListener{
    static int x,y;
    static boolean left,right;
    
    @Override
    public void mouseMoved(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        System.out.println("moved ," + this.x + " " + this.y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            left = true;
        if(e.getButton() == MouseEvent.BUTTON3)
            right = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            left = false;
        if(e.getButton() == MouseEvent.BUTTON3)
            right = false;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        this.x = me.getX();
        this.y = me.getY();
        System.out.println("dragged ," + this.x + " " + this.y);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}
