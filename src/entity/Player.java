package entity;

import Main.GamePanel;
import Main.KeyHabdler;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Shield_Start;
import object.OBJ_Sword_Start;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHabdler keyH;
    public final int screenX;
    public final int screenY;
    public int standCounter = 0;
    public boolean attackCanceled = false;

    public Player(GamePanel gp, KeyHabdler keyH) {

        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2;
        screenY = gp.screenHeight/2;

        //SOLID AREA
        solidArea = new Rectangle();
        solidArea.x = 6;
        solidArea.y = 14;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        //ATTACK AREA
//      attackArea.width = 36;
//      attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();

    }
    public void setDefaultValues() {
        //mapv3
//        worldX = gp.tileSize * 23;// - (gp.tileSize/2);
//        worldY = gp.tileSize * 21;// - (gp.tileSize/2);

        //map interior
        worldX = gp.tileSize * 12;//map interior01
        worldY = gp.tileSize * 12;

        speed = 4;
        direction = "down";

        //PLAYER STATUS
        level =1;
        maxLife = 9;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        strength = 1;
        agility = 1;
        exp = 0;
        nextLevelExp = 4;
        coin = 9999;
        currentWeapon = new OBJ_Sword_Start(gp);
        //currentWeapon = new OBJ_Axe_Wood(gp);
        currentShield = new OBJ_Shield_Start(gp);
        projectile = new OBJ_Fireball(gp);
        //projectile = new OBJ_Rock(gp);
        attack = getAttack();
        defense = getDefense();
    }
    public void setDefaultPosition() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";
    }
    public void restoreLifeAndMana(){
        life = maxLife;
        mana = maxMana;
        invincible = false;
    }
    public void setItems(){
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));


    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = strength * currentShield.defenseValue;
    }
    public void getPlayerImage(){

        up = setup("/player/up", gp.tileSize, gp.tileSize);
        up1 = setup("/player/up1",gp.tileSize, gp.tileSize);
        up2 = setup("/player/up2",gp.tileSize, gp.tileSize);
        up3 = setup("/player/up3",gp.tileSize, gp.tileSize);
        up4 = setup("/player/up4",gp.tileSize, gp.tileSize);
        down = setup("/player/down",gp.tileSize, gp.tileSize);
        down1 = setup("/player/down1",gp.tileSize, gp.tileSize);
        down2 = setup("/player/down2",gp.tileSize, gp.tileSize);
        down3 = setup("/player/down3",gp.tileSize, gp.tileSize);
        down4 = setup("/player/down4",gp.tileSize, gp.tileSize);
        left = setup("/player/left",gp.tileSize, gp.tileSize);
        left1 = setup("/player/left1",gp.tileSize, gp.tileSize);
        left2 = setup("/player/left2",gp.tileSize, gp.tileSize);
        left3 = setup("/player/left3",gp.tileSize, gp.tileSize);
        left4 = setup("/player/left4",gp.tileSize, gp.tileSize);
        right = setup("/player/right",gp.tileSize, gp.tileSize);
        right1 = setup("/player/right1",gp.tileSize, gp.tileSize);
        right2 = setup("/player/right2",gp.tileSize, gp.tileSize);
        right3 = setup("/player/right3",gp.tileSize, gp.tileSize);
        right4 = setup("/player/right4",gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage(){
        attackUp1 = setup("/player/attack_up1",gp.tileSize*3, gp.tileSize*2);
        attackUp2 = setup("/player/attack_up2",gp.tileSize*3, gp.tileSize*2);
        attackUp3 = setup("/player/attack_up3",gp.tileSize*3, gp.tileSize*2);
        attackUp4 = setup("/player/attack_up4",gp.tileSize*3, gp.tileSize*2);
        attackDown1 = setup("/player/attack_down1",gp.tileSize*3, gp.tileSize*2);
        attackDown2 = setup("/player/attack_down2",gp.tileSize*3, gp.tileSize*2);
        attackDown3 = setup("/player/attack_down3",gp.tileSize*3, gp.tileSize*2);
        attackDown4 = setup("/player/attack_down4",gp.tileSize*3, gp.tileSize*2);
        attackLeft1 = setup("/player/attack_left1",gp.tileSize*2, gp.tileSize*3);
        attackLeft2 = setup("/player/attack_left2",gp.tileSize*2, gp.tileSize*3);
        attackLeft3 = setup("/player/attack_left3",gp.tileSize*2, gp.tileSize*3);
        attackLeft4 = setup("/player/attack_left4",gp.tileSize*2, gp.tileSize*3);
        attackRight1 = setup("/player/attack_right1",gp.tileSize*2, gp.tileSize*3);
        attackRight2 = setup("/player/attack_right2",gp.tileSize*2, gp.tileSize*3);
        attackRight3 = setup("/player/attack_right3",gp.tileSize*2, gp.tileSize*3);
        attackRight4 = setup("/player/attack_right4",gp.tileSize*2, gp.tileSize*3);
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
            int objIndex = gp.cCheker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cCheker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cCheker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //CHECK INTERACTIVE TILE COLLISION
            int iTileIndex = gp.cCheker.checkEntity(this, gp.iTile);


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

        if(keyH.enterPressed == true && attackCanceled == false) {
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
                if (spriteNum > 5) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {spriteNum = 1;}

        if(gp.keyH.shotKeyPressed == true && projectile.alive == false &&
                shotAvaliableCounter == 30 && projectile.haveResource(this) == true) {

            //SET DEFAULT COORDINATE, DIRECTION AND USER
            projectile.set(worldX, worldY, direction, true, this);

            //SUBTRACT COST(MANA, LATER ARROWS)
            projectile.subtractResource(this);

            //ADD IT TO THE LIST
            gp.projectileList.add(projectile);

            shotAvaliableCounter = 0;

            gp.playSE(10);
        }

        //OUTSIDE OF KEY IF
        if(invincible == true){
            invicibleCounter++;
            if(invicibleCounter > 60){
                invincible = false;
                invicibleCounter = 0;
            }
        }
        if(shotAvaliableCounter < 30){
            shotAvaliableCounter++;

        }
        if(life > maxLife){
            life = maxLife;
        }
        if(mana > maxMana){
            mana = maxMana;
        }
        if(life <=0){
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            //gp.playMusic(index);// потом добавить музыку
            gp.playSE(12);
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
            damageMonster(monsterIndex, attack);

            int iTileIndex = gp.cCheker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

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

            //PICKUP ONLY ITEMS
            if(gp.obj[gp.currentMap][i].type == type_pickupOnly){

                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }
            //INVENTORY ITEMS
            else{String text;
                if(inventory.size() != maxInventorySize){
                    inventory.add(gp.obj[gp.currentMap][i]);
                    gp.playSE(1);

                    text = "Подобрано: " + gp.obj[gp.currentMap][i].name + "!";
                }
                else{
                    text = "Многовато не потяну!";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;} //DONT FORGET !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }
    }
    public void interactNPC(int i){
        if(gp.keyH.enterPressed == true){
            if(i != 999){
                attackCanceled = true;
                   gp.gameState = gp.dialogueState;
                    gp.npc[gp.currentMap][i].speak();}

        }
    }
    public void contactMonster(int i){
//        if(i != 999 &&i >-0 && i < gp.monster.length){
//            if(gp.monster[i] !=null && invincible == false){
            if( i != 999){
                if(invincible == false && gp.monster[gp.currentMap][i].dying == false){
                gp.playSE(6);
                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if(damage < 0 ) {
                    damage = 0;
                }

                life -= damage;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i, int attack) {
        if (i != 999){// && i >= 0 && i < gp.monster.length) { // Проверка индекса
            if (gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].invincible == false) {
                gp.playSE(5);

                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if(damage < 0 ) {
                    damage = 0;
                }
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage( "Урон: " + damage );
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Убит: " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Опыт + " + gp.monster[gp.currentMap][i].exp + "!");
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void damageInteractiveTile(int i){

        if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true &&
                gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].invincible == false) {

            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;
            //GENERATE PARTICLE
            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);

            if(gp.iTile[gp.currentMap][i].life == 0){
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
            }
            gp.iTile[gp.currentMap][i].playSE();

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
            + "Ты чувствуешь себя сильнее!";

        }
    }
    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentWeapon = selectedItem;
                attack = getAttack();
            }
            if(selectedItem.type == type_shield){
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_consumable){
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
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

        //DEBUG COLLISION
        g2.setColor(Color.RED);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

        //DEBUG invincible
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("invincible" + invicibleCounter, 10, 400);
    }
}
