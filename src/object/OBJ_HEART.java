package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_HEART extends SuperObject{
    GamePanel gp;
    public OBJ_HEART(GamePanel gp){
        this.gp = gp;
        name = "Heart";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/fullHeart.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/halfHeart.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/noHeart.png")));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 =  uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        solidArea.x = 5;
    }
}
