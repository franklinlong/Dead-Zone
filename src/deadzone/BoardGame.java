/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Dimension;
import java.awt.Toolkit;
import deadzone.listeners.KAdapter;
import deadzone.sprite.animated.PlayerDemo;
import deadzone.sprite.animated.PlayerFemale;
import deadzone.sprite.animated.PlayerMale;
import deadzone.utilities.Assets;
import deadzone.utilities.Database;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        if (male) {
            player = new PlayerMale(1600, 1600, 3, 1600, playerName);
        } else {
            player = new PlayerFemale(1600, 1600, 3, 1600, playerName);

        }

        super.handler = new Handler(player, w);
        player.setHandler(super.handler);

        if (Database.online) {
            player.inserisciOnline();
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
