package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Tent_Big extends Entity {

    GamePanel gp;

    public OBJ_Tent_Big(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Tent_Big";
        down1 = setup("/objects/Tent_Small",gp.tileSize*5,gp.tileSize*6);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 64;
        solidArea.width = gp.tileSize * 5 - 20;
        solidArea.height = gp.tileSize * 4;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
