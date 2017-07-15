package Model.Object.Impl;

import Model.Board.Monitor;
import Model.Object.LiveObject;

/**
 * Character in the board.
 * Created by kamil on 27.06.17.
 */
public abstract class Person extends ObjectOnBoardImpl implements LiveObject {
    transient protected Monitor monitor;

    protected Person() {}

    @Override
    public void setBoard(Monitor monitor){
        if (this.monitor==null)
            this.monitor=monitor;
    }
}
