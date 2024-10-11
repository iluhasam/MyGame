package monster;

import Main.GamePanel;
import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Rock;

import java.util.Random;

public class MON_Ghost extends Entity {

    GamePanel gp;

    public MON_Ghost(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Призрак";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 1;
        life = maxLife;
        attack = 0;
        defense = 0;
        exp = 10;

        projectile = new OBJ_Rock(gp);
        solidArea.x = 3;
        solidArea.y = 28;
        solidArea.width = 58;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getAttackImage();
        getImage();
    }
    public void getImage(){

        up1 = setup("/monster/ghost/up1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/ghost/up2",gp.tileSize, gp.tileSize);
        up3 = setup("/monster/ghost/up3",gp.tileSize, gp.tileSize);
        up4 = setup("/monster/ghost/up4",gp.tileSize, gp.tileSize);
        down1 = setup("/monster/ghost/down1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/ghost/down2",gp.tileSize, gp.tileSize);
        down3 = setup("/monster/ghost/down3",gp.tileSize, gp.tileSize);
        down4 = setup("/monster/ghost/down4",gp.tileSize, gp.tileSize);
        left1 = setup("/monster/ghost/left1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/ghost/left2",gp.tileSize, gp.tileSize);
        left3 = setup("/monster/ghost/left3",gp.tileSize, gp.tileSize);
        left4 = setup("/monster/ghost/left4",gp.tileSize, gp.tileSize);
        right1 = setup("/monster/ghost/right1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/ghost/right2",gp.tileSize, gp.tileSize);
        right3 = setup("/monster/ghost/right3",gp.tileSize, gp.tileSize);
        right4 = setup("/monster/ghost/right4",gp.tileSize, gp.tileSize);

    }
    public void getAttackImage(){
        attackUp1 = setup("/monster/ghost/attack_up1",gp.tileSize, gp.tileSize);
        attackUp2 = setup("/monster/ghost/attack_up2",gp.tileSize, gp.tileSize);
        attackUp3 = setup("/monster/ghost/attack_up3",gp.tileSize, gp.tileSize);
        attackUp4 = setup("/monster/ghost/attack_up4",gp.tileSize, gp.tileSize);

        attackDown1 = setup("/monster/ghost/attack_down1",gp.tileSize, gp.tileSize);
        attackDown2 = setup("/monster/ghost/attack_down2",gp.tileSize, gp.tileSize);
        attackDown3 = setup("/monster/ghost/attack_down3",gp.tileSize, gp.tileSize);
        attackDown4 = setup("/monster/ghost/attack_down4",gp.tileSize, gp.tileSize);

        attackLeft1 = setup("/monster/ghost/attack_left1",gp.tileSize, gp.tileSize);
        attackLeft2 = setup("/monster/ghost/attack_left2",gp.tileSize, gp.tileSize);
        attackLeft3 = setup("/monster/ghost/attack_left3",gp.tileSize, gp.tileSize);
        attackLeft4 = setup("/monster/ghost/attack_left4",gp.tileSize, gp.tileSize);

        attackRight1 = setup("/monster/ghost/attack_right1",gp.tileSize, gp.tileSize);
        attackRight2 = setup("/monster/ghost/attack_right2",gp.tileSize, gp.tileSize);
        attackRight3 = setup("/monster/ghost/attack_right3",gp.tileSize, gp.tileSize);
        attackRight4 = setup("/monster/ghost/attack_right4",gp.tileSize, gp.tileSize);

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
            getRandomDirection();
        }
        // Проверка атаки
        if(attacking == false){
            checkAttackOrNot(30, gp.tileSize*2, gp.tileSize);
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
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if(i >= 90 && i < 100){
            dropItem(new OBJ_Coin_Bronze(gp));
        }
    }

}
