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
/*     */ }


