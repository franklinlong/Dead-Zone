/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author giova
 */
public class Window extends JFrame{

    public Window(String playerName) {
        Image iconaFrame;
        iconaFrame = new ImageIcon(getClass().getResource("/images/icona_frame.png")).getImage();
        this.setIconImage(iconaFrame);  
        //IF single player:
        initUI(playerName);
    }

    private void initUI(String playerName) {
        add(new Board(playerName));
        Dimension dimensioneSchermo = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dimensioneSchermo);
        setResizable(false);
        setTitle("Dead Zone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
}