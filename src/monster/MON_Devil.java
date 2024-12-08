package monster;

import Main.GamePanel;
import data.Progress;
import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_Devil extends Entity {
    GamePanel gp;

    public  static final String monName = "Демон";

    public MON_Devil(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_monster;
        boss = true;
        name = monName;
        defaultSpeed = 3;
        speed = defaultSpeed;
        maxLife = 75;
        attack = 15;
        defense = 4;
        exp = 50;
        motion1_duration = 20;
        motion2_duration = 40;
        knockBackPower = 10;
        sleep = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize*2;
        solidArea.height = gp.tileSize*2;
        attackArea.width = 64;
        attackArea.height = 64;

        getImage();
        getAttackImage();
        setDialogue();
    }
    public void getImage() {
        int i = 2;

        if(inRage == false){
            up1 = setup("/monster/devil/up1",gp.tileSize*i, gp.tileSize*i);
            up2 = setup("/monster/devil/up2",gp.tileSize*i, gp.tileSize*i);

            down1 = setup("/monster/devil/down1",gp.tileSize*i, gp.tileSize*i);
            down2 = setup("/monster/devil/down2",gp.tileSize*i, gp.tileSize*i);

            left1 = setup("/monster/devil/left1",gp.tileSize*i, gp.tileSize*i);
            left2= setup("/monster/devil/left2",gp.tileSize*i, gp.tileSize*i);

            right1 = setup("/monster/devil/right1",gp.tileSize*i, gp.tileSize*i);
            right2= setup("/monster/devil/right2",gp.tileSize*i, gp.tileSize*i);
        }
        if(inRage == true){
            up1 = setup("/monster/devil/up1",gp.tileSize*i, gp.tileSize*i);
            up2 = setup("/monster/devil/up2",gp.tileSize*i, gp.tileSize*i);

            down1 = setup("/monster/devil/down1",gp.tileSize*i, gp.tileSize*i);
            down2 = setup("/monster/devil/down2",gp.tileSize*i, gp.tileSize*i);

            left1 = setup("/monster/devil/left1",gp.tileSize*i, gp.tileSize*i);
            left2= setup("/monster/devil/left2",gp.tileSize*i, gp.tileSize*i);

            right1 = setup("/monster/devil/right1",gp.tileSize*i, gp.tileSize*i);
            right2= setup("/monster/devil/right2",gp.tileSize*i, gp.tileSize*i);
        }
    }
    public void getAttackImage(){

        int i = 2;

        if(inRage == false){

            attackUp1 = setup("/monster/devil/attackup1",gp.tileSize*i, gp.tileSize*i);
            attackUp2 = setup("/monster/devil/attackup2",gp.tileSize*i, gp.tileSize*i);

            attackDown1 = setup("/monster/devil/attackdown1",gp.tileSize*i, gp.tileSize*i);
            attackDown2 = setup("/monster/devil/attackdown2",gp.tileSize*i, gp.tileSize*i);

            attackLeft1 = setup("/monster/devil/attackleft1",gp.tileSize*i, gp.tileSize*i);
            attackLeft2 = setup("/monster/devil/attackleft2",gp.tileSize*i, gp.tileSize*i);

            attackRight1 = setup("/monster/devil/attackright1",gp.tileSize*i, gp.tileSize*i);
            attackRight2 = setup("/monster/devil/attackright2",gp.tileSize*i, gp.tileSize*i);
        }
        if(inRage == true){

            attackUp1 = setup("/monster/devil/attackup1",gp.tileSize*i, gp.tileSize*i);
            attackUp2 = setup("/monster/devil/attackup2",gp.tileSize*i, gp.tileSize*i);

            attackDown1 = setup("/monster/devil/attackdown1",gp.tileSize*i, gp.tileSize*i);
            attackDown2 = setup("/monster/devil/attackdown2",gp.tileSize*i, gp.tileSize*i);

            attackLeft1 = setup("/monster/devil/attackleft1",gp.tileSize*i, gp.tileSize*i);
            attackLeft2 = setup("/monster/devil/attackleft2",gp.tileSize*i, gp.tileSize*i);

            attackRight1 = setup("/monster/devil/attackright1",gp.tileSize*i, gp.tileSize*i);
            attackRight2 = setup("/monster/devil/attackright2",gp.tileSize*i, gp.tileSize*i);
        }


    }
    public void setDialogue(){

        dialogues[0][0] = "Долго ты идёшь";
        dialogues[0][1] = "Ты Станешь прекрасной душой для моей коллекции";


    }
    public void setAction(){

        if(inRage == false && life < maxLife/2){

            inRage = true;
            getImage();
            getAttackImage();
            defaultSpeed++;
            speed = defaultSpeed;
            attack*= 2;
        }

        if(getTileDistance(gp.player) < 10){

            moveTowardPlayer(30);
        }
        else {
            //Рандои направление
            getRandomDirection(90);
        }
        // Проверка атаки
        if(attacking == false){
            checkAttackOrNot(60, gp.tileSize*7, gp.tileSize*5);
        }
    }
    public void damageReaction(){
        actionLockCounter = 0;

    }
    public void checkDrop(){

        gp.bossBattleOn = false;
        Progress.devilDeafeated = true;

        //Возвращение музыки

        gp.stopMusic();
        gp.playMusic(17);

        //Дроп после смерти
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
