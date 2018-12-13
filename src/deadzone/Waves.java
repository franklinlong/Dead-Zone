/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import deadzone.menu.MapFrame;
import deadzone.menu.PauseMenu;
import deadzone.menu.Settings;
import java.util.logging.Level;
import java.util.logging.Logger;
import deadzone.sprite.Coins;
import deadzone.sprite.animated.Boss;
import deadzone.sprite.animated.SpittleZombie;
import deadzone.sprite.animated.StandardZombie;
import deadzone.utilities.Animation;
import deadzone.utilities.Assets;
import deadzone.utilities.Sound;

/**
 *
 * @author franc
 */
public class Waves implements Runnable {

    private int waveCount;
    private int numZombieRound;
    private int numWeakRound;
    private int numFastRound;
    private int numSpittleRound;
    private int numBossRound;
    private int numWeakToCreate;
    private int numFastToCreate;
    private int numSpittleToCreate;
    private int numBossToCreate;
    private int diffToCreate;
    private int numZombieKilledRound;
    private boolean allKilled;
    private static final Object KL = new Object(); //lock per l'allKilled
    private float mult;
    private final Sound endRound;
    private final Handler handler;
    private int score;
    private int numZombieSpawn;
    private int i=3,j=2;
    private int prob=20;
    public Waves(Handler handler) {
        this.numZombieRound = 0;
        this.numWeakRound = 0;
        this.numFastRound = 0;
        this.numBossRound = 0;
        this.numSpittleRound = 0;
        this.handler = handler;
        this.waveCount = 0;
        this.diffToCreate = 0;
        this.mult = 1; //moltiplicatore per la salute dello zombie. Viene incrementato di 0.13 ogni 5 ondate
        this.numZombieKilledRound = 0; //zombie uccisi per round
        this.score = 0; //variabile di punteggio dell'ondata. Inizialmente è 0, verrà incrementata di 5 ogni round
        this.endRound = new Sound(Assets.endOfRound);

    }

    @Override
    public void run() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
        }
        float x = 0;
        float y = 0;
        while (!handler.getPlayer().isDeath()) {
            this.waveCount += 1; //incrementa di 1 il contatore delle ondate ogni volta
            this.spawnCoins();
            this.allKilled = false; //boolean che indica se sono tutti uccisi
            this.numWeakRound += 8; //aumentano di 8 ogni round
            this.numFastRound += i; //aumentano di 3 ogni round
            this.numSpittleRound += j; //aumentano di 2 ogni round
            this.numBossRound = 0; //è settato sempre a 0
            if (this.waveCount % 5 == 0) {
                this.numWeakRound -= 8; //numero di scarsi decrementato alle quinte ondate
                this.numBossRound = this.waveCount / 5; //se è il boss round si setta il numero di boss
            }
            if (!(this.numBossRound <= 3)) {
                this.numBossRound = 4; //cosicché non siano più di 4
            }
//            if (!(this.waveCount % 5 == 0)) {
//                this.numBossRound = 0; //i boss spawnano ogni 5
//            }
            if (this.waveCount % 5 == 2 || this.waveCount % 5 == 1) {
                this.numFastRound = 0; //spawnano alle tre ondate finali in un set di 5 ondate
            }
            if (this.waveCount % 5 == 1) {
                this.numSpittleRound = 0; //spawnano alle quattro ondate finali in un set di 5 ondate
            }
            if (!(this.numWeakRound <= 40)) {
                this.numWeakRound = 48; //cosicché non siano più di 48
            }
            if (!(this.numFastRound <= 9)) {
                this.numFastRound = 12; //cosicché non siano più di 12
            }
            if (!(this.numSpittleRound <= 12)) {
                this.numSpittleRound = 15; //cosicché non siano più di 15
            }
            this.numZombieRound = this.numFastRound + this.numWeakRound + this.numBossRound + this.numSpittleRound;
            this.numFastToCreate = this.numFastRound;
            this.numWeakToCreate = this.numWeakRound;
            this.numBossToCreate = this.numBossRound;
            this.numSpittleToCreate = this.numSpittleRound;
            this.numZombieSpawn = this.numZombieRound;
            
            //inizio spawn
            //WARNING: CI SONO DELLE STAMPE COMMENTATE! NON CANCELLARE, SERVONO IN FASE DI TEST
            int i = 0;
            while (!handler.getPlayer().isDeath() && i < this.numZombieSpawn) {
//                System.out.println("Sto nel while: " + i);
//                System.out.println("Nel round: " + this.numZombieRound);
//                System.out.println("Scarsi da creare: " + this.numWeakToCreate);
//                System.out.println("Veloci da creare: " + this.numFastToCreate);
//                System.out.println("boss da creare: " + this.numBossToCreate);
//                System.out.println("Splitt da creare: " + this.numSpittleToCreate);
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

                    this.numDiffToCreate();
                    //System.out.println(this.diffToCreate);                    
                    switch (this.diffToCreate) {//switch del numero di differenti da creare
                        case 4: //se devo crearli tutti, ne creo randomicamente uno
                            //System.out.println("UNO DEI 4");
                            n = (int) (Math.random() * 4);
                            switch (n) {
                                case 0:
                                    this.createBoss(x, y, mult, (float) 100);
                                    this.numBossToCreate -= 1;
                                    i++;
                                    break;
                                case 1:
                                    this.createFastZombie(x, y, mult, prob);
                                    this.numFastToCreate -= 1;
                                    i++;
                                    break;
                                case 2:
                                    this.createSpittleZombie(x, y, mult, prob);
                                    this.numSpittleToCreate -= 1;
                                    i++;
                                    break;
                                case 3:
                                    this.createWeakZombie(x, y, mult, prob);
                                    this.numWeakToCreate -= 1;
                                    i++;
                                    break;
                            }
                            break;
                        case 3: //se sono 3 da creare...
                            n = (int) (Math.random() * 3);
                            if (this.numBossToCreate == 0) {//...e tra questi 3 non c'è il boss
                                //System.out.println("vel spittle o weak");
                                switch (n) {
                                    case 0:
                                        this.createFastZombie(x, y, mult, prob);
                                        this.numFastToCreate -= 1;
                                        i++;
                                        break;
                                    case 1:
                                        this.createSpittleZombie(x, y, mult, prob);
                                        this.numSpittleToCreate -= 1;
                                        i++;
                                        break;
                                    case 2:
                                        this.createWeakZombie(x, y, mult, prob);
                                        this.numWeakToCreate -= 1;
                                        i++;
                                        break;
                                }

                            } else if (this.numFastToCreate == 0) {//...e tra questi 3 non c'è il veloce
                                //System.out.println("boss spittle o weak");
                                switch (n) {
                                    case 0:
                                        this.createBoss(x, y, mult, (float)100);
                                        this.numBossToCreate -= 1;
                                        i++;
                                        break;
                                    case 1:
                                        this.createSpittleZombie(x, y, mult, prob);
                                        this.numSpittleToCreate -= 1;
                                        i++;
                                        break;
                                    case 2:
                                        this.createWeakZombie(x, y, mult,prob);
                                        this.numWeakToCreate -= 1;
                                        i++;
                                        break;
                                }
                            } else if (this.numSpittleToCreate == 0) {//...e tra questi 3 non c'è spittle
                                //System.out.println("vel boss o weak");
                                switch (n) {
                                    case 0:
                                        this.createBoss(x, y, mult,(float) 100);
                                        this.numBossToCreate -= 1;
                                        i++;
                                        break;
                                    case 1:
                                        this.createFastZombie(x, y, mult, prob);
                                        this.numFastToCreate -= 1;
                                        i++;
                                        break;
                                    case 2:
                                        this.createWeakZombie(x, y, mult, prob);
                                        this.numWeakToCreate -= 1;
                                        i++;
                                        break;
                                }
                            } else if (this.numWeakToCreate == 0) {//...e tra questi 3 non c'è weak
                                //System.out.println("vel spittle o boss");
                                switch (n) {
                                    case 0:
                                        this.createBoss(x, y, mult, (float)100);
                                        this.numBossToCreate -= 1;
                                        i++;
                                        break;
                                    case 1:
                                        this.createFastZombie(x, y, mult, prob);
                                        this.numFastToCreate -= 1;
                                        i++;
                                        break;
                                    case 2:
                                        this.createSpittleZombie(x, y, mult, prob);
                                        this.numSpittleToCreate -= 1;
                                        i++;
                                        break;
                                }
                            }
                            break;
                        case 2://se ne devo creare 2...
                            n = (int) (Math.random() * 2);
                            if (this.numBossToCreate == 0) {//...e tra questi 2 non c'è il boss...
                                if (this.numFastToCreate == 0) {//...e il veloce
                                    //System.out.println("spittle o weak");
                                    switch (n) {
                                        case 0:
                                            this.createSpittleZombie(x, y, mult, prob);
                                            this.numSpittleToCreate -= 1;
                                            i++;
                                            break;
                                        case 1:
                                            this.createWeakZombie(x, y, mult, prob);
                                            this.numWeakToCreate -= 1;
                                            i++;
                                            break;
                                    }
                                } else if (this.numWeakToCreate == 0) {//...e il weak
                                    //System.out.println("spittle o vel");
                                    switch (n) {
                                        case 0:
                                            this.createSpittleZombie(x, y, mult, (float) 25);
                                            this.numSpittleToCreate -= 1;
                                            i++;
                                            break;
                                        case 1:
                                            this.createFastZombie(x, y, mult, (float) 25);
                                            this.numFastToCreate -= 1;
                                            i++;
                                            break;
                                    }
                                } else if (this.numSpittleToCreate == 0) {//...e split
                                    //System.out.println("vel o weak");
                                    switch (n) {
                                        case 0:
                                            this.createWeakZombie(x, y, mult, prob);
                                            this.numWeakToCreate -= 1;
                                            i++;
                                            break;
                                        case 1:
                                            this.createFastZombie(x, y, mult, prob);
                                            this.numFastToCreate -= 1;
                                            i++;
                                            break;
                                    }
                                }
                            } else if (this.numFastToCreate == 0) {//...e tra questi 2 non c'è il veloce...
                                if (this.numWeakToCreate == 0) {//...e il weak
                                    //System.out.println("spittle o boss");
                                    switch (n) {
                                        case 0:
                                            this.createSpittleZombie(x, y, mult, prob);
                                            this.numSpittleToCreate -= 1;
                                            i++;
                                            break;
                                        case 1:
                                            this.createBoss(x, y, mult, (float) 100);
                                            this.numBossToCreate -= 1;
                                            i++;
                                            break;
                                    }
                                } else if (this.numSpittleToCreate == 0) {//...e lo spittle
                                    //System.out.println("boss o weak");
                                    switch (n) {
                                        case 0:
                                            this.createWeakZombie(x, y, mult, prob);
                                            this.numWeakToCreate -= 1;
                                            i++;
                                            break;
                                        case 1:
                                            this.createBoss(x, y, mult, (float) 100);
                                            this.numBossToCreate -= 1;
                                            i++;
                                            break;
                                    }

                                }
                            } else if (this.numWeakToCreate == 0) {//...e tra questi 2 non c'è weak...
                                if (this.numSpittleToCreate == 0) {//...e spittle
                                    //System.out.println("boss o vel");
                                    switch (n) {
                                        case 0:
                                            this.createFastZombie(x, y, mult, prob);
                                            this.numFastToCreate -= 1;
                                            i++;
                                            break;
                                        case 1:
                                            this.createBoss(x, y, mult, (float) 100);
                                            this.numBossToCreate -= 1;
                                            i++;
                                            break;
                                    }
                                }
                            }
                            break;
                        case 1://se ne devo creare 1...
                            if (this.numBossToCreate > 0) {//...ed è il boss
                                //System.out.println("boss");
                                this.createBoss(x, y, mult, (float) 100);
                                this.numBossToCreate -= 1;
                                i++;
                            } else if (this.numFastToCreate > 0) {//...ed è il veloce
                                //System.out.println("vel");
                                this.createFastZombie(x, y, mult, prob);
                                this.numFastToCreate -= 1;
                                i++;
                            } else if (this.numSpittleToCreate > 0) {//...ed è lo spittle
                                //System.out.println("spitt");
                                this.createSpittleZombie(x, y, mult, prob);
                                this.numSpittleToCreate -= 1;
                                i++;
                            } else if (this.numWeakToCreate > 0) {//... ed è il weak
                                //System.out.println("weak");
                                this.createWeakZombie(x, y, mult, prob);
                                this.numWeakToCreate -= 1;
                                i++;
                            }
                            break;
                        default:
                            break;
                    }
                }
                try {
                    //System.out.println("Dormo");
                    Thread.sleep(1000);//ne creo uno ogni secondo
                } catch (InterruptedException ex) {
                    Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.println("Ricomincio");
            }
            //System.out.println("Fine spawn");
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
            this.handler.getPlayer().updatePunteggio(score);
            if (Settings.soundMusic) {
                MapFrame.gameMusic.stopSound();
                System.out.println(MapFrame.gameMusic.getFramePosition());
                endRound.playSound();
            }
            try {
                Thread.sleep(endRound.getMicrosecondLenght() / 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (Settings.soundMusic) {
                MapFrame.gameMusic.setFramePosition(1430000);
                MapFrame.gameMusic.loopSound();
            }
            if (this.waveCount % 5 == 0) {
                this.numWeakRound = 8 * this.numWeakRound/5;
                this.numFastRound = 0;
                this.numSpittleRound = 0;
                this.mult += 0.13;
                if(i==3) i*=2;
                if(j==2) j*=4;
            }
            this.score += 500;  //score bonus che ottieni a fine round;
        }

    }
    
    public void spawnCoins(){
        switch (this.waveCount % 9) {
            case 1:
                this.handler.addSprite(new Coins(2125, 810, 20, 20, handler));
                break;
            case 2:
                this.handler.addSprite(new Coins(2477, 809, 20, 20, handler));
                break;
            case 3:
                this.handler.addSprite(new Coins(2660, 810, 20, 20, handler));
                break;
            case 4:
                this.handler.addSprite(new Coins(2930, 805, 20, 20, handler));
                break;
            case 5:
                this.handler.addSprite(new Coins(2722, 1035, 20, 20, handler));
                break;
            case 6:
                this.handler.addSprite(new Coins(2340, 1130, 20, 20, handler));
                break;
            case 7:
                this.handler.addSprite(new Coins(2250, 1130, 20, 20, handler));
                break;
            case 8:
                this.handler.addSprite(new Coins(2930, 1130, 20, 20, handler));
                break;
            default:
                this.handler.addSprite(new Coins(2570, 975, 20, 20, handler));
                break;
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
        if (this.numWeakToCreate > 0 && this.numFastToCreate > 0 && this.numBossToCreate > 0 && this.numSpittleToCreate > 0) {
            this.diffToCreate = 4;
        } else if ((this.numBossToCreate > 0 && this.numWeakToCreate > 0 && this.numFastToCreate > 0) || (this.numBossToCreate > 0 && this.numWeakToCreate > 0 && this.numSpittleToCreate > 0) || (this.numBossToCreate > 0 && this.numFastToCreate > 0 && this.numSpittleToCreate > 0) || (this.numFastToCreate > 0 && this.numSpittleToCreate > 0 && this.numWeakToCreate > 0)) {
            this.diffToCreate = 3;
        } else if ((this.numBossToCreate > 0 && this.numFastToCreate > 0) || (this.numBossToCreate > 0 && this.numWeakToCreate > 0) || (this.numBossToCreate > 0 && this.numSpittleToCreate > 0) || (this.numWeakToCreate > 0 && this.numFastToCreate > 0) || (this.numWeakToCreate > 0 && this.numSpittleToCreate > 0) || (this.numFastToCreate > 0 && this.numSpittleToCreate > 0)) {
            this.diffToCreate = 2;
        } else if ((this.numBossToCreate > 0 && !(this.numFastToCreate > 0) && !(this.numWeakToCreate > 0) && !(this.numSpittleToCreate > 0)) || (!(this.numBossToCreate > 0) && (this.numFastToCreate > 0) && !(this.numWeakToCreate > 0) && !(this.numSpittleToCreate > 0)) || (!(this.numBossToCreate > 0) && !(this.numFastToCreate > 0) && (this.numWeakToCreate > 0) && !(this.numSpittleToCreate > 0)) || (!(this.numBossToCreate > 0) && !(this.numFastToCreate > 0) && !(this.numWeakToCreate > 0) && (this.numSpittleToCreate > 0))) {
            this.diffToCreate = 1;
        } else {
            this.diffToCreate = 0;
        }
    }

    public void createWeakZombie(float x, float y, float mulHealth, float prob) {
        this.handler.addSprite(new StandardZombie(x, y, (float) 2, (int) (100 * mulHealth), 25, handler.getPlayer(), this.handler, prob, 60, 60, 5, new Animation(Assets.zombie, 20), new Animation(Assets.zombieAttack, 35), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
    }

    public void createFastZombie(float x, float y, float mulHealth, float prob) {
        this.handler.addSprite(new StandardZombie(x, y, (float) 4, (int) (35 * mulHealth), 50, handler.getPlayer(), this.handler, prob, 60, 60, 20, new Animation(Assets.zombie2, 15), new Animation(Assets.zombie2Attack, 50), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
    }

    public void createSpittleZombie(float x, float y, float mulHealth, float prob) {
        this.handler.addSprite(new SpittleZombie(x, y, (float) 2, (int) (400 * mulHealth), 75, handler.getPlayer(), this.handler, prob, 60, 60, 45, new Animation(Assets.zombie3, 40), new Animation(Assets.zombie3Attack, 50), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
    }

    public void createBoss(float x, float y, float mulHealth, float prob) {
        this.handler.addSprite(new Boss(x, y, (float) 1, 4000, 100, handler.getPlayer(), this.handler, prob, 120, 120, 500, new Animation(Assets.boss, 40), new Animation(Assets.bossAttack, 120), new Animation(Assets.bossdeath, 70), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
    }

    public void addEnemy() {
        this.numZombieRound += 1;
    }

    public void removeEnemy() {
        this.numZombieRound -= 1;
    }
}
