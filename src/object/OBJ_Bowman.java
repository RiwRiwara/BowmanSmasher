package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bowman extends Entity{
    GamePanel gp;
    public OBJ_Bowman(GamePanel gp){
        super(gp);
        name = "Bow";
        down1 = setup("/res/objects/bow.png");
        attackValue = 1;
        attackArea.width = 30;
        attackArea.height = 30;
        description = String.format("[%s]\nA Wooden Bow.\nDamage : %d", name, attackValue);

    }
}
