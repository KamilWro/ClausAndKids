package Model.Object;

import java.awt.*;

/**
 * Created by kamil on 27.06.17.
 */
public interface ObjectOnBoard {
    boolean check(int x, int y);
    void draw(Graphics2D g, int widthPlace, int heightPlace);
    int getX();
    int getY();
    void setLocation(int x, int y);
}
