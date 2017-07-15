package Model.Object;

import Model.Board.Monitor;

/**
 * Created by kamil on 05.07.17.
 */
public interface LiveObject extends ObjectOnBoard {
    void randomLocation();
    void setBoard(Monitor monitor);
}
