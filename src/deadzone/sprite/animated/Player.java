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
import deadzone.utilities.Database;
import deadzone.utilities.Zona;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Timer;

/**
 *
 * @author giova
 */
public abstract class Player extends AnimatedSprite {
    
    public static final int PLAYERSIZE = 60;
    protected Zona zona;

    //animations
    protected Animation shotgunIdle, shotgunReload, shotgunShoot;
    protected Animation pistolIdle, pistolReload, pistolShoot;
    protected Animation rifleIdle, rifleReload, rifleShoot;
    protected Animation rpgIdle, rpgReload, rpgShoot;

    // sounds
    protected Sound rifleShootSound, rifleReloadSound;
    protected Sound pistolShootSound, pistolReloadSound;
    protected Sound shotgunShootSound, shotgunReloadSound;
    protected Sound rpgShootSound, rpgReloadSound;

    // trap
    private final Sound shockTrapS1, shockTrapS2, shockTrapS3, wallTrapS1,
            wallTrapS2, fireTrapS, holeTrapS;

    //Handler che serve per rimuovere il player quando muore
    protected Handler handler;
    
    protected Gun currentGun;
    protected Gun pistol, rifle, shotgun, rpg;
    protected Gun[] allGuns = new Gun[4];
    protected float xx, yy;
    private boolean isDeath;
    protected boolean male;
    
    private int punteggioAttuale;
    private int onlineID;
    private final String name;
    private int zombieKilled;
    private final int maximumHealth;
    protected int coins;
    private Graphics2D g2;
    
    protected Map<Vertex, Edge> camminiMinimi;
    private boolean trap;
    private final Graph grafo;
    
    private boolean shockTrapActive1 = false, shockTrapActive2 = false,
            shockTrapActive3 = false, wallTrapActive1 = false, wallTrapActive2 = false,
            fireTrapActive = false, holeTrapActive1 = false, holeTrapActive2 = false,
            flagShop = false;
    private Timer shopTimer;
    
    //private Window window;
    public Player(float x, float y, int vel, int health, String name) {
        super(x, y, PLAYERSIZE, PLAYERSIZE, (float) vel, health);
        
        this.punteggioAttuale = 0;
        this.name = name;
        this.maximumHealth = health;
        this.coins = 5;
        this.trap = true;
        this.zona = new Zona(getX(), getY());
        grafo = new Graph();
        camminiMinimi = Graph.BFS_complete(new Vertex(zona.getIndex()));
        
        
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
        
        this.shopTimer = new Timer(1000, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                flagShop = false;
                shopTimer.stop();
            }

        });
        
        if (Database.online) {
            this.inserisciOnline();
        }
    }
    
    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        Graphics2D g2d = (Graphics2D) g;

        //Controllo se mi trovo in vicinanza di una trappola
        switch (controlloAction()) {
            case 1:
                g2d.drawImage(Assets.actionImg, (int) (getX() - offsetX - 115), (int) (getY() - offsetY) - 30, null);
                break;
            case 2:
                g2d.drawImage(Assets.alreadyActive, (int) (getX() - offsetX - 115), (int) (getY() - offsetY) - 40, null);
                break;
            case 3:
                g2d.drawImage(Assets.noCoins, (int) (getX() - offsetX - 25), (int) (getY() - offsetY) - 30, null);
                break;
            case 5: //Negozio
                g2d.drawImage(Assets.actionImg, (int) (getX() - offsetX - 25), (int) (getY() - offsetY) - 30, null);
                break;
        }
        
        xx = this.getX() - offsetX;
        yy = this.getY() - offsetY;

        angleRotation(MAdapter.x,MAdapter.y,xx,yy);
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
            for (Sprite s : handler.getBloods()) {
                if (s instanceof Trap) {
                    ((Trap) s).getSound().stopSound();
                }
            }
            death();
        }
        if(this.currentGun.getSkin()==Assets.pistolSkin && this.currentGun.getTotalBullets()==0) this.currentGun.setTotalBullets(2000);
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
        
        //System.out.println("ciao2: "+velY);
        //scelta direzione dipendente dal tasto premuto
        if (KAdapter.up) {
            velY = -initialVelocity;
        } else if (!KAdapter.down) {
            velY = 0;
        }
        
        if (KAdapter.down) {
            velY = initialVelocity;
        } else if (!KAdapter.up) {
         //   System.out.println("ciao");
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
        
        if (KAdapter.action) {
            int pixel = mapRGB.getRGB((int) getX() + width / 2, (int) getY() + height / 2);
            pixel = (pixel >> 8) & 0xff;
            activeTrap(pixel);
            
        }

        //viene premuto left(mouse) quindi sparo
        if (MAdapter.left) {
            //Abbiamo aggiunto un parametro random tra -5 e 5 gradi per inserire una inprecisione dell'arma.
            currentGun.shoot((float) (angle + (Math.random() - 0.5) * (Math.PI) / 36), x, y);
        }

        //viene premuto tasto destro del mouse quindi reload
        if (MAdapter.right && currentGun.getRound() != currentGun.getBulletsPerRound()
                && currentGun.getTotalBullets() > 0) {
            currentGun.reload();
        }

        //aggiorno animazione del personaggio
        currentGun.update();
    }
    
    public Gun getCurrentGun() {
        return currentGun;
    }
    
    public Gun[] getAllGuns() {
        return allGuns;
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
        this.aggiornaDB();
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
    
    public int getCoins() {
        return coins;
    }
    
    public void updateCoins(int coins) {
        this.coins += coins;
    }

    //ritorna 1 se deve può comprare, 3 se mancano i soldi, 2 se è già attiva, -1 se niente
    private int controlloAction() {
        int pixel = mapRGB.getRGB((int) getX() + width / 2, (int) getY() + height / 2);
        pixel = (pixel >> 8) & 0xff;
        switch (pixel) {
            case 130:
                if (!this.shockTrapActive1 && this.coins >= 3) {
                    return 1;
                } else if (this.shockTrapActive1) {
                    return 2;
                } else {
                    return 3;
                }
            case 115:
                if (!this.shockTrapActive2 && this.coins >= 3) {
                    return 1;
                } else if (this.shockTrapActive2) {
                    return 2;
                } else {
                    return 3;
                }
            case 49:
                if (!this.shockTrapActive3 && this.coins >= 3) {
                    return 1;
                } else if (this.shockTrapActive3) {
                    return 2;
                } else {
                    return 3;
                }
            case 255:
                if (!this.fireTrapActive && this.coins >= 5) {
                    return 1;
                } else if (this.fireTrapActive) {
                    return 2;
                } else {
                    return 3;
                }
            case 150:
                if (!this.wallTrapActive1 && this.coins >= 1) {
                    return 1;
                } else if (this.wallTrapActive1) {
                    return 2;
                } else {
                    return 3;
                }
            case 155:
                if (!this.holeTrapActive1 && this.coins >= 7) {
                    return 1;
                } else if (this.holeTrapActive1) {
                    return 2;
                } else {
                    return 3;
                }
            
            case 99:
                if (!this.holeTrapActive2 && this.coins >= 7) {
                    return 1;
                } else if (this.holeTrapActive2) {
                    return 2;
                } else {
                    return 3;
                }
            
            case 189:
                if (!this.wallTrapActive2 && this.coins >= 1) {
                    return 1;
                } else if (this.wallTrapActive2) {
                    return 2;
                } else {
                    return 3;
                }
            case 64:
                if(this.coins >= 1){
                    return 5;
                }
                else{
                    return 3;
                }
            default:
                return -1;
        }
    }
    
    public void aggiornaDB() {
        if (Database.online) {
            
            //new connectionThread(this.name,this.punteggioAttuale).start();
            Database.CancellaOnline(onlineID);
        }
       
    }
    
    public void inserisciOnline() {
        ThreadInserisciOnline t = new ThreadInserisciOnline();
        t.start();
    }
    
    public int getOnlineID() {
        return onlineID;
    }
    
    public void setOnlineID(int onlineID) {
        this.onlineID = onlineID;
    }
    
    protected void activeTrap(int pixel) {
        
        switch (pixel) {
            case 130:
                if (this.coins >= 3 && !this.shockTrapActive1) {
                    handler.addSprite(new ShockTrap((float) 564, (float) 2253, 155, 24, handler, true, 800, this.shockTrapS1, 1));
                    this.updateCoins(-3);
                    this.shockTrapActive1 = true;
                }
                break;
            case 115:
                if (this.coins >= 3 && !this.shockTrapActive2) {
                    handler.addSprite(new ShockTrap((float) 560, (float) 2970, 155, 24, handler, true, 800, this.shockTrapS2, 2));
                    this.updateCoins(-3);
                    this.shockTrapActive2 = true;
                    break;
                }
            case 49:
                if (this.coins >= 3 && !this.shockTrapActive3) {
                    handler.addSprite(new ShockTrap((float) 221, (float) 630, 155, 24, handler, false, 800, this.shockTrapS3, 3));
                    this.updateCoins(-3);
                    this.shockTrapActive3 = true;
                }
                break;
            case 255:
                if (this.coins >= 5 && !this.fireTrapActive) {
                    for (int i = 0; i < 7; i++) {
                        handler.addSprite(new FireTrap((float) 170 + 110 * i, (float) 1370, 120, 66, handler, true, 740, this.fireTrapS));
                    }
                    for (int i = 7; i < 14; i++) {
                        handler.addSprite(new FireTrap((float) 170 + 110 * i, (float) 1370, 120, 66, handler, true, 760, this.fireTrapS));
                    }
                    for (int i = 0; i < 7; i++) {
                        handler.addSprite(new FireTrap((float) 1730, (float) 1384 + 66 * i, 120, 66, handler, false, 780, this.fireTrapS));
                    }
                    for (int i = 0; i < 7; i++) {
                        handler.addSprite(new FireTrap((float) 170 + 110 * i, (float) 1860, 120, 66, handler, true, 820, this.fireTrapS));
                    }
                    for (int i = 7; i < 14; i++) {
                        handler.addSprite(new FireTrap((float) 170 + 110 * i, (float) 1860, 120, 66, handler, true, 800, this.fireTrapS));
                    }
                    for (int i = 0; i < 7; i++) {
                        handler.addSprite(new FireTrap((float) 170, (float) 1384 + 66 * i, 120, 66, handler, false, 840, this.fireTrapS));
                    }
                    this.updateCoins(-5);
                    this.fireTrapActive = true;
                }
                break;
            case 150:
                if (this.coins >= 1 && !this.wallTrapActive1) {
                    this.grafo.rimuoviCorridoio();
                    this.camminiMinimi = Graph.BFS_complete(new Vertex(this.zona.getIndex()));
                    handler.addSprite(new WallTrap((float) 2395, (float) 1890, 200, 30, handler, true, 3000, this.wallTrapS1, 1));
                    this.updateCoins(-1);
                    this.wallTrapActive1 = true;
                }
                break;
            case 155:
                if (this.coins >= 7 && !this.holeTrapActive1) {
                    handler.addSprite(new HoleTrap((float) 2670, (float) 2240, 60, 60, handler, 700, this.holeTrapS, 1));
                    this.updateCoins(-7);
                    this.holeTrapActive1 = true;
                    break;
                }
            case 99:
                if (this.coins >= 7 && !this.holeTrapActive2) {
                    handler.addSprite(new HoleTrap((float) 1440, (float) 1020, 60, 60, handler, 700, this.holeTrapS, 2));
                    this.updateCoins(-7);
                    this.holeTrapActive2 = true;
                }
                break;
            case 189:
                if (this.coins >= 1 && !this.wallTrapActive2) {
                    this.grafo.rimuoviEntrataLabirinto();
                    this.camminiMinimi = Graph.BFS_complete(new Vertex(this.zona.getIndex()));
                    handler.addSprite(new WallTrap((float) 2525, (float) 1217, 200, 30, handler, true, 1500, this.wallTrapS2, 2));
                    handler.addSprite(new WallTrap((float) 2750, (float) 545, 200, 30, handler, true, 1500, this.wallTrapS2, 2));
                    handler.addSprite(new WallTrap((float) 1980, (float) 727, 200, 30, handler, false, 1500, this.wallTrapS2, 2));
                    this.updateCoins(-1);
                    this.wallTrapActive2 = true;
                }
                break;
            case 64: //SHOP
                if(this.coins >= 1 && !flagShop){
                    this.flagShop = true;
                    this.shopTimer.start();
                    this.updateCoins(-1);
                    if(currentGun.getSkin() == Assets.ak47)
                        this.currentGun.setTotalBullets(currentGun.getTotalBullets() + 100);
                    else if(currentGun.getSkin() == Assets.rpgSkin)
                        this.currentGun.setTotalBullets(currentGun.getTotalBullets() + 5);
                    else if(currentGun.getSkin() == Assets.shotgunSkin)
                        this.currentGun.setTotalBullets(currentGun.getTotalBullets() + 30);
                    
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
        if(this.wallTrapActive1 && !wallTrapActive1){
            this.grafo.inserisciCorridoio();
            this.camminiMinimi = Graph.BFS_complete(new Vertex(this.zona.getIndex()));
        }
        this.wallTrapActive1 = wallTrapActive1;
    }
    
    public void setWallTrapActive2(boolean wallTrapActive2) {
        if(this.wallTrapActive2 && !wallTrapActive2){
            this.grafo.inserisciEntrataLabirinto();
            this.camminiMinimi = Graph.BFS_complete(new Vertex(this.zona.getIndex()));
        }
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
    
    private class ThreadInserisciOnline extends Thread {
        
        @Override
        public void run() {
            System.out.println("Mi sto inserendo online");
            onlineID = Database.InserisciOnline(name);
        }
    }
    
    public void setHandler(Handler h){
        handler = h;
        pistol.setHandler(h);
        rifle.setHandler(h);
        shotgun.setHandler(h);
        rpg.setHandler(h);
    }
    
    public void angleRotation(float x, float y, float xx, float yy){
        //Segmento tra hotSpot del mouse e centro del giocatore...
        float wR = x - xx - width / 2;
        float hR = y - yy - height / 2;

        angle = (float) Math.atan(hR / wR);
        if (wR < 0) {
            angle = (float) -Math.PI + angle;
        }

        //Aggiorno l'angolo in modo che il giocatore si giri verso il mirino con la pistola e non col proprio centro
        float angoloPistola = (float) (angle + Math.PI / 4);
        wR = x - (xx + width / 2 + (float) (22 * Math.cos(angoloPistola)));
        hR = y - (yy + height / 2 + (float) (22 * Math.sin(angoloPistola)));
        angle = (float) Math.atan(hR / wR);
        if (wR < 0) {
            angle = (float) - Math.PI + angle;
        }
    }
    
    public void setWindow(Window window){
        window.addWindowListener((new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (Database.online) {
                    Database.CancellaOnline(onlineID);
                }
            }
        }));
    }
    
    public boolean isMale(){
        return this.male;
    }
    
}
