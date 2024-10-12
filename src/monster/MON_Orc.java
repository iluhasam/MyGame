package monster;

import Main.GamePanel;
import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_Orc extends Entity {
    GamePanel gp;

    public MON_Orc(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Орк";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 20;
        life = maxLife;
        attack = 10;
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

        up1 = setup("/monster/orc/orc_up_1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/orc/orc_up_2",gp.tileSize, gp.tileSize);
        down1 = setup("/monster/orc/orc_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/orc/orc_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("/monster/orc/orc_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/orc/orc_left_2",gp.tileSize, gp.tileSize);
        right1 = setup("/monster/orc/orc_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/orc/orc_right_2",gp.tileSize, gp.tileSize);
    }
    public void getAttackImage(){
        attackUp1 = setup("/monster/orc/orc_attack_up_1",gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/monster/orc/orc_attack_up_2",gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/monster/orc/orc_attack_down_1",gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/monster/orc/orc_attack_down_2",gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/monster/orc/orc_attack_left_1",gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/monster/orc/orc_attack_left_2",gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/monster/orc/orc_attack_right_1",gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/monster/orc/orc_attack_right_2",gp.tileSize*2, gp.tileSize);
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
