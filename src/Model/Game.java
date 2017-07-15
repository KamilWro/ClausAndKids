package Model;

import Main.Configuration;
import Model.Board.Board;
import Model.Board.Monitor;
import Model.Board.MonitorBoard;
import Model.Object.Claus;
import Model.Object.Impl.ChildImpl;
import Model.Object.Impl.ClausImpl;
import Model.Object.Package.Children;
import Model.Object.Package.ListPresents;
import View.Window;
import java.io.Serializable;
import java.util.Timer;

import Exception.IncorrectGameState;

/**
 * Mechanics of the game
 * Created by kamil on 27.06.17.
 */
public class Game implements Serializable{

    private Configuration conf;
    transient private Window window;
    transient private Monitor monitor;
    transient private Board board;
    transient private Timer timer;
    transient private Watch watch;
    private Claus claus;
    private ListPresents presents;
    private Children children;

    private boolean theEnd;
    private boolean win;
    transient private boolean pause;
    private long endTime;
    private long remainingTime;

    public void init(Configuration conf) throws Exception {
        this.conf=conf;
        remainingTime =conf.time*conf.howChildren;
        createObject();
        createEnvironment();
        randomLocation();
        runGame(remainingTime);
    }

    private void createObject(){
        claus = new ClausImpl();
        children = new Children();
        presents = new ListPresents(conf.howChildren);
        for(int i=0; i<conf.howChildren; i++)
            children.addChild(new ChildImpl());
    }

    private void randomLocation(){
        claus.randomLocation();
        children.randomLocation();
    }

    public void createEnvironment() throws Exception{
        if (theEnd) 
            throw new IncorrectGameState("Game is over!");
        board = new Board(conf.row, conf.column, presents, children, claus, this);
        monitor = new MonitorBoard(board,conf, this);
        children.setBoard(monitor);
        claus.setBoard(monitor);
        window=new Window(conf,presents,children, claus, board, this);
    }     

    public void updateLocations(){
        children.updateBoard();
    }

    public void playAgain(){
        watch.setState("Loading...");
        stopGame();
        children.waitFor();
        resetGame();
        randomLocation();
        runGame(conf.time*conf.howChildren);
    }

    private void resetGame(){
        presents.reset();
        children.reset();
        board.reset();
        theEnd=false;
        win=false;
        pause =false;
        window.setVisibilitySave(true);
        watch.reset();
    }  

    private void stopGame(){
        children.stop();
        timer.cancel();
    }

    public void exitGame(){
        stopGame();
        if(window != null)
            window.dispose();
    }

    public void pauseGame(){
        if (!pause && !theEnd) {
            monitor.lockMonitor();
            pause = true;
            timer.cancel();
            watch.setState("Pause");
            remainingTime = endTime - System.currentTimeMillis();
        }
    }

    public void resumeGame(){
        if(pause && !theEnd) {
            pause = false;
            monitor.unlockMonitor();
            runGame(remainingTime);
            watch.reset();
        }
    }

    public void runGame(long time){
        startTimer();
        endTime = System.currentTimeMillis() + time;
        children.run();
    }

    public void setResult(boolean win){
        window.setVisibilitySave(false);
        theEnd=true;
        this.win=win;
        if(win)
            watch.setState("Game is over, YOU WIN");
        else
            watch.setState("Game is over, YOU LOST");
        monitor.unlockMonitor();
        stopGame();
    }

    public boolean isWin() {
        return win && theEnd;
    }

    public boolean isPause() {
        return pause;
    }

    public boolean isTheEnd() {
        return theEnd;
    }

    public long restTime(){
        return endTime-System.currentTimeMillis();
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    /**
     * Start tracking the state of the game.
     */
    private void startTimer(){
        timer = new Timer();
        watch = new Watch(this,window,presents,children);
        timer.schedule(watch,0,50);
    }
}
