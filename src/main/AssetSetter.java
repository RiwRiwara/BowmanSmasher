/*    */ package main;
/*    */ 
/*    */ import object.OBJ_Boots;
/*    */ import object.OBJ_Chest;
/*    */ import object.OBJ_Door;
/*    */ import object.OBJ_Key;
/*    */ import object.SuperObject;
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
/* 18 */     this.gp.obj[0] = (SuperObject)new OBJ_Key();
/* 19 */     this.gp.getClass(); (this.gp.obj[0]).worldX = 23 * 48;
/* 20 */     this.gp.getClass(); (this.gp.obj[0]).worldY = 7 * 48;
/*    */     
/* 22 */     this.gp.obj[1] = (SuperObject)new OBJ_Key();
/* 23 */     this.gp.getClass(); (this.gp.obj[1]).worldX = 23 * 48;
/* 24 */     this.gp.getClass(); (this.gp.obj[1]).worldY = 40 * 48;
/*    */     
/* 26 */     this.gp.obj[2] = (SuperObject)new OBJ_Key();
/* 27 */     this.gp.getClass(); (this.gp.obj[2]).worldX = 38 * 48;
/* 28 */     this.gp.getClass(); (this.gp.obj[2]).worldY = 8 * 48;
/*    */     
/* 30 */     this.gp.obj[3] = (SuperObject)new OBJ_Door();
/* 31 */     this.gp.getClass(); (this.gp.obj[3]).worldX = 10 * 48;
/* 32 */     this.gp.getClass(); (this.gp.obj[3]).worldY = 11 * 48;
/*    */     
/* 34 */     this.gp.obj[4] = (SuperObject)new OBJ_Door();
/* 35 */     this.gp.getClass(); (this.gp.obj[4]).worldX = 8 * 48;
/* 36 */     this.gp.getClass(); (this.gp.obj[4]).worldY = 28 * 48;
/*    */     
/* 38 */     this.gp.obj[5] = (SuperObject)new OBJ_Door();
/* 39 */     this.gp.getClass(); (this.gp.obj[5]).worldX = 12 * 48;
/* 40 */     this.gp.getClass(); (this.gp.obj[5]).worldY = 22 * 48;
/*    */     
/* 42 */     this.gp.obj[6] = (SuperObject)new OBJ_Chest();
/* 43 */     this.gp.getClass(); (this.gp.obj[6]).worldX = 10 * 48;
/* 44 */     this.gp.getClass(); (this.gp.obj[6]).worldY = 7 * 48;
/*    */     
/* 46 */     this.gp.obj[7] = (SuperObject)new OBJ_Boots();
/* 47 */     this.gp.getClass(); (this.gp.obj[7]).worldX = 37 * 48;
/* 48 */     this.gp.getClass(); (this.gp.obj[7]).worldY = 42 * 48;
/*    */   }
/*    */ }


/* Location:              C:\Users\Admin\Desktop\ProjectBowman\RyiSclone\BlueBoyTreasure v1.0.jar!\main\AssetSetter.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */