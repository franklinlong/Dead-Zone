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
    private final Modality modality;
    
    public Context(Modality m){
        this.modality = m;
    }
    
    public Window init(String playerName, boolean male){
        return this.modality.initGame(playerName, male);
    }
}
