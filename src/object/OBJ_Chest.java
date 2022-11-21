/*    */ package object;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OBJ_Chest
/*    */   extends SuperObject
/*    */ {
/*    */   public OBJ_Chest() {
/*    */     try {
/* 13 */       this.image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
/*    */     }
/* 15 */     catch (IOException e) {
/* 16 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\object\OBJ_Chest.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */