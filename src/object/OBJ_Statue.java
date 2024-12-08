package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Statue extends Entity {

    GamePanel gp;

    public OBJ_Statue(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Статуя";
        down1 = setup("/objects/statue", gp.tileSize*2, gp.tileSize*2);

        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize * 2;
        solidArea.height = gp.tileSize * 2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
