package Main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    private static final int FPS = 60;
    //Настройки экрана
    final int originalTileSize = 32;  // плитка 22x22
    final int scale = 2;

    public final int tileSize = originalTileSize * scale;// 66x66 плитка
    public final int maxScreenCol = 32;
    public final int maxScreenRow = 15;
    public final int screenWidth = tileSize * maxScreenCol;  //1792px
    public final int screenHeight = tileSize * maxScreenRow;    // 1064 px

    //Settings world
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;


    //TILE
    TileManager tileM = new TileManager(this);
    public KeyHabdler keyH = new KeyHabdler(this);
    //sound
    Sound music = new Sound();
    Sound se = new Sound();

    public CollisionCheker cCheker = new CollisionCheker(this);
    public Assets assets = new Assets(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    //entity and obj
    public Player player = new Player(this,keyH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();

    //game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

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
        //playMusic(0);

        gameState = titleState;
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
                repaint();
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
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if(gameState == pauseState) {
            //nothing
        }

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime ==true){
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

            //ADD ENTITIES TO THE LIST
            entityList.add(player);

            for(int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }

            for(int i = 0; i < obj.length; i++) {
                if(obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
            //UI
            ui.draw(g2);

            //DEBUG
            if(keyH.checkDrawTime ==true){
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor(Color.white);
                g2.drawString("Время отрисовки " + passed, 10,400);
                System.out.println("Время отрисоки " + passed);
            }
        }
        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        se.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();

    }
}
