package se.fishtank.dag;

import org.junit.Assert;
import org.junit.Test;

public class VertexTest {

    @Test
    public void testVertex() {
        DirectedAcyclicGraph graph = new DirectedAcyclicGraph();

        DirectedAcyclicGraph.Vertex vertex1 = graph.addVertex("a");
        Assert.assertEquals(vertex1, graph.addVertex("a"));
        Assert.assertEquals("a", vertex1.label);
        Assert.assertTrue(vertex1.getChildren().isEmpty());

        DirectedAcyclicGraph.Vertex vertex2 = graph.addVertex("b");
        Assert.assertEquals("b", vertex2.label);

        vertex1.addEdgeTo(vertex2);
        Assert.assertEquals(1, vertex1.getChildren().size());
        Assert.assertTrue(vertex2.getChildren().isEmpty());
        Assert.assertEquals(vertex2, vertex1.getChildren().iterator().next());
    }

}
