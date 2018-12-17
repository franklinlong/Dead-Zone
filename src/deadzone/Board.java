/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JSplitPane;
import deadzone.sprite.animated.PlayerFactory;

/**
 *
 * @author giova
 */
public abstract class Board extends JSplitPane {

    private boolean inGame = false;
    public static Thread tHud;
    public static Thread tMap;
    private long averageFPS = 0;
    protected Handler handler; 
    protected PlayerFactory player;
    protected MapPanel mapPanel;
    protected HudPanel hudPanel;
    private final int location = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()*1/5;
    
    public Board(String playerName, boolean male){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim);
        this.setOpaque(false);
        this.setFocusable(true);
        this.requestFocus(true);
        this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        this.setDividerSize(0);
        this.setDividerLocation((int) dim.getWidth()*1/5);
        
        initBoard(playerName, male);
    }
    
    protected abstract void initBoard(String playerName, boolean male);

    
    
    protected synchronized void initGame(){
        if(inGame)
            return;

        inGame = true;
        tMap = new Thread(mapPanel);
        tHud = new Thread(hudPanel);
        tMap.start();
        tHud.start();
        
    }

    @Override
    public int getDividerLocation() {
        return location;
    }

    @Override
    public int getLastDividerLocation() {
        return location;
    }
    
    public void setWindow(Window window){
        player.setWindow(window);
    }

}
