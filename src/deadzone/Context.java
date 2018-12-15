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
public class Context {
    private Modality modality;
    
    public Context(Modality m){
        this.modality = m;
    }
    
    public void init(String playerName, boolean male){
        this.modality.initGame(playerName, male);
    }
}
