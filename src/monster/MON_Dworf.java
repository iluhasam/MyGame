package monster;

import Main.GamePanel;
import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_Dworf extends Entity {

    GamePanel gp;
    public MON_Dworf(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Дворф";
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 30;
        life = maxLife;
        attack = 0;
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
        update();
    }
    public void getImage() {

        up1 = setup("/monster/dworf/dworf_up_1",gp.tileSize,gp.tileSize);
//        up2 = setup("/monster/dworf/dworf_up_2",gp.tileSize,gp.tileSize);
        up2 = setup("/monster/dworf/dworf_up_3",gp.tileSize,gp.tileSize);
//        up4 = setup("/monster/dworf/dworf_up_4",gp.tileSize,gp.tileSize);

        down1 = setup("/monster/dworf/dworf_down_1",gp.tileSize,gp.tileSize);
//        down2 = setup("/monster/dworf/dworf_down_2",gp.tileSize,gp.tileSize);
        down2 = setup("/monster/dworf/dworf_down_3",gp.tileSize,gp.tileSize);
//        down4 = setup("/monster/dworf/dworf_down_4",gp.tileSize,gp.tileSize);

        right1 = setup("/monster/dworf/dworf_right_1",gp.tileSize,gp.tileSize);
//        right2 = setup("/monster/dworf/dworf_right_2",gp.tileSize,gp.tileSize);
        right2 = setup("/monster/dworf/dworf_right_3",gp.tileSize,gp.tileSize);
//        right4 = setup("/monster/dworf/dworf_right_4",gp.tileSize,gp.tileSize);

        left1 = setup("/monster/dworf/dworf_left_1",gp.tileSize,gp.tileSize);
//        left2 = setup("/monster/dworf/dworf_left_2",gp.tileSize,gp.tileSize);
        left2 = setup("/monster/dworf/dworf_left_3",gp.tileSize,gp.tileSize);
//        left4 = setup("/monster/dworf/dworf_left_4",gp.tileSize,gp.tileSize);

    }
    public void getAttackImage(){

        attackUp1 = setup("/monster/dworf/dworf_attack_up_2",gp.tileSize, gp.tileSize + (gp.tileSize / 2));
        attackUp2 = setup("/monster/dworf/dworf_attack_up_3",gp.tileSize, gp.tileSize + (gp.tileSize / 4));

        attackDown1 = setup("/monster/dworf/dworf_attack_down_2",gp.tileSize,gp.tileSize + (((int)(gp.tileSize/1.8))));
        attackDown2 = setup("/monster/dworf/dworf_attack_down_3",gp.tileSize, gp.tileSize + (((gp.tileSize/4))));

        attackRight1 = setup("/monster/dworf/dworf_attack_right_2",gp.tileSize,gp.tileSize + (((int)(gp.tileSize/1.6))));
        attackRight2 = setup("/monster/dworf/dworf_attack_right_3",gp.tileSize + (((int)(gp.tileSize/2.3))),gp.tileSize+((gp.tileSize/4)));

        attackLeft1 = setup("/monster/dworf/dworf_attack_left_2",gp.tileSize,gp.tileSize + (((int)(gp.tileSize/1.6))));
        attackLeft2 = setup("/monster/dworf/dworf_attack_left_3",gp.tileSize + (((int)(gp.tileSize/2.3))),gp.tileSize+((gp.tileSize/4)));
    }
    public void setAction(){

        if(onPath == true){
            //STOP CHASING
            checkStopChasingOrNot(gp.player, 10, 100);

            //НЕПИСЬ СЛЕДУЕТ ЗА ТОБОЙ (НАДО В ENTITY УБИРАТЬ, КАК ТОЛЬКО ПОГОВОРИЛ СРАЗУ ББ)
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        }else {
            //START CHASING
            checkStartChasingOrNot(gp.player, 10, 100);

            //Рандои направление
            getRandomDirection(60);
        }
        // Проверка атаки
        if(attacking == false){
            checkAttackOrNot(30, gp.tileSize, gp.tileSize);
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
