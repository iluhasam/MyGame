package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Building3_blue extends Entity {

    GamePanel gp;

    public OBJ_Building3_blue(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Синий дом3";
        down1 = setup("/objects/building3_blue",gp.tileSize*6,gp.tileSize*7);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 64;
        solidArea.width = gp.tileSize * 6 - 4;
        solidArea.height = gp.tileSize * 6;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}