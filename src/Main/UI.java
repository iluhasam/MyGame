package Main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameOver = false;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics g2) {

        if (gameOver == true) {

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLenght;
            int x;
            int y;

            text = "Ты нашёл сундук!!! ";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);

            text = "Легенда!!! ";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);

            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString(" = " + gp.player.hasKey, 74, 50);

            //message
            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, gp.tileSize / 3, gp.tileSize * 5);

                messageCounter++;

                if (messageCounter > 130) {
                    messageCounter = 0;
                    messageOn = false;
                }

            }
        }
    }
}
