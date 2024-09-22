package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {
        super(gp);
        name = "heart";
        image = setup("/objects/life_full");
        image = setup("/objects/life_1");
        image = setup("/objects/life_2");
        image = setup("/objects/life_blank");
    }
}
