package be.tomcools.tinkering;

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.io.graphml.GraphMLIo;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws IOException {
        Graph g = TinkerGraph.open();

        Vertex me = g.addVertex("Tom");
        Vertex myWife = g.addVertex("Silke");

        me.addEdge("married-to",myWife);
        myWife.addEdge("married-to",me);

        g.io(GraphMLIo.build()).writeGraph("test.ml");
    }
}
