/**
 * From GraphStream
 */

package algorithmsExtension;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.Viewer;
import utils.CreationGraph;

public class DijkstraExtension {

    public static void main(String[] args) {
        Graph g = CreationGraph.creationGraph();
        String source = args[0];
        String destination = args[1];

        System.out.println("source: " + source + "destination " + destination);

        Viewer viewer = g.display();
        viewer.disableAutoLayout();
        viewer.enableAutoLayout();

        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");

        // Compute the shortest paths in g from A to all nodes
        dijkstra.init(g);
        dijkstra.setSource(g.getNode(source));
        dijkstra.compute();

        // Print the lengths of all the shortest paths
        for (Node node : g)
            System.out.printf("%s->%s:%10.2f%n", dijkstra.getSource(), node,
                    dijkstra.getPathLength(node));

        // Color in green all the edges in the shortest path tree
        for (Edge edge : dijkstra.getTreeEdges())
            edge.addAttribute("ui.style", "fill-color: green;");

        // Print the shortest path from A to x
        System.out.println("/nThe shortest path from " + source + " to " + destination + " is: ");
        System.out.println(dijkstra.getPath(g.getNode(destination)));
    }
}