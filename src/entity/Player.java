package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;


public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
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
        int startX = 5;
        int startY = 32;
        worldX = gp.tileSize * startX;
        worldY = gp.tileSize * startY;
        this.speed = 3;
        this.direction = "down";

        //Player Status
        maxLife = 6;
        life = maxLife;
    }


    public void getPlayerImage() {
        down1 = setup("/res/player/boman_down1.png");
        down2 = setup("/res/player/boman_down2.png");
        left1 = setup("/res/player/boman_left1.png");
        left2 = setup("/res/player/boman_left2.png");
        right1 = setup("/res/player/boman_right1.png");
        right2 = setup("/res/player/boman_right2.png");
        up1 = setup("/res/player/boman_up1.png");
        up2 = setup("/res/player/boman_up2.png");
    }
    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage Image = null;
        try {
            Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            Image = uTool.scaleImage(Image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return Image;
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

            //Check TILE Collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //Check OBJ collision
            int objIndex =  gp.cChecker.checkObject(this, true);
            pickUpObj(objIndex);

            //Check Npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);


            //IF collision == False, Player can move
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

    public void pickUpObj(int index){
        if(index != 999){
            String objName = gp.obj[index].name;
            switch (objName){
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[index] = null;
                    gp.ui.showMessage("get key!");
                    break;
                case "Door":
                    if(hasKey>0){
                        gp.playSE(2);
                        gp.obj[index] = null;
                        hasKey--;
                    }
                    break;
            }
        }
    }

    public void interactNPC(int i){
        if(i != 999){
            if(gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
                switch (direction){
                    case "down":
                        gp.npc[i].direction = "up";
                        break;
                    case "up":
                        gp.npc[i].direction = "down";
                        break;
                    case "right":
                        gp.npc[i].direction = "left";
                        break;
                    case "left":
                        gp.npc[i].direction = "right";
                        break;

                }
            }
        }
        gp.keyH.enterPressed = false;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (this.spriteNum == 1) {
                    image = left1;
                }
                if (this.spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (this.spriteNum == 1) {
                    image = right1;
                }
                if (this.spriteNum == 2) {
                    image = right2;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}