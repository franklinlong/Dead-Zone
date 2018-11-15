/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author USER
 */
public class Zombie extends AnimatedSprite{

    private Route route;
    private Player player;
    private Handler handler;
    private double x;
    private double y;
    
    private int w_frame = 1000;
    private int h_frame = 700;
    private int w_map = 3200;
    private int h_map = 3200;
    
    public Zombie(int x, int y, int width, int height, int velX, int velY, int health, Player player, Handler handler) {
        super(x, y, width, height, velX, velY, health);
        this.player = player;
        this.handler = handler;
        this.x = x;
        this.y = y;
    }

    public double getDoubleX() {
        return x;
    }

    public double getDoubleY() {
        return y;
    }

    @Override
    public void drawImage(Graphics g) {
        int xx,yy;
//        if (getX()<=w_frame/2 && getY()<=h_frame/2){ //zona1
//                xx = getX();
//                yy = getY();
//            }
//            else if (getX()>w_frame/2 && getX()<w_map-w_frame/2 && getY()<=h_frame/2){ //zona 2
//                xx = w_frame/2;
//                yy=getY();
//            }
//            else if (getX()>=w_map-w_frame/2 && getY()<=h_frame/2){ //zona3
//                xx =getX()-(w_map-w_frame);
//                yy = getY();
//            }
//            else if (getX()<=w_frame/2 && getY()>h_frame/2 && getY()<h_map-h_frame/2){ //zona4
//                xx = getX();
//                yy = h_frame/2;
//            }
//            else if (getX()>=w_map-w_frame/2 && getY()>h_frame/2 && getY()<h_map-h_frame/2){ //zona6
//                xx = getX() - (w_map - w_frame);
//                yy = h_frame/2;
//            }
//            else if (getX()<=w_frame/2 && getY()>=h_map-h_frame/2){ //zona7
//                xx = getX();
//                yy = getY() - (h_map - h_frame);
//            }
//            else if (getX()>w_frame/2 && getX()<w_map-w_frame/2 && getY()>=h_map-h_frame/2){ //zona 8
//                xx = w_frame/2;
//                yy = getY() - (h_map - h_frame);
//            }
//            else if (getX()>=w_map-w_frame/2 && getY()>=h_map-h_frame/2){ //zona 9
//                xx = getX()-(w_map-w_frame);
//                yy = getY()-(h_map-h_frame);
//            }
//            else{ //zona5
//                xx = w_frame/2;
//                yy = h_frame/2;
//            }
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 32,48);
        
    }

    @Override
    public void animationCycle() {
        
        double[] a = new Route(player, this, handler).seek();
        
        System.out.println("velocitaX: "+a[0]);
        System.out.println("velocitaY: "+a[1]);
        this.x += a[0];
        this.y += a[1];
        System.out.println(x);
        System.out.println(y); 
        
    }
    
    
}
