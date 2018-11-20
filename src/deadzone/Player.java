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
    
        private Handler handler;
    
        public static final int PLAYERSIZE = 60;
    
        // pistol animations
	
 	private Animation pistolIdle, pistolReload, pistolShoot, pistolMove;
 	
 	// rifle animations
 	
 	private Animation rifleIdle, rifleReload, rifleShoot, rifleMove;
 	
 	//shotgun animations
 	
 	private Animation shotgunIdle, shotgunReload, shotgunShoot, shotgunMove;
	// sounds
	
	private Sound rifleShootSound, rifleReloadSound;
	private Sound pistolShootSound, pistolReloadSound;
	private Sound shotgunShootSound, shotgunReloadSound;
        
	private static Gun currentGun;
	private Gun pistol,rifle,shotgun;
	
	private int w_map = 3200;
	private int h_map = 3200;
	
        int xx,yy;
        
    public Player(int x, int y, int velX, int velY, int health, Handler handler) {
        super(x, y, 100);
        height=PLAYERSIZE;
        width=PLAYERSIZE;
        xx=yy=0;
        this.velX = velX;
        this.velY = velY;
        this.initialVelocity = velX;
        this.handler = handler;
        
        pistolIdle = new Animation(Assets.pistolIdle,20);
		pistolReload = new Animation(Assets.pistolReload, 100);
		pistolShoot = new Animation(Assets.pistolShootAnim, 80);
		pistolShootSound = new Sound(Assets.pistolShoot);
		pistolShootSound.changeVolume(-10);
		pistolReloadSound = new Sound(Assets.pistolReloadSound);
		pistolMove = new Animation(Assets.pistolMove,50);
                
		rifleIdle = new Animation(Assets.rifleIdle, 20);
		rifleReload = new Animation(Assets.rifleReload, 100);
		rifleShoot = new Animation(Assets.rifleShootAnim, 80);
		rifleShootSound = new Sound(Assets.rifleShoot);
		rifleShootSound.changeVolume(-5);
		rifleReloadSound = new Sound(Assets.rifleReloadSound);
                rifleMove = new Animation(Assets.rifleMove,50);
                                
		shotgunIdle = new Animation(Assets.shotgunIdle,20);
		shotgunReload = new Animation(Assets.shotgunReload,100);
		shotgunShoot = new Animation(Assets.shotgunShootAnim,80);
                shotgunShootSound = new Sound(Assets.shotgunShoot);
                shotgunReloadSound = new Sound(Assets.shotgunReloadSound);
		shotgunMove = new Animation(Assets.shotgunMove,50);

        initPlayer();
        

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

    private void initPlayer(){
        
        setX(x);
        setY(y);
    }
    
    @Override
    public void drawImage(Graphics g,int offsetX, int offsetY){
        xx=this.x-offsetX;
        yy=this.y-offsetY;
        
        double wR = MAdapter.x - xx - width/2;
        double hR = MAdapter.y - yy - height/2;
        
        angle = Math.atan(hR / wR);
        if(wR < 0)
            angle = -Math.PI + angle;
                
        at = AffineTransform.getTranslateInstance(xx,yy);
        at.rotate(angle,width/2,height/2);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(currentGun.getCurrentAnimation().getCurrentFrame(), at, null);
    }

    public int getXX(){
        return xx;
    }
    
    public int getYY(){
        return yy;
    }

    @Override
    public void animationCycle() {
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
 
        if(KAdapter.one)
                currentGun = pistol;
        if(KAdapter.two)
                currentGun = rifle;
        if(KAdapter.three)
                currentGun = shotgun;

        if(KAdapter.reload && currentGun.getRound() != currentGun.getBulletsPerRound() &&
                        currentGun.getTotalBullets() > 0)
            currentGun.reload();

        if(MAdapter.left){            
            currentGun.shoot(angle, xx, yy);
        }
        currentGun.update();
    }
    
  
}
