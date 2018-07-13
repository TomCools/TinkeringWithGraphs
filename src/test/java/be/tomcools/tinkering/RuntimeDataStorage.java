package be.tomcools.tinkering;

import com.steelbridgelabs.oss.neo4j.structure.Neo4JGraph;
import com.steelbridgelabs.oss.neo4j.structure.Neo4JGraphFactory;
import com.steelbridgelabs.oss.neo4j.structure.providers.Neo4JNativeElementIdProvider;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Transaction;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.io.graphml.GraphMLIo;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

public class RuntimeDataStorage {

    @Test
    public void inMemory() throws Exception {
        Graph g  = TinkerGraph.open();

        g.addVertex("Example");

        g.close();
    }

    //In docs: http://tinkerpop.apache.org/docs/current/reference/#_configuration_3
    @Test
    public void asFile() throws Exception {
        String file = "/tmp/tinkergraph";
        String serialisation = "graphml"; //or "graphson" "gryo"

        Configuration config = new BaseConfiguration();
        config.addProperty("gremlin.tinkergraph.graphLocation", file);
        config.addProperty("gremlin.tinkergraph.graphFormat", serialisation);

        Graph g = TinkerGraph.open(config);

        g.addVertex("Example");

        g.close();
    }

    @Test
    @Ignore //Manual, requires running database (and transaction) with correct credentials
    public void toRemoteNeo4j() throws Exception {
        Driver driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("neo4j", "belgacom"));

        Graph g = new Neo4JGraph(driver, new Neo4JNativeElementIdProvider(), new Neo4JNativeElementIdProvider());

        try(Transaction transaction = g.tx()) {
            Vertex tom = g.addVertex(T.label,"Person", "name", "Tom");
            Vertex silke = g.addVertex(T.label,"Person", "name", "Silke");
            tom.addEdge("IS_MARRIED_TO",silke);
            silke.addEdge("IS_MARRIED_TO",tom);

            transaction.commit();
        }
    }
}
