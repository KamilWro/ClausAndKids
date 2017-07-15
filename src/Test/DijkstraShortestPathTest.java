package Test;

import Model.Graph.DijkstraShortestPath;
import Model.Graph.Edge;
import Model.Graph.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by kamil on 29.06.17.
 */
class DijkstraShortestPathTest {
    private List<Edge> edges;
    private List<Location> nodes;

    @BeforeEach
    public void setUp(){
        edges = new LinkedList<>();
        nodes= new LinkedList<>();
    }

    @Test
    void pathLengthIfGraphContainsACycle() {
        Location node1 = addNode(0,0,3);
        Location node2 = addNode(0,1,3);
        Location node3 = addNode(0,2,3);
        Location node4 = addNode(1,0,3);
        Location node6 = addNode(1,2,3);
        Location node7 = addNode(2,0,3);
        Location node8 = addNode(2,1,3);
        Location node9 = addNode(2,2,3);

        addEdge(node1,node2);
        addEdge(node2,node3);
        addEdge(node3,node6);
        addEdge(node6,node9);
        addEdge(node9,node8);
        addEdge(node8,node7);
        addEdge(node7,node4);
        addEdge(node4,node1);

        DijkstraShortestPath dijkstra = new DijkstraShortestPath(edges);
        dijkstra.execute(node1);
        LinkedList<Location> path = (LinkedList<Location>) dijkstra.getPath(node9);
        assertEquals(5,path.size());
    }

    @Test
    void firstNodeInShortestPath() {
        Location node1 = addNode(0, 0, 4);
        Location node2 = addNode(0, 1, 4);
        Location node3 = addNode(0, 2, 4);
        Location node5 = addNode(1, 1, 4);
        Location node6 = addNode(1, 2, 4);
        Location node9 = addNode(2, 2, 4);
        Location node12 = addNode(3, 2, 4);

        addEdge(node1, node2);
        addEdge(node1, node5);
        addEdge(node2, node3);
        addEdge(node2, node5);
        addEdge(node2, node6);
        addEdge(node3, node6);
        addEdge(node5, node9);
        addEdge(node5, node6);
        addEdge(node6, node9);
        addEdge(node9, node12);

        DijkstraShortestPath dijkstra = new DijkstraShortestPath(edges);
        dijkstra.execute(node1);
        LinkedList<Location> path = (LinkedList<Location>) dijkstra.getPath(node12);
        assertEquals(node5, path.get(1));
    }

    @Test
    void returnEmptyPathIfNoConnection() {
        Location node1 = addNode(0, 0, 3);
        Location node2 = addNode(1, 1, 3);
        Location node3 = addNode(2, 2, 3);

        addEdge(node1, node2);
        DijkstraShortestPath dijkstra = new DijkstraShortestPath(edges);
        dijkstra.execute(node1);
        ArrayList<Location> path = new ArrayList<>(dijkstra.getPath(node3));
        assertEquals(Collections.EMPTY_LIST,path);
    }

    @Test
    void returnEmptyListIfNodesDoNotExistInTheGraph() {
        Location node1 = addNode(0, 0, 3);
        Location node2 = addNode(1, 1, 3);

        addEdge(node1, node2);
        DijkstraShortestPath dijkstra = new DijkstraShortestPath(edges);
        dijkstra.execute(null);
        ArrayList<Location> path = new ArrayList<>(dijkstra.getPath(null));
        assertEquals(Collections.EMPTY_LIST,path);
    }

    private Location addNode(int x, int y, int maxX) {
        Location location = new Location(3, maxX);
        location.setX(x);
        location.setY(y);
        nodes.add(location);
        return location;
    }

    private void addEdge(Location from, Location to) {
        Edge edge = new Edge(from, to, 1);
        edges.add(edge);
        edge = new Edge(to,from, 1);
        edges.add(edge);
    }
}