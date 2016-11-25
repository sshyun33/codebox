package datastructure.graph;

import com.google.common.collect.Sets;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GuavaGraphTest {
    @Test
    public void testDefaultGraph() throws Exception {
        MutableGraph<Integer> graph = GraphBuilder.undirected().build();

        graph.addNode(1);

        graph.putEdge(2, 3);
        assertThat(graph.nodes(), is(Sets.newHashSet(1, 2, 3)));

        Set<Integer> successorsOfTwo = graph.successors(2);
        assertThat(successorsOfTwo, hasItem(3));

        graph.putEdge(2, 3); // Nothing. Graph Doesn't support parallel edges

        assertThat(successorsOfTwo, hasItem(3));

        assertThat(graph.adjacentNodes(2), is(Sets.newHashSet(3)));
    }

    @Test
    public void testValuedGraph() throws Exception {
        MutableValueGraph<Integer, Double> weightedGraph = ValueGraphBuilder.directed().build();

        weightedGraph.addNode(1);

        weightedGraph.putEdgeValue(2, 3, 1.5);  // also adds nodes 2 and 3 if not already present
        assertThat(weightedGraph.nodes(), is(Sets.newHashSet(1, 2, 3)));

        weightedGraph.putEdgeValue(3, 5, 1.5);  // edge values (like Map values) need not be unique
        assertThat(weightedGraph.nodes(), is(Sets.newHashSet(1, 2, 3, 5)));

        weightedGraph.putEdgeValue(2, 3, 2.0);  // updates the value for (2,3) to 2.0
        assertThat(weightedGraph.edgeValue(2, 3), is(2.0));

        assertThat(weightedGraph.adjacentNodes(3), is(Sets.newHashSet(2, 5)));
    }
}
