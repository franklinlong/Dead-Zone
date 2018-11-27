/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSplitPane;
import listeners.KAdapter;
import sprite.animated.Player;
import utilities.Assets;

/**
 *
 * @author giova
 */
public class Board extends JSplitPane implements Runnable{
    private boolean inGame=false;
    private Thread tHud;
    private Thread tMap;
    private long averageFPS = 0;
    private Handler handler; 
    private Player player;
    private final int w_frame = Camera.w_frame;
    private final int h_frame = Camera.h_frame;
    private int w_map;
    private int h_map;
    private Camera camera;
    private MapPanel mapPanel;
    private HudPanel hudPanel;
    private final int location = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()*1/5;
    
    public Board(String playerName){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim);
        this.setOpaque(false);
        this.setFocusable(true);
        this.requestFocus(true);
        this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        this.setDividerSize(0);
        this.setDividerLocation((int) dim.getWidth()*1/5);
        
        
        initBoard(playerName);
    }
    
    private void initBoard(String playerName){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    	Assets.init();
        
        handler = new Handler(playerName);
        mapPanel = new MapPanel(handler);
        hudPanel = new HudPanel(handler);
        KAdapter kad = new KAdapter();
        this.setRightComponent(mapPanel);
        this.setLeftComponent(hudPanel);
        
        initGame();
    }

    
    
    private synchronized void initGame(){
        if(inGame)
            return;
        
        inGame=true;
        tMap = new Thread(mapPanel);
        tHud = new Thread(hudPanel);
        tMap.start();
        tHud.start();
    }
    
    private synchronized void stopGame(){
        if(!inGame)
            return;
        try {
            inGame = false;
            tHud.join();
            tMap.join();
	} catch (InterruptedException e) {
            e.printStackTrace();
	}
    }
    
    
    @Override
    public void run() {
        try {
            tHud.join();
            System.out.println("LA HUD E FINITA");
            tMap.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("FINE");
    
    }
    
    
    @Override
    public int getDividerLocation(){
        return location;
    }
    
    @Override
    public int getLastDividerLocation(){
        return location;
    }
    
// 
//    public void animationCycle() {
//        kAdapt.update();
//        handler.animationCycle();
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        //super.paintComponent(g);
//        Graphics gMap = mapPanel.getGraphics();
//        drawMap(gMap);
//        handler.drawImage(gMap, camera.getOffset_x(), camera.getOffset_y());
//        Toolkit.getDefaultToolkit().sync();
//        gMap.dispose();
//    }
//
//    public void drawMap(Graphics g) {
//        g.drawImage(mapImage, -camera.getOffset_x(), -camera.getOffset_y(), this);
//
//    }
    
   
}
