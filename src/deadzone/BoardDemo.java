/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import deadzone.sprite.animated.PlayerDemo;

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
