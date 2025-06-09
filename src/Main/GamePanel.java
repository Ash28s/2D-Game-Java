package Main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();

    Thread gameThread;

    //Set Player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
            //SLEEP Method (For FPS)
            double drawInterval = 1000000000 / FPS; //0.016666 seconds
            double nextDrawTime = System.nanoTime() + drawInterval;
            while(gameThread !=null) {
                //// 1. UPDATE :- Update information such as character position.
                update();
                //// 2. DRAW :- Draw the screen with the updated information.
                repaint();
                try {
                    double remainingTime = nextDrawTime - System.nanoTime();
                    remainingTime = remainingTime / 1000000;
                    if (remainingTime < 0) {
                        remainingTime = 0;
                    }
                    Thread.sleep((long)remainingTime);
                    nextDrawTime += drawInterval;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

    }

    public void update(){
        if (keyH.upPressed){
            playerY -= playerSpeed;
        }
        else if (keyH.downPressed){
            playerY += playerSpeed;
        }
        else if (keyH.leftPressed){
            playerX -= playerSpeed;
        }
        else if (keyH.rightPressed){
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {            // Graphic Class:- A class that has many functions to draw objects on the screen.
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; //Graphics2D class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations,color management, and text layout.

        g2.setColor(Color.WHITE);  // Sets a color to use for drawing objects.

        g2.fillRect(playerX, playerY, tileSize, tileSize); //Draw a rectangle and fills it with the specified color.

        g2.dispose();  //Dispose of this graphics context and release any system resources that it is using.


    }
}
