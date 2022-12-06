package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Bowman;
import object.OBJ_Fireball;


public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int MaxInventorySize = 20;
    int attackDelay = 0;
    
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
        setItems();
    }
    public void setDefaultValues() {
        int startX = 5;
        int startY = 32;
        type = 0;
        worldX = gp.tileSize * startX;
        worldY = gp.tileSize * startY;
        this.speed = 3;
        this.direction = "down";

        //Player Status
        invincibleTime = 40;
        maxLife = 6;
        life = maxLife;
        currentWeapon = new OBJ_Bowman(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        stamina = 5;



        //Sound
        getDamageSound = getClass().getResource("/res/sound/BowDamage.wav");
        attackSound = getClass().getResource("/res/sound/smashBow.wav");
    }
    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
    }
    public void setDefaultPosition(){
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 35;
        direction = "down";
    }
    public void restoreLife(){
        life = maxLife;
        invincible = false;
    }

    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return  currentWeapon.attackValue;
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
        attackUp1 = setup("/res/player/boman_attack_up1.png", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/res/player/boman_attack_up2.png", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/res/player/boman_attack_down1.png", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/res/player/boman_attack_down2.png", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/res/player/boman_attack_left1.png", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/res/player/boman_attack_left2.png", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/res/player/boman_attack_right1.png", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/res/player/boman_attack_right2.png", gp.tileSize*2, gp.tileSize);
    }
    public void setEquipSpear() {
        down1 = setup("/res/player/spear/boman_down1.png");
        down2 = setup("/res/player/spear/boman_down2.png");
        left1 = setup("/res/player/spear/boman_left1.png");
        left2 = setup("/res/player/spear/boman_left2.png");
        right1 = setup("/res/player/spear/boman_right1.png");
        right2 = setup("/res/player/spear/boman_right2.png");
        up1 = setup("/res/player/spear/boman_up1.png");
        up2 = setup("/res/player/spear/boman_up2.png");
    }

    public void update() {
        if (attacking){
            attacking();
        } else if (keyH.upPressed || keyH.downPressed ||
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
                int objIndex =  gp.cChecker.checkItem(this, true);
                pickUpObj(objIndex);

                //Check Item collision
                int itemIndex =  gp.cChecker.checkObject(this, true);
                interactObj(itemIndex);


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
        if(gp.keyH.shotKeyPressed && !projectile.alive && shotAvailableCounter == 40){
            //SET DEFAULT
            projectile.set(worldX, worldY, direction, true, this);
            //ADD TO LIST
            gp.projecttileList.add(projectile);
            shotAvailableCounter = 0;
            gp.playSE(3);
        }

        if(shotAvailableCounter < 40){
            shotAvailableCounter++;
        }

        //need to be outside of key statement
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }

        //Attack Phase
        if(keyH.enterPressed && !attackCanceled && attackDelay == 100) {
            gp.playSE(attackSound);
            attacking = true;
            spriteCounter = 0;
        }
        if(attackDelay == 100) {
            attackCanceled = false;
            attackDelay = 0;
        }
        if(attackDelay < 100){
            attackDelay++;
        }
        gp.keyH.enterPressed = false;


        if(life > maxLife) {
            life = maxLife;
        }
        if(life <= 0) {
            gp.ui.numCommand = 0;
            gp.stopMusic();
            gp.gameState = gp.gameOverState;
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

            //Adjust player attack area
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
            damageMonster(monsterIndex, attack);
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

    public void pickUpObj(int i){
        if(i != 999){
            String text;
                if (inventory.size() != MaxInventorySize) {
                    switch (gp.item[gp.currentMap][i].name){
                        case "Key":
                            gp.playSE(1);
                            hasKey++;
                            break;
                        default:
                            gp.playSE(4);
                            break;
                    }

                    inventory.add(gp.item[gp.currentMap][i]);
                    text = ("Got a " + gp.item[gp.currentMap][i].name + "!");
                    gp.item[i] = null;
                } else {
                    text = "Inventory Full!";
                }
                gp.ui.showMessage(text);

        }
    }

    public void interactObj(int i){
        if(i != 999) {
            switch (gp.obj[gp.currentMap][i].name) {
                case "Door":
                    if (hasKey > 0) {
                        gp.playSE(2);
                        hasKey--;
                        gp.obj[i] = null;
                    }
                    break;
                case "Warp":
                    gp.obj[gp.currentMap][i].teleport(gp);
                    break;
                case "Box":
                    System.out.println("This is Boxx");
                    break;
            }
        }
    }
    public void interactNPC(int i){
        if(gp.keyH.enterPressed){
            if(i != 999){
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i] .speak();

                switch (direction) {
                    case "down" -> gp.npc[gp.currentMap][i] .direction = "up";
                    case "up" -> gp.npc[gp.currentMap][i] .direction = "down";
                    case "right" -> gp.npc[gp.currentMap][i] .direction = "left";
                    case "left" -> gp.npc[gp.currentMap][i] .direction = "right";
                }
            }else {
                gp.playSE(attackSound);
                attacking = true;
            }
        }
        gp.keyH.enterPressed = false;
    }
    public void damageMonster(int i, int attack){
        if(i!=999){
            if(!gp.monster[gp.currentMap][i].invincible){
                gp.monster[gp.currentMap][i].damageReaction();
                gp.monster[gp.currentMap][i].life -= attack;
                gp.monster[gp.currentMap][i].invincible = true;

                if(gp.monster[gp.currentMap][i].life <= 0){
                    gp.playSE(gp.monster[gp.currentMap][i].deadSound);
                    gp.monster[gp.currentMap][i].dying = true;
                }else{
                    gp.playSE(gp.monster[gp.currentMap][i].getDamageSound);
                }
            }
        }
    }
    public void contactMonster(int i){
        if(i!=999){
            if(!invincible && !gp.monster[gp.currentMap][i].dying) {
                if(!gp.monster[gp.currentMap][i].dying){
                    gp.playSE(getDamageSound);
                    life -= gp.monster[gp.currentMap][i].attack;
                    invincible = true;
                }
            }

        }
    }
    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == type_bow) {
                getPlayerImage();
                currentWeapon = selectedItem;
                attack = getAttack();
            }
            if(selectedItem.type == type_spear) {
                setEquipSpear();
                currentWeapon = selectedItem;
                attack = getAttack();
            }
            if(selectedItem.type == type_consumable) {
                switch (selectedItem.name) {
                    case "Red Potion" -> {
                        inventory.get(itemIndex).use(this);
                        inventory.remove(itemIndex);
                    }
                    case "Key" -> inventory.remove(itemIndex);
                }
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