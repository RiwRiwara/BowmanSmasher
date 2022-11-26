package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //Title State
        if(gp.gameState == gp.titleState) {
            if(gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_S) {
                    gp.ui.numCommand++;
                    if (gp.ui.numCommand > 2) {
                        gp.ui.numCommand = 0;
                    }
                }
                if (code == KeyEvent.VK_W) {
                    gp.ui.numCommand--;
                    if (gp.ui.numCommand < 0) {
                        gp.ui.numCommand = 2;
                    }
                }
                //Take action in title
                if (code == KeyEvent.VK_ENTER) {
                    switch (gp.ui.numCommand) {
                        case 0:
                            gp.gameState = gp.playState;
                            gp.playMusic(0);
                            break;
                        case 1:
                            //Setting
                            break;
                        case 2:
                            System.exit(0);
                            break;
                    }
                }
            }

        }

        //Play State
        if(gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                this.upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                this.downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                this.leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                this.rightPressed = true;
            }
            //Increase Speed
            if(code == KeyEvent.VK_UP){
                gp.player.speed++;
            }
            if(code == KeyEvent.VK_DOWN){
                gp.player.speed--;
            }

        }

        if (code == KeyEvent.VK_P) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;

            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;

            }
        }



    }



    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            this.upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            this.downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            this.leftPressed = false;
        }
        if (code == KeyEvent.VK_D)
            this.rightPressed = false;
    }
}