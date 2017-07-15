package Model.Object.Impl;

import Model.Object.Child;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kamil on 04.07.17.
 */
public class ChildImpl extends Person implements Child {
    private boolean tired=true;
    private boolean finish;

    public ChildImpl(){}

    @Override
    public void updateBoard(){
        monitor.updateBoard(this);
    }

    @Override
    public void randomLocation(){
        monitor.randomLocation(this);
    }

    /**
     * Random selection state of child (sleeps, not sleeps):
     * Child's life cycle:
     * - He fall asleep for a period of time, when he tired.
     * - He is looking for Claus around himself. If he finds him, the game is over.
     * - He is looking for a gift. If he finds it, he goeas to that position and stays there.
     * - If Claus is around, child goes to him.
     * - Otherwise, He do random movement.
     */
    @Override
    public void run() {
        Timer timer = new Timer();
        Random generator = new Random();
        init();
        while(!finish){
            if (tired){
                setState("sleep.png");
                waiting(3,8);
                setState("notSleep.png");
                tired=false;
                timer.schedule(new Tiredness(), (generator.nextInt(5)+5)*1000);
            }
            if(monitor.catchClous(this)){
                break;
            }
            if(monitor.searchPresent(this)){
                finish=true;
                setState("happy.png");
                break;
            }
            if (monitor.searchClous(this)){
                waiting(1,2);
            }
            else {
                monitor.randomMove(this);
                waiting(1,3);
            }
        }
        timer.cancel();
    }

    private void init(){
        String name;
        Random generator = new Random();
        if (finish)
            name="happy.png";
        else if(generator.nextInt(2)==0){
            name="sleep.png";
            tired=true;
        }else{
            name="notSleep.png";
            tired=false;
        }
        setState(name);
    }

    private void setState(String name){
        img.set(name);
    }

    private void waiting(int from, int to){
        Random generator = new Random();
        try {
            Thread.sleep(generator.nextInt((to-from)*1000)+from*1000);
        } catch (InterruptedException e) {
            Logger.getLogger(ChildImpl.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    class Tiredness extends TimerTask {
        @Override
        public void run( ) {
            tired=true;
        }
    }
}