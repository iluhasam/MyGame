/*
package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Door_Iron extends Entity {

    GamePanel gp;
    public OBJ_Door_Iron(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Железная дверь";
        down1 = setup("/objects/door_iron",gp.tileSize,gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 32;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();

    }
    public void setDialogue(){

        dialogues[0][0] = "Хм... Кажется надо что-то сделать.";
    }
    public void interact(){

        startDialogue(this,0);


    }
}
*/
