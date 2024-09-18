package entity;

import Main.GamePanel;
import Main.KeyHabdler;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHabdler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0; // ключей у плеера в данный момент
    public int standCounter = 0;

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

        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        up3 = setup("boy_up_3");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        down3 = setup("boy_down_3");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
    }


    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image =  ImageIO.read(getClass().getResourceAsStream("/player/" + imageName +".png"));
            image = uTool.scaleImage(image,gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
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
            int objIndex = gp.cCheker.checkObject(this, true);
            pickUpObject(objIndex);

            // Если нет коллизии, персонаж может двигаться
            if (!collisionOn) {
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
        }

        // Если все клавиши отпущены, персонаж стоит на месте
        if (!keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed) {
            standCounter++;
            if (standCounter > 20) { // можно настроить время простоя
                spriteNum = 1; // возвращаем персонажа в начальное положение
                standCounter = 0;
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
        g2.drawImage(image, screenX, screenY, null);


        // Прорисовка коллизии
//        g2.setColor(Color.red);
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
