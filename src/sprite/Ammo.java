/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author USER
 */
public class Ammo extends Sprite{

    public Ammo(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        System.out.println(getX());
        g.setColor(Color.YELLOW);
        g.fillRect((int) getX(), (int) getY(), width, height);
    }

    @Override
    public void animationCycle() {};
    
}
