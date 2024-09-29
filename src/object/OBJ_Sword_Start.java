package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Sword_Start extends Entity {

    public OBJ_Sword_Start(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Обычный меч";
        down1 = setup("/objects/sword", gp.tileSize, gp.tileSize);
        attackArea.width = 36;
        attackArea.height = 36;
        attackValue = 1;
        description = "[" + name + "]"+"\nСтарый меч.";
        price = 15;

    }
}
