package graphs;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private TreeNode father;
    private String id;
    private List<TreeNode> connections;
    private int depth;

    TreeNode(Node copy){
        this.id = copy.getId();
        this.connections = new ArrayList<TreeNode>();
        this.depth = copy.getDepth();
    }

    TreeNode getFather() {
        return father;
    }

    void setFather(TreeNode father) {
        this.father = father;
    }

    String getId() {
        return id;
    }

    List<TreeNode> getConnections() {
        return connections;
    }

    private int getDepth() {
        return depth;
    }

    void addVertex(TreeNode toAdd) {
        this.connections.add(toAdd);
    }

    void print(){
        print("", true);
    }

    private void print(String prefix, boolean isTail){
        if(isTail){
            System.out.println(prefix + "└── " + this.getId() + " (" + this.getDepth() + ")");
        }
        else{
            System.out.println(prefix + "├── " + this.getId() + " (" + this.getDepth() + ")");
        }

        for(int  i = 0; i < this.connections.size() - 1; i++){
            if(isTail){
                connections.get(i).print(prefix + "    ", false);
            }
            else{
                connections.get(i).print(prefix + "│   ", false);
            }
        }

        if(connections.size() > 0){
            if(isTail){
                connections.get(connections.size() - 1).print(prefix + "    ", true);
            }
            else{
                connections.get(connections.size() - 1).print(prefix + "│   ", true);
            }
        }
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