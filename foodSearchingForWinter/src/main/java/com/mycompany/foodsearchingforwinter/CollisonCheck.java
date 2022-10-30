package com.mycompany.foodsearchingforwinter; //change all below
import Objects.Object;

public class CollisonCheck {
    GamePanel gp;
    
    public CollisonCheck(GamePanel gp){
        this.gp = gp;
    }

    public void check(Object object){
        int obj_left_x = object.xpo + object.solidArea.x;
        int obj_right_x = object.xpo+ object.solidArea.x + object.solidArea.width;
        int obj_top_y = object.ypo + object.solidArea.y;
        int obj_bottom_y = object.ypo + object.solidArea.y + object.solidArea.height; //origin 60 but doesn't work

        //actual col # and row # in the 2d map
        int map_left_col = obj_left_x/gp.tileSize;
        int map_right_col = obj_right_x/gp.tileSize;
        int map_top_row = obj_top_y/45; //origin 60
        int map_bottom_row = obj_bottom_y/45;  //origin 60

        int pixel_num1, pixel_num2;

        switch(object.direction){
            case "up":
                map_top_row = (obj_top_y-object.speed)/45; //origin 60
                pixel_num1 = gp.tileM.mapTileCord[map_left_col][map_top_row];
                pixel_num2 = gp.tileM.mapTileCord[map_right_col][map_top_row];
                if(gp.tileM.tile[pixel_num1].collision==true || gp.tileM.tile[pixel_num2].collision==true ){
                    object.IsCollison=true;
                }
                break;
            case "down":
                map_bottom_row = ((obj_bottom_y+object.speed)/45); //origin 60
                pixel_num1 = gp.tileM.mapTileCord[map_left_col][map_bottom_row];
                pixel_num2 = gp.tileM.mapTileCord[map_right_col][map_bottom_row];
                if(gp.tileM.tile[pixel_num1].collision==true || gp.tileM.tile[pixel_num2].collision==true ){
                    object.IsCollison=true;
                }
                break;
            case "left":
                map_left_col = (obj_left_x-object.speed)/gp.tileSize;
                pixel_num1 = gp.tileM.mapTileCord[map_left_col][map_top_row];
                pixel_num2 = gp.tileM.mapTileCord[map_left_col][map_bottom_row];
                if(gp.tileM.tile[pixel_num1].collision==true || gp.tileM.tile[pixel_num2].collision==true ){
                    object.IsCollison=true;
                }
                break;
            case "right":
                map_right_col = (obj_right_x+object.speed)/gp.tileSize;
                pixel_num1 = gp.tileM.mapTileCord[map_right_col][map_top_row];
                pixel_num2 = gp.tileM.mapTileCord[map_right_col][map_bottom_row];
                if(gp.tileM.tile[pixel_num1].collision==true || gp.tileM.tile[pixel_num2].collision==true ){
                    object.IsCollison=true;
                }
                break;
        }

    }
}
