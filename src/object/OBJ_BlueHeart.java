package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_BlueHeart extends Entity {

    GamePanel gp;
    public static final String objName = "Blue Heart";

    public OBJ_BlueHeart(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        down1 = setup("/objects/blueheart", gp.tileSize, gp.tileSize);


        setDialogues ();
    }
    public void setDialogues (){

        dialogues[0][0] = "Ты подобрал прекрасный синий кристалл.";
        dialogues[0][1] = "Ты нашел Синее Сердце, легендарное сокровище";

    }
    public boolean use(Entity entity){

        gp.gameState = gp.cutsceneState;
        gp.csManager.sceneNum = gp.csManager.ending;
        return true;
    }
}
