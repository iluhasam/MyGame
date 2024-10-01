package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Heart extends Entity {
    GamePanel gp;
    public OBJ_Heart(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "heart";

        type = type_pickupOnly;
        value = 3;
        down1 = setup("/objects/life_full",gp.tileSize,gp.tileSize);
        image = setup("/objects/life_full",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/life_1",gp.tileSize,gp.tileSize);
        image3 = setup("/objects/life_2",gp.tileSize,gp.tileSize);
        image4 = setup("/objects/life_blank",gp.tileSize,gp.tileSize);
    }
    public boolean use(Entity entity) {
        gp.playSE(1);
        gp.ui.addMessage("Здоровье + " + value);
        entity.life += value;
        return true;
    }
}
