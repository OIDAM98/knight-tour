package graphs;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private TreeNode root;
    private List<TreeNode> nodeList;

    public Tree(){
        this.root = null;
        this.nodeList = new ArrayList<>();
    }

    public void addNodeToTree(TreeNode toAdd){
        if(this.root == null){
            this.root = toAdd;
            this.nodeList.add(toAdd);
            return;
        }
        this.addNodeToTree(root, toAdd);
    }

    private void addNodeToTree(TreeNode source, TreeNode dest){//Add connection from node Source to Dest
        if (!this.nodeList.contains(dest)) { //If Tree doesn't contain destination
            this.nodeList.add(dest);
        }
        if(dest.getFather().getId().equals(source.getId())){
            source.addVertex(dest);
            return;
        }
        for(TreeNode i : source.getConnections()){
            if(dest.getFather().getId().equals(i.getId())){
                i.addVertex(dest);
                return;
            }
            this.addNodeToTree(i, dest);
        }
    }

    private void printNodesInTree(){
        System.out.println("Nodes in Tree:");
        for(TreeNode i : this.nodeList){
            System.out.println(i);
        }
        System.out.println("");
    }

    private void printConnectionsInTree(){
        System.out.println("Connections in Tree");
        for(TreeNode i : this.nodeList){
            System.out.print("Connections of " + i.getId() + " : ");
            for(TreeNode j : i.getConnections()){
                System.out.print(j.getId() + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void printTree(){
        System.out.println("Tree starting from " + root.getId());
        System.out.println("");

        this.root.print();

        System.out.println("");
    }

    public void printTree(int i){
        System.out.println("Tree" + "("+ i + ") starting from " + root.getId());
        System.out.println("");

        this.root.print();

        System.out.println("");
    }

}