package Model.Board;

import Model.Game;
import Model.Graph.Location;
import Model.Object.Child;
import Model.Object.Claus;
import Model.Object.Package.Children;
import Model.Object.Package.ListPresents;
import Model.Object.Impl.Present;
import Exception.IncorrectSynchronization;

/**
 * Created by kamil on 04.07.17.
 */
public class Board {
    private boolean[][] childrenArray;
    private final ListPresents presents;
    private final Children children;
    private final Claus claus;
    private final int row;
    private final int column;
    private final Game game;

    public Board(int row, int column, ListPresents listPresent, Children children, Claus clous, Game game) {
        this.row=row;
        this.column=column;
        childrenArray = new boolean[row][column];
        this.children = children;
        presents = listPresent;
        this.claus = clous;
        this.game = game;
    }

    public void move(Child child, Location location) throws IncorrectSynchronization {
        if (isLiveObject(location))
            throw new IncorrectSynchronization("This field is occupied");

        int x = location.getX();
        int y = location.getY();

        childrenArray[x][y] = childrenArray[child.getX()][child.getY()];
        childrenArray[child.getX()][child.getY()] = false;

        child.setLocation(location.getX(),location.getY());
    }

    public void move(Claus claus, Location location) throws IncorrectSynchronization {
        if (isLiveObject(location))
            throw new IncorrectSynchronization("This field is occupied");
        claus.setLocation(location.getX(),location.getY());
    }

    public void setLocation(Child child, Location location) throws IncorrectSynchronization {
        if (isLiveObject(location))
            throw new IncorrectSynchronization("This field is occupied");

        childrenArray[location.getX()][location.getY()] = true;
        child.setLocation(location.getX(),location.getY());
    }

    public void setLocation(Claus claus, Location location) throws IncorrectSynchronization {
        if (isLiveObject(location))
            throw new IncorrectSynchronization("This field is occupied");

        claus.setLocation(location.getX(), location.getY());
    }

    public boolean isLiveObject(Location location){
        return isChild(location) || isClaus(location);
    }

    public boolean isChild(Location location){
        return childrenArray[location.getX()][location.getY()];
    }

    public boolean isClaus(Location location){
        return claus.check(location.getX(),location.getY());
    }

    public void openPresent(Location location) throws IncorrectSynchronization {
        removePresent(location);
        presents.downMaxPresent();
        children.upHappy();
        if(children.getHappy()==children.length()){
            game.setResult(true);
        }
    }

    private Present getPresent(Location location){
        Present present=new Present();
        present.setLocation(location.getX(),location.getY());
        return present;
    }

    public void setPresent(Location location) throws IncorrectSynchronization {
        Present present = getPresent(location);
        presents.setPresents(present);
    }
    
    public void removePresent(Location location) throws IncorrectSynchronization {
        Present present = getPresent(location);
        presents.removePresent(present);
    }

    public boolean isPresent(Location location){
        Present present = getPresent(location);
        return presents.isExist(present);
    }

    public Location getClausLocation(){
        Location location = new Location(row,column);
        location.setX(claus.getX());
        location.setY(claus.getY());
        return location;
    }

    public Location getChildLocation(Child child){
        Location location = new Location(row,column);
        location.setX(child.getX());
        location.setY(child.getY());
        return location;
    }
    public int availablePresents(){
        return presents.availablePresents();
    }

    public void reset(){
        childrenArray = new boolean[row][column];
    }
}
