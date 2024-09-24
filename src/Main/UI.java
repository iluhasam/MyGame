package Main;

import entity.Entity;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    BufferedImage life_full, life_1, life_2, life_blank;
    Font maruMonica;
    public boolean messageOn = false;
//    public String message = "";
//    int messageCounter = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameOver = false;
    public String currentDialogue = "";
    public int commandNum = 0;


    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //HUD OBJECT(LIFE)
        Entity heart = new OBJ_Heart(gp);
        life_full = heart.image;
        life_1 = heart.image2;
        life_2 = heart.image3;
        life_blank = heart.image4;
    }
    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        //TITLESTATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //PLAYSTATE
        if(gp.gameState == gp.playState){
            // do playstate ... later
            drawPlayerLife();
            drawMessage();
        }

        //PAUSESTATE
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }

        //DIALOGUESTATE
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogScreen();
        }
        //CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
        }
    }
    public void drawPlayerLife(){

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        //DRAW MAX HEART
        while(i < gp.player.maxLife/3){
            g2.drawImage(life_blank, x, y, null);
            i++;
            x +=gp.tileSize;
        }
        //RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //DRAW LIFE
        while(i < gp.player.life){
            g2.drawImage(life_2, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(life_1, x, y, null);
            }
            i++;
            if(i < gp.player.life){
                g2.drawImage(life_full, x, y, null);
                }
            i++;
            x += gp.tileSize;
        }
    }
    public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32f));

        for(int i = 0; i < message.size(); i++){
            if(message.get(i) != null){
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;//messageCounter++
                messageCounter.set(i, counter);//set the counter to the array
                messageY += 50;

                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    public void drawTitleScreen(){

        //TITLE COLOR
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight );
        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Boy's Journey: The Island of Change";
        int x = getXforCenteredText(text);//(gp.tileSize*5);
        int y = gp.tileSize *3;

        //SHADOW
        g2.setColor(Color.darkGray);
        g2.drawString(text, x+5, y+5);
        //MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //DRAW BOY IMAGE
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;//gp.screenWidth/2 - (gp.tileSize*2);
        y += gp.tileSize*2;
        g2.drawImage(gp.player.right1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = getXforCenteredText(text);// gp.screenWidth/2 - (gp.tileSize*3);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString("> ", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);//gp.screenWidth/2 - (gp.tileSize*3);
        y += gp.tileSize ;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString("> ", x - gp.tileSize, y);
        }

        text = "EXIT GAME";
        x = getXforCenteredText(text);//gp.screenWidth/2 - (gp.tileSize*3);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString("> ", x - gp.tileSize, y);
        }

    }

    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont( Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }
    public void drawDialogScreen(){

        //window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*6);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont( Font.PLAIN, 48F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }

    }
    public void drawCharacterScreen(){
        //CREATE A FRAME
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*7;
        final int frameHeight = gp.tileSize*10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(42F));

        int textX = frameX + 10;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 48;

        //NAMES
        g2.drawString("Уровень", textX, textY);
        textY += lineHeight;
        g2.drawString("Здоровье", textX, textY);
        textY += lineHeight;
        g2.drawString("Сила", textX, textY);
        textY += lineHeight;
        g2.drawString("Ловкость", textX, textY);
        textY += lineHeight;
        g2.drawString("Атака", textX, textY);
        textY += lineHeight;
        g2.drawString("Защита", textX, textY);
        textY += lineHeight;
        g2.drawString("Опыт", textX, textY);
        textY += lineHeight;
        g2.drawString("До следующего уровня", textX, textY);
        textY += lineHeight;
        g2.drawString("Монет", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Оружие", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Щит", textX, textY);
        textY += lineHeight;

        //VALUES
        int tailX = (frameX + frameWidth) - 15;
        //RESET textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/"+ gp.player.maxLife);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.agility);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 25, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 25, null);
        textY += gp.tileSize;

    }
    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0, 0, 0, 195);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35,35 );

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25,25 );

    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXforAlignToRight(String text, int tailX) {
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
