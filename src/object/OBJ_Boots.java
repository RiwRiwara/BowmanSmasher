/*    */ package object;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OBJ_Boots
/*    */   extends SuperObject
/*    */ {
/*    */   public OBJ_Boots() {
/*    */     try {
/* 13 */       this.image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
/*    */     }
/* 15 */     catch (IOException e) {
/* 16 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\object\OBJ_Boots.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */