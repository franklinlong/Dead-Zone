/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import gameMenu.PauseMenu;
import java.util.logging.Level;
import java.util.logging.Logger;
import sprite.animated.StandardZombie;
import utilities.Animation;
import utilities.Assets;
import utilities.Sound;

/**
 *
 * @author franc
 */
public class Waves implements Runnable {

    private int waveCount;
    private int numZombieRound;
    private int numZombieKilledRound;
    private boolean allKilled;
    private static final Object KL = new Object(); //lock per l'allKilled
    private float mult;
    private Handler handler;

    public Waves(Handler handler) {
        this.numZombieRound = 0;
        this.handler = handler;
        this.waveCount = 0;
        this.mult = 1; //moltiplicatore per la salute dello zombie. Viene incrementato di 0.13 ogni 5 ondate
        this.numZombieKilledRound = 0; //zombie uccisi per round
    }

    @Override
    public void run() {
        float x = 0;
        float y = 0;
        while (!handler.getPlayer().isDeath()) {
            this.waveCount += 1; //incremento di 1 il numero di ondata
            this.allKilled = false; 
            this.numZombieRound += 8; //aumentano di 8 ogni ondata
            if (!(this.numZombieRound <= 40)) {
                this.numZombieRound = 48;
            }
            if (this.waveCount % 5 == 0) {
                this.numZombieRound -= 8;
            }
            int i = 0;
            while (!handler.getPlayer().isDeath() && i < this.numZombieRound) {
                boolean p = PauseMenu.pause; //non cancellare, senza non funziona... da vedere
                if (!p) {
                    int n = (int) (Math.random() * 10);
                    switch (n) {
                        case 0:                 //fossa
                            x = 2072;
                            y = 2514;
                            break;
                        case 1:                 //tomba 11
                            x = 2224;
                            y = 238;
                            break;
                        case 2:                 //tomba 21
                            x = 2716;
                            y = 238;
                            break;
                        case 3:                 //tomba 12
                            x = 2418;
                            y = 466;
                            break;
                        case 4:                 //tomba 22
                            x = 2804;
                            y = 466;
                            break;
                        case 5:
                            x = 608;            //teatro
                            y = 3118;
                            break;
                        case 6:                 //fognatura 2
                            x = 624;
                            y = 2080;
                            break;
                        case 7:                 //parco
                            x = 0;
                            y = 1640;
                            break;
                        case 8:                 //incrocio sopra parco
                            x = 132;
                            y = 1282;
                            break;
                        case 9:                 //fognatura 1
                            x = 674;
                            y = 300;
                            break;
                    }
                }
                this.createStandardZommbie(x, y, mult, (float) 1);
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
                        KL.wait(); //si aspetta che venga modificato allKilled
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
    }

    public void createStandardZommbie(float x, float y, float mulHealth, float prob) {
        this.handler.addSprite(new StandardZombie(x, y, 1, (int) (100 * mulHealth), handler.getPlayer(), this.handler, prob, 60, 60, 5, new Animation(Assets.zombie, 20), new Animation(Assets.zombieAttack, 35), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
    }

}
