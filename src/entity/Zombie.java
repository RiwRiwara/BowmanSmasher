package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Zombie extends Entity{
    public Zombie(GamePanel gp){
        super(gp);
        speed = 1;
        getImage();
    }
    public void getImage() {
        try {

            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Mob/zombie/ZOMBIE-down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Mob/zombie/ZOMBIE-down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Mob/zombie/ZOMBIE-left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Mob/zombie/ZOMBIE-left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Mob/zombie/ZOMBIE-right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Mob/zombie/ZOMBIE-right2.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Mob/zombie/ZOMBIE-up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Mob/zombie/ZOMBIE-up2.png")));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
}
