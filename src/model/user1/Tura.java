package model.user1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Семен on 14.04.2014.
 */
public class Tura extends  AChess{
    public Tura(int x, int y) {
        super(x, y);
        setImages();
        setName("Tura");
    }
    public void setImages() {
        images = new Image[1];
        try {
            images[0] = ImageIO.read(new File("9.png").getAbsoluteFile());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Cann`t search that file");
        }

    }
}
