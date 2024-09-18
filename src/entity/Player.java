package entity;

import Main.GamePanel;
import Main.KeyHabdler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHabdler keyH;
    public int hasKey = 0; // ключей у плеера в данный момент

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHabdler keyH) {
        this.gp = gp;
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
        worldX = gp.tileSize * 26 - (gp.tileSize/2   );
        worldY = gp.tileSize * 21 - (gp.tileSize/2 );
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){

        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

//    public void setDefaultValues() {
//         worldX = gp.tileSize * 23;
//         worldY = gp.tileSize * 21;
//         speed = 4;
//         direction = "down";
//    }

    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
            {
            if(keyH.upPressed == true) {
                direction = "up";

            }
            else if(keyH.downPressed == true) {
                direction = "down";
//                worldY += speed;
            }
            else if(keyH.leftPressed == true) {
                direction = "left";
//                worldX -= speed;
            }
            else if(keyH.rightPressed == true) {
                direction = "right";
//                worldX += speed;
            }
        }

        //check tile collision
        collisionOn = false;
        gp.cCheker.checkTile(this);
        // check obj collision
        int objIndex = gp.cCheker.checkObject(this, true);
        pickUpObject(objIndex);

        //IF COLLISION FALSE, Player can move
        if(collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 15) {
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void pickUpObject (int i){
        if(i != 999){
            String objectName = gp.obj[i].name;

            switch(objectName){
                case "key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Ключик!!!");
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.playSE(3);
                        gp.obj[i] = null;
                        gp.ui.showMessage("Ты дверь?-_-");
                        hasKey--;
                    }else
                        gp.ui.showMessage("Ключ забыл!");
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed += 1.1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Тапок скорости");
                    break;
                case "Chest":
                    gp.ui.gameOver = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
        }
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
            case "down":
                if(spriteNum == 1) {
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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
