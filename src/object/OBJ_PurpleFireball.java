package object;

import Main.GamePanel;
import entity.Entity;
import entity.Projectile;

import java.awt.*;

public class OBJ_PurpleFireball extends Projectile {

    GamePanel gp;

    public OBJ_PurpleFireball(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Молния";
        speed = 10;
        maxLife = 60;
        life = maxLife;
        attack = 5;
        useCost = 1;
        knockBackPower = 0;
        alive = false;
        getImage();
    }

    public void getImage(){
        up1 = setup("/projectile/purplefireball/purplefireball5", gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/purplefireball/purplefireball6", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/purplefireball/purplefireball5", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/purplefireball/purplefireball6", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/purplefireball/purplefireball5", gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/purplefireball/purplefireball6", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/purplefireball/purplefireball5", gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/purplefireball/purplefireball6", gp.tileSize, gp.tileSize);
    }
    public boolean haveResource (Entity user) {
        boolean haveResource  = false;
        if(user.mana >= useCost){
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource (Entity user) {
        user.mana -= useCost;
    }
    public Color getParticleColor() {
        Color color = new Color(151,39,228);
        return color;
    }
    public int getParticleSize() {
        int size = 10; // 6 pixels
        return size;
    }
    public int getParticleSpeed(){
        int speed = 2;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    }
}

