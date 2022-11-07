package Objects;

import com.mycompany.foodsearchingforwinter.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

/**This class contain all characters in the game like players and enimies */
public class Object {

    /**position of characters */
    public int xpo, ypo;
    /** speed of characters  */
    public int speed;
    GamePanel gp;

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

    /** for pathfinding */
    public boolean onPath = false;

    public Object(GamePanel gp){
        this.gp = gp;
    }

    public void searchShortestPath(int goalCol, int goalRow){
        int startCol = gp.bunny.xpo / gp.tileSize;
        System.out.println("startcol = " + startCol);
        int startRow = gp.bunny.ypo / gp.tileSize;
        System.out.println("startrow = " + startRow);

        gp.Path.setNode(startCol, startRow, goalCol, goalRow, this);

        if (gp.Path.search() == true){
            int nextX  = gp.Path.pathList.get(0).col * gp.tileSize;
            int nextY  = gp.Path.pathList.get(0).row * gp.tileSize;

            //object position
            int objLeftX = gp.bunny.xpo;
            int objRightX = gp.bunny.xpo + gp.bunny.solidArea.width;
            int objTopY = gp.bunny.ypo;
            int objButtonY = gp.bunny.ypo + gp.bunny.solidArea.height;

            if (objTopY > nextY && objLeftX >= nextX && objRightX < nextX + gp.tileSize){
                direction = "up";
            }
            else if (objTopY < nextY && objLeftX >= nextX && objRightX < nextX + gp.tileSize){
                direction = "down";
            }
            else if (objTopY >= nextY && objButtonY < nextY + gp.tileSize){
                if (objLeftX > nextX){
                    direction = "left";
                }
                if (objLeftX < nextX){
                    direction = "right";
                }
            }
            else if (objTopY > nextY && objLeftX > nextX){
                direction = "up";
            }
            else if (objTopY > nextY && objLeftX < nextX){
                direction = "up";
            }
            else if (objTopY < nextY && objLeftX > nextX){
                direction = "down";
            }
            else if (objTopY < nextY && objLeftX < nextX){
                direction = "down";
            }
        }

        /**
        //when it reaches the goal, stop searching for path
        int nextCol = gp.Path.pathList.get(0).col;
        int nextRow = gp.Path.pathList.get(0).row;
        if (nextCol == goalCol && nextRow == goalRow){
            onPath = false;
        }
         */
    }



}
