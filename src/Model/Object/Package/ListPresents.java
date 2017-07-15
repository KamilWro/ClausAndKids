package Model.Object.Package;

import Model.Object.Impl.Present;
import Model.Object.ObjectOnBoard;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import Exception.IncorrectSynchronization;

/**
 * Collection of gifts
 * Created by kamil on 27.06.17.
 */
public class ListPresents implements Serializable{
    private int maxPresent;
    private List<ObjectOnBoard> presents = new LinkedList();;
    private final int howChildren;

    public ListPresents(int howChildren){
        this.howChildren=howChildren;
        maxPresent=howChildren;
    }
    
    public void downMaxPresent() {
        maxPresent--;
    }
    
    public boolean isExist(Present present){
        return presents.indexOf(present) != -1;
    }
    
    public void removePresent(Present present) throws IncorrectSynchronization {
        int i= presents.indexOf(present);
        if(i != -1) 
            presents.remove(i);
        else
            throw new IncorrectSynchronization("There is no present here!");
    }

    public void draw(Graphics2D g,int widthPlace, int heightPlace){
        for (ObjectOnBoard present : presents)
            present.draw(g, widthPlace, heightPlace);
    } 

    public void setPresents(Present present) throws IncorrectSynchronization {
        if (availablePresents()<=0)
            throw new IncorrectSynchronization("There are no more presents");
        if ( isExist(present) )
            throw new IncorrectSynchronization("There is a gift here already.");
        presents.add(present);
    }
    
    public void reset(){
        presents =new LinkedList<>();
        maxPresent=howChildren;
    }

    public int availablePresents(){
        return maxPresent-presents.size();
    }
}
