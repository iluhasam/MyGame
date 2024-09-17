package Main;

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
    }

}
