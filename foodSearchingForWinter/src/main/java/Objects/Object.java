package Objects;

import java.awt.image.BufferedImage;
//import java.awt.Rectangle;

public class Object {

    public int xpo, ypo;
    public int speed;

    public BufferedImage idle, up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    //public Rectangle rectangle; //video has but didn't use
    public boolean IsCollison = false; //change

}
