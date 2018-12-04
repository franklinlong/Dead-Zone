/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import gameMenu.PauseMenu;
import java.util.logging.Level;
import java.util.logging.Logger;
import sprite.animated.Zombie;

/**
 *
 * @author franc
 */
public class Waves implements Runnable {
    
    private int waveCount;
    private int numZombieRound;
    private int numZombieKilledRound;
    private boolean allKilled;
    private static final Object KL = new Object();
    private float mult;
    private Handler handler;
    
    public Waves(Handler handler){
        this.numZombieRound = 0;
        this.handler = handler;
        this.waveCount = 0;
        this.mult = 1;
        this.numZombieKilledRound = 0;
    }
    
    

    @Override
    public void run() {
        int x = 0;
        int y = 0;
        while(!handler.getPlayer().isDeath() && !PauseMenu.pause){
            this.waveCount += 1;
            this.allKilled = false;
            //this.numZombieRound = 40*this.getWaveCount()*1/2;
            this.numZombieRound += 8;
            if(!(this.numZombieRound <= 40))
                this.numZombieRound=48;
            if(this.waveCount % 5 == 0)
               this.numZombieRound-=8;
            int i = 0;
            while(!handler.getPlayer().isDeath() && !PauseMenu.pause && i<this.numZombieRound){                
                    int n = (int) (Math.random()*1);
                    switch(n){
//                        case 0:                 //fosso
//                            x = 2050;
//                            y = 2570;
//                            break;
//                        case 1:                 //tomba 11
//                            x = 2224;
//                            y = 242;
//                            break;
//                        case 2:                 //tomba 21
//                            x = 2700;
//                            y = 242;
//                            break;
//                        case 3:                 //tomba 12
//                            x = 2420;
//                            y = 470;
//                            break;
                        case 0:                 //tomba 22
                            x = 2800;
                            y = 470;
                            break;
                    }
                    handler.addSprite(new Zombie(x , y, 1, (int) (100*mult), handler.getPlayer(), this.handler, (float)1));
                    i++;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                }
              }
            synchronized (KL){
                while(!this.allKilled){
                    try {
                        KL.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if(this.waveCount % 5 ==0){
                this.numZombieRound=8;
                this.mult += 0.1; 
            }
        }
    }
        

    public int getWaveCount() {
        return waveCount;
    }

    public int getNumZombieRemaining() {
        int i = numZombieRound - numZombieKilledRound;
        if (i == 0){
            synchronized (KL){
                this.allKilled = true;
                KL.notifyAll();
            }
        }
        return i;
    }
    
    public void updateNumZombieKilledRound(){          
        this.numZombieKilledRound += 1;
        if(this.numZombieKilledRound == this.numZombieRound){
            this.numZombieKilledRound = 0;
        }
        System.out.println(this.numZombieKilledRound);
    }
    
    

    
}
