package Model.Object.Impl;
import Model.Object.ObjectOnBoard;
import Model.Picture;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by kamil on 06.07.17.
 */
public abstract class ObjectOnBoardImpl implements ObjectOnBoard, Serializable {
    protected int x;
    protected int y;
    protected final Picture img=new Picture();

    protected ObjectOnBoardImpl(){}

    @Override
    public int getX(){
        return x;
    }

    @Override
    public int getY(){
        return y;
    }

    @Override
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g, int widthPlace, int heightPlace){
        img.draw(g,getX()*widthPlace,getY()*heightPlace,widthPlace,heightPlace);
    }

    @Override
    public boolean check(int x, int y) {
        return x == this.x && y == this.y;
    }

}
