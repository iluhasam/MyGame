package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Axe_Wood extends Entity {

    public OBJ_Axe_Wood(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Деревянный топор";
        down1 = setup("/objects/axe_wood", gp.tileSize, gp.tileSize);
        attackArea.width = 30;
        attackArea.height = 30;
        attackValue = 2;
        description = "[" + name + "]" + "\nПоменьше чем меч *_*";
    }
}
