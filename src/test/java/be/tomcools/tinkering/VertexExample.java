package be.tomcools.tinkering;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.junit.Test;

public class VertexExample {

    @Test
    public void basicSetup() throws Exception {
        Graph g = TinkerGraph.open();

        Vertex me = g.addVertex("Tom");
        Vertex myWife = g.addVertex("Silke");

        me.addEdge("married-to",myWife);
        myWife.addEdge("married-to",me);

        g.traversal().V().outE().where(__.inV().hasLabel("Tom"))
                .forEachRemaining(System.out::println);

        g.traversal().E().hasLabel("married-to").bothV();

        g.close();
    }
}
