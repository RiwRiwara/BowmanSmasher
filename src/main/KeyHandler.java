package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, ePressed, shotKeyPressed;
    GamePanel gp;


    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //Title State
        if(gp.gameState == gp.titleState) {
            titleState(code);
        }

        //Pause State
       else if(gp.gameState == gp.pauseState) {
            pauseState(code);
        }

        //Play State
       else if (gp.gameState == gp.playState) {
            playState(code);
        }
        //Dialogue State
       else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
       //Character state
        else if(gp.gameState == gp.characterState){
            characterState(code);
        }
        //Option State
        else if (gp.gameState == gp.optionState) {
            optionState(code);
        }

        //Game Over State
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
        else if (gp.gameState == gp.choiceState) {
            choiceState(code);
        }


    }

    public void titleState(int code){
        if(gp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_S) {
                gp.playSE(3);
                gp.ui.numCommand++;
                if (gp.ui.numCommand > 2) {
                    gp.ui.numCommand = 0;
                }
            }
            if (code == KeyEvent.VK_W) {
                gp.playSE(3);
                gp.ui.numCommand--;
                if (gp.ui.numCommand < 0) {
                    gp.ui.numCommand = 2;
                }
            }
            //Take action in title
            if (code == KeyEvent.VK_ENTER) {
                switch (gp.ui.numCommand) {
                    //Play onCLick
                    case 0:
                        gp.playSE(1);
                        gp.restart();
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                        break;
                    case 1:
                        gp.playSE(1);
                        gp.ui.titleScreenState = 1;
                        break;
                    case 2:
                        System.exit(0);
                        break;
                }
            }
        } else if (gp.ui.titleScreenState == 1) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.playSE(3);
                gp.ui.titleScreenState = 0;
            }
            if (code == KeyEvent.VK_ENTER) {
                gp.playSE(3);
                if(gp.ui.musicOn) {
                    gp.ui.musicOn = false;
                }else{
                    gp.ui.musicOn = true;
                }
            }
        }
    }
    public void optionState(int code){
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
            gp.ui.subState = 0;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        switch (gp.ui.subState){
            case 0:
                if (code == KeyEvent.VK_S) {
                    gp.playSE(3);
                    gp.ui.numCommand++;
                    if (gp.ui.numCommand > 4) {
                        gp.ui.numCommand = 0;
                    }
                }
                if (code == KeyEvent.VK_W) {
                    gp.playSE(3);
                    gp.ui.numCommand--;
                    if (gp.ui.numCommand < 0) {
                        gp.ui.numCommand = 4;
                    }
                }
                break;
            case 2:
                if (code == KeyEvent.VK_S) {
                    gp.playSE(3);
                    gp.ui.numCommand++;
                    if (gp.ui.numCommand > 1) {
                        gp.ui.numCommand = 0;
                    }
                }
                if (code == KeyEvent.VK_W) {
                    gp.playSE(3);
                    gp.ui.numCommand--;
                    if (gp.ui.numCommand < 0) {
                        gp.ui.numCommand = 1;
                    }
                }
                break;


        }

        //Increase Volume
        if(code == KeyEvent.VK_A) {
            if(gp.ui.subState == 0 ) {
                if (gp.ui.numCommand == 0 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(4);
                }
                if (gp.ui.numCommand == 1 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.se.checkVolume();
                    gp.playSE(4);
                }
            }
        }
        if(code == KeyEvent.VK_D) {
            if(gp.ui.subState == 0 ) {
                if (gp.ui.numCommand == 0 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(4);
                }
                if (gp.ui.numCommand == 1 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                    gp.se.checkVolume();
                    gp.playSE(4);
                }
            }
        }
    }
    public void playState(int code){
        if(code == KeyEvent.VK_E){
            ePressed = true;
        }
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_Q){
            shotKeyPressed = true;
        }



        //Increase Speed
        if(code == KeyEvent.VK_UP){
            gp.player.speed++;
        }
        if(code == KeyEvent.VK_DOWN){
            gp.player.speed--;
        }

        if(code == KeyEvent.VK_C){
            gp.gameState = gp.characterState;
        }

        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionState;
        }

        //Console
        if(code == KeyEvent.VK_U){
            new console(gp).setVisible(true);
        }

        if (code == KeyEvent.VK_R) {
                gp.currentMap = 1;
        }
        if (code == KeyEvent.VK_T) {
            gp.currentMap = 0;
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
    }
    public void characterState(int code){
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_W) {
            if(gp.ui.slotRow != 0) {
                gp.playSE(4);
                gp.ui.slotRow--;
            }
        }
        if(code == KeyEvent.VK_A) {
            if(gp.ui.slotCol != 0) {
                gp.playSE(4);
                gp.ui.slotCol--;
            }
        }
        if(code == KeyEvent.VK_S) {
            if(gp.ui.slotRow != 3) {
                gp.playSE(4);
                gp.ui.slotRow++;
            }
        }
        if(code == KeyEvent.VK_D) {
            if(gp.ui.slotCol != 4) {
                gp.playSE(4);
                gp.ui.slotCol++;
            }
        }
        if(code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
    }
    public void gameOverState(int code) {
        if (code == KeyEvent.VK_W){
            gp.ui.numCommand--;
            if(gp.ui.numCommand < 0) {
                gp.ui.numCommand = 1;
            }
            gp.playSE(4);
        }
        if (code == KeyEvent.VK_S){
            gp.ui.numCommand++;
            if(gp.ui.numCommand > 1) {
                gp.ui.numCommand = 0;
            }
            gp.playSE(4);
        }
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.numCommand == 0) {
                gp.restart();
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }else{
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }
    public void choiceState(int code){
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_W){
            gp.ui.numCommand--;
            if(gp.ui.numCommand < 0) {
                gp.ui.numCommand = 1;
            }
            gp.playSE(4);
        }
        if (code == KeyEvent.VK_S){
            gp.ui.numCommand++;
            if(gp.ui.numCommand > 1) {
                gp.ui.numCommand = 0;
            }
            gp.playSE(4);
        }
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.numCommand == 0) {
                switch (gp.eHandler.from){
                    case "teleport":
                        gp.eHandler.mapWarp(gp.eHandler.curMapTo, gp.eHandler.curXTo, gp.eHandler.curYTo);
                        break;
                }
                gp.gameState = gp.playState;
            }else{
                switch (gp.player.direction) {
                    case "up" -> {gp.player.worldY += 40;gp.player.direction="down";}
                    case "down" -> {gp.player.worldY -= 40;gp.player.direction="up";}
                    case "left" -> {gp.player.worldX += 40;gp.player.direction="right";}
                    case "right" -> {gp.player.worldX -= 40;gp.player.direction="left";}
                }
                gp.gameState = gp.playState;

            }
        }
    }
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_Q) {
            shotKeyPressed = false;
        }
        if (code == KeyEvent.VK_E) {
            ePressed = false;
        }
    }
}