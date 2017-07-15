
package Main;

import java.io.Serializable;

/**
 * Configuration of game.
 * Created by kamil on 27.06.17.
 */
public class Configuration implements Serializable{
    public final int howChildren;
    public final int width;
    public final int height;
    public final int row;
    public final int column;
    public final int area;
    public final int trap=1; // trap is area of child, where child can catch claus
    public final int distance=2; // distance measured in fields
    public final long time=6*1000; // time for one kid measured in seconds
    
    public Configuration(int howChildren, int column, int row, int area) throws Exception {
        if (howChildren<0 || howChildren>=row*column || row<=0 || column<=0 || area<=0)
            throw new Exception();
        this.howChildren =howChildren;
        width=800;
        height=500;
        this.row =row;
        this.column =column;
        this.area =area;
    }
}
