package main;

import entity.Entity;
import object.OBJ_HEART;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font pixelFont;
    public BufferedImage heart_full, heart_half, heart_blank;
    public int ImageCount = 1, ChangeImgCount = 0;
    public int numCommand = 0, messageCounter = 0;
    public int titleScreenState = 0; // 0 First Screen, 1 Setting
    public String message;
    public boolean messageOn = false, musicOn = true;
    public String currentDialogue = "";

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/res/fonts/Weiholmir_regular.ttf");
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
            //another Font
        }catch (FontFormatException | IOException e){
            e.printStackTrace();
        }

        //Create HUD
        Entity heart = new OBJ_HEART(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setColor(Color.white);
        g2.setFont(pixelFont);
        // TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        //PLAY STATE
        if(gp.gameState == gp.playState){
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12));
            g2.drawString("CAVE 01", 45, 30);
            g2.drawString(String.format("(%d, %d)",gp.player.worldX/48, gp.player.worldY/48+1), 50, 50);
            g2.drawString(String.format("Speed: %d",gp.player.speed), 50, 70);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15));
            g2.drawString(String.format("KEY: %d",gp.player.hasKey), 650, 30);
            drawPlayerLife();
        }
        //Pause STATE
        if(gp.gameState == gp.pauseState){
            drawPause();
        }
        // If getKey
        if(messageOn){
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
            messageCounter++;
            if(messageCounter>120){
                messageCounter = 0;
                messageOn = false;
            }
        }
        //Dialogue State
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        //Character State
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
        }
    }

    public void drawCharacterScreen() {
        //Create A FRAME
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        //Text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(15F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 32;
        // Name
        g2.drawString(String.format("Heart        %d/%d",gp.player.life, gp.player.maxLife), textX, textY);
        g2.drawImage(gp.player.currentWeapon.down1, textX+50,textY+20, gp.tileSize*2, gp.tileSize*2, null);


    }

    public void drawPause(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
        g2.drawString("Pause", gp.screenWidth/2-100, gp.screenHeight/2);
    }
    public void drawDialogueScreen(){

        // WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
        g2.drawString(currentDialogue, x, y);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        g2.drawString(">",x*4+gp.tileSize,y*3+gp.tileSize/2);
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0, 100);
        g2.setColor(c);
        g2.fillRoundRect(x, y,width, height, 35, 35);

        c = new Color(255, 255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5, width-10, height-10,25,25);
    }


    public void drawTitleScreen(){
        if(titleScreenState == 0 ) {
            //MAIN TITlE
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 45));
            String text = "BOWMAN SMASHER";
            int x = gp.tileSize*2;
            int y = gp.tileSize*3;
            //SHADOW
            g2.setColor(Color.BLUE);
            g2.drawString(text, x + 5, y + 5);
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //menu
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24));
            text = "Play";
            x = 320;
            y = 400;
            g2.drawString(text, x, y);
            if (numCommand == 0) {
                g2.drawString(">", x - 20, y);
            }


            text = "Setting";
            x = 320;
            y = 450;
            g2.drawString(text, x, y);
            if (numCommand == 1) {
                g2.drawString(">", x - 20, y);
            }

            text = "Exit";
            x = 320;
            y = 500;
            g2.drawString(text, x, y);
            if (numCommand == 2) {
                g2.drawString(">", x - 20, y);
            }

            //Image center
            x = 320;
            y = 200;

            //Image animate
            ChangeImgCount++;
            if (ChangeImgCount > 100) {
                ImageCount++;
                if (ImageCount > 4) {
                    ImageCount = 1;
                }
                ChangeImgCount = 0;
            }
            switch (ImageCount) {
                case 1 -> g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
                case 2 -> g2.drawImage(gp.player.right1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
                case 3 -> g2.drawImage(gp.player.up1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
                case 4 -> g2.drawImage(gp.player.left1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
            }
        } else if (titleScreenState == 1) {

            //CLASS SETTING
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30));
            String musicStatus = "ON";
            if(!musicOn) {
                musicStatus = "OFF";
            }
            g2.drawString(String.format("Music : %s", musicStatus), gp.screenWidth/2-100, gp.screenHeight/2-50);
            g2.drawString("<Enter>", gp.screenWidth/2-100, gp.screenHeight/2);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
            g2.drawString("BACK(ESC)", 50, gp.screenHeight-50);
        }
    }
    public void drawPlayerLife(){
        int x = 10, y =80, i = 0;
        //Draw Blank Heart
        while (i  < gp.player.maxLife/2){
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x+= 45;
        }
        //RESET
        x = 10; y =80; i = 0;
        //DRAW CURRENT LIFE
        while (i  < gp.player.life){
            g2.drawImage(heart_half,x,y,null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x+=45;
        }


    }


}
