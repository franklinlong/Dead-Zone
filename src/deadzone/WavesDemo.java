/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import deadzone.sprite.animated.Boss;
import deadzone.sprite.animated.PlayerDemo;
import deadzone.sprite.animated.SpittleZombie;
import deadzone.sprite.animated.StandardZombie;
import deadzone.utilities.Animation;
import deadzone.utilities.Assets;
import deadzone.utilities.Sound;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author casang
 */
public class WavesDemo extends Waves{
    
    public static final Object FW = new Object();
    
    public WavesDemo(){
        super();
    }
    
    @Override
    public void run() {
        this.numZombieRound = 3;
        this.allKilled = false;
        this.createWeakZombie(2072, 2514, 1, (float) 0);
        this.createWeakZombie(10, 1640, 1, (float) 100);
        this.createWeakZombie(2516, 238, 1, (float) 0);
     
        synchronized (KL) {
            while (!this.allKilled) {
                try {
                    KL.wait(); //si aspetta che venga modificato allKilled
                } catch (InterruptedException ex) {
                    Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        PlayerDemo.FlagWaves = true;
        
        synchronized(FW){
            while (PlayerDemo.FlagWaves) {
                try {
                    FW.wait(); //si aspetta che venga modificato allKilled
                } catch (InterruptedException ex) {
                    Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        this.allKilled = false;
        this.createSpittleZombie(2072, 2514, 1, (float) 15);
        this.createSpittleZombie(2224, 238, 1, (float) 15);
        this.createWeakZombie(2418, 466, 1, (float) 15);
        this.createWeakZombie(10, 1640, 1, (float) 15);
        this.numZombieRound = 4;
        
        
        synchronized (KL) {
            while (!this.allKilled) {
                try {
                    KL.wait(); //si aspetta che venga modificato allKilled
                } catch (InterruptedException ex) {
                    Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        PlayerDemo.FlagWaves = true;
        
        synchronized(FW){
            while (PlayerDemo.FlagWaves) {
                try {
                    FW.wait(); //si aspetta che venga modificato allKilled
                } catch (InterruptedException ex) {
                    Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        this.allKilled = false;
        this.createFastZombie(2250, 180, 1, (float) 15);
        this.createFastZombie(2072, 2514, 1, (float) 15);
        this.createSpittleZombie(2224, 238, 1, (float) 15);
        this.createWeakZombie(624, 2080, 1, (float) 15);
        this.createWeakZombie(2418, 466, 1, (float) 15);
        this.numZombieRound = 5;
        
        synchronized (KL) {
            while (!this.allKilled) {
                try {
                    KL.wait(); //si aspetta che venga modificato allKilled
                } catch (InterruptedException ex) {
                    Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        PlayerDemo.FlagWaves = true;
        
        synchronized(FW){
            while (PlayerDemo.FlagWaves) {
                try {
                    FW.wait(); //si aspetta che venga modificato allKilled
                } catch (InterruptedException ex) {
                    Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(WavesDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.allKilled = false;
        this.createBoss(10, 1340, 1, (float) 1);
        this.numZombieRound = 1;
        
        synchronized (KL) {
            while (!this.allKilled) {
                try {
                    KL.wait(); //si aspetta che venga modificato allKilled
                } catch (InterruptedException ex) {
                    Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        PlayerDemo.FlagWaves = true;
        
        synchronized(FW){
            while (PlayerDemo.FlagWaves) {
                try {
                    FW.wait(); //si aspetta che venga modificato allKilled
                } catch (InterruptedException ex) {
                    Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        try {
            Thread.sleep(6000);
        } catch (InterruptedException ex) {
            Logger.getLogger(PlayerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.allKilled = false;
        this.createFastZombie(2300, 1330, 1, (float) 0);
        this.createSpittleZombie(1900, 2200, 1, (float) 0);
        this.createSpittleZombie(380, 1667, 1, (float) 0);
        this.createSpittleZombie(1000, 885, 1, (float) 0);
        this.createSpittleZombie(1885, 735, 1, (float) 0);
        this.createSpittleZombie(1420, 1050, 1, (float) 0);
        this.createSpittleZombie(2138, 2046, 1, (float) 0);
        this.createSpittleZombie(1728, 2313, 1, (float) 0);
        this.createSpittleZombie(2476, 1773, 1, (float) 0);
        this.createSpittleZombie(850, 1480, 1, (float) 0);
        this.createSpittleZombie(920, 1910, 1, (float) 0);
        this.createFastZombie(87, 1310, 1, (float) 0);
        this.createFastZombie(1200, 2533, 1, (float) 0);
        this.createFastZombie(2198, 2350, 1, (float) 0);
        this.createFastZombie(2812, 2254, 1, (float) 0);
        this.createFastZombie(1883, 264, 1, (float) 0);
        this.createFastZombie(2303, 367, 1, (float) 0);
        this.createFastZombie(2870, 373, 1, (float) 0);
        this.createFastZombie(3104, 2015, 1, (float) 0);
        this.createFastZombie(486, 1871, 1, (float) 0);
        this.createFastZombie(1475, 1826, 1, (float) 0);
        this.createWeakZombie(370, 1090, 1, (float) 0);
        this.createWeakZombie(840, 1105, 1, (float) 0);
        this.createWeakZombie(107, 900, 1, (float) 0);
        this.createWeakZombie(920, 1315, 1, (float) 0);
        this.createWeakZombie(2150, 2876, 1, (float) 0);
        this.numZombieRound = 27;
        
        
        synchronized (KL) {
            while (!this.allKilled) {
                try {
                    KL.wait(); //si aspetta che venga modificato allKilled
                } catch (InterruptedException ex) {
                    Logger.getLogger(Waves.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        PlayerDemo.FlagWaves = true;

    }
    
    
    @Override
    public void createWeakZombie(float x, float y, float mulHealth, float prob) {
        this.handler.addSprite(new StandardZombie(x, y, (float) 3, (int) (100 * mulHealth), 10, handler.getPlayer(), this.handler, prob, 60, 60, 5, new Animation(Assets.zombie, 20), new Animation(Assets.zombieAttack, 35), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
    }

    @Override
    public void createFastZombie(float x, float y, float mulHealth, float prob) {
        this.handler.addSprite(new StandardZombie(x, y, (float) 4, (int) (35 * mulHealth), 50, handler.getPlayer(), this.handler, prob, 60, 60, 20, new Animation(Assets.zombie2, 15), new Animation(Assets.zombie2Attack, 50), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
    }

    @Override
    public void createSpittleZombie(float x, float y, float mulHealth, float prob) {
        this.handler.addSprite(new SpittleZombie(x, y, (float) 3, (int) (400 * mulHealth), 10, handler.getPlayer(), this.handler, prob, 60, 60, 45, new Animation(Assets.zombie3, 40), new Animation(Assets.zombie3Attack, 50), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
    }

    @Override
    public void createBoss(float x, float y, float mulHealth, float prob) {
        this.handler.addSprite(new Boss(x, y, (float) 3, 5000, 10, handler.getPlayer(), this.handler, prob, 60, 60, 500, new Animation(Assets.boss, 40), new Animation(Assets.bossAttack, 120), new Animation(Assets.bossdeath, 70), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
    }
    
}
