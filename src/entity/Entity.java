package entity;
import main.GamePanel;
import main.UtilityTool;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    public GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX,  solidAreaDefaultY;
    public BufferedImage image, image2, image3;
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public String[] dialogues = new String[20];
    public int itemHover = 999;

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

    //Counter
    public int actionLockCounter = 0;
    public  int standCounter = 0;
    public int invincibleCounter = 0;
    public int spriteCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int shotAvailableCounter = 0;


    //Character Status
    public int maxLife;
    public int life;
    public int speed;
    public int attack;
    public int stamina;
    public String name;
    public int invincibleTime;

    //
    public Entity currentWeapon;
    public Entity getCurrentShield;
    public Projectile projectile;

    //item artibute
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useStamina;

    //TYPE
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_bow = 3;
    public final int type_spear = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;



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

    public void setAction(){}
    public void damageReaction(){}
    public void speak(){}
    public void use(Entity player){}
    //Teleport entity
    public void teleport(GamePanel gp){}

    public void update(){
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.npc);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if(this.type == 2 && contactPlayer){
            damagePlayer(attack);
        }

        if (!collisionOn) {
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
    public void damagePlayer(int attack) {
        if(!gp.player.invincible) {
            if(!dying){
                soundFX(gp.player.getDamageSound);
                gp.player.life -= attack;
            }
            gp.player.invincible = true;
        }
    }
    public  void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
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
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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
    public void soundFX(URL sound) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        clip.start();
    }

}