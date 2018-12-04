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
public class Board extends JSplitPane{
    private boolean inGame=false;
    public static Thread tHud;
    public static Thread tMap;
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
    
    @Override
    public int getDividerLocation(){
        return location;
    }
    
    @Override
    public int getLastDividerLocation(){
        return location;
    }
   
}
