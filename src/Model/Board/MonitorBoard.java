package Model.Board;

import Main.Configuration;
import Model.Object.Child;
import Model.Directions;
import Model.Game;
import Model.Graph.DijkstraShortestPath;
import Model.Graph.Distance;
import Model.Graph.Edge;
import Model.Graph.Location;
import Model.Object.Claus;
import Exception.IncorrectSynchronization;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by kamil on 04.07.17.
 */
public class MonitorBoard implements Monitor {
    private volatile boolean lock;
    private final Random generator= new Random();
    private final Board board;
    private final Configuration conf;
    private final Game game;

    public MonitorBoard(Board board, Configuration conf, Game game){
        this.board = board;
        this.conf = conf;
        this.game = game;
    }

    /**
     * Searching for gifts around the child.
     */
    public synchronized boolean searchPresent(Child child){
        waiting();
        for(Directions dir: Directions.values()){
            Location newLocation;
            try {
                newLocation = board.getChildLocation(child);
                newLocation.move(dir);
                if (board.isPresent(newLocation)){
                    catchPresent(newLocation, child);
                    return true;
                }
            } catch (IncorrectSynchronization e){
                e.printStackTrace();
            }
        }
        return false;
    }

    private void catchPresent(Location newLocation, Child child) throws IncorrectSynchronization {
        board.move(child, newLocation);
        board.openPresent(newLocation);
    }

    /**
     * Searching for Claus in the field of view of the child.
     */
    public synchronized boolean searchClous(Child child){
        waiting();
        Location childLocation = board.getChildLocation(child);
        Location clausLocation = board.getClausLocation();
        Distance distance = new Distance(conf, childLocation, conf.area);
        distance.setBorder();
        if (distance.isAround(clausLocation)) {
            DijkstraShortestPath dijkstra = new DijkstraShortestPath(getGraph(childLocation));
            dijkstra.execute(childLocation);
            LinkedList<Location> path = (LinkedList<Location>) dijkstra.getPath(clausLocation);
            if (path != null && path.size() >= 2 && !board.isLiveObject(path.get(1))) {
                try {
                    board.move(child,path.get(1));
                } catch (IncorrectSynchronization incorrectSynchronization) {
                    incorrectSynchronization.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    private List<Edge> getGraph(Location childLocation){
        LinkedList<Edge> edges = new LinkedList<>();
        for (int i = -conf.area; i <= conf.area; i++) {
            for (int j = -conf.area; j <= conf.area; j++) {
                Location location = getLocation(childLocation.getX() + i, childLocation.getY() + j);
                if ( !board.isChild(location) || (i == 0 && j == 0)) {
                    for (Directions dir : Directions.values()) {
                        Edge edge = getEdge(location,dir);
                        if (edge != null)
                            edges.add(edge);
                    }
                }
            }
        }
        return edges;
    }

    private Edge getEdge(Location location, Directions dir){
        Location neighbour;
        try {
            neighbour = location.clone();
            neighbour.move(dir);
            if (!board.isChild(neighbour))
                return new Edge(location, neighbour, 1);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized void randomMove(Child child){
        waiting();
        Location location;
        Directions[] dirs=Directions.values();
        do {
            location = board.getChildLocation(child);
            location.move(dirs[generator.nextInt(dirs.length)]);
        } while (board.isChild(location) || board.isClaus(location));

        try {
            board.move(child,location);
        } catch (IncorrectSynchronization incorrectSynchronization) {
            incorrectSynchronization.printStackTrace();
        }
    }


    /**
     * Searching for clous around the child.
     */
    public synchronized boolean catchClous(Child child){
        waiting();
        Location childLocation = board.getChildLocation(child);
        Location clousLocation = board.getClausLocation();
        Distance distance = new Distance(conf,childLocation,conf.trap);
        distance.setBorder();
        if( distance.isAround(clousLocation) ){
            game.setResult(false);
            return true;
        }
        return false;
    }

    /**
     * Random selection position of the object on the board. Child must be distant.
     */
    public synchronized void randomLocation(Child child){
        waiting();
        Location location=new Location(conf.row,conf.column);
        Location clousLocation = board.getClausLocation();
        Distance distance = new Distance(conf,clousLocation,conf.distance);
        distance.setBorder();
        do{
            location.setX(generator.nextInt(conf.column));
            location.setY(generator.nextInt(conf.row));
        }
        while (board.isChild(location) || distance.isAround(location) );
        try {
            board.setLocation(child,location);
        } catch (IncorrectSynchronization incorrectSynchronization) {
            incorrectSynchronization.printStackTrace();
        }
    }

    public synchronized void randomLocation(Claus claus){
        Location location=new Location(conf.row,conf.column);
        do{
            location.setX(generator.nextInt(conf.column));
            location.setY(generator.nextInt(conf.row));
        }
        while (board.isChild(location));
        try {
            board.setLocation(claus,location);
        } catch (IncorrectSynchronization incorrectSynchronization) {
            incorrectSynchronization.printStackTrace();
        }
    }

    private Location getLocation(int x, int y){
        Location location = new Location(conf.row,conf.column);
        location.setX(x);
        location.setY(y);
        return location;
    }

    public synchronized void updateBoard(Child child){
        try {
            Location location = board.getChildLocation(child);
            board.setLocation(child,location);
        } catch (IncorrectSynchronization incorrectSynchronization) {
            incorrectSynchronization.printStackTrace();
        }
    }

    private void waiting(){
        if (lock)
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public synchronized void lockMonitor(){
        lock=true;
    }

    public synchronized void unlockMonitor(){
        lock=false;
        notifyAll();
    }
}
