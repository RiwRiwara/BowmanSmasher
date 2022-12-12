package entity;
import main.GamePanel;
import main.UtilityTool;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Entity {
    public GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX,  solidAreaDefaultY;
    public BufferedImage image, image2, image3;
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public String[][] dialogues = new String[20][20];
    public Entity attacker;

    //STATE
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public String knockBackDirection;
    public boolean effect = false;

    //Counter
    public int actionLockCounter = 0;
    public  int standCounter = 0;
    public int invincibleCounter = 0;
    public int spriteCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int shotAvailableCounter = 0;
    public int knockBackCounter = 0;


    //Character Status
    public int maxLife;
    public int life;
    public int speed;
    public int attack;
    public int stamina;
    public String name;
    public int invincibleTime;
    public int changeDirect;
    //Speed of attack
    public int motion1_duration;
    public int motion2_duration;

    //
    public Entity currentWeapon;
    public Entity getCurrentShield;
    public Projectile projectile;

    //item attribute
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useStamina;
    public boolean isStunt = false;
    public int stuntCounter = 0;
    public int defaultSpeed;
    public int knockBackPower = 0;

    //TYPE
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_bow = 3;
    public final int type_spear = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_obstacle= 7;



    //Sound
    public Clip clip;
    public URL getDamageSound ;
    public URL deadSound;
    public URL attackSound;

    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public Entity(GamePanel gp, int x, int y){
        this.gp = gp;
        this.worldX = x * gp.tileSize;
        this.worldY = y * gp.tileSize;
    }

    public void resetCounter(){
        actionLockCounter = 0;
        standCounter = 0;
        invincibleCounter = 0;
        spriteCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        shotAvailableCounter = 0;
        knockBackCounter = 0;
    }
    public int getLeftX() {return worldX + solidArea.x;}
    public int getRightX() {return worldX + solidArea.x + solidArea.width;}
    public int getTopY() {return worldY + solidArea.y;}
    public int getBottomY() {return worldY + solidArea.y + solidArea.height;}
    public int getCol(){return (worldX + solidArea.x)/gp.tileSize;}
    public int getRow(){return (worldY + solidArea.y)/gp.tileSize;}
    public int getXDistance(Entity target){
        return Math.abs(worldX - target.worldX);
    }
    public int getYDistance(Entity target){
        return Math.abs(worldY - target.worldY);
    }
    public int getTileDistance(Entity target){
        return Math.abs(getXDistance(target) - getYDistance(target)) / gp.tileSize;
    }
    public int getGoalCol(Entity target) {
        return (target.worldX + target.solidArea.x) / gp.tileSize;
    }
    public int getGoalRow(Entity target) {
        return (target.worldY + target.solidArea.y) / gp.tileSize;
    }
    public void setAction(){}
    public void damageReaction(){}
    public void speak(){

    }
    public void  facePlayer(){
        switch (gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "right" -> direction = "left";
            case "left" -> direction = "right";
        }
    }
    public void startDialogue(Entity entity, int setNum){
        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }
    public void interact() {

    }
    public boolean interactDoor() {
        return false;
    }
    public boolean use(Entity player){return false;}
    public void teleport(GamePanel gp){}
    public void checkCollision(){
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.npc);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if(this.type == type_monster && contactPlayer){
            damagePlayer(attack);
            if(name == "Zombie") {
                speed = 0;
                isStunt = true;
            }
        }
    }
    public void update(){

        if(knockBack && !dying) {
            checkCollision();
            if(collisionOn) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            } else {
                switch (knockBackDirection) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            knockBackCounter ++;
            if(knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        } else if (attacking) {

            attacking();
        } else{
            setAction();
            checkCollision();
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            spriteCounter++;
            if (spriteCounter > 24) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > invincibleTime){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 40) {
            shotAvailableCounter++;
        }

    }
    public void getRandomDirection(){
        actionLockCounter++;
        if (actionLockCounter == changeDirect) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // 1 -100
            if (i <= 25) {direction = "up";}
            if (i > 25 && i <= 50) {direction = "down";}
            if (i > 50 && i <= 75) {direction = "left";}
            if (i > 75) {direction = "right";}
            actionLockCounter = 0;
        }
    }
    public void checkStopChasingOrNot(Entity target, int distance, int rate) {
        if(getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if(i>50) {
                onPath = false;
            }
        }
    }
    public void checkStartChasingOrNot(Entity target, int distance, int rate) {
        if(getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if(i>50) {
                onPath = true;
            }
        }
    }
    public void checkAttackOrNot(int rate, int straight, int holizontal) {
        boolean targetInRange = false;
        int xDis = getXDistance(gp.player);
        int yDis = getYDistance(gp.player);
        switch (direction) {
            case "up" -> {
                if (gp.player.worldY < worldY && yDis < straight && xDis < holizontal) {
                    targetInRange = true;
                }
            }
            case "down" -> {
                if (gp.player.worldY > worldY && yDis < straight && xDis < holizontal) {
                    targetInRange = true;
                }
            }
            case "left" -> {
                if (gp.player.worldX < worldX && xDis < straight && yDis < holizontal) {
                    targetInRange = true;
                }
            }
            case "right" -> {
                if (gp.player.worldX > worldX && xDis < straight && yDis < holizontal) {
                    targetInRange = true;
                }
            }
        }

        if(targetInRange) {
            int i = new Random().nextInt(rate);
            if(i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }
    public void attacking(){

        spriteCounter++;
        if(spriteCounter <= motion1_duration){
            spriteNum = 1;
        }
        if(spriteCounter >motion1_duration && spriteCounter<= motion2_duration ){
            spriteNum = 2;

            //Save current wx wy
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust player attack area
            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if(type == type_monster) {
                if(gp.cChecker.checkPlayer(this)) {
                    damagePlayer(attack);
                }
            }else{
                //Check monster Collision
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, knockBackPower);
            }
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if(spriteCounter > motion2_duration ){
            spriteNum = 1;
            spriteCounter=0;
            attacking = false;
        }

    }
    public void damagePlayer(int attack) {
        if(!gp.player.invincible) {
            if(!dying){
                gp.playSE(gp.player.getDamageSound);
                gp.player.life -= attack;
            }
            gp.player.invincible = true;
        }
    }
    public void setKnockBack(Entity target, Entity attacker, int knockBackPower){
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }
    public  void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
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
            //HoverItem

            //Monster health Bar
            if(type==type_monster && hpBarOn) {
                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;
                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1, screenY - 16, gp.tileSize+2, 7);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 5);

                hpBarCounter++;
                if(hpBarCounter > 300){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }
            if(invincible){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4F);
            }
            if(dying){
                dyingAnimate(g2);
            }
            if(effect){
                effect(g2);
            }
            g2.drawImage(image, tempScreenX, tempScreenY, null);
            changeAlpha(g2, 1F);
        }
    }
    public void dyingAnimate(Graphics2D g2){
        dyingCounter++;
        int i = 5;
        if(dyingCounter <= i ) { changeAlpha(g2, 0f);}
        if(dyingCounter > i && dyingCounter<= i*2) {changeAlpha(g2, 1f);}
        if(dyingCounter > i*2 && dyingCounter<= i*3) {changeAlpha(g2, 0f);}
        if(dyingCounter > i*3 && dyingCounter<= i*4) {changeAlpha(g2, 1f);}
        if(dyingCounter > i*4 && dyingCounter<= i*5) {changeAlpha(g2, 0f);}
        if(dyingCounter > i*5 && dyingCounter<= i*6) {changeAlpha(g2, 1f);}
        if(dyingCounter > i*6 && dyingCounter<= i*7) {changeAlpha(g2, 0f);}
        if(dyingCounter > i*7 && dyingCounter<= i*8) {changeAlpha(g2, 1f);}
        if(dyingCounter > i*8) {
            alive = false;
        }
    }
    public void effect(Graphics2D g2){
        dyingCounter++;
        if(dyingCounter <= 30 ) { changeAlpha(g2, 0.6f);down1 = setup("/res/objects/greenDest/d2.png");}
        if(dyingCounter > 30 && dyingCounter<= 60) {changeAlpha(g2, 1F);down1 = setup("/res/objects/greenDest/d1.png");}
        if(dyingCounter > 60) {
            dyingCounter=0;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            image = uTool.scaleImage(image, width, height);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
    public void searchPath(int goalCol, int goalRow){
        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);

        if(gp.pFinder.search()) {
            //Next Wx Wy
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                if(enLeftX > nextX) {
                    direction = "left";
                }
                if(enLeftX < nextX) {
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX) {
                direction = "up";
                checkCollision();
                if(collisionOn){
                    direction = "left";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX) {
                direction = "up";
                checkCollision();
                if(collisionOn){
                    direction = "right";
                }
            }
            else if (enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                checkCollision();
                if(collisionOn){
                    direction = "left";
                }
            }
            else if (enTopY < nextY && enLeftX < nextX) {
                direction = "down";
                checkCollision();
                if(collisionOn){
                    direction = "right";
                }
            }

            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if(nextCol == goalCol && nextRow == goalRow) {
                onPath = false;
            }

        }

    }
    public int getDetected(Entity user, Entity[][] target, String targetName) {
        int index = 999;
        int nextWorldX = user.getLeftX()/gp.tileSize;
        int nextWorldY = user.getTopY()/gp.tileSize;
        switch (user.direction) {
            case "up" -> nextWorldY = user.getTopY()/gp.tileSize - 1;
            case "down" -> nextWorldY = user.getBottomY()/gp.tileSize + 1;
            case "left" -> nextWorldX = user.getLeftX()/gp.tileSize - 1;
            case "right" -> nextWorldX = user.getRightX()/gp.tileSize + 1;
        }
        int col = nextWorldX;
        int row = nextWorldY;
        for (int i = 0; i < target[1].length; i++) {
            if(target[gp.currentMap][i] != null) {
                if(target[gp.currentMap][i].getCol() == col &&
                        target[gp.currentMap][i].getRow() == row &&
                        target[gp.currentMap][i].name.equals(targetName)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}