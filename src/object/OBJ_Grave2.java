package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Grave2 extends Entity {

    GamePanel gp;

    public OBJ_Grave2(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Могила";
        down1 = setup("/objects/grave3", gp.tileSize, gp.tileSize+6);

        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize+6;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
