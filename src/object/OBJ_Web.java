package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Web extends Entity {

    GamePanel gp;

    public OBJ_Web(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Паутина";
        down1 = setup("/objects/web", gp.tileSize, gp.tileSize);

        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
