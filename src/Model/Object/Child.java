package Model.Object;

/**
 * Created by kamil on 29.06.17.
 */
public interface Child extends Runnable, LiveObject {
    void setFinish(boolean finish);
    void updateBoard();
}
