package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Potion_Red extends Entity {
    int value = 5;
    GamePanel gp;
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Фласка здоровья";
        down1 = setup("/objects/potion_hil", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nВосполняет здоровье на " + value + ".";

    }
    public void use(Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "Ты выпил фласку здоровья!\n"
        + "Твоё здоровье восполнено на " + value;

        entity.life += value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife ;
        }
        gp.playSE(2);
    }
}
