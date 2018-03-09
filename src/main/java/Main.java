import graphs.Graph;
import graphs.Node;

import java.util.Scanner;

public class Main {

    public static void main(String... args){

        //BFS example
        //Graph 1 from class
        Graph classroomBFS = new Graph();
        Node nV = new Node("v");
        Node nR = new Node("r");
        Node nS = new Node("s");
        Node nW = new Node("w");
        Node nX = new Node("x");
        Node nT = new Node("t");
        Node nY = new Node("y");
        Node nU = new Node("u");

        //Graph from class is undirected
        classroomBFS.addIndirectedConnection(nV, nR);//Undirected connection from 'v' to 'r'
        classroomBFS.addIndirectedConnection(nR, nS);//Undirected connection from 'r' to 's'
        classroomBFS.addIndirectedConnection(nS, nW);//Undirected connection from 's' to 'w'
        classroomBFS.addIndirectedConnection(nW, nX);//Undirected connection from 'w' to 'x'
        classroomBFS.addIndirectedConnection(nW, nT);//Undirected connection from 'w' to 't'
        classroomBFS.addIndirectedConnection(nX, nT);//Undirected connection from 'x' to 't'
        classroomBFS.addIndirectedConnection(nX, nY);//Undirected connection from 'x' to 'y'
        classroomBFS.addIndirectedConnection(nX, nU);//Undirected connection from 'x' to 'u'
        classroomBFS.addIndirectedConnection(nT, nU);//Undirected connection from 't' to 'u'
        classroomBFS.addIndirectedConnection(nY, nU);//Undirected connection from 'y' to 'u'

        //Print Nodes in Graph
        classroomBFS.printNodesInGraph();
        //Print connections between Nodes
        classroomBFS.printConnectionsInGraph();

        classroomBFS.BFS(nS);


        //DFS Example
        //Graph 2 from class, load from file

        /*try{
            Scanner in = new Scanner(System.in);
            System.out.println("Type in Direct or Indirect");
            String op = in.next();
            in.nextLine();
            in.close();

            if(op.toLowerCase().equals("direct") || op.toLowerCase().equals("indirect") || op.toLowerCase().equals("knight")) {
                Graph classroom2 = new Graph(op);
                classroom2.printNodesInGraph();
                classroom2.printConnectionsInGraph();
                classroom2.DFSi();
                classroom2.DFSrec();
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        catch (IllegalArgumentException e){
            System.out.println("Case not specified, try again!");
        }
        finally {
            System.exit(0);
        }*/

        /*try{
            Scanner in = new Scanner(System.in);
            System.out.println("Type in Direct or Indirect or Knight");
            String op = in.next();
            in.nextLine();
            in.close();

            if(op.toLowerCase().equals("direct") || op.toLowerCase().equals("indirect") || op.toLowerCase().equals("knight")) {
                Graph classroom2 = new Graph(op);
                classroom2.printNodesInGraph();
                classroom2.printConnectionsInGraph();
                //classroom2.DFSrand();
                classroom2.DFSrec_all();
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        catch (IllegalArgumentException e){
            System.out.println("Case not specified, try again!");
        }
        finally {
            System.exit(0);
        }*/


        //Graph created randomly
        /*try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter number of nodes to create randomly:");
            int n = in.nextInt();
            if(n <= 0) throw new ArithmeticException();
            in.nextLine();
            in.close();

            Graph ranG = new Graph(n);
            ranG.printNodesInGraph();
            ranG.printConnectionsInGraph();
            ranG.BFS(ranG.getNodeList().get(0));
            ranG.DFSi();
            ranG.DFSrec();

        }
        catch (ArithmeticException e){
            System.out.println("Number cannot be negative or 0!");
        }
        finally {
            System.exit(0);
        }*/

    }
}
