package View;

import Controller.CreatorListener;
import Model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Wizard game.
 * Created by kamil on 27.06.17.
 */
public class CreatorView extends JFrame {
    private JTextField tRow;
    private JTextField tColumn;
    private JTextField tArea;
    private JTextField tHowChild;
    private Container container;
    private JButton mSubmit;
    private JButton mOpen;

    public CreatorView() {
        super("Wizard game");
        create();
        add();
        setCommand();
        addListener();
        pack();
        setVisible(true);
    }

    public CreatorView(Game game){
        super("Wizard game");
        create();
        add();
        setCommand();
        addListener(game);
        pack();
        setVisible(true);

    }

    private void create( ){
        tColumn = new JTextField("10", 20);
        tHowChild = new JTextField("12", 20);
        tArea = new JTextField("5", 20);
        tRow = new JTextField("10",20);
        mSubmit = new JButton("Play");
        mOpen = new JButton("Open");
        container = getContentPane();
        GridLayout layout = new GridLayout(5,2);
        container.setLayout(layout);
    }

    private void add(){
        addToContainer(new JLabel("Row:"),tRow);
        addToContainer(new JLabel("Column:"),tColumn);
        addToContainer(new JLabel("Children:"),tHowChild);
        addToContainer(new JLabel("Field of view of the child:"),tArea);
        addToContainer(mSubmit,mOpen);
    }

    private void setCommand(){
        mOpen.setActionCommand("Open");
        mSubmit.setActionCommand("Play");
    }

    private void addListener(){
        mSubmit.addActionListener(new CreatorListener(this));
        mOpen.addActionListener(new CreatorListener(this));
    }

    private void addListener(Game game){
        mSubmit.addActionListener(new CreatorListener(this,game));
        mOpen.addActionListener(new CreatorListener(this,game));
    }

    public void closeOperation(boolean isExit){
        if(isExit)
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        else
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addToContainer(Component obj1, Component obj2){
        container.add(obj1);
        container.add(obj2);
    }

    public String getRow() {
        return tRow.getText();
    }

    public String getColumn() {
        return tColumn.getText();
    }

    public String getArea() {
        return tArea.getText();
    }

    public String getHowChild() {
        return tHowChild.getText();
    }

}
