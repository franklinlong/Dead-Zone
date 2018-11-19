/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

/**
 *
 * @author Enrico
 */
public class Camera {
    private Player p;
    private int offset_x;
    private int offset_y;
    private int w_frame = 1000;
    private int h_frame = 700;
    private int w_map = 3200;
    private int h_map = 3200;

    public Camera(Player p) {
        this.p = p;
    }

    public int getOffset_x() {
        calcola_offset();
        return offset_x;
    }

    public int getOffset_y() {
        calcola_offset();
        return offset_y;
    }
    public void calcola_offset(){  //calcola il primo pixel da stampare (in alto a sx del frame)
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
            this.offset_x=p.getX()-w_frame/2;
            this.offset_y=p.getY()-h_frame/2;
        }
        
        
    }
    
    //probabilmente quello dopo è da cancellare
    public int getXOffset(){
        
        int xx;
        if (p.getX()<=w_frame/2 && p.getY()<=h_frame/2){ //zona1
                xx = p.getX();
            }
            else if (p.getX()>w_frame/2 && p.getX()<w_map-w_frame/2 && p.getY()<=h_frame/2){ //zona 2
                xx = w_frame/2;
            }
            else if (p.getX()>=w_map-w_frame/2 && p.getY()<=h_frame/2){ //zona3
                xx =p.getX()-(w_map-w_frame);
            }
            else if (p.getX()<=w_frame/2 && p.getY()>h_frame/2 && p.getY()<h_map-h_frame/2){ //zona4
                xx = p.getX();
            }
            else if (p.getX()>=w_map-w_frame/2 && p.getY()>h_frame/2 && p.getY()<h_map-h_frame/2){ //zona6
                xx = p.getX() - (w_map - w_frame);
            }
            else if (p.getX()<=w_frame/2 && p.getY()>=h_map-h_frame/2){ //zona7
                xx = p.getX();
            }
            else if (p.getX()>w_frame/2 && p.getX()<w_map-w_frame/2 && p.getY()>=h_map-h_frame/2){ //zona 8
                xx = w_frame/2;
            }
            else if (p.getX()>=w_map-w_frame/2 && p.getY()>=h_map-h_frame/2){ //zona 9
                xx = p.getX()-(w_map-w_frame);
            }
            else{ //zona5
                xx = w_frame/2;
            }
        return xx;
    }
    
    public int getYOffset(){
        
        int yy;
        if (p.getX()<=w_frame/2 && p.getY()<=h_frame/2){ //zona1
                
                yy = p.getY();
            }
            else if (p.getX()>w_frame/2 && p.getX()<w_map-w_frame/2 && p.getY()<=h_frame/2){ //zona 2
                
                yy=p.getY();
            }
            else if (p.getX()>=w_map-w_frame/2 && p.getY()<=h_frame/2){ //zona3
                
                yy = p.getY();
            }
            else if (p.getX()<=w_frame/2 && p.getY()>h_frame/2 && p.getY()<h_map-h_frame/2){ //zona4
                
                yy = h_frame/2;
            }
            else if (p.getX()>=w_map-w_frame/2 && p.getY()>h_frame/2 && p.getY()<h_map-h_frame/2){ //zona6
                
                yy = h_frame/2;
            }
            else if (p.getX()<=w_frame/2 && p.getY()>=h_map-h_frame/2){ //zona7
                
                yy = p.getY() - (h_map - h_frame);
            }
            else if (p.getX()>w_frame/2 && p.getX()<w_map-w_frame/2 && p.getY()>=h_map-h_frame/2){ //zona 8
                
                yy = p.getY() - (h_map - h_frame);
            }
            else if (p.getX()>=w_map-w_frame/2 && p.getY()>=h_map-h_frame/2){ //zona 9
                
                yy = p.getY()-(h_map-h_frame);
            }
            else{ //zona5
                
                yy = h_frame/2;
            }
        return yy;
    }
}
