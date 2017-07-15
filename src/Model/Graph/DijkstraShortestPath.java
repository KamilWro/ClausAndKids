package Model.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Implementation the Dijkstra's algorithm. Find the shortest path.
 */
public class DijkstraShortestPath {
    private final List<Edge> edges;
    private Set<Location> settledNodes;
    private Set<Location> unSettledNodes;
    private Map<Location, Location> predecessors;
    private Map<Location, Integer> distance;


    public DijkstraShortestPath(List <Edge> edges) {
        this.edges = new ArrayList<>(edges);
    }

    /**
     * Find the shortest paths.
     * @param source Source vertex
     */
    public void execute(Location source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Location node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Location node) {
        for (Location target : getNeighbors(node)) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Location node, Location target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private Iterable<Location> getNeighbors(Location node) {
        List<Location> neighbors = new LinkedList<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Location getMinimum(Set<Location> nodes) {
        Location minimum = null;
        for (Location location : nodes) {
            if (minimum == null) {
                minimum = location;
            } else {
                if (getShortestDistance(location) < getShortestDistance(minimum)) {
                    minimum = location;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Location location) {
        return settledNodes.contains(location);
    }

    private int getShortestDistance(Location destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    public List<Location> getPath(Location target) {
        LinkedList<Location> path = new LinkedList<>();
        Location step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return Collections.EMPTY_LIST;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
}