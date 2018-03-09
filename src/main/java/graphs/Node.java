package graphs;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node father;
    private String id;
    private List<Node> children;
    private String color;
    private int depth;

    public Node(String n){
        this.id = n;
        this.children = new ArrayList<Node>();
        this.depth = 0;
    }

    private Node getFather() {
        return father;
    }

    void setFather(Node father) {
        this.father = father;
    }

    String getId() {
        return id;
    }

    List<Node> getChildren() {
        return children;
    }

    String getColor() {
        return color;
    }

    void setColor(String color) {
        this.color = color;
    }

    int getDepth() {
        return depth;
    }

    void setDepth(int depth) {
        this.depth = depth;
    }

    void addEdge(Node toAdd) { //Add connection between This and toAdd
        this.children.add(toAdd);
    }

    @Override
    public String toString(){
        String temp = "Node: " + this.getId() + " (" + this.getDepth();
        if(this.getFather() == null){
            temp = temp + " null";
        }
        else{
            temp = temp + " " + this.getFather().getId();
        }
        temp += ")";
        return temp;
    }
}
