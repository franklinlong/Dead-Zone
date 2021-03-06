package deadzone;

import deadzone.utilities.Assets;
import deadzone.utilities.Sound;
import deadzone.utilities.Animation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;
import deadzone.sprite.animated.PlayerFactory;
import deadzone.sprite.animated.Projectile;
import deadzone.sprite.animated.RocketBullet;

public class Gun {

    private final int bulletOffset = 120;
    private final BufferedImage skin;
    private final int width;

    // animations
    private Animation currentAnimation;
    private final Animation idle, reloadAnimation, shoot;

    // sounds
    private final Sound shootSound, emptyGun, reloadSound;

    // timers
    private final Timer firingDelay, shootAminDelay, reloadDelay, emptyGunDelay, reloadLimit;

    // bullets managment
    private final int bulletsPerRound; //colpi totali del caricatore
    private int round, totalBullets; //colpi rimanenti e colpi totali
    private boolean shooting = false, reloading = false;

    // handler
    private Handler handler;
    
    private final int damage;

    public Gun(BufferedImage skin, Animation idle, Animation reloadAnimation, Animation shoot,
                    Sound shootSound, Sound reloadSound, PlayerFactory player, int firingDelay,
                    int bulletsPerRound, int totalBullets, int damage){
            this.damage = damage;
            this.skin = skin;
            this.idle = idle;
            this.reloadAnimation = reloadAnimation;
            this.shoot = shoot;
            this.shootSound = shootSound;
            this.reloadSound = reloadSound;
            this.emptyGun = new Sound(Assets.emptyGun);
            this.firingDelay = new Timer(firingDelay, new TimeHandler());
            this.firingDelay.setActionCommand("1");
            this.shootAminDelay = new Timer(100, new TimeHandler());
            this.shootAminDelay.setActionCommand("2");
            this.reloadDelay = new Timer(1700, new TimeHandler());
            this.reloadDelay.setActionCommand("3");
            this.emptyGunDelay = new Timer(700, new TimeHandler());
            this.emptyGunDelay.setActionCommand("4");
            this.reloadLimit = new Timer(3000, new TimeHandler());
            this.reloadLimit.setActionCommand("5");
            this.bulletsPerRound = bulletsPerRound;
            this.round = bulletsPerRound;
            this.totalBullets = totalBullets;
            width = skin.getWidth();
            currentAnimation = idle;
    }

    //sparo
    public void shoot(float angle, float xx, float yy) {
        if (round <= 0) {
            if (!emptyGunDelay.isRunning()) {
                emptyGun.playSound();
                emptyGunDelay.start();
            }
            return;
        }
        if (reloading) {
            return;
        }

        if (!firingDelay.isRunning()) {
            shooting = true;
            firingDelay.start();
            shootSound.playSound();
            round--;

            int vitaProiettile = 100;
            if (this.skin == Assets.shotgunSkin) {
                vitaProiettile = 13;
            }

            float angoloPistola = angle + (float) Math.PI / 4;
            float xbullet = (float) Math.cos(angle);
            float ybullet = (float) Math.sin(angle);
            float bulletdirectionX = xbullet / (float) Math.sqrt(xbullet * xbullet + ybullet * ybullet);
            float bulletdirectionY = ybullet / (float) Math.sqrt(ybullet * ybullet + xbullet * xbullet);

            if(this.skin == Assets.rpgSkin){
                //Proiettile di RPG
                RocketBullet rb = new RocketBullet((xx + PlayerFactory.PLAYERSIZE / 2 + (float) (22 * Math.cos(angoloPistola))),
                    (yy + PlayerFactory.PLAYERSIZE / 2 + (float) (22 * Math.sin(angoloPistola))),
                    bulletdirectionX, bulletdirectionY, 20, vitaProiettile, handler, damage);
                shootAminDelay.start();
                currentAnimation = shoot;
                this.handler.addSprite(rb);
            }
            else{
                //Proiettile standard in linea retta
                Projectile p = new Projectile((xx + PlayerFactory.PLAYERSIZE / 2 + (float) (22 * Math.cos(angoloPistola))),
                        (yy + PlayerFactory.PLAYERSIZE / 2 + (float) (22 * Math.sin(angoloPistola))),
                        bulletdirectionX, bulletdirectionY, 20, vitaProiettile, handler, damage);
                shootAminDelay.start();
                currentAnimation = shoot;
                this.handler.addSprite(p);
            }

            //Ventaglio di proiettili
            if (this.skin == Assets.shotgunSkin) {
                angle += Math.PI / 20;
                xbullet = (float) Math.cos(angle);
                ybullet = (float) Math.sin(angle);
                bulletdirectionX = xbullet / (float) Math.sqrt(xbullet * xbullet + ybullet * ybullet);
                bulletdirectionY = ybullet / (float) Math.sqrt(ybullet * ybullet + xbullet * xbullet);

                Projectile p2 = new Projectile((xx + PlayerFactory.PLAYERSIZE / 2 + (float) (22 * Math.cos(angoloPistola))),
                        (yy + PlayerFactory.PLAYERSIZE / 2 + (float) (22 * Math.sin(angoloPistola))),
                        bulletdirectionX, bulletdirectionY, 20, vitaProiettile, handler, damage);

                angle += Math.PI / 20;
                xbullet = (float) Math.cos(angle);
                ybullet = (float) Math.sin(angle);
                bulletdirectionX = xbullet / (float) Math.sqrt(xbullet * xbullet + ybullet * ybullet);
                bulletdirectionY = ybullet / (float) Math.sqrt(ybullet * ybullet + xbullet * xbullet);

                Projectile p3 = new Projectile((xx + PlayerFactory.PLAYERSIZE / 2 + (float) (22 * Math.cos(angoloPistola))),
                        (yy + PlayerFactory.PLAYERSIZE / 2 + (float) (22 * Math.sin(angoloPistola))),
                        bulletdirectionX, bulletdirectionY, 20, vitaProiettile, handler, damage);

                angle -= Math.PI * 3 / 20;
                xbullet = (float) Math.cos(angle);
                ybullet = (float) Math.sin(angle);
                bulletdirectionX = xbullet / (float) Math.sqrt(xbullet * xbullet + ybullet * ybullet);
                bulletdirectionY = ybullet / (float) Math.sqrt(ybullet * ybullet + xbullet * xbullet);

                Projectile p4 = new Projectile((xx + PlayerFactory.PLAYERSIZE / 2 + (float) (22 * Math.cos(angoloPistola))),
                        (yy + PlayerFactory.PLAYERSIZE / 2 + (float) (22 * Math.sin(angoloPistola))),
                        bulletdirectionX, bulletdirectionY, 20, vitaProiettile, handler, damage);

                angle -= Math.PI / 20;
                xbullet = (float) Math.cos(angle);
                ybullet = (float) Math.sin(angle);
                bulletdirectionX = xbullet / (float) Math.sqrt(xbullet * xbullet + ybullet * ybullet);
                bulletdirectionY = ybullet / (float) Math.sqrt(ybullet * ybullet + xbullet * xbullet);

                Projectile p5 = new Projectile((xx + PlayerFactory.PLAYERSIZE / 2 + (float) (22 * Math.cos(angoloPistola))),
                        (yy + PlayerFactory.PLAYERSIZE / 2 + (float) (22 * Math.sin(angoloPistola))),
                        bulletdirectionX, bulletdirectionY, 20, vitaProiettile, handler, damage);

                this.handler.addSprite(p2);
                this.handler.addSprite(p3);
                this.handler.addSprite(p4);
                this.handler.addSprite(p5);
            }
        }

    }

    public void reload() {
        if (reloadLimit.isRunning()) {
            return;
        }
        reloadLimit.start();
        
        
        
        if (round != 0 && totalBullets >= bulletsPerRound) {
            totalBullets = totalBullets - bulletsPerRound + round;
            round = bulletsPerRound;
        } else if (totalBullets < bulletsPerRound && round != 0) {

            if (round + totalBullets > bulletsPerRound) {
                totalBullets = round + totalBullets - bulletsPerRound;
                round = bulletsPerRound;
            } else {
                round += totalBullets;
                totalBullets = 0;
            }
        } else if (totalBullets < bulletsPerRound) {
            round = totalBullets;
            totalBullets = 0;
        } else {
            round = bulletsPerRound;
            totalBullets -= bulletsPerRound;
        }

        if (!reloadDelay.isRunning()) {
            currentAnimation = reloadAnimation;
            reloading = true;
            reloadDelay.start();
            reloadSound.playSound();
        }
    }

    boolean isShooting() {
        return shooting;
    }

    class TimeHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(firingDelay.getActionCommand())) {
                shootSound.stopSound();
                firingDelay.stop();
                shooting = false;
            } else if (e.getActionCommand().equals(shootAminDelay.getActionCommand())) { //Serve per rimettere a idle 
                shoot.setIndex();											//l'animazione dopo lo sparo
                currentAnimation = idle;
                shootAminDelay.stop();
            } else if (e.getActionCommand().equals(reloadDelay.getActionCommand())) {
                reloading = false;
                reloadAnimation.setIndex();
                currentAnimation = idle;
                reloadDelay.stop();
            } else if (e.getActionCommand().equals(emptyGunDelay.getActionCommand())) {
                emptyGunDelay.stop();
            } else if (e.getActionCommand().equals(reloadLimit.getActionCommand())) {
                reloadLimit.stop();
            }
        }
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setTotalBullets(int totalBullets) {
        this.totalBullets = totalBullets;
    }

    public int getRound() {
        return round;
    }

    public int getBulletsPerRound() {
        return bulletsPerRound;
    }

    public int getTotalBullets() {
        return totalBullets;
    }

    //aggiornamento animazione    
    public void update() {
        currentAnimation.update();
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    public BufferedImage getSkin() {
        return skin;
    }
    
    public void setHandler(Handler h){
        handler = h;
    }
    
}
