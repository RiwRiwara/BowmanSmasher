/*    */ package object;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OBJ_Door
/*    */   extends SuperObject
/*    */ {
/*    */   public OBJ_Door() {
/*    */     try {
/* 13 */       this.image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
/*    */     }
/* 15 */     catch (IOException e) {
/* 16 */       e.printStackTrace();
/*    */     } 
/* 18 */     this.collision = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\object\OBJ_Door.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */