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
        this.createFastZombie(2250, 180, 1, (float) 1);
        this.createWeakZombie(2516, 238, 1, (float) 1);
        this.numZombieRound += 1;
        this.createSpittleZombie(3100, 2080, 1, (float) 1);
        this.numZombieRound += 1;
        this.createWeakZombie(100, 10, 1, (float) 1);
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
        
        this.createBoss(1900, 670, 1, (float) 1);
        
        
    }
}
