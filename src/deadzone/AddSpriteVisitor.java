/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import deadzone.sprite.*;
import deadzone.sprite.animated.*;
import deadzone.trap.Trap;

/**
 *
 * @author casang
 */
public class AddSpriteVisitor implements SpriteVisitor{
    private Handler handler;
    
    public AddSpriteVisitor(Handler handler){
        this.handler = handler;
    }
    
    @Override
    public void visit(Zombie zombie){
        handler.getZombies().add(zombie);
    }
    
    @Override
    public void visit(PlayerFactory player){
        handler.getPlayers().add(player);
    }
    
    @Override
    public void visit(Projectile projectile){
        handler.getProjectiles().add(projectile);
    }
    
    @Override
    public void visit(DropItem dropItem){
        handler.getItems().add(dropItem);
    }
    
    @Override
    public void visit(Trap trap){
        handler.getTraps().add(trap);
    }
    
    @Override
    public void visit(Spittle spittle){
        handler.getSpittles().add(spittle);
    }
    
    @Override
    public void visit(SpawnSpittle spawnSpittle){
        handler.getspawnSpittles().add(spawnSpittle);
    }
    
    @Override
    public void visit(Circle circle){
        handler.getCircle().add(circle);
    }
    
    @Override
    public void visit(Blood blood){
        handler.getBloods().add(blood);
    }
}
