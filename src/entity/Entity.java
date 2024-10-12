package entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Entity {
    GamePanel gp;

    public BufferedImage  up1, up2, up3,up4,
            down1, down2, down3,down4,
             left1, left2, left3, left4,
            right, right1, right2, right3, right4;
    public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4,
            attackDown1, attackDown2, attackDown3, attackDown4,
            attackLeft1, attackLeft2, attackLeft3, attackLeft4,
            attackRight1, attackRight2, attackRight3, attackRight4;
    public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0 );
    public BufferedImage image, image2, image3, image4;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public String[][] dialogues = new String[50][50];

    public Entity attacker;
    public Entity linkedEntity;

    //STATE
    public int worldX,worldY;
    public String direction = "down";
    public int spriteNum = 1;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean collision = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDiraction;
    public Entity loot;
    public boolean opened = false;
    public boolean inRage = false;

    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invicibleCounter = 0;
    public int shotAvaliableCounter = 0;
    int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int knockBackCounter = 0;

    //CHARACTER ATTIBUTES
    public int speed;
    public int defaultSpeed;
    public String name;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int agility;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentArmor;
    public Entity currentHelment;
    public Entity currentLight;
    public Entity currentBoots;
    public Projectile projectile;
    public boolean boss;


    //ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = " ";
    public int useCost;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;

    //TYPE
    public int type;  // 0 = player, 1 = npc, 2 = monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;
    public final int type_pickaxe = 10;



    public Entity (GamePanel gp){
        this.gp = gp;
    }
    public int getScreenX(){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        return screenX;
    }
    public int getScreenY(){
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        return screenY;
    }
    public int getLeftX(){
        return worldX + solidArea.x;
    }
    public int getRightX(){
        return worldX + solidArea.x + solidArea.width;
    }
    public int getTopY(){
        return worldY + solidArea.y;
    }
    public int getBottomY(){
        return worldY + solidArea.y + solidArea.height;
    }
    public int getCol(){
        return (worldX + solidArea.x)/gp.tileSize;
    }
    public int getRow(){
        return (worldY + solidArea.y)/gp.tileSize;
    }
    public int getCenterX(){
        int centerX = worldX + left1.getWidth()/2;
        return centerX;
    }
    public int getCenterY(){
        int centerY = worldY + up1.getHeight()/2;
        return centerY;
    }
    public int getXdistance(Entity target){
        int xDistance = Math.abs(getCenterX() - target.getCenterX());
        return xDistance;
    }
    public int getYdistance(Entity target){
        int yDistance = Math.abs(getCenterY() - target.getCenterY());
        return yDistance;
    }
    public int getTileDistance(Entity target){
        int tileDistance = (getXdistance(target) + getYdistance(target)) / gp.tileSize;
        return tileDistance;
    }
    public int getGoalCol(Entity target){
        int goalCol = (target.worldX + target.solidArea.x)/gp.tileSize;
        return goalCol;
    }
    public int getGoalRow(Entity target){
        int goalRow = (target.worldY + target.solidArea.y)/gp.tileSize;
        return goalRow;
    }
    public void setLoot(Entity loot){}
    public void setAction(){}
    public void move(String direction){

    }
    public void damageReaction(){}
    public void speak(){}
    public void facePlayer(){

        switch (gp.player.direction){
            case "up":direction = "down";break;
            case "down":direction = "up";break;
            case "left":direction = "right";break;
            case "right":direction = "left";break;
        }
    }
    public void startDialogue(Entity entity, int setNum){

        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }
    public void interact(){}
    public boolean use(Entity entity){
        return false;
    }
    public void checkDrop(){}
    public void dropItem(Entity droppedItem){
        for(int i = 0; i < gp.obj[1].length; i++){
            if(gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX ;// COORDINATES DEAD MONSTER'S worldX
                gp.obj[gp.currentMap][i].worldY = worldY ;
                break;
            }
        }
    }
    public Color getParticleColor() {
        Color color = null;
        return color;
    }
    public int getParticleSize() {
        int size = 0; // 6 pixels
        return size;
    }
    public int getParticleSpeed(){
        int speed = 0;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 0;
        return maxLife;
    }
    public void generateParticle(Entity generator, Entity target){

        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife,  -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife,  2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife,  -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife,  2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }
    public void checkCollision(){
        collisionOn = false;
        gp.cCheker.checkTile(this);
        gp.cCheker.checkObject(this,false);
        gp.cCheker.checkEntity(this, gp.npc);
        gp.cCheker.checkEntity(this, gp.monster);
        gp.cCheker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cCheker.checkPlayer(this);

        if(this.type == type_monster && contactPlayer == true){
            damagePlayer(attack);
        }
    }
    public void update(){

        if(knockBack == true){

            checkCollision();

            if(collisionOn == true){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            } else if (collisionOn == false) {
                switch (knockBackDiraction){
                    case "up":worldY -= speed;break;
                    case "down":worldY += speed;break;
                    case "left":worldX -= speed;break;
                    case "right":worldX += speed;break;
                }
            }

            knockBackCounter++;
            if(knockBackCounter == 10){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }
        else if(attacking == true){
            attacking();
        }
        else {
            setAction();
            checkCollision();

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


        if(invincible == true){
            invicibleCounter++;
            if(invicibleCounter > 40){
                invincible = false;
                invicibleCounter = 0;
            }
        }
        if(shotAvaliableCounter < 30){
            shotAvaliableCounter++;
        }

    }
    public void checkAttackOrNot(int rate, int straight, int horizontal){

        boolean targetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);

        switch(direction){
            case "up":
                if(gp.player.getCenterY() < getCenterY()  && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "down":
                if(gp.player.getCenterY()  > getCenterY()  && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "left":
                if(gp.player.getCenterX()  < getCenterX()  && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "right":
                if(gp.player.getCenterX()  > getCenterX()  && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
        }

        if(targetInRange == true){
            //Проверка атаки
            int i = new Random().nextInt(rate);
            if(i == 0){
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvaliableCounter = 0;
            }
        }
    }
    public void checkShootOrNot(int rate, int shotInterval){

        int i = new Random().nextInt(rate);
        if(i == 0 && projectile.alive == false && shotAvaliableCounter == shotInterval){
            projectile.set(worldX,worldY, direction, true, this);
            //gp.projectileList.add(projectile);
            //CHECK PROJECTILE
            for(int ii = 0; ii < gp.projectile[1].length; ii++){
                if(gp.projectile[gp.currentMap][ii] == null){
                    gp.projectile[gp.currentMap][ii] = projectile;
                    break;
                }
            }
            shotAvaliableCounter = 0;
        }
    }
    public void checkStartChasingOrNot(Entity target, int distance, int rate){

        if(getTileDistance(target) < distance){
            int i = new Random().nextInt(rate);
            if( i == 0){
                onPath = true;
            }
        }
    }
    public void checkStopChasingOrNot(Entity target, int distance, int rate){

        if(getTileDistance(target) > distance){
            int i = new Random().nextInt(rate);
            if( i == 0){
                onPath = false;
            }
        }
    }
    public void getRandomDirection(int interval){
        //Рандомное направление
        actionLockCounter++;

        if(actionLockCounter > interval){
            Random random = new Random();
            int i = random.nextInt(100)+1; // 1 to 100(not 0 to 99)

            if(i <= 25){direction = "up";}
            if(i > 25 && i <= 50){direction = "down";}
            if(i > 50 && i <= 75 ){direction = "left";}
            if(i > 75 && i <= 100) {direction = "right";}
            actionLockCounter = 0;
        }
    }
    public void moveTowardPlayer(int interval){

        actionLockCounter++;
        if(actionLockCounter > interval){

            if(getXdistance(gp.player) > getYdistance(gp.player)){

                if(gp.player.getCenterX() < getCenterX()){
                    direction = "left";
                }
                else {direction = "right";}
            }
            else if(getXdistance(gp.player) < getYdistance(gp.player)){

                if(gp.player.getCenterY() < getCenterY()){
                    direction = "up";
                }
                else {direction = "down";}
            }
            actionLockCounter = 0;
        }


    }
    public void attacking() {

        spriteCounter++;

        if (spriteCounter <= motion1_duration) {
            spriteNum = 1;
        } else if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
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

            if (type == type_monster) {
                if (gp.cCheker.checkPlayer(this) == true) {
                    damagePlayer(attack);
                }
            } else {

                //PLAYER
                //Check monster collision with the updated worldX, worldY and solidArea
                int monsterIndex = gp.cCheker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                int iTileIndex = gp.cCheker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cCheker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }

            //After checking collision
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidht;
            solidArea.height = solidAreaHeight;

            //SPEED ANIMATION ATTACKING
//        } else if (spriteCounter > 40 && spriteCounter <= 60) {
//            spriteNum = 1;
//        } else if (spriteCounter > 60 && spriteCounter <= 80) {
//            spriteNum = 2;
        } else if (spriteCounter > motion2_duration) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false; // Сбрасываем атаку
        }
    }
    public void damagePlayer(int attack){

        if(gp.player.invincible == false){
            //we can give damage
            gp.playSE(6);
            int damage = attack - gp.player.defense;
            if(damage < 0 ) {
                damage = 0;
            }
            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }
    public void setKnockBack(Entity target, Entity attacker, int knockBackPower){

        this.attacker = attacker;
        target.knockBackDiraction = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
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
    public boolean inCamera(){
        boolean inCamera = false;
        if (worldX + gp.tileSize * 6> gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize * 6> gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            inCamera = true;
        }
        return inCamera;
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;


        // прорисовка ресов
        if (inCamera() == true) {

            int tempScreenX = getScreenX();
            int tempScreenY = getScreenY();

            switch (direction) {
            case "up":
                if(attacking == false) {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                if(attacking == true) {
                    tempScreenY = getScreenY() - up1.getHeight();
                    if(spriteNum == 1){image = attackUp1;}
                    if(spriteNum == 2){image = attackUp2;}
                }
                break;
            case "down":
                if(attacking == false) {
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                    //if (spriteNum == 3) {image = down3;}
                }
                if(attacking == true) {
                    if(spriteNum == 1){image = attackDown1;}
                    if(spriteNum == 2){image = attackDown2;}
                }
                break;
            case "left":
                if(attacking == false) {
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                    //if (spriteNum == 3) {image = down3;}
                }
                if(attacking == true) {
                    tempScreenX = getScreenX() - left1.getWidth();
                    if(spriteNum == 1){image = attackLeft1;}
                    if(spriteNum == 2){image = attackLeft2;}
                }
                break;
            case "right":
                if(attacking == false) {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                    //if (spriteNum == 3) {image = down3;}
                }
                if(attacking == true) {
                    if(spriteNum == 1){image = attackRight1;}
                    if(spriteNum == 2){image = attackRight2;}
                }
                break;
        }
        // Monster HP bar


            if(invincible == true){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.5f);
            }
            if(dying == true){
                dyingAnimation(g2);
            }
           g2.drawImage(image, tempScreenX, tempScreenY, null);
            changeAlpha(g2, 1f);
        }

        //g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;
        int i =5;

        //ЕСЛИ БУДЕТ АНИМАЦИЯ СПЕРТИ ТО ПРОСТО ПЕРЕКЛЮЧИТЬ dyingCounter
        if(dyingCounter <= i){changeAlpha(g2,0f);}
        if(dyingCounter > i && dyingCounter <= i*2){changeAlpha(g2,1f);}
        if(dyingCounter > i*2 && dyingCounter <= i*3){changeAlpha(g2,0f);}
        if(dyingCounter > i*3 && dyingCounter <= i*4){changeAlpha(g2,1f);}
        if(dyingCounter > i*4 && dyingCounter <= i*5){changeAlpha(g2,0f);}
        if(dyingCounter > i*5 && dyingCounter <= i*6){changeAlpha(g2,1f);}
        if(dyingCounter > i*6 && dyingCounter <= i*7){changeAlpha(g2,0f);}
        if(dyingCounter > i*7 && dyingCounter <= i*8){changeAlpha(g2,1f);}
        if(dyingCounter > i*8 ){
            alive = false;
        }

    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public void searchPath(int goalCol, int goalRow){

        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if(gp.pFinder.search() == true){

            //Next worldX, worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            //Entity solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize){
                //LEFT OR RIGHT
                if(enLeftX > nextX){
                    direction = "left";
                }
                if(enLeftX < nextX){
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX){
                //UP OR LEFT
                direction = "up";
                checkCollision();
                if(collisionOn == true){
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                //UP OR RIGHT
                direction = "up";
                checkCollision();
                if(collisionOn == true){
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX){
                //DOWN OR LEFT
                direction = "down";
                checkCollision();
                if(collisionOn == true){
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX){
                //DOWN OR RIGHT
                direction = "down";
                checkCollision();
                if(collisionOn == true){
                    direction = "right";
                }
            }
            //If reaches the goal, stop search
            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if(nextCol == goalCol && nextRow == goalRow){
                onPath = false;
            }
        }
    }
    public int getDetected(Entity user, Entity target[][], String targetName){

        int index = 999;

        //Проверка окружающих объектов
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction){
            case "up": nextWorldY = user.getTopY()-1;break;
            case "down": nextWorldY = user.getBottomY()+1;break;
            case "left": nextWorldX = user.getLeftX()-1;break;
            case "right": nextWorldX = user.getRightX()+1;break;
        }
        int col = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;

        for(int i = 0; i < target[1].length; i++){
            if(target[gp.currentMap][i] != null){
                if(target[gp.currentMap][i].getCol() == col
                        && target[gp.currentMap][i].getRow() == row
                            && target[gp.currentMap][i].name.equals(targetName)){

                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}
