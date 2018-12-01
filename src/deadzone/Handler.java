/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import gameMenu.PauseMenu;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import sprite.Blood;
import sprite.DropItem;
import sprite.Sprite;
import sprite.animated.Player;
import sprite.animated.Projectile;
import sprite.animated.Zombie;


/**
 *
 * @author giova
 */
public class Handler {
    private final List<Sprite> players = new ArrayList<>();
    private final List<Sprite> zombies = new ArrayList<>();
    private final List<Sprite> proiettili = new ArrayList<>();
    private final List<Sprite> itemsAndBlood = new ArrayList<>();
    
    private static Camera camera;
    private final Player player;
    
    //Gestisce lo spawn dello zombie
    public final Timer spawn = new Timer(2000, new ActionListener(){
        int spawnX;
        int spawnY;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            int n = (int) (Math.random()*5);
            switch(n){
                case 0:                     //Fosso
                    spawnX=2150;
                    spawnY=2540;
                    break;
                case 1:                     //Tomba 11
                    spawnX=2224;
                    spawnY=242;
                    break;
                case 2:                     //Tomba 21
                    spawnX=2700;
                    spawnY=242;
                    break;
                case 3:                     //Tomba 12
                    spawnX=2420;
                    spawnY=470;
                    break;
                case 4:                     //Tomba 22
                    spawnX=2800;
                    spawnY=470;
                    break;
            }
            if (!PauseMenu.pause){
            createZombie(spawnX,spawnY);
            }
        }});
    
    public Handler(String playerName){
        player = new Player(2000,60,2,100,this, playerName);
        
        createZombie(2300,20);
        createZombie(2300,60);
        createZombie(2300,100);
        
        camera = new Camera(player);
        players.add(player);
        spawn.start();
        
    }
    
    public void animationCycle(){
        //Faccio partire l'animation cycle di tutti gli sprite della mappa
        for(int i=0;i<players.size();i++){
            Sprite s = players.get(i);
            s.animationCycle();
        }
        
        for(int i=0;i<zombies.size();i++){
            Sprite s = zombies.get(i);
            s.animationCycle();
        }
        
        for(int i=0;i<proiettili.size();i++){
            Sprite s = proiettili.get(i);
            s.animationCycle();
        }
        
        for(int i=0;i<itemsAndBlood.size();i++){
            Sprite s = itemsAndBlood.get(i);
            s.animationCycle();
        }
    }
    
    public void drawImage(Graphics g){
        float offsetX = camera.getOffset_x();
        float offsetY = camera.getOffset_y();
        
        //Faccio partire il draw image di tutti gli sprite della mappa
        for(int i=0;i<players.size();i++){
            Sprite s = players.get(i);
            s.drawImage(g,offsetX,offsetY);
        }
        
        for(int i=0;i<zombies.size();i++){
            Sprite s = zombies.get(i);
            s.drawImage(g,offsetX,offsetY);
        }
        
        for(int i=0;i<proiettili.size();i++){
            Sprite s = proiettili.get(i);
            s.drawImage(g,offsetX,offsetY);
        }
        
        for(int i=0;i<itemsAndBlood.size();i++){
            Sprite s = itemsAndBlood.get(i);
            s.drawImage(g,offsetX,offsetY);
        }
    }
    
    public void addSprite(Sprite s){
        //Prima di aggiungere lo sprite devo individuare in che lista aggiungerlo
        if(s instanceof Zombie){
            this.zombies.add(s);
        }
        else if(s instanceof Player){
            this.players.add(s);
        }
        else if(s instanceof Projectile){
            this.proiettili.add(s);
        }
        else if(s instanceof DropItem || s instanceof Blood){
            this.itemsAndBlood.add(s);
        }
        else 
            System.err.println("ERROREEEE, in handler");
        
    }
    
    public void removeSprite(Sprite s){
        //Prima di rimuovere lo sprite devo individuare in che lista rimuoverlo
        if(s instanceof Zombie){
            this.zombies.remove(s);
        }
        else if(s instanceof Player){
            this.players.remove(s);
        }
        else if(s instanceof Projectile){
            this.proiettili.remove(s);
        }
        else if(s instanceof DropItem || s instanceof Blood){
            this.itemsAndBlood.remove(s);
        }
        else 
            System.err.println("ERROREEEE, in handler");
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

    public List<Sprite> getitemsAndBlood() {
        return itemsAndBlood;
    }

    //DA FARE PER BENE BENE
    private void createZombie(float x, float y) {         
        System.out.println(x + " " + y);
        addSprite((new Zombie(x, y, 1, 100, this.player, this, (float)1)));
    }

    public Camera getCamera() {
        return camera;
    }

    public Player getPlayer() {
        return player;
    }
    
}
