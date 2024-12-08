package Main;

import javax.swing.*;

public class Main {
    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Boy RPG");
        new Main().setIcon();
        //window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        if(gamePanel.fullScreenOn == true){
            window.setUndecorated(true);
        }
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameTread();
        
    }
    public void setIcon(){

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("player/boy_down_2.png"));
        window.setIconImage(icon.getImage());
    }
}