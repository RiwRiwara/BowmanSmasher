package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_HEART extends Entity {
    GamePanel gp;
    public static final String objName = "Heart";
    public OBJ_HEART(GamePanel gp){
        super(gp);
        name = objName;
        image = setup("/res/objects/fullHeart.png");
        image2 = setup("/res/objects/halfHeart.png");
        image3 = setup("/res/objects/noHeart.png");
    }
}
