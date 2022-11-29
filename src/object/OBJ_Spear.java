package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Spear extends Entity{
    GamePanel gp;
    public OBJ_Spear(GamePanel gp){
        super(gp);
        name = "Spear";
        down1 = setup("/res/objects/spear.png");
        attackValue = 999;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "["+name+"]\nLegendary spear.";
        description = String.format("[%s]\nLegendary spear.\nDamage : %d", name, attackValue);

    }
}
