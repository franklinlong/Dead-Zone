 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Toolkit;
import sprite.animated.Player;

/**
 *
 * @author Enrico
 */
public class Camera {
    
    private final Player p;
    
    //posizione giocatore riferito alla camera/frame
    private float offset_x;
    private float offset_y;
    
    //dimensione frame uguali allo schermo
    public static int w_frame = Toolkit.getDefaultToolkit().getScreenSize().width * 4/5;
    public static int h_frame = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    //dimensione immagine
    private final int w_map = 3200;
    private final int h_map = 3200;

    public Camera(Player p) {
        this.p = p;
    }

    public float getOffset_x() {
        calcola_offset();
        return offset_x;
    }

    public float getOffset_y() {
        calcola_offset();
        return offset_y;
    }
    
    //calcola il primo pixel da stampare (in alto a sx del frame)
    public void calcola_offset(){  
        if (p.getX()<=w_frame/2 && p.getY()<=h_frame/2){ //zona1
            this.offset_x=0;
            this.offset_y=0;
        }
        else if (p.getX()>w_frame/2 && p.getX()<w_map-w_frame/2 && p.getY()<=h_frame/2){ //zona 2
            this.offset_x=p.getX()-w_frame/2;
            this.offset_y=0;
        }
        else if (p.getX()>=w_map-w_frame/2 && p.getY()<=h_frame/2){ //zona3
            this.offset_x=w_map-w_frame;
            this.offset_y=0;
        }
        else if (p.getX()<=w_frame/2 && p.getY()>h_frame/2 && p.getY()<h_map-h_frame/2){ //zona4
            this.offset_x=0;
            this.offset_y=p.getY()-h_frame/2;
        }
        else if (p.getX()>=w_map-w_frame/2 && p.getY()>h_frame/2 && p.getY()<h_map-h_frame/2){ //zona6
            this.offset_x=w_map-w_frame;
            this.offset_y=p.getY()-h_frame/2;
        }
        else if (p.getX()<=w_frame/2 && p.getY()>=h_map-h_frame/2){ //zona7
            this.offset_x=0;
            this.offset_y=h_map-h_frame;
        }
        else if (p.getX()>w_frame/2 && p.getX()<w_map-w_frame/2 && p.getY()>=h_map-h_frame/2){ //zona 8
            
            this.offset_x=p.getX()-w_frame/2;
            this.offset_y=h_map-h_frame;
        }
        else if (p.getX()>=w_map-w_frame/2 && p.getY()>=h_map-h_frame/2){ //zona 9
         
            this.offset_x=w_map-w_frame;
            this.offset_y=h_map-h_frame;
        }
        else{ //zona5
            this.offset_x=p.getX() - w_frame/2;
            this.offset_y=p.getY() - h_frame/2;
        }
        
        
    }
    
}
