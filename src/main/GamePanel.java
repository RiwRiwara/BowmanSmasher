package main;

import entity.Entity;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import object.SuperObject;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable
{
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = 48;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = 768;
    public final int screenHeight = 576;


    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //SYSTEMS
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public  EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[100];
    public Entity mob[] = new Entity[10];

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.black);
        setDoubleBuffered(true);
        addKeyListener(keyH);
        setFocusable(true);
    }


    public void setupGame() {
        aSetter.setObject();
        aSetter.setMob();
        gameState = titleState;
    }
    

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = (1000000000 / FPS);
        double delta = 0.0D;
        long lastTime = System.nanoTime();

        long timer = 0L;
        int drawCount = 0;

        while (gameThread != null) {
            long currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1.0D) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000L) {

                drawCount = 0;
                timer = 0L;
            }
        }
    }


    public void update() {
        if(gameState==playState){
            //Player
            player.update();
            //Mob
            for (int i = 0; i < mob.length; i++) {
                if(mob[i] != null){
                    mob[i].update();
                }
            }
        }
        if(gameState==pauseState){
            //nothing
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //TITLE SCREEN
        // If title state
        if(gameState == titleState ){
            ui.draw(g2);
        }//Other
            else{
            // TILE
            tileM.draw(g2);

            // OBJECT
            for(int i = 0;i<obj.length;i++){
                if(obj[i] != null){
                    obj[i].draw(g2, this);
                }
            }

            //MOBs
            for (int i = 0; i < mob.length ; i++) {
                if(mob[i] != null){
                    mob[i].draw(g2);
                }
            }

            //PLAYER
            player.draw(g2);

            //UI
            ui.draw(g2);

        }

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}