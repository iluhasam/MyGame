package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_ManaCrystal extends Entity {
    GamePanel gp;
    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Кристалл Маны";
        value = 1;
        down1 = setup("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image = setup("/objects/manacrystal_blank",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/manacrystal_full",gp.tileSize,gp.tileSize);
    }
    public boolean use(Entity entity) {
        gp.playSE(1);
        gp.ui.addMessage("Мана + " + value);
        entity.mana += value;
        return true;
    }
}
