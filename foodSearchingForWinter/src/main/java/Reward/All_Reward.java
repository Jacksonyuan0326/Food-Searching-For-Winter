package Reward;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import com.mycompany.foodsearchingforwinter.GamePanel;
public class All_Reward {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int x, y;

    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x,y, gp.tileSize, gp.tileSize, null);
    }

}