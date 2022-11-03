package Reward;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.mycompany.foodsearchingforwinter.GamePanel;
public class All_Reward {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int x, y;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefault_x = 0;
    public int solidAreaDefault_y = 0;

    /**This method draws image on map
     * 
     * @param g2 Graphics2d
     * @param gp GamePanel
     */
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x,y, gp.tileSize, gp.tileSize, null);
    }

}