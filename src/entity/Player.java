package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;


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

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }


    public void setDefaultValues() {
        // TODO: 22/11/2565 Player start point(X, Y)
        type = 0;
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
    public void getPlayerAttackImage(){
        attackUp1 = setup("/res/player/boman_attack_up1.png", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/res/player/boman_attack_up2.png", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/res/player/boman_attack_down1.png", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/res/player/boman_attack_down2.png", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/res/player/boman_attack_left1.png", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/res/player/boman_attack_left2.png", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/res/player/boman_attack_right1.png", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/res/player/boman_attack_right2.png", gp.tileSize*2, gp.tileSize);
    }


    public void update() {

        if (attacking){
            attacking();
        }
        else if (keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {

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

            //Check Monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //Check Event
            gp.eHandler.checkEvent();

            //IF collision == False, Player can move
            if (!collisionOn && !keyH.enterPressed) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
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
        }else{
            standCounter++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }
        }

        //need to be outside of key statement
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }


    }
    public void attacking(){
        spriteCounter++;
        if(spriteCounter <=5){
            spriteNum = 1;
        }
        if(spriteCounter >5 && spriteCounter<=25 ){
            spriteNum = 2;

            //Save current wx wy
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //ADjust player attack area
            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //Check monster Collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;



        }
        if(spriteCounter >25 ){
            spriteNum = 1;
            spriteCounter=0;
            attacking = false;
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
        if(gp.keyH.enterPressed){
            if(i != 999){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();

                switch (direction) {
                    case "down" -> gp.npc[i].direction = "up";
                    case "up" -> gp.npc[i].direction = "down";
                    case "right" -> gp.npc[i].direction = "left";
                    case "left" -> gp.npc[i].direction = "right";
                }
            }else {
                attacking = true;
            }
        }
        gp.keyH.enterPressed = false;
    }

    public void damageMonster(int i){
        if(i!=999){
            if(gp.monster[i].invincible == false){
                gp.monster[i].life -=1;
                gp.monster[i].invincible = true;

                if(gp.monster[i].life <= 0){
                    gp.monster[i] = null;
                }
            }
        }
    }
    public void contactMonster(int i){
        if(i!=999){
            if(!invincible) {
                life -= 1;
                invincible = true;
            }

        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction) {
            case "up" -> {
                if (!attacking){
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                if (attacking){
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                }
            }
            case "down" -> {
                if (!attacking){
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }
                if (attacking){
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
            }
            case "left" -> {
                if (!attacking){
                    if (this.spriteNum == 1) {image = left1;}
                    if (this.spriteNum == 2) {image = left2;}
                }
                if (attacking){
                    tempScreenX = screenX - gp.tileSize;
                    if (this.spriteNum == 1) {image = attackLeft1;}
                    if (this.spriteNum == 2) {image = attackLeft2;}
                }
            }
            case "right" -> {
                if (!attacking){
                    if (this.spriteNum == 1) {image = right1;}
                    if (this.spriteNum == 2) {image = right2;}
                }
                if (attacking){
                    if (this.spriteNum == 1) {image = attackRight1;}
                    if (this.spriteNum == 2) {image = attackRight2;}
                }
            }
        }

        if(invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY,null);
        //Reset
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

}