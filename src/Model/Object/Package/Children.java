package Model.Object.Package;

import Model.Board.Monitor;
import Model.Object.Child;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Collection of children
 * Created by kamil on 27.06.17.
 */
public class Children implements Serializable{
    private final List<Child> children=new LinkedList<>();
    private int howHappyKinds;
    transient private Thread[] threads;

    public void addChild(Child child){
        children.add(child);
    }

    public void setBoard(Monitor monitor){
        for (Child child: children)
            child.setBoard(monitor);
    }

    public void draw(Graphics2D g,int widthPlace, int heightPlace){
        for (Child child1 : children)
            child1.draw(g, widthPlace, heightPlace);
    }

    public int length(){
        return children.size();
    }

    public void run(){
        Runnable[] runners = new Runnable[children.size()];
        threads = new Thread[children.size()];

        for(int i=0; i<children.size(); i++) {
            runners[i] = children.get(i);
            threads[i] = new Thread(runners[i]);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void stop(){
        for(Child child: children){
            child.setFinish(true);
        }
        for (Thread thread: threads){
            if (!thread.isInterrupted())
                thread.interrupt();
        }
    }

    public void waitFor(){
        for (Thread thread: threads){
            try {
                if(thread.isAlive())
                    thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void randomLocation(){
        for(Child child: children){
            child.randomLocation();
        }
    }

    public int getHappy() {
        return howHappyKinds;
    }
    
    public void upHappy(){
        this.howHappyKinds++;
    }
    
    public void reset(){
        howHappyKinds=0;
        for(Child child: children){
            child.setFinish(false);
        }
    }

    public void updateBoard(){
        for (Child child : children){
            child.updateBoard();
        }
    }
}
