package Main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();

    Thread gameThread;

    Player player = new Player(this, keyH);


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
//            //SLEEP Method (For FPS)
//            double drawInterval = 1000000000 / FPS; //0.016666 seconds
//            double nextDrawTime = System.nanoTime() + drawInterval;
//            while(gameThread !=null) {
//                //// 1. UPDATE :- Update information such as character position.
//                update();
//                //// 2. DRAW :- Draw the screen with the updated information.
//                repaint();
//                try {
//                    double remainingTime = nextDrawTime - System.nanoTime();
//                    remainingTime = remainingTime / 1000000;
//                    if (remainingTime < 0) {
//                        remainingTime = 0;
//                    }
//                    Thread.sleep((long)remainingTime);
//                    nextDrawTime += drawInterval;
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }

        //Delta/Accumulator Method
        double drawInterval = 1000000000 / FPS; //0.016666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null ) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1) {
                //// 1. UPDATE :- Update information such as character position.
                update();

                //// 2. DRAW :- Draw the screen with the updated information.
                repaint();

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g) {            // Graphic Class:- A class that has many functions to draw objects on the screen.
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; //Graphics2D class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations,color management, and text layout.

        tileM.draw(g2);

        player.draw(g2);



        g2.dispose();  //Dispose of this graphics context and release any system resources that it is using.


    }
}
