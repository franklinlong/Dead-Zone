/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

/**
 *
 * @author casang
 */
public class ModalityGame implements Modality{
    
    @Override
    public void initGame(String playerName, boolean male){
        new Window(new BoardGame(playerName,male)).setVisible(true);
    }
}
