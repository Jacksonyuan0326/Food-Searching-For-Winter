package com.mycompany.foodsearchingforwinter;

import Objects.Bunny;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;

//this GamePanel will have all the functions in JPanel
public class GamePanel extends JPanel implements  Runnable{
    //game screen setting
    final int fixedTileSize = 16;
    final int scaleFactor = 3;
    public int tileSize = fixedTileSize * scaleFactor;
    public int bunnytileWidth = 16 * scaleFactor;
    public int bunnytileHeight = 20 * scaleFactor;

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    //add the control to the gamepanel
    Control keyControl = new Control();

    //set up FPS
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    Thread gameThread;
    //adding the bunny
    Bunny bunny = new Bunny(this, keyControl);

    //set the drop in position
    int xpo = 100;
    int ypo = 100;
    int playerSpeed = 4;



    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyControl);
        this.setFocusable(true);
    }

    public void startGameThread(){
        //passing the game panel into the Thread()
        //this = the gamepanel in this case
        gameThread = new Thread(this);
        //automatically call the override method
        gameThread.start();
    }

    //automatically call this
    @Override
    public void run() {
        //time for update the frames
        double drawInterval = 1000000000/FPS;//refresh rate of the update
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        //create the game loop
        while (gameThread!= null){
            currentTime = System.nanoTime();
            //how much time has past
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            //this loop should keep things updated
            //keep drawing the screen according to the new info
            //update and repaint
            if (delta >= 1){
                update();
                repaint();
                delta--;
            }

        }
    }

    public void update(){
        bunny.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //cascating the g to Graphics2D type
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        bunny.draw(g2);
        g2.dispose();
    }
}