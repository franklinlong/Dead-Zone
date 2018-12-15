/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Dimension;
import java.awt.Toolkit;
import listeners.KAdapter;
import sprite.animated.PlayerFemale;
import sprite.animated.PlayerMale;
import utilities.Assets;

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
    	Assets.init();
        
        Waves w = new Waves();
        if (male){
            PlayerMale p = new PlayerMale(2000,60,2,300,playerName);
            super.handler = new Handler(p,w);
            p.setHandler(super.handler);
        }else{
            PlayerFemale p = new PlayerFemale(2000,60,2,300,playerName);
            super.handler = new Handler(p,w);
            p.setHandler(super.handler);
        }
        
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
