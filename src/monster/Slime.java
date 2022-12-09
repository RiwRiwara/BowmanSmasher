package monster;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import object.OBJ_Fireball;

import java.util.Random;

public class Slime extends Entity {
    int tempSpeed;
    int changeDirect = 30;
    public Slime(GamePanel gp, int x, int y){
        super(gp, x, y);

        //STATUs
        type = type_monster;
        invincibleTime = 30;
        name = "Slime";
        speed = 1;
        tempSpeed = speed;
        maxLife = 10;
        attack = 1;
        life = maxLife;
        projectile = new OBJ_Fireball(gp);

        solidArea.x = 0;
        solidArea.y = 10;
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
        if(onPath){
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol, goalRow);
            //Shoot Fire
            int i = new Random().nextInt(200)+1;
            if(i>190 && !projectile.alive && shotAvailableCounter == 40) {
                projectile.set(worldX, worldY, direction, true, this);
                gp.projecttileList.add(projectile);
                shotAvailableCounter = 0;
            }

        }else{
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

    }
    public void damageReaction(){
        actionLockCounter = 0;
        onPath = true;
        speed = 2;

    }

}
