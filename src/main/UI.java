package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    GamePanel gp;
    Font arial_40, arial_14, arial_60;
    int ImageCount = 1, ChangeImgCount = 0;
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_14 = new Font("Arial", Font.PLAIN, 14);
        arial_60 = new Font("Arial", Font.PLAIN, 60);
    }



    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        // TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen(g2);
        }

        //PLAY STATE
        if(gp.gameState == gp.playState){
            g2.setFont(arial_14);
            g2.drawString("CAVE 01", 45, 30);
            g2.drawString(String.format("(%d, %d)",gp.player.worldX/48, gp.player.worldY/48), 50, 50);
        }
        //Pause STATE
        if(gp.gameState == gp.pauseState){
            drawPause(g2);
        }
    }

    public void drawPause(Graphics2D g2){
        g2.setFont(arial_60);
        g2.drawString("Pause", gp.screenWidth/2-50, gp.screenHeight/2);
    }
    public void drawTitleScreen(Graphics2D g2){
        //MAIN TITlE
        g2.setFont(new Font("Arial", Font.BOLD, 60));
        String text = "BOWMAN SMASHER";
        int x = 80;
        int y = 140;
        //SHADOW
        g2.setColor(Color.BLUE);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //menu
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        text = "Play(enter)";
        x = 300;
        y = 400;
        g2.drawString(text, x, y);

        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        text = "Exit(esc)";
        x = 300;
        y = 460;
        g2.drawString(text, x, y);

        //Image center
        x = 320;
        y = 200;

        ChangeImgCount++;
        if(ChangeImgCount > 100) {
            ImageCount++;
            if (ImageCount > 4) {
                ImageCount = 1;
            }
            ChangeImgCount = 0;
        }
            switch (ImageCount){
                case 1:
                    g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
                    break;
                case 2:
                    g2.drawImage(gp.player.right1, x, y, gp.tileSize*2, gp.tileSize*2, null);
                    break;
                case 3 :
                    g2.drawImage(gp.player.up1, x, y, gp.tileSize*2, gp.tileSize*2, null);
                    break;
                case 4:
                    g2.drawImage(gp.player.left1, x, y, gp.tileSize*2, gp.tileSize*2, null);
                    break;
            }



    }


}
