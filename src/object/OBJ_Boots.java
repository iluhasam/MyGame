package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Boots extends Entity {

    public OBJ_Boots(GamePanel gp) {
        super(gp);
        name = "boots";
        down1 = setup("/objects/boots");

    }


}
