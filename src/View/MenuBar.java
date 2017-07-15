package View;

import Controller.MenuBarListener;
import Model.Game;

import javax.swing.*;
/**
 * @author kamil
 */
public class MenuBar extends JMenuBar{
    private JMenu menuFile, menuHelp, menuOption;
    private JMenuItem mEnd,mNew, mIApk, mIAuthor, mSave, mAgain, mPause, mStart;

    public MenuBar(Game game) {
        create();
        add();
        setCommand();
        setAccelerator();
        addListener(game);
    }
    
    private void create(){
        menuFile = new JMenu("File");
        mNew= new JMenuItem("NewGame", 'N');
        mEnd = new JMenuItem("Exit", 'E');
        mSave=new JMenuItem("Save");
        mAgain=new JMenuItem("PlayAgain");

        menuOption=new JMenu("Program");
        mPause=new JMenuItem("Pause");
        mStart=new JMenuItem("Start");
        
        menuHelp = new JMenu("Help");
        mIApk = new JMenuItem("About Api");
        mIAuthor = new JMenuItem("About Author");
    }
    
    private void add() {

        add(menuFile);
        menuFile.add(mNew);
        menuFile.add(mAgain);
        menuFile.addSeparator();
        menuFile.add(mSave);
        menuFile.addSeparator();        
        menuFile.add(mEnd);

        add(menuOption);
        menuOption.add(mStart);
        menuOption.add(mPause);
        
        add(Box.createGlue());

        add(menuHelp);
        menuHelp.add(mIApk);
        menuHelp.add(mIAuthor);

    }   
    private void setCommand(){
        mEnd.setActionCommand("Exit");
        mNew.setActionCommand("NewGame");
        mIAuthor.setActionCommand("AboutAuthor");
        mIApk.setActionCommand("AboutApi");
        mAgain.setActionCommand("PlayAgain");
        mSave.setActionCommand("Save");
        mStart.setActionCommand("Start");
        mPause.setActionCommand("Pause");
    }    
    private void setAccelerator() {
        mPause.setAccelerator(KeyStroke.getKeyStroke("P"));
        mStart.setAccelerator(KeyStroke.getKeyStroke("S"));
        mSave.setAccelerator(KeyStroke.getKeyStroke("control S"));
        mAgain.setAccelerator(KeyStroke.getKeyStroke("control A"));
        
    }    
    private void addListener(Game game){
        mEnd.addActionListener(new MenuBarListener(game));
        mNew.addActionListener(new MenuBarListener(game));
        mIAuthor.addActionListener(new MenuBarListener(game));
        mIApk.addActionListener(new MenuBarListener(game));
        mIApk.addActionListener(new MenuBarListener(game));
        mSave.addActionListener(new MenuBarListener(game));
        mAgain.addActionListener(new MenuBarListener(game));
        mStart.addActionListener(new MenuBarListener(game));
        mPause.addActionListener(new MenuBarListener(game));
    }  
    
    /**
     * Sets the visibility of the button to save.
     */
    public void setVisibilitySave(boolean is){
        mSave.setEnabled(is);
    }
}