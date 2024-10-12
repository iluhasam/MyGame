package monster;

import Main.GamePanel;
import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Rock;

import java.util.Random;

public class MON_Goblin extends Entity {

    GamePanel gp;

    public MON_Goblin(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Гоблин";
        defaultSpeed = 3;
        speed = defaultSpeed;
        maxLife = 8;
        life = maxLife;
        attack = 4;
        defense = 0;
        exp = 1;
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

        up1 = setup("/monster/goblin/up1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/goblin/up2",gp.tileSize, gp.tileSize);
        up3 = setup("/monster/goblin/up3",gp.tileSize, gp.tileSize);
        up4 = setup("/monster/goblin/up4",gp.tileSize, gp.tileSize);
        down1 = setup("/monster/goblin/down1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/goblin/down2",gp.tileSize, gp.tileSize);
        down3 = setup("/monster/goblin/down3",gp.tileSize, gp.tileSize);
        down4 = setup("/monster/goblin/down4",gp.tileSize, gp.tileSize);
        left1 = setup("/monster/goblin/left1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/goblin/left2",gp.tileSize, gp.tileSize);
        left3 = setup("/monster/goblin/left3",gp.tileSize, gp.tileSize);
        left4 = setup("/monster/goblin/left4",gp.tileSize, gp.tileSize);
        right1 = setup("/monster/goblin/right1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/goblin/right2",gp.tileSize, gp.tileSize);
        right3 = setup("/monster/goblin/right3",gp.tileSize, gp.tileSize);
        right4 = setup("/monster/goblin/right4",gp.tileSize, gp.tileSize);

    }
    public void getAttackImage(){
        attackUp1 = setup("/monster/goblin/attack_up1",gp.tileSize, gp.tileSize);
        attackUp2 = setup("/monster/goblin/attack_up2",gp.tileSize, gp.tileSize);
        attackUp3 = setup("/monster/goblin/attack_up3",gp.tileSize, gp.tileSize);
        attackUp4 = setup("/monster/goblin/attack_up4",gp.tileSize, gp.tileSize);

        attackDown1 = setup("/monster/goblin/attack_down1",gp.tileSize, gp.tileSize);
        attackDown2 = setup("/monster/goblin/attack_down2",gp.tileSize, gp.tileSize);
        attackDown3 = setup("/monster/goblin/attack_down3",gp.tileSize, gp.tileSize);
        attackDown4 = setup("/monster/goblin/attack_down4",gp.tileSize, gp.tileSize);

        attackLeft1 = setup("/monster/goblin/attack_left1",gp.tileSize, gp.tileSize);
        attackLeft2 = setup("/monster/goblin/attack_left2",gp.tileSize, gp.tileSize);
        attackLeft3 = setup("/monster/goblin/attack_left3",gp.tileSize, gp.tileSize);
        attackLeft4 = setup("/monster/goblin/attack_left4",gp.tileSize, gp.tileSize);

        attackRight1 = setup("/monster/goblin/attack_right1",gp.tileSize, gp.tileSize);
        attackRight2 = setup("/monster/goblin/attack_right2",gp.tileSize, gp.tileSize);
        attackRight3 = setup("/monster/goblin/attack_right3",gp.tileSize, gp.tileSize);
        attackRight4 = setup("/monster/goblin/attack_right4",gp.tileSize, gp.tileSize);

    }
    public void setAction(){

        if(onPath == true){
            //STOP CHASING
            checkStopChasingOrNot(gp.player, 14, 100);

            //НЕПИСЬ СЛЕДУЕТ ЗА ТОБОЙ (НАДО В ENTITY УБИРАТЬ, КАК ТОЛЬКО ПОГОВОРИЛ СРАЗУ ББ)
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        }else {
            //START CHASING
            checkStartChasingOrNot(gp.player, 14, 100);

            //Рандои направление
            getRandomDirection(70);
        }
        // Проверка атаки
        if(attacking == false){
            checkAttackOrNot(30, gp.tileSize*3, gp.tileSize);
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
