package Pathfinding;

public class Node {
    Node parent;
    public int col;
    public int row;
    int gCost;
    int hCost;
    int fCost;
    boolean solid;
    boolean open;
    boolean checked;
    boolean start;
    boolean goal;

    public Node(int col, int row){
        this.col = col;
        this.row = row;
    }
    public void setAsStart(){
        System.out.println("This is the start");
        start = true;
    }

    public void setAsGoal(){
        System.out.println("This is the goal");
        goal = true;
    }

    public void setAsOpen(){
        System.out.println("THis node is open");
        open = true;
    }
    public void setAsChecked(){
        System.out.println("THis node is checked");
        checked = true;
    }

}
