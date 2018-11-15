package deadzone;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;


public class Gun {
	private final int bulletOffset = 120;
	private BufferedImage skin;
	private int width;
	
	
	// animations
	
	private Animation currentAnimation;
	private Animation idle, reloadAnimation, shoot;
	
	// sounds
	
	private Sound shootSound, emptyGun, reloadSound;
	// timers
	
	// firing rate
	
	private Timer firingDelay, shootAminDelay, reloadDelay, emptyGunDelay, reloadLimit;
	
	// bullets managment
	
	private int bulletsPerRound, round, totalBullets;
	
	// player
	private boolean shooting = false, reloading = false;

	private Player player;
	
	public Gun(BufferedImage skin, Animation idle, Animation reloadAnimation, Animation shoot,
			Sound shootSound, Sound reloadSound, Player player, int firingDelay,
			int bulletsPerRound, int totalBullets){
		
		this.skin = skin;
		this.idle = idle;
		this.reloadAnimation = reloadAnimation;
		this.shoot = shoot;
		this.player = player;
		this.shootSound = shootSound;
		this.reloadSound = reloadSound;
		this.emptyGun = new Sound(Assets.emptyGun);
		this.firingDelay = new Timer(firingDelay, new TimeHandler());
		this.firingDelay.setActionCommand("1");
		this.shootAminDelay = new Timer(100, new TimeHandler());
		this.shootAminDelay.setActionCommand("2");
		this.reloadDelay = new Timer(1700, new TimeHandler());
		this.reloadDelay.setActionCommand("3");
		this.emptyGunDelay = new Timer(700, new TimeHandler());
		this.emptyGunDelay.setActionCommand("4");
		this.reloadLimit = new Timer(3000, new TimeHandler());
		this.reloadLimit.setActionCommand("5");
		this.bulletsPerRound = bulletsPerRound;
		this.round = bulletsPerRound;
		this.totalBullets = totalBullets;
		this.emptyGun = new Sound(Assets.emptyGun);
		width = skin.getWidth();
		currentAnimation = idle;
		
	}
	
	public void render(Graphics g){
		g.drawImage(skin, Window.WIDTH - width - bulletOffset, 20, null);
	}
	
	public void update(){
		currentAnimation.update();
	}
	
	public Animation getCurrentAnimation() {
		return currentAnimation;
	}
	
	public void shoot(Projectile p){
	   if(round <= 0){
			if(!emptyGunDelay.isRunning())
			{
				emptyGun.playSound();
				emptyGunDelay.start();
				System.out.println(round);
			}
			return;
		}
		if(reloading)
			return;
		
		if(!shootAminDelay.isRunning() && shooting && !reloading){
			shootAminDelay.start();
			currentAnimation = shoot;
		}
		
		if(!firingDelay.isRunning()){
			shooting = true;
			firingDelay.start();
			shootSound.playSound();
			round--;
		}
	}
	
	public void reload(){
		if(reloadLimit.isRunning()){
			return;
		}
		reloadLimit.start();
		
		if(round != 0 && totalBullets >= bulletsPerRound)
		{
			totalBullets = totalBullets - bulletsPerRound + round;
			round = bulletsPerRound;
		}else if (totalBullets < bulletsPerRound && round != 0){
			
			if(round + totalBullets > bulletsPerRound){
				totalBullets = round + totalBullets - bulletsPerRound;
				round = bulletsPerRound;
			}else{
				round += totalBullets;
				totalBullets = 0;
			}
		}else if (totalBullets < bulletsPerRound){
			round = totalBullets;
			totalBullets = 0;
		}else{
			round = bulletsPerRound;
			totalBullets -= bulletsPerRound;
		}
		
		if(!reloadDelay.isRunning())
		{
			currentAnimation = reloadAnimation;
			reloading = true;
			reloadDelay.start();
			reloadSound.playSound();
		}
	}
	
	class TimeHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == firingDelay.getActionCommand()){
				shootSound.stopSound();
				firingDelay.stop();
			}else if(e.getActionCommand() == shootAminDelay.getActionCommand()){ //Serve per rimettere a idle 
				shoot.setIndex();											//l'animazione dopo lo sparo
				currentAnimation = idle;
				shootAminDelay.stop();
				shooting = false;
			}else if(e.getActionCommand() == reloadDelay.getActionCommand()){
				reloading = false;
				reloadAnimation.setIndex();
				currentAnimation = idle;
				reloadDelay.stop();
				
			}else if(e.getActionCommand() == emptyGunDelay.getActionCommand()){
				emptyGunDelay.stop();
				
			}else if(e.getActionCommand() == reloadLimit.getActionCommand()){
				reloadLimit.stop();
			}
		}
	}
	
	public int getRound(){
		return round;
	}
	
	public int getBulletsPerRound(){
		return bulletsPerRound;
	}
	public int getTotalBullets(){
		return totalBullets;
	}
}
