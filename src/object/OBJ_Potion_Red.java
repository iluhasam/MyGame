package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Potion_Red extends Entity {
    GamePanel gp;
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = "Фласка здоровья";
        value = 5;
        price = 75;
        stackable = true;
        down1 = setup("/objects/potion_hil", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nВосполняет здоровье на " + value + ".";

        setDialogue();
    }
    public void setDialogue(){

        dialogues[0][0] = "Ты выпил фласку здоровья!\n"
                + "Твоё здоровье восполнено на " + value;
    }
    public boolean use(Entity entity) {

        startDialogue(this,0);
        entity.life += value;
        gp.playSE(2);
        return true;
    }
}
