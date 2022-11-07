package com.mycompany.foodsearchingforwinter; //change all below
import Objects.Object;

/**This class check the collison between objects */
public class CollisonCheck {
    GamePanel gp;
    
    /**initialize the GamePanel 
    * @param gp gamepanel
    */
    public CollisonCheck(GamePanel gp){
        this.gp = gp;
    }

    /**This method used to check for player's collison status
     * @param object any player that belong to the the object class
     */
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

    /**This method will check whether bunny will reach the rewards and return the
     * index of the reward in the reward list
     * @param object the bunny
     * @param bunny to judge if the object is bunny
     * @return the index of the reward
     */
    public int checkRewards(Object object, boolean bunny ){

        int index = 999;

        for(int i = 0; i < gp.the_rewards.length; i++){
            if(gp.the_rewards[i] != null){
                //get bunny solid area position
                object.solidArea.x = object.xpo +object.solidArea.x;
                object.solidArea.y = object.ypo +object.solidArea.y;
                // get the rewards solid area position
                gp.the_rewards[i].solidArea.x = gp.the_rewards[i].x + gp.the_rewards[i].solidArea.x;
                gp.the_rewards[i].solidArea.y = gp.the_rewards[i].y + gp.the_rewards[i].solidArea.y;

                switch (object.direction){
                    case "up":
                        object.solidArea.y -= object.speed;
                        if(object.solidArea.intersects(gp.the_rewards[i].solidArea)){
                            //check if it hits a door (not a reward)
                            if(gp.the_rewards[i].collision == true){
                                //player collison on 
                                object.IsCollison = true;
                            }
                            if(bunny == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        object.solidArea.y += object.speed;
                        if(object.solidArea.intersects(gp.the_rewards[i].solidArea)){
                            if(gp.the_rewards[i].collision == true){
                                object.IsCollison = true;
                            }
                            if(bunny == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        object.solidArea.x -= object.speed;
                        if(object.solidArea.intersects(gp.the_rewards[i].solidArea)){
                            if(gp.the_rewards[i].collision == true){
                                object.IsCollison = true;
                            }
                            if(bunny == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        object.solidArea.x += object.speed;
                        if(object.solidArea.intersects(gp.the_rewards[i].solidArea)){
                            if(gp.the_rewards[i].collision == true){
                                object.IsCollison = true;
                            }
                            if(bunny == true){
                                index = i;
                            }
                        }
                        break;
                }
                object.solidArea.x = object.solidAreaDefaultX;
                object.solidArea.y = object.solidAreaDefaultY;
                gp.the_rewards[i].solidArea.x = gp.the_rewards[i].solidAreaDefault_x;
                gp.the_rewards[i].solidArea.y = gp.the_rewards[i].solidAreaDefault_y;
            }
        }

        return index;
    }
}
