 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import deadzone.sprite.animated.PlayerFactory;
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
import deadzone.sprite.SpriteInterface;
import deadzone.trap.Trap;

/**
 *
 * @author giova
 */
public class Handler implements SpriteInterface{

    private final List<SpriteInterface> players = new ArrayList<>();
    private final List<SpriteInterface> zombies = new ArrayList<>();
    private final List<SpriteInterface> proiettili = new ArrayList<>();
    private final List<SpriteInterface> spittles = new ArrayList<>();
    private final List<SpriteInterface> spawnSpittles = new ArrayList<>();
    private final List<SpriteInterface> items = new ArrayList<>();
    private final List<SpriteInterface> traps = new ArrayList<>();
    private final List<SpriteInterface> bloods = new ArrayList<>();
    private final List<SpriteInterface> circle = new ArrayList<>();
    
    private final PlayerFactory player;
    private final Waves waves;

    RemoveSpriteVisitor rsv = new RemoveSpriteVisitor(this);
    AddSpriteVisitor asv = new AddSpriteVisitor(this);
    
    public Handler(PlayerFactory p, Waves w){
        player = p;              
        players.add(player);
        this.waves = w;
    }

    public void animationCycle() {
        //Faccio partire l'animation cycle di tutti gli sprite della mappa
        for (int i = 0; i < players.size(); i++) {
            SpriteInterface s = players.get(i);
            s.animationCycle();
        }

        for (int i = 0; i < zombies.size(); i++) {
            SpriteInterface s = zombies.get(i);
            s.animationCycle();
        }

        for (int i = 0; i < proiettili.size(); i++) {
            SpriteInterface s = proiettili.get(i);
            s.animationCycle();
        }

        for (int i = 0; i < spittles.size(); i++) {
            SpriteInterface s = spittles.get(i);
            s.animationCycle();
        }

        for (int i = 0; i < spawnSpittles.size(); i++) {
            SpriteInterface s = spawnSpittles.get(i);
            s.animationCycle();
        }

        for (int i = 0; i < items.size(); i++) {
            SpriteInterface s = items.get(i);
            s.animationCycle();
        }
        
        for (int i = 0; i < traps.size(); i++) {
            SpriteInterface s = traps.get(i);
            s.animationCycle();
        }
        
        for (int i = 0; i < circle.size(); i++) {
            SpriteInterface s = circle.get(i);
            s.animationCycle();
        }
        
        for (int i = 0; i < bloods.size(); i++) {
            SpriteInterface s = bloods.get(i);
            s.animationCycle();
        }
    }

    public void drawImage(Graphics g, float offsetX, float offsetY){

        //Faccio partire il draw image di tutti gli sprite della mappa
        for (int i = 0; i < bloods.size(); i++) {
            SpriteInterface s = bloods.get(i);
            s.drawImage(g, offsetX, offsetY);
        }
        
        for (int i = 0; i < items.size(); i++) {
            SpriteInterface s = items.get(i);
            s.drawImage(g, offsetX, offsetY);
        }

        for (int i = 0; i < traps.size(); i++) {
            SpriteInterface s = traps.get(i);
            s.drawImage(g, offsetX, offsetY);
        }
        
        for (int i = 0; i < proiettili.size(); i++) {
            SpriteInterface s = proiettili.get(i);
            s.drawImage(g, offsetX, offsetY);
        }

        for (int i = 0; i < spittles.size(); i++) {
            SpriteInterface s = spittles.get(i);
            s.drawImage(g, offsetX, offsetY);
        }
        
        for (int i = 0; i < spawnSpittles.size(); i++) {
            SpriteInterface s = spawnSpittles.get(i);
            s.drawImage(g, offsetX, offsetY);
        }

        for (int i = 0; i < zombies.size(); i++) {
            SpriteInterface s = zombies.get(i);
            s.drawImage(g, offsetX, offsetY);
        }

        for (int i = 0; i < players.size(); i++) {
            SpriteInterface s = players.get(i);
            s.drawImage(g, offsetX, offsetY);
        }
        
        for (int i = 0; i < circle.size(); i++) {
            SpriteInterface s = circle.get(i);
            s.drawImage(g, offsetX, offsetY);
        }
        
        
    }

    public void addSprite(Sprite s) {
        //Prima di aggiungere lo sprite devo individuare in che lista aggiungerlo
        s.accept(asv);
    }

    public void removeSprite(Sprite s) {
        //Prima di rimuovere lo sprite devo individuare in che lista rimuoverlo
        s.accept(rsv);
    }

    public List<SpriteInterface> getPlayers() {
        return players;
    }

    public List<SpriteInterface> getZombies() {
        return zombies;
    }

    public List<SpriteInterface> getProjectiles() {
        return proiettili;
    }

    public List<SpriteInterface> getSpittles() {
        return spittles;
    }

    public List<SpriteInterface> getspawnSpittles() {
        return spawnSpittles;
    }
    
    public List<SpriteInterface> getItems() {
        return items;
    }
    
    public List<SpriteInterface> getTraps() {
        return traps;
    }
    
    public List<SpriteInterface> getCircle() {
        return circle;
    }

    public PlayerFactory getPlayer() {
        return player;
    }

    public Waves getWaves() {
        return waves;
    }

    public List<SpriteInterface> getBloods() {
        return bloods;
    }

}
