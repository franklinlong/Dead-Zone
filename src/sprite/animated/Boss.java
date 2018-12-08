/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite.animated;

import deadzone.Handler;
import deadzone.Waves;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.Timer;
import utilities.Animation;
import utilities.Assets;
import utilities.Route;
import utilities.Sound;
/**
 *
 * @author genna
 */
public class Boss extends Zombie{
    
    //la size andrà modificata!!
    public static final int ZOMBIESIZE = 120;
    private boolean morto=false;
    //Danni inflitti dallo zombie quando attacca
    private int damage;
    Timer attackDelay, hitZombie, deathDelay;
    private Animation deathAnimation;
    private Waves wave;
    
    public Boss(float x, float y, int vel, int health, int damage, Player player, Handler handler,
            float probabilityDrop, int width, int height, int score, Animation walkAnimation, Animation attackAnimation, Animation deathAnimation, Sound biteSound, Sound hitSound, Waves wave){
        
        super(x, y, vel, health, player, handler, probabilityDrop, width, height, score, walkAnimation, attackAnimation, biteSound, hitSound);
        this.deathAnimation=deathAnimation;
        this.damage = damage;
        this.wave=wave;
        //l'attacco deve essere diverso..
        attackDelay = new Timer(1500, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                biteSound.playSound();
                attackDelay.stop();
                currentAnimation = walkAnimation;
            }

        });
        
        deathDelay = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MORTE SOUND.playSound();
                deathDelay.stop();
            }

        });
        
        hitZombie = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hitSound.playSound();
                hitZombie.stop();
            }
        });
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        double xx, yy;
        xx = getX() - offsetX;
        yy = getY() - offsetY;

        at = AffineTransform.getTranslateInstance(xx, yy);
        at.rotate(angle, this.width / 2, this.height / 2);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(currentAnimation.getCurrentFrame(), at, null);

    }

    @Override
    public void animationCycle() {
        //Controllo che sia vivo
        System.out.println(this.getHealth());
        if (getHealth() <= 0) {
            if(morto == false){
                currentAnimation = deathAnimation;
                currentAnimation.setIndex();
                morto=true;
                //deathDelay.start();
            }
            currentAnimation.update();
            System.out.println(currentAnimation.getIndex());
            if(currentAnimation.getIndex() == 6){
                System.out.println("wow");
                death();
                this.handler.addSprite(new SpittleZombie(this.getX(), this.getY(), 1, 700, 50, handler.getPlayer(), this.handler, 0, 60, 60, 5, new Animation(Assets.zombie3, 15), new Animation(Assets.zombie3Attack, 15), new Sound(Assets.zombieBite), new Sound(Assets.zombieHit)));
                this.wave.addEnemy();
                SpawnSpittle m2 = new SpawnSpittle(this.getX(), this.getY(), 1,
                        1, 3, 15, this.handler, this.handler.getPlayer());
                this.handler.addSprite(m2);
                this.wave.addEnemy();
                SpawnSpittle m3 = new SpawnSpittle(this.getX(), this.getY(), -1,
                        1, 3, 15, this.handler, this.handler.getPlayer());
                this.handler.addSprite(m3);
                this.wave.addEnemy();
                SpawnSpittle m4 = new SpawnSpittle(this.getX(), this.getY(), 1,
                        -1, 3, 15, this.handler, this.handler.getPlayer());
                this.handler.addSprite(m4);
                this.wave.addEnemy();
                SpawnSpittle m5 = new SpawnSpittle(this.getX(), this.getY(), -1,
                        -1, 3, 15, this.handler, this.handler.getPlayer());
                this.handler.addSprite(m5);
                this.wave.addEnemy();
            }
            
        }
        
        this.zona.aggiorna(getX(),getY());
        if (morto==false){
            Route traiettoria = new Route(player, this, handler);

            //Velocità dello zombie per raggiungere la zona corretta
            float[] velStandard = traiettoria.raggiungiZona();
        
            float toPlayerX = player.getX() - this.getX();
            float toPlayerY = player.getY() - this.getY();
            distanceToPlayerX = (float) (Math.sqrt(toPlayerX * toPlayerX + toPlayerY * toPlayerY));
            distanceToPlayerY = (float) (Math.sqrt(toPlayerX * toPlayerX + toPlayerY * toPlayerY));

            //qui creo le coordinate del bullet
            float xbullet = (float) Math.cos(angle);
            float ybullet = (float) Math.sin(angle);
            float spittledirectionX = xbullet / (float) Math.sqrt(xbullet * xbullet + ybullet * ybullet);
            float spittledirectionY = ybullet / (float) Math.sqrt(ybullet * ybullet + xbullet * xbullet);

            //randomicamente si può curare...
            if(this.getHealth() < 2000 && this.getHealth()>0){
                int n = (int) (Math.random() * 700); 
                if(n==0) this.setHealth(this.getHealth()+400);
                //dovrebbe esserci un effetto grafico di cura...
            }
            //Se lo zombie è nelle vicinanze del player lo attacca
            if ((distanceToPlayerX <= player.width * 5 && distanceToPlayerY <= player.height * 5) && !attackDelay.isRunning() && !player.isDeath()) {
                int n = (int) (Math.random() * 25); //randomicamente

                switch (n) {
                    case 0:
                        //attacco spawn
                        int m = (int) (Math.random() * 3);
                        switch(m){
                            case 0:
                                spittledirectionX*=-1;
                                break;
                            case 1:
                                spittledirectionY*=-1;
                                break;
                            case 2:
                                spittledirectionX*=-1;
                                spittledirectionY*=-1;
                                break;
                        }
                        SpawnSpittle ms = new SpawnSpittle(this.getX(), this.getY(), spittledirectionX,
                        spittledirectionY, 3, 100, this.handler, this.handler.getPlayer());
                        this.handler.addSprite(ms);
                        this.wave.addEnemy();
                        break;
                    default:
                        //proiettile
                        Spittle s = new Spittle(this.getX(), this.getY(), spittledirectionX,
                        spittledirectionY, 3, 100, this.handler, damage, this.handler.getPlayer());
                        this.handler.addSprite(s);
                        break;
                }
                attacking = true;
                attackDelay.start();
                currentAnimation = attackAnimation;
                currentAnimation.setIndex();
            }

            if ((distanceToPlayerX > player.width * 5 && distanceToPlayerY > player.height * 5) && !player.isDeath()) {
                int n = (int) (Math.random() * 220); //randomicamente
                switch (n) {
                    case 0:
                        //attacco spawn
                        int m = (int) (Math.random() * 3);
                        switch(m){
                            case 0:
                                spittledirectionX*=-1;
                                break;
                            case 1:
                                spittledirectionY*=-1;
                                break;
                            case 2:
                                spittledirectionX*=-1;
                                spittledirectionY*=-1;
                                break;
                        }
                        SpawnSpittle ms = new SpawnSpittle(this.getX(), this.getY(), spittledirectionX,
                        spittledirectionY, 3, 25, this.handler, this.handler.getPlayer());
                        this.handler.addSprite(ms);
                        this.wave.addEnemy();
                        break;
                    default: break;}
                attacking = false;
                attackDelay.start();
                currentAnimation = attackAnimation;
                currentAnimation.setIndex();
            }
        
            //Se è in corso l'animazione dell'attacco lo zombie non si muove
            //Se il player è morto lo zombie non s muove
            if (attacking || player.isDeath()) {
                velStandard[0] = 0;
                velStandard[1] = 0;
            }

            //posizione futura dello zombie considerando gli ostacoli
            float[] pos = traiettoria.gestisciOstacoli(velStandard[0], velStandard[1]);

            //posizione futura dello zombie considerando gli ostacoli
            setX(pos[0]);
            setY(pos[1]);
             
            //Aggiorno l'animazione
            currentAnimation.update();
        }
    }

    @Override
    public void sound_hit() {
        if (!hitZombie.isRunning()) {
            hitZombie.start();
        }
    }
    
}
