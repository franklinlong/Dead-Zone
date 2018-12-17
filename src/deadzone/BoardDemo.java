/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Dimension;
import java.awt.Toolkit;
import deadzone.sprite.animated.PlayerDemo;
import deadzone.utilities.Assets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author casang
 */
public class BoardDemo extends Board{
    
    public BoardDemo(String playerName,boolean male){
        super(playerName,male);
    }
    
    @Override
    protected void initBoard(String playerName, boolean male){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    	Assets.init();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BoardDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        Waves w = new WavesDemo();
        player = new PlayerDemo(2000,450,2,300,playerName,w);
        super.handler = new Handler(player, w);
        player.setHandler(super.handler);
        w.setHandler(super.handler);
        
        mapPanel = new MapPanel(handler);
        hudPanel = new HudPanel(handler);
        this.setRightComponent(mapPanel);
        this.setLeftComponent(hudPanel);
        
        initGame();
    }
}
