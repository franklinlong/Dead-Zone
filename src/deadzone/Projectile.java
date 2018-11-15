package deadzone;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends Sprite{
	private double velx;
	private double vely;
	private final static int BULLETDIAMETER = 6;
	private final int velocity = 20;
	
	public Projectile(int x,int y,double velx,double vely){
		super(x,y);
		this.velx = velx;
		this.vely = vely;
	}
	
	@Override
	public void drawImage(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(this.x-BULLETDIAMETER/2, this.y-Projectile.BULLETDIAMETER/2, Projectile.BULLETDIAMETER, BULLETDIAMETER);
	}

	@Override
	public void animationCycle() {
		this.x = this.x+this.velX*this.velocity;
		this.y = this.y+this.velY*this.velocity;
	}
	
}
