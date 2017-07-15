package Model.Graph;

import Main.Configuration;

/**
 * Created by kamil on 29.06.17.
 * Verify the node's extent on the board.
 */
public class Distance {
    private final int row;
    private final int column;
    private final int area;
    private final Location source;

    // Border of visibility.
    private int borderN;
    private int borderS;
    private int borderE;
    private int borderW;

    // Does it cross borders?
    private boolean exceedN;
    private boolean exceedS;
    private boolean exceedE;
    private boolean exceedW;



    public Distance(Configuration conf, Location source,int area) {
        this.area = area;
        this.row = conf.row;
        this.column = conf.column;
        this.source=source;
    }

    public void setBorder(){
        bordersOfArea();
        outsideBoard();
        if (exceedN)
            borderN=row+borderN;
        if (exceedW)
            borderW=column+borderW;
        if (exceedE)
            borderE-=column;
        if (exceedS)
            borderS-=row;
    }

    public boolean isAround(Location location){
        boolean sounth= isInTheSounth(location.getY());
        boolean east= isInTheEast(location.getX());
        boolean north= isInTheNorth(location.getY());
        boolean west= isInTheWest(location.getX());
        return (sounth && east) || (sounth && west) || (north && west) || (north && east);
    }

    private void bordersOfArea(){
        borderN = source.getY()-area;
        borderS = source.getY()+area;
        borderW = source.getX()-area;
        borderE = source.getX()+area;
    }

    private void outsideBoard(){
        exceedN =(borderN<0);
        exceedS =(borderS>=row);
        exceedW =(borderW<0);
        exceedE =(borderE>=column);
    }

    private boolean isInTheNorth(int y){
        if (exceedN && (borderN<=y || y<=source.getY()))
            return true;
        else if(!exceedN && borderN<=y && source.getY()>=y)
            return true;
        return false;
    }

    private boolean isInTheSounth(int y){
        if(exceedS && (borderS>=y || y>=source.getY()))
            return true;
        else if(!exceedS && borderS>=y && source.getY()<=y)
            return true;
        return false;
    }

    private boolean isInTheEast(int x){
        if(exceedE && (borderE>=x || x>=source.getX()))
            return true;
        else if(!exceedE && borderE>=x && source.getX()<=x)
            return true;
        return false;
    }

    private boolean isInTheWest(int x){
        if(exceedW && (borderW <= x || x <= source.getX()))
            return true;
        else if(!exceedW && borderW <= x && source.getX() >= x)
            return true;
        return false;
    }
}
