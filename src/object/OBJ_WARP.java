package object;

import main.GamePanel;
import entity.Entity;

public class OBJ_WARP extends Entity{
    GamePanel gp;
    public int x , y ;
    public OBJ_WARP(GamePanel gp, int x, int y){
        super(gp);
        this.x = x;
        this.y = y;
        name = "Warp";
        down1 = setup("/res/objects/warp.png");
        solidArea.x = 10;
        solidArea.y = 10;
        solidArea.height = 28;
        solidArea.width = 28;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void teleport(GamePanel gp){
        gp.playSE(5);
        gp.player.worldX = x * gp.tileSize;
        gp.player.worldY = y * gp.tileSize+1;
    }
}
