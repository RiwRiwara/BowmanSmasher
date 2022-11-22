package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    GamePanel gp;
    Font arial_40, arial_14, arial_60;

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
        g2.setFont(arial_60);
        String text = "BOWMAN SMASHER";
        int x = 100;
        int y = 150;
        g2.drawString(text, x, y);

        //menu
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        text = "Play(enter)";
        x = 280;
        y = 300;
        g2.drawString(text, x, y);

        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        text = "Exit(esc)";
        x = 280;
        y = 330;
        g2.drawString(text, x, y);
    }


}
