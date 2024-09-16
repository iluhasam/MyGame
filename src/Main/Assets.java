package Main;

import object.OBJ_Key;

public class Assets {
    GamePanel gp;
    public Assets(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject(){

        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 32 * gp.tileSize;
        gp.obj[0].worldY = 6 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 9 * gp.tileSize;
        gp.obj[1].worldY = 16 * gp.tileSize;
    }

}
