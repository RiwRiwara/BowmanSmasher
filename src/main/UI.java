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
    public int slotCol = 0;
    public int slotRow = 0;
    public int subState = 0;

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
            g2.drawString(String.format("(%d, %d)",gp.player.worldX/48, gp.player.worldY/48+1), gp.tileSize, gp.tileSize*2);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12));
            g2.drawString(String.format("KEY: %d",gp.player.hasKey), gp.screenWidth-gp.tileSize*2,  gp.tileSize);
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
            drawInventory();
        }

        //Option State
        if(gp.gameState == gp.optionState){
            drawOptionScreen();
        }

        //Game over state
        if(gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
    }

    public void drawCharacterScreen() {
        //Create A FRAME
        final int frameX = gp.tileSize*2 - 20;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        //Text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(12F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 32;
        // Name
        g2.drawString("Heart", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Speed", textX, textY);
        textY += lineHeight;

        //Values
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gp.tileSize;
        String value;

        value = String.format("%d/%d", gp.player.life, gp.player.maxLife);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.format("%d", gp.player.attack);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.format("%d", gp.player.speed);
        textX = getXAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, textX/2,textY, gp.tileSize*2, gp.tileSize*2, null);

    }
    public void drawInventory(){
        int frameX = gp.tileSize * 13;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+4;

        //DRAW Player's items
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            //Equip Cursor
            if (gp.player.inventory.get(i) == gp.player.currentWeapon) {
                g2.setColor(new Color(255, 217, 142, 255));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;
            if(i ==4 || i==9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //Cursor
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        //Draw Cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // DESC
        int dFrameX = frameX;
        int dFrameY = frameY +frameHeight + 10;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize * 3;
        //Draw desc
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize-10;
        g2.setFont(g2.getFont().deriveFont(12F));
        int itemIndex = getItemIndexOnSlot();
        if (itemIndex < gp.player.inventory.size()) {
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            for (String line : gp.player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line, textX, textY);
                textY += 15;
            }

        }


    }
    public int getItemIndexOnSlot() {
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
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
        Color c = new Color(0,0,0, 150);
        g2.setColor(c);
        g2.fillRoundRect(x, y,width, height, 35, 35);

        c = new Color(255, 255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5, width-10, height-10,25,25);
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return  x;
    }
    public int getXAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length/2;
        return  x;
    }


    public void drawTitleScreen(){
        if(titleScreenState == 0 ) {
            //MAIN TITlE
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 45));
            String text = "BOWMAN SMASHER";
            int x = gp.tileSize*4;
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
            x = gp.tileSize*8;
            y = gp.tileSize*10;
            g2.drawString(text, x, y);
            if (numCommand == 0) {
                g2.drawString(">", x - 20, y);
            }


            text = "Setting";
            x = gp.tileSize*8;
            y = gp.tileSize*11;
            g2.drawString(text, x, y);
            if (numCommand == 1) {
                g2.drawString(">", x - 20, y);
            }

            text = "Exit";
            x = gp.tileSize*8;
            y = gp.tileSize*12;
            g2.drawString(text, x, y);
            if (numCommand == 2) {
                g2.drawString(">", x - 20, y);
            }

            //Image center
            x = gp.tileSize*8;
            y = gp.tileSize*5;

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
                case 1 -> g2.drawImage(gp.player.down1, x, y, gp.tileSize * 3, gp.tileSize * 3, null);
                case 2 -> g2.drawImage(gp.player.right1, x, y, gp.tileSize * 3, gp.tileSize * 3, null);
                case 3 -> g2.drawImage(gp.player.up1, x, y, gp.tileSize * 3, gp.tileSize * 3, null);
                case 4 -> g2.drawImage(gp.player.left1, x, y, gp.tileSize * 3, gp.tileSize * 3, null);
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
        int x = gp.tileSize/2, y = gp.tileSize/2, i = 0;
        //Draw Blank Heart
        while (i  < gp.player.maxLife/2){
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x+= 45;
        }
        //RESET
        x = gp.tileSize/2; y = gp.tileSize/2; i = 0;
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
    public void drawOptionScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(28F));

        //Sub window
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        switch (subState) {
            case 0: option_top(frameX, frameY);break;
            case 1:options_control(frameX, frameY) ;break;
            case 2:options_endGameConfirm(frameX, frameY) ;break;
        }
        gp.keyH.enterPressed = false;
    }
    public void option_top(int frameX, int frameY) {
        int textX;
        int textY;

        //Title
        String text = "Option";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        g2.setFont(g2.getFont().deriveFont(20F));
        textX = frameX + gp.tileSize;
        //Music
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(numCommand == 0){
            g2.drawString(">", textX-25, textY);
        }

        //SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if(numCommand == 1){
            g2.drawString(">", textX-25, textY);
        }

        //Control
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if(numCommand == 2){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                subState = 1;
                numCommand = 0;
            }
        }

        //End Game
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if(numCommand == 3){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed){
                gp.playSE(3);
                subState = 2;
                gp.ui.numCommand = 0;
            }
        }

        //Back
        textY += gp.tileSize*4;
        g2.drawString("Back", textX, textY);
        if(numCommand == 4){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                gp.playSE(3);
                gp.gameState = gp.playState;
            }
        }

        //Music Volume
        textX = frameX + (int)(gp.tileSize*4.5);
        textY = frameY + gp.tileSize + 28;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 120, 24); //120/5 = 24
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        //SE Volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

    }
    public void options_control( int frameX, int frameY) {
        int textX;
        int textY;

        //Title
        g2.setFont(g2.getFont().deriveFont(28F));
        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        g2.setFont(g2.getFont().deriveFont(16F));
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY+=gp.tileSize;
        g2.drawString("Select/Attack", textX, textY); textY+=gp.tileSize;
        g2.drawString("Shoot/Cast", textX, textY); textY+=gp.tileSize;
        g2.drawString("Inventory", textX, textY); textY+=gp.tileSize;
        g2.drawString("Pause", textX, textY); textY+=gp.tileSize;
        g2.drawString("Option", textX, textY); textY+=gp.tileSize;

        //Back
        g2.setFont(g2.getFont().deriveFont(20F));
        textY += gp.tileSize*2 -25;
        g2.drawString("Back", textX, textY);
        if(numCommand == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                gp.playSE(3);
                subState = 0;
                numCommand = 2;
            }
        }

        g2.setFont(g2.getFont().deriveFont(16F));
        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY); textY+=gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY+=gp.tileSize;
        g2.drawString("Q", textX, textY); textY+=gp.tileSize;
        g2.drawString("C", textX, textY); textY+=gp.tileSize;
        g2.drawString("P", textX, textY); textY+=gp.tileSize;
        g2.drawString("ESC", textX, textY); textY+=gp.tileSize;

    }
    public void options_endGameConfirm(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "Sure?";
        g2.drawString(currentDialogue, textX, textY);
        //YES
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (numCommand == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
                gp.stopMusic();
                gp.gameState = gp.titleState;
            }
        }
        //NO
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (numCommand == 1) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
                numCommand = 3;
            }
        }

    }

    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.drawRect(0,0,gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        text = "Game Over";
        //Shadow
        g2.setColor(Color.BLUE);
        x = getXforCenteredText(text);
        y = gp.tileSize*3;
        g2.drawString(text, x, y);
        //Main
        g2.setColor(Color.WHITE);
        g2.drawString(text, x-4, y-4);

        //retry
        g2.setFont(g2.getFont().deriveFont(40F));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*9;
        g2.drawString(text, x, y);
        if (numCommand == 0) {
            g2.drawString(">", x-40, y);
        }

        //Back
        text = "Quit";
        x = getXforCenteredText(text);
        y += 50;
        g2.drawString(text, x, y);
        if (numCommand == 1) {
            g2.drawString(">", x-40, y);
        }

    }


}
