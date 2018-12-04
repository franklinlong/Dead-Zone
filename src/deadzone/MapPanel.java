
package deadzone;

import gameMenu.PauseMenu;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import listeners.KAdapter;
import listeners.MAdapter;
import sprite.animated.Player;
import utilities.Scoreboard;

public class MapPanel extends JPanel implements Runnable{
    
    private Image mapImage;
    private Handler handler;
    private KAdapter kAdapt;
    private MAdapter mAdapt;
    
    public MapPanel(Handler h){
    this.handler = h;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    this.setSize(new java.awt.Dimension((int) dim.getWidth()*4/5,(int) dim.getHeight()));
    initMapPanel();
    }
    
    public void initMapPanel(){
        loadMap();
        kAdapt = new KAdapter();
        mAdapt = new MAdapter();
        
        this.addMouseListener(mAdapt);
        this.addMouseMotionListener(mAdapt);
        this.addKeyListener(kAdapt);
        
        this.setFocusable(true);
        this.requestFocusInWindow(); 
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ImageIcon image = new ImageIcon(getClass().getResource("/images/mirino_trasparente.png"));
        Image immagine = image.getImage();
        
        Cursor c = toolkit.createCustomCursor(immagine, new Point(20, 20), "Cursore mirino");
        this.setCursor (c);

        
    }
    
    private void loadMap(){
    	ImageIcon map = new ImageIcon("map.png");
        mapImage = map.getImage();
    }
    
    
    
        @Override
    public void run() {
        int fps = 60;
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long ticks = 0;
        long timer = 0;
        
        while(!handler.getPlayer().isDeath() && !PauseMenu.end){
        
            now = System.nanoTime();
            delta += (now - lastTime)/timePerTick;
            timer += now - lastTime;
            lastTime = now;
            while(delta >= 1){
                    animationCycle();
                    repaint();
                    this.setFocusable(true);
                    this.requestFocusInWindow(); 
                    
                    delta --;
                    ticks++;
            }
            if(timer >= 1000000000){
                    
                    ticks = 0;
                    timer = 0;
            }
            
        }
        
        handler.spawn.stop();
        System.out.println("FINE PARTITA MAP PANEL");
        
        if (handler.getPlayer().isDeath()){
            Player p = this.handler.getPlayer();
            new Scoreboard().addScore(p.getName(), p.getPunteggioAttuale());
        }
        
    }
    
    public void animationCycle(){
        
        kAdapt.update();
        handler.animationCycle();
    }
   
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        drawMap(g);
        handler.drawImage(g);        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void drawMap(Graphics g){
        g.drawImage(mapImage, (int) -handler.getCamera().getOffset_x(),(int) -handler.getCamera().getOffset_y(), this);
        
    }
}

