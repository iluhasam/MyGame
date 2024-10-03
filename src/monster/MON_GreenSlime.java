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
        defaultSpeed = 1;
        speed = defaultSpeed;
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
    public void setAction(){

        if(onPath == true){
            //STOP CHASING
            checkStopChasingOrNot(gp.player, 10, 100);

            //НЕПИСЬ СЛЕДУЕТ ЗА ТОБОЙ( НАДО В ENTITY УБИРАТЬ, КАК ТОЛЬКО ПОГОВОРИЛ СРАЗУ ББ)
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

            //Проверка выстрела projectile
            checkShootOrNot(200, 30);
        }else {
            //START CHASING
            checkStartChasingOrNot(gp.player, 5, 100);

            //Рандои направление
            getRandomDirection();
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
