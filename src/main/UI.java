package main;

import object.OBJ_HEART;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Font pixelFont;
    Font arial_40, arial_14, arial_60;
    public BufferedImage heart_full, heart_half, heart_blank;
    public int ImageCount = 1, ChangeImgCount = 0;
    public int numCommand = 0;
    public int titleScreenState = 0; // 0 First Screen, 1 Second Screen

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/res/fonts/joystix monospace.ttf");
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
            //anoter Font
        }catch (FontFormatException | IOException e){
            e.printStackTrace();
        }

        //Create HUD
        SuperObject heart = new OBJ_HEART(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }



    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.setFont(pixelFont);
        // TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen(g2);
        }

        //PLAY STATE
        if(gp.gameState == gp.playState){
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12));
            g2.drawString("CAVE 01", 45, 30);
            g2.drawString(String.format("(%d, %d)",gp.player.worldX/48, gp.player.worldY/48+1), 50, 50);
            g2.drawString(String.format("Speed: %d",gp.player.speed), 50, 70);
            drawPlayerLife(g2);
        }
        //Pause STATE
        if(gp.gameState == gp.pauseState){
            drawPause(g2);
        }
    }

    public void drawPause(Graphics2D g2){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
        g2.drawString("Pause", gp.screenWidth/2-100, gp.screenHeight/2);
    }


    public void drawTitleScreen(Graphics2D g2){
        if(titleScreenState == 0 ) {
            //MAIN TITlE
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
            String text = "BOWMAN SMASHER";
            int x = 80;
            int y = 140;
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

            //CLASS SELECTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(new Font("Arial", Font.PLAIN, 28));
            //...
        }
    }
    public void drawPlayerLife(Graphics2D g2){
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
