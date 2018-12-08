/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import gameMenu.Menu;
import gameMenu.PauseMenu;
import gameMenu.Settings;
import java.util.logging.Level;
import java.util.logging.Logger;
import sprite.animated.SpittleZombie;
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
    private int numWeakRound;
    private int numFastRound;
    private int numWeakToCreate;
    private int numFastToCreate;
    private int diffToCreate;
    private int numZombieKilledRound;
    private boolean allKilled;
    private static final Object KL = new Object(); //lock per l'allKilled
    private float mult;
    private Sound endRound;
    private Handler handler;
    //private static final Object PL = new Object();

    public Waves(Handler handler) {
        this.numZombieRound = 0;
        this.numWeakRound = 0;
        this.numFastRound = 0;
        this.handler = handler;
        this.waveCount = 0;
        this.diffToCreate = 0;
        this.mult = 1; //moltiplicatore per la salute dello zombie. Viene incrementato di 0.13 ogni 5 ondate
        this.numZombieKilledRound = 0; //zombie uccisi per round
        this.endRound = new Sound(Assets.endOfRound);
    }

    @Override
    public void run() {
        float x = 0;
        float y = 0;
        while (!handler.getPlayer().isDeath()) {
            this.waveCount += 1; //incrementa di 1 il contatore delle ondate ogni volta
            this.allKilled = false; //boolean che indica se sono tutti uccisi
            this.numWeakRound += 8; //aumentano di 8 ogni round
            this.numFastRound += 5; //aumentano di 5 ogni round
            if (!(this.numWeakRound <= 40)) {
                this.numWeakRound = 48; //cosicché non siano più di 48
            }
            if (this.waveCount % 5 == 0) {
                this.numWeakRound -= 8; //numero di scarsi decrementato ogni 5 round
            }
            if (!(this.numFastRound <= 15)) {
                this.numFastRound = 18; //cosicché non siano più di 18
            }
            if (this.waveCount % 5 == 2 || this.waveCount % 5 == 1) {
                this.numFastRound = 0; //spawnano dall'ondata 3 ogni 3,4,5 ondate
            }

            this.numZombieRound = this.numFastRound + this.numWeakRound;
            this.numFastToCreate = this.numFastRound;
            this.numWeakToCreate = this.numWeakRound;
            
            //inizio spawn
            //WARNING: CI SONO DELLE STAMPE COMMENTATE! NON CANCELLARE, SERVONO IN FASE DI TEST
            int i = 0;
            while (!handler.getPlayer().isDeath() && i < this.numZombieRound) {
                //System.out.println("Sto nel while: " + i);
                //System.out.println("Da creare: " + this.numZombieRound);
                //System.out.println("Scarsi da creare: " + this.numWeakToCreate);
                //System.out.println("Veloci da creare: " + this.numFastToCreate);
                if (PauseMenu.isPause()) {
                    System.out.println("PAUSA WAVES");
                    synchronized (PauseMenu.PAUSELOCK) { //acquisisco il lock di Pausa
                        try {
                            PauseMenu.PAUSELOCK.wait(); //attendo che pause cambi
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {

                    //System.out.println("Sto nel else");
                    int n = (int) (Math.random() * 10); //randomicamente, cerco tutti i punti di spawn
                    switch (n) {
                        case 0:             //fossa
                            x = 2072;
                            y = 2514;
                            break;
                        case 1:             //tomba 11
                            x = 2224;
                            y = 238;
                            break;
                        case 2:             //tomba 12
                            x = 2716;
                            y = 238;
                            break;
                        case 3:             //tomba 21
                            x = 2418;
                            y = 466;
                            break;
                        case 4:             //tomba 22
                            x = 2804;
                            y = 466;
                            break;
                        case 5:             //teatro
                            x = 608;
                            y = 3118;
                            break;
                        case 6:             //parco
                            x = 10;
                            y = 1640;
                            break;
                        case 7:             //incrocio
                            x = 136;
                            y = 1282;
                            break;
                        case 8:             //fognatura 2
                            x = 624;
                            y = 2080;
                            break;
                        case 9:             //fognatura 1 
                            x = 674;
                            y = 300;
                            break;
                    }
                    
                    this.numDiffToCreate(); //potrebbe essere inutile, il numero di differenti so che sono 2 al momento. può servire per dopo

                    if (this.numWeakToCreate == 0 && !(this.numFastToCreate == 0)) {
                        this.createFastZombie(x, y, mult, (float) 25);
                        this.numFastToCreate -= 1; //decremento il numero di scarsi
                        i++;
                    } else if (!(this.numWeakToCreate == 0) && this.numFastToCreate == 0) {
                        this.createWeakZommbie(x, y, mult, (float) 25);
                        this.numWeakToCreate -= 1; //decremento il numero di forti
                        i++;
                    } else {
                        n = (int) (Math.random() * this.diffToCreate); //randomicamente, creo uno o l'altro
                        switch (n) {
                            case 0:
                                this.createWeakZommbie(x, y, mult, (float) 25);
                                this.numWeakToCreate -= 1;
                                i++;
                                break;
                            case 1:
                                this.createFastZombie(x, y, mult, (float) 25);
                                this.numFastToCreate -= 1;
                                i++;
                                break;
                        }
                    }
                }                
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            synchronized (KL) { //acquisisco il lock di allKilled
                //System.out.println("Sto nel synch");
                while (!this.allKilled) {  
                    try {
                        KL.wait(); //aspetto che modifichi il valore di allKilled
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            //System.out.println("Fine ondata");
            if (Settings.soundMusic) {
                Menu.gameMusic.stopSound();
                endRound.playSound();
            }
            try {
                Thread.sleep(endRound.getMicrosecondLenght() / 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (Settings.soundMusic) {
                Menu.gameMusic.loopSound();
            }
            if (this.waveCount % 5 == 0) {
                this.numWeakRound = 8;
                this.mult += 0.13;
            }

        }

    }

    public int getWaveCount() {
        return waveCount;
    }

    public int getNumZombieRemaining() {
        if (this.allKilled == true) {
            return 0;
        }
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

    public void numDiffToCreate() {
        if (this.numWeakToCreate > 0 && this.numFastToCreate > 0) {
            this.diffToCreate = 2;
        } else if ((this.numFastToCreate > 0 && !(this.numWeakToCreate > 0)) || (!(this.numFastToCreate > 0) && this.numWeakToCreate > 0)) {
            this.diffToCreate = 1;
        } else {
            this.diffToCreate = 0;
        }
    }

    public void createWeakZommbie(float x, float y, float mulHealth, float prob) {
        this.handler.addSprite(new StandardZombie(x, y, 1, (int) (100 * mulHealth), 25, handler.getPlayer(), this.handler, prob, 60, 60, 5, new Animation(Assets.zombie, 20), new Animation(Assets.zombieAttack, 35), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
    }

    public void createFastZombie(float x, float y, float mulHealth, float prob) {
        this.handler.addSprite(new StandardZombie(x, y, 1, (int) (35 * mulHealth), 40, handler.getPlayer(), this.handler, prob, 60, 60, 5, new Animation(Assets.zombie2, 15), new Animation(Assets.zombie2Attack, 15), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
    }

    public void createSpittleZombie(float x, float y, float mulHealth, float prob) {
        this.handler.addSprite(new SpittleZombie(x, y, 1, (int) (100 * mulHealth), 50, handler.getPlayer(), this.handler, prob, 60, 60, 5, new Animation(Assets.zombie2, 15), new Animation(Assets.zombie2Attack, 15), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
    }
}
