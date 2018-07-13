package be.tomcools.tinkering;

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Transaction;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.junit.Test;

public class Transactions {

    @Test(expected = UnsupportedOperationException.class)
    public void transaction() {
        Graph g = TinkerGraph.open();

        g.tx();
    }
}
