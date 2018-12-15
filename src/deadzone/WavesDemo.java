/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author casang
 */
public class WavesDemo extends Waves{
    
    public WavesDemo(){
        super();
    }
    
    @Override
    public void run() {
        this.numZombieRound += 1;
        this.allKilled = false;
        this.createWeakZommbie(2200, 180, 1, (float) 1);
        this.createFastZombie(2716, 238, 1, (float) 1);
        this.numZombieRound += 1;
        this.createWeakZommbie(600, 3118, 1, (float) 1);
        this.numZombieRound += 1;
        this.createFastZombie(624, 2080, 1, (float) 1);
        this.numZombieRound += 1;
        synchronized(this.handler.getZombies()){
            this.handler.getZombies().notify();
        }
        
        synchronized (KL) {
                while (!this.allKilled) {
                    try {
                        KL.wait(); //si aspetta che venga modificato allKilled
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
        
        this.createFastZombie(1900, 466, 1, (float) 1);
        
        
    }
}
