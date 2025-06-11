package entity;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update(){
        if (keyH.upPressed){
            y -= speed;
        }
        else if (keyH.downPressed){
            y += speed;
        }
        else if (keyH.leftPressed){
            x -= speed;
        }
        else if (keyH.rightPressed){
            x += speed;
        }
    }

    public void draw(Graphics2D g2){

        g2.setColor(Color.WHITE);  // Sets a color to use for drawing objects.

        g2.fillRect(x, y, gp.tileSize, gp.tileSize); //Draw a rectangle and fills it with the specified color.
    }
}
