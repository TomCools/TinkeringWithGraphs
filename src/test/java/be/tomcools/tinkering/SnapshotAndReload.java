package be.tomcools.tinkering;

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.io.graphml.GraphMLIo;
import org.apache.tinkerpop.gremlin.structure.io.graphson.GraphSONIo;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.junit.Test;

import java.io.IOException;

public class SnapshotAndReload {

    @Test
    public void saveSnapshot() throws Exception {
        Graph g = TinkerGraph.open();

        Vertex me = g.addVertex("Tom");
        Vertex myWife = g.addVertex("Silke");

        me.addEdge("married-to",myWife);
        myWife.addEdge("married-to",me);

        g.io(GraphMLIo.build()).writeGraph("export.ml");
        //or         GraphSONIo.build()

        g.close();
    }

    @Test
    public void loadSnapshot() throws IOException {
        Graph g = TinkerGraph.open();

        g.io(GraphMLIo.build()).readGraph("export.ml");

        System.out.println(g);
    }
}
