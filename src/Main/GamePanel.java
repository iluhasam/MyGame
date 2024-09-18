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
//    public final int maxWorldCol = 6;
//    public final int maxWorldRow = 6;


    //FPS

    TileManager tileM = new TileManager(this);
    KeyHabdler keyH = new KeyHabdler();
    //sound
    Sound music = new Sound();
    Sound se = new Sound();

    public CollisionCheker cCheker = new CollisionCheker(this);
    public Assets assets = new Assets(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //entity and obj
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
        playMusic(0);
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

        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //tile
        tileM.draw(g2);
        for( int i = 0; i < obj.length; i++){
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //player
        player.draw(g2);
        //ui
        ui.draw(g2);
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
