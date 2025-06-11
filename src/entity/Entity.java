package entity;

import java.awt.image.BufferedImage;

public class Entity {     //This will store the variables that will be used in player, monster and NPC classes.
    public int x, y;
    public int speed;

    public BufferedImage up1, down1, left1, right1, up2, down2, left2, right2;   //BufferedImage = It describes an Image with an accessible buffer of image data.(We use this to store our image files)

    public String direction;

    public int spriteCounter = 0;

    public int spriteNum = 1;

}
