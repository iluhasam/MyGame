package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Building_blue extends Entity {

    GamePanel gp;

    public OBJ_Building_blue(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Синий дом";
        down1 = setup("/objects/building1_blue",gp.tileSize*6,gp.tileSize*6);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 64;
        solidArea.width = gp.tileSize * 6 - 20;
        solidArea.height = gp.tileSize * 5;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
