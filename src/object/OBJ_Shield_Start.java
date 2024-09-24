package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Shield_Start extends Entity {
    public OBJ_Shield_Start(GamePanel gp) {
        super(gp);
        name = "Деревянный щит";
        down1 = setup("/objects/shield", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]" + "\nСделан из дерева.";

    }

}
