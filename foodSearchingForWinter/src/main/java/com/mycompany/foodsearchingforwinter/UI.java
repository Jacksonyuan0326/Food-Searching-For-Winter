package com.mycompany.foodsearchingforwinter;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.text.DecimalFormat;

import Reward.Reward_carrot;
import Reward.Reward_medkit;

import javax.swing.text.AttributeSet.FontAttribute;

/**The user interface class for display game information on screen. */
public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage carrotImage;
    BufferedImage medkitImage;
    /**for checking if need to display image */
    public boolean messageOn = false;
    /**the string you want to display on screen */
    public String message = "";
    int messageCounter = 0;
    /**check if game needs to finish */
    public boolean gameFinished = false; //if player get all carrot
    /**check if player losses the game */
    public boolean gameLoss = false; //if player have negative score

    public int commandNum = 0;

    //timer for game
    double play_time; 
    DecimalFormat dformat = new DecimalFormat("#0.00");

    /**
     * Setting the font for messages display on screen.
     * Reads all reward images
     * @param gp need GamePanel for drawing stuff
     */
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        Reward_carrot carrot = new Reward_carrot();
        carrotImage = carrot.image;
        Reward_medkit medkit = new Reward_medkit();
        medkitImage = medkit.image;
    }
    /**
     * This method gets the text you want to display
     * @param text string for displaying in the screen
     */
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    /**This method will show the score and carrot for bunny in
     * the screen, and will print message when bunny collect something
     * @param g2 the image of screen
     */
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        //TITLE SCREEN
        if(gp.gameState == gp.titleState){
            drawTitle();
        }
        if(gp.gameState == gp.playState){
            if(gameFinished == true && gameLoss==false){
            
                g2.setFont(arial_80B);
                g2.setColor(Color.WHITE);
                String text;
                int textLength;
                int x;
                int y;
                
                text = "Congratulation! You win!";
                textLength =(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenHeight/2;
    
                g2.drawString(text, x, y);
    
                gp.gameThread = null;
            }
            else if (gameLoss == true){
                g2.setFont(arial_80B);
                g2.setColor(Color.WHITE);
                String text;
                int textLength;
                int x;
                int y;
                
                text = "oh no! You loss!";
                textLength =(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenHeight/2;
    
                g2.drawString(text, x, y);
                gp.gameThread = null;
            }
            else{        
                g2.setFont(arial_40);
                g2.setColor(Color.WHITE);
                //LOAD SCORE OF BUNNY
                g2.drawString("Score: " + gp.bunny.score, 25, 50); 
                //LOAD CARROT INFORMATION HERE
                g2.drawImage(carrotImage, 5*gp.tileSize,12, 48, 48, null);
                g2.drawString( " : " + gp.bunny.carrotNum + "/5", 268 , 50);
                //ADD MEDKIT SESSION HERE
                g2.drawImage(medkitImage, 400,12, 48, 48, null );
                g2.drawString( " x " + gp.bunny.medkitNum, 430 , 50);
    
                //calculate the time
                play_time+= (double)1/60; //60 frame/sec so 
                g2.drawString("Total time: "+dformat.format(play_time)+" sec", 600, 50);
    
                if(messageOn == true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize*9,gp.tileSize*13);
                messageCounter++;
    
                //The message will print 2 seconds in the screen, cuz FPS = 60
                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
        }
        if(gp.gameState == gp.pauseState){
            pauseScreen();
        }
       
    }
    public void drawTitle(){
        //TITLE SCREEN
        g2.setColor(new Color(60,110,70));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Food Searching For Winter";
        int x = getCentreX(text);
        int y = gp.tileSize*3;
        
        //SHADOW
        g2.setColor(Color.GRAY);
        g2.drawString(text, x+5, y+5);

        //TITLE
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);

        //Bunny IMAGE
        x = gp.screenWidth/2 - 1*gp.tileSize;
        y += gp.tileSize*2;
        g2.drawImage(gp.bunny.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        //Display menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        //NEW GAME option
        g2.setColor(Color.RED);
        text = "NEW GAME";
        x = getCentreX(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">>", x-gp.tileSize*2,y);
        }

        //LOAD GAME option
        g2.setColor(Color.yellow);
        text = "LOAD GAME";
        x = getCentreX(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">>", x-gp.tileSize*2,y);
        }

        //EXIT GAME option
        g2.setColor(Color.black);
        text = "EXIT GAME";
        x = getCentreX(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">>", x-gp.tileSize*2,y);
        }




    }
    public void pauseScreen(){
        this.g2 = g2;
        g2.setFont(arial_80B);
        String text = "PAUSED";
        int x = getCentreX(text);
        int y = gp.screenHeight /2;

        g2.drawString(text, x, y);
    }
    public int getCentreX(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length /2;
        return x;
    }
}
