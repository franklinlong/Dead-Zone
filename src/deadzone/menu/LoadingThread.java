/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.menu;

import deadzone.Context;
import deadzone.ModalityDemo;
import deadzone.ModalityGame;
import deadzone.Window;
import static deadzone.menu.MapFrame.gameClip;
import static deadzone.menu.MapFrame.gameMusic;
import java.util.logging.Level;
import java.util.logging.Logger;
import deadzone.utilities.Sound;
import deadzone.utilities.Utilities;

/**
 *
 * @author USER
 */
public class LoadingThread extends Thread {

    private SinglePlayer sp;
    private LoadingScreen ls;
    private Window w;
    
    public LoadingThread(SinglePlayer sp, LoadingScreen ls, Window w) {
        this.sp = sp;
        this.ls = ls;
        this.w = w;
    }

    @Override
    public void run() {

        try {
            gameClip = Utilities.LoadSound("/sound/ingame.wav");
            gameMusic = new Sound(gameClip);
            
            ls.setVisible(true);
            ls.jLabel2.setText("0%");
            
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
