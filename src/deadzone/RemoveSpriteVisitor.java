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
public class RemoveSpriteVisitor implements SpriteVisitor{
    private Handler handler;
    
    public RemoveSpriteVisitor(Handler handler){
        this.handler = handler;
    }
    
    @Override
    public void visit(Zombie zombie){
        handler.getZombies().remove(zombie);
    }
    
    @Override
    public void visit(PlayerFactory player){
        handler.getPlayers().remove(player);
    }
    
    @Override
    public void visit(Projectile projectile){
        handler.getProjectiles().remove(projectile);
    }
    
    @Override
    public void visit(DropItem dropItem){
        handler.getItems().remove(dropItem);
    }
    
    @Override
    public void visit(Trap trap){
        handler.getTraps().remove(trap);
    }
    
    @Override
    public void visit(Spittle spittle){
        handler.getSpittles().remove(spittle);
    }
    
    @Override
    public void visit(SpawnSpittle spawnSpittle){
        handler.getspawnSpittles().remove(spawnSpittle);
    }
    
    @Override
    public void visit(Circle circle){
        handler.getCircle().remove(circle);
    }
    
    @Override
    public void visit(Blood blood){
        handler.getBloods().remove(blood);
    }
}
