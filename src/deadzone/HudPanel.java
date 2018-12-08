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
import gameMenu.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.Assets;

public class HudPanel extends JPanel implements Runnable {

    private ImageIcon ridimensionaImageIcon(URL url, int nuovaW, int nuovaH) {
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
    private JProgressBarH playerHealth;
    private JLabel numBullets;
    private JLabel imageLabel;
    private JLabel enemies;
    private JLabel numEnemies;
    private JLabel fpsLabel;
    private Handler handler;
    private MinimapPanel minimapPanel;
    private ImageIcon actualWeapon;
    private long averageFPS = 0;

    private class MinimapPanel extends JPanel {

        private Image minimap;
        private int h_minimap;
        private int w_minimap;
        private Image resized_minimap;
        private Handler handler;
        private Image red_indicator;
        private Image green_indicator;
        private int sizeMap = 3200;

        public MinimapPanel(Handler handler) {
            this.handler = handler;
            loadMiniMap();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            h_minimap = (int) (dim.width * 18 / 100); //in modo da lasciare il 5% a dx e il 5% a sx
            w_minimap = (int) (dim.width * 18 / 100); //in modo da lasciare lo stesso margine sopra e sotto
            resized_minimap = minimap.getScaledInstance(w_minimap, h_minimap, Image.SCALE_DEFAULT);
            loadIndicator();
            red_indicator = red_indicator.getScaledInstance((int) w_minimap / 20, (int) h_minimap / 20, Image.SCALE_DEFAULT);  //gli indicatori hanno come size il 5% di quelle della minimap
            green_indicator = green_indicator.getScaledInstance((int) w_minimap / 20, (int) h_minimap / 20, Image.SCALE_DEFAULT);
        }

        public void loadIndicator() {
            ImageIcon red = new ImageIcon(Assets.redIndicator);
            this.red_indicator = red.getImage();
            ImageIcon green = new ImageIcon(Assets.greenIndicator);
            this.green_indicator = green.getImage();

        }

        public int getH_minimap() {
            return h_minimap;
        }

        public int getW_minimap() {
            return w_minimap;
        }

        public void loadMiniMap() {
            ImageIcon map = new ImageIcon(Assets.minimap);
            this.minimap = map.getImage();
        }

        public void drawMiniMap(Graphics g) {
            g.drawImage(resized_minimap, 0, 0, this);

        }

        @Override
        public void paintComponent(Graphics g) { //stampa la minimappa e poi tutti gli indicatori
            super.paintComponent(g);
            drawMiniMap(g);

            for (int j = 0; j < handler.getZombies().size(); j++) {
                Sprite i = handler.getZombies().get(j);
                drawIndicator(g, i.getX() - (i.getWidth() / 2), i.getY() - (i.getHeight() / 2), ((Zombie) i).getAngle(), red_indicator);
            }

            for (int j = 0; j < handler.getPlayers().size(); j++) {
                Sprite i = handler.getPlayers().get(j);
                drawIndicator(g, i.getX() - (i.getWidth() / 2), i.getY() - (i.getHeight() / 2), ((Player) i).getAngle(), green_indicator);
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }

        public void drawIndicator(Graphics g, float x, float y, float angle, Image img) {  //stampa l'indicatore in relazione a quello che gli si passa
            float rapporto = ((float) sizeMap) / ((float) w_minimap); //proporzione tra mappa completa e minimap, si potrebbe rendere attributo di classe per non ricalcolarlo ogni volta
            float xx = (x / rapporto);
            float yy = (y / rapporto);
            AffineTransform at = AffineTransform.getTranslateInstance(xx, yy);
            at.rotate(angle, w_minimap / 20 / 2, h_minimap / 20 / 2); //usiamo le size dell'indicatore fratto 2 per avere la rotazione intorno al centro
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(img, at, null);
        }

    }

    public HudPanel(Handler handler) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(new java.awt.Dimension((int) dim.getWidth() * 1 / 5, (int) dim.getHeight()));
        this.setLayout(null);
        this.handler = handler;
        java.awt.Font font = new java.awt.Font("Comic Sans MS", 1, 24);

        minimapPanel = new MinimapPanel(handler);
        minimapPanel.setSize(minimapPanel.getW_minimap(), minimapPanel.getH_minimap());
        minimapPanel.setLocation((int) this.getSize().getWidth() * 1 / 18, (int) this.getSize().getHeight() * 1 / 40);
        this.add(minimapPanel);

        this.nameLabel = new JLabel(this.handler.getPlayer().getName(), SwingConstants.CENTER); //nome del player centrato nella Label
        nameLabel.setForeground(Color.white);
        nameLabel.setFont(font);
        nameLabel.setSize(this.getWidth(), 39);
        nameLabel.setLocation(0, (int) this.getSize().getHeight() * 1 / 40 + minimapPanel.getHeight());
        this.add(nameLabel);

        this.playerHealth = new JProgressBarH();
        playerHealth.setSize(minimapPanel.getWidth(), 30);
        playerHealth.setLocation((int) this.getSize().getWidth() * 1 / 18, nameLabel.getY() + nameLabel.getHeight());
        playerHealth.setValue(handler.getPlayer().getHealth()); //inizializzo con health iniziale del Player
        playerHealth.setOpaque(true);
        playerHealth.setMaximum(handler.getPlayer().getMaximumHealth());
        playerHealth.setMinimum(0);
        playerHealth.setHealth(this.handler.getPlayer().getHealth());
        playerHealth.repaint();
        this.add(playerHealth);

        this.imageLabel = new JLabel();
        imageLabel.setSize(this.getWidth() / 3, minimapPanel.getHeight() / 4);
        if (this.handler.getPlayer().isMale()) {
            ImageIcon i = ridimensionaImageIcon(getClass().getResource("/images/busto_soldato_uomo.png"), imageLabel.getWidth(), imageLabel.getHeight());
            imageLabel.setIcon(i);
        } else {
            ImageIcon i = ridimensionaImageIcon(getClass().getResource("/images/busto_soldato_donna.png"), imageLabel.getWidth(), imageLabel.getHeight());
            imageLabel.setIcon(i);
        }

        imageLabel.setLocation((int) this.getWidth() / 18, playerHealth.getY() + playerHealth.getHeight() * 3 / 2);
        this.add(imageLabel);

        this.gunLabel = new JLabel();
        this.actualWeapon = new ImageIcon(handler.getPlayer().getCurrentGun().getSkin());
        gunLabel.setSize(actualWeapon.getIconWidth(), actualWeapon.getIconHeight());
        gunLabel.setIcon((actualWeapon));
        gunLabel.setLocation(this.getWidth() - gunLabel.getWidth() - this.getWidth() * 1 / 5, imageLabel.getY());
        this.add(gunLabel);

        this.numBullets = new JLabel();
        numBullets.setSize(this.getWidth() / 2, 39);
        numBullets.setForeground(Color.white);
        numBullets.setFont(font);
        numBullets.setText(Integer.toString(handler.getPlayer().getCurrentGun().getRound()) + "/" + Integer.toString(handler.getPlayer().getCurrentGun().getTotalBullets()));
        numBullets.setLocation(gunLabel.getX(), gunLabel.getY() + gunLabel.getHeight());
        this.add(numBullets);

        this.wave = new JLabel("Wave:", SwingConstants.CENTER);
        wave.setForeground(Color.white);
        wave.setFont(font);
        wave.setSize(this.getWidth() / 2, 39);
        wave.setLocation(0, imageLabel.getY() + imageLabel.getHeight() * 4 / 3);
        this.add(wave);

        this.numWave = new JLabel(Integer.toString(handler.getWaves().getWaveCount()) + " - \u221e", SwingConstants.CENTER);
        numWave.setForeground(Color.white);
        numWave.setFont(font);
        numWave.setSize(this.getWidth() / 2, 39);
        numWave.setLocation(0, wave.getY() + wave.getHeight());
        this.add(numWave);

        this.enemies = new JLabel("Enemies:", SwingConstants.LEFT);
        enemies.setForeground(Color.white);
        enemies.setFont(font);
        enemies.setSize(this.getWidth() / 2, 39);
        enemies.setLocation(this.getWidth() - gunLabel.getWidth() - this.getWidth() * 1 / 5, wave.getY());
        this.add(enemies);

        this.numEnemies = new JLabel(Integer.toString(handler.getWaves().getNumZombieRemaining()), SwingConstants.CENTER);
        numEnemies.setForeground(Color.white);
        numEnemies.setFont(font);
        numEnemies.setSize(this.getWidth() / 2, 39);
        numEnemies.setLocation(this.getWidth() / 2, numWave.getY());
        this.add(numEnemies);

        this.scoreTextLabel = new JLabel("Score", SwingConstants.CENTER);
        scoreTextLabel.setForeground(Color.white);
        scoreTextLabel.setFont(font);
        scoreTextLabel.setSize(this.getWidth(), 39);
        scoreTextLabel.setLocation(0, numEnemies.getY() + numEnemies.getHeight());
        this.add(scoreTextLabel);

        this.scoreLabel = new JLabel("" + this.handler.getPlayer().getPunteggioAttuale(), SwingConstants.CENTER);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(font);
        scoreLabel.setSize(this.getWidth(), 39);
        scoreLabel.setLocation(0, scoreTextLabel.getY() + scoreTextLabel.getHeight());
        this.add(scoreLabel);

        this.fpsLabel = new JLabel();
        fpsLabel.setFont(font);
        fpsLabel.setSize(this.getWidth(), 39);
        fpsLabel.setLocation(0, scoreLabel.getY() + scoreLabel.getHeight() * 2);
        this.add(fpsLabel);

        JButton pauseButton = new JButton();
        pauseButton.setSize(playerHealth.getWidth(), playerHealth.getHeight() * 3 / 2);
        pauseButton.setBackground(Color.GRAY);
        pauseButton.setText("PAUSE");
        pauseButton.setForeground(Color.white);
        pauseButton.setFont(new java.awt.Font("Comic Sans MS", 1, pauseButton.getHeight()));
        pauseButton.setLocation(minimapPanel.getX(), this.getHeight() - pauseButton.getHeight() * 2);
        this.add(pauseButton);

        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseActionPerformed(evt);
            }
        });

        this.setBackground(Color.BLACK);

    }

    private class JProgressBarH extends JProgressBar {

        private int health;

        public JProgressBarH() {
            super();
        }

        public void setHealth(int health) {
            this.health = health;
        }

        @Override
        public void paintComponent(Graphics g) {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            if (this.health >= this.getMaximum() * 7 / 10) {
                g.setColor(Color.GREEN);
                g.fillRect(0, 0, (this.health * dim.width * 9 / 50) / handler.getPlayer().getMaximumHealth(), this.getHeight());
            } else if (this.health >= this.getMaximum() * 3 / 10 && this.health < this.getMaximum() * 7 / 10) {
                g.setColor(Color.YELLOW);
                g.fillRect(0, 0, (this.health * dim.width * 9 / 50) / handler.getPlayer().getMaximumHealth(), this.getHeight());
            } else if (this.health == 0) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
            } else {
                g.setColor(Color.red);
                g.fillRect(0, 0, (this.health * dim.width * 9 / 50) / handler.getPlayer().getMaximumHealth(), this.getHeight());
            }
        }

    }

    private void pauseActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        PauseMenu pm = new PauseMenu(topFrame, true);
        pm.setVisible(true);
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

        do {
            if (PauseMenu.isPause()) {
                synchronized (PauseMenu.PAUSELOCK) { //evito di ciclare se c'è pausa
                    try {
                        System.out.println("PAUSA HUD");
                        PauseMenu.PAUSELOCK.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(HudPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                now = System.nanoTime();
                delta += (now - lastTime) / timePerTick;
                timer += now - lastTime;
                lastTime = now;
                while (delta >= 1) {
                    this.scoreLabel.setText("" + this.handler.getPlayer().getPunteggioAttuale());
                    this.actualWeapon = new ImageIcon(handler.getPlayer().getCurrentGun().getSkin());
                    gunLabel.setSize(actualWeapon.getIconWidth(), actualWeapon.getIconHeight()); //dimensiona label secondo grandezza immagine arma
                    gunLabel.setIcon(actualWeapon); //aggiorna immagine arma
                    numBullets.setText(Integer.toString(handler.getPlayer().getCurrentGun().getRound()) + "/" + Integer.toString(handler.getPlayer().getCurrentGun().getTotalBullets())); //aggiorna numero proiettili
                    numEnemies.setText("" + handler.getZombies().size());
                    playerHealth.setHealth(this.handler.getPlayer().getHealth()); //aggiorna progressBar player
                    numWave.setText(Integer.toString(handler.getWaves().getWaveCount()) + " - \u221e");
                    numEnemies.setText(Integer.toString(handler.getWaves().getNumZombieRemaining()));

                    this.repaint();
                    minimapPanel.repaint();
                    delta--;
                    ticks++;
                }

                if (timer >= 1000000000) {
                    this.averageFPS = ticks;
                    fpsLabel.setForeground(Color.WHITE);
                    fpsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    fpsLabel.setVerticalAlignment(SwingConstants.CENTER);
                    fpsLabel.setText("fps: " + Long.toString(averageFPS));
                    ticks = 0;
                    timer = 0;
                }

            }

        } while (!handler.getPlayer().isDeath() && !PauseMenu.end);
        JFrame hudPanel = (JFrame) SwingUtilities.getWindowAncestor(this);
        GameOver gameOver;
        if (!PauseMenu.end) {
            this.playerHealth.setHealth(0);
            this.playerHealth.repaint();
            gameOver = new GameOver(hudPanel);
        }
        System.out.println("FINE PARTITA HUD");
    }
}
