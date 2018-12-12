/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite.animated;

import deadzone.graph.Edge;
import deadzone.graph.Graph;
import deadzone.graph.Vertex;
import utilities.Animation;
import utilities.Assets;
import deadzone.Gun;
import deadzone.Handler;
import deadzone.menu.MapFrame;
import utilities.Sound;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.util.Map;
import javax.swing.Timer;
import listeners.*;
import trap.FireTrap;
import trap.HoleTrap;
import trap.ShockTrap;
import trap.WallTrap;
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

    
    // trap
    
    private final Sound shockTrapS, wallTrapS, fireTrapS;
    
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
    private final int maximumHealth;
    private int coins;
    private Graphics2D g2;
        
    private Map<Vertex,Edge> camminiMinimi;
    //Timer trap
    Timer shockTrap1,shockTrap2,shockTrap3;
    Timer fireTrap,wallTrap1,wallTrap2, holeTrap1,holeTrap2;
    Timer durataWall1, durataWall2;
    private boolean trap;
    private final Graph grafo;

    public Player(float x, float y, int vel, int health, Handler handler, String name, boolean male) {
        super(x, y, PLAYERSIZE, PLAYERSIZE, (float)vel, health);
        this.punteggioAttuale = 0;
        this.handler = handler;
        this.male = male;
        this.name = name;
        this.maximumHealth = health;
        this.coins = 11;
        this.trap = true;
        //g2.drawString("press Q to activate the trap", getX()-offsetX-250, getY()-offsetY);
        
        this.zona = new Zona(getX(), getY());
        grafo = new Graph();
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
            
            rpgIdle = new Animation(Assets.femalerpgIdle, 20);
            rpgReload = new Animation(Assets.femalerpgReload, 100);
            rpgShoot = new Animation(Assets.femalerpgShootAnim, 80);
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
        
        shockTrapS = new Sound(Assets.shockTrap);
        fireTrapS = new Sound(Assets.fireTrap);
        wallTrapS = new Sound(Assets.wallTrap);
        
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
                rpgReloadSound, this, 1200,
                1, 9, handler, 1000);
        
        currentGun = pistol;
        
        shockTrap1 = new Timer(10000, (ActionEvent ae) -> {
            shockTrap1.stop();
            shockTrapS.stopSound();
        });
        
        shockTrap2 = new Timer(10000, (ActionEvent ae) -> {
            //stop musica
            shockTrap2.stop();
            shockTrapS.stopSound();

        });
        
        shockTrap3 = new Timer(10000, (ActionEvent ae) -> {
            shockTrap3.stop();    
            shockTrapS.stopSound();

        });

        fireTrap = new Timer(10000, (ActionEvent ae) -> {
            fireTrap.stop();    
            fireTrapS.stopSound();

        });
        
        wallTrap1 = new Timer(10000, (ActionEvent ae) -> {
            wallTrap1.stop();    
            wallTrapS.stopSound();
        });
        
        wallTrap2 = new Timer(10000, (ActionEvent ae) -> {
            wallTrap2.stop();    
            wallTrapS.stopSound();
        });
        
        holeTrap1 = new Timer(10000, (ActionEvent ae) -> {
            holeTrap1.stop();    
            //stop musica
        });
        
        holeTrap2 = new Timer(10000, (ActionEvent ae) -> {
            holeTrap2.stop();    
            //stop musica
        });
        
        durataWall1 = new Timer(10000, (ActionEvent ae) -> {
            durataWall1.stop();
            this.grafo.inserisciCorridoio();
        });
        
        durataWall2 = new Timer(10000, (ActionEvent ae) -> {
            durataWall2.stop();
            this.grafo.inserisciEntrataLabirinto();
        });
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        Graphics2D g2d = (Graphics2D) g;

        //Controllo se mi trovo in vicinanza di una trappola
        if(controlloAction()){
            g2d.drawImage(Assets.actionImg, (int) (getX()-offsetX-115), (int) (getY()-offsetY) - 30, null);
        }
        
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
        g2d = (Graphics2D) g;
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
        
        if(KAdapter.action){
            int pixel = mapRGB.getRGB((int)getX()+width/2,(int)getY()+height/2);
            pixel = (pixel >> 8) & 0xff;
            activeTrap(pixel);

        }

        //viene premuto left(mouse) quindi sparo
        if (MAdapter.left) {
            //Abbiamo aggiunto un parametro random tra -5 e 5 gradi per inserire una inprecisione dell'arma.
            currentGun.shoot((float) (angle + (Math.random() - 0.5) * (Math.PI) / 36), x, y);
        }

        //viene premuto tasto destro del mouse quindi reload
        if(MAdapter.right && currentGun.getRound() != currentGun.getBulletsPerRound()
                && currentGun.getTotalBullets() > 0){
            currentGun.reload();
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
        MapFrame.gameMusic.stopSound();
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

    public int getCoins() {
        return coins;
    }
    
    public void updateCoins(int coins){
        this.coins += coins;
    }    
    
    private boolean controlloAction(){
        int pixel = mapRGB.getRGB((int)getX()+width/2,(int)getY()+height/2);
        pixel = (pixel >> 8) & 0xff;
        return pixel==130 || pixel == 115 || pixel == 49 || pixel == 255 || pixel == 150 || pixel == 155 || pixel == 99 || pixel == 189;
    }
    
    private void activeTrap(int pixel){
        
        switch (pixel) {
            case 130:
                if (this.coins >= 2 && !shockTrap1.isRunning()) {
                    handler.addSprite(new ShockTrap((float) 564, (float) 2253, 155, 24, handler, true, shockTrap1));
                    shockTrap1.start();
                    shockTrapS.loopSound();
                    //start musica
                    this.updateCoins(-2);
                }
                break;

            case 115:
                if (this.coins >= 2 && !shockTrap2.isRunning()) {
                    handler.addSprite(new ShockTrap((float) 560, (float) 2970, 155, 24, handler, true, shockTrap2));
                    shockTrap2.start();
                    shockTrapS.loopSound();
                    //start musica
                    this.updateCoins(-2);
                }
                break;

            case 49:
                if (this.coins >= 2 && !shockTrap3.isRunning()) {
                    handler.addSprite(new ShockTrap((float) 221, (float) 630, 155, 24, handler, false, shockTrap3));
                    shockTrap3.start();
                    //start musica
                    this.updateCoins(-2);
                    shockTrapS.loopSound();
                }
                break;
            case 255:
                if (this.coins >= 2 && !fireTrap.isRunning()) {
                    for(int i=0;i<14;i++)
                        handler.addSprite(new FireTrap((float) 170 + 110*i, (float) 1400, 120, 66, handler, fireTrap, true));
                    for(int i=0;i<14;i++)
                        handler.addSprite(new FireTrap((float) 170 + 110*i, (float) 1860, 120, 66, handler, fireTrap, true));    
                    for(int i=0;i<5;i++)
                        handler.addSprite(new FireTrap((float) 170, (float) 1450 + 66*i, 120, 66, handler, fireTrap, false));
                    for(int i=0;i<5;i++)
                        handler.addSprite(new FireTrap((float) 1730, (float) 1450 + 66*i, 120, 66, handler, fireTrap, false));
                    fireTrap.start();
                    this.updateCoins(-2);
                    fireTrapS.loopSound();
                }
                break;
            case 150:
                if (this.coins >= 2 && !wallTrap1.isRunning()) {
                    this.grafo.rimuoviCorridoio();
                    handler.addSprite(new WallTrap((float) 2395, (float) 1890, 200, 30, handler, true, durataWall1));
                    wallTrap1.start();
                    durataWall1.start();
                    this.updateCoins(-2);
                    wallTrapS.playSound();
                }
                break;
            case 155:
                if (this.coins >= 2 && !holeTrap1.isRunning()) {
                    handler.addSprite(new HoleTrap((float) 2670, (float) 2240, 60, 60, handler, holeTrap1));
                    holeTrap1.start();
                    this.updateCoins(-2);
                }
                break;
            case 99:
                if (this.coins >= 2 && !holeTrap2.isRunning()) {
                    handler.addSprite(new HoleTrap((float) 1440, (float) 1020, 60, 60, handler, holeTrap2));
                    holeTrap2.start();
                    this.updateCoins(-2);
                }
                break;
            case 189:
                if (this.coins >= 2 && !wallTrap2.isRunning()) {
                    this.grafo.rimuoviEntrataLabirinto();
                    handler.addSprite(new WallTrap((float) 2525, (float) 1217, 200, 30, handler, true, durataWall2));
                    handler.addSprite(new WallTrap((float) 2750, (float) 545, 200, 30, handler, true, durataWall2));
                    handler.addSprite(new WallTrap((float) 1980, (float) 727, 200, 30, handler, false, durataWall2));

                    durataWall2.start();
                    wallTrap2.start();
                    this.updateCoins(-2);
                    wallTrapS.playSound();
                }
                break;
        }
        
    }
}
