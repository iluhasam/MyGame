package tile_interactive;

import Main.GamePanel;
import entity.Entity;

public class InteractiveTile extends Entity {
    GamePanel gp;
    public boolean destructible = false;
    public InteractiveTile (GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;


    }
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        return isCorrectItem;
    }
    public void playSE(){

    }
    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = null;
        return tile;
    }
    public void update() {
        if(invincible == true){
            invicibleCounter++;
            if(invicibleCounter > 20){
                invincible = false;
                invicibleCounter = 0;
            }
        }
    }
}
