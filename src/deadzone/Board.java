/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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
    private Image mapImage;
    
    public Board(){
        initBoard();
    }
    
    private void initBoard(){
        //Caricamento animazioni e suoni 
        Assets.init();
        
        //Caricamento mappa
    	loadMap();
        
        //Gestore di tutti gli sprite
        handler = new Handler();
        
        kAdapt = new KAdapter();
        mAdapt = new MAdapter();
        this.addKeyListener(kAdapt);
        this.addMouseListener(mAdapt);
        this.addMouseMotionListener(mAdapt);
        this.setFocusable(true);
        
        //Immagine cursore
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ImageIcon image = new ImageIcon(getClass().getResource("mirino_trasparente.png"));
        Image immagine = image.getImage();
        Cursor c = toolkit.createCustomCursor(immagine, new Point(immagine.getWidth(this)/2, immagine.getHeight(this)/2), "Cursore mirino");
        this.setCursor (c);
        
        initGame();
    }

    private void loadMap(){
        System.out.println("Carico la mappa");
    	ImageIcon map = new ImageIcon("map.png");
        mapImage = map.getImage();
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
        handler.drawImage(g);        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void drawMap(Graphics g){
        g.drawImage(mapImage, (int)-handler.getCamera().getOffset_x(), (int)-handler.getCamera().getOffset_y(), this);
    }
    
}
