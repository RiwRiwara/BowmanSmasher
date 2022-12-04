package monster;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import object.OBJ_Fireball;

import java.util.Random;

public class Slime extends Entity {
    int tempSpeed;
    int changeDirect = 100;
    public Slime(GamePanel gp, int x, int y){
        super(gp, x, y);

        //STATUs
        type = type_monster;
        invincibleTime = 30;
        name = "Slime";
        speed = 2;
        tempSpeed = speed;
        maxLife = 2;
        attack = 1;
        life = maxLife;
        projectile = new OBJ_Fireball(gp);

        solidArea.x = 3;
        solidArea.y = 16;
        solidArea.width = 42;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        //Sound
        getDamageSound = getClass().getResource("/res/sound/slimeDamage.wav");
        deadSound = getClass().getResource("/res/sound/slimeDead.wav");

        getImage();
    }
    public void getImage() {
            down1 = setup("/res/Mob/slime/slime_down1.png");
            down2 = setup("/res/Mob/slime/slime_down2.png");
            left1 = setup("/res/Mob/slime/slime_left1.png");
            left2 = setup("/res/Mob/slime/slime_left2.png");
            right1 = setup("/res/Mob/slime/slime_right1.png");
            right2 = setup("/res/Mob/slime/slime_right2.png");
            up1 = setup("/res/Mob/slime/slime_up1.png");
            up2 = setup("/res/Mob/slime/slime_up1.png");

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

        //Shoot Fire

        int i = new Random().nextInt(100)+1;
        if(!projectile.alive && shotAvailableCounter == 40) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projecttileList.add(projectile);
            shotAvailableCounter = 0;
        }
        if(shotAvailableCounter < 40) {
            shotAvailableCounter++;
        }
    }
    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;

    }

}
