package main;

import entity.Entity;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;

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
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public  EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[100];
    public Entity npc[] = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();

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
        aSetter.setNPC();
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
            //NPC
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null){
                    npc[i].update();
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

            //ADD Entity
            entityList.add(player);
            for (int i = 0; i < npc.length; i++) {
                if(npc[i]!=null){
                    entityList.add(npc[i]);
                }
            }
            for (int i = 0; i < obj.length; i++) {
                if(obj[i]!=null){
                    entityList.add(obj[i]);
                }
            }
            //Sort
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {
                    int result = Integer.compare(o1.worldY, o2.worldY);
                    return result;
                }
            });
            //DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            //EMPTTY ENTITIES LIST
            for (int i = 0; i < entityList.size(); i++) {
                entityList.remove(i);
            }

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