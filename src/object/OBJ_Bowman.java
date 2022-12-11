package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bowman extends Entity{
    GamePanel gp;
    public static final String objName = "Bow";
    public OBJ_Bowman(GamePanel gp){
        super(gp);
        name = objName;
        type = type_bow;
        down1 = setup("/res/objects/bow.png");
        attackValue = 1;
        attackArea.width = 30;
        attackArea.height = 30;
        description = String.format("[%s]\nA Wooden Bow.\nDamage : %d", name, attackValue);
        knockBackPower = 5;
        motion1_duration = 5;
        motion2_duration = 30;


    }
}
