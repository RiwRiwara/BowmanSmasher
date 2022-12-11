package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class Skeleton extends Entity {
    public Skeleton(GamePanel gp, int x, int y){
        super(gp, x, y);

        //STATUs
        changeDirect = 20;
        type = type_monster;
        invincibleTime = 10;
        name = "Skeleton";
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 5;
        attack = 1;
        life = maxLife;

        solidArea.x = 16;
        solidArea.y = 2;
        solidArea.width = 40;
        solidArea.height = 40;
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
            up2 = setup("/res/Mob/skeleton/skeleton_up2.png");

    }


    public void setAction() {

        if(onPath) {
            //Check stop chasing
            checkStopChasingOrNot(gp.player, 5, 100);
            //Search direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        }else{

            //Start chasing
            checkStartChasingOrNot(gp.player, 5, 100);
            getRandomDirection();
        }


    }
    public void damageReaction(){


    }

}
