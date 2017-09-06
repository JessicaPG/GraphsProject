
/**
 @Date July 2017
 @Author Jessica Pérez Guijarro
 */

package utils;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;


public abstract class CreationGraph {

    private static BufferedReader buffer = null;

    /**
     * Create a graph
     * @return graph
     */
    public static Graph creationGraph() {
         Graph graph = new SingleGraph("graph");
         ArrayList<String> nodesiD = null;


         try {
             buffer = new BufferedReader(new InputStreamReader(System.in));

             System.out.println("Is a directional graph?");
             Scanner scanner = new Scanner(System.in);

             boolean isBidirectional = scanner.nextBoolean();

             nodesiD = readVertex(graph);
             readNode(nodesiD, graph, isBidirectional);

             for (Node n : graph)
                 n.addAttribute("label", n.getId());
             for (Edge e : graph.getEachEdge())
                 e.addAttribute("label", "" + (int) e.getNumber("length"));


         } finally {
             if (buffer != null) {
                 try {
                     buffer.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }

         }
         return graph;
     }

    /**
     * Reads from the user input how many vertex has the graph to be created
     * @param graph
     * @return arrayId (An array of the id's nodes)
     */
     protected static ArrayList readVertex(Graph graph) {
         ArrayList<String> arrayId = null;

         try {
             //Read vertex
             System.out.println("How many vertex have your graph?");
             int input = Integer.parseInt(buffer.readLine());

             //Read vertex's Id
             System.out.println("Please, introduce each vertex's id" +
                     "example: A (Pulse enter key)" +
                     "         B (Pulse enter key)");

             String nodeId;
             arrayId = new ArrayList<String>();
             int vertexCounter = 1;

             while (vertexCounter <= input) {
                 nodeId = buffer.readLine();
                 if (!(arrayId.contains(nodeId))) {
                     arrayId.add(nodeId);
                     graph.addNode(nodeId);
                     vertexCounter++;
                 } else {
                     System.out.println("This id already exists. Please, try with another one");
                 }
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
         return arrayId;
     }


    /**
     * Reads user's input and generated graph
     * @param arrayId arrayId (An array of the id's nodes)
     * @param graph
     * @param isBd Is a bidirectional graph
     */
     protected static void readNode(ArrayList arrayId, Graph graph, boolean isBd){
             try {
                 //read node e1 -> node2 and weight of the edge
                 System.out.println("Format for introducing the weight of the edge is: node1 node2 weight " +
                         "Please press q to exit");
                 HashMap<String,Integer>  edgesWeight = new HashMap<String, Integer>();
                 String edgeInfo;
                 String[] arrayEdges;

                 int countEdge = 1;
                 int numEdges = 0;
                 int maxEdges = (arrayId.size() * (arrayId.size() - 1))/2; //n(n-1)/2

                 edgeInfo = buffer.readLine();

                 while ((!edgeInfo.equals("q")) && numEdges < maxEdges){

                     arrayEdges = edgeInfo.split(" ");

                     String nameNode = arrayEdges[0] + arrayEdges[1];
                     String node1 = arrayEdges[0];
                     String node2 = arrayEdges[1];
                     Integer weight = Integer.parseInt(arrayEdges[2]);

                     if (!(arrayId.contains(node1) && arrayId.contains(node2))){
                         System.out.println("Invalid nodes");
                     }
                     if(edgesWeight.containsKey(nameNode) || edgesWeight.containsKey((node2+node1))){
                         System.out.println("Duplicate values");
                     } else {
                         edgesWeight.put(nameNode,weight);
                         if(isBd){
                             graph.addEdge(nameNode, node1, node2,true).addAttribute("length", weight);
                         } else {
                             graph.addEdge(nameNode, node1, node2).addAttribute("length", weight);
                         }
                         numEdges +=1;
                         countEdge++;
                     }
                     edgeInfo = buffer.readLine();
                 }
             }  catch (IOException e) {
             e.printStackTrace();
         }
    }


    public static String[] readSourceAndDestinationNodes(){
        Scanner scanner = new Scanner(System.in);
        String [] nodesArray = new String[2];

         try {
             scanner.nextLine();
             System.out.println("Which node is the source - origin one for the " +
                     "compute of the shortest paht?");
             String source = scanner.nextLine();
             System.out.println("Which is the destination node for the compute of the " +
                     "shortest path?/n");
             String destination = scanner.nextLine();

             nodesArray[0] = source;
             nodesArray[1] = destination;

         } catch (Exception e) {
             e.printStackTrace();
        }
         return nodesArray;
    }
}

