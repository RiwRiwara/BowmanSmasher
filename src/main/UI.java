/*    */ package main;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.text.DecimalFormat;
/*    */ import object.OBJ_Key;
/*    */ 
/*    */ public class UI
/*    */ {
/*    */   GamePanel gp;
/*    */   Font arial_40;
/*    */   Font arial_80B;
/*    */   BufferedImage keyImage;
/*    */   public boolean messageOn = false;
/* 17 */   public String message = "";
/* 18 */   int messageCounter = 0;
/*    */   
/*    */   public boolean gameFinished = false;
/*    */   double playTime;
/* 22 */   DecimalFormat dFormat = new DecimalFormat("#0.00");
/*    */ 
/*    */   
/*    */   public UI(GamePanel gp) {
/* 26 */     this.gp = gp;
/*    */     
/* 28 */     this.arial_40 = new Font("Arial", 0, 40);
/* 29 */     this.arial_80B = new Font("Arial", 1, 80);
/* 30 */     OBJ_Key key = new OBJ_Key();
/* 31 */     this.keyImage = key.image;
/*    */   }
/*    */ 
/*    */   
/*    */   public void showMessage(String text) {
/* 36 */     this.message = text;
/* 37 */     this.messageOn = true;
/*    */   }
/*    */   
/*    */   public void draw(Graphics2D g2) {
/* 41 */     if (this.gameFinished) {
/*    */       
/* 43 */       g2.setFont(this.arial_40);
/* 44 */       g2.setColor(Color.white);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 51 */       String text = "You found the treasure!";
/* 52 */       int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
/* 53 */       this.gp.getClass(); int x = 768 / 2 - textLength / 2;
/* 54 */       this.gp.getClass(); this.gp.getClass(); int y = 576 / 2 - 48 * 3;
/* 55 */       g2.drawString(text, x, y);
/*    */       
/* 57 */       text = "Your Time is " + this.dFormat.format(this.playTime) + "!";
/* 58 */       textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
/* 59 */       this.gp.getClass(); x = 768 / 2 - textLength / 2;
/* 60 */       this.gp.getClass(); this.gp.getClass(); y = 576 / 2 + 48 * 4;
/* 61 */       g2.drawString(text, x, y);
/*    */ 
/*    */       
/* 64 */       g2.setFont(this.arial_80B);
/* 65 */       g2.setColor(Color.yellow);
/* 66 */       text = "Congratulations!";
/* 67 */       textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
/* 68 */       this.gp.getClass(); x = 768 / 2 - textLength / 2;
/* 69 */       this.gp.getClass(); this.gp.getClass(); y = 576 / 2 + 48 * 2;
/* 70 */       g2.drawString(text, x, y);
/*    */       
/* 72 */       this.gp.gameThread = null;
/*    */     } else {
/*    */       
/* 75 */       g2.setFont(this.arial_40);
/* 76 */       g2.setColor(Color.white);
/*    */       
/* 78 */       this.gp.getClass(); this.gp.getClass(); this.gp.getClass(); this.gp.getClass(); g2.drawImage(this.keyImage, 48 / 2, 48 / 2, 48, 48, null);
/* 79 */       g2.drawString("x " + this.gp.player.hasKey, 74, 65);
/*    */ 
/*    */       
/* 82 */       this.playTime += 0.016666666666666666D;
/* 83 */       this.gp.getClass(); g2.drawString("Time:" + this.dFormat.format(this.playTime), 48 * 11, 65);
/*    */ 
/*    */ 
/*    */       
/* 87 */       if (this.messageOn) {
/*    */         
/* 89 */         g2.setFont(g2.getFont().deriveFont(30.0F));
/* 90 */         this.gp.getClass(); this.gp.getClass(); g2.drawString(this.message, 48 / 2, 48 * 5);
/*    */         
/* 92 */         this.messageCounter++;
/*    */         
/* 94 */         if (this.messageCounter > 120) {
/* 95 */           this.messageCounter = 0;
/* 96 */           this.messageOn = false;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\main\UI.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */