/*     */ package main;
/*     */ 
/*     */ import entity.Entity;
/*     */ 
/*     */ public class CollisionChecker
/*     */ {
/*     */   GamePanel gp;
/*     */   
/*     */   public CollisionChecker(GamePanel gp) {
/*  10 */     this.gp = gp;
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkTile(Entity entity) {
/*  15 */     int tileNum1, tileNum2, entityLeftWorldX = entity.worldX + entity.solidArea.x;
/*  16 */     int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
/*  17 */     int entityTopWorldY = entity.worldY + entity.solidArea.y;
/*  18 */     int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
/*     */     
/*  20 */     this.gp.getClass(); int entityLeftCol = entityLeftWorldX / 48;
/*  21 */     this.gp.getClass(); int entityRightCol = entityRightWorldX / 48;
/*  22 */     this.gp.getClass(); int entityTopRow = entityTopWorldY / 48;
/*  23 */     this.gp.getClass(); int entityBottomRow = entityBottomWorldY / 48;
/*     */     
/*     */     String str;
/*     */     
/*  27 */     switch ((str = entity.direction).hashCode()) { case 3739: if (!str.equals("up"))
/*     */           break; 
/*  29 */         this.gp.getClass(); entityTopRow = (entityTopWorldY - entity.speed) / 48;
/*  30 */         tileNum1 = this.gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
/*  31 */         tileNum2 = this.gp.tileM.mapTileNum[entityRightCol][entityTopRow];
/*  32 */         if ((this.gp.tileM.tile[tileNum1]).collision || (this.gp.tileM.tile[tileNum2]).collision)
/*  33 */           entity.collisionOn = true;  break;
/*     */       case 3089570:
/*     */         if (!str.equals("down"))
/*     */           break; 
/*  37 */         this.gp.getClass(); entityBottomRow = (entityBottomWorldY + entity.speed) / 48;
/*  38 */         tileNum1 = this.gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
/*  39 */         tileNum2 = this.gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
/*  40 */         if ((this.gp.tileM.tile[tileNum1]).collision || (this.gp.tileM.tile[tileNum2]).collision)
/*  41 */           entity.collisionOn = true;  break;
/*     */       case 3317767:
/*     */         if (!str.equals("left"))
/*     */           break; 
/*  45 */         this.gp.getClass(); entityLeftCol = (entityLeftWorldX - entity.speed) / 48;
/*  46 */         tileNum1 = this.gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
/*  47 */         tileNum2 = this.gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
/*  48 */         if ((this.gp.tileM.tile[tileNum1]).collision || (this.gp.tileM.tile[tileNum2]).collision)
/*  49 */           entity.collisionOn = true;  break;
/*     */       case 108511772:
/*     */         if (!str.equals("right"))
/*     */           break; 
/*  53 */         this.gp.getClass(); entityRightCol = (entityRightWorldX + entity.speed) / 48;
/*  54 */         tileNum1 = this.gp.tileM.mapTileNum[entityRightCol][entityTopRow];
/*  55 */         tileNum2 = this.gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
/*  56 */         if ((this.gp.tileM.tile[tileNum1]).collision || (this.gp.tileM.tile[tileNum2]).collision) {
/*  57 */           entity.collisionOn = true;
/*     */         }
/*     */         break; }
/*     */   
/*     */   }
/*     */   
/*     */   public int checkObject(Entity entity, boolean player) {
/*  64 */     int index = 999;
/*     */     
/*  66 */     for (int i = 0; i < this.gp.obj.length; i++) {
/*     */       
/*  68 */       if (this.gp.obj[i] != null) {
/*     */ 
/*     */         
/*  71 */         entity.solidArea.x = entity.worldX + entity.solidArea.x;
/*  72 */         entity.solidArea.y = entity.worldY + entity.solidArea.y;
/*     */         
/*  74 */         (this.gp.obj[i]).solidArea.x = (this.gp.obj[i]).worldX + (this.gp.obj[i]).solidArea.x;
/*  75 */         (this.gp.obj[i]).solidArea.y = (this.gp.obj[i]).worldY + (this.gp.obj[i]).solidArea.y;
/*     */         String str;
/*  77 */         switch ((str = entity.direction).hashCode()) { case 3739: if (!str.equals("up"))
/*     */               break; 
/*  79 */             entity.solidArea.y -= entity.speed;
/*  80 */             if (entity.solidArea.intersects((this.gp.obj[i]).solidArea)) {
/*  81 */               if ((this.gp.obj[i]).collision) {
/*  82 */                 entity.collisionOn = true;
/*     */               }
/*  84 */               if (player)
/*  85 */                 index = i; 
/*     */             }  break;
/*     */           case 3089570:
/*     */             if (!str.equals("down"))
/*     */               break; 
/*  90 */             entity.solidArea.y += entity.speed;
/*  91 */             if (entity.solidArea.intersects((this.gp.obj[i]).solidArea)) {
/*  92 */               if ((this.gp.obj[i]).collision) {
/*  93 */                 entity.collisionOn = true;
/*     */               }
/*  95 */               if (player)
/*  96 */                 index = i; 
/*     */             }  break;
/*     */           case 3317767:
/*     */             if (!str.equals("left"))
/*     */               break; 
/* 101 */             entity.solidArea.x -= entity.speed;
/* 102 */             if (entity.solidArea.intersects((this.gp.obj[i]).solidArea)) {
/* 103 */               if ((this.gp.obj[i]).collision) {
/* 104 */                 entity.collisionOn = true;
/*     */               }
/* 106 */               if (player)
/* 107 */                 index = i; 
/*     */             }  break;
/*     */           case 108511772:
/*     */             if (!str.equals("right"))
/*     */               break; 
/* 112 */             entity.solidArea.x += entity.speed;
/* 113 */             if (entity.solidArea.intersects((this.gp.obj[i]).solidArea)) {
/* 114 */               if ((this.gp.obj[i]).collision) {
/* 115 */                 entity.collisionOn = true;
/*     */               }
/* 117 */               if (player) {
/* 118 */                 index = i;
/*     */               }
/*     */             } 
/*     */             break; }
/*     */         
/* 123 */         entity.solidArea.x = entity.solidAreaDefaultX;
/* 124 */         entity.solidArea.y = entity.solidAreaDefaultY;
/* 125 */         (this.gp.obj[i]).solidArea.x = (this.gp.obj[i]).solidAreaDefaultX;
/* 126 */         (this.gp.obj[i]).solidArea.y = (this.gp.obj[i]).solidAreaDefaultY;
/*     */       } 
/*     */     } 
/* 129 */     return index;
/*     */   }
/*     */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\main\CollisionChecker.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */