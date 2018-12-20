/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.sprite.animated;

import deadzone.Gun;
import deadzone.utilities.Animation;
import deadzone.utilities.Assets;

/**
 *
 * @author casang
 */
public class PlayerFemale extends PlayerFactory{
    
    public PlayerFemale(float x, float y, int vel, int health){
        super(x, y, vel, health);
        initPlayer();
    }
    
    @Override
    public void initPlayer(){
                
        this.male = false;
        
        pistolIdle = new Animation(Assets.femalepistolIdle, 20);
        pistolReload = new Animation(Assets.femalepistolReload, 100);
        pistolShoot = new Animation(Assets.femalepistolShootAnim, 80);

        rifleIdle = new Animation(Assets.femalerifleIdle, 20);
        rifleReload = new Animation(Assets.femalerifleReload, 100);
        rifleShoot = new Animation(Assets.femalerifleShootAnim, 80);

        shotgunIdle = new Animation(Assets.femaleshotgunIdle, 20);
        shotgunReload = new Animation(Assets.femaleshotgunReload, 100);
        shotgunShoot = new Animation(Assets.femaleshotgunShootAnim, 80);
        
        rpgIdle = new Animation(Assets.femalerpgIdle, 20);
        rpgReload = new Animation(Assets.femalerpgReload, 100);
        rpgShoot = new Animation(Assets.femalerpgShootAnim, 80);    
        
        
        pistol = new Gun(Assets.pistolSkin, pistolIdle, pistolReload, pistolShoot, pistolShootSound,
                pistolReloadSound, this, 400,
                15, 200, 50);
        rifle = new Gun(Assets.ak47, rifleIdle, rifleReload, rifleShoot, rifleShootSound,
                rifleReloadSound, this, 100,
                35, 105, 34);

        shotgun = new Gun(Assets.shotgunSkin, shotgunIdle, shotgunReload, shotgunShoot, shotgunShootSound,
                shotgunReloadSound, this, 800,
                8, 24, 45);
        
        rpg = new Gun(Assets.rpgSkin, rpgIdle, rpgReload, rpgShoot, rpgShootSound,
                rpgReloadSound, this, 1200,
                1, 0, 700);
        
        currentGun = pistol;
        
        allGuns[0] = pistol;
        allGuns[1] = rifle;
        allGuns[2] = shotgun;
        allGuns[3] = rpg;
    }
    
    @Override
    public boolean isMale(){
        return false;
    }
    
    @Override
    public void setName(String name){
        this.name=name;
    }
}
