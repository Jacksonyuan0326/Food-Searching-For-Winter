package Reward;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Reward_medkit extends All_Reward {
    public Reward_medkit(){
        name = "medkit";
        //collision = true;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Reward/medkit.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
