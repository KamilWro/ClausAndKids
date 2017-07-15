package Model.Board;

import Model.Object.Child;
import Model.Object.Claus;


/**
 * Created by kamil on 09.07.17.
 */
public interface Monitor {
    boolean searchPresent(Child child);
    boolean searchClous(Child child);
    void randomMove(Child child);
    boolean catchClous(Child child);
    void randomLocation(Child child);
    void randomLocation(Claus claus);
    void updateBoard(Child child);
    void lockMonitor();
    void unlockMonitor();
}
