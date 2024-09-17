package Main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class Assets {
    GamePanel gp;
    public Assets(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject(){

//        gp.obj[0] = new OBJ_Key();
//        gp.obj[0].worldX = gp.tileSize * 9 - (gp.tileSize/2);
//        gp.obj[0].worldY = gp.tileSize * 14 - (gp.tileSize/2);
        gp.obj[0] = new OBJ_Key();
//        gp.obj[0].worldX; // =  3 * gp.tileSize ;
//        gp.obj[0].worldY; // =  5 * gp.tileSize ;
////
//        gp.obj[1] = new OBJ_Key();
//        gp.obj[1].worldX = 14 * gp.tileSize;
//        gp.obj[1].worldY = 10 * gp.tileSize;


       if( gp.obj[0] != null) {
            gp.obj[0].worldX = 8 * gp.tileSize;
            gp.obj[0].worldY = 15 * gp.tileSize;
       }else{
           System.out.println("idi nahyi");
       }

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = gp.tileSize * 31;
        gp.obj[1].worldY = gp.tileSize * 5;

        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = gp.tileSize * 12;
        gp.obj[2].worldY = gp.tileSize * 16;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = gp.tileSize * 13;
        gp.obj[3].worldY = gp.tileSize * 16;

        gp.obj[4] = new OBJ_Chest();
        gp.obj[4].worldX = gp.tileSize * 7;
        gp.obj[4].worldY = gp.tileSize * 42;
    }

}
