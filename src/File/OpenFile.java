package File;



import Model.Game;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 * A class supports opening state of the game from the local disk.
 * Created by kamil on 27.06.17.
 */
public class OpenFile{
    private Game game;

    public OpenFile(){}

    public void open(){
        JFileChooser fc=new JFileChooser();
        if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            File file=fc.getSelectedFile();
            readObject(file);
        }
    }

    public Game getGame(){
        return game;
    }

    private void readObject(File file){
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file.getPath())))){
            game = (Game) in.readObject();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString(), "File not exist.", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}