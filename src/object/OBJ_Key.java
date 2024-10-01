package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Key extends Entity {

    GamePanel gp;
    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Ключ";
        down1 = setup("/objects/key",gp.tileSize,gp.tileSize);
        description = "[" + name + "]"+ "\nОткрывает двери!\n(и возможно сундуки *_*).";
        collision = true;
        stackable = true;

    }
    public boolean use(Entity entity) {

        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "door");

        if(objIndex != 999) {
            gp.ui.currentDialogue = "Ты использовал " + name + " и открыл дверь";
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        } else{
            gp.ui.currentDialogue = "Что ты делаешь?";
            return false;
        }
    }
}
