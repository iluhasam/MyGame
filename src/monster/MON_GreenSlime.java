package monster;

import Main.GamePanel;
import entity.Entity;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 2;
        name = "Green Slime";
        speed = 1;
        maxLife = 2;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;

        solidArea.x = 3;
        solidArea.y = 28;
        solidArea.width = 58;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){

        up1 = setup("/monster/slime_move",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/slime_jump",gp.tileSize, gp.tileSize);
        down1 = setup("/monster/slime_move",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/slime_jump",gp.tileSize, gp.tileSize);
        left1 = setup("/monster/slime_move",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/slime_jump",gp.tileSize, gp.tileSize);
        right1 = setup("/monster/slime_move",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/slime_jump",gp.tileSize, gp.tileSize);
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
    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
