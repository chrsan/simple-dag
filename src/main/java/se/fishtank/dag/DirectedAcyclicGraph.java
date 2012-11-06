package se.fishtank.dag;

import java.util.*;

public final class DirectedAcyclicGraph {

    private final HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();

    public Collection<Vertex> getVertices() {
        return Collections.unmodifiableCollection(vertices.values());
    }

    public Set<String> getLabels() {
        return Collections.unmodifiableSet(vertices.keySet());
    }

    public Vertex addVertex(String label) {
        Vertex vertex = vertices.get(label);
        if (vertex == null) {
            vertex = new Vertex(label, this);
            vertices.put(label, vertex);
        }

        return vertex;
    }

    public Vertex getVertex(String label) {
        return vertices.get(label);
    }

    public boolean hasVertex(Vertex vertex) {
        return vertex.graph == this && vertices.containsKey(vertex.label);
    }

    public static List<Vertex> addEdge(Vertex from, Vertex to) {
        from.addEdgeTo(to);
        to.addEdgeFrom(from);
        return CycleDetector.detectCycle(to);
    }

    public static boolean hasEdge(Vertex from, Vertex to) {
        return from.children.contains(to);
    }

    public static void removeEdge(Vertex from, Vertex to) {
        from.removeEdgeTo(to);
        to.removeEdgeFrom(from);
    }

    public static final class Vertex {

        public final String label;

        private final DirectedAcyclicGraph graph;

        private final LinkedHashSet<Vertex> children = new LinkedHashSet<Vertex>();

        private final LinkedHashSet<Vertex> parents = new LinkedHashSet<Vertex>();

        private Vertex(String label, DirectedAcyclicGraph graph) {
            this.label = label;
            this.graph = graph;
        }

        public boolean addEdgeTo(Vertex other) {
            return children.add(other);
        }

        public boolean removeEdgeTo(Vertex other) {
            return children.remove(other);
        }

        public boolean addEdgeFrom(Vertex other) {
            return parents.add(other);
        }

        public boolean removeEdgeFrom(Vertex other) {
            return parents.remove(other);
        }

        public Set<Vertex> getChildren() {
            return Collections.unmodifiableSet(children);
        }

        public Set<Vertex> getParents() {
            return Collections.unmodifiableSet(parents);
        }

        public boolean isLeaf() {
            return children.isEmpty();
        }

        public boolean isRoot() {
            return parents.isEmpty();
        }

        public boolean isConnected() {
            return isLeaf() || isRoot();
        }

        @Override
        public String toString() {
            return "Vertex{" + label + "}";
        }

    }

    public static enum VertexState {
        NOT_VISITED, VISITING, VISITED
    }

    private static boolean isNotVisited(Vertex vertex, Map<Vertex, VertexState> vertexState) {
        VertexState state = vertexState.get(vertex);
        return state == null || state == VertexState.NOT_VISITED;
    }

    private static boolean isVisiting(Vertex vertex, Map<Vertex, VertexState> vertexState) {
        VertexState state = vertexState.get(vertex);
        return state != null && state == VertexState.VISITING;
    }

    public static final class CycleDetector {

        public static List<Vertex> detectCycle(DirectedAcyclicGraph graph) {
            Map<Vertex, VertexState> vertexState = new HashMap<Vertex, VertexState>();
            for (Vertex v : graph.vertices.values()) {
                if (isNotVisited(v, vertexState)) {
                    List<Vertex> result = detectCycle(v, vertexState);
                    if (result != null)
                        return result;
                }
            }

            return null;
        }

        public static List<Vertex> detectCycle(Vertex vertex) {
            return detectCycle(vertex, new HashMap<Vertex, VertexState>());
        }

        private static List<Vertex> detectCycle(Vertex vertex, Map<Vertex, VertexState> vertexState) {
            LinkedList<Vertex> result = new LinkedList<Vertex>();
            if (dfs(vertex, vertexState, result)) {
                Vertex v = result.peek();
                int pos = result.lastIndexOf(v);
                List<Vertex> list = result.subList(0, pos + 1);
                Collections.reverse(list);
                return list;
            }

            return null;
        }

        private static boolean dfs(Vertex vertex, Map<Vertex, VertexState> vertexState, LinkedList<Vertex> result) {
            result.push(vertex);
            vertexState.put(vertex, VertexState.VISITING);
            for (Vertex v : vertex.children) {
                if (isNotVisited(v, vertexState)) {
                    if (dfs(v, vertexState, result))
                        return true;
                } else if (isVisiting(v, vertexState)) {
                    result.push(v);
                    return true;
                }
            }

            vertexState.put(vertex, VertexState.VISITED);
            result.pop();
            return false;
        }

    }

    public static final class TopologicalSorter {

        public static List<Vertex> sort(DirectedAcyclicGraph graph) {
            Map<Vertex, VertexState> vertexState = new HashMap<Vertex, VertexState>();
            LinkedList<Vertex> result = new LinkedList<Vertex>();
            for (Vertex v : graph.vertices.values()) {
                if (isNotVisited(v, vertexState))
                    dfs(v, vertexState, result);
            }

            return result;
        }

        public static List<Vertex> sort(Vertex vertex) {
            Map<Vertex, VertexState> vertexState = new HashMap<Vertex, VertexState>();
            LinkedList<Vertex> result = new LinkedList<Vertex>();
            dfs(vertex, vertexState, result);
            return result;
        }

        private static void dfs(Vertex vertex, Map<Vertex, VertexState> vertexState, LinkedList<Vertex> result) {
            vertexState.put(vertex, VertexState.VISITING);
            for (Vertex v : vertex.children) {
                if (isNotVisited(v, vertexState))
                    dfs(v, vertexState, result);
            }

            vertexState.put(vertex, VertexState.VISITED);
            result.add(vertex);
        }

    }

}
