package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHabdler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
        //debug
    boolean showDebugConsole = false;

    public KeyHabdler(GamePanel gp) {
    this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // TITLE STATE(MENU)
        if(gp.gameState == gp.titleState){
            titleState(code);
        }
        //PLAYSTATE
        else if (gp.gameState == gp.playState){
            playState(code);
        }
        //pauseState
        else if (gp.gameState == gp.pauseState){
            pauseState(code);
        }
        //dialogueState
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
        //CHARACTER STATE
        else if(gp.gameState == gp.characterState){
            characterState(code);
        }

    }
    public void titleState(int code){
        if (code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 2;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 2){
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            if(gp.ui.commandNum == 1){
                //LATER
            }
            if(gp.ui.commandNum == 2){
                System.exit(0);
            }
        }
    }

    public void playState(int code){
        if (code == KeyEvent.VK_W) {upPressed = true;}
        if (code == KeyEvent.VK_S) {downPressed = true;}
        if (code == KeyEvent.VK_A) {leftPressed = true;}
        if (code == KeyEvent.VK_D) {rightPressed = true;}
        if (code == KeyEvent.VK_ESCAPE) {gp.gameState = gp.pauseState;}
        if (code == KeyEvent.VK_ENTER) {enterPressed = true;}
        if (code == KeyEvent.VK_C){gp.gameState = gp.characterState;}

        //DEBUG
        if (code == KeyEvent.VK_T) {
            if(showDebugConsole == false) {
                showDebugConsole = true;
            } else if (showDebugConsole == true) {
                showDebugConsole = false;

            }
        }
    }
    public void pauseState(int code){
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }
    public void characterState(int code){
        if(code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
    }

    @Override
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
        if (code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
    }
}
