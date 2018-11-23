/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import deadzone.Handler;
import java.awt.Graphics;

/**
 *
 * @author USER
 */
public class DropItem extends Sprite{

    private final float probAmmo = 50;
    private final float probNuke = 20;
    private final float probMK = 30;
    
    public DropItem(float x, float y, int width, int height, Handler handler) {
        super(x, y, width, height);
        float valoreCasuale = (float) (Math.random()*100);
        if(valoreCasuale < probNuke){
            handler.addSprite(new Nuke(x,y,width,height));
        }
        else if(valoreCasuale < probNuke+probMK){
            handler.addSprite(new MedicalKit(x,y,width,height));
        }
        else if(valoreCasuale <= probNuke+probMK+probAmmo){
            handler.addSprite(new Ammo(x,y,width,height));
        }
        else{
            System.out.println("NEL COSTRUTTORE DI DROP ITEM, NON DOVREBBE STAMPARE STO MESSAGGIO");
        }
                
    }

    @Override
    public void drawImage(Graphics g, float offsetX, float offsetY) {};

    @Override
    public void animationCycle(){} ;

}
