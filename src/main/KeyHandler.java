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
                    case 0:
                        gp.playSE(1);
                        gp.gameState = gp.playState;
                        if(gp.ui.musicOn) {
                            gp.playMusic(0);
                        }
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


        //Return to Title
        if(code == KeyEvent.VK_ESCAPE){
            if(gp.ui.musicOn) {
                gp.music.stop();
            }
            gp.gameState = gp.titleState;
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
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