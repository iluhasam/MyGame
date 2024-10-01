package Main;

import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    BufferedImage life_full, life_1, life_2, life_blank, crystal_full, crystal_blank, coin;
    Font maruMonica;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameOver = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    public int subState = 0;
    public Entity npc;




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
        Entity crystal = new OBJ_ManaCrystal(gp);
        crystal_full = crystal.image2;
        crystal_blank = crystal.image;
        Entity bronzeCoin = new OBJ_Coin_Bronze(gp);
        coin = bronzeCoin.down1;
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
            drawDialogScreen();
        }
        //CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory(gp.player, true);
        }
        //OPTIONS STATE
        if(gp.gameState == gp.optionsState){
            drawOptionsScreen();
        }
        //GAME OVER STATE
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
        //TRADE STATE
        if(gp.gameState == gp.tradeState){
            drawTradeScreen();
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

        //DRAW MAX MANA
        x = (gp.tileSize/2) - 5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while(i < gp.player.maxMana){
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x+=35;
        }
        //DRAW MANA
        x = (gp.tileSize/2) - 5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while(i < gp.player.mana){
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x +=35;
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
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 72F));
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

        text = "НОВАЯ ИГРА";
        x = getXforCenteredText(text);// gp.screenWidth/2 - (gp.tileSize*3);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString("> ", x - gp.tileSize, y);
        }

        text = "ЗАГРУЗИТЬ ИГРУ";
        x = getXforCenteredText(text);//gp.screenWidth/2 - (gp.tileSize*3);
        y += gp.tileSize ;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString("> ", x - gp.tileSize, y);
        }

        text = "ВЫЙТИ ИЗ ИГРЫ";
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
        int x = gp.tileSize*3;
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
        final int frameX = gp.tileSize*2;
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
        g2.drawString("Мана", textX, textY);
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
        textY += lineHeight + 10;
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

        value = String.valueOf(gp.player.mana + "/"+ gp.player.maxMana);
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

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 30, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 35, null);
        textY += gp.tileSize;

    }

    public void drawInventory(Entity entity, boolean cursor){

        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if(entity == gp.player){
            frameX = gp.tileSize*12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize*5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        else {
            frameX = gp.tileSize*2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize*5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }
        //FRAME
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize;

        //DRAW PLAYER ITEMS
        for(int i = 0; i < entity.inventory.size(); i++){

            //EQUIP CURSOR
            if(entity.inventory.get(i) == entity.currentWeapon ||
                    entity.inventory.get(i) == entity.currentShield ||
                        entity.inventory.get(i) == entity.currentLight){
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX+1, slotY+1, gp.tileSize-2, gp.tileSize-2, 10 , 10);
            }
            g2.drawImage(entity.inventory.get(i).down1, slotX,slotY, null);

            //DISPLAY AMOUNT
            if(entity == gp.player && entity.inventory.get(i).amount > 1){

                g2.setFont(g2.getFont().deriveFont(32f));
                int amountX;
                int amountY;

                String s = "" + entity.inventory.get(i).amount;
                amountX = getXforAlignToRight(s, slotX + 44);
                amountY = slotY + gp.tileSize;

                //SHADOW
                g2.setColor(new Color(60, 60, 60 ));
                g2.drawString(s, amountX, amountY);
                //NUMBER
                g2.setColor(Color.WHITE);
                g2.drawString(s, amountX - 3, amountY - 3);
            }

            slotX += gp.tileSize;

            if( i == 4 || i == 9|| i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }

        }

        //CURSOR
        if(cursor == true){
            int cursorX = slotXstart + (slotSize * slotCol);
            int cursorY = slotYstart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            //DRAW CURSOR
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            //DESCRIPTION FRAME
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize * 3;


            //DRAW DESCRIPTION TEXT
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(28F));

            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

            if(itemIndex < entity.inventory.size()){

                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

                for(String line: entity.inventory.get(itemIndex).description.split("\n")){

                    g2.drawString(line, textX, textY);
                    textY += 32;
                }

            }
        }


    }

    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "YOU DIED";
        //SHADOW
        g2.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        //MAIN
        g2.setColor(Color.WHITE);
        g2.drawString(text, x-4, y-4);

        //RETRY
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "RETRY";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;

        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-40, y);
        }

        //BACK TO MENU
        text = "Выход";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-40, y);
        }
    }

    public void drawOptionsScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //SUB WINDOW
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState){
            case 0:options_top(frameX, frameY);break;
            case 1:options_fullScreenNotification(frameX,frameY);break;
            case 2:options_control(frameX,frameY);break;
            case 3:options_endGameConfirmation(frameX,frameY);break;
        }

        gp.keyH.enterPressed = false;
    }

    public void options_top(int frameX, int frameY){
        int textX;
        int textY;

        //TITLE
        String text = "Настройки";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Полноэкранный режим", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                if(gp.fullScreenOn == false){
                    gp.fullScreenOn = true;
                }
                else if(gp.fullScreenOn == true){
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }

        //MUSIC
        textY += gp.tileSize;
        g2.drawString("Музыка", textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX-25, textY);
        }

        //SE
        textY += gp.tileSize;
        g2.drawString("Звуковые эффекты", textX, textY);
        if(commandNum == 2){
            g2.drawString(">", textX-25, textY);
        }

        //CONTROL
        textY += gp.tileSize;
        g2.drawString("Управление", textX, textY);
        if(commandNum == 3){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 2;
                commandNum = 0;
            }
        }

        //END GAME
        textY += gp.tileSize;
        g2.drawString("Выйти из игры", textX, textY);
        if(commandNum == 4){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 3;
                commandNum = 0;
            }
        }

        //BACK
        textY += gp.tileSize*2;
        g2.drawString("Вернуться", textX, textY);
        if(commandNum == 5){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }
        //FULL SCREEN CHECK BOX
        textX = frameX + (int)(gp.tileSize*5.5);
        textY = frameY + gp.tileSize*2 + 42;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn == true){
            g2.fillRect(textX, textY, 24, 24);
        }

        //MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        //SE VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);
    }

    public void options_fullScreenNotification(int frameX, int frameY){

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "Изменения вступят в силу \nпосле перезапуска игры ";
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //BACK
        textY = frameY + gp.tileSize*9;
        g2.drawString("Вернуться", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
            }
        }
    }

    public void options_control(int frameX, int frameY){

        int textX;
        int textY;

        //TITLE
        String text = "Управление";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Движение", textX-20, textY); textY += gp.tileSize;
        g2.drawString("Выбрать/Атака", textX-20, textY); textY += gp.tileSize;
        g2.drawString("Стрельба/Атака(М)", textX-20, textY); textY += gp.tileSize;
        g2.drawString("Экран персонажа", textX-20, textY); textY += gp.tileSize;
        g2.drawString("Пауза", textX-20, textY); textY += gp.tileSize;
        g2.drawString("Настройки", textX-20, textY); textY += gp.tileSize;


        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("W/S/A/D", textX-10, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX-10, textY); textY += gp.tileSize;
        g2.drawString("F", textX-10, textY); textY += gp.tileSize;
        g2.drawString("C", textX-10, textY); textY += gp.tileSize;
        g2.drawString("P", textX-10, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX-10, textY); textY += gp.tileSize;

        //BACK
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Вернуться", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 3;
            }
        }
    }

    public void options_endGameConfirmation(int frameX, int frameY){

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "Выйти из игры\nи вернуться в начальное меню?";

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //YES
        String text = "Да";
        textX = getXforCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                gp.gameState = gp.titleState;
            }
        }
        //NO
        text = "Нет";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 4;
            }
        }

    }

    public void drawTradeScreen(){
        switch (subState){
            case 0:trade_select();break;
            case 1:trade_buy();break;
            case 2:trade_sell();break;
        }
        gp.keyH.enterPressed = false;
    }

    public void trade_select(){
        drawDialogScreen();

        //DRAW WINDOW
        int x = gp.tileSize*15;
        int y = gp.tileSize*4;
        int width = (int)(gp.tileSize * 3.5);
        int height = (int)(gp.tileSize * 3.5);
        drawSubWindow(x,y,width,height);

        //DRAW TEXT
        x += gp.tileSize-20;
        y += gp.tileSize;
        g2.drawString("Купить",x,y);
        if(commandNum == 0){
            g2.drawString(">", x-20,y);
            if(gp.keyH.enterPressed == true){
                subState = 1;
            }
        }
        y += gp.tileSize;

        g2.drawString("Продать",x,y);
        if(commandNum == 1){
            g2.drawString(">", x-20,y);
            if(gp.keyH.enterPressed == true){
                subState = 2;
            }
        }
        y += gp.tileSize;

        g2.drawString("Выйти",x,y);
        if(commandNum == 2){
            g2.drawString(">", x-20,y);
            if(gp.keyH.enterPressed == true){
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "Приходи ещё, хе-хе-хе!!!";
            }
        }
        y += gp.tileSize;


    }

    public void trade_buy(){


        //DRAW PLAYER INVENTORY
        drawInventory(gp.player, false);
        //DRAW NPC INVENTORY
        drawInventory(npc, true);

        //DRAW HINT WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Вернуться", x+24, y+60);

        //DRAW PLAYER COIN
        x = gp.tileSize*12;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Твои монеты: " + gp.player.coin  , x+24, y+70);

        //DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol,npcSlotRow);
        if(itemIndex < npc.inventory.size()){

            x = (int)(gp.tileSize*5.5);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+8 ,48,48,null);

            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlignToRight(text, gp.tileSize*8 );
            g2.drawString(text, x -30, y+45);

            //BUY
            if(gp.keyH.enterPressed == true){
                if(npc.inventory.get(itemIndex).price > gp.player.coin){
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "Надо больше";
                    drawDialogScreen();
                }
                else {
                    if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true){
                        gp.player.coin -=npc.inventory.get(itemIndex).price;
                    }
                    else {
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        currentDialogue = "В тебя больше не влезет";
                    }
                }
            }
        }
    }

    public void trade_sell(){

        //DRAW PLAYER INVENTORY
        drawInventory(gp.player, true);

        int x;
        int y;
        int width;
        int height;

//        drawInventory(npc, true);

        //DRAW HINT WINDOW
         x = gp.tileSize*2;
         y = gp.tileSize*9;
         width = gp.tileSize*6;
         height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Вернуться", x+24, y+60);

        //DRAW PLAYER COIN
        x = gp.tileSize*12;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Твои монеты: " + gp.player.coin  , x+24, y+70);

        //DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol,playerSlotRow);
        if(itemIndex < gp.player.inventory.size()){

            x = (int)(gp.tileSize*15.5);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+8 ,48,48,null);

            int price = gp.player.inventory.get(itemIndex).price/2;
            String text = "" + price;
            x = getXforAlignToRight(text, gp.tileSize*18 );
            g2.drawString(text, x -30, y+45);

            //SELL
            if(gp.keyH.enterPressed == true) {
                if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
                gp.player.inventory.get(itemIndex) == gp.player.currentArmor ||
                gp.player.inventory.get(itemIndex) == gp.player.currentShield ||
                gp.player.inventory.get(itemIndex) == gp.player.currentHelment){
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "Ты не можешь продать\nэкипированные предметы";
                }
                else {
                    if (gp.player.inventory.get(itemIndex).amount > 1) {
                        gp.player.inventory.get(itemIndex).amount--;
                    }
                    else{
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.coin += price;
                }
            }
        }
    }

    public int getItemIndexOnSlot(int slotCol, int slotRow){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
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
