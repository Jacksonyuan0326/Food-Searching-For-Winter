package Objects;

import com.mycompany.foodsearchingforwinter.Control;
import com.mycompany.foodsearchingforwinter.GamePanel;
import Reward.Reward_medkit; //change

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * This is the bunny player class
 */
public class Bunny extends Object{
    Control keyControl;

    /** the total score player has */
    public int score = 0;
    /** the total carrots player gets */
    public int carrotNum = 0;
    /** the total medkit player gets*/
    public int medkitNum = 0;

    /**The constructor sets basic position of player bunny
     * and read the bunny image
     * @param gp need GamePanel object
     * @param keyControl need Control object that manages the keyborad movement
     */
    public Bunny (GamePanel gp, Control keyControl){
        super(gp);
        this.keyControl = keyControl;

        //rectangle = new Rectangle(0,0,48,60); //didn't use
        //we should use this because we need the collision box
        solidArea = new Rectangle(8 , 5, 32 , 30);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setBunnyBasic();
        getBunnyPixel();

    }


    /**This method is setting the basic atrribution of bunny such as initial x and y coodinates, the speed of movement
     * and its direction
     */
    public void setBunnyBasic(){

        xpo = 100;
        ypo = 100;
        speed = 4;
        direction = "down";
    }

    /**This method is getting the bunny pixel from the resource
     */
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
    /**This method sets the direction of bunny when user press the WASD keys in 
     * the keyboard and check whether there is a collision with other object
     * in the map.
     */
    public void update(){
        
        if(keyControl.isUp==true || keyControl.isDown==true ||keyControl.isLeft==true ||keyControl.isRight==true){
            if (keyControl.isUp){
                direction = "up";
                //ypo -= speed;  //change
            }
            else if (keyControl.isDown){
                direction = "down";
                //ypo += speed;
            }
            else if (keyControl.isLeft){
                direction = "left";
                //xpo -= speed;
            }
            else if (keyControl.isRight){
                direction = "right";
                //xpo += speed;
            }

            IsCollison = false; //change start
            gp.checker.check(this);

            //check rewards collision
            int rewardsIndex = gp.checker.checkRewards(this, true);
            pickUpRewards(rewardsIndex);

            /** check wolf collision */
            int wolfIndex = gp.checker.checkWolf(this, gp.wolf);
            bunnyDie(wolfIndex);

            //if no collison happen, then move
            if(IsCollison==false){
                switch(direction){
                    case "up":
                        ypo -= speed;
                        //random_carrot2();
                        break;
                    case "down":
                        ypo += speed;
                        random_carrot1();
                        break;
                    case "left":
                        xpo -= speed;
                        random_carrot2();
                        break;
                    case "right":
                        xpo += speed;
                        random_carrot3();
                        break;
                }
                    
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
    }

    /**This method will remove the rewards from the rewards list if bunny
     * reach the position, otherwise it will not do anything
     * @param i the position of the reward in the rewards list
     */
    public void pickUpRewards(int i){
        if(i != 999){
            String rewardType = gp.the_rewards[i].name;

            switch (rewardType){
                case "carrot":
                    score+=5;//one carrot for adding 5 points
                    carrotNum++;
                    gp.the_rewards[i] = null;
                    gp.ui.showMessage("You got a carrot!");
                    break;
                case "spoiled_carrot":
                    score-=5;//one carrot for decreasing 5 points
                    gp.the_rewards[i] = null;
                    gp.ui.showMessage("Punishment! you got a spoiled carrot!");
                    if (score<0){
                        gp.ui.gameLoss = true;
                    }
                    break;
                case "medkit":
                    score+=10;//one medkit for adding 10 points
                    gp.the_rewards[i] = null;
                    medkitNum++;
                    gp.ui.showMessage("You got a medkit!");
                    break;
                case "door":
                    if(carrotNum == 5){
                        gp.ui.gameFinished = true;
                        gp.the_rewards[i] = null;
                    }
                    else if(carrotNum < 5)
                        gp.ui.showMessage("You need more carrots!");
                    break;
            }
        }
    }

    public void bunnyDie(int index){
        if ( index != 999){
            score -= 50;
            if (score<0){
                gp.ui.gameLoss = true;
            }
            System.out.println("Bunny touched the wolf, bunny dies!\n");
        }
    }

    /**
     * This method used to import and draw the image
     * @param g2 the grahics use to draw images on map
     */
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

    private void random_carrot1(){
        if(ypo<300 && xpo<240){ //change------------- random spoiled carrot
            gp.the_rewards[12]=new Reward_medkit();
            gp.the_rewards[12].x = 7*gp.tileSize;
            gp.the_rewards[12].y = 6*gp.tileSize;

            gp.the_rewards[6] = null;
        }
        else if (ypo>300 && ypo<576 && xpo<500){
            gp.the_rewards[12]=null;

            gp.the_rewards[6] = new Reward_medkit();
            gp.the_rewards[6].x = 3*gp.tileSize;
            gp.the_rewards[6].y = 14*gp.tileSize;
        }
        else if(ypo<150 && xpo>500 && xpo<1000){
            gp.the_rewards[13]=new Reward_medkit();
            gp.the_rewards[13].x = 16*gp.tileSize;
            gp.the_rewards[13].y = 5*gp.tileSize;
        }
    }

    private void random_carrot2(){
        if(ypo<300 && xpo>500 && xpo<1000){ //change------------- random spoiled carrot
            //gp.the_rewards[13]=new Reward_medkit();
            //gp.the_rewards[13].x = 16*gp.tileSize;
            //gp.the_rewards[13].y = 4*gp.tileSize;

            gp.the_rewards[7] = null;
        }
        else if (ypo>300 && ypo<400 && xpo>500){
            gp.the_rewards[12]=null;
            gp.the_rewards[13] = null;

            //gp.the_rewards[7] = new Reward_medkit();
            //gp.the_rewards[7].x = 20*gp.tileSize;
            //gp.the_rewards[7].y = 7*gp.tileSize;
        }
        else if (xpo>500){
            gp.the_rewards[7]=null;
        }
    }

    private void random_carrot3(){
        if(xpo>700 && xpo<900){ //change------------- random spoiled carrot
            gp.the_rewards[6] = null;
            //gp.the_rewards[13] =null;

            gp.the_rewards[14] = new Reward_medkit();
            gp.the_rewards[14].x = 20*gp.tileSize;
            gp.the_rewards[14].y = 14*gp.tileSize;

        }
        else if (xpo<700){
            gp.the_rewards[14] = null;
        }
    }
}

