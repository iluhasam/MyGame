package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Tent_Small extends Entity {

    GamePanel gp;
    public OBJ_Tent_Small(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "tent small";
        down1 = setup("/objects/Tent_Small",gp.tileSize*3,gp.tileSize*6);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 64;
        solidArea.width = gp.tileSize * 3 - 10;
        solidArea.height = gp.tileSize * 4;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}

