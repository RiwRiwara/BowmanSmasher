/*    */ package main;
/*    */
/*    */ import object.OBJ_Boots;
/*    */ import object.OBJ_Chest;
/*    */ import object.OBJ_Door;
/*    */ import object.OBJ_Key;

/*    */ 
/*    */ public class AssetSetter {
/*    */   GamePanel gp;
/*    */   
/*    */   public AssetSetter(GamePanel gp) {
/* 13 */     this.gp = gp;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setObject() {
/* 18 */     gp.obj[0] = new OBJ_Key();
/* 19 */     gp.obj[0].worldX = 23 * 48;
/* 20 */     gp.obj[0].worldY = 7 * 48;
/*    */     
/* 22 */     gp.obj[1] = new OBJ_Key();
/* 23 */     gp.obj[1].worldX = 23 * 48;
/* 24 */     gp.obj[1].worldY = 40 * 48;
/*    */     
/* 26 */     gp.obj[2] = new OBJ_Key();
/* 27 */     gp.obj[2].worldX = 38 * 48;
/* 28 */     gp.obj[2].worldY = 8 * 48;
/*    */     
/* 30 */     gp.obj[3] = new OBJ_Door();
/* 31 */     gp.obj[3].worldX = 10 * 48;
/* 32 */     gp.obj[3].worldY = 11 * 48;
/*    */     
/* 34 */     gp.obj[4] = new OBJ_Door();
/* 35 */     gp.obj[4].worldX = 8 * 48;
/* 36 */     gp.obj[4].worldY = 28 * 48;
/*    */     
/* 38 */     gp.obj[5] = new OBJ_Door();
/* 39 */     gp.obj[5].worldX = 12 * 48;
/* 40 */     gp.obj[5].worldY = 22 * 48;
/*    */     
/* 42 */     gp.obj[6] = new OBJ_Chest();
/* 43 */     gp.obj[6].worldX = 10 * 48;
/* 44 */     gp.obj[6].worldY = 7 * 48;
/*    */     
/* 46 */     gp.obj[7] = new OBJ_Boots();
/* 47 */     gp.obj[7].worldX = 37 * 48;
/* 48 */     gp.obj[7].worldY = 42 * 48;
/*    */   }
/*    */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\main\AssetSetter.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */