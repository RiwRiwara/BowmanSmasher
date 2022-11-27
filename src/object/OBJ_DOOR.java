package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_DOOR extends Entity {
    public OBJ_DOOR(GamePanel gp){
        super(gp);
        name = "Door";
        down1 = setup("/res/objects/Door.png");
        collisionOn = true;
    }
}
