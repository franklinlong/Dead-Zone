/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.sprite;

import java.awt.Graphics;

/**
 *
 * @author giova
 */
public interface SpriteInterface {
    public abstract void drawImage(Graphics g, float offsetX, float offsetY);

    public abstract void animationCycle();
}
