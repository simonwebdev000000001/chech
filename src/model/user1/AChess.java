package model.user1;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Created by Семен on 14.04.2014.
 */
public class AChess {
    private boolean destroyed = false;
    public Image[] images;
    private int x;
    private int y;
    private String name;
    private boolean active = false;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
public boolean exisst(int x, int y){
    if(this.x == x && this.y==y){
        return true;
    }
    return false;
}
    public AChess existEl(int x, int y){
        if(this.x == x && this.y==y){
            return this;
        }
        return null;
    }
    public boolean isActive() {
        return active;
    }

    public void setCoord(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public AChess(int x, int y){
        this.x = x;
        this.y = y;
//        this.active = active;
    }
    public boolean isDestroyed() {
        return destroyed;
    }
    public void destroy() {
        destroyed = true;
    }
    public void draw(Graphics g) {

        if (!destroyed) {
            g.drawImage(images[0], getX(), getY(),
                    new ImageObserver() {
                        @Override
                        public boolean imageUpdate(Image img, int infoflags,
                                                   int x, int y, int width, int height) {
                            // TODO Auto-generated method stub
                            return false;
                        }
                    });
        }	else{
            x=1000;
            y=1000;
        }
    }
}
