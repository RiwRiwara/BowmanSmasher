package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_DOOR extends Entity {
    GamePanel gp;
    public static final String objName = "Door";
    public OBJ_DOOR(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = objName;
        down1 = setup("/res/objects/Door.png");
        collisionOn = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }
    public void setDialogue (){
        dialogues[0][0] = "You need Key!";
        dialogues[1][0] = "Unlock!";
    }
    public boolean interactDoor() {
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            if(gp.player.inventory.get(i).name == "Key") {
                startDialogue(this, 1);
                gp.player.inventory.remove(i);
                gp.playSE(2);
                return true;
            }
        }
        startDialogue(this, 0);
        return false;

    }
}
