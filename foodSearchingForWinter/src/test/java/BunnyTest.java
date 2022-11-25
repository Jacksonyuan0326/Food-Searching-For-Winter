
import org.junit.jupiter.api.Test;

import com.mycompany.foodsearchingforwinter.*;

import Objects.Bunny;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BunnyTest {
    public GamePanel gp;
    public Control keyControlTest;

    public Bunny bunnyTest;
    @BeforeEach
    void setupBunny(){
        GamePanel gamePanel = new GamePanel();
        gp = gamePanel;
        Control keyControl = new Control(gamePanel);
        keyControlTest = keyControl;
        Bunny bunny = new Bunny(gp, keyControlTest);
        bunnyTest = bunny;
    }
    @Test
    void testSetBunnyBasic(){
        System.out.println("Testing the basic of the bunny");
        System.out.println("TESTING INIT LOCATION AND SPEED");
        assertTrue(bunnyTest.worldX == 100);
        assertTrue(bunnyTest.worldY == 100);
        assertTrue(bunnyTest.speed == 4);
        assertTrue(bunnyTest.direction.equals("down"));
    }
    @Test
    void testBunnyDie(){
        System.out.println("TESTING DYING");
        bunnyTest.bunnyDie(-5);
        assertTrue(bunnyTest.isDead == true);
    }



}
