package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Building_red extends Entity {

    GamePanel gp;

    public OBJ_Building_red(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Красный дом";
        down1 = setup("/objects/building1_red",gp.tileSize*6,gp.tileSize*6);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 64;
        solidArea.width = gp.tileSize * 6 - 20;
        solidArea.height = gp.tileSize * 5;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
