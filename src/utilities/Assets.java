package utilities;

import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

public class Assets {

    // sprite sheets	
    public static BufferedImage[] pistolIdle = new BufferedImage[20];
    public static BufferedImage[] rifleIdle = new BufferedImage[20];
    public static BufferedImage[] shotgunIdle = new BufferedImage[20];

    public static BufferedImage[] pistolReload = new BufferedImage[15];
    public static BufferedImage[] rifleReload = new BufferedImage[20];
    public static BufferedImage[] shotgunReload = new BufferedImage[20];

    public static BufferedImage[] pistolShootAnim = new BufferedImage[3];
    public static BufferedImage[] rifleShootAnim = new BufferedImage[3];
    public static BufferedImage[] shotgunShootAnim = new BufferedImage[3];

    public static BufferedImage[] femalepistolIdle = new BufferedImage[20];
    public static BufferedImage[] femalerifleIdle = new BufferedImage[20];
    public static BufferedImage[] femaleshotgunIdle = new BufferedImage[20];

    public static BufferedImage[] femalepistolReload = new BufferedImage[15];
    public static BufferedImage[] femalerifleReload = new BufferedImage[20];
    public static BufferedImage[] femaleshotgunReload = new BufferedImage[20];

    public static BufferedImage[] femalepistolShootAnim = new BufferedImage[3];
    public static BufferedImage[] femalerifleShootAnim = new BufferedImage[3];
    public static BufferedImage[] femaleshotgunShootAnim = new BufferedImage[3];

    public static BufferedImage[] zombie = new BufferedImage[17];
    public static BufferedImage[] zombieAttack = new BufferedImage[9];

    public static BufferedImage[] zombie2 = new BufferedImage[32];
    public static BufferedImage[] zombie2Attack = new BufferedImage[20];

    // guns skin
    public static BufferedImage pistolSkin, ak47, rifleLoader, shotgunSkin;

    //spittle
    public static BufferedImage spittle_n;
    public static BufferedImage spittle_ne;
    public static BufferedImage spittle_e;
    public static BufferedImage spittle_se;
    public static BufferedImage spittle_s;
    public static BufferedImage spittle_so;
    public static BufferedImage spittle_o;
    public static BufferedImage spittle_no;

    //sounds		
    public static Clip pistolShoot, rifleShoot, shotgunShoot, background, zombieHit, pistolReloadSound,
            rifleReloadSound, shotgunReloadSound, emptyGun, zombieBite;
    public static Clip endGame, endOfRound;

    //blood
    public static BufferedImage blood;

    //Item 
    public static BufferedImage mediKit;
    public static BufferedImage ammo;
    public static BufferedImage nuke;

    public static BufferedImage minimap;
    public static BufferedImage greenIndicator;
    public static BufferedImage redIndicator;

    public static void init() {

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

        //gun skins
        shotgunSkin = Utilities.loadImage("/guns/shotgun.png");
        pistolSkin = Utilities.loadImage("/guns/pistol.png");
        ak47 = Utilities.loadImage("/guns/ak-47.png");
        rifleLoader = Utilities.loadImage("/guns/rifleLoader.png");


        //blood 
        blood = Utilities.loadImage("/images/blood.png");
        //sounds
        pistolShoot = Utilities.LoadSound("/sound/pistol.wav");
        rifleShoot = Utilities.LoadSound("/sound/machinegun.wav");
        pistolReloadSound = Utilities.LoadSound("/sound/pistolReload.wav");
        rifleReloadSound = Utilities.LoadSound("/sound/rifleReload.wav");
        emptyGun = Utilities.LoadSound("/sound/emptygun.wav");
        zombieBite = Utilities.LoadSound("/sound/zombieBite.wav");
        zombieHit = Utilities.LoadSound("/sound/zombiehit.wav");

        endGame = Utilities.LoadSound("/sound/endGame.wav");
        endOfRound = Utilities.LoadSound("/sound/round.wav");

        shotgunReloadSound = Utilities.LoadSound("/sound/shotgunReload.wav");
        shotgunShoot = Utilities.LoadSound("/sound/shotgun.wav");

        //spittle
        spittle_n = Utilities.loadImage("/zombie/spittle/nord.png");
        spittle_ne = Utilities.loadImage("/zombie/spittle/nordest.png");
        spittle_e = Utilities.loadImage("/zombie/spittle/est.png");
        spittle_se = Utilities.loadImage("/zombie/spittle/sudest.png");
        spittle_s = Utilities.loadImage("/zombie/spittle/sud.png");
        spittle_so = Utilities.loadImage("/zombie/spittle/sudovest.png");
        spittle_o = Utilities.loadImage("/zombie/spittle/ovest.png");
        spittle_no = Utilities.loadImage("/zombie/spittle/nordovest.png");

        //item
        mediKit = Utilities.loadImage("/images/medikitItem.png");
        ammo = Utilities.loadImage("/images/ammoItem.png");
        nuke = Utilities.loadImage("/images/bombItem.png");

        //minimap
                    minimap = Utilities.loadImage("/images/grigionero.png");
                    greenIndicator = Utilities.loadImage("/images/green.png");
                    redIndicator = Utilities.loadImage("/images/red.png");
    }
}
