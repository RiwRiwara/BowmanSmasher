/*    */ package object;
/*    */ 
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.BufferedImage;
/*    */ import main.GamePanel;
/*    */ 
/*    */ public class SuperObject
/*    */ {
/*    */   public BufferedImage image;
/*    */   public String name;
/*    */   public boolean collision = false;
/*    */   public int worldX;
/*    */   public int worldY;
/* 15 */   public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
/* 16 */   public int solidAreaDefaultX = 0;
/* 17 */   public int solidAreaDefaultY = 0;
/*    */ 
/*    */   
/*    */   public void draw(Graphics2D g2, GamePanel gp) {
/* 21 */     int screenX = this.worldX - gp.player.worldX + gp.player.screenX;
/* 22 */     int screenY = this.worldY - gp.player.worldY + gp.player.screenY;
/*    */     
/* 24 */     gp.getClass();
/* 25 */     gp.getClass();
/* 26 */     gp.getClass();
/* 27 */     gp.getClass(); if (this.worldX + 48 > gp.player.worldX - gp.player.screenX && this.worldX - 48 < gp.player.worldX + gp.player.screenX && this.worldY + 48 > gp.player.worldY - gp.player.screenY && this.worldY - 48 < gp.player.worldY + gp.player.screenY) {
/*    */       
/* 29 */       gp.getClass(); gp.getClass(); g2.drawImage(this.image, screenX, screenY, 48, 48, null);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\object\SuperObject.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */