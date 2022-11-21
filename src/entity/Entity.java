/*    */ package entity;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ public class Entity {
/*    */   public int worldX;
/*    */   public int worldY;
/*    */   public int speed;
/*    */   public BufferedImage up1;
/*    */   public BufferedImage up2;
/*    */   public BufferedImage down1;
/* 12 */   public int spriteCounter = 0; public BufferedImage down2; public BufferedImage left1; public BufferedImage left2; public BufferedImage right1; public BufferedImage right2; public String direction;
/* 13 */   public int spriteNum = 1;
/*    */   public Rectangle solidArea;
/*    */   public int solidAreaDefaultX;
/*    */   public int solidAreaDefaultY;
/*    */   public boolean collisionOn = false;
/*    */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\entity\Entity.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */