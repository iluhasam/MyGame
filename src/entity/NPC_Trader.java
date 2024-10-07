package entity;

import Main.GamePanel;
import object.*;

import java.awt.*;

public class NPC_Trader extends Entity{
    GamePanel gp;
    public NPC_Trader(GamePanel gp) {
        super(gp);
        this.gp = gp;

        direction = "down";
        speed = 0;

        solidArea = new Rectangle();
        solidArea.x = 32;
        solidArea.y = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 64;
        solidArea.height = 64;

        getImage();
        setDialogue();
        setItems();
    }
    public void getImage(){
        down1 = setup("/npc/trader01", gp.tileSize*2, gp.tileSize*2);
        down2 = setup("/npc/trader02", gp.tileSize*2, gp.tileSize*2);
        up1 = setup("/npc/trader01", gp.tileSize*2, gp.tileSize*2);
        up2 = setup("/npc/trader02", gp.tileSize*2, gp.tileSize*2);
        left1 = setup("/npc/trader01", gp.tileSize*2, gp.tileSize*2);
        left2 = setup("/npc/trader02", gp.tileSize*2, gp.tileSize*2);
        right1 = setup("/npc/trader01", gp.tileSize*2, gp.tileSize*2);
        right2 = setup("/npc/trader02", gp.tileSize*2, gp.tileSize*2);
    }
    public void setDialogue(){
        dialogues[0][0] = " Привет, ищешь что можно прикупить?\nТогда ты пришел по адресу ";
    }
    public void setItems(){
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Shield_Start(gp));
        inventory.add(new OBJ_Axe_Wood(gp));
        inventory.add(new OBJ_Sword_Start(gp));

    }
    public void speak(){
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
