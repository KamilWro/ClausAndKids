package Model.Graph;

/**
 * Created by kamil on 05.07.17.
 * Edge in the graph.
 */
public class Edge {
    private final Location source;
    private final Location destination;
    private final int weight;

    public Edge(Location source, Location destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Location getDestination() {
        return destination;
    }

    public Location getSource() {
        return source;
    }

    public int getWeight() {
        return weight;
    }
}
