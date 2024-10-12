package Main;

//import entity.NPC_BigRock;
import entity.NPC_OldMan;
import entity.NPC_Trader;
import entity.NPC_Trader2;
import monster.*;
import object.*;
//import tile_interactive.IT_DestructibleWall;
import tile_interactive.IT_DryTree;
//import tile_interactive.IT_MetalPlate;

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
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*30;
        gp.obj[mapNum][i].worldY = gp.tileSize*29;
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

        mapNum = 2;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*40 ;
        gp.obj[mapNum][i].worldY = gp.tileSize*41;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*13 ;
        gp.obj[mapNum][i].worldY = gp.tileSize*16;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*26 ;
        gp.obj[mapNum][i].worldY = gp.tileSize*34;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*27 ;
        gp.obj[mapNum][i].worldY = gp.tileSize*15;
        i++;

        /*gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*18 ;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;*/



        mapNum = 4;
        gp.obj[mapNum][i] = new OBJ_Tent_Small(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*43;
        gp.obj[mapNum][i].worldY = gp.tileSize*42;
        i++;
        gp.obj[mapNum][i] = new OBJ_Tent_Big(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*38;
        gp.obj[mapNum][i].worldY = gp.tileSize*42;
        i++;
        gp.obj[mapNum][i] = new OBJ_Building_blue(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*58;
        gp.obj[mapNum][i].worldY = gp.tileSize*47;
        i++;
        gp.obj[mapNum][i] = new OBJ_Building_red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*64;
        gp.obj[mapNum][i].worldY = gp.tileSize*47;
        i++;
        gp.obj[mapNum][i] = new OBJ_Building3_blue(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*59;
        gp.obj[mapNum][i].worldY = gp.tileSize*61;
        i++;


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

//        mapNum = 2;
//        i = 0;
//        gp.npc[mapNum][i] = new NPC_BigRock(gp);
//        gp.npc[mapNum][i].worldX = gp.tileSize * 20;
//        gp.npc[mapNum][i].worldY = gp.tileSize * 25;
//        i++;
//
//        gp.npc[mapNum][i] = new NPC_BigRock(gp);
//        gp.npc[mapNum][i].worldX = gp.tileSize * 11;
//        gp.npc[mapNum][i].worldY = gp.tileSize * 18;
//        i++;
//
//        gp.npc[mapNum][i] = new NPC_BigRock(gp);
//        gp.npc[mapNum][i].worldX = gp.tileSize * 23;
//        gp.npc[mapNum][i].worldY = gp.tileSize * 14;
//        i++;



        //MAP 5
        mapNum = 5;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Trader2(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 22;
        gp.npc[mapNum][i].worldY = gp.tileSize * 16;
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
        gp.monster[mapNum][i]= new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 37;
        i++;
        gp.monster[mapNum][i]= new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 12;
        gp.monster[mapNum][i].worldY = gp.tileSize * 33;
        i++;
        gp.monster[mapNum][i]= new MON_Dworf(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 12;
        gp.monster[mapNum][i].worldY = gp.tileSize * 31;
        i++;

        mapNum = 2;
        i++;
        gp.monster[mapNum][i]= new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 39;
        i++;
        gp.monster[mapNum][i]= new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 36;
        gp.monster[mapNum][i].worldY = gp.tileSize * 25;
        i++;
        gp.monster[mapNum][i]= new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
        gp.monster[mapNum][i].worldY = gp.tileSize * 26;
        i++;
        gp.monster[mapNum][i]= new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 28;
        gp.monster[mapNum][i].worldY = gp.tileSize * 11;
        i++;
        gp.monster[mapNum][i]= new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 10;
        gp.monster[mapNum][i].worldY = gp.tileSize * 19;
        i++;

        mapNum = 3;
        gp.monster[mapNum][i]= new MON_SkeletonLord(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 16;
        i++;
        mapNum = 6;
        i++;
        gp.monster[mapNum][i]= new MON_Goblin(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 43;
        gp.monster[mapNum][i].worldY = gp.tileSize * 33;
        i++;
        gp.monster[mapNum][i]= new MON_Goblin(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 45;
        gp.monster[mapNum][i].worldY = gp.tileSize * 36;
        i++;
        gp.monster[mapNum][i]= new MON_Goblin(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 46;
        gp.monster[mapNum][i].worldY = gp.tileSize * 39;
        i++;
        gp.monster[mapNum][i]= new MON_Goblin(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 41;
        gp.monster[mapNum][i].worldY = gp.tileSize * 43;
        i++;
        gp.monster[mapNum][i]= new MON_Ghost(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 43;
        gp.monster[mapNum][i].worldY = gp.tileSize * 46;
        i++;
        gp.monster[mapNum][i]= new MON_Ghost(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 53;
        gp.monster[mapNum][i].worldY = gp.tileSize * 43;
        i++;
        gp.monster[mapNum][i]= new MON_Ghost(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 55;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[mapNum][i]= new MON_Ghost(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 63;
        gp.monster[mapNum][i].worldY = gp.tileSize * 49;
        i++;
        gp.monster[mapNum][i]= new MON_Ghost(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 70;
        gp.monster[mapNum][i].worldY = gp.tileSize * 57;
        i++;
        gp.monster[mapNum][i]= new MON_Ghost(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 70;
        gp.monster[mapNum][i].worldY = gp.tileSize * 57;
        i++;
        gp.monster[mapNum][i]= new MON_Ghost(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 70;
        gp.monster[mapNum][i].worldY = gp.tileSize * 57;
        i++;


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

        /*mapNum = 2;
        i = 0;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,18, 30 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,17, 31 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,17, 32 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,17, 34 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,18, 34 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,18, 33 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,10, 22 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,10, 24 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,38, 18 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,38, 19 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,38, 20 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,38, 21 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,18, 13 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,18, 14 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,22, 28 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,30, 28 );i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,32, 28 );i++;

        gp.iTile[mapNum][i] = new IT_MetalPlate(gp,20, 22 );i++;
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp,8, 17 );i++;
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp,39, 31 );i++;*/







    }
}
