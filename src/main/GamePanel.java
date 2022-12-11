package main;

import AI.PathFinder;
import entity.Entity;
import entity.EntityGenerator;
import entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;

import environment.EnvironmentManger;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable
{
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 15;
    public final int screenWidth = tileSize * maxScreenCol;//960;
    public final int screenHeight = tileSize * maxScreenRow;//720;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 10;
    public int currentMap = 0;

    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;

    //SYSTEMS
    int FPS = 60;
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public  EventHandler eHandler = new EventHandler(this);
    Thread gameThread;
    public PathFinder pFinder = new PathFinder(this);
    public EnvironmentManger eManager = new EnvironmentManger(this);
    public EntityGenerator eGenerator = new EntityGenerator(this);

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity[][] monster = new Entity[maxMap][100];
    public Entity[][] obj = new Entity[maxMap][100];
    public Entity[][] item = new Entity[maxMap][100];
    public Entity[][] npc = new Entity[maxMap][100];
    ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projecttileList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionState = 5;
    public final int gameOverState = 6;

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
        aSetter.setMonster();
        aSetter.setItem();
        eManager.setup();
        gameState = titleState;
    }

    public void restart() {
        player.resetCounter();
        player.setDefaultValues();
        player.setDefaultPosition();
        player.restoreLife();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setItem();
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
            for (int i = 0; i < npc[currentMap].length; i++) {
                if(npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }
            for (int i = 0; i < monster[currentMap].length; i++) {
                if(monster[currentMap][i]!=null) {
                    if(monster[currentMap][i].alive && !monster[currentMap][i].dying){
                        monster[currentMap][i].update();
                    }
                    if(!monster[currentMap][i].alive){
                        monster[currentMap][i] = null;
                    }
                }
            }
            for (int i = 0; i < projecttileList.size(); i++) {
                if(projecttileList.get(i) !=null) {
                    if(projecttileList.get(i) .alive){
                        projecttileList.get(i).update();
                    }
                    if(!projecttileList.get(i).alive){
                        projecttileList.remove(i);
                    }
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

            for (int i = 0; i < npc[currentMap].length; i++) {
                if(npc[currentMap][i] !=null){
                    entityList.add(npc[currentMap][i] );
                }
            }
            for (int i = 0; i < obj[currentMap].length; i++) {
                if(obj[currentMap][i]!=null){
                    entityList.add(obj[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[currentMap].length; i++) {
                if(monster[currentMap][i] !=null){
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (int i = 0; i < projecttileList.size(); i++) {
                if(projecttileList.get(i)!=null){
                    entityList.add(projecttileList.get(i));
                }
            }
            for (int i = 0; i < item[currentMap].length; i++) {
                if(item[currentMap][i]!=null){
                    entityList.add(item[currentMap][i]);
                }
            }

            //DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            //EMPTTY ENTITIES LIST
            entityList.clear();

            // Environment
            if(player.life <= 0) {
                eManager.draw(g2);
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
    public void playSE(URL i) {
        se.setFile(i);
        se.play();
    }
}