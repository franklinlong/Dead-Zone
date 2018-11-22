/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


/**
 *
 * @author giova
 */
public class Player extends AnimatedSprite{
    
    public static final int PLAYERSIZE = 60;

    //animations
    private final Animation shotgunIdle, shotgunReload, shotgunShoot;
    private final Animation pistolIdle, pistolReload, pistolShoot;
    private final Animation rifleIdle, rifleReload, rifleShoot;

    // sounds
    private final Sound rifleShootSound, rifleReloadSound;
    private final Sound pistolShootSound, pistolReloadSound;
    private final Sound shotgunShootSound, shotgunReloadSound;

    private static Gun currentGun;
    private final Gun pistol,rifle,shotgun;

    private float xx,yy;
        
    public Player(float x, float y, int vel, int health, Handler handler) {
        super(x, y, PLAYERSIZE, PLAYERSIZE, vel, health);
                
        pistolIdle = new Animation(Assets.pistolIdle,20);
        pistolReload = new Animation(Assets.pistolReload, 100);
        pistolShoot = new Animation(Assets.pistolShootAnim, 80);
        pistolShootSound = new Sound(Assets.pistolShoot);
        pistolShootSound.changeVolume(-10);
        pistolReloadSound = new Sound(Assets.pistolReloadSound);

        rifleIdle = new Animation(Assets.rifleIdle, 20);
        rifleReload = new Animation(Assets.rifleReload, 100);
        rifleShoot = new Animation(Assets.rifleShootAnim, 80);
        rifleShootSound = new Sound(Assets.rifleShoot);
        rifleShootSound.changeVolume(-5);
        rifleReloadSound = new Sound(Assets.rifleReloadSound);

        shotgunIdle = new Animation(Assets.shotgunIdle,20);
        shotgunReload = new Animation(Assets.shotgunReload,100);
        shotgunShoot = new Animation(Assets.shotgunShootAnim,80);
        shotgunShootSound = new Sound(Assets.shotgunShoot);
        shotgunReloadSound = new Sound(Assets.shotgunReloadSound);
        
        pistol = new Gun(Assets.pistolSkin, pistolIdle, pistolReload, pistolShoot,pistolShootSound,
				pistolReloadSound, this, 400,
				9, 2700, handler);
        rifle = new Gun(Assets.ak47, rifleIdle, rifleReload, rifleShoot, rifleShootSound,
                        rifleReloadSound, this, 100,
                        30, 9000, handler);

        shotgun = new Gun(Assets.pistolSkin,shotgunIdle, shotgunReload, shotgunShoot, shotgunShootSound,
                        shotgunReloadSound, this,800,
                        5, 1000, handler);
		
        currentGun = pistol;
    }
    
    @Override
    public void drawImage(Graphics g,float offsetX, float offsetY){
        xx=this.getX() - offsetX;
        yy=this.getY() - offsetY;
        
        //Segmento tra hotSpot del mouse e centro del giocatore...
        float wR = MAdapter.x - xx - width/2;
        float hR = MAdapter.y - yy - height/2;
        
        angle = (float) Math.atan(hR / wR);
        if(wR < 0)
            angle = (float)-Math.PI + angle;
          
        //Aggiorno l'angolo in modo che il giocatore si giri verso il mirino con la pistola e non col proprio centro
        float angoloPistola = (float) (angle + Math.PI/4);
        wR = MAdapter.x - (xx+width/2 + (float)(22*Math.cos(angoloPistola)));
        hR = MAdapter.y - (yy+height/2 + (float)(22*Math.sin(angoloPistola)));
        angle = (float) Math.atan(hR / wR);
        if(wR < 0)
            angle = (float) -Math.PI + angle;
        
        
        at = AffineTransform.getTranslateInstance(xx,yy);
        at.rotate(angle,width/2,height/2);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(currentGun.getCurrentAnimation().getCurrentFrame(), at, null);
    }

    public float getXX(){
        return xx;
    }
    
    public float getYY(){
        return yy;
    }

    @Override
    public void animationCycle() {
        float x = getX();
        float y = getY();
        x+=velX;
        y+=velY;
        
        if (x < 0) {
            x = 2;
        }
        if (y < 0) {
            y = 2;
        }
        int k = collision();
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
        if(KAdapter.up)
            velY = -initialVelocity;
        else if(!KAdapter.down)
            velY = 0;
        
        if(KAdapter.down)
            velY = initialVelocity;
        else if(!KAdapter.up)
            velY = 0;
        
        if(KAdapter.right)
            velX = initialVelocity;
        else if(!KAdapter.left)
            velX = 0;
        
        if(KAdapter.left)
            velX = -initialVelocity;
        else if(!KAdapter.right)
            velX = 0;
        
        //scelta arma corrente in base al pulsante 1-2-3
        if(KAdapter.one)
                currentGun = pistol;
        if(KAdapter.two)
                currentGun = rifle;
        if(KAdapter.three)
                currentGun = shotgun;
        
        //viene premuto R quindi reload
        if(KAdapter.reload && currentGun.getRound() != currentGun.getBulletsPerRound() &&
                        currentGun.getTotalBullets() > 0)
            currentGun.reload();
        
        //viene premuto left(mouse) quindi sparo
        if(MAdapter.left){  
            //Abbiamo aggiunto un parametro random tra -5 e 5 gradi per inserire una inprecisione dell'arma.
            currentGun.shoot((float)(angle + (Math.random() - 0.5)*(Math.PI)/36), xx, yy);
        }
        
        //aggiorno animazione del personaggio
        currentGun.update();
    }
    
  
}
