package Controller;

import File.OpenFile;
import Main.Configuration;
import Model.Game;
import View.CreatorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kamil on 27.06.17.
 * Class listening for the game creation window.
 */
public class CreatorListener implements ActionListener {
    private final CreatorView creator;
    private Game game;

    public CreatorListener(CreatorView creator) {
        this.creator = creator;
    }

    public CreatorListener(CreatorView creator, Game game){
        this.game = game;
        this.creator = creator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String source=e.getActionCommand();
        switch(source){
            case "Play":
                play();
                break;
            case "Open":
                open();
                break;
        }
    }
    private void play(){
        try{
            int howChild=Integer.parseInt(creator.getHowChild());
            int column=Integer.parseInt(creator.getColumn());
            int row=Integer.parseInt(creator.getRow());
            int area=Integer.parseInt(creator.getArea());
            reset();
            Configuration conf=new Configuration(howChild,column,row,area);
            game = new Game();
            game.init(conf);
            creator.dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "The game was not created.", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void open(){
        OpenFile op=new OpenFile();
        op.open();
        try {
            if(op.getGame()!=null){
                reset();
                game = op.getGame();
                game.createEnvironment();
                game.updateLocations();
                game.runGame(game.getRemainingTime());
                creator.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "The game was not created.", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void reset(){
        if(game != null) {
            game.exitGame();
            game = null;
        }
    }
}
