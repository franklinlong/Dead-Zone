/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;



/**
 *
 * @author giova
 */
public class Player extends AnimatedSprite{
    
 // pistol animations
	
 	private Animation pistolIdle, pistolReload, pistolShoot;
 	
 	// rifle animations
 	
 	private Animation rifleIdle, rifleReload, rifleShoot;
 	
 	//shotgun animations
 	
 	private Animation shotgunIdle, shotgunReload, shotgunShoot;
	// sounds
	
	private Sound rifleShootSound, rifleReloadSound;
	private Sound pistolShootSound, pistolReloadSound;
	
	private static Gun currentGun;
	private Gun pistol,rifle,shotgun;
	
	private int w_frame = 1000;
	private int h_frame = 700;
	private int w_map = 3200;
	private int h_map = 3200;
	
        int xx,yy;
        
    public Player(int x, int y,int health) {
        super(x, y, 100);
        height=112;
        width=112;
        xx=yy=0;
        
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
		
        initPlayer();
        

        pistol = new Gun(Assets.pistolSkin, pistolIdle, pistolReload, pistolShoot,pistolShootSound,
				pistolReloadSound, this, 400,
				9, 2700);
		rifle = new Gun(Assets.ak47, rifleIdle, rifleReload, rifleShoot, rifleShootSound,
				rifleReloadSound, this, 100,
				30, 9000);
		
		shotgun = new Gun(Assets.pistolSkin,shotgunIdle, shotgunReload, shotgunShoot, pistolShootSound,
				pistolReloadSound, this,800,
				5, 1000);
		
		currentGun = pistol;
    }

    private void initPlayer(){
        
        setX(x);
        setY(y);
    }

    @Override
    public void drawImage(Graphics g) {
            //System.out.println("x="+player.getX()+"  y="+player.getY());

            if (getX()<=w_frame/2 && getY()<=h_frame/2){ //zona1
                xx = getX();
                yy = getY();
            }
            else if (getX()>w_frame/2 && getX()<w_map-w_frame/2 && getY()<=h_frame/2){ //zona 2
                xx = w_frame/2;
                yy=getY();
            }
            else if (getX()>=w_map-w_frame/2 && getY()<=h_frame/2){ //zona3
                xx =getX()-(w_map-w_frame);
                yy = getY();
            }
            else if (getX()<=w_frame/2 && getY()>h_frame/2 && getY()<h_map-h_frame/2){ //zona4
                xx = getX();
                yy = h_frame/2;
            }
            else if (getX()>=w_map-w_frame/2 && getY()>h_frame/2 && getY()<h_map-h_frame/2){ //zona6
                xx = getX() - (w_map - w_frame);
                yy = h_frame/2;
            }
            else if (getX()<=w_frame/2 && getY()>=h_map-h_frame/2){ //zona7
                xx = getX();
                yy = getY() - (h_map - h_frame);
            }
            else if (getX()>w_frame/2 && getX()<w_map-w_frame/2 && getY()>=h_map-h_frame/2){ //zona 8
                xx = w_frame/2;
                yy = getY() - (h_map - h_frame);
            }
            else if (getX()>=w_map-w_frame/2 && getY()>=h_map-h_frame/2){ //zona 9
                xx = getX()-(w_map-w_frame);
                yy = getY()-(h_map-h_frame);
            }
            else{ //zona5
                xx = w_frame/2;
                yy = h_frame/2;
            }
            
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
        
        if(collision()){
            x += velX*-1;
            y += velY*-1;
        }
        
        if(KAdapter.up)
            velY = -5;
        else if(!KAdapter.down)
            velY = 0;
        
        if(KAdapter.down)
            velY = 5;
        else if(!KAdapter.up)
            velY = 0;
        
        if(KAdapter.right)
            velX = 5;
        else if(!KAdapter.left)
            velX = 0;
        
        if(KAdapter.left)
            velX = -5;
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
			double xbullet = Math.cos(angle);
			double ybullet = Math.sin(angle);
			double bulletdirectionX = xbullet/Math.sqrt(xbullet*xbullet+ybullet*ybullet);
			double bulletdirectionY = ybullet/Math.sqrt(ybullet*ybullet+xbullet*xbullet);
			Projectile p = new Projectile(x,y,bulletdirectionX,bulletdirectionY);
			currentGun.shoot(p);
		}
		currentGun.update();
    }
    
  
}
