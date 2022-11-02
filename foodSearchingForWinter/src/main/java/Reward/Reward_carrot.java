package Reward;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Reward_carrot extends All_Reward {

    public Reward_carrot(){
        name = "carrot";
        //collision = true;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Reward/carrot3.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }



}

