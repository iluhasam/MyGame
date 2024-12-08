package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_With_Hut extends Entity {

        GamePanel gp;

        public OBJ_With_Hut(GamePanel gp) {
            super(gp);
            this.gp = gp;

            type = type_obstacle;
            name = "Дом Ведьмы";
            down1 = setup("/objects/With_Hut", gp.tileSize*8, gp.tileSize*7);
            collision = true;

            solidArea.x = 0;
            solidArea.y = 0;
            solidArea.width = gp.tileSize * 7;
            solidArea.height = gp.tileSize * 7;
            solidAreaDefaultX = solidArea.x;
            solidAreaDefaultY = solidArea.y;

        }

}
