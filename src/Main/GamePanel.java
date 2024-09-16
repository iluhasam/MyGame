package Main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private static final int FPS = 60;
    //Настройки экрана
    final int originalTileSize = 16;  // плитка 22x22
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;// 66x66 плитка
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol;  //1792px
    public final int screenHeight = tileSize * maxScreenRow;    // 1064 px

    //Nastroyki world
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS

    TileManager tileM = new TileManager(this);
    KeyHabdler keyH = new KeyHabdler();
    Thread gameThread;
    public CollisionCheker cCheker = new CollisionCheker(this);
    public Assets assets = new Assets(this);
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10];



    // set res/player default position
//    int x = 100;
//    int y = 100;
//    int speed = 10;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame(){
        assets.setObject();
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
                System.out.println("FPS:" + drawCount);
                timer = 0;
                drawCount = 0;
            }
        }
    }

    public void update(){

        player.update();

//        if(keyH.upPressed == true) {
//            playerY -= playerSpeed;
//            playerY = playerY - playerSpeed;
//        }
//        else if(keyH.downPressed == true) {
//            playerY += playerSpeed;
//        }
//        else if(keyH.leftPressed == true) {
//            playerX -= playerSpeed;
//        }
//        else if(keyH.rightPressed == true) {
//            playerX += playerSpeed;
//        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        player.draw(g2);

//        g2d.setColor(Color.WHITE); белый кв
//        g2d.fillRect(playerX, playerY, tileSize, tileSize );
        g2.dispose();
    }
}
