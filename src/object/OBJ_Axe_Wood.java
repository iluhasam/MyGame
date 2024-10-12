package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Axe_Wood extends Entity {

    public OBJ_Axe_Wood(GamePanel gp) {
        super(gp);

        type = type_axe;
        name = "Деревянный топор";
        down1 = setup("/objects/axe_wood", gp.tileSize, gp.tileSize);
        attackArea.width = 36;
        attackArea.height = 36;
        attackValue = 2;
        description = "[" + name + "]" + "\nДеревья рубит";
        price = 10;
        knockBackPower = 10;
        motion1_duration = 20;
        motion2_duration = 45;
    }
}
