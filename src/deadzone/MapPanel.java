package deadzone;

import deadzone.listeners.FListener;
import deadzone.menu.PauseMenu;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import deadzone.listeners.KAdapter;
import deadzone.listeners.MAdapter;
import deadzone.menu.Menu;
import deadzone.sprite.Sprite;
import deadzone.sprite.SpriteInterface;
import deadzone.sprite.animated.PlayerDemo;
import deadzone.sprite.animated.PlayerFactory;
import deadzone.trap.Trap;
import deadzone.utilities.Assets;
import deadzone.utilities.Scoreboard;
import java.awt.event.KeyEvent;
import java.util.Iterator;

public class MapPanel extends JPanel implements Runnable {

    private Image mapImage;
    private Handler handler;
    private KAdapter kAdapt;
    private MAdapter mAdapt;
    private Camera camera;
    private FListener fListener;
    
    public MapPanel(Handler h) {
        this.handler = h;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(new java.awt.Dimension((int) dim.getWidth() * 4 / 5, (int) dim.getHeight()));
        camera = new Camera(handler.getPlayer());
        initMapPanel();
    }

    public void initMapPanel() {
        loadMap();
        kAdapt = new KAdapter();
        mAdapt = new MAdapter();
        fListener = new FListener();
        
        this.addMouseListener(mAdapt);
        this.addMouseMotionListener(mAdapt);
        this.addKeyListener(kAdapt);
        this.addFocusListener(fListener);
        
        if(Menu.demo)
            ((PlayerDemo)handler.getPlayer()).setWindowDemo(this);
        
        this.setFocusable(true);
        this.requestFocusInWindow();

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ImageIcon image = new ImageIcon(getClass().getResource("/images/mirino_trasparente.png"));
        Image immagine = image.getImage();

        Cursor c = toolkit.createCustomCursor(immagine, new Point(20, 20), "Cursore mirino");
        this.setCursor(c);

    }

    private void loadMap() {
        ImageIcon map = new ImageIcon("map.png");
        mapImage = map.getImage();
    }

    @Override
    public void run() {
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long ticks = 0;
        long timer = 0;

        while (!handler.getPlayer().isDeath() && PauseMenu.end == false) {

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            while (delta >= 1) {
                animationCycle();
                repaint();
                this.setFocusable(true);
                this.requestFocusInWindow();

                delta--;
                ticks++;
            }
            if (timer >= 1000000000) {

                ticks = 0;
                timer = 0;
            }

        }

        //handler.spawn.stop();
        System.out.println("FINE PARTITA MAP PANEL");

        if (handler.getPlayer().isDeath()) {
            PlayerFactory p = this.handler.getPlayer();
            if (!(Menu.demo)) {
                System.out.println("Non sono Player Demo... QUindi sono qui");
                new Scoreboard().addScore(p.getName(), p.getPunteggioAttuale());
                p.aggiornaDB();
            }
        }

    }

    boolean first = true;

    public void animationCycle() {

        if (!PauseMenu.isPause()) {
            if(!first){
                KAdapter.keys[KeyEvent.VK_UP] = false;
                KAdapter.keys[KeyEvent.VK_DOWN] = false;
                KAdapter.keys[KeyEvent.VK_LEFT] = false;
                KAdapter.keys[KeyEvent.VK_RIGHT] = false;
                KAdapter.keys[KeyEvent.VK_W] = false;
                KAdapter.keys[KeyEvent.VK_S] = false;
                KAdapter.keys[KeyEvent.VK_A] = false;
                KAdapter.keys[KeyEvent.VK_D] = false;
            }
            first = true;
            kAdapt.update();
            handler.animationCycle();
        } else {
            if (first) {
                KAdapter.keys[KeyEvent.VK_UP] = false;
                KAdapter.keys[KeyEvent.VK_DOWN] = false;
                KAdapter.keys[KeyEvent.VK_LEFT] = false;
                KAdapter.keys[KeyEvent.VK_RIGHT] = false;
                KAdapter.keys[KeyEvent.VK_W] = false;
                KAdapter.keys[KeyEvent.VK_S] = false;
                KAdapter.keys[KeyEvent.VK_A] = false;
                KAdapter.keys[KeyEvent.VK_D] = false;

                for (Iterator<SpriteInterface> it = handler.getTraps().iterator(); it.hasNext();) {
                    Sprite s =(Sprite) it.next();
                    s.animationCycle();
                }
                first = false;
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        float offsetX = camera.getOffset_x();
        float offsetY = camera.getOffset_y();
        
        drawMap(g);
        handler.drawImage(g, offsetX, offsetY);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void drawMap(Graphics g) {
        g.drawImage(mapImage, (int) -camera.getOffset_x(), (int) -camera.getOffset_y(), this);

    }
}
