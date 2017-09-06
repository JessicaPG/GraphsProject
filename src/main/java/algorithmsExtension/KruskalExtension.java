/**
 * From GraphStream
 */

package algorithmsExtension;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.algorithm.Kruskal;
import utils.CreationGraph;

public class KruskalExtension {

    public static void main(String ... args) {

        Graph graph = CreationGraph.creationGraph();
        //put id of the node and weight of each edge
        for (Node n : graph)
            n.addAttribute("label", n.getId());
        for (Edge e : graph.getEachEdge())
            e.addAttribute("label", "" + (int) e.getNumber("length"));


        String css = "edge .notintree {size:1px;fill-color:gray;} " +
                "edge .intree {size:3px;fill-color:green;}";

        graph.addAttribute("ui.stylesheet", css);
        graph.display();

        Kruskal kruskal = new Kruskal("length", "ui.class", "intree", "notintree");

        kruskal.init(graph);
        kruskal.compute();
    }
}


