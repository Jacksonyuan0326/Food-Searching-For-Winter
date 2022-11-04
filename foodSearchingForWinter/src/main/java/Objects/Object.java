package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

/**This class contain all characters in the game like players and enimies */
public class Object {

    /**position of characters */
    public int xpo, ypo;
    /** speed of characters  */
    public int speed;

    /**image of characters */
    public BufferedImage idle, up1, up2, down1, down2, left1, left2, right1, right2;
    /**direction of character */
    public String direction;
    /**using for frame check */
    public int spriteCounter = 0;
    /**use to switch to different images */
    public int spriteNum = 1;

    /**the position of character in rectangle format*/
    public Rectangle solidArea;
    /**default position in the rectangle */
    public int solidAreaDefaultX, solidAreaDefaultY;
    /**check character collision */
    public boolean IsCollison = false;

}
