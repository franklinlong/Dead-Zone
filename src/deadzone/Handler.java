/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import sprite.Blood;
import sprite.DropItem;
import sprite.Sprite;
import sprite.animated.*;

/**
 *
 * @author giova
 */
public class Handler {

    private final List<Sprite> players = new ArrayList<>();
    private final List<Sprite> zombies = new ArrayList<>();
    private final List<Sprite> proiettili = new ArrayList<>();
    private final List<Sprite> spittles = new ArrayList<>();
    private final List<Sprite> itemsAndBlood = new ArrayList<>();

    private static Camera camera;
    private final Player player;
    private final Waves waves;

    public Handler(String playerName, boolean male) {
        player = new Player(2000, 60, 2, 300, this, playerName, male);
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

        for (int i = 0; i < itemsAndBlood.size(); i++) {
            Sprite s = itemsAndBlood.get(i);
            s.animationCycle();
        }
    }

    public void drawImage(Graphics g) {
        float offsetX = camera.getOffset_x();
        float offsetY = camera.getOffset_y();

        //Faccio partire il draw image di tutti gli sprite della mappa
        for (int i = 0; i < itemsAndBlood.size(); i++) {
            Sprite s = itemsAndBlood.get(i);
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

        for (int i = 0; i < itemsAndBlood.size(); i++) {
            Sprite s = itemsAndBlood.get(i);
        }
        for (int i = 0; i < zombies.size(); i++) {
            Sprite s = zombies.get(i);
            s.drawImage(g, offsetX, offsetY);
        }

        for (int i = 0; i < players.size(); i++) {
            Sprite s = players.get(i);
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
        } else if (s instanceof DropItem || s instanceof Blood) {
            this.itemsAndBlood.add(s);
        } else if (s instanceof Spittle) {
            this.spittles.add(s);
        } else {
            System.err.println("ERROREEEE, in handler");
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
        } else if (s instanceof DropItem || s instanceof Blood) {
            this.itemsAndBlood.remove(s);
        } else if (s instanceof Spittle) {
            this.spittles.remove(s);
        } else {
            System.err.println("ERROREEEE, in handler");
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

    public List<Sprite> getitemsAndBlood() {
        return itemsAndBlood;
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
