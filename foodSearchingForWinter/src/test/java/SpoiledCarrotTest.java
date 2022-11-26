
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;

import Punishment.*;
import Reward.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;



public class SpoiledCarrotTest {

    @Test
    void name() {
        spoiled_carrot carrot = new spoiled_carrot();
        assertTrue(carrot.name == "spoiled_carrot");
        System.out.println("name correct");
    }

    @Test
    void image_load(){
        try{
            spoiled_carrot carrot = new spoiled_carrot();
            carrot.image=ImageIO.read(getClass().getResourceAsStream("/Reward/carrot3.png"));; 
            System.out.println("image load correct");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
