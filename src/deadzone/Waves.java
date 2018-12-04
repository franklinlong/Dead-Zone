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
    private int zombieSpowned;
    private Handler handler;

    public Waves(Handler handler) {
        this.numZombieRound = 0;
        this.handler = handler;
        this.waveCount = 0;
        this.mult = 1;
        this.numZombieKilledRound = 0;
        this.zombieSpowned = 0;
    }

    @Override
    public void run() {
        int x = 0;
        int y = 0;
        while (!handler.getPlayer().isDeath()) {
            this.waveCount += 1;
            this.allKilled = false;
            this.numZombieRound += 8;
            if (!(this.numZombieRound <= 40)) {
                this.numZombieRound = 48;
            }
            if (this.waveCount % 5 == 0) {
                this.numZombieRound -= 8;
            }
            int i = 0;
            while (!handler.getPlayer().isDeath() && i < this.numZombieRound) {
                boolean p = PauseMenu.pause;
                if (!p) {
                    int n = (int) (Math.random() * 5);
                    switch (n) {
                        case 0:                 //fosso
                            x = 2050;
                            y = 2570;
                            break;
                        case 1:                 //tomba 11
                            x = 2224;
                            y = 242;
                            break;
                        case 2:                 //tomba 21
                            x = 2700;
                            y = 242;
                            break;
                        case 3:                 //tomba 12
                            x = 2420;
                            y = 470;
                            break;
                        case 4:                 //tomba 22
                            x = 2800;
                            y = 470;
                            break;
                    }
                }

                handler.addSprite(new Zombie(x, y, 1, (int) (100 * mult), handler.getPlayer(), this.handler, (float) 1));
                i++;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            synchronized (KL) {
                while (!this.allKilled) {
                    try {
                        KL.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (this.waveCount % 5 == 0) {
                this.numZombieRound = 8;
                this.mult += 0.13;
            }
        }
    }

    public int getWaveCount() {
        return waveCount;
    }

    public int getNumZombieRemaining() {
        int i = numZombieRound - numZombieKilledRound;
        if (i == 0) {
            this.numZombieKilledRound = 0;
            synchronized (KL) {
                this.allKilled = true;
                KL.notifyAll();
            }
        }
        return i;
    }

    public void updateNumZombieKilledRound() {
        this.numZombieKilledRound += 1;
        System.out.println(this.numZombieKilledRound);
    }

}
