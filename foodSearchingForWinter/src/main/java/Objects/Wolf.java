package Objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;

import com.mycompany.foodsearchingforwinter.GamePanel;
import Objects.Object;

public class Wolf extends Object{
    String name;
    
    public Wolf(GamePanel gp){
        super(gp);
        name = "Wolf";

        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 28;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getWolfImage();
    }

    public void setWolfBasic(){
        xpo = 50;
        ypo = 50;
        speed = 2;
        direction = "down";
    }

    /**
     * This method is the pixel image getter for the wolf
     */
    public void getWolfImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/down2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/right2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/left2.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void setAction(){
        actionLockCounter++;
        Random random = new Random();
        int i = random.nextInt(100)+1;// pick up a number from 1 to 100

        if(i <= 25){
            direction = "up";
        }
        if(i > 25 && i <= 50){
            direction = "down";
        }
        if(i > 50 && i <= 75){
            direction = "right";
        }
        if(i > 75 && i <= 100){
            direction = "right";
        }

        actionLockCounter = 0;
    }
    public void update(){   
        

    }
    public void draw(Graphics2D g2, GamePanel gp){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNum == 1 ){
                    image = up1;
                }
                if (spriteNum == 2 ){
                    image = up2;
                }
                break;

            case "down":
                if (spriteNum == 1 ){
                    image = down1;
                }
                if (spriteNum == 2 ){
                    image = down2;
                }
                break;

            case "left":
                if (spriteNum == 1 ){
                    image = left1;
                }
                if (spriteNum == 2 ){
                    image = left2;
                }
                break;

            case "right":
                if (spriteNum == 1 ){
                    image = right1;
                }
                if (spriteNum == 2 ){
                    image = right2;
                }
                break;
        }
        /** drawing the wolf */
        g2.drawImage(image, xpo, ypo, gp.wolftileWidth, gp.WolftileHeight, null);
    }
}
