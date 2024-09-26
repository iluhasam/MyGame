package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Coin_Bronze extends Entity {
    GamePanel gp;
    public OBJ_Coin_Bronze(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Бронзовая монета";
        value = 1;
        down1 = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize);
    }
    public void use(Entity entity) {

        gp.playSE(1);
        gp.ui.addMessage("Монета + " + value );
        gp.player.coin += value;
    }
}
