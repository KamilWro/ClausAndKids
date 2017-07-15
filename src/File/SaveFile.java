package File;

import Model.Game;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Class supports writing state of the game to the local disk.
 * Created by kamil on 27.06.17.
 */
public class SaveFile{
    
    public SaveFile(){
    }
    
    public void save(Game game){
        JFileChooser fc=new JFileChooser();
        if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
            File file=fc.getSelectedFile();
            writeObject(file,game);
        }
    }

    private void writeObject(File file, Game game){
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file.getPath())))){
            out.writeObject(game);
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString(), "File wasn't created. ", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }
}