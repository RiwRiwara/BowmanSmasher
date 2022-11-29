package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class Zombie extends Entity {
    int tempSpeed;
    public Zombie(GamePanel gp){
        super(gp);

        //STATUs
        type = 2;
        name = "Zombie";
        speed = 1;
        tempSpeed = speed;
        maxLife = 3;
        attack = 2;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 16;
        solidArea.width = 42;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        //Sound
        getDamageSound = getClass().getResource("/res/sound/zombieDamage.wav");
        deadSound = getClass().getResource("/res/sound/zombieDead.wav");

        getImage();
    }
    public void getImage() {
            down1 = setup("/res/Mob/zombie/ZOMBIE-down1.png");
            down2 = setup("/res/Mob/zombie/ZOMBIE-down2.png");
            left1 = setup("/res/Mob/zombie/ZOMBIE-left1.png");
            left2 = setup("/res/Mob/zombie/ZOMBIE-left2.png");
            right1 = setup("/res/Mob/zombie/ZOMBIE-right1.png");
            right2 = setup("/res/Mob/zombie/ZOMBIE-right2.png");
            up1 = setup("/res/Mob/zombie/ZOMBIE-up1.png");
            up2 = setup("/res/Mob/zombie/ZOMBIE-up2.png");

    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 60) {
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
        actionLockCounter = 0;
        tempSpeed++;
        speed = 0;
    }
}
