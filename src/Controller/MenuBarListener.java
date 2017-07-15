package Controller;

import Model.Game;
import File.SaveFile;
import View.CreatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.Scanner;

/**
 * Class that handles events from the main window's ManuBar.
 * Created by kamil on 27.06.17.
 */
public class MenuBarListener implements ActionListener {   
        
    private final Game game;

    public MenuBarListener(Game game){
        this.game=game;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        String source=event.getActionCommand();
        switch(source){
            case "Exit":
                game.exitGame();
                break;
            case "NewGame":
                CreatorView creatorView = new CreatorView(game);
                creatorView.closeOperation(false);
                break;
            case "PlayAgain":
                game.playAgain();
                break;
            case "AboutApi":
                String app="";
                try (Scanner odczyt = new Scanner(new File("Application.txt"))){
                    while(odczyt.hasNextLine()){
                        app=app+odczyt.nextLine()+'\n';
                    }
                } catch (FileNotFoundException e) {
                    app=e.getMessage();
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, app, "About Application", JOptionPane.INFORMATION_MESSAGE);
                break;                                  
            case "AboutAuthor":
                String author = String.format("%-15s %-15s","Author:","Kamil Breczko");
                String v = String.format("%-15s %-15s","Version:","2.0");
                String date = String.format("%-15s %-15s","Release date:","01 07 2017");
                JOptionPane.showMessageDialog(
                        null,
                        author+"\n"+v+"\n"+date,
                        "About Author", JOptionPane.INFORMATION_MESSAGE
                );
                break;
            case "Save":
                SaveFile saveFile = new SaveFile();
                saveFile.save(game);
                break;
            case "Start":
                game.resumeGame();
                break;
            case "Pause":
                game.pauseGame();
                break;
            default:
                break;
        }
    }
}
