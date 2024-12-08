package entity;

import Main.GamePanel;

import java.awt.*;

public class NPC_Witch extends Entity {

    GamePanel gp;

    public NPC_Witch (GamePanel gp) {
        super(gp);
        this.gp = gp;

        direction = "down";
        speed = 0;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getImage();
        setDialogue();
    }
    public void getImage(){
        down1 = setup("/npc/witch/witch1", gp.tileSize*2, gp.tileSize*2);
        down2 = setup("/npc/witch/witch2", gp.tileSize*2, gp.tileSize*2);
        up1 = setup("/npc/witch/witch1", gp.tileSize*2, gp.tileSize*2);
        up2 = setup("/npc/witch/witch2", gp.tileSize*2, gp.tileSize*2);
        left1 = setup("/npc/witch/witch1", gp.tileSize*2, gp.tileSize*2);
        left2 = setup("/npc/witch/witch2", gp.tileSize*2, gp.tileSize*2);
        right1 = setup("/npc/witch/witch1", gp.tileSize*2, gp.tileSize*2);
        right2 = setup("/npc/witch/witch2", gp.tileSize*2, gp.tileSize*2);
    }
    public void setDialogue(){

        dialogues[0][0] = " Привет, накопилось много хлама?)\n Тогда можешь отдавать мне\n и получать всякие плюшки взамен";
        dialogues[0][1] = "Приходи ещё, хи-хи-хи!!!";


    }
//    public void setAction(){
//
//        if(onPath == true){
//
//            int goalCol = 12;
//            int goalRow = 9;
//
//            //НЕПИСЬ СЛЕДУЕТ ЗА ТОБОЙ( НАДО В ENTITY УБИРАТЬ, КАК ТОЛЬКО ПОГОВОРИЛ СРАЗУ ББ)
////            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
////            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
//
//            searchPath(goalCol, goalRow);
//        }
//        else{
//            actionLockCounter++;
//
//            if(actionLockCounter == 120){
//                Random random = new Random();
//                int i = random.nextInt(100)+1; // 1 to 100(not 0 to 99)
//
//                if(i <= 25){
//                    direction = "up";
//                }
//                if(i > 25 && i <= 50){
//                    direction = "down";
//                }
//                if(i > 50 && i <= 75 ){
//                    direction = "left";
//                }
//                if(i > 75 && i <= 100)
//                {
//                    direction = "right";
//                }
//                actionLockCounter = 0;
//            }
//        }
//    }
    public void speak(){
        //Мб дополнить(принести дроп)

        facePlayer();
        startDialogue(this,dialogueSet);

        dialogueSet++;

        if(dialogues[dialogueSet][0] == null){
            dialogueSet--;
        }

//        if(gp.player.life < gp.player.maxLife/3){
//            dialogueSet = 1;
//        }

        //onPath = true;
    }
}
