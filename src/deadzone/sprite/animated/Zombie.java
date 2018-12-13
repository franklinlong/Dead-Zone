/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.sprite.animated;

import deadzone.Handler;
import java.awt.Graphics;
import deadzone.sprite.Blood;
import deadzone.utilities.Animation;
import deadzone.utilities.Sound;
import deadzone.sprite.Ammo;
import deadzone.sprite.MedicalKit;
import deadzone.sprite.Nuke;
import deadzone.utilities.Zona;

/**
 *
 * @author USER
 */
public abstract class Zombie extends AnimatedSprite {

    private final int score;
    protected Zona zona;
    protected float distanceToPlayerX;
    protected float distanceToPlayerY;

    protected final Player player;

    protected final Animation walkAnimation, attackAnimation;
    protected Animation currentAnimation;

    private final Sound biteSound, hitSound;

    protected boolean attacking = false;

    protected final Handler handler;
    private final float probabilityDrop; //probabilità percentuale di rilascio oggetto dello zombie

    public Zombie(float x, float y, float vel, int health, Player player, Handler handler,
            float probabilityDrop, int width, int height, int score, Animation walkAnimation, Animation attackAnimation, Sound biteSound, Sound hitSound) {
        super(x, y, width, height, vel, health);
        this.velX = vel;
        this.velY = vel;
        this.player = player;
        this.handler = handler;
        this.probabilityDrop = probabilityDrop;
        this.score = score;

        this.walkAnimation = walkAnimation;
        this.attackAnimation = attackAnimation;
        this.biteSound = biteSound;
        this.hitSound = hitSound;
        currentAnimation = walkAnimation;

        this.zona = new Zona(getX(), getY());
    }

    @Override
    public abstract void drawImage(Graphics g, float offsetX, float offsetY);

    @Override
    public abstract void animationCycle();

    //Metodo chiamato alla morte dello zombie
    @Override
    public void death() {
        //Alla orte dello zombie si crea la chiazza di sangue
        this.handler.addSprite(new Blood(this.getX(), this.getY(), 30, 30, handler));
        this.player.updatePunteggio(this.score);
        this.player.updateZombieKilled();
        this.handler.getWaves().updateNumZombieKilledRound();

        //Alla morte dello zombie, con una data probabilita, viene rilasciato un nuovo item
        boolean drop = (Math.random() * 100) <= probabilityDrop;
        if (drop) {
            float probAmmo = 60;
            float probNuke = 5;
            float probMK = 35;
            float valoreCasuale = (float) (Math.random() * 100);

            if (valoreCasuale < probNuke) {
                handler.addSprite(new Nuke(this.getX() + this.width / 2, this.getY() + this.height / 2, 20, 20, handler));
            } else if (valoreCasuale < probNuke + probMK) {
                handler.addSprite(new MedicalKit(this.getX() + this.width / 2, this.getY() + this.height / 2, 20, 20, handler));
            } else if (valoreCasuale <= probNuke + probMK + probAmmo) {
                handler.addSprite(new Ammo(this.getX() + this.width / 2, this.getY() + this.height / 2, 20, 20, handler));
            } else {
                System.out.println("NEL COSTRUTTORE DI DROP ITEM, NON DOVREBBE STAMPARE STO MESSAGGIO");
            }
        }

        this.handler.removeSprite(this);
    }

    public void hit(int damage) {
        setHealth(getHealth() - damage);
        sound_hit();
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public abstract void sound_hit();
}
