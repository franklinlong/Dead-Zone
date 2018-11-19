package deadzone;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends AnimatedSprite{
	private double velx;
	private double vely;
        private double x;
        private double y;
	private final static int BULLETDIAMETER = 6;
	private final int velocity = 20;
	
	public Projectile(int x,int y,double velx,double vely, int health){
		super(x,y,health);
		this.velx = velx;
		this.vely = vely;
                this.velx *= this.velocity;
                this.vely *= this.velocity;
                this.x = x;
                this.y = y;
	}
	
	@Override
	public void drawImage(Graphics g, int offsetX, int offsetY) {  //non aggiornato rispetto alla camera
            g.setColor(Color.RED);
            g.fillOval((int)x, (int)y, BULLETDIAMETER, BULLETDIAMETER);
	}

	@Override
	public void animationCycle() {
            this.x = this.x + velx;
            this.y = this.y + vely;
	}
	
}
