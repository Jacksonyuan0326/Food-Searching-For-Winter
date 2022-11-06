package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;

import com.mycompany.foodsearchingforwinter.GamePanel;
import Objects.Object;

public class Wolf extends Object{
    String name;
    int actionLockCounter = 0;
    
    public Wolf(GamePanel gp){
        super(gp);
        name = "Wolf";

        solidArea = new Rectangle(8, 8, 20, 20);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getWolfImage();
        setWolfBasic();
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
        if (actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;// pick up a number from 1 to 100

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i < 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }
    public void update(){   
        setAction();
        gp.checker.check(this);

        if(!IsCollison){
            switch(direction){
                case "up":
                    ypo -= speed;
                    break;
                case "down":
                    ypo += speed;
                    break;
                case "left":
                    xpo -= speed;
                    break;
                case "right":
                    xpo += speed;
                    break;
            }
        }

        if (IsCollison){
            //wolf movement method here to bounce back from the collision
        }

        spriteCounter ++;
        //can adjust this 10 so the animation can be smoother
        if (spriteCounter > 10){
            if (spriteNum == 1){
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum =1;
            }
            //reset the counter
            spriteCounter = 0;
        }


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
