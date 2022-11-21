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


