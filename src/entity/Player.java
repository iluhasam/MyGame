package entity;

import Main.GamePanel;
import Main.KeyHabdler;
import object.OBJ_Key;
import object.OBJ_Shield_Start;
import object.OBJ_Sword_Start;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {

    KeyHabdler keyH;
    public final int screenX;
    public final int screenY;
    public int standCounter = 0;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Player(GamePanel gp, KeyHabdler keyH) {

        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2;
        screenY = gp.screenHeight/2;

        solidArea = new Rectangle();
        solidArea.x = 6;
        solidArea.y = 16;
        solidArea.width = 46;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();

    }
    public void setDefaultValues() {
        worldX = gp.tileSize * 23 - (gp.tileSize/2);
        worldY = gp.tileSize * 21 - (gp.tileSize/2);
//        worldX = gp.tileSize * 11 - (gp.tileSize/2);
//        worldY = gp.tileSize * 14 - (gp.tileSize/2);
        speed = 4;
        direction = "down";

        //PLAYER STATUS
        level =1;
        maxLife = 9;
        life = maxLife;
        strength = 1;
        agility = 1;
        exp = 0;
        nextLevelExp = 4;
        coin = 0;
        currentWeapon = new OBJ_Sword_Start(gp);
        currentShield = new OBJ_Shield_Start(gp);
        attack = getAttack();
        defense = getDefense();
    }
    public void setItems(){
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));


    }
    public int getAttack(){
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = strength * currentShield.defenseValue;
    }
    public void getPlayerImage(){

        up = setup("/player/boy_up", gp.tileSize, gp.tileSize);
        up1 = setup("/player/boy_up1",gp.tileSize, gp.tileSize);
        up2 = setup("/player/boy_up2",gp.tileSize, gp.tileSize);
        up3 = setup("/player/boy_up3",gp.tileSize, gp.tileSize);
        up4 = setup("/player/boy_up4",gp.tileSize, gp.tileSize);
        up5 = setup("/player/boy_up5",gp.tileSize, gp.tileSize);
        up6 = setup("/player/boy_up6",gp.tileSize, gp.tileSize);
        down = setup("/player/boy_down",gp.tileSize, gp.tileSize);
        down1 = setup("/player/boy_down1",gp.tileSize, gp.tileSize);
        down2 = setup("/player/boy_down2",gp.tileSize, gp.tileSize);
        down3 = setup("/player/boy_down3",gp.tileSize, gp.tileSize);
        down4 = setup("/player/boy_down4",gp.tileSize, gp.tileSize);
        down5 = setup("/player/boy_down5",gp.tileSize, gp.tileSize);
        down6 = setup("/player/boy_down6",gp.tileSize, gp.tileSize);
        left = setup("/player/boy_left",gp.tileSize, gp.tileSize);
        left1 = setup("/player/boy_left1",gp.tileSize, gp.tileSize);
        left2 = setup("/player/boy_left2",gp.tileSize, gp.tileSize);
        left3 = setup("/player/boy_left3",gp.tileSize, gp.tileSize);
        left4 = setup("/player/boy_left4",gp.tileSize, gp.tileSize);
        left5 = setup("/player/boy_left5",gp.tileSize, gp.tileSize);
        left6 = setup("/player/boy_left6",gp.tileSize, gp.tileSize);
        right = setup("/player/boy_right",gp.tileSize, gp.tileSize);
        right1 = setup("/player/boy_right1",gp.tileSize, gp.tileSize);
        right2 = setup("/player/boy_right2",gp.tileSize, gp.tileSize);
        right3 = setup("/player/boy_right3",gp.tileSize, gp.tileSize);
        right4 = setup("/player/boy_right4",gp.tileSize, gp.tileSize);
        right5 = setup("/player/boy_right5",gp.tileSize, gp.tileSize);
        right6 = setup("/player/boy_right6",gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage(){
        attackUp1 = setup("/player/boy_atack_up1",gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/player/boy_atack_up2",gp.tileSize, gp.tileSize*2);
        attackUp3 = setup("/res/player/boy_atack_up3",gp.tileSize, gp.tileSize*2);
        attackUp4 = setup("/res/player/boy_atack_up4",gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/res/player/boy_atack_down1",gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/res/player/boy_atack_down2",gp.tileSize, gp.tileSize*2);
        attackDown3 = setup("/player/boy_atack_down3",gp.tileSize, gp.tileSize*2);
        attackDown4 = setup("/player/boy_atack_down4",gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/player/boy_atack_left1",gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/player/boy_atack_left2",gp.tileSize*2, gp.tileSize);
        attackLeft3 = setup("/player/boy_atack_left3",gp.tileSize*2, gp.tileSize);
        attackLeft4 = setup("/player/boy_atack_left4",gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/player/boy_atack_right1",gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/player/boy_atack_right2",gp.tileSize*2, gp.tileSize);
        attackRight3 = setup("/player/boy_atack_right3",gp.tileSize*2, gp.tileSize);
        attackRight4 = setup("/player/boy_atack_right4",gp.tileSize*2, gp.tileSize);
    }
    public void update() {

        if(attacking == true){
            attacking ();
            return ;
        }

        boolean isMoving = false;

        // Проверка нажатия клавиш
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed ||
                keyH.rightPressed) {
            isMoving = true;
            if (keyH.upPressed)  {direction = "up";
            } else if (keyH.downPressed) {direction = "down";
            } else if (keyH.leftPressed) {direction = "left";
            } else if (keyH.rightPressed) {direction = "right";}


            // Проверка на коллизию
            collisionOn = false;
            gp.cCheker.checkTile(this);

            //CHECK NPC COLLISION
            int npcIndex = gp.cCheker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cCheker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);


            //CHECK EVENT
            gp.eHandler.checkEvent();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false && keyH.enterPressed == false) {
                switch (direction) {
                case "up":worldY -= speed;break;
                case "down":worldY += speed;break;
                case "left":worldX -= speed;break;
                case "right":worldX += speed;break;
                }
            }
        }

        if(keyH.enterPressed == true && attackCanceled == false){
            gp.playSE(7);
            attacking = true;
            spriteCounter = 0;
        }

        attackCanceled = false;
        gp.keyH.enterPressed = false;

        if (keyH.enterPressed) {
            attacking = true;
            keyH.enterPressed = false;
        }
        // Обновление анимации
        if (isMoving) {
            spriteCounter++;
            if (spriteCounter > 11) {
                spriteNum++;
                if (spriteNum > 7) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {spriteNum = 1;}




        //OUTSIDE OF KEY IF
        if(invincible == true){
            invicibleCounter++;
            if(invicibleCounter > 60){
                invincible = false;
                invicibleCounter = 0;
            }
        }

    }
    public void attacking(){

        spriteCounter++;

        if (spriteCounter <= 10) {
            spriteNum = 1;
        } else if (spriteCounter > 10 && spriteCounter <= 20) {
            spriteNum = 2;

            //Save the current worldX, worldY solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidht = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust player's worldX/Y for the attackArea
            switch (direction) {
                case "up": worldY -= attackArea.height;break;
                case "down": worldY += attackArea.height;break;
                case "left": worldX -= attackArea.width;break;
                case "right": worldX += attackArea.width;break;
            }

            //attackArea become solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gp.cCheker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            //After checking collision
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaHeight;
            solidArea.height = solidAreaHeight;


        } else if (spriteCounter > 20 && spriteCounter <= 30) {
            spriteNum = 3;
        } else if (spriteCounter > 30 && spriteCounter <= 40) {
            spriteNum = 4;
        } else if (spriteCounter > 40) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false; // Сбрасываем атаку
        }


    }
    public void pickUpObject (int i){
        if(i != 999){

        }
    }
    public void interactNPC(int i){
        if(gp.keyH.enterPressed == true){
            if(i != 999){
                attackCanceled = true;
                   gp.gameState = gp.dialogueState;
                    gp.npc[i].speak();}

        }
    }
    public void contactMonster(int i){
        if(i != 999 &&i >-0 && i < gp.monster.length){
            if(gp.monster[i] !=null && invincible == false){
                gp.playSE(6);
                int damage = gp.monster[i].attack - defense;
                if(damage < 0 ) {
                    damage = 0;
                }

                life -= damage;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i) {
        if (i != 999 && i >= 0 && i < gp.monster.length) { // Проверка индекса
            if (gp.monster[i] != null && gp.monster[i].invincible == false) {
                gp.playSE(5);

                int damage = attack - gp.monster[i].defense;
                if(damage < 0 ) {
                    damage = 0;
                }
                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <= 0) {
                    gp.monster[i].dying = true;
                    gp.ui.addMessage("killed the" + gp.monster[i].name + "!");
                    gp.ui.addMessage("Exp +" + gp.monster[i].exp + "!");
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void checkLevelUp(){
        if(exp >= nextLevelExp){
            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 3;
            strength++;
            agility++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "Твой уровень " + level + " сейчас \n"
            + " Ты чувствуешь себя сильнее!";

        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize );

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch(direction){
            case "up":
                if(attacking == false){
                    if(spriteNum == 1) {image = up;}
                    if(spriteNum == 2) {image = up1;}
                    if(spriteNum == 3) {image = up2;}
                    if(spriteNum == 4) {image = up3;}
                    if(spriteNum == 5) {image = up4;}
                    if(spriteNum == 6) {image = up5;}
                    if(spriteNum == 7) {image = up6;}
                    }
                if(attacking == true){
                    if(spriteNum == 1) {image = attackUp1;}
                    if(spriteNum == 2) {image = attackUp2;}
                    if(spriteNum == 3) {image = attackUp3;}
                    if(spriteNum == 4) {image = attackUp4;}
                }
                break;
            case "down":
                 if(attacking == false){
                if(spriteNum == 1){image = down;}
                if(spriteNum == 2) {image = down1;}
                if(spriteNum == 3) {image = down2;}
                if(spriteNum == 4) {image = down3;}
                if(spriteNum == 5) {image = down4;}
                if(spriteNum == 6) {image = down5;}
                if(spriteNum == 7) {image = down6;}
                }
                if(attacking == true){
                    if(spriteNum == 1) {image = attackDown1;}
                    if(spriteNum == 2) {image = attackDown2;}
                    if(spriteNum == 3) {image = attackDown3;}
                    if(spriteNum == 4) {image = attackDown4;}
                }
                break;
            case "left":
                if(attacking == false){
                if(spriteNum == 1) {image = left;}
                if(spriteNum == 2) {image = left1;}
                if(spriteNum == 3) {image = left2;}
                if(spriteNum == 4) {image = left3;}
                if(spriteNum == 5) {image = left4;}
                if(spriteNum == 6) {image = left5;}
                if(spriteNum == 7) {image = left6;}
                }
                if(attacking == true){
                    tempScreenX = screenX - gp.tileSize;
                    if(spriteNum == 1) {image = attackLeft1;}
                    if(spriteNum == 2) {image = attackLeft2;}
                    if(spriteNum == 3) {image = attackLeft3;}
                    if(spriteNum == 4) {image = attackLeft4;}
                }
                break;
            case "right":
                if(attacking == false){
                if(spriteNum == 1) {image = right;}
                if(spriteNum == 2) {image = right1;}
                if(spriteNum == 3) {image = right2;}
                if(spriteNum == 4) {image = right3;}
                if(spriteNum == 5) {image = right4;}
                if(spriteNum == 6) {image = right5;}
                if(spriteNum == 7) {image = right6;}
                }
                if(attacking == true){
                    if(spriteNum == 1) {image = attackRight1;}
                    if(spriteNum == 2) {image = attackRight2;}
                    if(spriteNum == 3) {image = attackRight3;}
                    if(spriteNum == 4) {image = attackRight4;}
                }

                break;
        }

        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        }
        g2.drawImage(image, tempScreenX, screenY, null);

        //RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //DEBUG invincible
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("invincible" + invicibleCounter, 10, 400);
    }
}
