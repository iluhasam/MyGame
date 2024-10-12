package monster;

import Main.GamePanel;
import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_SkeletonLord extends Entity {

        GamePanel gp;

        public static final String monName = "Skeleton";

        public MON_SkeletonLord(GamePanel gp) {
            super(gp);

            this.gp = gp;

            type = type_monster;
            name = monName;
            defaultSpeed = 1;
            speed = defaultSpeed;
            maxLife = 100;
            life = maxLife;
            attack = 10;
            defense = 4;
            exp = 50;
            motion1_duration = 25;
            motion2_duration = 50;

            int size = gp.tileSize*5;
            solidArea.x = 64;
            solidArea.y = 64;
            solidArea.width = size - 64*2;
            solidArea.height = size - 64;
            solidAreaDefaultX = solidArea.x;
            solidAreaDefaultY = solidArea.y;
            attackArea.width = 250;
            attackArea.height = 250;


            getImage();
            getAttackImage();
        }

        public void getImage(){

            int i = 5;

            if(inRage == false){
                up1 = setup("/monster/skeletonlord/skeletonlord_up_1",gp.tileSize*i, gp.tileSize*i);
                up2 = setup("/monster/skeletonlord/skeletonlord_up_2",gp.tileSize*i, gp.tileSize*i);

                down1 = setup("/monster/skeletonlord/skeletonlord_down_1",gp.tileSize*i, gp.tileSize*i);
                down2 = setup("/monster/skeletonlord/skeletonlord_down_2",gp.tileSize*i, gp.tileSize*i);

                left1 = setup("/monster/skeletonlord/skeletonlord_left_1",gp.tileSize*i, gp.tileSize*i);
                left2 = setup("/monster/skeletonlord/skeletonlord_left_2",gp.tileSize*i, gp.tileSize*i);

                right1 = setup("/monster/skeletonlord/skeletonlord_right_1",gp.tileSize*i, gp.tileSize*i);
                right2 = setup("/monster/skeletonlord/skeletonlord_right_2",gp.tileSize*i, gp.tileSize*i);
            }
            if(inRage == true){
                up1 = setup("/monster/skeletonlord/skeletonlord_phase2_up_1",gp.tileSize*i, gp.tileSize*i);
                up2 = setup("/monster/skeletonlord/skeletonlord_phase2_up_2",gp.tileSize*i, gp.tileSize*i);

                down1 = setup("/monster/skeletonlord/skeletonlord_phase2_down_1",gp.tileSize*i, gp.tileSize*i);
                down2 = setup("/monster/skeletonlord/skeletonlord_phase2_down_2",gp.tileSize*i, gp.tileSize*i);

                left1 = setup("/monster/skeletonlord/skeletonlord_phase2_left_1",gp.tileSize*i, gp.tileSize*i);
                left2 = setup("/monster/skeletonlord/skeletonlord_phase2_left_2",gp.tileSize*i, gp.tileSize*i);

                right1 = setup("/monster/skeletonlord/skeletonlord_phase2_right_1",gp.tileSize*i, gp.tileSize*i);
                right2 = setup("/monster/skeletonlord/skeletonlord_phase2_right_2",gp.tileSize*i, gp.tileSize*i);
            }
        }
        public void getAttackImage(){

            int i = 5;

            if(inRage == false){

                attackUp1 = setup("/monster/skeletonlord/skeletonlord_attack_up_1",gp.tileSize*i, gp.tileSize*i*2);
                attackUp2 = setup("/monster/skeletonlord/skeletonlord_attack_up_2",gp.tileSize*i, gp.tileSize*i*2);

                attackDown1 = setup("/monster/skeletonlord/skeletonlord_attack_down_1",gp.tileSize*i, gp.tileSize*i*2);
                attackDown2 = setup("/monster/skeletonlord/skeletonlord_attack_down_2",gp.tileSize*i, gp.tileSize*i*2);

                attackLeft1 = setup("/monster/skeletonlord/skeletonlord_attack_left_1",gp.tileSize*i*2, gp.tileSize*i);
                attackLeft2 = setup("/monster/skeletonlord/skeletonlord_attack_left_2",gp.tileSize*i*2, gp.tileSize*i);

                attackRight1 = setup("/monster/skeletonlord/skeletonlord_attack_right_1",gp.tileSize*i*2, gp.tileSize*i);
                attackRight2 = setup("/monster/skeletonlord/skeletonlord_attack_right_2",gp.tileSize*i*2, gp.tileSize*i);
            }
            if(inRage == true){

                attackUp1 = setup("/monster/skeletonlord/skeletonlord_phase2_attack_up_1",gp.tileSize*i, gp.tileSize*i*2);
                attackUp2 = setup("/monster/skeletonlord/skeletonlord_phase2_attack_up_2",gp.tileSize*i, gp.tileSize*i*2);

                attackDown1 = setup("/monster/skeletonlord/skeletonlord_phase2_attack_down_1",gp.tileSize*i, gp.tileSize*i*2);
                attackDown2 = setup("/monster/skeletonlord/skeletonlord_phase2_attack_down_2",gp.tileSize*i, gp.tileSize*i*2);

                attackLeft1 = setup("/monster/skeletonlord/skeletonlord_phase2_attack_left_1",gp.tileSize*i*2, gp.tileSize*i);
                attackLeft2 = setup("/monster/skeletonlord/skeletonlord_phase2_attack_left_2",gp.tileSize*i*2, gp.tileSize*i);

                attackRight1 = setup("/monster/skeletonlord/skeletonlord_phase2_attack_right_1",gp.tileSize*i*2, gp.tileSize*i);
                attackRight2 = setup("/monster/skeletonlord/skeletonlord_phase2_attack_right_2",gp.tileSize*i*2, gp.tileSize*i);
            }


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
