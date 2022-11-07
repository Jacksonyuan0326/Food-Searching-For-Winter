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
            getCost(node[col][row]);
            col++;
            if (col == gp.maxScreenCol){
                col = 0;
                row++;
            }
        }
    }

    public void getCost(Node node){
        int xDistant = Math.abs(node.col - startNode.col);
        int yDistant = Math.abs(node.row - startNode.row);
        node.gCost = xDistant + yDistant;

        xDistant = Math.abs(node.col - startNode.col);
        yDistant = Math.abs(node.row - startNode.row);
        node.hCost = xDistant + yDistant;

        node.fCost = node.hCost + node.gCost;


    }

    public boolean search(){
        while (reachedGoal == false && step < 500){
            int col = currentNode.col;
            int row = currentNode.row;

            // to check the current node and remove from the unchecked list
            currentNode.checked = true;
            openList.remove(currentNode);

            // open the up node
            if (row - 1 >= 0 ){
                openNode(node[col][row - 1]);
            }
            // left node
            if (col - 1 >= 0 ){
                openNode(node[col - 1][row]);
            }
            // down node
            if (row + 1 < gp.maxScreenRow ){
                openNode(node[col][row + 1]);
            }
            //right node
            if (col + 1 < gp.maxScreenCol ){
                openNode(node[col + 1][row]);
            }

            /** to find the best path */
            int bestNodeIndex = 0 ;
            int bestNodefCost = 999;
            for (int i = 0; i < openList.size(); i++){
                //compare f cost
                if (openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                //if F cost is the same, what about G cost
                else if (openList.get(i).fCost == bestNodefCost){
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }
            if (openList.size() == 0){
                break;
            }

            //after the loop, openlist[bestNodeIndex] is the next step => current node
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                reachedGoal = true;
                pathTracking();
            }
            step++;
        }
        return reachedGoal;
    }

    public void pathTracking() {
        Node current = goalNode;
        while (current != startNode){
            pathList.add(0, current);
            current = current .parent;
        }
    }

    public void openNode(Node node){
        if (node.open == false && node.checked == false && node.solid == false){
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }
}


