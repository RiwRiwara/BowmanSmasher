/*     */ package tile;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import javax.imageio.ImageIO;
/*     */ import main.GamePanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileManager
/*     */ {
/*     */   GamePanel gp;
/*     */   public Tile[] tile;
/*     */   public int[][] mapTileNum;
/*     */   
/*     */   public TileManager(GamePanel gp) {
/*  21 */     this.gp = gp;
/*     */     
/*  23 */     this.tile = new Tile[10];
/*  24 */     gp.getClass(); gp.getClass(); this.mapTileNum = new int[50][50];
/*     */     
/*  26 */     getTileImage();
/*  27 */     loadMap("/maps/world01.txt");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void getTileImage() {
/*     */     try {
/*  34 */       this.tile[0] = new Tile();
/*  35 */       (this.tile[0]).image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
/*     */       
/*  37 */       this.tile[1] = new Tile();
/*  38 */       (this.tile[1]).image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
/*  39 */       (this.tile[1]).collision = true;
/*     */       
/*  41 */       this.tile[2] = new Tile();
/*  42 */       (this.tile[2]).image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
/*  43 */       (this.tile[2]).collision = true;
/*     */       
/*  45 */       this.tile[3] = new Tile();
/*  46 */       (this.tile[3]).image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
/*     */       
/*  48 */       this.tile[4] = new Tile();
/*  49 */       (this.tile[4]).image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
/*  50 */       (this.tile[4]).collision = true;
/*     */       
/*  52 */       this.tile[5] = new Tile();
/*  53 */       (this.tile[5]).image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
/*     */     
/*     */     }
/*  56 */     catch (IOException e) {
/*  57 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void loadMap(String filePath) {
/*     */     try {
/*  63 */       InputStream is = getClass().getResourceAsStream(filePath);
/*  64 */       BufferedReader br = new BufferedReader(new InputStreamReader(is));
/*     */       
/*  66 */       int col = 0;
/*  67 */       int row = 0;
/*     */       
/*  69 */       this.gp.getClass(); this.gp.getClass(); while (col < 50 && row < 50) {
/*     */         
/*  71 */         String line = br.readLine();
/*     */         
/*  73 */         this.gp.getClass(); while (col < 50) {
/*     */           
/*  75 */           String[] numbers = line.split(" ");
/*     */           
/*  77 */           int num = Integer.parseInt(numbers[col]);
/*     */           
/*  79 */           this.mapTileNum[col][row] = num;
/*  80 */           col++;
/*     */         } 
/*  82 */         this.gp.getClass(); if (col == 50) {
/*  83 */           col = 0;
/*  84 */           row++;
/*     */         } 
/*     */       } 
/*  87 */       br.close();
/*     */     }
/*  89 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(Graphics2D g2) {
/*  95 */     int worldCol = 0;
/*  96 */     int worldRow = 0;
/*     */     
/*  98 */     this.gp.getClass(); this.gp.getClass(); while (worldCol < 50 && worldRow < 50) {
/*     */       
/* 100 */       int tileNum = this.mapTileNum[worldCol][worldRow];
/*     */       
/* 102 */       this.gp.getClass(); int worldX = worldCol * 48;
/* 103 */       this.gp.getClass(); int worldY = worldRow * 48;
/* 104 */       int screenX = worldX - this.gp.player.worldX + this.gp.player.screenX;
/* 105 */       int screenY = worldY - this.gp.player.worldY + this.gp.player.screenY;
/*     */       
/* 107 */       this.gp.getClass();
/* 108 */       this.gp.getClass();
/* 109 */       this.gp.getClass();
/* 110 */       this.gp.getClass(); if (worldX + 48 > this.gp.player.worldX - this.gp.player.screenX && worldX - 48 < this.gp.player.worldX + this.gp.player.screenX && worldY + 48 > this.gp.player.worldY - this.gp.player.screenY && worldY - 48 < this.gp.player.worldY + this.gp.player.screenY) {
/*     */         
/* 112 */         this.gp.getClass(); this.gp.getClass(); g2.drawImage((this.tile[tileNum]).image, screenX, screenY, 48, 48, null);
/*     */       } 
/*     */       
/* 115 */       worldCol++;
/*     */       
/* 117 */       this.gp.getClass(); if (worldCol == 50) {
/* 118 */         worldCol = 0;
/* 119 */         worldRow++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\tile\TileManager.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */