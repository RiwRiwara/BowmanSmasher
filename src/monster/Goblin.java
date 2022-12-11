package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class Goblin extends Entity {
    GamePanel gp;
    public Goblin(GamePanel gp, int x, int y){
        super(gp, x, y);
        this.gp = gp;
        //STATUs
        type = type_monster;
        invincibleTime = 30;
        name = "Goblin";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        attack = 1;
        life = maxLife;
        changeDirect = 100;
        motion1_duration = 40;
        motion2_duration = 85;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 44;
        attackArea.height = 44;

        //Sound
        getDamageSound = getClass().getResource("/res/sound/goblinDamage.wav");
        deadSound = getClass().getResource("/res/sound/goblinDead.wav");

        getImage();

    }
    public void getImage() {
        down1 = setup("/res/Mob/goblin/goblin_down1.png");
        down2 = setup("/res/Mob/goblin/goblin_down2.png");
        left1 = setup("/res/Mob/goblin/goblin_left1.png");
        left2 = setup("/res/Mob/goblin/goblin_left2.png");
        right1 = setup("/res/Mob/goblin/goblin_right1.png");
        right2 = setup("/res/Mob/goblin/goblin_right2.png");
        up1 = setup("/res/Mob/goblin/goblin_up1.png");
        up2 = setup("/res/Mob/goblin/goblin_up2.png");
        attackUp1 = setup("/res/Mob/goblin/goblin_attack_up1.png", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/res/Mob/goblin/goblin_attack_up2.png", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/res/Mob/goblin/goblin_attack_down1.png", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/res/Mob/goblin/goblin_attack_down2.png", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/res/Mob/goblin/goblin_attack_left1.png", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/res/Mob/goblin/goblin_attack_left2.png", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/res/Mob/goblin/goblin_attack_right1.png", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/res/Mob/goblin/goblin_attack_right2.png", gp.tileSize*2, gp.tileSize);
    }

    public void setAction() {
        if(onPath) {
            checkStopChasingOrNot(gp.player, 5, 100);
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        }else {
            checkStartChasingOrNot(gp.player, 5 , 100);
            getRandomDirection();
        }

        if(!attacking) {
            checkAttackOrNot(30, gp.tileSize*4, gp.tileSize);
        }
    }
    public void damageReaction(){

    }

}
