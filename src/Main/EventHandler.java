package Main;

import data.Progress;
import entity.Entity;

public class EventHandler  {

    GamePanel gp;
    EventRect eventRect[][][];
    Entity eventMaster;
    int tempMap,tempCol,tempRow;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

        public EventHandler(GamePanel gp) {
            this.gp = gp;

            eventMaster = new Entity(gp);

            eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

            int map = 0;
            int col = 0;
            int row = 0;
            while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {

                eventRect[map][col][row] = new EventRect();
                eventRect[map][col][row].x = 23;
                eventRect[map][col][row].y = 23;
                eventRect[map][col][row].width = 8;
                eventRect[map][col][row].height = 8;
                eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
                eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

                col++;
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                    if (row == gp.maxWorldRow) {
                        row = 0;
                        map++;
                    }
                }
            }
            setDialogue();
        }
        public void setDialogue(){

            eventMaster.dialogues[0][0] = "Ты наступил в какашку!)";;

            eventMaster.dialogues[1][0] = "Ты попил воды\nРадуйся жизни!\n(Прогресс сохранён) ";

            eventMaster.dialogues[1][1] = "Черт. Очень вкусно";

        }
        public void checkEvent() {
            //CHECK PLAYER 1 TILE AWAY FROM THE LAST EVENT
            int xDistance = Math.abs(gp.player.worldX - previousEventX);
            int yDistance = Math.abs(gp.player.worldY - previousEventY);
            int distance = Math.max(xDistance, yDistance);
            if(distance > gp.tileSize){
                canTouchEvent = true;
            }

            if(canTouchEvent == true) {
                if(hit(0,27,16,"right") == true){damagePit(gp.dialogueState);}
                else if (hit(0,23,12,"up") == true){healingPool(gp.dialogueState);}

                else if (hit(0,22,44,"any") == true){teleport(4,50,7, gp.outside);}//worldmap2
                else if (hit(0,23,44,"any") == true){teleport(4,49,7, gp.outside);}//worldmap2

                else if (hit(4,50,7,"any") == true){teleport(0,22,44, gp.outside);}//return map2
                else if (hit(4,49,7,"any") == true){teleport(0,22,44, gp.outside);}//return map2

                else if (hit(4, 61, 68, "up") == true){teleport(5,27,21, gp.indoor);}//build
                else if (hit(5,27,22, "down") == true){teleport(4,61,68, gp.outside);}

                else if (hit(0,10,39,"any") == true){teleport(1,12,13, gp.indoor);}//trader
                else if (hit(1,12,13, "any") == true){teleport(0,10,39, gp.outside);}//out trader

                else if (hit(1, 12,7,"up") == true){speak(gp.npc[1][0]);}
                else if (hit(0,12,9, "any") == true){teleport(2,9,41, gp.dungeon);} // тп в данж
                else if (hit(2,9,41, "any") == true){teleport(0,12,9, gp.outside);} // тп из данжа

                else if (hit(2,8,7, "any") == true){teleport(3,26,41, gp.dungeon);} // to b2
                else if (hit(3,26,41, "any") == true){teleport(2,8,7, gp.dungeon);} // to b1

                //CAVE
                else if(hit(4,74,41, "up") == true){teleport(6,36,26, gp.dungeon);}
                else if(hit(4,75,41, "up") == true){teleport(6,37,26, gp.dungeon);}

                else if(hit(6,36,26,"up") == true){teleport(4,74,42, gp.outside);}
                else if(hit(6,37,26,"up") == true){teleport(4,75,42, gp.outside);}

                //BOSS CUTSCENE
                else if(hit(3,25,27,"up") == true){skeletonLord();}

                else if(hit(8,76,37, "any") == true){devil();}

                //WITCHMAP
                else if(hit(4, 89,43, "right") == true){teleport(7,14,48, gp.outside);}
                else if(hit(4, 89,44, "right") == true){teleport(7,14,48, gp.outside);}
                else if(hit(4, 89,45, "right") == true){teleport(7,14,48, gp.outside);}
                else if(hit(4, 89,42, "right") == true){teleport(7,14,48, gp.outside);}
                else if(hit(4, 89,46, "right") == true){teleport(7,14,48, gp.outside);}

                else if(hit(7, 12,46, "left") == true){teleport(4,87,44, gp.outside);}
                else if(hit(7, 12,47, "left") == true){teleport(4,87,44, gp.outside);}
                else if(hit(7, 12,48, "left") == true){teleport(4,87,44, gp.outside);}
                else if(hit(7, 12,49, "left") == true){teleport(4,87,44, gp.outside);}
                else if(hit(7, 12,50, "left") == true){teleport(4,87,44, gp.outside);}

                //dung
                else if(hit(4,39, 36, "up") == true ){teleport(8,26,90, gp.dungeon);}
                else if(hit(4,40, 36, "up") == true ){teleport(8,26,90, gp.dungeon);}

                else if(hit(8,45,50,"up")== true){teleport(8,76,40, gp.dungeon);}
                else if(hit(8,46,50,"up")== true){teleport(8,76,40, gp.dungeon);}

                else if(hit(8,35,63,"up")== true){teleport(8,15,12, gp.dungeon);}
                else if(hit(8,36,63,"up")== true){teleport(8,15,12, gp.dungeon);}

                else if(hit(8,14,10,"up")== true){teleport(8,35,64, gp.dungeon);}
                else if(hit(8,15,10,"up")== true){teleport(8,35,64, gp.dungeon);}

                else if(hit(8,60,27,"up")== true){teleport(4,39,38, gp.outside);}
                else if(hit(8,61,27,"up")== true){teleport(4,39,38, gp.outside);}

            }

        }

        public boolean hit(int map,int col, int row, String reqDirection) {

            boolean hit = false;

            if(map == gp.currentMap){
                gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
                gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
                eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
                eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

                if(gp.player.solidArea.intersects(eventRect[map][col][row]) &&
                        eventRect[map][col][row].eventDone == false) {
                    if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                        hit = true;

                        previousEventX = gp.player.worldX;
                        previousEventY = gp.player.worldY;
                    }
                }
                gp.player.solidArea.x = gp.player.solidAreaDefaultX;
                gp.player.solidArea.y = gp.player.solidAreaDefaultY;
                eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
                eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;

            }
            return hit;
        }
        public void damagePit(int gameState){

            gp.gameState = gameState;
            gp.playSE(6);
            eventMaster.startDialogue(eventMaster,0);
            gp.player.life -= 1;
            canTouchEvent = false;
        }
        public void healingPool(int gameState ){

            if(gp.keyH.enterPressed == true){
                gp.gameState = gameState;
                gp.player.attackCanceled = true;
                eventMaster.startDialogue(eventMaster,1);
                gp.player.life = gp.player.maxLife;
                gp.player.mana = gp.player.maxMana;
                gp.assets.setMonster();
                gp.saveLoad.save();
                gp.playSE(2);

            }
        }
        public void teleport(int map, int col, int row,int area) {

            gp.gameState = gp.transitionState;
            gp.nextArea = area;
            tempMap = map;
            tempCol = col;
            tempRow = row;
//            gp.currentMap = map;
//            gp.player.worldX = gp.tileSize * col;
//            gp.player.worldY = gp.tileSize * row;
//            previousEventX = gp.player.worldX;
//            previousEventY = gp.player.worldY;
            canTouchEvent = false;
            gp.playSE(13);
        }
        public void speak(Entity entity){

            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogueState;
                gp.player.attackCanceled = true;
                entity.speak();
            }
        }
        public void skeletonLord(){

            if(gp.bossBattleOn == false && Progress.skeletonLordDefeated == false){
                gp.gameState = gp.cutsceneState;
                gp.csManager.sceneNum = gp.csManager.skeletonLord;
            }
        }
        public void devil(){

            if(gp.bossBattleOn == false && Progress.devilDeafeated == false){
                gp.gameState = gp.cutsceneState;
                gp.csManager.sceneNum = gp.csManager.devil;
            }
        }

}
