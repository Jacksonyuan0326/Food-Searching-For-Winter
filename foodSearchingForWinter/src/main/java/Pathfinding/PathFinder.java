package Pathfinding;

import com.mycompany.foodsearchingforwinter.GamePanel;

import java.util.ArrayList;

public class PathFinder {
    GamePanel  gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean reachedGoal = false;
    int step = 0;

    public PathFinder(GamePanel gp){
        this.gp = gp;
        instantiateNodes();
    }

    public void instantiateNodes() {
        node = new Node[gp.maxScreenCol][gp.maxScreenRow];

        int col = 0;
        int row = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            node[col][row] = new Node(col, row);
            col++;

            if (col == gp.maxScreenCol){
                col = 0;
                row++;
            }
        }
    }

    public void resetNode(){
        int col = 0;
        int row = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            /** reset the nodes */
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;
            col++;

            if (col == gp.maxScreenCol){
                col = 0;
                row++;
            }
        }
        /** reset everything else */
        openList.clear();
        pathList.clear();
        reachedGoal = false;
        step = 0;
    }

    public void setNode(int startCol, int startRow, int goalCol, int goalRow, Object target){
        resetNode();

        /** set the start and goal nodes */
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode =node[goalCol][goalRow];
        //and add the current node on the list
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = gp.tileM.mapTileCord[col][row];
            if (gp.tileM.tile[tileNum].collision == true){
                node[col][row].solid = true;
            }
        }
        //getDistance(node[col][row]);
        col++;

    }


}


