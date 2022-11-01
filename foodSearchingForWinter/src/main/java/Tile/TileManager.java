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
    public int mapTileCord[][];//load tileMap.txt

    /**This method will manage and call all method to struct the map in this class
     * @param gp
     */
    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[15];
        //!!we will change here for maxWorldCol and maxWorldRow
        mapTileCord = new int[gp.maxScreenCol][gp.maxScreenRow]; // will store all values in tileMap.txt

        getTileImage();
        readMap();
    }

    /**Load all tile images to this method
     * The grass tile will be "0" in the tileMap.txt
     * The sidewalk tile will be "1" in the tileMap.txt
     * The water tile will be "2" in the tileMap.txt
     * The grassWall tile will be "3" in the tileMap.txt
     */
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

    /**This method will store values in the tileMap.txt file and read eachline with outer while loop and
     * change to next line with inner while loop
     */
    public void readMap(){
        try {
            //!!and here to tileWorld.txt
            InputStream dir = getClass().getResourceAsStream("/MappingFile/tileMap.txt");//can be substitue with file path here
            BufferedReader buffer = new BufferedReader(new InputStreamReader(dir));//read text file

            int col = 0;
            int row = 0;

            //!!here we need Change to maxScreenCol and maxScreenRow to maxWorldCol and maxWorldRow
            while (col < gp.maxScreenCol && row < gp.maxScreenRow){//size of Map
                String line = buffer.readLine();//read a line of text

                while (col < gp.maxScreenCol){
                    String number[] = line.split(" ");//Split string matching the format of text file

                    int num = Integer.parseInt(number[col]);
                    mapTileCord[col][row] = num;//store each value
                    col++;
                }
                if (col == gp.maxScreenCol){
                    col = 0;
                    row ++;//change to the next line/row
                }
            }
            dir.close();//close the file safely

        }catch (Exception e){
        }
    }

    /**This method will draw the map of this game with 16*12 map with grass, sidewalk, water and grasswall
     * @param g2 Take the Graphic2D g2 from GamePanel.Java
     * @see the Map of game
     */
    public void drawMap(Graphics2D g2){
        //16 * 12 map
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow){//MaxCol = 16, MaxRow = 12
            int tileNum = mapTileCord[col][row];
            g2.drawImage(tile[tileNum].image, x,y, gp.tileSize, gp.tileSize, null);
            col++;//going to draw next tile
            x += gp.tileSize;

            if (col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

    /**This method will extend the map to a world, which means the map of the game
     *will be bigger and screen will still be the origin size but map will move while
     * bunny move to the boudary
     * @param g2
     */
    public void drawWorld(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileCord[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = WorldX - gp.bunny.xpo + gp.bunny.screenX;
            int screenY = worldY - gp.bunny.ypo + gp.bunny.screenY;

            if(worldX + gp.tileSize > gp.bunny.xpo - gp.bunny.screenX &&
               worldX - gp.tileSize< gp.bunny.xpo + gp.bunny.screenX &&
               worldY + gp.tileSize> gp.bunny.ypo - gp.bunny.screenY &&
               worldY - gp.tileSize< gp.bunny.ypo + gp.bunny.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
