package Punishment;
import java.io.IOException;

import javax.imageio.ImageIO;

import Reward.All_Reward;

public class spoiled_carrot extends All_Reward{
    public spoiled_carrot(){
        name = "spoiled_carrot";
    
    try{
        image = ImageIO.read(getClass().getResourceAsStream("/punish/spoiled_carrot.png"));
    }catch(IOException e){
        e.printStackTrace();
    }
    }
}