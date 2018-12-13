/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JPanel;

/**
 *
 * @author USER
 */
public class Immagine extends JPanel {
        Image img1;

        public Immagine(URL url) {
            Toolkit tk = Toolkit.getDefaultToolkit();
            img1 = tk.getImage(url);
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Dimension dim2 = Toolkit.getDefaultToolkit().getScreenSize();
            g.drawImage(img1,0,0,dim2.width*2/5,720, null);
        }
    }
