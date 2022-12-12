package tile_interactive;

import main.GamePanel;

public class IT_House extends InteractiveTile{
    GamePanel gp;

    public IT_House (GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "House";

        collisionOn = false;
        down1 = setup("/res/objects/home.png");
        destrucuible = true;
    }

}
