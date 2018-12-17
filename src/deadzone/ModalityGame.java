/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import deadzone.utilities.Assets;

/**
 *
 * @author casang
 */
public class ModalityGame implements Modality{
    
    @Override
    public Window initGame(String playerName, boolean male){
        Window w = new Window(new BoardGame(playerName,male));
        w.prepareImage(Assets.greenIndicator, null);
        w.prepareImage(Assets.redIndicator, null);
        w.prepareImage(Assets.minimap, null);
        //w.setVisible(true);
        return w;
    }
}
