package monster;

import Main.GamePanel;
import entity.Entity;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        type = 2;
        name = "Green Slime";
        speed = 1;
        maxLife = 3;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 28;
        solidArea.width = 58;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){

        up1 = setup("/monster/slime_move");
        up2 = setup("/monster/slime_jump");
        down1 = setup("/monster/slime_move");
        down2 = setup("/monster/slime_jump");
        left1 = setup("/monster/slime_move");
        left2 = setup("/monster/slime_jump");
        right1 = setup("/monster/slime_move");
        right2 = setup("/monster/slime_jump");
    }
    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1; // 1 to 100(not 0 to 99)

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75 ){
                direction = "left";
            }
            if(i > 75 && i <= 100)
            {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
