package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class NPC_Dad extends Entity{
    public int dialogueIndex = 0;
    public NPC_Dad(GamePanel gp){
        super(gp);
        type = 1;
        speed = 2;
        setDialogue();
        getImage();
        solidArea.x = 9;
        solidArea.y = 18;
        solidArea.width = 30;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void getImage() {
            down1 = setup("/res/Mob/dad/dad_down1.png");
            down2 = setup("/res/Mob/dad/dad_down2.png");
            left1 = setup("/res/Mob/dad/dad_left1.png");
            left2 = setup("/res/Mob/dad/dad_left2.png");
            right1 = setup("/res/Mob/dad/dad_right1.png");
            right2 = setup("/res/Mob/dad/dad_right2.png");
            up1 = setup("/res/Mob/dad/dad_up1.png");
            up2 = setup("/res/Mob/dad/dad_up2.png");
    }
    public void setDialogue (){
        dialogues[0] = "Hey, son.";
        dialogues[1] = "What happen?";
        dialogues[2] = "Why you scare?";
    }

    public void setAction() {

                actionLockCounter++;
                if (actionLockCounter == 120) {
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
                    actionLockCounter = 0;
                }


    }
    public void speak(){
        dialogueIndex = new Random().nextInt(3);
        gp.ui.currentDialogue = dialogues[dialogueIndex];

    }


}
