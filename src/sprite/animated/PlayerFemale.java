/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite.animated;

import deadzone.Gun;
import utilities.Animation;
import utilities.Assets;

/**
 *
 * @author casang
 */
public class PlayerFemale extends Player{
    
    public PlayerFemale(float x, float y, int vel, int health, String name){
        super(x, y, vel, health, name);
        
        pistolIdle = new Animation(Assets.femalepistolIdle, 20);
        pistolReload = new Animation(Assets.femalepistolReload, 100);
        pistolShoot = new Animation(Assets.femalepistolShootAnim, 80);

        rifleIdle = new Animation(Assets.femalerifleIdle, 20);
        rifleReload = new Animation(Assets.femalerifleReload, 100);
        rifleShoot = new Animation(Assets.femalerifleShootAnim, 80);

        shotgunIdle = new Animation(Assets.femaleshotgunIdle, 20);
        shotgunReload = new Animation(Assets.femaleshotgunReload, 100);
        shotgunShoot = new Animation(Assets.femaleshotgunShootAnim, 80);
        
        pistol = new Gun(Assets.pistolSkin, pistolIdle, pistolReload, pistolShoot, pistolShootSound,
                pistolReloadSound, this, 400,
                9, 200, 50);
        rifle = new Gun(Assets.ak47, rifleIdle, rifleReload, rifleShoot, rifleShootSound,
                rifleReloadSound, this, 100,
                30, 200, 34);

        shotgun = new Gun(Assets.shotgunSkin, shotgunIdle, shotgunReload, shotgunShoot, shotgunShootSound,
                shotgunReloadSound, this, 800,
                5, 200, 45);
        
        currentGun = pistol;
    }
}
