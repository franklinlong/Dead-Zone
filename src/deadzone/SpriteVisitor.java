/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import deadzone.sprite.animated.*;
import deadzone.sprite.*;
import deadzone.trap.Trap;

/**
 *
 * @author casang
 */
public interface SpriteVisitor {
    public void visit(Zombie zombie);
    public void visit(PlayerFactory player);
    public void visit(Projectile projectile);
    public void visit(DropItem dropItem);
    public void visit(Trap trap);
    public void visit(Spittle spittle);
    public void visit(SpawnSpittle spawnSpittle);
    public void visit(Circle circle);
    public void visit(Blood blood);
    
    
}
