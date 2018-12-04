/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite.animated;

import deadzone.Handler;
import gameMenu.PauseMenu;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.Timer;
import sprite.Blood;
import utilities.Animation;
import utilities.Assets;
import utilities.Route;
import utilities.Sound;
import sprite.Ammo;
import sprite.MedicalKit;
import sprite.Nuke;

/**
 *
 * @author USER
 */
public class Zombie extends AnimatedSprite{

    public static final int ZOMBIESIZE = 60;
    public static final int SCORE = 5;
    
    private float distanceToPlayerX;
    private float distanceToPlayerY;
    
    private final Player player;
    
    //Danni inflitti dallo zombie quando attacca
    private final int damage = 20;
    
    private final Animation walkAnimation, attackAnimation;
    private Animation currentAnimation;
    
    private final Sound biteSound,hitSound;
    
    private Timer attackDelay, hitZombie;
    private boolean attacking = false;
    
    private final Handler handler;
    private float probabilityDrop; //probabilità percentuale di rilascio oggetto dello zombie
    
    public Zombie(float x, float y, int vel, int health, Player player, Handler handler, float probabilityDrop) {
        super(x, y, ZOMBIESIZE, ZOMBIESIZE, vel, health);
        this.velX = vel;
        this.velY = vel;
        this.player = player;
        this.handler = handler;
        this.probabilityDrop = probabilityDrop;
        biteSound = new Sound(Assets.zombieBite);
        hitSound = new Sound(Assets.zombieHit);
        
        attackDelay = new Timer(350, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(distanceToPlayerX < Player.PLAYERSIZE && distanceToPlayerY < Player.PLAYERSIZE && player.isDeath() == false)
                { 
                    biteSound.playSound();
                    player.hit(damage);
                }

                attackDelay.stop();
                attacking = false;
                currentAnimation = walkAnimation;
            }

        });
        
        hitZombie = new Timer(300,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hitSound.playSound();
                hitZombie.stop();
            }
        });
           
        walkAnimation = new Animation(Assets.zombie, 20);
        attackAnimation = new Animation(Assets.zombieAttack, 35);
        currentAnimation = walkAnimation;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        double xx,yy;
        xx=getX()-offsetX;
        yy=getY()-offsetY;

                
        at = AffineTransform.getTranslateInstance(xx,yy);
        at.rotate(angle,this.width/2,this.height/2);
        
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(currentAnimation.getCurrentFrame(), at, null);
        
    }

    @Override
    public void animationCycle() {
        if (!PauseMenu.pause){
            //Controllo che sia vivo        
            if(getHealth()<=0)
                death();
        
        
            //in base al percorso che deve seguire lo zombie, a[] avrà la velocitaX e la velocitaY
            float[] b = new Route(player, this).seek();
            
            //velocita su X e su Y fittizie per capire se ci sono ostacoli attorno lo zombie
            int vx =0;
            int vy = 0;
            if(b[0] < 0)
                vx = -1;
            else if(b[0] > 0)
                vx = +1;
            
            if(b[1]<0)
                vy = -1;
            else if(b[1] >0)
                vy = +1;
            
            //Codice per ricalcolare la direzione in base alla presenza di zombie vicini ... DA FARE
            //float[] a = new Route(player, this).evitaZombie(vx,vy, this.handler.getZombies());
            float[] a = new Route(player, this).seek();
        
            angle = (float) Math.acos(a[0]);
            if(a[1] < 0)
                    angle *= -1;
        
            float toPlayerX = player.getX() - this.getX();
            float toPlayerY = player.getY() - this.getY();
            distanceToPlayerX = (float)(Math.sqrt(toPlayerX*toPlayerX + toPlayerY*toPlayerY));
            distanceToPlayerY = (float)(Math.sqrt(toPlayerX*toPlayerX + toPlayerY*toPlayerY));
        
            //Se lo zombie è vicino al player lo attacca e quindi non si deve muovere
            if(distanceToPlayerX < player.width/2 && distanceToPlayerY < player.height/2 && !attackDelay.isRunning() && !player.isDeath())
            {
                attacking = true;
                attackDelay.start();
                currentAnimation = attackAnimation;
                currentAnimation.setIndex();
            }
        
            //Se è in corso l'animazione dell'attacco lo zombie non si muove
            //Se il player è morto lo zombie non si muove
            if(attacking || player.isDeath()){
                a[0] = 0;
                a[1] = 0;
            }
        
            float x = getX();
            float y = getY();
        
            
            //Aggiorno la posizione dello zombie in base alle velocità fittizie per controllare le collisioni
            x += vx;
            y += vy;
        
            //aggiorno le variabili dello sprite per come funziona collision
            setX(x);
            setY(y);
        
            //Se c'è una collisione non posso passare
            int k = collision(vx, vy, x, y);
            switch (k) {
                case 1:
                    y = y - vy + a[1];
                    x -= vx;
                    break;
                case 2:
                    x = x - vx + a[0];
                    y -= vy;
                    break;
                case 3:
                    x -= vx;
                    y -= vy;
                    break;
                default:
                    //Aggiorno la posizione dello zombie in base ai calcoli sul percorso
                    x = x - vx + a[0];
                    y = y - vy + a[1];
                    break;
                }
                
            setX(x);
            setY(y);
        
            //Aggiorno l'animazione
            currentAnimation.update();
        }
    }
    
    //Metodo chiamato alla morte dello zombie
    @Override
    public void death(){    
        //Alla orte dello zombie si crea la chiazza di sangue
        this.handler.addSprite(new Blood(this.getX(), this.getY(),30, 30, handler));
        this.player.updatePunteggio(SCORE);
        
        //Alla morte dello zombie, con una data probabilita, viene rilasciato un nuovo item
        boolean drop = (Math.random() *100) <= probabilityDrop;
        if(drop){
            float probAmmo = 50;
            float probNuke = 20;
            float probMK = 30;
            float valoreCasuale = (float) (Math.random()*100);
        
            if(valoreCasuale < probNuke){
                handler.addSprite(new Nuke(this.getX()+this.width/2, this.getY()+this.height/2, 20, 20, handler));
            }
            else if(valoreCasuale < probNuke+probMK){
                handler.addSprite(new MedicalKit(this.getX()+this.width/2, this.getY()+this.height/2, 20, 20, handler));
            }
            else if(valoreCasuale <= probNuke+probMK+probAmmo){
                handler.addSprite(new Ammo(this.getX()+this.width/2, this.getY()+this.height/2, 20, 20, handler));
            }
            else{
                System.out.println("NEL COSTRUTTORE DI DROP ITEM, NON DOVREBBE STAMPARE STO MESSAGGIO");
            }
        }
        
        //Lo zombie viene rimosso
        this.handler.removeSprite(this);
    }
    
    //metodo chiamato dall'esterno che mi infligge danni
    public void hit(int damage){
        setHealth(getHealth()-damage);
        if(!hitZombie.isRunning())
            hitZombie.start();
    }
    
}
