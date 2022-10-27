package Objects;

import com.mycompany.foodsearchingforwinter.Control;
import com.mycompany.foodsearchingforwinter.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bunny extends Object{
    GamePanel gp;
    Control keyControl;

    public Bunny (GamePanel gp, Control keyControl){
        this.gp = gp;
        this.keyControl = keyControl;
        setBunnyBasic();
        getBunnyPixel();
    }

    public void setBunnyBasic(){
        xpo = 100;
        ypo = 100;
        speed = 4;
        direction = "down";
    }

    public void getBunnyPixel() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/down2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/right2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/left2.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if (keyControl.isUp){
            direction = "up";
            ypo -= speed;
        }
        else if (keyControl.isDown){
            direction = "down";
            ypo += speed;
        }
        else if (keyControl.isLeft){
            direction = "left";
            xpo -= speed;
        }
        else if (keyControl.isRight){
            direction = "right";
            xpo += speed;
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

    public void draw(Graphics g2){
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
        //importing the pixel
        g2.drawImage(image, xpo, ypo, gp.bunnytileWidth, gp.bunnytileHeight, null);
    }

}

