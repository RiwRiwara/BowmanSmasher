package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_BOX extends Entity {

    public OBJ_BOX(GamePanel gp){
        super(gp);
        name = "Box";
        down1 = setup("/res/objects/Box.png");
        collisionOn = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.height = 48;
        solidArea.width = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
