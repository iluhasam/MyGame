package entity;

import Main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);


        direction = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        getImage();
        setDialogue();
    }
    public void getImage(){
        up1 = setup("/npc/oldman_up_1",gp.tileSize, gp.tileSize);
        up2 = setup("/npc/oldman_up_2",gp.tileSize, gp.tileSize);
        down1 = setup("/npc/oldman_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/npc/oldman_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("/npc/oldman_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("/npc/oldman_left_2",gp.tileSize, gp.tileSize);
        right1 = setup("/npc/oldman_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("/npc/oldman_right_2",gp.tileSize, gp.tileSize);
    }
    public void setDialogue(){

        dialogues[0] = " Привет ";
        dialogues[1] = " Ты пришел на этот остров, чтобы найти сокровища?";
        dialogues[2] = " Не хочу тебя огорчать но \n эти сокровища уже давно утеряны в глубинах этого острова";
        dialogues[3] = " Но поспешу тебя предупредить. \n Последний кто видел эти сокровища давно перестал быть человеком";
        dialogues[4] = " В чертогах своего разума я помню нашу с ним битву...";
        dialogues[5] = " Наши битвы были легендарны. \n О нас слагали легенды";
        dialogues[6] = " Но увы время берет свое. \n Я уже слишком стар! \n Но могу тебя кое чему научить";
        dialogues[7] = " Ладно, удачи тебе в твоем путешествии ";

    }
    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1; // 1 to 100(not 0 to 99)

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
             if(i > 50 && i <= 75 ){
                direction = "left";
            }
            if(i > 75 && i <= 100)
            {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    public void speak(){
        //Мб дополнить(принести дроп)
        super.speak();
    }
}
