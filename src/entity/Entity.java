package entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    GamePanel gp;

    public BufferedImage up, up1, up2, up3,up4,up5,up6,
            down, down1, down2, down3, down4, down5, down6,
            left, left1, left2, left3, left4, left5, left6,
            right, right1, right2, right3, right4, right5, right6;
    public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4,
            attackDown1, attackDown2, attackDown3, attackDown4,
            attackLeft1, attackLeft2, attackLeft3, attackLeft4,
            attackRight1, attackRight2, attackRight3, attackRight4;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public BufferedImage image, image2, image3, image4;
    public int solidAreaDefaultX, solidAreaDefaultY;
    String dialogues[] = new String[50];

    //STATE
    public int worldX,worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean collision = false;
    boolean attacking = false;

    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invicibleCounter = 0;

    //CHARACTER ATTIBUTES
    public int speed;
    public String name;
    public int type;  // 0 = player, 1 = npc, 2 = monster
    public int maxLife;
    public int life;

    public Entity (GamePanel gp){
        this.gp = gp;
    }
    public void setAction(){

    }
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction){
            case "up":direction = "down";break;
            case "down":direction = "up";break;
            case "left":direction = "right";break;
            case "right":direction = "left";break;
        }
    }
    public void update(){

            setAction();

        collisionOn = false;
        gp.cCheker.checkTile(this);
        gp.cCheker.checkObject(this,false);
        gp.cCheker.checkEntity(this, gp.npc);
        gp.cCheker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cCheker.checkPlayer(this);


        if(this.type == 2 && contactPlayer == true){
            if(gp.player.invincible == false){
                //we can give damage
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }


        if(collisionOn == false){
            switch(direction){
                case "up":worldY -= speed;break;
                case "down":worldY += speed;break;
                case "left":worldX -= speed;break;
                case "right":worldX += speed;break;
            }
        }
     //Обновление анимации
        boolean isMoving = up1 != null || down1 != null || left1 != null || right != null;

        if (isMoving) {
            spriteCounter++;
            if (spriteCounter > 11) {
                spriteNum++;
                if (spriteNum > 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteNum = 1; // Останавливаем анимацию на первом спрайте
        }

    }
    public BufferedImage setup(String imagePath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // прорисовка ресов
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
            case "up":
                if (spriteNum == 1) {image = up1;}
                if (spriteNum == 2) {image = up2;}
                if (spriteNum == 3) {image = up3;}
                break;
            case "down":
                if (spriteNum == 1) {image = down1;}
                if (spriteNum == 2) {image = down2;}
                if (spriteNum == 3) {image = down3;}
                break;
            case "left":
                if (spriteNum == 1) {image = left1;}
                if (spriteNum == 2) {image = left2;}
                break;
            case "right":
                if (spriteNum == 1) {image = right1;}
                if (spriteNum == 2) {image = right2;}
                break;
        }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

}
