/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Zona {
    
    private int index;
    private ArrayList<Integer> zoneAdiacenti;
    
    public Zona(float posX, float posY){
        zoneAdiacenti = new ArrayList<>();
        index = 1;
        zoneAdiacenti.add(2);
        zoneAdiacenti.add(3);
        
    }
 
    public boolean equals(Zona z){
        return this.index == z.index;
    }
    
    public boolean isNear(Zona z){
        return this.zoneAdiacenti.contains(z.index);
    }
    
    public void aggiorna(){
        index = 1;
    }
}
