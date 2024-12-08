package monster;

import Main.GamePanel;
import entity.Entity;
import object.OBJ_PurpleFireball;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MON_CrystalKnight extends Entity {

    GamePanel gp;

    public static final String monName = "Кристальный рыцарь";

    public MON_CrystalKnight(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        boss = true;
        name = monName;
        defaultSpeed = 3;
        speed = defaultSpeed;
        maxLife = 100;
        life = maxLife;
        attack = 10;
        defense = 4;
        exp = 50;
        projectile = new OBJ_PurpleFireball(gp);

        motion1_duration = 10;
        motion2_duration = 25;
        motion3_duration = 35;
        motion4_duration = 50;
        knockBackPower = 10;
        sleep = true;

        int size = gp.tileSize * 4;
        solidArea.x = 64;
        solidArea.y = 64;
        solidArea.width = size - size*2;
        solidArea.height = size ;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 250;
        attackArea.height = 250;

        getImage();
        getAttackImage();
        setDialogue();
    }
    public void getImage() {
        int i = 4;
        up1 = setup("/monster/crystalknight/crystalknight1", gp.tileSize * i, gp.tileSize * i);
        up2 = setup("/monster/crystalknight/crystalknight2", gp.tileSize * i, gp.tileSize * i);

        down1 = setup("/monster/crystalknight/crystalknight1", gp.tileSize * i, gp.tileSize * i);
        down2 = setup("/monster/crystalknight/crystalknight2", gp.tileSize * i, gp.tileSize * i);

        left1 = setup("/monster/crystalknight/crystalknight1", gp.tileSize * i, gp.tileSize * i);
        left2 = setup("/monster/crystalknight/crystalknight2", gp.tileSize * i, gp.tileSize * i);

        right1 = setup("/monster/crystalknight/crystalknight1", gp.tileSize * i, gp.tileSize * i);
        right2 = setup("/monster/crystalknight/crystalknight2", gp.tileSize * i, gp.tileSize * i);
    }
    public void getAttackImage() {
        int i = 4;
        attackUp1 = setup("/monster/crystalknight/attack1", gp.tileSize * i, gp.tileSize * i);
        attackUp2 = setup("/monster/crystalknight/attack2", gp.tileSize * i, gp.tileSize * i);
        attackUp3 = setup("/monster/crystalknight/attack3", gp.tileSize * i, gp.tileSize * i);
        attackUp4 = setup("/monster/crystalknight/attack4", gp.tileSize * i, gp.tileSize * i);

        attackDown1 = setup("/monster/crystalknight/attack1", gp.tileSize * i, gp.tileSize * i);
        attackDown2 = setup("/monster/crystalknight/attack2", gp.tileSize * i, gp.tileSize * i);
        attackDown3 = setup("/monster/crystalknight/attack3", gp.tileSize * i, gp.tileSize * i);
        attackDown4 = setup("/monster/crystalknight/attack4", gp.tileSize * i, gp.tileSize * i);

        attackLeft1 = setup("/monster/crystalknight/attack1", gp.tileSize * i, gp.tileSize * i);
        attackLeft2 = setup("/monster/crystalknight/attack2", gp.tileSize * i, gp.tileSize * i);
        attackLeft3 = setup("/monster/crystalknight/attack3", gp.tileSize * i, gp.tileSize * i);
        attackLeft4 = setup("/monster/crystalknight/attack4", gp.tileSize * i, gp.tileSize * i);

        attackRight1 = setup("/monster/crystalknight/attack21", gp.tileSize * i, gp.tileSize * i);
        attackRight2 = setup("/monster/crystalknight/attack22", gp.tileSize * i, gp.tileSize * i);
        attackRight3 = setup("/monster/crystalknight/attack23", gp.tileSize * i, gp.tileSize * i);
        attackRight4 = setup("/monster/crystalknight/attack24", gp.tileSize * i, gp.tileSize * i);
    }
    public void setDialogue() {
        dialogues[0][0] = "...";
    }
    public void setAction() {
        if (!inRage && life < maxLife / 2) {
            inRage = true;
            getImage();
            getAttackImage();
            defaultSpeed++;
            speed = defaultSpeed;
            attack *= 2;

        }
        checkShootOrNot(200, 30);
        if (getTileDistance(gp.player) < 10) {
            moveTowardPlayer(30);
        } else {
            // Случайное движение
            getRandomDirection(90);
        }

        if (!attacking) {
            checkAttackOrNot(120, gp.tileSize * 4, gp.tileSize * 3);
        }
    }
    public void damageReaction() {
            actionLockCounter = 0; // Обычная реакция на урон
    }
    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= motion1_duration) {
            spriteNum = 1;
        } else if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
            spriteNum = 2;
        } else if (spriteCounter > motion2_duration && spriteCounter <= motion3_duration) {
            spriteNum = 3;
            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (type == type_monster) {
                if (gp.cCheker.checkPlayer(this)) {
                    damagePlayer(attack);
                }
            } else {
                int monsterIndex = gp.cCheker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                int iTileIndex = gp.cCheker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cCheker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        } else if (spriteCounter <= motion4_duration) {
            spriteNum = 4;
        } else if (spriteCounter > motion4_duration) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (inCamera()) {
            int tempScreenX = getScreenX();
            int tempScreenY = getScreenY();

            switch (direction) {
                case "up":
                    if (!attacking) {
                        if (spriteNum == 1) image = up1;
                        if (spriteNum == 2) image = up2;
                        if (spriteNum == 3) image = up3;
                        if (spriteNum == 4) image = up4;
                    } else {
                        if (spriteNum == 1) image = attackUp1;
                        if (spriteNum == 2) image = attackUp2;
                        if (spriteNum == 3) image = attackUp3;
                        if (spriteNum == 4) image = attackUp4;
                    }
                    break;
                case "down":
                    if (!attacking) {
                        if (spriteNum == 1) image = down1;
                        if (spriteNum == 2) image = down2;
                        if (spriteNum == 3) image = down3;
                        if (spriteNum == 4) image = down4;
                    } else {
                        if (spriteNum == 1) image = attackDown1;
                        if (spriteNum == 2) image = attackDown2;
                        if (spriteNum == 3) image = attackDown3;
                        if (spriteNum == 4) image = attackDown4;
                    }
                    break;
                case "left":
                    if (!attacking) {
                        if (spriteNum == 1) image = left1;
                        if (spriteNum == 2) image = left2;
                        if (spriteNum == 3) image = left3;
                        if (spriteNum == 4) image = left4;
                    } else {
                        if (spriteNum == 1) image = attackLeft1;
                        if (spriteNum == 2) image = attackLeft2;
                        if (spriteNum == 3) image = attackLeft3;
                        if (spriteNum == 4) image = attackLeft4;
                    }
                    break;
                case "right":
                    if (!attacking) {
                        if (spriteNum == 1) image = right1;
                        if (spriteNum == 2) image = right2;
                        if (spriteNum == 3) image = right3;
                        if (spriteNum == 4) image = right4;
                    } else {
                        if (spriteNum == 1) image = attackRight1;
                        if (spriteNum == 2) image = attackRight2;
                        if (spriteNum == 3) image = attackRight3;
                        if (spriteNum == 4) image = attackRight4;
                    }
                    break;
            }
            if(dying == true){
                dyingAnimation(g2);
            }
            g2.drawImage(image, tempScreenX, tempScreenY, null);
        }
    }
}
