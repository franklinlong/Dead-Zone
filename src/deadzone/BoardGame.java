/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import deadzone.sprite.animated.PlayerFactory;
import deadzone.utilities.Database;

/**
 *
 * @author casang
 */
public class BoardGame extends Board {

    public BoardGame(String playerName, boolean male) {
        super(playerName, male);
    }

    @Override
    protected void initBoard(String playerName, boolean male) {

        Waves w = new Waves();
        player = PlayerFactory.getPlayer(male,false);
        player.setName(playerName);
        this.handler = new Handler(player,w);
        player.setHandler(this.handler);
        
        if (Database.online) {
            player.inserisciOnline();
        }
        
        w.setHandler(this.handler);
        Thread t = new Thread(w);
        t.start();
        mapPanel = new MapPanel(handler);
        hudPanel = new HudPanel(handler);
        this.setRightComponent(mapPanel);
        this.setLeftComponent(hudPanel);

        initGame();
        
        

    }
}
