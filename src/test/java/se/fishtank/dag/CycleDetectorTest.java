package se.fishtank.dag;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CycleDetectorTest {

    @Test
    public void testCycleDetection1() {
        DirectedAcyclicGraph graph = new DirectedAcyclicGraph();
        DirectedAcyclicGraph.Vertex a = graph.addVertex("a");
        DirectedAcyclicGraph.Vertex b = graph.addVertex("b");
        DirectedAcyclicGraph.Vertex c = graph.addVertex("c");

        Assert.assertNull(DirectedAcyclicGraph.addEdge(a, b));
        Assert.assertNull(DirectedAcyclicGraph.addEdge(b, c));
    }

    @Test
    public void testCycleDetection2() {
        DirectedAcyclicGraph graph = new DirectedAcyclicGraph();
        DirectedAcyclicGraph.Vertex a = graph.addVertex("a");
        DirectedAcyclicGraph.Vertex b = graph.addVertex("b");
        DirectedAcyclicGraph.Vertex c = graph.addVertex("c");

        Assert.assertNull(DirectedAcyclicGraph.addEdge(a, b));
        Assert.assertNull(DirectedAcyclicGraph.addEdge(b, c));

        List<DirectedAcyclicGraph.Vertex> cycle = DirectedAcyclicGraph.addEdge(c, a);
        Assert.assertNotNull(cycle);
        Assert.assertEquals(4, cycle.size());

        int i = 0;
        DirectedAcyclicGraph.Vertex[] vertices = new DirectedAcyclicGraph.Vertex[] { a, b, c, a };
        for (DirectedAcyclicGraph.Vertex v : cycle)
            Assert.assertEquals(vertices[i++], v);

        Assert.assertNotNull(DirectedAcyclicGraph.CycleDetector.detectCycle(graph));
    }

    @Test
    public void testCycleDetection3() {
        DirectedAcyclicGraph graph = new DirectedAcyclicGraph();
        DirectedAcyclicGraph.Vertex a = graph.addVertex("a");
        DirectedAcyclicGraph.Vertex b = graph.addVertex("b");
        DirectedAcyclicGraph.Vertex c = graph.addVertex("c");
        DirectedAcyclicGraph.Vertex d = graph.addVertex("d");

        Assert.assertNull(DirectedAcyclicGraph.addEdge(a, b));
        Assert.assertNull(DirectedAcyclicGraph.addEdge(b, c));
        Assert.assertNull(DirectedAcyclicGraph.addEdge(b, d));
        Assert.assertNull(DirectedAcyclicGraph.addEdge(a, d));
    }

    @Test
    public void testCycleDetection4() {
        DirectedAcyclicGraph graph = new DirectedAcyclicGraph();
        DirectedAcyclicGraph.Vertex a = graph.addVertex("a");
        DirectedAcyclicGraph.Vertex b = graph.addVertex("b");
        DirectedAcyclicGraph.Vertex c = graph.addVertex("c");
        DirectedAcyclicGraph.Vertex d = graph.addVertex("d");

        Assert.assertNull(DirectedAcyclicGraph.addEdge(a, b));
        Assert.assertNull(DirectedAcyclicGraph.addEdge(b, c));
        Assert.assertNull(DirectedAcyclicGraph.addEdge(b, d));
        Assert.assertNull(DirectedAcyclicGraph.addEdge(a, d));

        List<DirectedAcyclicGraph.Vertex> cycle = DirectedAcyclicGraph.addEdge(c, a);
        Assert.assertNotNull(cycle);
        Assert.assertEquals(4, cycle.size());

        int i = 0;
        DirectedAcyclicGraph.Vertex[] vertices = new DirectedAcyclicGraph.Vertex[] { a, b, c, a };
        for (DirectedAcyclicGraph.Vertex v : cycle)
            Assert.assertEquals(vertices[i++], v);

        Assert.assertNotNull(DirectedAcyclicGraph.CycleDetector.detectCycle(graph));
    }

    @Test
    public void testCycleDetection5() {
        DirectedAcyclicGraph graph = new DirectedAcyclicGraph();
        DirectedAcyclicGraph.Vertex a = graph.addVertex("a");
        DirectedAcyclicGraph.Vertex b = graph.addVertex("b");
        DirectedAcyclicGraph.Vertex c = graph.addVertex("c");
        DirectedAcyclicGraph.Vertex d = graph.addVertex("d");
        DirectedAcyclicGraph.Vertex e = graph.addVertex("e");
        DirectedAcyclicGraph.Vertex f = graph.addVertex("f");
        DirectedAcyclicGraph.Vertex g = graph.addVertex("g");
        DirectedAcyclicGraph.Vertex h = graph.addVertex("h");

        Assert.assertNull(DirectedAcyclicGraph.addEdge(a, b));
        Assert.assertNull(DirectedAcyclicGraph.addEdge(b, c));
        Assert.assertNull(DirectedAcyclicGraph.addEdge(b, f));
        Assert.assertNull(DirectedAcyclicGraph.addEdge(f, g));
        Assert.assertNull(DirectedAcyclicGraph.addEdge(g, h));
        Assert.assertNull(DirectedAcyclicGraph.addEdge(c, d));
        Assert.assertNull(DirectedAcyclicGraph.addEdge(d, e));

        List<DirectedAcyclicGraph.Vertex> cycle = DirectedAcyclicGraph.addEdge(e, b);
        Assert.assertNotNull(cycle);
        Assert.assertEquals(5, cycle.size());

        int i = 0;
        DirectedAcyclicGraph.Vertex[] vertices = new DirectedAcyclicGraph.Vertex[] { b, c, d, e, b };
        for (DirectedAcyclicGraph.Vertex v : cycle)
            Assert.assertEquals(vertices[i++], v);

        Assert.assertNotNull(DirectedAcyclicGraph.CycleDetector.detectCycle(graph));
    }

}
