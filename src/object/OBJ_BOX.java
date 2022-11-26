package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_BOX extends SuperObject{
    public OBJ_BOX(){
        name = "Box";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/Box.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
