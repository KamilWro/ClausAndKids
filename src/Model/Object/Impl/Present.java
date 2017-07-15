package Model.Object.Impl;

import java.awt.Graphics2D;


/**
 * Created by kamil on 27.06.17.
 */
public class Present extends ObjectOnBoardImpl {
    public Present(){
        String name = "present.png";
        img.set(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Present that = (Present) o;

        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public void draw(Graphics2D g, int widthPlace, int heightPlace){
        img.draw(g,getX()*widthPlace,getY()*heightPlace,widthPlace/2,heightPlace/2); 
    }

}
