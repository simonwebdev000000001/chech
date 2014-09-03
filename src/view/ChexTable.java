package view;

import controller.MakeMouse;
import model.Field;
import model.user1.*;
import model.user2.*;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Семен on 13.04.2014.
 */
public class ChexTable extends JPanel{
    private Field f;
    public int curX=-32, curY=-32;
    public int xPoint, yPoint;
   private AChess tura1,tura2,horse1,horse2,slon1,slon2,quen,king,pawn;
    private LinkedList<AChess>bl, wh;
    private  MakeMouse mouse;
    public int count=0;

    public LinkedList<AChess> getBl() {
        return bl;
    }

    public LinkedList<AChess> getWh() {
        return wh;
    }

    public void setWh(LinkedList<AChess> wh) {
        this.wh = wh;
    }

    public ChexTable(){
        f = new Field();
        wh= new LinkedList();
        bl= new LinkedList();
         mouse=new MakeMouse(this);
        JFrame frame = new JFrame("Chex");
        frame.setLocation(10, 10);

        JMenuBar menuBar= new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem m = new JMenuItem("Save as");
        JMenuItem m1 = new JMenuItem("Open");
        menu.add(m);
        menu.add(m1);
        menuBar.add(menu);
//        frame.setJMenuBar(menuBar);
        frame.setMinimumSize(new Dimension(270,
               260+ 32));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createTableW();
        createTableB();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }
    public void createTableW(){
         tura1= new Tura(0,224);
        wh.add(tura1);
         tura2= new Tura(224,224);
        wh.add(tura2);
         horse1= new Horse(32,224);
        wh.add(horse1);
         horse2= new Horse(192,224);
        wh.add(horse2);
        slon1 = new Elepant(160,224);
        wh.add(slon1);
        slon2 = new Elepant(64,224);
        wh.add(slon2);
        quen = new Queen(96,224);
        wh.add(quen);
        king = new King(128,224);
        wh.add(king);
        for(int i=0;i<8;i++){
            pawn =new Pawn(i*32,192);
            wh.add(pawn);
        }
    }
    public void createTableB(){
        tura1= new BTura(0,0);
        bl.add(tura1);
        tura2= new BTura(224,0);
        bl.add(tura2);
        horse1= new BHorse(32,0);
        bl.add(horse1);
        horse2= new BHorse(192,0);
        bl.add(horse2);
        slon1 = new BElepant(160,0);
        bl.add(slon1);
        slon2 = new BElepant(64,0);
        bl.add(slon2);
        quen = new BQueen(96,0);
        bl.add(quen);
        king = new BKing(128,0);
        bl.add(king);
        for(int i=0;i<8;i++){
            pawn =new BPawn(i*32,32);
            bl.add(pawn);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        f.draw(g);
        try {
            paintFigures(g);
        }catch (Exception e){
            System.out.print(1);
        }
        g.setColor(new Color(250, 250, 170));
        g.drawRect(xPoint,yPoint,32,32);
        if(mouse.getShine().equals("nothing")){
            mouse.checkQuadrant(g);
        }else{

        }


       }
    public boolean checkWh(int x, int y){
        for(Iterator<AChess> iter = wh.iterator(); iter.hasNext();){
         if(iter.next().exisst(x,y)){
             return true;
         }
        }
        return false;
    }
    public boolean checkBl(int x, int y){
        for(Iterator<AChess> iter = bl.iterator(); iter.hasNext();){
            if(iter.next().exisst(x,y)){
                return true;
            }
        }
        return false;
    }
    public void paintFigures(Graphics g) {
        for(Iterator<AChess> iter = wh.iterator(); iter.hasNext();) iter.next().draw(g);
        for(Iterator<AChess> iter = bl.iterator(); iter.hasNext();) iter.next().draw(g);

    }
}
