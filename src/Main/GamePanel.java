package Main;

import ai.PathFinder;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    private static final int FPS = 60;
    //Настройки экрана
    final int originalTileSize = 16;  // плитка 22x22
    final int scale = 4;

    public final int tileSize = originalTileSize * scale;// 66x66 плитка
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;  //1792px
    public final int screenHeight = tileSize * maxScreenRow;    // 1064 px

    //Settings world
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 20;
    public int currentMap = 0;


    //FOR FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    //SYSTEM
    public TileManager tileM = new TileManager(this);
    public KeyHabdler keyH = new KeyHabdler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionCheker cCheker = new CollisionCheker(this);
    public Assets assets = new Assets(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    //Config config = new Config(this);
    public PathFinder pFinder = new PathFinder(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    Thread gameThread;

    //ENTITY AND OBJ
    public Player player = new Player(this,keyH);
    public Entity obj[][] = new Entity[maxMap][50];
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster[][] = new Entity[maxMap][20];
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][100];
    public Entity projectile[][] = new Entity[maxMap][20];
    //public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int tradeState = 7;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame(){

        assets.setObject();
        assets.setNPC();
        assets.setMonster();
        assets.setInteractiveTile();
        eManager.setup();

        //playMusic(0);
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight,BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        setFullscreen();
    }

    public void retry(){
        player.setDefaultPosition();
        player.restoreLifeAndMana();
        assets.setNPC();
        assets.setMonster();
    }

    public void restart(){
        player.setDefaultValues();
        player.setDefaultPosition();
        player.restoreLifeAndMana();
        player.setItems();
        assets.setNPC();
        assets.setMonster();
        assets.setInteractiveTile();
        assets.setObject();
    }

    public void setFullscreen(){
        //GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        //gd.setFullScreenWindow(Main.window);

        //GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }
    public void startGameTread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long timer = 0;
        long drawCount = 0;
        long currentTime;

        while(gameThread !=null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                //1: обновление информации позиции непися
                update();
                //2: нарисовать экран с информацией об обновлении
                drawToTempScreen();// draw everything to buf image
                drawToScreen();// draw buf image to screen
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {

                timer = 0;
                drawCount = 0;
            }
        }
    }

    public void update() {

        if(gameState == playState) {
            //player
            player.update();
            //npc
            for (int i = 0; i < npc[1].length; i++) {
                if(npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if(monster[currentMap][i] != null) {
                    if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false){
                        monster[currentMap][i].update();
                    }
                    if(monster[currentMap][i].alive == false){
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }
            for (int i = 0; i < projectile[1].length; i++) {
                if(projectile[currentMap][i] != null) {
                    if(projectile[currentMap][i].alive == true){
                        projectile[currentMap][i].update();
                    }
                    if(projectile[currentMap][i] .alive == false){
                        projectile[currentMap][i] = null;
                    }
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if(particleList.get(i) != null) {
                    if(particleList.get(i).alive == true){
                        particleList.get(i).update();
                    }
                    if(particleList.get(i).alive == false){
                        particleList.remove(i);
                    }
                }
            }
            for(int i = 0; i < iTile[1].length; i++) {
                if(iTile[currentMap][i] != null) {
                    iTile[currentMap][i].update();
                }
            }
            eManager.update();
        }
        if(gameState == pauseState) {
            //nothing
        }

    }
    public void drawToTempScreen() {
        //DEBUG
        long drawStart = 0;
        if(keyH.showDebugConsole == true){
            drawStart = System.nanoTime();
        }

        //titleScreen
        if(gameState == titleState){
            ui.draw(g2);
        }
        //others
        else {
            //tile
            tileM.draw(g2);

            //INTERACTIVE TILE
            for(int i = 0; i < iTile[1].length; i++) {
                if(iTile[currentMap][i] != null){
                    iTile[currentMap][i].draw(g2);
                }
            }

            //ADD ENTITIES TO THE LIST
            entityList.add(player);

            for(int i = 0; i < npc[1].length; i++) {
                if(npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for(int i = 0; i < obj[1].length; i++) {
                if(obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            for(int i = 0; i < monster[1].length; i++) {
                if(monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for(int i = 0; i < projectile[1].length; i++) {
                if(projectile[currentMap][i] != null) {
                    entityList.add(projectile[currentMap][i]);
                }
            }

            for(int i = 0; i < particleList.size(); i++) {
                if(particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY,e2.worldY);
                    return result;
                }
            });

            //DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            //EMPTY ENTITY LIST
            entityList.clear();

            //ENVIRONMENT
            eManager.draw(g2);

            //UI
            ui.draw(g2);

            //DEBUG
            if(keyH.showDebugConsole == true){
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;

                g2.setFont(new Font("Arial", Font.PLAIN, 20));
                g2.setColor(Color.white);
                int x = 10;
                int y = 400;
                int lineHeight = 20;

                g2.drawString("WorldX" + player.worldX, x, y);
                y += lineHeight;
                g2.drawString("WorldY" + player.worldY, x, y);
                y += lineHeight;
                g2.drawString("Col" + (player.worldX + player.solidArea.x)/tileSize, x, y);
                y += lineHeight;
                g2.drawString("Row" + (player.worldY + player.solidArea.y)/tileSize, x, y);
                y += lineHeight;

                g2.drawString("Время отрисовки " + passed, x,y);
                System.out.println("Время отрисоки " + passed);
            }
        }
    }
    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2,  null);
        g.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
        se.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
