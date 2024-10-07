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
    public void setDialogue(){

        dialogues[0][0] = "Ты использовал " + name + " и открыл дверь";

        dialogues[1][0] = "Что ты делаешь?";

    }
    public boolean use(Entity entity) {

        int objIndex = getDetected(entity, gp.obj, "door");

        if(objIndex != 999) {
            startDialogue(this,0);
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        } else{
            startDialogue(this,1);
            return false;
        }
    }
}
