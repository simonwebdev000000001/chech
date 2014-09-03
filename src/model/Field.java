package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * Created by Семен on 13.04.2014.
 */
public class Field {
    private Image iEagle;
    public Field(){
        try {
            iEagle = ImageIO.read(new File("b02300475a58.jpg").getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics g) {
        if (true) {
//			if (image != null) {
            g.drawImage(iEagle, 0, 0, 256, 256,
                   0, 0, 256, 256,
                    new ImageObserver() {
                        @Override
                        public boolean imageUpdate(Image img,
                                                   int infoflags, int x, int y, int width,
                                                   int height) {
                            // TODO Auto-generated method stub
                            return false;
                        }
                    });
        }
    }
}
