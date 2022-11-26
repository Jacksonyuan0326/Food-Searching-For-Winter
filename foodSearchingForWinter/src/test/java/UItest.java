
import org.junit.jupiter.api.Test;

import com.mycompany.foodsearchingforwinter.GamePanel;
import com.mycompany.foodsearchingforwinter.UI;

import Title.All_Titles;

import java.awt.Graphics2D;
import java.awt.image.*;

import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class UItest{
    public GamePanel gamePanel;
    public Graphics2D g2;
    @BeforeEach
    void setup(){
        GamePanel gamePanel = new GamePanel();
        UI ui = new UI(gamePanel);
    }
    @Test
    void test_showMessage(){
        GamePanel gamePanel = new GamePanel();
        String text = "Show message working";
        UI ui = new UI(gamePanel);
        ui.showMessage(text);
        assertEquals(text, ui.message);
    }
    
    @Test 
    void test_ImageLoad(){
        GamePanel gamePanel = new GamePanel();
        UI ui = new UI(gamePanel);

    }
}