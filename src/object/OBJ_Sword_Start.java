package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Sword_Start extends Entity {

    public OBJ_Sword_Start(GamePanel gp) {
        super(gp);
        name = "Обычный меч";
        down1 = setup("/objects/sword", gp.tileSize, gp.tileSize);
        attackValue = 1;
        description = "[" + name + "]"+"\nСтарый меч.";

    }
}