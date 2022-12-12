package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_House extends Entity{
    GamePanel gp;
    public static final String objName = "House";
    public OBJ_House(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = objName;
        down1 = setup("/res/objects/home.png");
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.height = 48;
        solidArea.width = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    @Override
    public void interact() {
        super.interact();
        System.out.println("This is your home.");
    }
}
