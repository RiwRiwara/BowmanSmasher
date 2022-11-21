/*    */ package main;
/*    */ 
/*    */ import java.awt.event.KeyEvent;
/*    */ import java.awt.event.KeyListener;
/*    */ 
/*    */ public class KeyHandler
/*    */   implements KeyListener
/*    */ {
/*    */   public boolean upPressed;
/*    */   public boolean downPressed;
/*    */   public boolean leftPressed;
/*    */   public boolean rightPressed;
/*    */   
/*    */   public void keyTyped(KeyEvent e) {}
/*    */   
/*    */   public void keyPressed(KeyEvent e) {
/* 17 */     int code = e.getKeyCode();
/*    */     
/* 19 */     if (code == 87) {
/* 20 */       this.upPressed = true;
/*    */     }
/* 22 */     if (code == 83) {
/* 23 */       this.downPressed = true;
/*    */     }
/* 25 */     if (code == 65) {
/* 26 */       this.leftPressed = true;
/*    */     }
/* 28 */     if (code == 68) {
/* 29 */       this.rightPressed = true;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void keyReleased(KeyEvent e) {
/* 36 */     int code = e.getKeyCode();
/*    */     
/* 38 */     if (code == 87) {
/* 39 */       this.upPressed = false;
/*    */     }
/* 41 */     if (code == 83) {
/* 42 */       this.downPressed = false;
/*    */     }
/* 44 */     if (code == 65) {
/* 45 */       this.leftPressed = false;
/*    */     }
/* 47 */     if (code == 68)
/* 48 */       this.rightPressed = false; 
/*    */   }
/*    */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\main\KeyHandler.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */