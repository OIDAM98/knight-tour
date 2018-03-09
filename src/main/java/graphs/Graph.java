package graphs;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Graph {

    private List<Node> nodeList;
    private int connections;

    public Graph(){
        this.nodeList = new ArrayList<>();
        this.connections = 0;
    }

    private String randomizeString(){ //Create Random String
        String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder(); //Create new StringBuilder which is String to return
        Random rnd = new Random(); //Create new Randomizer
        int desiredLength = 3; //Set desired Length of new String
        while(sb.length() < desiredLength){ //While new String length < desired Length
            int randomchar = rnd.nextInt(CHARS.length()); //Generate random int to search index in CHARS
            sb.append(CHARS.charAt(randomchar)); //Append CHARS[randomchar] to sb
        }
        return sb.toString();
    }

    public Graph(int n){ //Creating a Graph from a number specified of Random Nodes
        this.nodeList = new ArrayList<>();
        this.connections = 0;
        Random randomgen = new Random();

        for(int i = 0; i < n; i++){ //Filling graph with Nodes with names generated Randomly
            String data = this.randomizeString(); //Generate random name
            Node temp = new Node(data); //Creating new Node with previous generated name
            this.nodeList.add(temp); //Adding Node to graph
        }

        //Creating Connections between Graph

        int connectionsDesired = randomgen.nextInt(n*4);
        int connectionsMade = 0;

        while(connectionsMade < connectionsDesired){
            int connectFrom = randomgen.nextInt(n);
            int connectTo = randomgen.nextInt(n);

            Node source = this.nodeList.get(connectFrom);
            Node destination = this.nodeList.get(connectTo);

            if (!indirectedConnectionExists(source, destination)) {
                this.addIndirectedConnection(source, destination);
            }
            connectionsMade++;
        }
    }

    public Graph(String op){
        this.nodeList = new ArrayList<>();
        this.connections = 0;
        this.open_file(op);
    }

    public void addIndirectedConnection(Node source, Node dest){
        if(!this.inNodeList(source)){
            this.nodeList.add(source);
        }
        else{
            source = this.getNode(source);
        }
        if(!this.inNodeList(dest)){
            this.nodeList.add(dest);
        }
        else{
            dest = this.getNode(dest);
        }
        source.addEdge(dest);
        dest.addEdge(source);
        this.connections += 2;
    }

    public void addDirectedConnection(Node source, Node dest){
        if(!this.inNodeList(source)){//If Source is not in Node List
            this.nodeList.add(source);
        }
        else{ //Get node in Node List
            source = this.getNode(source);
        }
        if(!this.inNodeList(dest)){
            this.nodeList.add(dest);
        }
        else{
            dest = this.getNode(dest);
        }
        source.addEdge(dest);
        this.connections++;
    }

    private boolean inNodeList(Node search){
        for(Node u : this.nodeList){
            if(u.getId().equals(search.getId())){
                return true;
            }
        }
        return false;
    }

    private Node getNode(Node foo){
        Node temp = null;
        for(Node u : this.nodeList){
            if(u.getId().equals(foo.getId())){
                temp = u;
            }
        }
        return temp;
    }

    private boolean indirectedConnectionExists(Node source, Node dest){
        return (source.getChildren().contains(dest) && dest.getChildren().contains(source));
    }

    public void printNodesInGraph(){
        System.out.print("V = { ");
        for(Node i : this.nodeList){
            System.out.print(i.getId() + " ");
        }
        System.out.print("}");
        System.out.println("");
        System.out.println("");
    }

    public void printConnectionsInGraph(){
        System.out.println("Connections in Graph " + "(" + this.connections + ")" + " :"); //Connections in Graph (n):

        for(Node i : this.nodeList){ //Visit ALL Nodes of the Graph
            System.out.print("Adj[" + i.getId() + "] : ");//Print: Adj[i]

            for(Node current : i.getChildren()){
                System.out.print(current.getId() + " "); //Print all Children of Current Node
            }
            System.out.println(""); //Print new line between "Adj[...]
        }

        System.out.println(""); //Print new Line
    }

    public void DFSrec_all(){
        for(Node u : this.nodeList){
            u.setColor("White");
            u.setFather(null);
        }

        for(Node u : this.nodeList){
            this.DFSrec(u);
            for(Node v : this.nodeList){
                v.setColor("White");
                v.setFather(null);
            }
        }
    }

    public void DFSrec(){
        for(Node u : this.nodeList){
            u.setColor("White");
            u.setFather(null);
        }
        //Depth in DFS = Time that Node was Explored
        int time = 0; //Counter of time
        int i = 0;

        for(Node u : this.nodeList){
            if(u.getColor().equals("White")){
                i++; //Increment counter
                System.out.println(i + ".- " + "DFS Connections starting from " + u.getId());
                DFS_visit(u, time);
                System.out.println("");
            }
        }

    }

    public void DFSrec(Node start){
        for(Node u : this.nodeList){
            u.setColor("White");
            u.setFather(null);
        }
        //Depth in DFS = Time that Node was Explored
        int time = 0; //Counter of time
        int i = 0;

        i++; //Increment counter
        System.out.println(i + ".- " + "DFS Connections starting from " + start.getId());
        DFS_visit(start, time);
        System.out.println("");

    }



    private void DFS_visit(Node u, int time){
        time++; //Increment time
        u.setDepth(time); //Set depth of U (time of exploration)
        u.setColor("Gray"); //Set color to Gray
        System.out.println(u); //Print U
        for(Node v : u.getChildren()){ //Visit ALL children of U
            if(v.getColor().equals("White")){
                v.setFather(u); //Set father of V to U
                DFS_visit(v, time); //Visit V through DFS
                return;
            }
        }
        u.setColor("Black"); //U has been fully-explored (Color = Black)
        time++; //Increase time
    }

    public void DFSrand(){
        for(Node u : this.nodeList){
            u.setColor("White");
            u.setFather(null);
        }
        //Depth in DFS = Time that Node was Explored
        int time = 0; //Counter of time
        int i = 0;
        Random randgen = new Random();
        int start = randgen.nextInt(this.nodeList.size());
        Node u = this.nodeList.get(start);
        System.out.println(i + ".- " + "DFS Connections starting from " + u.getId());
        DFS_visit_rand(u, time);
        System.out.println("");

    }

    private void DFS_visit_rand(Node u, int time){
        time++; //Increment time
        u.setDepth(time); //Set depth of U (time of exploration)
        u.setColor("Gray"); //Set color to Gray
        System.out.println(u); //Print U
        Random randg = new Random();
        int nodesVisited = 0;
        while(true){
            int posChild = randg.nextInt(u.getChildren().size());
            if(u.getChildren().get(posChild).getColor().equals("White")){
                Node v = u.getChildren().get(posChild);
                v.setFather(u);
                DFS_visit_rand(v, time);
                break;
            }
            else if(nodesVisited ==  u.getChildren().size()){
                break;
            }
            nodesVisited++;
        }

        u.setColor("Black"); //U has been fully-explored (Color = Black)
        time++; //Increase time
    }

    public void DFSi(){
        for(Node u : this.nodeList){ //Reset values of all Nodes in Graph
            u.setColor("White");
            u.setFather(null);
            u.setDepth(0);
        }

        System.out.println("DFS Forest of Graph:\n");

        int i = 0; //Counter of Tree being created

        for(Node u : this.nodeList){ //Visit ALL Nodes on |V|

            if(u.getColor().equals("White")){ //If Node hasn't been visited before
                i++; //Increment i
                Tree temp = new Tree(); //Create New Tree (structure to be printed)
                Stack<Node> dfs = new Stack<Node>(); //Create new Stack of Nodes
                u.setColor("Gray"); //Mark U as Gray
                dfs.push(u); //Add U to Stack
                TreeNode nU = new TreeNode(u); //Create new Tree Node from U
                temp.addNodeToTree(nU); //Add U to Tree (as Root)
                int time = 0;
                //Depth in DFS = Time that Node was Explored

                while(!dfs.isEmpty()){
                    Node current = dfs.pop(); //Extract Node to analyze from Stack
                    time++; //Increment time
                    current.setDepth(time); //Set Depth of Current
                    TreeNode father = new TreeNode(current); //Create new Tree Node from Current

                    for(Node v : current.getChildren()){ //Visit all children of Current
                        if(v.getColor().equals("White")){ //If Node hasn't been visited before
                            v.setColor("Gray"); //Set Color of Node being visited to Gray
                            v.setDepth(current.getDepth()); //
                            dfs.push(v); //Add Node to Stack to finish processing later
                            TreeNode nV = new TreeNode(v); //Create new Tree Node from Node being visited
                            nV.setFather(father); //Set father of V to Current
                            temp.addNodeToTree(nV); //Add node V to Tree
                        }
                    }

                    current.setColor("Black");// Mark Current as Black (Fully-Analized)
                }

                temp.printTree(i); //Print Tree created from DFS
            }
        }
    }

    public void BFS(Node toPrint){
        for(Node u : this.nodeList){//Re-initializing values of nodes in graph
            if(u != toPrint){
                u.setColor("White");
                u.setFather(null);
            }
        }

        System.out.println("BFS Tree of Node Specified (" + toPrint.getId() + "):\n");

        toPrint.setColor("Gray");//toPrint is going to be fully processed
        toPrint.setDepth(0);//Depth of toPrint is 0
        toPrint.setFather(null);//Since is root of new tree, it doesn't have a father

        Tree toRet = new Tree();//Tree structure to return
        TreeNode tPr = new TreeNode(toPrint);
        toRet.addNodeToTree(tPr);

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(toPrint);//Adds toPrint to queue
        int currentLevel = 1;//Starting level 1
        int nextLevel = 0;//Next level is 0 since toPrint connections hasn't been visited

        while(!queue.isEmpty()){//While queue is not empty
            Node n = queue.remove();//n is first of queue
            n.setColor("Gray");//n is being fully processed
            TreeNode nTree = new TreeNode(n);

            if(!n.getChildren().isEmpty()){//While queue is not empty
                for(Node v : n.getChildren()){//Iterate on connections of n
                    if(v.getColor().equals("White")) {//If v has not been processed yet
                        v.setFather(n);//v is son of n
                        v.setColor("Gray");//Gray node, not fully processed
                        v.setDepth(n.getDepth() + 1);//Set depth + 1 from father
                        TreeNode vTree = new TreeNode(v);
                        vTree.setFather(nTree);
                        toRet.addNodeToTree(vTree);//Add connection from n to v
                        queue.add(v);//Add v to queue to process later
                        nextLevel++;//Increment next level to process
                    }
                }
            }
            currentLevel--;//Decrease current level being processed

            if(currentLevel == 0){//If all nodes of current level (depth) have been processed
                currentLevel = nextLevel;//Move forward to next level
                nextLevel = 0;//Set next level as 0
            }
            n.setColor("Black");//Node has been fully processed
        }

        toRet.printTree();//Print tree created from BFS
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    private void open_file(String op){
        File inFile = null;
        FileInputStream fr;
        InputStreamReader frint;
        FileDialog fd;
        BufferedReader bufReader;
        int num;
        boolean check = true;
        int cont = 0;

        String line, doc, path, filename;

        try{
            if(op.toLowerCase().equals("knight")){
                ClassLoader classLoader = getClass().getClassLoader();
                doc = "knight.txt";
                inFile = new File(classLoader.getResource("knight.txt").getFile());
            }
            else{
                ClassLoader classLoader = getClass().getClassLoader();
                doc = "grafo.txt";
                inFile = new File(classLoader.getResource("grafo.txt").getFile());
            }

            JOptionPane.showMessageDialog(null, "Document read:" + doc);//Shows to user filename and path of file read
            fr = new FileInputStream(inFile);
            frint = new InputStreamReader(fr);
            bufReader = new BufferedReader(frint);
			/*Count number of data in file
			 * Counting is done by reading 1 value in 1 line, which equals to 1 data read
			 */
            while(check){
                if(bufReader.readLine() != null){
                    cont++;
                }
                else{
                    check = false;//Exit while loop
                }

            }
            JOptionPane.showMessageDialog(null, "Number of lines in file: " + cont);//Returns number of values found in file

            check = true;//resets check variable to true

            fr.getChannel().position(0);
            frint = new InputStreamReader(fr);
            bufReader = new BufferedReader(frint);

            if(op.toLowerCase().equals("direct")) {
                while (check) {
                    line = bufReader.readLine();
                    if (line == null) {//If line read is empty, exit while loop
                        check = false;
                    } else {
                        String[] words = line.split("\\s+");
                        String from = words[0];
                        String to = words[1];
                        Node start = new Node(from);
                        Node dest = new Node(to);
                        this.addDirectedConnection(start, dest);
                    }
                }
            }

            else if(op.toLowerCase().equals("indirect")){
                while(check){
                    line = bufReader.readLine();
                    if (line == null){//If line read is empty, exit while loop
                        check = false;
                    }
                    else{
                        String[] words = line.split("\\s+");
                        String from = words[0];
                        String to = words[1];
                        Node start = new Node(from);
                        Node dest = new Node(to);
                        this.addIndirectedConnection(start, dest);
                    }
                }
            }
            else if(op.toLowerCase().equals("knight")){
                while(check){
                    line = bufReader.readLine();
                    if (line == null){//If line read is empty, exit while loop
                        check = false;
                    }
                    else{
                        String[] words = line.split("\\s+");
                        String from = words[0];
                        Node start = new Node(from);
                        for(int i = 1; i < words.length; i++){
                            String to = words[i];
                            Node dest = new Node(to);
                            this.addIndirectedConnection(start, dest);
                        }
                    }
                }
            }

        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error opening file: \n"+e.getMessage());
        }
    }


}