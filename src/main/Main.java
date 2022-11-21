/*    */ package main;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ public class Main
/*    */ {
/*    */   public static void main(String[] args) {
/*  9 */     JFrame window = new JFrame();
/* 10 */     window.setDefaultCloseOperation(3);
/* 11 */     window.setResizable(false);
/* 12 */     window.setTitle("2D Adventure");
/*    */     
/* 14 */     GamePanel gamePanel = new GamePanel();
/* 15 */     window.add(gamePanel);
/*    */     
/* 17 */     window.pack();
/*    */     
/* 19 */     window.setLocationRelativeTo((Component)null);
/* 20 */     window.setVisible(true);
/*    */     
/* 22 */     gamePanel.setupGame();
/* 23 */     gamePanel.startGameThread();
/*    */   }
/*    */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\main\Main.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */