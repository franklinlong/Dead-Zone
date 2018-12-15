/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Dimension;
import java.awt.Toolkit;
import sprite.animated.PlayerDemo;
import utilities.Assets;

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
        
        Waves w = new WavesDemo();
        PlayerDemo p = new PlayerDemo(2000,450,2,300,playerName);
        super.handler = new Handler(p, w);
        p.setHandler(super.handler);
        w.setHandler(super.handler);
        p.setWave(w);
        
        mapPanel = new MapPanel(handler);
        hudPanel = new HudPanel(handler);
        this.setRightComponent(mapPanel);
        this.setLeftComponent(hudPanel);
        
        initGame();
    }
}
