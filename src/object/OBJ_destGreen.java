package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_destGreen extends Entity {
    GamePanel gp;
    public static final String objName = "destGreen";

    public OBJ_destGreen(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = objName;
        down1 = setup("/res/objects/greenDest/d1.png");
        effect = true;


    }

}
