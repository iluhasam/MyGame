package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {
        super(gp);
        name = "heart";
        image = setup("/objects/life_full",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/life_1",gp.tileSize,gp.tileSize);
        image3 = setup("/objects/life_2",gp.tileSize,gp.tileSize);
        image4 = setup("/objects/life_blank",gp.tileSize,gp.tileSize);
    }
}
