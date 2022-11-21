/*     */ package main;

/*     */ import entity.Player;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import javax.swing.JPanel;
/*     */ import tile.TileManager;
/*     */ 
/*     */ 
/*     */ public class GamePanel
/*     */   extends JPanel
/*     */   implements Runnable
/*     */ {
/*  17 */   final int originalTileSize = 16;
/*  18 */   final int scale = 3;
/*  19 */   public final int tileSize = 48;
/*  20 */   public final int maxScreenCol = 16;
/*  21 */   public final int maxScreenRow = 12;
/*  22 */   public final int screenWidth = 768;
/*  23 */   public final int screenHeight = 576;
/*     */ 
/*     */   
/*  26 */   public final int maxWorldCol = 50;
/*  27 */   public final int maxWorldRow = 50;
/*     */ 
/*     */   
/*  30 */   int FPS = 60;


/*  33 */   TileManager tileM = new TileManager(this);
/*  34 */   KeyHandler keyH = new KeyHandler();
/*  35 */   Sound music = new Sound();
/*  36 */   Sound se = new Sound();
/*  37 */   public CollisionChecker cChecker = new CollisionChecker(this);
/*  39 */   public UI ui = new UI(this);

/*     */   Thread gameThread;
/*     */   
/*  43 */   public Player player = new Player(this, this.keyH);

/*     */   public GamePanel() {
/*  50 */     setPreferredSize(new Dimension(screenWidth, screenHeight));
/*  51 */     setBackground(Color.black);
/*  52 */     setDoubleBuffered(true);
/*  53 */     addKeyListener(this.keyH);
/*  54 */     setFocusable(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupGame() {

/*     */     
/*  61 */     playMusic(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startGameThread() {
/*  67 */     this.gameThread = new Thread(this);
/*  68 */     this.gameThread.start();
/*     */   }
/*     */   
/*     */   public void run() {
/*  72 */     double drawInterval = (1000000000 / this.FPS);
/*  73 */     double delta = 0.0D;
/*  74 */     long lastTime = System.nanoTime();
/*     */     
/*  76 */     long timer = 0L;
/*  77 */     int drawCount = 0;
/*     */     
/*  79 */     while (this.gameThread != null) {
/*     */       
/*  81 */       long currentTime = System.nanoTime();
/*     */       
/*  83 */       delta += (currentTime - lastTime) / drawInterval;
/*  84 */       timer += currentTime - lastTime;
/*  85 */       lastTime = currentTime;
/*     */       
/*  87 */       if (delta >= 1.0D) {
/*  88 */         update();
/*  89 */         repaint();
/*  90 */         delta--;
/*  91 */         drawCount++;
/*     */       } 
/*     */       
/*  94 */       if (timer >= 1000000000L) {
/*     */         
/*  96 */         drawCount = 0;
/*  97 */         timer = 0L;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 104 */     this.player.update();
/*     */   }
/*     */   
/*     */   public void paintComponent(Graphics g) {
/* 108 */     super.paintComponent(g);
/* 109 */     Graphics2D g2 = (Graphics2D)g;
/*     */ 
/*     */     
/* 112 */     this.tileM.draw(g2);
/*     */ 
/*     */     

/*     */     
/* 122 */     this.player.draw(g2);
/*     */ 
/*     */     
/* 125 */     this.ui.draw(g2);
/*     */     
/* 127 */     g2.dispose();
/*     */   }
/*     */   
/*     */   public void playMusic(int i) {
/* 131 */     this.music.setFile(i);
/* 132 */     this.music.play();
/* 133 */     this.music.loop();
/*     */   }
/*     */   
/*     */   public void stopMusic() {
/* 137 */     this.music.stop();
/*     */   }
/*     */   
/*     */   public void playSE(int i) {
/* 141 */     this.se.setFile(i);
/* 142 */     this.se.play();
/*     */   }
/*     */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\main\GamePanel.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */