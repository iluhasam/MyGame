package entity;

import Main.GamePanel;
import Main.KeyHabdler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHabdler keyH;
    public final int screenX;
    public final int screenY;
    public int standCounter = 0;

    public Player(GamePanel gp, KeyHabdler keyH) {

        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2;
        screenY = gp.screenHeight/2;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23 - (gp.tileSize/2);
        worldY = gp.tileSize * 21 - (gp.tileSize/2);
        speed = 4;
        direction = "down";

        //PLAYER STATUS
        maxLife = 9;
        life = maxLife;
    }

    public void getPlayerImage(){

        up1 = setup("/player/boy_up_1");
        up2 = setup("/player/boy_up_2");
        up3 = setup("/player/boy_up_3");
        down1 = setup("/player/boy_down_1");
        down2 = setup("/player/boy_down_2");
        down3 = setup("/player/boy_down_3");
        left1 = setup("/player/boy_left_1");
        left2 = setup("/player/boy_left_2");
        right1 = setup("/player/boy_right_1");
        right2 = setup("/player/boy_right_2");
    }

    public void update() {

        // Проверка нажатия клавиш
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }


            // Проверка на коллизию
            collisionOn = false;
            gp.cCheker.checkTile(this);

            //check npc collision
            int npcIndex = gp.cCheker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            if(collisionOn == false){
                switch (direction) {
                case "up":worldY -= speed;break;
                case "down":worldY += speed;break;
                case "left":worldX -= speed;break;
                case "right":worldX += speed;break;
                }
            }
            }

        // Обновление анимации
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void pickUpObject (int i){
        if(i != 999){

        }
    }
    public void interactNPC(int i){
        if(i != 999){
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.enterPressed = false;
    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize );

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1) {
                image = up1;
                }
                if(spriteNum == 2) {
                    image = up2 ;
                }
                if(spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":if(spriteNum == 1){
                image = down1;
            }
                if(spriteNum == 2) {
                    image = down2;
                }
                if(spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
default:

        }
        g2.drawImage(image, screenX, screenY, null);


        // Прорисовка коллизии
//        g2.setColor(Color.red);
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
