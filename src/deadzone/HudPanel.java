
package deadzone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import javax.swing.*;
import sprite.Sprite;
import sprite.animated.Player;
import sprite.animated.Zombie;

public class HudPanel extends JPanel implements Runnable {
    
    private ImageIcon ridimensionaImageIcon(URL url, int nuovaW, int nuovaH){
    ImageIcon image = new ImageIcon(url);
    Image immagineScalata = image.getImage().getScaledInstance(nuovaW, nuovaH, Image.SCALE_DEFAULT);
    return new ImageIcon(immagineScalata);
    }
    
    private JLabel nameLabel;
    private JLabel scoreLabel;
    private JLabel scoreTextLabel;
    private JLabel gunLabel;
    private JLabel numWave;
    private JLabel wave;
    private JProgressBar playerHealth;
    private JLabel numBullets;
    private JLabel imageLabel;
    private JLabel enemies;
    private JLabel numEnemies;
    private Handler handler;
    private MinimapPanel minimapPanel;
    private ImageIcon actualWeapon;
    
    
    private class MinimapPanel extends JPanel {
        private Image minimap;
        private int h_minimap ;
        private int w_minimap ;
        private Image resized_minimap;
        private Handler handler;
        private Image red_indicator;
        private Image green_indicator;
        private int sizeMap = 3200;
        
        
        public MinimapPanel(Handler handler){
            this.handler = handler;
            loadMiniMap();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            h_minimap = (int) (dim.width*18/100) ; //in modo da lasciare il 5% a dx e il 5% a sx
            w_minimap = (int) (dim.width*18/100) ; //in modo da lasciare lo stesso margine sopra e sotto
            resized_minimap = minimap.getScaledInstance(w_minimap, h_minimap, Image.SCALE_DEFAULT);
            loadIndicator();
            red_indicator = red_indicator.getScaledInstance((int)w_minimap/20, (int)h_minimap/20, Image.SCALE_DEFAULT);  //gli indicatori hanno come size il 5% di quelle della minimap
            green_indicator = green_indicator.getScaledInstance((int)w_minimap/20, (int)h_minimap/20, Image.SCALE_DEFAULT);
        }
        
        public void loadIndicator(){
            ImageIcon red = new ImageIcon(getClass().getResource("/images/red.png"));
            this.red_indicator = red.getImage();
            ImageIcon green = new ImageIcon(getClass().getResource("/images/green.png"));
            this.green_indicator = green.getImage();
            
        }
        public int getH_minimap() {
            return h_minimap;
        }

        public int getW_minimap() {
            return w_minimap;
        }
        
        public void loadMiniMap(){
        ImageIcon map = new ImageIcon(getClass().getResource("/images/grigionero.png"));
        this.minimap = map.getImage();
        }
        public void drawMiniMap(Graphics g){
        g.drawImage(resized_minimap, 0,0, this);
        
        }
        
        @Override
        public void paintComponent(Graphics g) { //stampa la minimappa e poi tutti gli indicatori
        super.paintComponent(g);
        drawMiniMap(g);
        
        for(int j=0;j<handler.getZombies().size();j++){
            Sprite i = handler.getZombies().get(j);
            drawIndicator(g, i.getX()-(i.getWidth()/2), i.getY()-(i.getHeight()/2), ((Zombie) i).getAngle(),green_indicator);
        }
        
        for(int j=0;j<handler.getPlayers().size();j++){
            Sprite i = handler.getPlayers().get(j);
            drawIndicator(g, i.getX()-(i.getWidth()/2), i.getY()-(i.getHeight()/2), ((Player) i).getAngle(),red_indicator);
        }
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        }
        
        public void drawIndicator(Graphics g, float x, float y, float angle, Image img){  //stampa l'indicatore in relazione a quello che gli si passa
        float rapporto = ((float)sizeMap)/((float)w_minimap); //proporzione tra mappa completa e minimap, si potrebbe rendere attributo di classe per non ricalcolarlo ogni volta
        float xx =  (x / rapporto) ; 
        float yy =  (y / rapporto) ;
        AffineTransform at = AffineTransform.getTranslateInstance(xx,yy);
        at.rotate(angle,w_minimap/20/2, h_minimap/20/2); //usiamo le size dell'indicatore fratto 2 per avere la rotazione intorno al centro
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(img, at, null);
        }
        
    } 
    
    
    public HudPanel(Handler handler){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(new java.awt.Dimension((int) dim.getWidth()*1/5,(int) dim.getHeight()));
        this.setLayout(null);
        this.handler = handler;
        java.awt.Font font = new java.awt.Font("Comic Sans MS", 1, 24);
        
        minimapPanel = new MinimapPanel(handler);
        minimapPanel.setSize(minimapPanel.getW_minimap(),minimapPanel.getH_minimap());
        minimapPanel.setLocation((int) this.getSize().getWidth()*1/18, (int) this.getSize().getHeight()*1/40);  
        this.add(minimapPanel);
        

        this.nameLabel = new JLabel();
        nameLabel.setText("Player"); //il nome del payer sarà un attributo di Player
        nameLabel.setForeground(Color.white);
        nameLabel.setFont(font);
        nameLabel.setSize(100,60);
        nameLabel.setLocation(minimapPanel.getX() + minimapPanel.getWidth()*2/5, minimapPanel.getY() + minimapPanel.getHeight());
        this.add(nameLabel);

        this.playerHealth = new JProgressBar();      
        playerHealth.setSize(minimapPanel.getWidth(), 30);
        playerHealth.setLocation(minimapPanel.getX(), nameLabel.getY() + nameLabel.getHeight());
        playerHealth.setValue(handler.getPlayer().getHealth()); //inizializzo con health iniziale del Player
        playerHealth.setOpaque(false);
        playerHealth.setMaximum(handler.getPlayer().getHealth()); 
        playerHealth.setMinimum(0); 
        this.add(playerHealth);
        
        this.imageLabel = new JLabel();
        imageLabel.setSize(minimapPanel.getWidth()/3, minimapPanel.getHeight()/4);
        ImageIcon i  = ridimensionaImageIcon(getClass().getResource("/images/busto_soldato_uomo.png"), imageLabel.getWidth(), imageLabel.getHeight());
        imageLabel.setIcon(i);
        imageLabel.setLocation(playerHealth.getX(), playerHealth.getY() + playerHealth.getHeight()*2);
        this.add(imageLabel);
        
        this.gunLabel = new JLabel();
        this.actualWeapon = new ImageIcon(handler.getPlayer().getCurrentGun().getSkin());
        gunLabel.setSize(actualWeapon.getIconWidth(),actualWeapon.getIconHeight());
        gunLabel.setIcon((actualWeapon));
        gunLabel.setLocation(this.getWidth() - imageLabel.getWidth()*3/2,imageLabel.getY());
        this.add(gunLabel);
        
        this.numBullets = new JLabel();
        numBullets.setSize(150,60);
        numBullets.setForeground(Color.white);
        numBullets.setFont(font);
        numBullets.setText(Integer.toString(handler.getPlayer().getCurrentGun().getRound()) + "/" + Integer.toString(handler.getPlayer().getCurrentGun().getTotalBullets()));
        numBullets.setLocation(gunLabel.getX(),gunLabel.getY() + gunLabel.getHeight()*2/3);
        this.add(numBullets);
        
        this.wave = new JLabel();
        wave.setText("Wave:");
        wave.setForeground(Color.white);
        wave.setFont(font);
        wave.setSize(imageLabel.getWidth(),50);
        wave.setLocation(imageLabel.getX()*3/2, imageLabel.getY() + imageLabel.getHeight()*3/2);
        this.add(wave);
        
        this.numWave = new JLabel();
        numWave.setText("1-1");
        numWave.setForeground(Color.white);
        numWave.setFont(font);
        numWave.setSize(wave.getWidth(), wave.getHeight());
        numWave.setLocation(wave.getX()*3/2, wave.getY() + wave.getHeight());
        this.add(numWave);
        
        this.enemies = new JLabel();
        enemies.setText("Enemies:");
        enemies.setForeground(Color.white);
        enemies.setFont(font);
        enemies.setSize(120, 50);
        enemies.setLocation(gunLabel.getX(), wave.getY());
        this.add(enemies);
        
        this.numEnemies = new JLabel();
        int nemici = handler.getZombies().size();
        numEnemies.setText(Integer.toString(nemici));
        numEnemies.setForeground(Color.white);
        numEnemies.setFont(font);
        numEnemies.setSize(50, 50);
        numEnemies.setLocation(enemies.getX() + enemies.getWidth()/2, numWave.getY());
        this.add(numEnemies);
        
        this.scoreTextLabel = new JLabel();
        scoreTextLabel.setText("Score:");
        scoreTextLabel.setForeground(Color.white);
        scoreTextLabel.setFont(font);
        scoreTextLabel.setSize(100,100);
        scoreTextLabel.setLocation(nameLabel.getX(), numEnemies.getY() + numEnemies.getHeight());
        this.add(scoreTextLabel);
        
        this.scoreLabel = new JLabel();
        scoreLabel.setText("0000000000");
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(font);
        scoreLabel.setSize(150,100);
        scoreLabel.setLocation((int) this.getSize().getWidth()*1/3, scoreTextLabel.getY() + scoreTextLabel.getHeight()/2);
        this.add(scoreLabel);
        
        JButton pauseButton = new JButton();
        pauseButton.setSize(playerHealth.getWidth(), playerHealth.getHeight()*3/2);
        pauseButton.setBackground(Color.GRAY);
        pauseButton.setText("PAUSE");
        pauseButton.setForeground(Color.white);
        pauseButton.setFont(new java.awt.Font("Comic Sans MS", 1, pauseButton.getHeight()));
        pauseButton.setLocation(minimapPanel.getX(), scoreLabel.getY() + scoreLabel.getHeight()*2);
        System.out.println(pauseButton.getLocation());
        this.add(pauseButton);

        this.setBackground(Color.BLACK);
        
    }
    
   
    

    @Override
    public void run() {   //nel while dovrebbe essere tipo: chiama le varie set delle label (vita, punteggio ecc) poi chiama repaint su this e su minimapPanel
        int fps = 60;
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long ticks = 0;
        long timer = 0;
        
        do{
            now = System.nanoTime();
            delta += (now - lastTime)/timePerTick;
            timer += now - lastTime;
            lastTime = now;
            while(delta >= 1){
                    //animationCycle();
                    playerHealth.setValue(handler.getPlayer().getHealth()); //aggiorna valore health
                    this.actualWeapon = new ImageIcon(handler.getPlayer().getCurrentGun().getSkin());
                    gunLabel.setSize(actualWeapon.getIconWidth(), actualWeapon.getIconHeight());
                    gunLabel.setIcon(actualWeapon); //aggiorna immagine arma
                    numBullets.setText(Integer.toString(handler.getPlayer().getCurrentGun().getRound()) + "/" + Integer.toString(handler.getPlayer().getCurrentGun().getTotalBullets())); //aggiorna numero proiettili
                    numEnemies.setText(""+handler.getZombies().size());
                    // aggiornare il numero di ondata quando disponibile
                    
                    this.repaint();
                    minimapPanel.repaint();
                    delta --;
                    ticks++;
            }

            if(timer >= 1000000000){
                    //aggiungere gli fps e farli stampare dalla HUD (nuova label? )
                    ticks = 0;
                    timer = 0;
            }
        } while(!handler.getPlayer().isDeath());
        
        System.out.println("FINE PARTITA HUD");
    }

}
