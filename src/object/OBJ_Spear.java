package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Spear extends Entity{
    GamePanel gp;
    public static final String objName = "Spear";
    public OBJ_Spear(GamePanel gp){
        super(gp);
        type = type_spear;
        name = objName;
        down1 = setup("/res/objects/spear.png");
        attackValue = 3;
        attackArea.width = 36;
        attackArea.height = 36;
        description = String.format("[%s]\nLegendary spear.\nDamage : %d", name, attackValue);
        motion1_duration = 45;
        motion2_duration = 85;
        knockBackPower = 6;
    }
}
