package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class Skeleton extends Entity {
    int tempSpeed;
    int changeDirect = 10;
    public Skeleton(GamePanel gp, int x, int y){
        super(gp, x, y);

        //STATUs
        type = type_monster;
        invincibleTime = 100;
        name = "Skeleton";
        speed = 2;
        tempSpeed = speed;
        maxLife = 5;
        attack = 1;
        life = maxLife;

        solidArea.x = 18;
        solidArea.y = 9;
        solidArea.width = 30;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        //Sound
        getDamageSound = getClass().getResource("/res/sound/skelDamage.wav");
        deadSound = getClass().getResource("/res/sound/skelDead.wav");

        getImage();
    }
    public void getImage() {
            down1 = setup("/res/Mob/skeleton/skeleton_down1.png");
            down2 = setup("/res/Mob/skeleton/skeleton_down2.png");
            left1 = setup("/res/Mob/skeleton/skeleton_left1.png");
            left2 = setup("/res/Mob/skeleton/skeleton_left2.png");
            right1 = setup("/res/Mob/skeleton/skeleton_right1.png");
            right2 = setup("/res/Mob/skeleton/skeleton_right2.png");
            up1 = setup("/res/Mob/skeleton/skeleton_up1.png");
            up2 = setup("/res/Mob/skeleton/skeleton_up1.png");

    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == changeDirect) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // 1 -100
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            speed = tempSpeed;
            actionLockCounter = 0;
        }

    }
    public void damageReaction(){


    }

}
