package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import entity.Player;
import tile.TileManager;
import Object.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16; //tile 16x16
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 px
    public final int screenHeight = tileSize * maxScreenRow;  // 576 px

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public  CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];


    //World Setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        double drawinterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawinterval;
            lastTime = currentTime;
            if(delta >=1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //layerDraw
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
