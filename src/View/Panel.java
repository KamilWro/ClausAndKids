package View;

import Main.Configuration;
import Model.Object.Claus;
import Model.Object.Package.Children;
import Model.Game;
import Model.Object.Package.ListPresents;
import Model.Picture;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * View the game board.
 * @author kamil
 */
public class Panel extends JPanel{
    private final ListPresents presents;
    private final Children children;
    private final Claus clous;
    private final Game game;

    private final Configuration conf;
    private final Picture background=new Picture();
    private final Picture farewellPhoto=new Picture();
        
    
    public Panel(Configuration conf, ListPresents presents, Children children, Claus clous, Game game){
        this.conf=conf;
        this.presents =presents;
        this.children =children;
        this.clous=clous;
        this.game=game;
        background.set("snow.png");
    } 
 

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        background.draw(g2d,0,0, getWidth(), getHeight());
        if(!game.isTheEnd())
            drawObjectsOnBoard(g2d);
        else 
            gameOver(g2d);
    }   
    
    private void drawObjectsOnBoard(Graphics2D g){
        clous.draw(g,getPlaceWidth(), getPlaceHeight());
        children.draw(g,getPlaceWidth(), getPlaceHeight());
        presents.draw(g,getPlaceWidth(), getPlaceHeight());
    }

    private void gameOver(Graphics2D g){
        if(game.isWin())
            farewellPhoto.set("winner.png");
        else
            farewellPhoto.set("looser.png");
        farewellPhoto.draw(g, getWidth() / 4 , getHeight() / 4, getWidth() / 2, getHeight() / 2);
    }

    private int getPlaceHeight(){
        int height = getHeight();
        return (height - (height % conf.row)) / conf.row;
    }

    private int getPlaceWidth(){
        int width=getWidth();
        return (width - (width % conf.column)) / conf.column;
    }
}
