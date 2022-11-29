package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bowman extends Entity{
    GamePanel gp;
    public OBJ_Bowman(GamePanel gp){
        super(gp);
        name = "BoBow";
        down1 = setup("/res/objects/bow.png");
        attackValue = 1;

    }
}
