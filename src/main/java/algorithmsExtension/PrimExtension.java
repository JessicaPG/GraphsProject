
/**
 * From GraphStream
 */

package algorithmsExtension;
import org.graphstream.algorithm.Prim;
import org.graphstream.graph.Graph;
import utils.CreationGraph;


public class PrimExtension {
        public static void main(String ... args) {
            Graph graph = CreationGraph.creationGraph();

            String css = "edge .notintree {size:1px;fill-color:gray;} " +
                    "edge .intree {size:5px;fill-color:green;}";

            graph.addAttribute("ui.stylesheet", css);
            graph.display();

            Prim prim = new Prim("length","ui.class", "intree", "notintree");
            prim.init(graph);
            prim.compute();
        }
    }

