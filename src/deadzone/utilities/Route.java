/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.utilities;

import deadzone.graph.Edge;
import deadzone.graph.Vertex;
import deadzone.Handler;
import deadzone.menu.Menu;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import deadzone.sprite.Sprite;
import deadzone.sprite.SpriteInterface;
import deadzone.sprite.animated.PlayerFactory;
import deadzone.sprite.animated.Zombie;

/**
 *
 * @author USER
 */
//Definisce il percorso tra uno zombie e un player
public class Route {

    private final Sprite target;
    private final Zombie zombie;
    private final Handler handler;

    public Route(Sprite target, Zombie zombie, Handler handler) {
        this.target = target;
        this.zombie = zombie;
        this.handler = handler;
    }

    public float[] raggiungiZona() {

        //Se si trovano nella stessa zona
        if (zombie.getZona().equals(((PlayerFactory) target).getZona())) {
            return seek(target.getX(), target.getY(), target.width, target.height);
        } else {
            //System.out.println("player: "+((Player) target).getZona().getIndex());
            Vertex origin = new Vertex(zombie.getZona().getIndex());
            Edge edge = ((PlayerFactory) target).getCamminiMinimi().get(origin);
//            System.out.println("origin: "+origin);
//            System.out.println("edge: "+edge);
            Vertex destination;
            try {
                destination = edge.opposite(origin);
                float c[] = Zona.centro(destination.getElement());
                float c_x = c[0];
                float c_y = c[1];
//                System.out.println("destionation1: "+destination);
                return seek(c_x, c_y, 0, 0);
            } catch (NullPointerException e) {
                float c[] = Zona.centro(origin.getElement());
                float c_x = c[0];
                float c_y = c[1];
//                System.out.println("destionation2: "+origin);
                return seek(c_x, c_y, 0, 0);
            }
        }
    }



    //Restituisce la velocitaX e velocitaY per avvicinarsi al target in maniera standard senza ostacoli
    public float[] seek(float x_t, float y_t, int t_width, int t_height){

        float[] a;
        a = new float[2];
        
        
        float x = ((int)x_t + t_width/2 ) - ((int)zombie.getX() + zombie.width/2);
        float y  = ((int)y_t + t_height/2 ) - ((int)zombie.getY() + zombie.height/2);

        if(y < zombie.getInitialVelocity() && y > -1*zombie.getInitialVelocity()){
            y = 0;
        }
        if(x < zombie.getInitialVelocity() && x > -1*zombie.getInitialVelocity()){
            x = 0;
        }
        
        if(x == 0 && y ==0){
            //System.out.println("Entrambi zero in seek()");
            a[0] = 0;
            a[1] = 0;
        }
        else{
            x = (x/ (float)Math.sqrt(x*x + y*y));
            y = (y/ (float)Math.sqrt(x*x + y*y));

            x = x * zombie.getInitialVelocity();
            y = y * zombie.getInitialVelocity();

            a[0] = x;
            a[1] = y;
        }
        
        return a;
    }

    public ArrayList<Zombie> evitaZombies(int x, int y, List<SpriteInterface> zombies) {
        ArrayList<Zombie> vicini = new ArrayList<Zombie>();

        //aggiorno le variabili dello zombie in modo da vedere se nella nuova posizione ci sono atri zombie
        this.zombie.setX(this.zombie.getX() + x);
        this.zombie.setY(this.zombie.getY() + y);

        for (int i = 0; i < zombies.size(); i++) {
            Zombie s = (Zombie) zombies.get(i);

            if (!s.equals(this.zombie)) {
                Rectangle r = s.getBounds();
                r.setRect(s.getX() + s.width * 5 / 12, s.getY() + s.height * 5 / 12, s.width / 6, s.height / 6);
                if (this.zombie.getBounds().intersects(r)) {
                    this.zombie.setX(this.zombie.getX() - x);
                    this.zombie.setY(this.zombie.getY() - y);
                    vicini.add(s);
                }
            }
        }

        //Faccio tornare o zombie alla posizione iniziale perchè vanno fatti tutti i vari controlli nella classe zombie
        this.zombie.setX(this.zombie.getX() - x);
        this.zombie.setY(this.zombie.getY() - y);

        return vicini;
    }

    public float[] gestisciOstacoli(float velX, float velY) {
        
        //Velocità fittizie per capire se ci sono ostacoli
        int vx = 0;
        int vy = 0;
        if (velX < 0) {
            vx = -1*(int)zombie.getInitialVelocity();
        } else if (velX > 0) {
            vx = +1*(int)zombie.getInitialVelocity();
        }
        if (velY < 0) {
            vy = -1*(int)zombie.getInitialVelocity();
        } else if (velY > 0) {
            vy = +1*(int)zombie.getInitialVelocity();
        }

        //Codice per ricalcolare la direzione in base alla presenza di zombie vicini
        if(!Menu.demo){
            ArrayList<Zombie> vicino = this.evitaZombies(vx, vy, handler.getZombies());
            if (!vicino.isEmpty()) {
                for (int i = 0; i < vicino.size(); i++) {
                    Route r2 = new Route(this.zombie, vicino.get(i), handler);
                    float[] a = r2.seek(this.zombie.getX(),this.zombie.getY(),this.zombie.width,this.zombie.height);
                    velX = (velX + a[0]);
                    velY = (velY + a[1]);
                }
                velX = velX /10;
                velY = velY /10;

                if(velX > zombie.getInitialVelocity())
                    velX = zombie.getInitialVelocity();
                if(velX < -zombie.getInitialVelocity())
                    velX = -zombie.getInitialVelocity();
                if(velY > zombie.getInitialVelocity())
                    velY = zombie.getInitialVelocity();
                if(velY < -zombie.getInitialVelocity())
                    velY = -zombie.getInitialVelocity();
            } 
        }
        float x = zombie.getX();
        float y = zombie.getY();

        //Aggiorno la posizione dello zombie in base ai calcoli sul percorso
        x += vx;
        y += vy;

        //aggiorno le variabili dello sprite per come funziona collision
        zombie.setX(x);
        zombie.setY(y);

        //Se c'è una collisione non posso passare
        int k = zombie.collision(vx, vy, x, y);
        switch (k) {
            case 1:
                x -= vx;
                break;
            case 2:
                y -= vy;
                break;
            case 3:
                x -= vx;
                y -= vy;
                break;
            default:
                x = x - vx + velX;
                y = y - vy + velY;
                break;
        }

        float[] a = new float[2];
        a[0] = x;
        a[1] = y;

        return a;
    }

}
