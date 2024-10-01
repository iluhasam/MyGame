package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Lantern extends Entity {

    public OBJ_Lantern(GamePanel gp) {
        super(gp);

        type = type_light;
        name = "Фонарь";
        down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize);
        description = "[Фонарь]\nОсвещает окружение";
        price = 200;
        lightRadius = 250;
    }
}
