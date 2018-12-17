package deadzone.utilities;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Assets {

    // sprite sheets	
    public static BufferedImage[] pistolIdle = new BufferedImage[20];
    public static BufferedImage[] rifleIdle = new BufferedImage[20];
    public static BufferedImage[] shotgunIdle = new BufferedImage[20];
    public static BufferedImage[] rpgIdle = new BufferedImage[20];

    public static BufferedImage[] pistolReload = new BufferedImage[15];
    public static BufferedImage[] rifleReload = new BufferedImage[20];
    public static BufferedImage[] shotgunReload = new BufferedImage[20];
    public static BufferedImage[] rpgReload = new BufferedImage[20];

    public static BufferedImage[] pistolShootAnim = new BufferedImage[3];
    public static BufferedImage[] rifleShootAnim = new BufferedImage[3];
    public static BufferedImage[] shotgunShootAnim = new BufferedImage[3];
    public static BufferedImage[] rpgShootAnim = new BufferedImage[3];

    public static BufferedImage[] femalepistolIdle = new BufferedImage[20];
    public static BufferedImage[] femalerifleIdle = new BufferedImage[20];
    public static BufferedImage[] femaleshotgunIdle = new BufferedImage[20];
    public static BufferedImage[] femalerpgIdle = new BufferedImage[20];

    public static BufferedImage[] femalepistolReload = new BufferedImage[15];
    public static BufferedImage[] femalerifleReload = new BufferedImage[20];
    public static BufferedImage[] femaleshotgunReload = new BufferedImage[20];
    public static BufferedImage[] femalerpgReload = new BufferedImage[20];

    public static BufferedImage[] femalepistolShootAnim = new BufferedImage[3];
    public static BufferedImage[] femalerifleShootAnim = new BufferedImage[3];
    public static BufferedImage[] femaleshotgunShootAnim = new BufferedImage[3];
    public static BufferedImage[] femalerpgShootAnim = new BufferedImage[3];

    public static BufferedImage[] zombie = new BufferedImage[17];
    public static BufferedImage[] zombieAttack = new BufferedImage[9];

    public static BufferedImage[] zombie2 = new BufferedImage[32];
    public static BufferedImage[] zombie2Attack = new BufferedImage[9];

    public static BufferedImage[] zombie3 = new BufferedImage[8];
    public static BufferedImage[] zombie3Attack = new BufferedImage[5];
    public static BufferedImage[] zombie3death = new BufferedImage[15];

    public static BufferedImage[] boss = new BufferedImage[8];
    public static BufferedImage[] bossAttack = new BufferedImage[5];
    public static BufferedImage[] bossdeath = new BufferedImage[15];

    public static BufferedImage[] fulmini = new BufferedImage[6];
    public static BufferedImage[] fuochi = new BufferedImage[12];
    public static BufferedImage[] fuochiR = new BufferedImage[12];

    public static BufferedImage[] explosion = new BufferedImage[23];
    public static BufferedImage wall;
    public static BufferedImage hole;
    public static BufferedImage welcome;
    // guns skin
    public static BufferedImage pistolSkin, ak47, rpgSkin, rifleLoader, shotgunSkin;

    //spittle
    public static BufferedImage spittle_e;
    public static BufferedImage spittle_boss;
    public static BufferedImage spawnSpittle;
    public static BufferedImage rocketbull;
    //sounds		
    public static Clip pistolShoot, rifleShoot, shotgunShoot, rpgShoot, background, zombieHit, pistolReloadSound,
            rifleReloadSound, shotgunReloadSound, rpgReloadSound, rpgExplosionSound, emptyGun, zombieBite;
    public static Clip endGame, endOfRound;
    public static Clip restoreHealth, nukeExplosion, coinsDrop,ammoPickUp;

    public static Clip shockTrap1, shockTrap2, shockTrap3, wallTrap, holeTrap, fireTrap;

    //blood
    public static BufferedImage blood;

    //Item 
    public static BufferedImage mediKit;
    public static BufferedImage ammo;
    public static BufferedImage nuke;
    public static BufferedImage coins;
    public static BufferedImage coinsHud;

    public static Image minimap;
    public static Image greenIndicator;
    public static Image redIndicator;

    public static BufferedImage mapRGB;
    public static BufferedImage mapRGB2;
    public static BufferedImage actionImg;
    public static BufferedImage noCoins;
    public static BufferedImage alreadyActive;
    public static BufferedImage buyAmmo;
    
    public static ResultSet rs;

    public static void init() {
        
                ThreadOttieniScoreboard t = new ThreadOttieniScoreboard();
        t.start();

        // animations
        //idle
        for (int i = 0; i < pistolIdle.length; i++) {
            pistolIdle[i] = Utilities.loadImage("/player/idle/pistolIdle/" + i + ".png");
        }

        for (int i = 0; i < rifleIdle.length; i++) {
            rifleIdle[i] = Utilities.loadImage("/player/idle/rifleIdle/" + i + ".png");
        }

        for (int i = 0; i < shotgunIdle.length; i++) {
            shotgunIdle[i] = Utilities.loadImage("/player/idle/shotgunIdle/" + i + ".png");
        }

        for (int i = 0; i < rpgIdle.length; i++) {
            rpgIdle[i] = Utilities.loadImage("/player/idle/rpgIdle/" + i + ".png");
        }
        //reload
        for (int i = 0; i < pistolReload.length; i++) {
            pistolReload[i] = Utilities.loadImage("/player/reload/pistol/" + i + ".png");
        }

        for (int i = 0; i < rifleReload.length; i++) {
            rifleReload[i] = Utilities.loadImage("/player/reload/rifle/" + i + ".png");
        }

        for (int i = 0; i < shotgunReload.length; i++) {
            shotgunReload[i] = Utilities.loadImage("/player/reload/shotgun/" + i + ".png");
        }

        for (int i = 0; i < rpgReload.length; i++) {
            rpgReload[i] = Utilities.loadImage("/player/reload/rpg/" + i + ".png");
        }
        //shot
        for (int i = 0; i < pistolShootAnim.length; i++) {
            pistolShootAnim[i] = Utilities.loadImage("/player/shoot/pistol/" + i + ".png");
        }

        for (int i = 0; i < rifleShootAnim.length; i++) {
            rifleShootAnim[i] = Utilities.loadImage("/player/shoot/rifle/" + i + ".png");
        }

        for (int i = 0; i < shotgunShootAnim.length; i++) {
            shotgunShootAnim[i] = Utilities.loadImage("/player/shoot/shotgun/" + i + ".png");
        }

        for (int i = 0; i < rpgShootAnim.length; i++) {
            rpgShootAnim[i] = Utilities.loadImage("/player/shoot/rpg/" + i + ".png");
        }
        //female animation
        for (int i = 0; i < femalepistolIdle.length; i++) {
            femalepistolIdle[i] = Utilities.loadImage("/femalePlayer/idle/pistolIdle/" + i + ".png");
        }

        for (int i = 0; i < femalerifleIdle.length; i++) {
            femalerifleIdle[i] = Utilities.loadImage("/femalePlayer/idle/rifleIdle/" + i + ".png");
        }

        for (int i = 0; i < femaleshotgunIdle.length; i++) {
            femaleshotgunIdle[i] = Utilities.loadImage("/femalePlayer/idle/shotgunIdle/" + i + ".png");
        }

        for (int i = 0; i < femalerpgIdle.length; i++) {
            femalerpgIdle[i] = Utilities.loadImage("/femalePlayer/idle/rocketIdle/" + i + ".png");
        }
        //reload
        for (int i = 0; i < femalepistolReload.length; i++) {
            femalepistolReload[i] = Utilities.loadImage("/femalePlayer/reload/Pistol/" + i + ".png");
        }

        for (int i = 0; i < femalerifleReload.length; i++) {
            femalerifleReload[i] = Utilities.loadImage("/femalePlayer/reload/Rifle/" + i + ".png");
        }

        for (int i = 0; i < femaleshotgunReload.length; i++) {
            femaleshotgunReload[i] = Utilities.loadImage("/femalePlayer/reload/Shot/" + i + ".png");
        }

        for (int i = 0; i < femalerpgReload.length; i++) {
            femalerpgReload[i] = Utilities.loadImage("/femalePlayer/reload/Rocket/" + i + ".png");
        }
        //shot
        for (int i = 0; i < femalepistolShootAnim.length; i++) {
            femalepistolShootAnim[i] = Utilities.loadImage("/femalePlayer/shoot/Pistol/" + i + ".png");
        }

        for (int i = 0; i < femalerifleShootAnim.length; i++) {
            femalerifleShootAnim[i] = Utilities.loadImage("/femalePlayer/shoot/Rifle/" + i + ".png");
        }

        for (int i = 0; i < femaleshotgunShootAnim.length; i++) {
            femaleshotgunShootAnim[i] = Utilities.loadImage("/femalePlayer/shoot/Shotgun/" + i + ".png");
        }

        for (int i = 0; i < femalerpgShootAnim.length; i++) {
            femalerpgShootAnim[i] = Utilities.loadImage("/femalePlayer/shoot/Rocket/" + i + ".png");
        }

        //zombie walk
        for (int i = 0; i < zombie.length; i++) {
            zombie[i] = Utilities.loadImage("/zombie/walk/" + i + ".png");
        }

        //zombie attack
        for (int i = 0; i < zombieAttack.length; i++) {
            zombieAttack[i] = Utilities.loadImage("/zombie/attack/" + i + ".png");
        }

        //zombie2 walk
        for (int i = 0; i < zombie2.length; i++) {
            zombie2[i] = Utilities.loadImage("/zombie2/walk/" + i + ".png");
        }

        //zombie2 attack
        for (int i = 0; i < zombie2Attack.length; i++) {
            zombie2Attack[i] = Utilities.loadImage("/zombie2/attack/" + i + ".png");
        }

        //zombie3 walk
        for (int i = 0; i < zombie3.length; i++) {
            zombie3[i] = Utilities.loadImage("/gargant/move/" + i + ".png");
        }

        //zombie3 attack
        for (int i = 0; i < zombie3Attack.length; i++) {
            zombie3Attack[i] = Utilities.loadImage("/gargant/atk/" + i + ".png");
        }

        //zombie3 death
        for (int i = 0; i < zombie3death.length; i++) {
            zombie3death[i] = Utilities.loadImage("/gargant/death/" + i + ".png");
        }

        //boss walk
        for (int i = 0; i < boss.length; i++) {
            boss[i] = Utilities.loadImage("/boss/move/" + i + ".png");
        }

        //boss attack
        for (int i = 0; i < bossAttack.length; i++) {
            bossAttack[i] = Utilities.loadImage("/boss/atk/" + i + ".png");
        }

        //boss death
        for (int i = 0; i < bossdeath.length; i++) {
            bossdeath[i] = Utilities.loadImage("/boss/death/" + i + ".png");
        }

        //trap
        for (int i = 0; i < fulmini.length; i++) {
            fulmini[i] = Utilities.loadImage("/images/fulmine/fulmine" + (i + 1) + ".png");
        }

        for (int i = 0; i < fuochi.length; i++) {
            fuochi[i] = Utilities.loadImage("/images/fire/" + i + ".png");
        }

        for (int i = 0; i < fuochiR.length; i++) {
            fuochiR[i] = Utilities.loadImage("/images/fire2/" + i + ".png");
        }
        //rocket explosion
        for (int i = 0; i < explosion.length; i++) {
            explosion[i] = Utilities.loadImage("/explosion/" + i + ".png");
        }

        
        welcome = Utilities.loadImage("/demo/Welcome2.png");
        //gun skins
        shotgunSkin = Utilities.loadImage("/guns/shotgun.png");
        pistolSkin = Utilities.loadImage("/guns/pistol.png");
        rpgSkin = Utilities.loadImage("/guns/rpg.png");
        ak47 = Utilities.loadImage("/guns/ak-47.png");
        rifleLoader = Utilities.loadImage("/guns/rifleLoader.png");
        rocketbull = Utilities.loadImage("/guns/rocketbull.png");

        //Trap skins
        hole = Utilities.loadImage("/images/buca.png");
        wall = Utilities.loadImage("/images/muro.png");

        //Action String Image
        actionImg = Utilities.loadImage("/images/actionImg.png");
        noCoins = Utilities.loadImage("/images/nocoins.png");
        alreadyActive = Utilities.loadImage("/images/alreadyactive.png");
        buyAmmo = Utilities.loadImage("/images/buyammo.png");

        //blood 
        blood = Utilities.loadImage("/images/blood.png");
        //sounds
        pistolShoot = Utilities.LoadSound("/sound/pistol.wav");
        rifleShoot = Utilities.LoadSound("/sound/machinegun.wav");
        rpgShoot = Utilities.LoadSound("/sound/rpgShoot.wav");
        pistolReloadSound = Utilities.LoadSound("/sound/pistolReload.wav");
        rifleReloadSound = Utilities.LoadSound("/sound/rifleReload.wav");
        rpgReloadSound = Utilities.LoadSound("/sound/rpgReload.wav");
        rpgExplosionSound = Utilities.LoadSound("/sound/rpgExplosion.wav");
        emptyGun = Utilities.LoadSound("/sound/emptygun.wav");
        zombieBite = Utilities.LoadSound("/sound/zombieBite.wav");
        zombieHit = Utilities.LoadSound("/sound/zombiehit.wav");

        endGame = Utilities.LoadSound("/sound/endGame.wav");
        endOfRound = Utilities.LoadSound("/sound/round.wav");

        restoreHealth = Utilities.LoadSound("/sound/restoreHealth.wav");
        nukeExplosion = Utilities.LoadSound("/sound/soundExplosion.wav");
        coinsDrop = Utilities.LoadSound("/sound/coin.wav");
        ammoPickUp = Utilities.LoadSound("/sound/ammo.wav");
        shotgunReloadSound = Utilities.LoadSound("/sound/shotgunReload.wav");
        shotgunShoot = Utilities.LoadSound("/sound/shotgun.wav");

        //spittle
        spittle_e = Utilities.loadImage("/zombie/spittle/est.png");
        spittle_boss = Utilities.loadImage("/zombie/spittle/sputoBoss.png");
        spawnSpittle = Utilities.loadImage("/zombie/spawnSpittle/1.png");
        //item
        mediKit = Utilities.loadImage("/images/medikitItem.png");
        ammo = Utilities.loadImage("/images/ammoItem.png");
        nuke = Utilities.loadImage("/images/bombItem.png");
        coins = Utilities.loadImage("/images/coins.png");
        coinsHud = Utilities.loadImage("/images/coinsHud.png");

        //minimap
        int mp_h = Toolkit.getDefaultToolkit().getScreenSize().width * 18 / 100;
        int mp_w = Toolkit.getDefaultToolkit().getScreenSize().width * 18 / 100;
        ImageIcon i = new ImageIcon("resources/images/minimappa.png");
        minimap = i.getImage().getScaledInstance(mp_h, mp_w, Image.SCALE_FAST);

        greenIndicator = new ImageIcon("resources/images/green.png").getImage();
        redIndicator = new ImageIcon("resources/images/red.png").getImage();

        redIndicator = redIndicator.getScaledInstance((int) mp_w / 20, (int) mp_h / 20, Image.SCALE_FAST);  //gli indicatori hanno come size il 5% di quelle della minimap
        greenIndicator = greenIndicator.getScaledInstance((int) mp_w / 20, (int) mp_h / 20, Image.SCALE_FAST);

        try {
            mapRGB = ImageIO.read(new File("mapRGB.png"));
            mapRGB2 = ImageIO.read(new File("mapRGB.png"));
        } catch (IOException ex) {
            Logger.getLogger(Assets.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Trap
        shockTrap1 = Utilities.LoadSound("/sound/scossa.wav");
        shockTrap2 = Utilities.LoadSound("/sound/scossa.wav");
        shockTrap3 = Utilities.LoadSound("/sound/scossa.wav");

        fireTrap = Utilities.LoadSound("/sound/fuoco.wav");
        wallTrap = Utilities.LoadSound("/sound/muro.wav");
        holeTrap = Utilities.LoadSound("/sound/holeTrapSound.wav");

    }
    // forse non più utilizato
        public static class ThreadOttieniScoreboard extends Thread {

        public static final Object TOS = new Object();
        public static boolean occupato = false;

        @Override
        public void run() {
            synchronized (TOS) {
                occupato = true;
                System.out.println("Sto scaricando lo scoreboard");
                rs = Database.OttieniScoreboard();
                occupato = false;
                TOS.notifyAll();
            }

        }

    }
}
