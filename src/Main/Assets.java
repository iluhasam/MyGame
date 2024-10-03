package Main;

import entity.NPC_OldMan;
import entity.NPC_Trader;
import monster.MON_GreenSlime;
import monster.MON_Orc;
import object.*;
import tile_interactive.IT_DryTree;

public class Assets {
    GamePanel gp;
    public Assets(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject(){
        int mapNum = 0;
        int i = 0;
//        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
//        gp.obj[mapNum][i].worldX = gp.tileSize*25;
//        gp.obj[mapNum][i].worldY = gp.tileSize*19;
//        i++;
//        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
//        gp.obj[mapNum][i].worldX = gp.tileSize*21;
//        gp.obj[mapNum][i].worldY = gp.tileSize*19;
//        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*30;
        gp.obj[mapNum][i].worldY = gp.tileSize*28;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*14;
        gp.obj[mapNum][i].worldY = gp.tileSize*28;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*12;
        gp.obj[mapNum][i].worldY = gp.tileSize*12;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*26;
        gp.obj[mapNum][i].worldY = gp.tileSize*21;
        i++;
        gp.obj[mapNum][i] = new OBJ_Axe_Wood(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*33;
        gp.obj[mapNum][i].worldY = gp.tileSize*21;
        i++;
        gp.obj[mapNum][i] = new OBJ_Lantern(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*18;
        gp.obj[mapNum][i].worldY = gp.tileSize*20;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*22;
        gp.obj[mapNum][i].worldY = gp.tileSize*27;
        i++;
        gp.obj[mapNum][i] = new OBJ_Tent(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*23;
        gp.obj[mapNum][i].worldY = gp.tileSize*27;
        i++;
//        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
//        gp.obj[mapNum][i].worldX = gp.tileSize*22;
//        gp.obj[mapNum][i].worldY = gp.tileSize*26;
//        i++;
//        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
//        gp.obj[mapNum][i].worldX = gp.tileSize*22;
//        gp.obj[mapNum][i].worldY = gp.tileSize*25;
//        i++;
//        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
//        gp.obj[mapNum][i].worldX = gp.tileSize*22;
//        gp.obj[mapNum][i].worldY = gp.tileSize*24;
//        i++;
//        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
//        gp.obj[mapNum][i].worldX = gp.tileSize*22;
//        gp.obj[mapNum][i].worldY = gp.tileSize*23;
//        i++;

//        gp.obj[mapNum][i] =new OBJ_Heart(gp);
//        gp.obj[mapNum][i].worldX = gp.tileSize*30;
//        gp.obj[mapNum][i].worldY = gp.tileSize*21;
//        i++;
//        gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
//        gp.obj[mapNum][i].worldX = gp.tileSize*22;
//        gp.obj[mapNum][i].worldY = gp.tileSize*25;
//        i++;
    }
    public void setNPC(){
        //MAP 0
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 25;
        gp.npc[mapNum][i].worldY = gp.tileSize * 23;
        i++;

        //MAP 1
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Trader(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 12;
        gp.npc[mapNum][i].worldY = gp.tileSize * 7;
        i++;

    }
    public void setMonster(){
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 36;
        i++;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 37;
        i++;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 37;
        i++;
        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[mapNum][i]= new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 12;
        gp.monster[mapNum][i].worldY = gp.tileSize * 33;
        i++;
        gp.monster[mapNum][i]= new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 12;
        gp.monster[mapNum][i].worldY = gp.tileSize * 34;
        i++;
        gp.monster[mapNum][i]= new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 13;
        gp.monster[mapNum][i].worldY = gp.tileSize * 33;
        i++;

        //Мобы на новой карте добавляются так ->
//        mapNum = 1;
//        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
//        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
//        gp.monster[mapNum][i].worldY = gp.tileSize * 42;
//        i++;

    }
    public void setInteractiveTile(){
        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,27, 12);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,28, 12);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,29, 12);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,30, 12);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,31, 12);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,32, 12);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,33, 12);i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp,18, 40);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,17, 40 );i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,16, 40 );i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,15, 40 );i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,14, 40 );i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,13, 40 );i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,13, 41 );i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,12, 41 );i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,11, 41 );i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,10, 41 );i++;
    }
}
