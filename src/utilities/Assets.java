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

		public static BufferedImage[] zombie = new BufferedImage[17];
		public static BufferedImage[] zombieAttack = new BufferedImage[9];


		// guns skin
		public static BufferedImage pistolSkin, ak47, rifleLoader;
		
		
		//sounds		
		public static Clip pistolShoot, rifleShoot, shotgunShoot, background, zombieHit, pistolReloadSound,
		rifleReloadSound, shotgunReloadSound,emptyGun, zombieBite;
		
		//blood
                public static BufferedImage blood; 

		//Item 
                public static BufferedImage mediKit;
                public static BufferedImage ammo;
                public static BufferedImage nuke;
                
		public static void init(){
			
                    // animations
                    
                    //idle
                    for(int i = 0; i<pistolIdle.length; i++)
                            pistolIdle[i] = Utilities.loadImage("/player/idle/pistolIdle/"+i+".png");

                    for(int i = 0; i<rifleIdle.length; i++)
                            rifleIdle[i] = Utilities.loadImage("/player/idle/rifleIdle/"+i+".png");

                    for(int i = 0; i<shotgunIdle.length; i++)
                            shotgunIdle[i] = Utilities.loadImage("/player/idle/shotgunIdle/"+i+".png");
                    
                    //reload
                    for(int i = 0; i<pistolReload.length; i++)
                            pistolReload[i] = Utilities.loadImage("/player/reload/pistol/"+i+".png");

                    for(int i = 0; i<rifleReload.length; i++)
                            rifleReload[i] = Utilities.loadImage("/player/reload/rifle/"+i+".png");

                    for(int i = 0; i<shotgunReload.length; i++)
                            shotgunReload[i] = Utilities.loadImage("/player/reload/shotgun/"+i+".png");
                   
                    //shot
                    for(int i = 0; i<pistolShootAnim.length; i++)
                            pistolShootAnim[i] = Utilities.loadImage("/player/shoot/pistol/"+i+".png");

                    for(int i = 0; i<rifleShootAnim.length; i++)
                            rifleShootAnim[i] = Utilities.loadImage("/player/shoot/rifle/"+i+".png");

                    for(int i = 0; i<shotgunShootAnim.length; i++)
                            shotgunShootAnim[i] = Utilities.loadImage("/player/shoot/shotgun/"+i+".png");
                    
                    //zombie walk
                    for(int i = 0; i<zombie.length; i++)
                            zombie[i] = Utilities.loadImage("/zombie/walk/"+i+".png");
                    
                    //zombie attack
                    for(int i = 0; i<zombieAttack.length; i++)
                            zombieAttack[i] = Utilities.loadImage("/zombie/attack/"+i+".png");
                    

                    //gun skins

                    pistolSkin = Utilities.loadImage("/guns/pistol.png");
                    ak47 = Utilities.loadImage("/guns/ak-47.png");
                    rifleLoader = Utilities.loadImage("/guns/rifleLoader.png");
                    
                    //item
                    mediKit = Utilities.loadImage("/images/mediKitItem.png");
                    ammo = Utilities.loadImage("/images/ammoItem.png");
                    nuke = Utilities.loadImage("/images/bombItem.png");
                    
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
                    
                    shotgunReloadSound = Utilities.LoadSound("/sound/pistolReload.wav");
                    shotgunShoot = Utilities.LoadSound("/sound/pistol.wav");

		}
}
