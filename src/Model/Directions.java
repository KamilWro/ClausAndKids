package Model;

/**
 * Created by kamil on 28.06.17.
 * Moving objects on the board.
 */
public enum Directions {
    North(0,-1),
    South(0,1),
    East(1,0),
    West(-1,0),
    NorthEast(1,-1),
    NorthWest(-1,-1),
    SouthEast(1,1),
    SouthWest(-1,1);

    final int x;
    final int y;

    Directions(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}
