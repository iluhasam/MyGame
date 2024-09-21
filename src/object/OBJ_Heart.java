package object;

import Main.GamePanel;

import javax.imageio.ImageIO;

public class OBJ_Heart extends SuperObject{
    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        this.gp = gp;
        name = "heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/life_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/life_1.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/life_2.png"));
            image4 = ImageIO.read(getClass().getResourceAsStream("/objects/life_blank.png"));
            image =  uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
            image4 = uTool.scaleImage(image4, gp.tileSize, gp.tileSize);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
