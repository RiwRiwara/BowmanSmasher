package object;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Fireball extends Projectile {
    GamePanel gp;
    public static final String objName = "Fireball";
    public OBJ_Fireball(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = objName;
        speed = 10;
        maxLife = 50;
        life = maxLife;
        attack = 2;
        useStamina = 1;
        alive = false;
        knockBackPower = 0;
        getImage();

        solidArea = new Rectangle();
        solidArea.x = 10;
        solidArea.y = 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 30;
    }
    public void getImage(){
        down1 = setup("/res/objects/fireball/fire_down1.png");
        down2 = setup("/res/objects/fireball/fire_down2.png");
        left1 = setup("/res/objects/fireball/fire_left1.png");
        left2 = setup("/res/objects/fireball/fire_left2.png");
        right1 = setup("/res/objects/fireball/fire_right1.png");
        right2 = setup("/res/objects/fireball/fire_right2.png");
        up1 = setup("/res/objects/fireball/fire_up1.png");
        up2 = setup("/res/objects/fireball/fire_up2.png");
    }
}
