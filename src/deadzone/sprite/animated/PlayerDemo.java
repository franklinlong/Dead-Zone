/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.sprite.animated;

import deadzone.Gun;
import deadzone.Waves;
import deadzone.graph.Graph;
import deadzone.graph.Vertex;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;
import deadzone.sprite.Sprite;
import deadzone.sprite.SpriteInterface;
import deadzone.trap.Trap;
import deadzone.utilities.Animation;
import deadzone.utilities.Assets;
import java.util.Iterator;

/**
 *
 * @author casang
 */
public class PlayerDemo extends PlayerFactory{
    
    public static boolean visible;
    private String move;
    private Waves wave;
    private List<SpriteInterface> zombies; 
    
    public PlayerDemo(float x, float y, int vel, int health){
        super(x, y, vel, health);
        this.initPlayer();
    }
    
    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        xx = this.getX() - offsetX;
        yy = this.getY() - offsetY;
        if (visible){
            if (!this.handler.getZombies().isEmpty())
                angleRotation(((Sprite) zombies.get(0)).getX(), ((Sprite) zombies.get(0)).getY(),this.getX(),this.getY());
            
            at = AffineTransform.getTranslateInstance(xx, yy);
            at.rotate(angle, width / 2, height / 2);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(currentGun.getCurrentAnimation().getCurrentFrame(), at, null);
        }
    }
    
    @Override
    public void animationCycle() {
        //Controllo che sia vivo        
        if (getHealth() <= 0) {
            for (Iterator<SpriteInterface> it = handler.getBloods().iterator(); it.hasNext();) {
                Sprite s =(Sprite) it.next();
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

        if (!visible)
            showMap();
            
        float x = getX();
        float y = getY();
        x += velX;
        y += velY;

        setX(x);
        setY(y);

        if (visible){
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
        }
            
        switch(move){
            case "up":
                velY = -initialVelocity;
                velX = 0;
                break;
            case "down":
                velY = initialVelocity;
                velX = 0;
                break;
            case "left":
                velY = 0;
                velX = -initialVelocity;
                break;
            case "right":
                velY = 0;
                velX = initialVelocity;
                break;
            default:
                velX = 0;
                velY = 0;
        }
            

        if (move.equalsIgnoreCase("1"))
            currentGun = pistol;
            
        if (move.equalsIgnoreCase("2"))
            currentGun = rifle;
            
        if (move.equalsIgnoreCase("3"))
            currentGun = shotgun;

        if (move.equalsIgnoreCase("4"))
            currentGun = rpg;
        
        if (move.equalsIgnoreCase("action")){
            int pixel = mapRGB.getRGB((int) getX() + width / 2, (int) getY() + height / 2);
            pixel = (pixel >> 8) & 0xff;
            activeTrap(pixel);
        }
            
        if (move.equalsIgnoreCase("reload") && currentGun.getRound() != currentGun.getBulletsPerRound()
                && currentGun.getTotalBullets() > 0)
            currentGun.reload();

        if (move.equalsIgnoreCase("shoot"))
            currentGun.shoot((float) (angle + (Math.PI) / 30), x, y);

        if (!visible){
            velX *= 6;
            velY *= 6;
        }
                
        //aggiorno animazione del personaggio
        currentGun.update();
        
    }
    
    public void showMap(){
        
        if (getX() <= 2700 && getY() <= 450)
            this.move = "right";
        else if (getX() >= 2700 && getY() <= 2800)
            this.move = "down";
        else if (getX() >= 450 && getY() >= 2700)
            this.move = "left";
        else
            this.move = "up";
        
        if (getX() <= 1900 && getX() >= 1800 && getY() <= 450){
            visible = true;
            Thread t = new Thread(wave);
            t.start();
            move = "";
            
            synchronized(this.handler.getZombies()){
                if (this.handler.getZombies().isEmpty()){
                    try{    
                        this.handler.getZombies().wait();
                    }catch (InterruptedException ex){}
                }
            }
            
            zombies = this.handler.getZombies();
            Thread t2 = new Thread(action);
            t2.start();
            
        }
    }
    
    Thread action = new Thread(new Runnable()
        {
        @Override
        public synchronized void run() {
            try{
                wait(500);
                move = "shoot";
                wait(1500);
                move = "up";
                wait(700);
                move = "right";
                wait(1000);
                move = "2";
                wait(100);
                move = "shoot";
                wait(500);
                move = "left";
                wait(1200);
                move = "down";
                wait(1000);
                move = "reload";
                wait(200);
                move = "down";
                wait(4000);
                move = "left";
                wait(1100);
                move = "up";
                wait(1000);
                move = "action";
                wait(300);
                move = "left";
                wait(4500);
                move = "down";
                wait(5000);
                move = "right";
                wait(1000);
                move = "down";
                wait(1000);
                move = "action";
                wait(200);
                move = "right";
                wait(1000);
                move = "";
                wait(8500);
                move = "shoot";
                wait(5000);
                move = "reload";
                wait(100);
                move = "up";
                wait(500);
                move = "left";
                wait(1000);
                move = "4";
                wait(100);
                move = "shoot";
                wait(100);
                move = "2";
                wait(100);
                move = "shoot";
                
            }catch(InterruptedException ex){}
            
        }
            } 
    );

    @Override
    public void initPlayer() {
        this.coins = 40;
        this.visible = false; 
        
        this.male = true;
        
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
                1, 0, 700);
        
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
        this.name = "player demo";
    }
    
    public void setWave(Waves wave){
        this.wave = wave;
    }
}
