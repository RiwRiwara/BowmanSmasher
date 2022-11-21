/*     */ package entity;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
import java.util.Objects;
/*     */ import javax.imageio.ImageIO;
/*     */ import main.GamePanel;
/*     */ import main.KeyHandler;
/*     */ 
/*     */ 
/*     */ public class Player
/*     */   extends Entity
/*     */ {
/*     */   GamePanel gp;
/*     */   KeyHandler keyH;
/*     */   public final int screenX;
/*     */   public final int screenY;
/*  19 */   public int hasKey = 0;
/*     */   
/*     */   boolean bootsOn = false;
/*  22 */   int bootsCounter = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public Player(GamePanel gp, KeyHandler keyH) {
/*  27 */     this.gp = gp;
/*  28 */     this.keyH = keyH;
/*     */     
/*  30 */     screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
/*  31 */     screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
/*     */     
/*  33 */     solidArea = new Rectangle();
/*  34 */     solidArea.x = 8;
/*  35 */     solidArea.y = 16;
/*  36 */     solidAreaDefaultX = solidArea.x;
/*  37 */     solidAreaDefaultY = solidArea.y;
/*  38 */     solidArea.width = 32;
/*  39 */     solidArea.height = 32;
/*     */     
/*  41 */     setDefaultValues();
/*  42 */     getPlayerImage();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefaultValues() {
/*  47 */     worldX = gp.tileSize * 23;
/*  48 */     worldY = gp.tileSize * 21;
/*  52 */     this.speed = 4;
/*  53 */     this.direction = "down";
/*     */   }
/*     */ 
/*     */   
/*     */   public void getPlayerImage() {
/*     */     try {
//        up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boup1.png")));
//        up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boup2.png")));
//        down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/bodown1.png")));
//        down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/bodown2.png")));
//        left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boleft1.png")));
//        left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boleft2.png")));
//        right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boright1.png")));
//        right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boright2.png")));

        up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/ZOMBIE-up1.png")));
        up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/ZOMBIE-up2.png")));
        down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/ZOMBIE-down1.png")));
        down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/ZOMBIE-down2.png")));
        left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/ZOMBIE-left1.png")));
        left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/ZOMBIE-left2.png")));
        right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/ZOMBIE-right1.png")));
        right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/ZOMBIE-right2.png")));
         }
/*  68 */     catch (IOException e) {
/*  69 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 

/*     */   public void update() {
/*  85 */     if (keyH.upPressed || keyH.downPressed ||
/*  86 */       keyH.leftPressed || keyH.rightPressed) {
/*     */       
/*  88 */       if (keyH.upPressed) {
/*  89 */         direction = "up";
/*     */       }
/*  91 */       else if (keyH.downPressed) {
/*  92 */         direction = "down";
/*     */       }
/*  94 */       else if (keyH.leftPressed) {
/*  95 */         direction = "left";
/*     */       }
/*  97 */       else if (this.keyH.rightPressed) {
/*  98 */         direction = "right";
/*     */       } 
/*     */ 
/*     */       
/* 102 */       collisionOn = false;
/* 103 */       gp.cChecker.checkTile(this);


/* 111 */       if (!collisionOn) {
                    switch (direction) {
                        case "up" : worldY -= speed;break;
                        case "down" : worldY += speed;break;
                        case "left" : worldX -= speed;break;
                        case "right" : worldX += speed;break;
                        }
                   }
                spriteCounter++;
/* 122 */       if (spriteCounter > 12) {
/* 123 */         if (spriteNum == 1) {
/* 124 */               spriteNum = 2;
/*     */         }
/* 126 */         else if (spriteNum == 2) {
/* 127 */               spriteNum = 1;
/*     */         } 
/* 129 */         spriteCounter = 0;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 



         public void draw(Graphics2D g2) {
             BufferedImage image = null;
             switch (direction) {
                case "up":
                   if (spriteNum == 1) {
                         image = up1;
                   }
                   if (spriteNum == 2){
                        image = up2;
                   }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                   if (spriteNum == 2){
                        image = down2;
                    }
                    break;
                case "left":
                    if (this.spriteNum == 1) {
                        image = left1;
                    }
                    if (this.spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                if (this.spriteNum == 1) {
                        image = right1;
                }
                if (this.spriteNum == 2) {
                        image = right2;
                     }
                 break; }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        }


