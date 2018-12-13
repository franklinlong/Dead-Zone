 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import deadzone.sprite.animated.Player;
import deadzone.sprite.animated.SpawnSpittle;
import deadzone.sprite.animated.Projectile;
import deadzone.sprite.animated.Spittle;
import deadzone.sprite.animated.Zombie;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import deadzone.sprite.Blood;
import deadzone.sprite.Circle;
import deadzone.sprite.DropItem;
import deadzone.sprite.Sprite;
import deadzone.trap.Trap;

/**
 *
 * @author giova
 */
public class Handler {

    private final List<Sprite> players = new ArrayList<>();
    private final List<Sprite> zombies = new ArrayList<>();
    private final List<Sprite> proiettili = new ArrayList<>();
    private final List<Sprite> spittles = new ArrayList<>();
    private final List<Sprite> spawnSpittles = new ArrayList<>();
    private final List<Sprite> itemsAndBloodAndTrap = new ArrayList<>();
    private final List<Sprite> circle = new ArrayList<>();
    private static Camera camera;
    private final Player player;
    private final Waves waves;

    public Handler(String playerName, boolean male) {
        player = new Player(1600, 1600, 3, 1600, this, playerName, male);
        camera = new Camera(player);
        players.add(player);
        
        this.waves = new Waves(this);
               
        Thread t = new Thread(waves);
        t.start();

    }

    public void animationCycle() {
        //Faccio partire l'animation cycle di tutti gli sprite della mappa
        for (int i = 0; i < players.size(); i++) {
            Sprite s = players.get(i);
            s.animationCycle();
        }

        for (int i = 0; i < zombies.size(); i++) {
            Sprite s = zombies.get(i);
            s.animationCycle();
        }

        for (int i = 0; i < proiettili.size(); i++) {
            Sprite s = proiettili.get(i);
            s.animationCycle();
        }

        for (int i = 0; i < spittles.size(); i++) {
            Sprite s = spittles.get(i);
            s.animationCycle();
        }

        for (int i = 0; i < spawnSpittles.size(); i++) {
            Sprite s = spawnSpittles.get(i);
            s.animationCycle();
        }

        for (int i = 0; i < itemsAndBloodAndTrap.size(); i++) {
            Sprite s = itemsAndBloodAndTrap.get(i);
            s.animationCycle();
        }
        for (int i = 0; i < circle.size(); i++) {
            Sprite s = circle.get(i);
            s.animationCycle();
        }
        
    }

    public void drawImage(Graphics g) {
        float offsetX = camera.getOffset_x();
        float offsetY = camera.getOffset_y();

        //Faccio partire il draw image di tutti gli sprite della mappa
        for (int i = 0; i < itemsAndBloodAndTrap.size(); i++) {
            Sprite s = itemsAndBloodAndTrap.get(i);
            s.drawImage(g, offsetX, offsetY);
        }

        for (int i = 0; i < proiettili.size(); i++) {
            Sprite s = proiettili.get(i);
            s.drawImage(g, offsetX, offsetY);
        }

        for (int i = 0; i < spittles.size(); i++) {
            Sprite s = spittles.get(i);
            s.drawImage(g, offsetX, offsetY);
        }
        
        for (int i = 0; i < spawnSpittles.size(); i++) {
            Sprite s = spawnSpittles.get(i);
            s.drawImage(g, offsetX, offsetY);
        }

        for (int i = 0; i < zombies.size(); i++) {
            Sprite s = zombies.get(i);
            s.drawImage(g, offsetX, offsetY);
        }

        for (int i = 0; i < players.size(); i++) {
            Sprite s = players.get(i);
            s.drawImage(g, offsetX, offsetY);
        }
        
        for (int i = 0; i < circle.size(); i++) {
            Sprite s = circle.get(i);
            s.drawImage(g, offsetX, offsetY);
        }
    }

    public void addSprite(Sprite s) {
        //Prima di aggiungere lo sprite devo individuare in che lista aggiungerlo
        if (s instanceof Zombie) {
            this.zombies.add(s);
        } else if (s instanceof Player) {
            this.players.add(s);
        } else if (s instanceof Projectile) {
            this.proiettili.add(s);
        } else if (s instanceof DropItem || s instanceof Blood || s instanceof Trap) {
            this.itemsAndBloodAndTrap.add(s);
        } else if (s instanceof Spittle) {
            this.spittles.add(s);
        }else if (s instanceof SpawnSpittle) {
            this.spawnSpittles.add(s);
        }else if (s instanceof Circle) {
            this.circle.add(s);
        }else {
            System.err.println("ERROREEEE, in handler add sprite");
        }

    }

    public void removeSprite(Sprite s) {
        //Prima di rimuovere lo sprite devo individuare in che lista rimuoverlo
        if (s instanceof Zombie) {
            this.zombies.remove(s);
        } else if (s instanceof Player) {
            this.players.remove(s);
        } else if (s instanceof Projectile) {
            this.proiettili.remove(s);
        } else if (s instanceof DropItem || s instanceof Blood || s instanceof Trap) {
            this.itemsAndBloodAndTrap.remove(s);
        } else if (s instanceof Spittle) {
            this.spittles.remove(s);
        } else if (s instanceof SpawnSpittle) {
            this.spawnSpittles.remove(s);
        } else if (s instanceof Circle) {
            this.circle.remove(s);
        }else {
            System.err.println("ERROREEEE, in handler remove sprite");
        }
    }

    public List<Sprite> getPlayers() {
        return players;
    }

    public List<Sprite> getZombies() {
        return zombies;
    }

    public List<Sprite> getProiettili() {
        return proiettili;
    }

    public List<Sprite> getSpittles() {
        return spittles;
    }

    public List<Sprite> getspawnSpittles() {
        return spawnSpittles;
    }
    
    public List<Sprite> getitemsAndBlood() {
        return itemsAndBloodAndTrap;
    }
    
    public List<Sprite> getiCircle() {
        return circle;
    }

    public Camera getCamera() {
        return camera;
    }

    public Player getPlayer() {
        return player;
    }

    public Waves getWaves() {
        return waves;
    }

}
