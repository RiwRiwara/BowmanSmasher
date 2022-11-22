package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;


public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues() {
        // TODO: 22/11/2565 Player start point(X, Y) 
        int startX = 15;
        int startY = 42;
        worldX = gp.tileSize * startX;
        worldY = gp.tileSize * startY;
        this.speed = 3;
        this.direction = "down";
    }


    public void getPlayerImage() {
        try {

                    down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/boman_down1.png")));
                    down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/boman_down2.png")));
                    left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/boman_left1.png")));
                    left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/boman_left2.png")));
                    right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/boman_right1.png")));
                    right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/boman_right2.png")));
                    up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/boman_up1.png")));
                    up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/boman_up2.png")));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
            }
            else if (keyH.downPressed) {
                direction = "down";
            }
            else if (keyH.leftPressed) {
                direction = "left";
            }
            else if (this.keyH.rightPressed) {
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);


            if (!collisionOn) {
                switch (direction) {
                    case "up" : worldY -= speed;break;
                    case "down" : worldY += speed;break;
                    case "left" : worldX -= speed;break;
                    case "right" : worldX += speed;break;
                }
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }




    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if (this.spriteNum == 1) {
                    image = left1;
                }
                if (this.spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (this.spriteNum == 1) {
                    image = right1;
                }
                if (this.spriteNum == 2) {
                    image = right2;
                }
                break; }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}