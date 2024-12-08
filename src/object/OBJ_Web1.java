package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Web1 extends Entity {

    GamePanel gp;

    public OBJ_Web1(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Паутина";
        down1 = setup("/objects/web1", gp.tileSize, gp.tileSize);

        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
