package Main;

import entity.PlayerDummy;
import monster.MON_CrystalKnight;
import monster.MON_SkeletonLord;
import object.OBJ_BlueHeart;
import object.OBJ_Door_Iron;

import java.awt.*;

public class CutsceneManager {

    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;


    //Номер сцены
    public final int NA = 0;
    public final int skeletonLord = 1;
    public final int crystalknight = 2;
    public final int ending = 3;



    public CutsceneManager(GamePanel gp) {
        this.gp = gp;

        endCredit = "/Программа/Музыка/Арт\n"
                + "Илья"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "Поставьте автомат";

    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        switch (sceneNum) {
            case skeletonLord: scene_skeletonLord(); break;
            case crystalknight: scene_crystalknight(); break;
            case ending: scene_ending(); break;
        }
    }
    public void scene_skeletonLord(){

        if(scenePhase == 0){

            gp.bossBattleOn = true;

            //Поставить жел дверь
            for(int i = 0; i < gp.obj[1].length; i++){

                if( gp.obj[gp.currentMap][i] == null){
                    gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize*25;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize*28;
                    gp.obj[gp.currentMap][i].temp = true;
                    gp.playSE(19);
                    break;
                }
            }

            //Поиск места для манекена "Dummy"
            for(int i = 0; i < gp.npc[1].length; i++){

                if(gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            gp.player.drawing = false;
            scenePhase++;
        }
        if(scenePhase == 1){

            gp.player.worldY -= 6;
            if(gp.player.worldY < gp.tileSize * 16){

                scenePhase++;
            }
        }
        if(scenePhase == 2){

            //Поиск босса
            for(int i = 0; i < gp.monster[1].length; i++){

                if(gp.monster[gp.currentMap][i] != null &&
                        gp.monster[gp.currentMap][i].name == MON_SkeletonLord.monName){

                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }
        if(scenePhase == 3){

            //Диалог босса
            gp.ui.drawDialogScreen();
        }
        if(scenePhase == 4){

            //Возвращение камеры к игроку

            //Скан манекена
            for(int i = 0; i < gp.npc[1].length; i++){

                if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)){
                    //Возвращение позиции игрока
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;

                    //Удаление манекена
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }

            //Рисовка персонажа
            gp.player.drawing = true;

            //Сбросить
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;

            //Смена музыки
            gp.stopMusic();
            gp.playMusic(20);
        }
    }
    public void scene_crystalknight (){

        if(scenePhase == 0){

            gp.bossBattleOn = true;

            //Поиск места для манекена "Dummy"
            for(int i = 0; i < gp.npc[1].length; i++){

                if(gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            gp.player.drawing = false;
            scenePhase++;
        }
        if(scenePhase == 1){

            gp.player.worldY -= 6;
            gp.player.worldX += 6;
            if(gp.player.worldX >= gp.tileSize * 75 && gp.player.worldY < gp.tileSize * 25 ){

                scenePhase++;
            }
        }
        if(scenePhase == 2){

            //Поиск босса
            for(int i = 0; i < gp.monster[1].length; i++){

                if(gp.monster[gp.currentMap][i] != null &&
                        gp.monster[gp.currentMap][i].name == MON_CrystalKnight.monName){

                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }
        if(scenePhase == 3){

            //Диалог босса
            gp.ui.drawDialogScreen();
        }
        if(scenePhase == 4){

            //Возвращение камеры к игроку

            //Скан манекена
            for(int i = 0; i < gp.npc[1].length; i++){

                if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)){
                    //Возвращение позиции игрока
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;

                    //Удаление манекена
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }

            //Рисовка персонажа
            gp.player.drawing = true;

            //Сбросить
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;

            //Смена музыки
            gp.stopMusic();
            gp.playMusic(20);
        }
    }
    public void scene_ending(){

        if(scenePhase == 0){

            gp.stopMusic();
            gp.ui.npc = new OBJ_BlueHeart(gp);
            scenePhase++;
        }
        if(scenePhase == 1){

            //Дисплей диалога
            gp.ui.drawDialogScreen();
        }
        if(scenePhase == 2){

            //Воспроизведение фанфары
            gp.playSE(4);
            scenePhase++;
        }
        if(scenePhase == 3){

            //Ждать пока закончится звук
            if(counterReached(300) == true){
                scenePhase++;
            }
        }
        if(scenePhase == 4){

            //Потемнение экрана
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            drawBlackBackground(alpha);

            if(alpha == 1f){
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 5){

            drawBlackBackground(1f);

            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }

            String text = "После победы над Королём Скелетов,\n"
                    + "Главный герой закончил поиски легендарного сокровища\n"
                    + "Но это не конец истории,\n"
                    + "Приключение только началось.";
            drawString(alpha, 38f, 200, text, 70);

            if(counterReached(600) == true){
                gp.playMusic(0);
                scenePhase++;
            }

        }
        if(scenePhase == 6){

            drawBlackBackground(1f);

            drawString(1f,120f, gp.screenHeight/2, "Boy Adventure", 40);

            if(counterReached(480) == true){
                scenePhase++;
            }
        }
        if(scenePhase == 7){

            drawBlackBackground(1f);
            y = gp.screenHeight/2;
            drawString(1f, 38f, y, endCredit, 40);

            if(counterReached(480) == true){
                scenePhase++;
            }
        }
        if(scenePhase == 8){

            drawBlackBackground(1f);

            //Скролл текста
            y--;
            drawString(1f,38f, y, endCredit, 40);
        }
    }
    public boolean counterReached(int target){

        boolean counterReached = false;

        counter++;
        if(counter > target){
            counterReached = true;
            counter = 0;
        }

        return counterReached;
    }
    public void drawBlackBackground(float alpha){

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public void drawString(float alpha, float fontSize,int y, String text, int lineHeight){

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for(String line: text.split("\n")){

            int x = gp.ui.getXforCenteredText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
