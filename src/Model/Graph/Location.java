package Model.Graph;

import Model.Directions;

import java.io.Serializable;

/**
 * Created by kamil on 28.06.17.
 * Node in the graph.
 */
public class Location implements Serializable, Cloneable{
    private int x;
    private int y;
    private final int row; // Maximum X
    private final int column; // Maximum Y

    public Location(int row, int column){
        this.row=row;
        this.column=column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (x != location.x) return false;
        if (y != location.y) return false;
        if (row != location.row) return false;
        return column == location.column;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + row;
        result = 31 * result + column;
        return result;
    }

    public void move(Directions dir){
        x=updateOS(x+dir.getX(),column);
        y=updateOS(y+dir.getY(),row);
    }

    public void setX(int x1) {
        x=updateOS(x1,column);
    }

    public void setY(int y1) {
        y=updateOS(y1,row);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int updateOS(int point, int max){
        int newPoint = point%max;
        if( newPoint < 0)
            return max+newPoint;
        return newPoint;
    }

    @Override
    public Location clone() throws CloneNotSupportedException{
        return (Location) super.clone();
    }
}
