/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.sprite.animated;

import deadzone.Waves;
import deadzone.graph.Graph;
import deadzone.graph.Vertex;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;
import deadzone.sprite.Sprite;
import deadzone.trap.Trap;
import deadzone.utilities.Assets;

/**
 *
 * @author casang
 */
public class PlayerDemo extends PlayerMale {

    public static boolean visible;
    private String move;
    private Waves wave;
    private List<Sprite> zombies;

    public PlayerDemo(float x, float y, int vel, int health, String name, Waves wave) {
        super(x, y, vel, health, name);
        this.wave = wave;
        this.coins = 40;
        this.visible = false;
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {
        xx = this.getX() - offsetX;
        yy = this.getY() - offsetY;

        if (visible) {
            if (!this.handler.getZombies().isEmpty()) {
                angleRotation(zombies.get(0).getX(), zombies.get(0).getY(), this.getX(), this.getY());
            }

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
            for (Sprite s : handler.getBloods()) {
                if (s instanceof Trap) {
                    ((Trap) s).getSound().stopSound();
                }
            }
            death();
        }

        if (this.currentGun.getSkin() == Assets.pistolSkin && this.currentGun.getTotalBullets() == 0) {
            this.currentGun.setTotalBullets(2000);
        }
        if (this.zona.aggiorna(getX(), getY())) {
            this.camminiMinimi = Graph.BFS_complete(new Vertex(zona.getIndex()));
        }

        if (!visible) {
            showMap();
        }

        float x = getX();
        float y = getY();
        x += velX;
        y += velY;

        setX(x);
        setY(y);

        if (visible) {
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

        switch (move) {
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

        if (move.equalsIgnoreCase("1")) {
            currentGun = pistol;
        }

        if (move.equalsIgnoreCase("2")) {
            currentGun = rifle;
        }

        if (move.equalsIgnoreCase("3")) {
            currentGun = shotgun;
        }

        if (move.equalsIgnoreCase("4")) {
            currentGun = rpg;
        }

        if (move.equalsIgnoreCase("action")) {
            int pixel = mapRGB.getRGB((int) getX() + width / 2, (int) getY() + height / 2);
            pixel = (pixel >> 8) & 0xff;
            activeTrap(pixel);
        }

        if (move.equalsIgnoreCase("reload") && currentGun.getRound() != currentGun.getBulletsPerRound()
                && currentGun.getTotalBullets() > 0) {
            currentGun.reload();
        }

        if (move.equalsIgnoreCase("shoot")) {
            currentGun.shoot((float) (angle + (Math.PI) / 30), x, y);
        }

        if (!visible) {
            velX *= 6;
            velY *= 6;
        }

        //aggiorno animazione del personaggio
        currentGun.update();

    }

    public void showMap() {

        if (getX() <= 2700 && getY() <= 450) {
            this.move = "right";
        } else if (getX() >= 2700 && getY() <= 2800) {
            this.move = "down";
        } else if (getX() >= 450 && getY() >= 2700) {
            this.move = "left";
        } else {
            this.move = "up";
        }

        if (getX() <= 1900 && getX() >= 1800 && getY() <= 450) {
            visible = true;
            Thread t = new Thread(wave);
            t.start();
            move = "";

            synchronized (this.handler.getZombies()) {
                if (this.handler.getZombies().isEmpty()) {
                    try {
                        this.handler.getZombies().wait();
                    } catch (InterruptedException ex) {
                    }
                }
            }

            zombies = this.handler.getZombies();
            Thread t2 = new Thread(action);
            t2.start();

        }
    }

    Thread action = new Thread(new Runnable() {
        @Override
        public synchronized void run() {
            try {
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

            } catch (InterruptedException ex) {
            }

        }
    }
    );

}
