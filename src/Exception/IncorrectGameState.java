package Exception;

/**
 * Created by kamil on 06.07.17.
 */
public class IncorrectGameState extends Exception {
    public IncorrectGameState() {
    }

    public IncorrectGameState(String message) {
        super(message);
    }
}
