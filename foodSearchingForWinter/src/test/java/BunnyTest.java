
import org.junit.jupiter.api.Test;

import com.mycompany.foodsearchingforwinter.*;

import Objects.Bunny;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;

public class BunnyTest {
    private GamePanel gp;
    private Control keyControl;
    @BeforeEach
    void setBunny(){
        Bunny bunny = new Bunny(gp, keyControl);
    }
    @Test
    void testSetBunnyBasic(){
        
    }

}
