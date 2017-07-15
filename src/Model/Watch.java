package Model;

import Model.Object.Package.Children;
import Model.Object.Package.ListPresents;
import View.Window;

import java.util.TimerTask;

/**
 * Created by kamil on 09.07.17.
 * The timer class refreshes the game state and checks the game time.
 */
class Watch extends TimerTask {
    private final Window window;
    private final ListPresents presents;
    private final Children children;
    private final Game game;
    private boolean flagState;
    private String state;

    public Watch(Game game, Window window, ListPresents presents, Children children){
        this.game = game;
        this.window = window;
        this.presents = presents;
        this.children = children;
    }

    @Override
    public void run( ) {
        String state = getState();
        window.update(state);
        if(game.restTime() < 0 && !game.isTheEnd())
            game.setResult(false);
    }

    public void setState(String state){
        flagState = true;
        this.state = state;
        window.update(state);
    }

    private String getState(){
        if (flagState)
            return state;
        return  "    Available presents " + (presents.availablePresents()) +
                "    Happy children: " + children.getHappy() +
                "    Remaining children: " + (children.length() - children.getHappy()) +
                "    Remaining time: " + game.restTime() / 60;
    }

    public void reset(){
        flagState = false;
    }
}
