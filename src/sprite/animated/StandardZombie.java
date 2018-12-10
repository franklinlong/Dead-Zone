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
 * @author giova
 */
public class StandardZombie extends Zombie {

    public static final int ZOMBIESIZE = 60;

    //Danni inflitti dallo zombie quando attacca
    private int damage;

    //Timer
    Timer attackDelay, hitZombie;

    public StandardZombie(float x, float y, float vel, int health, int damage, Player player, Handler handler,
            float probabilityDrop, int width, int height, int score, Animation walkAnimation, Animation attackAnimation, Sound biteSound, Sound hitSound) {
        super(x, y, vel, health, player, handler, probabilityDrop, width, height, score, walkAnimation, attackAnimation, biteSound, hitSound);

        this.damage = damage;

        attackDelay = new Timer(350, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (distanceToPlayerX < Player.PLAYERSIZE && distanceToPlayerY < Player.PLAYERSIZE && player.isDeath() == false) {
                    biteSound.playSound();
                    player.hit(damage);
                }

                attackDelay.stop();
                attacking = false;
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
        float xx, yy;
        xx = getX() - offsetX;
        yy = getY() - offsetY;

        if(this.damage == 40){ //Zombie fast
            at = AffineTransform.getTranslateInstance(xx - 15, yy - 35);
            at.rotate(angle, 90/2, 130/2);
        }
        else{ //Zombie normale
            at = AffineTransform.getTranslateInstance(xx, yy);
            at.rotate(angle, ZOMBIESIZE/2, ZOMBIESIZE/2);
        }
        
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
            else
                System.err.println("Penso sia tutto ok... StandardZombie riga 104");
        }
        else{
            this.setAngle((float) Math.atan(velStandard[1] / velStandard[0]));
            if (velStandard[0] < 0) {
                this.setAngle((float) -Math.PI + this.getAngle());
            }
        }
        
        
        int portata;
        if(this.damage == 40){ //Zombie fast
            portata = 60;
        }
        else{ //Zombie normale
            portata = 30;
        }
        
        float toPlayerX = (player.getX()+ player.width) - (this.getX() + this.width);
        float toPlayerY = (player.getY() + player.height) - (this.getY() + this.height);
        distanceToPlayerX = (float) (Math.sqrt(toPlayerX * toPlayerX + toPlayerY * toPlayerY));
        distanceToPlayerY = (float) (Math.sqrt(toPlayerX * toPlayerX + toPlayerY * toPlayerY));

        //Se lo zombie è vicino al player lo attacca e quindi non si deve muovere
        if (distanceToPlayerX < portata && distanceToPlayerY < portata && !attackDelay.isRunning() && !player.isDeath()) {
            attacking = true;
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
