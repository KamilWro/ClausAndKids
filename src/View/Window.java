package View;

import Model.Board.Board;
import Controller.KeyList;
import Model.Game;
import Model.Object.Claus;
import Model.Object.Package.Children;
import Main.Configuration;
import Model.Object.Package.ListPresents;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Main window of the game.
 * Created by kamil on 27.06.17.
 */
public class Window extends JFrame{
    private Panel panel;
    private MenuBar menu;
    private JLabel label;
    
    public Window(Configuration conf, ListPresents presents, Children children, Claus claus, Board board, Game game) {
        super("Claus and Kinds");
        create(conf, claus, presents, children, game);
        add(board,claus, game);
        settings(conf.width, conf.height);
    }

    private void create(Configuration conf, Claus clous, ListPresents presents, Children children, Game game){
        panel=new Panel(conf, presents, children, clous, game);
        menu=new MenuBar(game);
        label = new JLabel("State of Game:");
    }

    private  void settings(int width, int height){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(80, 80);
        setSize(width,height);
        setResizable(true);
        setVisible(true);
    }

    private void add(Board board,Claus claus, Game game) {
        setLayout(new BorderLayout());
        setJMenuBar(menu);
        add(panel,BorderLayout.CENTER);
        add(label,BorderLayout.SOUTH);
        addKeyListener(new KeyList(claus,board,game));
    }

    public void update(String state){
        panel.repaint();
        label.setText(state);
    }

    public void setVisibilitySave(boolean isButton){
        menu.setVisibilitySave(isButton);
    }
}
