package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Grave1 extends Entity {

    GamePanel gp;

    public OBJ_Grave1(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Могила";
        down1 = setup("/objects/grave2", gp.tileSize, gp.tileSize+10);

        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize+10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}