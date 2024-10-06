package data;

import Main.GamePanel;
import entity.Entity;
import object.*;

import java.io.*;

public class SaveLoad {

    GamePanel gp;

    public SaveLoad(GamePanel gp) {

        this.gp = gp;
    }
    public Entity getObject(String itemName){

        Entity obj = null;

        switch(itemName){
            case "Деревянный топор": obj = new OBJ_Axe_Wood(gp); break;
            case "boots": obj = new OBJ_Boots(gp); break;
            case "Ключ": obj = new OBJ_Key(gp); break;
            case "Фонарь": obj = new OBJ_Lantern(gp); break;
            case "Фласка здоровья": obj = new OBJ_Potion_Red(gp); break;
            case "Деревянный щит": obj = new OBJ_Shield_Start(gp); break;
            case "Палка": obj = new OBJ_Stick(gp); break;
            case "Обычный меч": obj = new OBJ_Sword_Start(gp); break;
            case "Палатка": obj = new OBJ_Tent(gp); break;
            case "door": obj = new OBJ_Door(gp); break;
            case "chest": obj = new OBJ_Chest(gp); break;
        }
        return obj;
    }
    public void save() {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            //Player STATS
            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.ammo = gp.player.ammo;
            ds.strength = gp.player.strength;
            ds.agility = gp.player.agility;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;

            //PLAYER INVENTORY
            for(int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }
            //PLAYER EQUIPMENT
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();

            //OBJECT ON MAP
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int [gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int [gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootNames = new String [gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];

            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for(int i = 0; i < gp.obj[1].length; i++) {
                    if(gp.obj[mapNum][i] == null) {
                        ds.mapObjectNames[mapNum][i] = "NA";

                    }
                    else {
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
                        if(gp.obj[mapNum][i].loot != null) {
                            ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
                    }
                }
            }


            //написать Объекты
            oos.writeObject(ds);
        }
        catch(Exception e){
            System.out.println("Save Exception");
        }
    }
    public void load() {
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            //Прочитать объект
            DataStorage ds = (DataStorage) ois.readObject();

            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxMana = ds.maxMana;
            gp.player.mana = ds.mana;
            gp.player.ammo = ds.ammo;
            gp.player.strength = ds.strength;
            gp.player.agility = ds.agility;
            gp.player.exp = ds.exp;
            gp.player.coin = ds.coin;
            gp.player.nextLevelExp = ds.nextLevelExp;

            //PLAYER INVENTORY
            gp.player.inventory.clear();
            for(int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }
            //PLAYER EQUIPMENT
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getPlayerAttackImage();

            //OBJECT ON MAP
            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {

                for(int i = 0; i < gp.obj[1].length; i++) {

                    if(ds.mapObjectNames[mapNum][i].equals("NA")) {
                        gp.obj[mapNum][i] = null;
                    }
                    else {
                        gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if(ds.mapObjectLootNames[mapNum][i] != null) {
                            gp.obj[mapNum][i].loot = getObject(ds.mapObjectLootNames[mapNum][i]);
                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if(gp.obj[mapNum][i].opened == true) {
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                        }
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println("Load Exception");
        }
    }
}
