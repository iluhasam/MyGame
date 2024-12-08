package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Grave extends Entity {

    GamePanel gp;

    public OBJ_Grave(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Могила";
        down1 = setup("/objects/grave", gp.tileSize+gp.tileSize/2, gp.tileSize);

        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize+gp.tileSize/2;
        solidArea.height = gp.tileSize;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
