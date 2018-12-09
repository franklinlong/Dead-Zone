/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite.animated;

import deadzone.Handler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.Timer;
import utilities.Animation;
import utilities.Route;
import utilities.Sound;

/**
 *
 * @author casang
 */
public class SpittleZombie extends Zombie {

    public static final int ZOMBIESIZE = 60;

    //Danni inflitti dallo zombie quando attacca
    private int damage;

    //Timer
    Timer attackDelay, hitZombie;

    public SpittleZombie(float x, float y, float vel, int health, int damage, Player player, Handler handler,
            float probabilityDrop, int width, int height, int score, Animation walkAnimation, Animation attackAnimation, Sound biteSound, Sound hitSound) {
        super(x, y, vel, health, player, handler, probabilityDrop, width, height, score, walkAnimation, attackAnimation, biteSound, hitSound);

        this.damage = damage;

        attackDelay = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                biteSound.playSound();
                attackDelay.stop();
                currentAnimation = walkAnimation;
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
        if (getHealth() <= 0) {
            death();
        }

        this.zona.aggiorna(getX(),getY());

        Route traiettoria = new Route(player, this, handler);

        //Velocità dello zombie per raggiungere la zona corretta
        float[] velStandard = traiettoria.raggiungiZona();

        if(velStandard[0] == 0){
            if(velStandard[1] > 0)
                this.setAngle((float) - Math.PI/2);
            else if(velStandard[1]<0)
                this.setAngle((float) + Math.PI/2);
        }
        else{
            this.setAngle((float) Math.atan(velStandard[1] / velStandard[0]));
            if (velStandard[0] < 0) {
                this.setAngle((float) -Math.PI + this.getAngle());
            }
        }
        
        float toPlayerX = player.getX() - this.getX();
        float toPlayerY = player.getY() - this.getY();
        distanceToPlayerX = (float) (Math.sqrt(toPlayerX * toPlayerX + toPlayerY * toPlayerY));
        distanceToPlayerY = (float) (Math.sqrt(toPlayerX * toPlayerX + toPlayerY * toPlayerY));

        float xbullet = (float) Math.cos(angle);
        float ybullet = (float) Math.sin(angle);
        float spittledirectionX = xbullet / (float) Math.sqrt(xbullet * xbullet + ybullet * ybullet);
        float spittledirectionY = ybullet / (float) Math.sqrt(ybullet * ybullet + xbullet * xbullet);

        //Se lo zombie è nelle vicinanze del player lo attacca
        if (distanceToPlayerX < player.width * 5 && distanceToPlayerY < player.height * 5 && !attackDelay.isRunning() && !player.isDeath()) {
            Spittle s = new Spittle(this.getX(), this.getY(), spittledirectionX,
                    spittledirectionY, (float)3, 100, this.handler, damage,
                    this.handler.getPlayer());
            this.handler.addSprite(s);
            attacking = true;
            attackDelay.start();
        }

        if (distanceToPlayerX > player.width * 5 && distanceToPlayerY > player.height * 5 && !player.isDeath()) {
            attacking = false;
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

    @Override
    public void sound_hit() {
        if (!hitZombie.isRunning()) {
            hitZombie.start();
        }
    }
}
