package object;

import main.GamePanel;
import entity.Entity;

public class OBJ_KEY extends Entity{
    GamePanel gp;
    public OBJ_KEY(GamePanel gp){
        super(gp);
        type = type_consumable;
        name = "Key";
        down1 = setup("/res/objects/Key.png");
        description = "["+name+"]\nA Ancient key\n you can found everywhere.";
    }
}
