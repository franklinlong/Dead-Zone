/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.sprite.animated;

import deadzone.listeners.KAdapter;
import deadzone.listeners.MAdapter;
import deadzone.graph.Edge;
import deadzone.graph.Graph;
import deadzone.graph.Vertex;
import deadzone.utilities.Animation;
import deadzone.utilities.Assets;
import deadzone.Gun;
import deadzone.Handler;
import deadzone.menu.MapFrame;
import deadzone.utilities.Sound;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Map;
import deadzone.sprite.Sprite;
import deadzone.trap.FireTrap;
import deadzone.trap.HoleTrap;
import deadzone.trap.ShockTrap;
import deadzone.trap.Trap;
import deadzone.trap.WallTrap;
import deadzone.utilities.Zona;

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
    
    private final Sound shockTrapS1, shockTrapS2, shockTrapS3, wallTrapS1, 
            wallTrapS2,fireTrapS,holeTrapS;
    
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
    private boolean trap;
    private final Graph grafo;
    
    private boolean shockTrapActive1=false, shockTrapActive2=false, 
            shockTrapActive3=false, wallTrapActive1=false, wallTrapActive2=false,
            fireTrapActive=false, holeTrapActive1=false, holeTrapActive2=false;
    
    public Player(float x, float y, int vel, int health, Handler handler, String name, boolean male) {
        super(x, y, PLAYERSIZE, PLAYERSIZE, (float)vel, health);
        this.punteggioAttuale = 0;
        this.handler = handler;
        this.male = male;
        this.name = name;
        this.maximumHealth = health;
        this.coins = 200;
        this.trap = true;
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
        pistolReloadSound.changeVolume(-10);
        rifleShootSound = new Sound(Assets.rifleShoot);
        rifleShootSound.changeVolume(-10);
        rifleReloadSound = new Sound(Assets.rifleReloadSound);
        rifleReloadSound.changeVolume(-10);
        shotgunShootSound = new Sound(Assets.shotgunShoot);
        shotgunShootSound.changeVolume(-10);
        shotgunReloadSound = new Sound(Assets.shotgunReloadSound);
        shotgunReloadSound.changeVolume(-10);
        rpgShootSound = new Sound(Assets.rpgShoot);
        rpgShootSound.changeVolume(-10);
        rpgReloadSound = new Sound(Assets.rpgReloadSound);
        rpgReloadSound.changeVolume(-10);
        shockTrapS1 = new Sound(Assets.shockTrap1);
        shockTrapS2 = new Sound(Assets.shockTrap2);
        shockTrapS3 = new Sound(Assets.shockTrap3);
        
        fireTrapS = new Sound(Assets.fireTrap);
        
        wallTrapS1 = new Sound(Assets.wallTrap);
        wallTrapS2 = new Sound(Assets.wallTrap);

        holeTrapS = new Sound(Assets.holeTrap);
        
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
                1, 9, handler, 700);
        
        currentGun = pistol;
        
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
            for(Sprite s : handler.getBloods())
                if(s instanceof Trap)
                    ((Trap) s).getSound().stopSound();
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
                if (this.coins >= 3 && !this.shockTrapActive1) {
                    handler.addSprite(new ShockTrap((float) 564, (float) 2253, 155, 24, handler, true, 800, this.shockTrapS1, 1));
                    this.updateCoins(-3);
                    this.shockTrapActive1=true;
                }
                    break;
            case 115:
                if (this.coins >= 3 && !this.shockTrapActive2) {
                    handler.addSprite(new ShockTrap((float) 560, (float) 2970, 155, 24, handler, true, 800, this.shockTrapS2, 2));
                    this.updateCoins(-3);
                    this.shockTrapActive2=true;
                    break;
                }
            case 49:
                if (this.coins >= 3 && !this.shockTrapActive3) {
                    handler.addSprite(new ShockTrap((float) 221, (float) 630, 155, 24, handler, false, 800, this.shockTrapS3, 3));
                    this.updateCoins(-3);
                    this.shockTrapActive3=true;
                }
                break;
            case 255:
                if (this.coins >= 5 && !this.fireTrapActive) {
                    for(int i=0;i<7;i++)
                        handler.addSprite(new FireTrap((float) 170 + 110*i, (float) 1370, 120, 66, handler, true, 740, this.fireTrapS));
                    for(int i=7;i<14;i++)
                        handler.addSprite(new FireTrap((float) 170 + 110*i, (float) 1370, 120, 66, handler, true, 760, this.fireTrapS));
                    for(int i=0;i<7;i++)
                        handler.addSprite(new FireTrap((float) 1730, (float) 1384 + 66*i, 120, 66, handler, false, 780, this.fireTrapS));
                    for(int i=0;i<7;i++)
                        handler.addSprite(new FireTrap((float) 170 + 110*i, (float) 1860, 120, 66, handler ,true, 820, this.fireTrapS));    
                    for(int i=7;i<14;i++)
                        handler.addSprite(new FireTrap((float) 170 + 110*i, (float) 1860, 120, 66, handler ,true, 800, this.fireTrapS));    
                    for(int i=0;i<7;i++)
                        handler.addSprite(new FireTrap((float) 170, (float) 1384 + 66*i, 120, 66, handler, false, 840, this.fireTrapS));
                    this.updateCoins(-5);
                    this.fireTrapActive=true;
                }
                break;
            case 150:
                if (this.coins >= 1 &&  !this.wallTrapActive1) {
                    this.grafo.rimuoviCorridoio();
                    handler.addSprite(new WallTrap((float) 2395, (float) 1890, 200, 30, handler, true, 3000, this.wallTrapS1, 1));
                    this.updateCoins(-1);
                    this.wallTrapActive1=true;
                }
                break;
            case 155:
                if (this.coins >= 7 && !this.holeTrapActive1) {
                    handler.addSprite(new HoleTrap((float) 2670, (float) 2240, 60, 60, handler, 700, this.holeTrapS, 1));
                    this.updateCoins(-7);
                    this.holeTrapActive1=true;
                break;
                }
            case 99:
                if (this.coins >= 7 && !this.holeTrapActive2) {
                    handler.addSprite(new HoleTrap((float) 1440, (float) 1020, 60, 60, handler, 700, this.holeTrapS, 2));
                    this.updateCoins(-7);
                    this.holeTrapActive2=true;
                }
                break;
            case 189:
                if (this.coins >= 1 && !this.wallTrapActive2) {
                    this.grafo.rimuoviEntrataLabirinto();
                    handler.addSprite(new WallTrap((float) 2525, (float) 1217, 200, 30, handler, true, 2000, this.wallTrapS2, 2));
                    handler.addSprite(new WallTrap((float) 2750, (float) 545, 200, 30, handler, true, 2000, this.wallTrapS2, 2));
                    handler.addSprite(new WallTrap((float) 1980, (float) 727, 200, 30, handler, false, 2000, this.wallTrapS2, 2));
                    this.updateCoins(-1);
                    this.wallTrapActive2=true;
                }
                break;
        }
        
    }

    public void setShockTrapActive1(boolean shockTrapActive1) {
        this.shockTrapActive1 = shockTrapActive1;
    }

    public void setShockTrapActive2(boolean shockTrapActive2) {
        this.shockTrapActive2 = shockTrapActive2;
    }

    public void setShockTrapActive3(boolean shockTrapActive3) {
        this.shockTrapActive3 = shockTrapActive3;
    }

    public void setWallTrapActive1(boolean wallTrapActive1) {
        if(this.wallTrapActive1 && !wallTrapActive1)
            this.grafo.inserisciCorridoio();
        this.wallTrapActive1 = wallTrapActive1;
    }

    public void setWallTrapActive2(boolean wallTrapActive2) {
        if(this.wallTrapActive2 && !wallTrapActive2)
            this.grafo.inserisciEntrataLabirinto();
        this.wallTrapActive2 = wallTrapActive2;
    }

    public void setFireTrapActive(boolean fireTrapActive) {
        this.fireTrapActive = fireTrapActive;
    }

    public void setHoleTrapActive1(boolean holeTrapActive1) {
        this.holeTrapActive1 = holeTrapActive1;
    }

    public void setHoleTrapActive2(boolean holeTrapActive2) {
        this.holeTrapActive2 = holeTrapActive2;
    }
    
    
}
