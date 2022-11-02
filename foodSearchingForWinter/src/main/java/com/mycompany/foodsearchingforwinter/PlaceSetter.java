package com.mycompany.foodsearchingforwinter;

import Reward.Door;
import Reward.Reward_carrot;

public class PlaceSetter {
    GamePanel gp;

    public PlaceSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setThing(){
        //carrots
        gp.the_rewards[0] = new Reward_carrot();
        gp.the_rewards[0].x = 6*gp.tileSize;
        gp.the_rewards[0].y = 9*gp.tileSize;

        gp.the_rewards[1] = new Reward_carrot();
        gp.the_rewards[1].x = 12*gp.tileSize;
        gp.the_rewards[1].y = 12*gp.tileSize;

        gp.the_rewards[2] = new Reward_carrot();
        gp.the_rewards[2].x = 20*gp.tileSize;
        gp.the_rewards[2].y = 5*gp.tileSize;

        gp.the_rewards[3] = new Reward_carrot();
        gp.the_rewards[3].x = 24*gp.tileSize;
        gp.the_rewards[3].y = 14*gp.tileSize;

        gp.the_rewards[4] = new Reward_carrot();
        gp.the_rewards[4].x = 6*gp.tileSize;
        gp.the_rewards[4].y = 2*gp.tileSize;

        //Door
        gp.the_rewards[5] = new Door();
        gp.the_rewards[5].x = 26*gp.tileSize;
        gp.the_rewards[5].y = 2*gp.tileSize;
    }
}
