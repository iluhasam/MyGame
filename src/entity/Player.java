package entity;

import Main.GamePanel;
import Main.KeyHabdler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHabdler keyH;
    public final int screenX;
    public final int screenY;
    public int standCounter = 0;
    public boolean attackCanceled = false;
    public boolean lightUpdated = false;

    public Player(GamePanel gp, KeyHabdler keyH) {

        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2;
        screenY = gp.screenHeight/2;

        //SOLID AREA
        solidArea = new Rectangle();
        solidArea.x = 6;
        solidArea.y = 32;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        //ATTACK AREA
//      attackArea.width = 64;
//      attackArea.height = 64;

        setDefaultValues();


    }
    public void setDefaultValues() {

        //mapv3

        worldX = gp.tileSize * 21;// - (gp.tileSize/2);
        worldY = gp.tileSize * 21;// - (gp.tileSize/2);

        //map interior
//        worldX = gp.tileSize * 13;//map interior01
//        worldY = gp.tileSize * 12;

        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down";

        //PLAYER STATUS
        level = 1;
        maxLife = 9;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        strength = 1;
        agility = 1;
        exp = 0;
        nextLevelExp = 4;
        coin = 100;
        currentWeapon = new OBJ_Sword_Start(gp);
        //currentWeapon = new OBJ_Axe_Wood(gp);
        currentShield = new OBJ_Shield_Start(gp);
        projectile = new OBJ_Fireball(gp);
        //projectile = new OBJ_PurpleFireball(gp);
        //projectile = new OBJ_Rock(gp);
        attack = getAttack();
        defense = getDefense();
        getPlayerImage();
        getPlayerAttackImage();
        setDialogue();
        setItems();
    }
    public void setDefaultPosition() {

        gp.currentMap = 0;
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";
    }
    public void setDialogue(){

       dialogues [0][0] = "Твой уровень " + level + " сейчас\n"
                + "Ты чувствуешь себя сильнее!";
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
        inventory.add(new OBJ_Pickaxe(gp));
        inventory.add(new OBJ_Axe_Wood(gp));
        inventory.add(new OBJ_Key(gp));


    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = strength * currentShield.defenseValue;
    }
    public int getCurrentWeaponSlot(){
        int currentWeaponSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentWeapon){
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }
    public int getCurrentShieldSlot(){
        int currentShieldSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentShield){
                currentShieldSlot = i;
            }
        }
        return currentShieldSlot;
    }
    public void getPlayerImage(){

        up1 = setup("/res/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/player/boy_up_2",gp.tileSize, gp.tileSize);

        upLeft1 = setup("/res/player/boy_up_1", gp.tileSize, gp.tileSize);
        upLeft2 = setup("/res/player/boy_up_2",gp.tileSize, gp.tileSize);

        upRight1 = setup("/res/player/boy_up_1", gp.tileSize, gp.tileSize);
        upRight2 = setup("/res/player/boy_up_2",gp.tileSize, gp.tileSize);

        down1 = setup("/res/player/boy_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/res/player/boy_down_2",gp.tileSize, gp.tileSize);

        downLeft1 = setup("/res/player/boy_down_1",gp.tileSize, gp.tileSize);
        downLeft2 = setup("/res/player/boy_down_2",gp.tileSize, gp.tileSize);

        downRight1 = setup("/res/player/boy_down_1",gp.tileSize, gp.tileSize);
        downRight2 = setup("/res/player/boy_down_2",gp.tileSize, gp.tileSize);

        left1 = setup("/res/player/boy_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("/res/player/boy_left_2",gp.tileSize, gp.tileSize);

        right1 = setup("/res/player/boy_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("/res/player/boy_right_2",gp.tileSize, gp.tileSize);

    }
    public void getSleepingImage(BufferedImage image){
        up1 =image;
        up2 = image;

        down1 = image;
        down2 = image;

        left1 = image;
        left2 = image;

        right1 = image;
        right2 = image;

    }
    public void getPlayerAttackImage(){

        if(currentWeapon.type == type_sword){
            attackUp1 = setup("/player/boy_attack_up_1",gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/player/boy_attack_up_2",gp.tileSize, gp.tileSize*2);

            attackDown1 = setup("/player/boy_attack_down_1",gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/player/boy_attack_down_2",gp.tileSize, gp.tileSize*2);


            attackLeft1 = setup("/player/boy_attack_left_1",gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/player/boy_attack_left_2",gp.tileSize*2, gp.tileSize);

            attackRight1 = setup("/player/boy_attack_right_1",gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/player/boy_attack_right_2",gp.tileSize*2, gp.tileSize);

        }
        if(currentWeapon.type == type_axe){

            attackUp1 = setup("/player/boy_axe_up_1",gp.tileSize, gp.tileSize*2);
            attackUp1 = setup("/player/boy_axe_up_2",gp.tileSize, gp.tileSize*2);

            attackDown1 = setup("/player/boy_axe_down_1",gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/player/boy_axe_down_2",gp.tileSize, gp.tileSize*2);

            attackLeft1 = setup("/player/boy_axe_left_1",gp.tileSize*2, gp.tileSize);
            attackLeft1 = setup("/player/boy_axe_left_2",gp.tileSize*2, gp.tileSize);

            attackRight1 = setup("/player/boy_axe_right_1",gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/player/boy_axe_right_2",gp.tileSize*2, gp.tileSize);
        }
        if(currentWeapon.type == type_pickaxe){

            attackUp1 = setup("/player/boy_pick_up_1",gp.tileSize, gp.tileSize*2);
            attackUp1 = setup("/player/boy_pick_up_2",gp.tileSize, gp.tileSize*2);

            attackDown1 = setup("/player/boy_pick_down_1",gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/player/boy_pick_down_2",gp.tileSize, gp.tileSize*2);

            attackLeft1 = setup("/player/boy_pick_left_1",gp.tileSize*2, gp.tileSize);
            attackLeft1 = setup("/player/boy_pick_left_2",gp.tileSize*2, gp.tileSize);

            attackRight1 = setup("/player/boy_pick_right_1",gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/player/boy_pick_right_2",gp.tileSize*2, gp.tileSize);
        }
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


            // Диагональные направления
//            if (keyH.upPressed && keyH.leftPressed) {
//                direction = "upLeft";
//            } else if (keyH.upPressed && keyH.rightPressed) {
//                direction = "upRight";
//            } else if (keyH.downPressed && keyH.leftPressed) {
//                direction = "downLeft";
//            } else if (keyH.downPressed && keyH.rightPressed) {
//                direction = "downRight";
//            }
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

//                    case "upLeft":
//                        worldY -= speed / Math.sqrt(2);
//                        worldX -= speed / Math.sqrt(2);
//                        break;
//                    case "upRight":
//                        worldY -= speed / Math.sqrt(2);
//                        worldX += speed / Math.sqrt(2);
//                        break;
//                    case "downLeft":
//                        worldY += speed / Math.sqrt(1.7);
//                        worldX -= speed / Math.sqrt(1.7);
//                        break;
//                    case "downRight":
//                        worldY += speed / Math.sqrt(1.7);
//                        worldX += speed / Math.sqrt(1.7);
//                        break;
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
                if (spriteNum > 2) {
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

            //CHECK PROJECTILE
            for(int i = 0; i < gp.projectile[1].length; i++){
                if(gp.projectile[gp.currentMap][i] == null){
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }

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
        if(keyH.godModeOn == false){
            if(life <=0){
                gp.gameState = gp.gameOverState;
                gp.ui.commandNum = -1;
                gp.stopMusic();
                //gp.playMusic(index);// потом добавить музыку
                gp.playSE(12);
            }
        }


    }
    public void attacking(){
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        } else if (spriteCounter >5 && spriteCounter <= 25) {
            spriteNum = 2;

            //Save the current worldX, worldY solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidht = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust player's worldX/Y for the attackArea
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            //attackArea become solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gp.cCheker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

            int iTileIndex = gp.cCheker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            int projectileIndex = gp.cCheker.checkEntity(this, gp.projectile);
            damageProjectile(projectileIndex);

            //After checking collision
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidht;
            solidArea.height = solidAreaHeight;

        } else if (spriteCounter <= 45) {
            //SPEED ANIMATION ATTACKING
            spriteNum = 2;
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
            //OBSTACLE
            else if(gp.obj[gp.currentMap][i].type == type_obstacle){
                if(keyH.enterPressed == true){
                    attackCanceled = true;
                    gp.obj[gp.currentMap][i].interact();
                }
            }
            //INVENTORY ITEMS
            else{
                String text;
                if(canObtainItem(gp.obj[gp.currentMap][i]) == true){

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

        if(i != 999){
        if(gp.keyH.enterPressed == true) {
                attackCanceled = true;
                gp.npc[gp.currentMap][i].speak();}

          //  gp.npc[gp.currentMap][i].move(direction);
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
    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower ) {
        if (i != 999){// && i >= 0 && i < gp.monster.length) { // Проверка индекса
            if (gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].invincible == false) {
                gp.playSE(5);

                if(knockBackPower > 0){
                    setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);
                }

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
                //gp.iTile[gp.currentMap][i].checkDrop();
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
            }
            //gp.iTile[gp.currentMap][i].playSE();

        }
    }
    public void damageProjectile(int i){

        if(i != 999){
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
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
            dialogues [0][0] = "Твой уровень " + level + " сейчас \n"
                    + "Ты чувствуешь себя сильнее!";
            setDialogue();

            startDialogue(this,0);
        }
    }
    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_sword || selectedItem.type == type_axe || selectedItem.type == type_pickaxe){
                currentWeapon = selectedItem;
                attack = getAttack();
            }
            if(selectedItem.type == type_shield){
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_light){
                if(currentLight == selectedItem){
                    currentLight = null;
                }
                else{
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
            if(selectedItem.type == type_consumable){

                if(selectedItem.use(this) == true){
                    if(selectedItem.amount > 1){
                        selectedItem.amount--;
                    }
                    else{
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }
    public int searchItemInInventory(String itemName){

        int itemIndex = 999;

        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).name.equals(itemName)){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }
    public boolean canObtainItem(Entity item){

        boolean canObtain = false;

        //CHECK IF STACKABLE
        if(item.stackable == true){
            int index = searchItemInInventory(item.name);

            if(index != 999){
                inventory.get(index).amount++;
                canObtain = true;
            }
            else{ //new item need to check vacancy
                if(inventory.size() != maxInventorySize){
                    inventory.add(item);
                    canObtain = true;
                }

            }
        }
        else {
            //NOT STACKABLE  SO CHECK VACANCY
            if(inventory.size() != maxInventorySize){
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
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
                    if(spriteNum == 1) {image = up1;}
                    if(spriteNum == 2) {image = up2;}

                }
                if(attacking == true){
                    tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 1) {image = attackUp1;}
                    if(spriteNum == 2) {image = attackUp2;}
                }
                break;
            case "down":
                if(attacking == false){
                    if(spriteNum == 1){image = down1;}
                    if(spriteNum == 2) {image = down2;}

                }
                if(attacking == true){
                    if(spriteNum == 1) {image = attackDown1;}
                    if(spriteNum == 2) {image = attackDown2;}


                }
                break;
            case "left":
                if(attacking == false){
                    if(spriteNum == 1) {image = left1;}
                    if(spriteNum == 2) {image = left2;}

                }
                if(attacking == true){
                    tempScreenX = screenX - gp.tileSize;

                    if(spriteNum == 1) {image = attackLeft1;}
                    if(spriteNum == 2) {image = attackLeft2;}

                }
                break;
            case "right":
                if(attacking == false){
                    if(spriteNum == 1) {image = right1;}
                    if(spriteNum == 2) {image = right2;}

                }
                if(attacking == true){
                    if(spriteNum == 1) {image = attackRight1;}
                    if(spriteNum == 2) {image = attackRight2;}

                }
                break;
            // Добавление диагональных направлений
            case "upLeft":
                if (attacking == false) {
                    if (spriteNum == 1) { image = upLeft1; }
                    if (spriteNum == 2) { image = upLeft2; }
                }
                if (attacking == true) {
                   // tempScreenX = screenX - gp.tileSize; // Уменьшаем X для диагональной атаки
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) { image = attackUp1; }
                    if (spriteNum == 2) { image = attackUp2; }
                }
                break;

            case "upRight":
                if (attacking == false) {
                    if (spriteNum == 1) { image = upRight1; }
                    if (spriteNum == 2) { image = upRight2; }
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    //tempScreenX = screenX + gp.tileSize; // Увеличиваем X для диагональной атаки
                    if (spriteNum == 1) { image = attackUp1; }
                    if (spriteNum == 2) { image = attackUp2; }
                }
                break;

            case "downLeft":
                if (attacking == false) {
                    if (spriteNum == 1) { image = downLeft1; }
                    if (spriteNum == 2) { image = downLeft2; }
                }
                if (attacking == true) {
//                    tempScreenY = screenY + gp.tileSize; // Увеличиваем Y для диагональной атаки
//                    tempScreenX = screenX - gp.tileSize; // Уменьшаем X для диагональной атаки
                    if (spriteNum == 1) { image = attackDown1; }
                    if (spriteNum == 2) { image = attackDown2; }
                }
                break;

            case "downRight":
                if (attacking == false) {
                    if (spriteNum == 1) { image = downRight1; }
                    if (spriteNum == 2) { image = downRight2; }
                }
                if (attacking == true) {
                   // tempScreenY = screenY + gp.tileSize; // Увеличиваем Y для диагональной атаки
                   // tempScreenX = screenX + gp.tileSize; // Увеличиваем X для диагональной атаки
                    if (spriteNum == 1) { image = attackDown1; }
                    if (spriteNum == 2) { image = attackDown2; }
                }
                break;
        }


        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        }
        if(drawing == true){
            g2.drawImage(image, tempScreenX, tempScreenY, null);

        }

        //RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

//        //DEBUG COLLISION
//        g2.setColor(Color.RED);
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

        //DEBUG invincible
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("invincible" + invicibleCounter, 10, 400);
    }
}
