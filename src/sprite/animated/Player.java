/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite.animated;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;
import utilities.Animation;
import utilities.Assets;
import deadzone.Gun;
import deadzone.Handler;
import gameMenu.Menu;
import utilities.Sound;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Map;
import listeners.*;
import utilities.Zona;

/**
 *
 * @author giova
 */
public class Player extends AnimatedSprite {

    public static final int PLAYERSIZE = 60;
    protected Zona zona;

    //animations
    private final Animation shotgunIdle, shotgunReload, shotgunShoot;
    private final Animation pistolIdle, pistolReload, pistolShoot;
    private final Animation rifleIdle, rifleReload, rifleShoot;
    private final Animation rpgIdle, rpgReload, rpgShoot;
    
    // sounds
    private final Sound rifleShootSound, rifleReloadSound;
    private final Sound pistolShootSound, pistolReloadSound;
    private final Sound shotgunShootSound, shotgunReloadSound;
    private final Sound rpgShootSound, rpgReloadSound;
    
    private final Sound soundEndGame;

    //Handler che serve per rimuovere il player quando muore
    private final Handler handler;

    private Gun currentGun;
    private final Gun pistol, rifle, shotgun,rpg;

    private float xx, yy;
    private boolean isDeath;

    private int punteggioAttuale;
    private final String name;
    private final boolean male;
    private int zombieKilled;
    private int maximumHealth;
    
    private Map<Vertex,Edge> camminiMinimi;

    public Player(float x, float y, int vel, int health, Handler handler, String name, boolean male) {
        super(x, y, PLAYERSIZE, PLAYERSIZE, (float)vel, health);
        this.punteggioAttuale = 0;
        this.handler = handler;
        this.male = male;
        this.name = name;
        this.maximumHealth = health;

        this.zona = new Zona(getX(), getY());
        new Graph();
        camminiMinimi = Graph.BFS_complete(new Vertex(zona.getIndex()));
        if (this.male) {
            pistolIdle = new Animation(Assets.pistolIdle, 20);
            pistolReload = new Animation(Assets.pistolReload, 100);
            pistolShoot = new Animation(Assets.pistolShootAnim, 80);

            rifleIdle = new Animation(Assets.rifleIdle, 20);
            rifleReload = new Animation(Assets.rifleReload, 100);
            rifleShoot = new Animation(Assets.rifleShootAnim, 80);

            shotgunIdle = new Animation(Assets.shotgunIdle, 20);
            shotgunReload = new Animation(Assets.shotgunReload, 100);
            shotgunShoot = new Animation(Assets.shotgunShootAnim, 80);
            
            rpgIdle = new Animation(Assets.rpgIdle, 20);
            rpgReload = new Animation(Assets.rpgReload, 100);
            rpgShoot = new Animation(Assets.rpgShootAnim, 80);
        } else {
            pistolIdle = new Animation(Assets.femalepistolIdle, 20);
            pistolReload = new Animation(Assets.femalepistolReload, 100);
            pistolShoot = new Animation(Assets.femalepistolShootAnim, 80);

            rifleIdle = new Animation(Assets.femalerifleIdle, 20);
            rifleReload = new Animation(Assets.femalerifleReload, 100);
            rifleShoot = new Animation(Assets.femalerifleShootAnim, 80);

            shotgunIdle = new Animation(Assets.femaleshotgunIdle, 20);
            shotgunReload = new Animation(Assets.femaleshotgunReload, 100);
            shotgunShoot = new Animation(Assets.femaleshotgunShootAnim, 80);
            
            rpgIdle = new Animation(Assets.rpgIdle, 20);
            rpgReload = new Animation(Assets.rpgReload, 100);
            rpgShoot = new Animation(Assets.rpgShootAnim, 80);
        }

        pistolShootSound = new Sound(Assets.pistolShoot);
        pistolShootSound.changeVolume(-10);
        pistolReloadSound = new Sound(Assets.pistolReloadSound);

        rifleShootSound = new Sound(Assets.rifleShoot);
        rifleShootSound.changeVolume(-5);
        rifleReloadSound = new Sound(Assets.rifleReloadSound);

        shotgunShootSound = new Sound(Assets.shotgunShoot);
        shotgunReloadSound = new Sound(Assets.shotgunReloadSound);

        rpgShootSound = new Sound(Assets.rpgShoot);
        rpgReloadSound = new Sound(Assets.rpgReloadSound);
        
        soundEndGame = new Sound(Assets.endGame);
        soundEndGame.changeVolume(6);
        pistol = new Gun(Assets.pistolSkin, pistolIdle, pistolReload, pistolShoot, pistolShootSound,
                pistolReloadSound, this, 400,
                9, 200, handler, 50);
        rifle = new Gun(Assets.ak47, rifleIdle, rifleReload, rifleShoot, rifleShootSound,
                rifleReloadSound, this, 100,
                30, 200, handler, 34);

        shotgun = new Gun(Assets.shotgunSkin, shotgunIdle, shotgunReload, shotgunShoot, shotgunShootSound,
                shotgunReloadSound, this, 800,
                5, 200, handler, 45);

        rpg = new Gun(Assets.rpgSkin, rpgIdle, rpgReload, rpgShoot, rpgShootSound,
                rpgReloadSound, this, 1600,
                1, 9, handler, 1000);
        
        currentGun = pistol;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        xx = this.getX() - offsetX;
        yy = this.getY() - offsetY;

        //Segmento tra hotSpot del mouse e centro del giocatore...
        float wR = MAdapter.x - xx - width / 2;
        float hR = MAdapter.y - yy - height / 2;

        angle = (float) Math.atan(hR / wR);
        if (wR < 0) {
            angle = (float) -Math.PI + angle;
        }

        //Aggiorno l'angolo in modo che il giocatore si giri verso il mirino con la pistola e non col proprio centro
        float angoloPistola = (float) (angle + Math.PI / 4);
        wR = MAdapter.x - (xx + width / 2 + (float) (22 * Math.cos(angoloPistola)));
        hR = MAdapter.y - (yy + height / 2 + (float) (22 * Math.sin(angoloPistola)));
        angle = (float) Math.atan(hR / wR);
        if (wR < 0) {
            angle = (float) -Math.PI + angle;
        }

        at = AffineTransform.getTranslateInstance(xx, yy);
        at.rotate(angle, width / 2, height / 2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(currentGun.getCurrentAnimation().getCurrentFrame(), at, null);
    }

    public float getXX() {
        return xx;
    }

    public float getYY() {
        return yy;
    }

    @Override
    public void animationCycle() {
        //Controllo che sia vivo        
        if (getHealth() <= 0) {
            death();
        }

        if(this.zona.aggiorna(getX(),getY())){
            this.camminiMinimi = Graph.BFS_complete(new Vertex(zona.getIndex()));
        }

        float x = getX();
        float y = getY();
        x += velX;
        y += velY;
        //aggiorno le variabili dello sprite per come funziona collision
        setX(x);
        setY(y);

//          if (x < 0) {
//              x = 2;
//          }
//          if (y < 0) {
//              y = 2;
//          }
        int k = collision(velX, velY, x, y);
        switch (k) {
            case 1:
                x += velX * -1;
                break;
            case 2:
                y += velY * -1;
                break;
            case 3:
                y += velY * -1;
                x += velX * -1;
                break;
            default:
                break;
        }

        setX(x);
        setY(y);

        //scelta direzione dipendente dal tasto premuto
        if (KAdapter.up) {
            velY = -initialVelocity;
        } else if (!KAdapter.down) {
            velY = 0;
        }

        if (KAdapter.down) {
            velY = initialVelocity;
        } else if (!KAdapter.up) {
            velY = 0;
        }

        if (KAdapter.right) {
            velX = initialVelocity;
        } else if (!KAdapter.left) {
            velX = 0;
        }

        if (KAdapter.left) {
            velX = -initialVelocity;
        } else if (!KAdapter.right) {
            velX = 0;
        }

        //scelta arma corrente in base al pulsante 1-2-3
        if (KAdapter.one) {
            currentGun = pistol;
        }
        if (KAdapter.two) {
            currentGun = rifle;
        }
        if (KAdapter.three) {
            currentGun = shotgun;
        }
        if (KAdapter.four) {
            currentGun = rpg;
        }
        //viene premuto R quindi reload
        if (KAdapter.reload && currentGun.getRound() != currentGun.getBulletsPerRound()
                && currentGun.getTotalBullets() > 0) {
            currentGun.reload();
        }

        //viene premuto left(mouse) quindi sparo
        if (MAdapter.left) {
            //Abbiamo aggiunto un parametro random tra -5 e 5 gradi per inserire una inprecisione dell'arma.
            currentGun.shoot((float) (angle + (Math.random() - 0.5) * (Math.PI) / 36), x, y);
        }

        //aggiorno animazione del personaggio
        currentGun.update();
    }

    public Gun getCurrentGun() {
        return currentGun;
    }

    //metodo chiamato dall'esterno che mi infligge danni
    public void hit(int damage) {
        setHealth(getHealth() - damage);
    }

    public boolean isDeath() {
        return isDeath;
    }

    @Override
    public void death() {
        this.isDeath = true;
        handler.removeSprite(this);
        Menu.gameMusic.stopSound();
        Menu.gameMusic = null;
        soundEndGame.playSound();
    }

    public int getPunteggioAttuale() {
        return punteggioAttuale;
    }

    public void updatePunteggio(int punteggioAttuale) {
        this.punteggioAttuale += punteggioAttuale;
    }

    public void updateZombieKilled() {
        this.zombieKilled += 1;
    }

    public int getZombieKilled() {
        return zombieKilled;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public String getName() {
        return name;
    }

    public int getMaximumHealth() {
        return maximumHealth;
    }

    public Map<Vertex, Edge> getCamminiMinimi() {
        return camminiMinimi;
    }

    public boolean isMale(){
        return this.male;
    }
}
