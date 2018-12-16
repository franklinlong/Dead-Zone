/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Dimension;
import java.awt.Toolkit;
import deadzone.listeners.KAdapter;
import deadzone.sprite.animated.PlayerFemale;
import deadzone.sprite.animated.PlayerMale;
import deadzone.utilities.Assets;

/**
 *
 * @author casang
 */
public class BoardGame extends Board{
    
    public BoardGame(String playerName, boolean male){
        super(playerName,male);
    }   
    
    @Override
    protected void initBoard(String playerName, boolean male){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        Waves w = new Waves();
        if (male){
            player = new PlayerMale(2000,60,2,300,playerName);
        }else{
            player = new PlayerFemale(2000,60,2,300,playerName);
            
        }
        
        super.handler = new Handler(player,w);
        player.setHandler(super.handler);
        
        w.setHandler(super.handler);
        Thread t = new Thread(w);
        t.start();
        mapPanel = new MapPanel(handler);
        hudPanel = new HudPanel(handler);
        KAdapter kad = new KAdapter();
        this.setRightComponent(mapPanel);
        this.setLeftComponent(hudPanel);
        
        initGame();
        
    }
}
