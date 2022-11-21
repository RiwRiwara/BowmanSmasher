/*    */ package object;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OBJ_Key
/*    */   extends SuperObject
/*    */ {
/*    */   public OBJ_Key() {
/*    */     try {
/* 13 */       this.image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
/*    */     }
/* 15 */     catch (IOException e) {
/* 16 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\object\OBJ_Key.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */