package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 * The class represents the images in the game window.
 * Created by kamil on 27.06.17.
 */
public class Picture implements Serializable{
    transient private BufferedImage img = null;
    private String name;
    
    public void set(String name){
        this.name=name;
        update();
    }
    
    private BufferedImage get() {
        if (img==null)
            update();
        return img;
    }
    
    private void update(){
        try { 
            img = ImageIO.read(new File("./IMG/"+name)); 
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g,int X, int Y, int width, int height){
        if (img==null)
            update();
        g.drawImage(get(),X,Y,width,height, null);
    }
}
