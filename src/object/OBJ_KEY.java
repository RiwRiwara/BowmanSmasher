package object;

import main.GamePanel;
import entity.Entity;

public class OBJ_KEY extends Entity{
    GamePanel gp;
    public OBJ_KEY(GamePanel gp){
        super(gp);
        name = "Key";
        down1 = setup("/res/objects/Key.png");
    }
}
