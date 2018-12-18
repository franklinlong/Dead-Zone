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
public class PlayerMale extends PlayerFactory{
    
    public PlayerMale(float x, float y, int vel, int health){
        super(x, y, vel, health);
        initPlayer();
    }

    @Override
    public void initPlayer() {
                
        this.male = true;
        
        pistolIdle = new Animation(Assets.pistolIdle, 20);
        pistolReload = new Animation(Assets.pistolReload, 100);
        pistolShoot = new Animation(Assets.pistolShootAnim, 80);

        rifleIdle = new Animation(Assets.rifleIdle, 20);
        rifleReload = new Animation(Assets.rifleReload, 100);
        rifleShoot = new Animation(Assets.rifleShootAnim, 80);

        shotgunIdle = new Animation(Assets.shotgunIdle, 20);
        shotgunReload = new Animation(Assets.shotgunReload, 100);
        shotgunShoot = new Animation(Assets.shotgunShootAnim, 80);
        
        rpgIdle = new Animation(Assets.rpgIdle, 20);
        rpgReload = new Animation(Assets.rpgReload, 100);
        rpgShoot = new Animation(Assets.rpgShootAnim, 80);
        
        pistol = new Gun(Assets.pistolSkin, pistolIdle, pistolReload, pistolShoot, pistolShootSound,
                pistolReloadSound, this, 400,
                9, 200, 50);
        rifle = new Gun(Assets.ak47, rifleIdle, rifleReload, rifleShoot, rifleShootSound,
                rifleReloadSound, this, 100,
                30, 200, 34);

        shotgun = new Gun(Assets.shotgunSkin, shotgunIdle, shotgunReload, shotgunShoot, shotgunShootSound,
                shotgunReloadSound, this, 800,
                5, 200, 45);
        
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
        return true;
    }
    
    @Override
    public void setName(String name){
        this.name=name;
    }
}
