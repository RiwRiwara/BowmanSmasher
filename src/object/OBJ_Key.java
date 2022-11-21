/*    */ package object;
/*    */ 
/*    */ import java.io.IOException;
import java.util.Objects;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OBJ_Key
/*    */   extends SuperObject
/*    */ {
/*    */   public OBJ_Key() {
/*    */     try {
/* 13 */       this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
/*    */     }
/* 15 */     catch (IOException e) {
/* 16 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


