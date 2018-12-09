/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite.animated;

import java.awt.geom.AffineTransform;
import sprite.Sprite;

/**
 *
 * @author giova
 */
public abstract class AnimatedSprite extends Sprite {

    //essendo protected, non ho bisono di get e set
    protected float angle;
    private int health;
    protected final float initialVelocity;
    public float velX, velY;
    protected AffineTransform at;

    public AnimatedSprite(float x, float y, int width, int height, float vel, int health) {
        super(x, y, width, height);
        this.health = health;
        this.initialVelocity = vel;
        this.velX = 0;
        this.velY = 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int collision(float velx, float vely, Float xf, Float yf) {
        int xx, yy;
        //direzione
        int avanzamentoX = (int) velx;
        int avanzamentoY = (int) vely;
        //variabili utili
        int red = 0;
        int pixel = 0;
        boolean collisione1 = false;
        boolean collisione2 = false;
        boolean collisione3 = false;
        //dove ero
        int x = xf.intValue() - avanzamentoX;
        int y = yf.intValue() - avanzamentoY;
        
        try{
        if (avanzamentoX > 0) {
            if (avanzamentoY == 0) {
                //destra
                for (xx = x + width; xx < x + width + avanzamentoX; xx++) {
                    for (yy = y; yy < y + height; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            return 1;
                        }
                    }
                }
            } else if (avanzamentoY > 0) {
                //destra-sotto
                //controllo destra
                for (xx = x + width; xx < x + width + avanzamentoX; xx++) {
                    for (yy = y; yy < y + height; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            collisione1 = true;
                            break;
                        }
                    }
                }
                //controllo sotto
                for (xx = x; xx < x + width; xx++) {
                    for (yy = y + height; yy < y + height + avanzamentoY; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            collisione2 = true;
                            break;
                        }
                    }
                }
                //controllo la diagonale
                for (xx = x + width; xx < x + width + avanzamentoX; xx++) {
                    for (yy = y + height; yy < y + height + avanzamentoY; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            collisione3 = true;
                            break;
                        }
                    }
                }

                if (collisione3 == true && collisione1 == false && collisione2 == false) {
                    return 3;
                } else if (collisione3 == true && collisione1 == true && collisione2 == false) {
                    return 1;
                } else if (collisione3 == true && collisione2 == true && collisione1 == false) {
                    return 2;
                } else if (collisione1 == true && collisione2 == true & collisione3 == true) {
                    return 3;
                } else if (collisione1 == true && collisione2 == true) {
                    return 3;
                } else if (collisione1 == true) {
                    return 1;
                } else if (collisione2 == true) {
                    return 2;
                }
            } else {
                //destra-sopra
                //controllo destra
                for (xx = x + width; xx < x + width + avanzamentoX; xx++) {
                    for (yy = y; yy < y + height; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            collisione1 = true;
                            break;
                        }
                    }
                }
                //controllo sopra
                for (xx = x; xx < x + width; xx++) {
                    for (yy = y + avanzamentoY; yy < y; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            collisione2 = true;
                            break;
                        }
                    }
                }
                //controllo la diagonale
                for (xx = x + width; xx < x + width + avanzamentoX; xx++) {
                    for (yy = y + avanzamentoY; yy < y; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            collisione3 = true;
                            break;
                        }
                    }
                }
                if (collisione3 == true && collisione1 == false && collisione2 == false) {
                    return 3;
                } else if (collisione3 == true && collisione1 == true && collisione2 == false) {
                    return 1;
                } else if (collisione3 == true && collisione2 == true && collisione1 == false) {
                    return 2;
                } else if (collisione1 == true && collisione2 == true && collisione3 == true) {
                    return 3;
                } else if (collisione1 == true && collisione2 == true) {
                    return 3;
                } else if (collisione2 == true) {
                    return 2;
                } else if (collisione1 == true) {
                    return 1;
                }

            }

        } else if (avanzamentoX < 0) {
            if (avanzamentoY == 0) {
                //sinistra 
                for (xx = x + avanzamentoX; xx < x; xx++) {
                    for (yy = y; yy < y + height; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            return 1;
                        }
                    }
                }
            } else if (avanzamentoY > 0) {
                //sinistra sotto
                //sinistra 
                for (xx = x + avanzamentoX; xx < x; xx++) {
                    for (yy = y; yy < y + height; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            collisione1 = true;
                            break;
                        }
                    }
                }
                //sotto
                for (xx = x; xx < x + width; xx++) {
                    for (yy = y + height; yy < y + height + avanzamentoY; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            collisione2 = true;
                            break;
                        }
                    }
                }
                //controllo la diagonale
                for (xx = x + avanzamentoX; xx < x; xx++) {
                    for (yy = y + height; yy < y + height + avanzamentoY; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            collisione3 = true;
                            break;
                        }
                    }
                }
                if (collisione3 == true && collisione1 == false && collisione2 == false) {
                    return 3;
                } else if (collisione3 == true && collisione1 == true && collisione2 == false) {
                    return 1;
                } else if (collisione3 == true && collisione2 == true && collisione1 == false) {
                    return 2;
                } else if (collisione1 == true && collisione2 == true & collisione3 == true) {
                    return 3;
                } else if (collisione1 == true && collisione2 == true) {
                    return 3;
                } else if (collisione1 == true) {
                    return 1;
                } else if (collisione2 == true) {
                    return 2;
                }
            } else {
                //sinistra sopra
                //sinistra 
                for (xx = x + avanzamentoX; xx < x; xx++) {
                    for (yy = y; yy < y + height; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            collisione1 = true;
                            break;
                        }
                    }
                }
                //controllo sopra
                for (xx = x; xx < x + width; xx++) {
                    for (yy = y + avanzamentoY; yy < y; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            collisione2 = true;
                            break;
                        }
                    }
                }
                //controllo la diagonale
                for (xx = x + avanzamentoX; xx < x; xx++) {
                    for (yy = y + avanzamentoY; yy < y; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            collisione3 = true;
                            break;
                        }
                    }
                }
                if (collisione3 == true && collisione1 == false && collisione2 == false) {
                    return 3;
                } else if (collisione3 == true && collisione1 == true && collisione2 == false) {
                    return 1;
                } else if (collisione3 == true && collisione2 == true && collisione1 == false) {
                    return 2;
                } else if (collisione1 == true && collisione2 == true & collisione3 == true) {
                    return 3;
                } else if (collisione1 == true && collisione2 == true) {
                    return 3;
                } else if (collisione1 == true) {
                    return 1;
                } else if (collisione2 == true) {
                    return 2;
                }
            }
        } else {
            if (avanzamentoY == 0) {
                return 0;
            } else if (avanzamentoY > 0) {
                //sotto
                for (xx = x; xx < x + width; xx++) {
                    for (yy = y + height; yy < y + height + avanzamentoY; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            return 2;
                        }
                    }
                }
            } else {
                //sopra
                for (xx = x; xx < x + width; xx++) {
                    for (yy = y + avanzamentoY; yy < y; yy++) {
                        pixel = mapRGB.getRGB((int) xx, (int) yy);
                        red = (pixel >> 16) & 0xff;
                        if (red == 255) {
                            return 2;
                        }
                    }
                }
            }
        }
        }catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Eccezione in collisioni con queste coordinate: ");
            System.err.println("X: " + x +" --- Y: " + y);
        }
        return 0;
    }

    public float getInitialVelocity() {
        return initialVelocity;
    }
    
    

}
