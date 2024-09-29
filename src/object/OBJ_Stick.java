package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Stick extends Entity {
    public OBJ_Stick(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Палка";
        down1 = setup("/objects/stick", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]" + "\nПросто палка!.";
        price = 9999;
    }
}
