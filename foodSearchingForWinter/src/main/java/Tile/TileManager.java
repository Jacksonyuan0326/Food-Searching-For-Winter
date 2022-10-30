package Tile;

import com.mycompany.foodsearchingforwinter.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileCord[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[15];
        mapTileCord = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        readMap();
    }

    public void getTileImage(){
        try {
            //basic grass
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile/grass1.png")));
            //sidewalk
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile/sidewalk1.png")));
            //water
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile/water.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile/grasswall.png")));
            tile[3].collision = true;
            

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readMap(){
        try {
            InputStream dir = getClass().getResourceAsStream("/MappingFile/tileMap.txt");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(dir));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = buffer.readLine();

                while (col < gp.maxScreenCol){
                    String number[] = line.split(" ");

                    int num = Integer.parseInt(number[col]);
                    mapTileCord[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol){
                    col = 0;
                    row ++;
                }
            }
            dir.close();

        }catch (Exception e){
        }
    }
    public void drawMap(Graphics2D g2){
        //16 * 12 map
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapTileCord[col][row];
            g2.drawImage(tile[tileNum].image, x,y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

}
