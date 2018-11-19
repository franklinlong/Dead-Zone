/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author giova
 */
public class Board extends JPanel implements Runnable{
    private boolean inGame=false;
    private Thread t;
    private long averageFPS = 0;
    private Handler handler;
    private KAdapter kAdapt;
    private MAdapter mAdapt; 
    private Player player;
    private Image mapImage;
    private final int w_frame = Camera.w_frame;
    private final int h_frame = Camera.h_frame;
    private int w_map;
    private int h_map;
    private Camera camera;
    
    public Board(){
        initBoard();
    }
    
    private void initBoard(){
    	Assets.init();
    	loadMap();
        handler = new Handler();
        kAdapt = new KAdapter();
        mAdapt = new MAdapter();
        
        this.addKeyListener(kAdapt);
        this.addMouseListener(mAdapt);
        this.addMouseMotionListener(mAdapt);
        this.setFocusable(true);
        player = new Player(60,60,2,2,100);
        camera = new Camera(player);
        handler.addSprite(player);
        handler.addSprite((new Zombie(60, 500, 1, 1, 100, player, handler)));
        initGame();
    }

    private void loadMap(){
        System.out.println("aaaaaa");
    	ImageIcon map = new ImageIcon("map.png");
        System.out.println("bbbb");
        mapImage = map.getImage();
        w_map = mapImage.getWidth(this);
        h_map =  mapImage.getHeight(this);
        System.out.println(w_map + " " + h_map);
    }
    
    private synchronized void initGame(){
        if(inGame)
            return;
        
        inGame=true;
        t = new Thread(this);
        t.start();
    }
    
    private synchronized void stopGame(){
        if(!inGame)
            return;
        try {
            inGame = false;
            t.join();
	} catch (InterruptedException e) {
            e.printStackTrace();
	}
    }
    
    
    @Override
    public void run() {
        int fps = 60;
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long ticks = 0;
        long timer = 0;
        
        while(inGame){
            now = System.nanoTime();
            delta += (now - lastTime)/timePerTick;
            timer += now - lastTime;
            lastTime = now;
            while(delta >= 1){
                    animationCycle();
                    repaint();
                    delta --;
                    ticks++;
            }

            if(timer >= 1000000000){
                    averageFPS = ticks;
                    ticks = 0;
                    timer = 0;
            }
        }
        stopGame();
    
    }
    
    public void animationCycle(){
        kAdapt.update();
        handler.animationCycle();
    }
   
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        handler.drawImage(g,camera.getOffset_x(),camera.getOffset_y());        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void drawMap(Graphics g){
        g.drawImage(mapImage, -camera.getOffset_x(), -camera.getOffset_y(), this);
    }
    
//    public void drawMap(Graphics g){
//        //System.out.println("x="+player.getX()+"  y="+player.getY());
//    	g.drawImage(mapImage, 0, 0, this);
//        if (player.getX()<=w_frame/2 && player.getY()<=h_frame/2){ //zona1
//            g.drawImage(mapImage, 0, 0, this);
//        }
//        else if (player.getX()>w_frame/2 && player.getX()<w_map-w_frame/2 && player.getY()<=h_frame/2){ //zona 2
//            g.drawImage(mapImage,-(player.getX()-w_frame/2), 0, this);
//        }
//        else if (player.getX()>=w_map-w_frame/2 && player.getY()<=h_frame/2){ //zona3
//            g.drawImage(mapImage,-(w_map-w_frame), 0, this); 
//        }
//        else if (player.getX()<=w_frame/2 && player.getY()>h_frame/2 && player.getY()<h_map-h_frame/2){ //zona4
//            g.drawImage(mapImage,0,-(player.getY()-h_frame/2) , this);
//        }
//        else if (player.getX()>=w_map-w_frame/2 && player.getY()>h_frame/2 && player.getY()<h_map-h_frame/2){ //zona6
//            g.drawImage(mapImage,-(w_map-w_frame),-(player.getY()-h_frame/2) , this);
//        }
//        else if (player.getX()<=w_frame/2 && player.getY()>=h_map-h_frame/2){ //zona7
//            g.drawImage(mapImage,0,-(h_map-h_frame) , this);
//        }
//        else if (player.getX()>w_frame/2 && player.getX()<w_map-w_frame/2 && player.getY()>=h_map-h_frame/2){ //zona 8
//            g.drawImage(mapImage,-(player.getX()-w_frame/2), -(h_map-h_frame), this);
//        }
//        else if (player.getX()>=w_map-w_frame/2 && player.getY()>=h_map-h_frame/2){ //zona 9
//            g.drawImage(mapImage,-(w_map-w_frame), -(h_map-h_frame), this);
//        }
//        else{ //zona5
//            g.drawImage(mapImage,-(player.getX()-w_frame/2) ,-(player.getY()-h_frame/2), this);
//        }
//    }
   
}
