package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Pickaxe extends Entity {

    GamePanel gp;
    public OBJ_Pickaxe(GamePanel gp) {
        super(gp);
        type = type_pickaxe;
        name = "Кирка";
        down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
        attackArea.width = 36;
        attackArea.height = 36;
        attackValue = 2;
        description = "[" + name + "]" + "\nКамень долбит.";
        price = 10;
        knockBackPower = 10;
        motion1_duration = 10;
        motion2_duration = 20;
    }
}
