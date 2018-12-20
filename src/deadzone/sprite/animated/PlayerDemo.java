/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.sprite.animated;

import deadzone.Gun;
import deadzone.Waves;
import deadzone.WavesDemo;
import deadzone.graph.Graph;
import deadzone.graph.Vertex;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;
import deadzone.sprite.SpriteInterface;
import deadzone.trap.Trap;
import deadzone.utilities.Animation;
import deadzone.utilities.Assets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author casang
 */
public class PlayerDemo extends PlayerFactory{
    
    
    private String move;
    private Waves wave;
    private List<SpriteInterface> zombies; 
    private boolean next;
    private Animation demoImages;
    public static boolean FlagWaves = false;
    
    public PlayerDemo(float x, float y, int vel, int health){
        super(x, y, vel, health);
        this.next = false;
        this.initPlayer();
    }
    
    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        xx = this.getX() - offsetX;
        yy = this.getY() - offsetY;
        
        if(!next){
            int xScreen = Toolkit.getDefaultToolkit().getScreenSize().width*4/5;
            int yScreen = Toolkit.getDefaultToolkit().getScreenSize().height;
            nextScreen(g,(int)(xScreen-650),(int)(yScreen-370));
        }
        
        if (visible){
            at = AffineTransform.getTranslateInstance(xx, yy);
            at.rotate(angle, width / 2, height / 2);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(currentGun.getCurrentAnimation().getCurrentFrame(), at, null);
        }
        
    }
    
    @Override
    public void animationCycle() {
        if(next){   
            
            muovi();

            if (visible){
                //Controllo che sia vivo        
                if (getHealth() <= 0) {
                    for (Iterator<SpriteInterface> it = handler.getTraps().iterator(); it.hasNext();) {
                        Trap t =(Trap) it.next();
                        t.getSound().stopSound();
                    }
                    death();
                }

                if(this.currentGun.getSkin()==Assets.pistolSkin && this.currentGun.getTotalBullets()==0) 
                    this.currentGun.setTotalBullets(2000);

                if(this.zona.aggiorna(getX(),getY()))
                    this.camminiMinimi = Graph.BFS_complete(new Vertex(zona.getIndex()));
            
            }
            
            burattinaio();

            //aggiorno animazione del personaggio
            currentGun.update();
            

        } 
    }

    @Override
    public void initPlayer() {
        this.coins = 5;
        this.visible = false; 
        
        this.male = true;
        
        this.demoImages = new Animation(Assets.demoImages, 20);
        
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
        
        pistol = new Gun(Assets.pistolSkin, pistolIdle, pistolReload, pistolShoot, pistolShootSound,
                pistolReloadSound, this, 400,
                9, 200, 50);
        rifle = new Gun(Assets.ak47, rifleIdle, rifleReload, rifleShoot, rifleShootSound,
                rifleReloadSound, this, 100,
                30, 200, 34);

        shotgun = new Gun(Assets.shotgunSkin, shotgunIdle, shotgunReload, shotgunShoot, shotgunShootSound,
                shotgunReloadSound, this, 800,
                5, 200, 45);
        
        rpg = new Gun(Assets.rpgSkin, rpgIdle, rpgReload, rpgShoot, rpgShootSound,
                rpgReloadSound, this, 1200,
                1, 20, 700);
        
        currentGun = pistol;
        
        allGuns[0] = pistol;
        allGuns[1] = rifle;
        allGuns[2] = shotgun;
        allGuns[3] = rpg;    
    }

    @Override
    public boolean isMale() {
        return true;
    }

    @Override
    public void setName(String name) {
        this.name = "playerDemo";
    }
    
    public void setWave(Waves wave){
        this.wave = wave;
    }
    
    
    public void setWindowDemo(JPanel window){
        window.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                if(e.getKeyChar() == ' ' && !next){
                    next = true;
                    demoImages.update();
                }
            }
        });
    }
    
    private void nextScreen(Graphics g, int x, int y){
        Graphics2D g2d = (Graphics2D) g;

        //Aggiornare x e y in base all'immagine da far uscire
        if(demoImages.getIndex() == 0){
            x = Toolkit.getDefaultToolkit().getScreenSize().width*4/5 *1/2 - 640/2;
            y = Toolkit.getDefaultToolkit().getScreenSize().height/2 - 360/2;
        }
        else if(demoImages.getIndex() == 1){
            x = 0;
            y = 10;
        }
        else if(demoImages.getIndex() == 2 || demoImages.getIndex() == 3){
            x = 0;
            y = 360;
        }
        else if(demoImages.getIndex() == 4){
            x = 0;
            y = Toolkit.getDefaultToolkit().getScreenSize().height - 360;
            g2d.drawImage(Assets.unioneImg,  0, -160, null);
        }
        else if(this.isDeath()){
            g2d.drawImage(Assets.demoImages[19], x,y, null);
        }
        
        
        g2d.drawImage(demoImages.getCurrentFrame(), x,y, null);
        
    }
    
    JFrame f;
    private boolean flagTrap = false;
    boolean notFirst = false;
    private void burattinaio(){
        
        switch(demoImages.getIndex()){
            case 4:
                next = false;
                break;
            case 5:
                next = false;
                break;
            case 6:
                this.velX = 7;
                if(this.getX() > 2700){
                    this.velX = 0;
                    this.velY = 7;
                }
                if(this.getY() > 700){
                    this.velY = 0;
                    next = false;
                }
                break;
            case 7:
                next = false;
                break;
            case 8:
                if(this.getY() < 2700 && this.getX() > 2700)
                    this.velY = 7;
                else if(this.getY() >= 2700 && this.getX() > 600){
                    this.velX = -7;
                    this.velY = 0;
                } else if(this.getY() > 1670){
                    this.velX = 0;
                    this.velY = -7;
                } else if(this.getY() <= 1670){
                    this.velY = 0;
                    next = false;
                }
                break;
            case 10:
                if(this.getY() > 600){
                    this.velY = -7;
                }
                else{
                    this.velY = 0;
                    next = false;
                }
                break;
            case 11:
                if(this.getY() < 1600)
                    this.velY = 7;
                if(this.getX() < 1600)
                    this.velX = 7;
                
                if(this.getX() >= 1600 && this.getY() >= 1600){
                    this.visible = true;
                    this.velX = 0;
                    this.velY = 0;
                    next = false;
                }
                break;
            case 16:
                if(!notFirst){
                    Waves w = handler.getWaves();
                    Thread t = new Thread(w);
                    t.start();
                    notFirst = true;
                }
                if(FlagWaves){
                    this.velX = 0;
                    this.velY = 0;
                    next = false;
                    notFirst = false;
                }
                break;
            case 17:
                if(!notFirst){
                    currentGun = rifle;
                    notFirst = true;
                    synchronized(WavesDemo.FW){
                        FlagWaves = false;
                        WavesDemo.FW.notifyAll();
                    }
                }
                
                if(FlagWaves){
                    this.velX = 0;
                    this.velY = 0;
                    next = false;
                    notFirst = false;
                }
                break;
            case 18:
                if(!notFirst){
                    currentGun = shotgun;
                    notFirst = true;
                    flagTrap = true;
                    synchronized(WavesDemo.FW){
                        FlagWaves = false;
                        WavesDemo.FW.notifyAll();
                    }
                }
                
                if((this.getX() > 1445 && this.getX() < 1450) && (this.getY() < 1605 && this.getY() > 1600)){
                    
                    activeTrap(255);
                    if(!this.fireTrapActive)
                        flagTrap = false;
                    else{
                        this.velX = 0;
                        this.velY = 0;
                    }
                        
                }else if(flagTrap){
                    if(this.getX() > 1448)
                        velX = -getInitialVelocity();
                    if(this.getX() < 1448)
                        velX = getInitialVelocity();
                    if(this.getY() > 1604)
                        velY = -getInitialVelocity();
                    if(this.getY() < 1604)
                        velY = getInitialVelocity();
                }
                
                if(FlagWaves){
                    this.velX = 0;
                    this.velY = 0;
                    next = false;
                    notFirst = false;
                }
                break;
            case 19:
                if(!notFirst){
                    flagTrap = false;
                    currentGun = rpg;
                    notFirst = true;
                    synchronized(WavesDemo.FW){
                        FlagWaves = false;
                        WavesDemo.FW.notifyAll();
                    }
                }
                
                if(FlagWaves && notFirst){
                    this.velX = 0;
                    this.velY = 0;
                    notFirst = false;
                    
                    
                    f = new JFrame();
                    f.setResizable(false);
                    f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
                    f.setUndecorated(true);
                    f.setAlwaysOnTop(true);
                    f.setLocationRelativeTo(null);
                    
                    JLabel l = new JLabel();
                    l.setSize(Toolkit.getDefaultToolkit().getScreenSize());
                    l.setText("");
                    l.setIcon(new ImageIcon(Assets.schermoNero));
                    f.getContentPane().add(l);
                    
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(PlayerDemo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            f.setVisible(true);
                            
                        }
                    }).start();
                    
                    this.hit(this.getHealth()-200);
                    
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(6000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(PlayerDemo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            f.setVisible(false);
                            f.dispose();
//                            try {
//                                new Robot().keyPress(KeyEvent.VK_SPACE);
//                                
//                            } catch (AWTException ex) {
//                                Logger.getLogger(PlayerDemo.class.getName()).log(Level.SEVERE, null, ex);
//                            }
                        }
                    }).start();

                    synchronized(WavesDemo.FW){
                        FlagWaves = false;
                        WavesDemo.FW.notifyAll();
                    }
                }
                
                if(this.isDeath()){
                    this.velX = 0;
                    this.velY = 0;
                    next = false;
                    notFirst = false;
                    
                }
                break;
            
            default:
                next = false;
                break;
        }
    }
    
    public void muovi(){
        int x = (int)getX();
        int y = (int)getY();
        
        if(handler.getZombies().size() > 0 && !flagTrap){
            Zombie z = (Zombie) handler.getZombies().get(0);
                        
            float dx = ((int)z.getX() + z.width/2 ) - ((int)this.getX() + this.width/2);
            float dy  = ((int)z.getY() + z.height/2 ) - ((int)this.getY() + this.height/2);

            dx = (dx/ (float)Math.sqrt(dx*dx + dy*dy));
            dy = (dy/ (float)Math.sqrt(dx*dx + dy*dy));

            dx = dx * this.getInitialVelocity();
            dy = dy * this.getInitialVelocity();

            this.velX = dx;
            this.velY = dy;
            
            angleRotation(z.getX() + z.width/2, z.getY() + z.height/2, this.getX(),this.getY());
            float zombieX = (z.getX()+ z.width/2) - (this.getX() + this.width/2);
            float zombieY = (z.getY() + z.height/2) - (this.getY() + this.height/2);
            float distanceToZombieX = (float) (Math.sqrt(zombieX * zombieX + zombieY * zombieY));
            float distanceToZombieY = (float) (Math.sqrt(zombieX * zombieX + zombieY * zombieY));

            if (distanceToZombieX < 300 && distanceToZombieY < 300) {
                this.velX = -1 * (int)this.velX;
                this.velY = -1 * (int)this.velY;
                if(currentGun.getRound()>0)
                    currentGun.shoot((float) (angle + (Math.random() - 0.5) * (Math.PI) / 36), this.getX(), this.getY());
                else
                    currentGun.reload();
            }
            
            //Velocità fittizie per capire se ci sono ostacoli
            int vx = 0;
            int vy = 0;
            if (velX < 0) {
                vx = -1*(int)this.getInitialVelocity();
            } else if (velX > 0) {
                vx = +1*(int)this.getInitialVelocity();
            }
            if (velY < 0) {
                vy = -1*(int)this.getInitialVelocity();
            } else if (velY > 0) {
                vy = +1*(int)this.getInitialVelocity();
            }

            
            x += vx;
            y += vy;

            //aggiorno le variabili dello sprite per come funziona collision
            this.setX(x);
            this.setY(y);

            //Se c'è una collisione non posso passare
            int k = this.collision(vx, vy,(float) x,(float) y);
            switch (k) {
                case 1:
                    x -= vx;
                    break;
                case 2:
                    y -= vy;
                    break;
                case 3:
                    x -= vx;
                    y -= vy;
                    break;
                default:
                    x = (int) (x - vx + velX);
                    y = (int) (y - vy + velY);
                    break;
            }
  
        }else{
           x += (int)velX;
           y += (int)velY;
        }

        setX(x);
        setY(y);
    }

}
