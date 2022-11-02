package Reward;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Door extends All_Reward {
    public Door(){
        name = "door";
        collision = true;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Reward/door.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
