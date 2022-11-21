/*     */ package entity;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
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
/*  30 */     gp.getClass(); gp.getClass(); this.screenX = 768 / 2 - 48 / 2;
/*  31 */     gp.getClass(); gp.getClass(); this.screenY = 576 / 2 - 48 / 2;
/*     */     
/*  33 */     this.solidArea = new Rectangle();
/*  34 */     this.solidArea.x = 8;
/*  35 */     this.solidArea.y = 16;
/*  36 */     this.solidAreaDefaultX = this.solidArea.x;
/*  37 */     this.solidAreaDefaultY = this.solidArea.y;
/*  38 */     this.solidArea.width = 32;
/*  39 */     this.solidArea.height = 32;
/*     */     
/*  41 */     setDefaultValues();
/*  42 */     getPlayerImage();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefaultValues() {
/*  47 */     this.gp.getClass(); this.worldX = 48 * 23;
/*  48 */     this.gp.getClass(); this.worldY = 48 * 21;
/*     */ 
/*     */ 
/*     */     
/*  52 */     this.speed = 4;
/*  53 */     this.direction = "down";
/*     */   }
/*     */ 
/*     */   
/*     */   public void getPlayerImage() {
/*     */     try {
/*  59 */       this.up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
/*  60 */       this.up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
/*  61 */       this.down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
/*  62 */       this.down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
/*  63 */       this.left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
/*  64 */       this.left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
/*  65 */       this.right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
/*  66 */       this.right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
/*     */     }
/*  68 */     catch (IOException e) {
/*  69 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  85 */     if (this.keyH.upPressed || this.keyH.downPressed || 
/*  86 */       this.keyH.leftPressed || this.keyH.rightPressed) {
/*     */       
/*  88 */       if (this.keyH.upPressed) {
/*  89 */         this.direction = "up";
/*     */       }
/*  91 */       else if (this.keyH.downPressed) {
/*  92 */         this.direction = "down";
/*     */       }
/*  94 */       else if (this.keyH.leftPressed) {
/*  95 */         this.direction = "left";
/*     */       }
/*  97 */       else if (this.keyH.rightPressed) {
/*  98 */         this.direction = "right";
/*     */       } 
/*     */ 
/*     */       
/* 102 */       this.collisionOn = false;
/* 103 */       this.gp.cChecker.checkTile(this);
/*     */ 
/*     */       
/* 106 */       int objIndex = this.gp.cChecker.checkObject(this, true);
/* 107 */       pickUpObject(objIndex);
/*     */ 
/*     */ 
/*     */       
/* 111 */       if (!this.collisionOn) {
/*     */         String str;
/* 113 */         switch ((str = this.direction).hashCode()) { case 3739: if (!str.equals("up"))
/* 114 */               break;  this.worldY -= this.speed; break;case 3089570: if (!str.equals("down"))
/* 115 */               break;  this.worldY += this.speed; break;case 3317767: if (!str.equals("left"))
/* 116 */               break;  this.worldX -= this.speed; break;case 108511772: if (!str.equals("right"))
/* 117 */               break;  this.worldX += this.speed; break; } 
/* 118 */       }  this
/*     */ 
/*     */         
/* 121 */         .spriteCounter = this.spriteCounter + 1;
/* 122 */       if (this.spriteCounter > 12) {
/* 123 */         if (this.spriteNum == 1) {
/* 124 */           this.spriteNum = 2;
/*     */         }
/* 126 */         else if (this.spriteNum == 2) {
/* 127 */           this.spriteNum = 1;
/*     */         } 
/* 129 */         this.spriteCounter = 0;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pickUpObject(int i) {
/* 142 */     if (i != 999) {
/*     */       
/* 144 */       String objectName = (this.gp.obj[i]).name;
/*     */       String str1;
/* 146 */       switch ((str1 = objectName).hashCode()) { case 75327: if (!str1.equals("Key"))
/*     */             break; 
/* 148 */           this.gp.playSE(1);
/* 149 */           this.hasKey++;
/* 150 */           this.gp.obj[i] = null;
/* 151 */           this.gp.ui.showMessage("You got a key!"); break;
/*     */         case 2136014:
/*     */           if (!str1.equals("Door"))
/* 154 */             break;  if (this.hasKey > 0) {
/* 155 */             this.gp.playSE(3);
/* 156 */             this.gp.obj[i] = null;
/* 157 */             this.hasKey--;
/* 158 */             this.gp.ui.showMessage("You opened the door!");
/*     */             break;
/*     */           } 
/* 161 */           this.gp.ui.showMessage("You need a key!"); break;
/*     */         case 64369569:
/*     */           if (!str1.equals("Boots"))
/*     */             break; 
/* 165 */           this.gp.playSE(2);
/* 166 */           this.speed++;
/*     */           
/* 168 */           this.gp.obj[i] = null;
/* 169 */           this.gp.ui.showMessage("Speed up!"); break;
/*     */         case 65074913:
/*     */           if (!str1.equals("Chest"))
/* 172 */             break;  this.gp.ui.gameFinished = true;
/* 173 */           this.gp.stopMusic();
/* 174 */           this.gp.playSE(4);
/*     */           break; }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 185 */     BufferedImage image = null;
/*     */     String str;
/* 187 */     switch ((str = this.direction).hashCode()) { case 3739: if (!str.equals("up"))
/*     */           break; 
/* 189 */         if (this.spriteNum == 1) {
/* 190 */           image = this.up1;
/*     */         }
/* 192 */         if (this.spriteNum == 2)
/* 193 */           image = this.up2;  break;
/*     */       case 3089570:
/*     */         if (!str.equals("down"))
/*     */           break; 
/* 197 */         if (this.spriteNum == 1) {
/* 198 */           image = this.down1;
/*     */         }
/* 200 */         if (this.spriteNum == 2)
/* 201 */           image = this.down2;  break;
/*     */       case 3317767:
/*     */         if (!str.equals("left"))
/*     */           break; 
/* 205 */         if (this.spriteNum == 1) {
/* 206 */           image = this.left1;
/*     */         }
/* 208 */         if (this.spriteNum == 2)
/* 209 */           image = this.left2;  break;
/*     */       case 108511772:
/*     */         if (!str.equals("right"))
/*     */           break; 
/* 213 */         if (this.spriteNum == 1) {
/* 214 */           image = this.right1;
/*     */         }
/* 216 */         if (this.spriteNum == 2) {
/* 217 */           image = this.right2;
/*     */         }
/*     */         break; }
/*     */     
/* 221 */     this.gp.getClass(); this.gp.getClass(); g2.drawImage(image, this.screenX, this.screenY, 48, 48, null);
/*     */   }
/*     */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\entity\Player.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */