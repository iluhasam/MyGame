package monster;

import Main.GamePanel;
import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_GMaceman extends Entity {
    GamePanel gp;

    public MON_GMaceman(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Гоблин с булавой ";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 20;
        life = maxLife;
        attack = 5;
        defense = 1;
        exp = 8;
        motion1_duration = 40;
        motion2_duration = 80;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 54;
        solidArea.height = 58;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 64;
        attackArea.height = 64;


        getImage();
        getAttackImage();
    }

    public void getImage(){

        up1 = setup("/monster/G_Maceman/up1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/G_Maceman/up2",gp.tileSize, gp.tileSize);
        down1 = setup("/monster/G_Maceman/down1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/G_Maceman/down2",gp.tileSize, gp.tileSize);
        left1 = setup("/monster/G_Maceman/left1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/G_Maceman/left2",gp.tileSize, gp.tileSize);
        right1 = setup("/monster/G_Maceman/right1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/G_Maceman/right2",gp.tileSize, gp.tileSize);
    }
    public void getAttackImage(){
        attackUp1 = setup("/monster/G_Maceman/attackup1",gp.tileSize, gp.tileSize);
        attackUp2 = setup("/monster/G_Maceman/attackup2",gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/monster/G_Maceman/attackdown1",gp.tileSize, gp.tileSize);
        attackDown2 = setup("/monster/G_Maceman/attackdown2",gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/monster/G_Maceman/attackleft1",gp.tileSize, gp.tileSize);
        attackLeft2 = setup("/monster/G_Maceman/attackleft2",gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/monster/G_Maceman/attackright1",gp.tileSize, gp.tileSize);
        attackRight2 = setup("/monster/G_Maceman/attackright2",gp.tileSize*2, gp.tileSize);
    }
    public void setAction(){

        if(onPath == true){
            //STOP CHASING
            checkStopChasingOrNot(gp.player, 10, 100);

            //НЕПИСЬ СЛЕДУЕТ ЗА ТОБОЙ (НАДО В ENTITY УБИРАТЬ, КАК ТОЛЬКО ПОГОВОРИЛ СРАЗУ ББ)
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        }else {
            //START CHASING
            checkStartChasingOrNot(gp.player, 5, 100);

            //Рандои направление
            getRandomDirection(90);
        }
        // Проверка атаки
        if(attacking == false){
            checkAttackOrNot(30, gp.tileSize*4, gp.tileSize);
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

