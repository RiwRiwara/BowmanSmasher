package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_DOOR extends SuperObject{
    public OBJ_DOOR(){
        name = "Door";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/Door.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
