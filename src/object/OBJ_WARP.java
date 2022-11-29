package object;

import main.GamePanel;
import entity.Entity;

public class OBJ_WARP extends Entity{
    GamePanel gp;
    public OBJ_WARP(GamePanel gp){
        super(gp);
        name = "Warp";
        down1 = setup("/res/objects/warp.png");
    }
}
