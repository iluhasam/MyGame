package tile;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileManager {

    GamePanel gp;
    public Tile[]tile;
    public int mapTileNum[][][];
    boolean drawPath = true;
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(GamePanel gp) {
        this.gp = gp;

        //READ TILE DATA FILE
        InputStream is = getClass().getResourceAsStream("/maps/tiledata.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //GETTING TILE NAMES AND COLLISION INFO FROM FILE
        String line;

            try {
                while( (line = br.readLine()) != null) {
                    fileNames.add(line);
                    collisionStatus.add(br.readLine());
                }
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        //INITIALIZE THE TILE ARRAY BASED ON THE filenames size
        tile = new Tile[fileNames.size()];
        getTileImage();

        //get maxWorldCol && Row
        is = getClass().getResourceAsStream("/maps/worldmap.txt");
        br = new BufferedReader(new InputStreamReader(is));

        try{
            String line2 = br.readLine();
            String maxTile[] = line2.split(" ");

            gp.maxWorldCol = maxTile.length;
            gp.maxWorldRow = maxTile.length;
            mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

            br.close();

        }catch(IOException e) {
            System.out.println("Exception");
        }

        //loadMap("/maps/sample.txt",0);
    loadMap("/maps/worldmap.txt",0);
    loadMap("/maps/indoor01.txt",1);
    loadMap("/maps/dungeon01.txt",2);
    loadMap("/maps/dungeon02.txt",3);
    loadMap("/maps/worldmap2.txt",4);
    loadMap("/maps/maphouse01.txt",5);
    loadMap("/maps/cave.txt",6);
    loadMap("/maps/witchmap.txt", 7);
    loadMap("/maps/dung.txt", 8);
    }
    public void getTileImage(){

        for(int i = 0; i < fileNames.size(); i++){

            String fileName;
            boolean collision;

            //Get fileName
            fileName = fileNames.get(i);

            //get a collision status
            if(collisionStatus.get(i).equals("true")){
                collision = true;
            }
            else {
                collision = false;
            }

            setup(i, fileName, collision);
        }
    }
    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collission = collision;


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void setup1(int index, String imageName, boolean collision, int width, int height){
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collission = collision;


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath,int map){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){

        }
    }

    public void draw(Graphics2D g2){
    int worldCol = 0;
    int worldRow = 0;

    while(worldCol <  gp.maxWorldCol && worldRow <  gp.maxWorldRow){

        int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

        int worldX = worldCol * gp.tileSize;
        int worldY = worldRow * gp.tileSize;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

       // прортсовка карты
        if(worldX > gp.player.screenX - gp.player.screenX &&
           worldX < gp.player.screenX + gp.player.screenX &&
           worldY > gp.player.screenY - gp.player.screenY &&
           worldY < gp.player.screenY + gp.player.screenY){

            g2.drawImage(tile[tileNum].image,screenX,screenY,null);
        }
        // из-за этой гниды накрывается весь визуал(НЕ ТРОГАТЬ!!!)
        g2.drawImage(tile[tileNum].image,screenX,screenY,null);
        worldCol++;

        if(worldCol == gp.maxWorldCol){
            worldCol = 0;

            worldRow++;
        }

    }
    }
}
