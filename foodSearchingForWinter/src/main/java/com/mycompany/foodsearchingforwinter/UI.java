package java.com.mycompany.foodsearchingforwinter;

public class UI {
    GamePanel gp;
    Font arial_40;

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
    }
    public void draw(Graphics2D g2){

        g2.setFont(arial_40));
        g2.setColor(Color.white);
        g2.drawingString("Score = " + gp.bunny.score, 25, 50);
        //we need timer here
        //we need Image for carrot and medkit counting
    }
}
