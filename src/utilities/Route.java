/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import Graph.*;
import deadzone.Handler;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import sprite.Sprite;
import sprite.animated.Player;
import sprite.animated.Zombie;

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
        if(zombie.getZona().equals(((Player) target).getZona())){
            return seek(target.getX(), target.getY(), target.width, target.height);
        }
        else {
            Vertex origin = new Vertex(zombie.getZona().getIndex());
            Edge edge = ((Player) target).getCamminiMinimi().get(origin);
            Vertex destination = edge.opposite(origin);
            float c[] = Zona.centro(destination.getElement());
            float c_x = c[0];
            float c_y = c[1];
            return seek(c_x, c_y, 0, 0);
        }
    }

    //Restituisce la velocitaX e velocitaY per avvicinarsi al target in maniera standard senza ostacoli
    public float[] seek(float x_t, float y_t, int t_width, int t_height){

        float[] a;
        a = new float[2];
        
        float x = (x_t + t_width/2 ) - (zombie.getX() + zombie.width/2);
        float y  = (y_t + t_height/2 ) - (zombie.getY() + zombie.height/2);
        
        x = (x/ (float)Math.sqrt(x*x + y*y));
        y = (y/ (float)Math.sqrt(x*x + y*y));
        
        x = x * zombie.velX;
        y = y * zombie.velY;
        
        a[0] = x;
        a[1] = y;
        
        return a;
    }

    public ArrayList<Zombie> evitaZombies(int x, int y, List<Sprite> zombies) {
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
            vx = -zombie.getInitialVelocity();
        } else if (velX > 0) {
            vx = +zombie.getInitialVelocity();
        }
        if (velY < 0) {
            vy = -zombie.getInitialVelocity();
        } else if (velY > 0) {
            vy = +zombie.getInitialVelocity();
        }

        //Codice per ricalcolare la direzione in base alla presenza di zombie vicini ... DA MODIFICARE
//        ArrayList<Zombie> vicino = this.evitaZombies(vx, vy, handler.getZombies());
//        if (!vicino.isEmpty()) {
//            for (int i = 0; i < vicino.size(); i++) {
//                Route r2 = new Route(this.zombie, vicino.get(i), handler);
//                velX = (velX + r2.seek()[0]);
//                velY = (velY + r2.seek()[1]);
//            }
//        } else {
            if (vx != 0 && vy != 0) {
                zombie.setAngle((float) Math.acos(velX));
                if (velY < 0) {
                    zombie.setAngle(zombie.getAngle() * -zombie.getInitialVelocity());
                }
            }
        //}

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
