/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.menu;

import deadzone.Window;
import static deadzone.menu.MapFrame.gameClip;
import static deadzone.menu.MapFrame.gameMusic;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.Assets;
import utilities.Sound;
import utilities.Utilities;

/**
 *
 * @author USER
 */
public class LoadingThread extends Thread{
    
    private SinglePlayer sp;
    private LoadingScreen ls;
    
    public LoadingThread(SinglePlayer sp, LoadingScreen ls){
        this.sp = sp;
        this.ls = ls;
    }
    
    @Override
    public void run(){

        try {
            Window w = new Window(sp.getPlayerName(), sp.isMale());
            gameClip = Utilities.LoadSound("/sound/gameMusic.wav");
            gameMusic = new Sound(gameClip);
            
            w.prepareImage(Assets.greenIndicator, null);
            w.prepareImage(Assets.redIndicator, null);
            w.prepareImage(Assets.minimap, null);
                
            for(int i=0; i<100 ;i++){
                ls.jLabel2.setText( i+ "%");
                Thread.sleep(43);
            }
                 
            ls.setVisible(false);
            ls.dispose();
            w.setVisible(true);
            Menu.gameMusic.stopSound();
            gameMusic.loopSound();
        } catch (InterruptedException ex) {
            Logger.getLogger(LoadingScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
