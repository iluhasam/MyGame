package monster;

import Main.GamePanel;
import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Зеленый Слизень";
        speed = 1;
        maxLife = 8;
        life = maxLife;
        attack = 0;
        defense = 0;
        exp = 2;
        projectile = new OBJ_Rock(gp);

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
    public void update() {
        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;

        if(onPath == false && tileDistance < 5){

            int i = new Random().nextInt(100)+1;
            if(i > 50){
                onPath = true;
            }
        }
//        if(onPath == true && tileDistance > 20){
//            onPath = false;
//        }
    }
    public void setAction(){

        if(onPath == true){
            //НЕПИСЬ СЛЕДУЕТ ЗА ТОБОЙ( НАДО В ENTITY УБИРАТЬ, КАК ТОЛЬКО ПОГОВОРИЛ СРАЗУ ББ)
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol, goalRow);

            int i = new Random().nextInt(100)+1;
            if(i > 197 && projectile.alive == false && shotAvaliableCounter == 30){
                projectile.set(worldX,worldY, direction, true, this);
                gp.projectileList.add(projectile);
                shotAvaliableCounter = 0;
            }
        }
        else{
            actionLockCounter++;

            if(actionLockCounter == 120){
                Random random = new Random();
                int i = random.nextInt(100)+1; // 1 to 100(not 0 to 99)

                if(i <= 25){direction = "up";}
                if(i > 25 && i <= 50){direction = "down";}
                if(i > 50 && i <= 75 ){direction = "left";}
                if(i > 75 && i <= 100) {direction = "right";}
                actionLockCounter = 0;
            }
        }

    }
    public void damageReaction(){
        actionLockCounter = 0;
//        direction = gp.player.direction;
        onPath = true;
    }
    public void checkDrop(){

        //CAST A DIE
        int i = new Random().nextInt(100)+1;

        //SET MONSTER DROP
        if(i < 80){
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if(i >= 80 && i < 90){
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 90 && i < 100){
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}
