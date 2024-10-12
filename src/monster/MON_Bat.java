package monster;

import Main.GamePanel;
import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_Bat extends Entity {

    GamePanel gp;
    public MON_Bat(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Летучая Мышь";
        defaultSpeed = 6;
        speed = defaultSpeed;
        maxLife = 1;
        life = maxLife;
        attack = 0;
        defense = 0;
        exp = 10;
        //projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){

        up1 = setup("/monster/bat/bat_down_1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/bat/bat_down_2",gp.tileSize, gp.tileSize);
        down1 = setup("/monster/bat/bat_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/bat/bat_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("/monster/bat/bat_down_1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/bat/bat_down_2",gp.tileSize, gp.tileSize);
        right1 = setup("/monster/bat/bat_down_1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/bat/bat_down_2",gp.tileSize, gp.tileSize);
    }
    public void setAction(){

        if(onPath == true){

        }else {


            //Рандои направление
            getRandomDirection(10);
        }
    }
    public void damageReaction(){
        actionLockCounter = 0;
//        direction = gp.player.direction;
       // onPath = true;
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
