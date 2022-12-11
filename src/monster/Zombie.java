package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class Zombie extends Entity {
    GamePanel gp;
    public Zombie(GamePanel gp, int x, int y){
        super(gp, x, y);
        this.gp = gp;
        //STATUs
        type = type_monster;
        invincibleTime = 30;
        name = "Zombie";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 3;
        attack = 2;
        life = maxLife;
        changeDirect = 100;

        solidArea.x = 3;
        solidArea.y = 15;
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
    public void update() {
        super.update();
        if(!isStunt) {
            speed = defaultSpeed;
        }
        if(isStunt){
            stuntCounter++;
        }
        if(stuntCounter==50){
            stuntCounter = 0;
            isStunt = false;
        }
        
    }

    public void setAction() {
        if(onPath) {
            checkStopChasingOrNot(gp.player, 5, 100);
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        }else {
            checkStartChasingOrNot(gp.player, 5 , 100);
            getRandomDirection();
        }

    }
    public void damageReaction(){

        actionLockCounter = 0;
        defaultSpeed++;
        if(defaultSpeed == 3 ){
            changeStateZombie();
        }
        changeDirect -= 40;
        speed = 0;
        isStunt = true;
    }
    public void changeStateZombie(){

        down1 = setup("/res/Mob/zombie/state2/ZOMBIE-down1.png");
        down2 = setup("/res/Mob/zombie/state2/ZOMBIE-down2.png");
        left1 = setup("/res/Mob/zombie/state2/ZOMBIE-left1.png");
        left2 = setup("/res/Mob/zombie/state2/ZOMBIE-left2.png");
        right1 = setup("/res/Mob/zombie/state2/ZOMBIE-right1.png");
        right2 = setup("/res/Mob/zombie/state2/ZOMBIE-right2.png");
        up1 = setup("/res/Mob/zombie/state2/ZOMBIE-up1.png");
        up2 = setup("/res/Mob/zombie/state2/ZOMBIE-up2.png");
    }
}
