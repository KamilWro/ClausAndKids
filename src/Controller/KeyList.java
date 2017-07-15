package Controller;

import Model.*;
import Model.Board.Board;
import Model.Graph.Location;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Exception.IncorrectSynchronization;
import Model.Object.Claus;

/**
 * Class that supports Santa movements.
 * Created by kamil on 27.06.17.
 */
public class KeyList extends KeyAdapter{
    private final Board board;
    private final Claus claus;
    private final Game game;

    public KeyList(Claus claus, Board board, Game game){
        this.board = board;
        this.claus = claus;
        this.game = game;
    }
    
    @Override
    public void keyPressed (KeyEvent ev) {
        Location location= board.getClausLocation();
        switch (ev.getKeyCode()){
            case KeyEvent.VK_UP:
                location.move(Directions.North);
                break;
            case KeyEvent.VK_DOWN: 
                location.move(Directions.South);
                break;
            case KeyEvent.VK_RIGHT: 
                location.move(Directions.East);
                break;
            case KeyEvent.VK_LEFT:                     
                location.move(Directions.West);
                break;
            case KeyEvent.VK_SPACE:
                setPresent(location);
                break;
            case KeyEvent.VK_BACK_SPACE:
                deletePresent(location);
                break;
        }
        if ( !game.isPause() && isMove(ev) ){
            move(location);
        }
    }

    private boolean isMove(KeyEvent ev){
        boolean north = ev.getKeyCode() == KeyEvent.VK_UP;
        boolean south = ev.getKeyCode() == KeyEvent.VK_DOWN;
        boolean east = ev.getKeyCode() == KeyEvent.VK_RIGHT;
        boolean west = ev.getKeyCode() == KeyEvent.VK_LEFT;
        return (north || south || east || west);
    }

    private void setPresent(Location location){
        if(!board.isPresent(location) && board.availablePresents()>0) {
            try {
                board.setPresent(location);
            } catch (IncorrectSynchronization incorrectSynchronization) {
                incorrectSynchronization.printStackTrace();
            }
        }
    }

    private void deletePresent(Location location){
        if(board.isPresent(location)) {
            try {
                board.removePresent(location);
            } catch (IncorrectSynchronization incorrectSynchronization) {
                incorrectSynchronization.printStackTrace();
            }
        }
    }

    private void move(Location location){
        if( ! board.isLiveObject(location) ){
            try {
                board.move(claus,location);
            } catch (IncorrectSynchronization incorrectSynchronization) {
                incorrectSynchronization.printStackTrace();
            }
        }

    }
}
