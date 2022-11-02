package com.mycompany.foodsearchingforwinter;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;
import Reward.Reward_carrot;

import javax.swing.text.AttributeSet.FontAttribute;

public class UI {
    GamePanel gp;
    Font arial_40;
    BufferedImage carrotImage;

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        Reward_carrot carrot = new Reward_carrot();
        carrotImage = carrot.image;
    }
    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawString("Score: " + gp.bunny.score, 25, 50); 
        g2.drawImage(carrotImage, 5*gp.tileSize,12, 48, 48, null);
        // g2.drawImage()
        g2.drawString( " : " + gp.bunny.carrotNum + "/5", 268 , 50);
    }
}
