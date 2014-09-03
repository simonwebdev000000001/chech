package controller;

import model.user1.AChess;
import view.ChexTable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

/**
 * Created by Семен on 14.04.2014.
 */
public class MakeMouse implements  MouseListener,MouseMotionListener {
   private ChexTable c;
    private String whoMoves,pawn="",shine="";
    int x, y,f=1;
    private int curX, curY;

    public String getShine() {
        return shine;
    }

    public String getWhoMoves() {
        return whoMoves;
    }

    public String getPawn() {
        return pawn;
    }

    public MakeMouse(ChexTable c){
        whoMoves= "White";
        this.c =c;

    }
    public boolean elementOfWh(int x, int y,int x1, int y1){
        for(int i=0;i< c.getWh().size();i++){
            if(c.getWh().get(i).exisst(x1,y1)){
                c.getWh().get(i).setCoord(x*32,y*32);
                for(Iterator<AChess> iter =  c.getBl().iterator(); iter.hasNext();){
                    if(iter.next().exisst(x*32,y*32)) {
                        iter.remove();
                    }
                }
                return  true;
            }

        }
        return false;
    }
    public boolean elementOfBl(int x, int y,int x1, int y1){
        for(int i=0;i< c.getBl().size();i++){
            if(c.getBl().get(i).exisst(x1,y1)){
                c.getBl().get(i).setCoord(x * 32, y * 32);
                for(Iterator<AChess> iter =  c.getWh().iterator(); iter.hasNext();){
                    if(iter.next().exisst(x*32,y*32)) {
                        iter.remove();
                    }
                }
                return  true;
            }

        }
        return false;
    }
    public boolean checkElementW(int x, int y){
        for(Iterator<AChess> iter = c.getWh().iterator(); iter.hasNext();) {
            AChess cu = iter.next();
            if(x==cu.getX()/32 && y ==cu.getY()/32){
                if(cu.getName().equals("Pawn")){
                        pawn = "pan";
                    return true;
                }else if(cu.getName().equals("Horse")){
                    pawn = "horse";
                    return true;
                }else if(cu.getName().equals("Elepant")){
                    pawn = "ele";
                    return true;
                }else if(cu.getName().equals("King")){
                    pawn = "king";
                    return true;
                }else if(cu.getName().equals("Tura")){
                    pawn = "tur";
                    return true;
                }else if(cu.getName().equals("Queen")){
                    pawn = "queen";
                    return true;
                }

            }
        }
        pawn = "";
        return false;
    }
    public boolean checkElementB(int x, int y){
        for(Iterator<AChess> iter = c.getBl().iterator(); iter.hasNext();) {
            AChess cu = iter.next();
            if(x==cu.getX()/32 && y ==cu.getY()/32){
                if(cu.getName().equals("Pawn")){
                    pawn = "pawnb";
                    return true;
                }else if(cu.getName().equals("Horse")){
                    pawn = "horse";
                    return true;
                }else if(cu.getName().equals("Elepant")){
                    pawn = "ele";
                    return true;
                }else if(cu.getName().equals("King")){
                    pawn = "king";
                    return true;
                }else if(cu.getName().equals("Tura")){
                    pawn = "tur";
                    return true;
                }else if(cu.getName().equals("Queen")){
                    pawn = "queen";
                    return true;
                }
            }
        }
        pawn = "";
        return false;
    }
    public void checkQuadrant(Graphics g){
            if (getPawn().equals("pan")) {
                int count1 = 1;
                while (count1 < 3 && !c.checkBl((curX )* 32,(curY - count1) * 32)&& !c.checkWh((curX )* 32,(curY - count1) * 32)) {
                    g.fillOval(curX * 32, (curY - (count1)) * 32, 32, 32);
                    count1++;
                }
            }
               else if(getPawn().equals("pawnb") ){
                    int  count1=1;
                    while(count1<3 && !c.checkWh((curX )* 32,(curY + count1) * 32)&& !c.checkBl((curX )* 32,(curY + count1) * 32)) {
                        g.fillOval(curX*32, (curY+(count1))*32, 32, 32);
                        count1++;
                    }
                } else if(getPawn().equals("tur") ){
                repaintCheckForTur(g);
                }
        else if(getPawn().equals("horse")){
                if(curX - 2>=0 && curY - 1>=0&&!c.checkWh((curX - 2)* 32,(curY - 1) * 32)&&!c.checkBl((curX - 2)* 32,(curY - 1) * 32)){
                    g.fillOval((curX - 2) * 32, (curY - 1) * 32, 32, 32);
                }
                if(curX - 2>=0 && curY + 1<8&&!c.checkWh((curX - 2)* 32,(curY + 1) * 32)&&!c.checkBl((curX - 2)* 32,(curY + 1) * 32)) {
                    g.fillOval((curX - 2) * 32, (curY + 1) * 32, 32, 32);
                }
                if(curX + 2<8 && curY + 1<8&&!c.checkWh((curX + 2)* 32,(curY + 1) * 32)&&!c.checkBl((curX + 2)* 32,(curY + 1) * 32)) {
                    g.fillOval((curX + 2) * 32, (curY + 1) * 32, 32, 32);
                }
                if(curX + 2<8 && curY - 1>=0 && !c.checkWh((curX + 2)* 32,(curY - 1) * 32)&&!c.checkBl((curX + 2)* 32,(curY - 1) * 32)) {
                    g.fillOval((curX + 2) * 32, (curY - 1) * 32, 32, 32);
                }
                 if(curX - 1>=0 && curY - 1>=0&&!c.checkWh((curX - 1)* 32,(curY - 2) * 32)&&!c.checkBl((curX - 1)* 32,(curY - 2) * 32)) {
                      g.fillOval((curX - 1) * 32, (curY - 2) * 32, 32, 32);
                 }
            if(curX - 1>=0 && curY +2<8 &&!c.checkWh((curX - 1)* 32,(curY + 2) * 32)&&!c.checkBl((curX - 1)* 32,(curY + 2) * 32)) {
                g.fillOval((curX - 1) * 32, (curY + 2) * 32, 32, 32);
            }
            if(curX + 1<8 && curY +2<8 && !c.checkWh((curX +1)* 32,(curY +2) * 32)&&!c.checkBl((curX +1)* 32,(curY +2) * 32)) {
                g.fillOval((curX + 1) * 32, (curY + 2) * 32, 32, 32);
            }
            if(curX + 1<8 && curY -2>=0 && !c.checkWh((curX +1)* 32,(curY - 2) * 32)&&!c.checkBl((curX +1)* 32,(curY - 2) * 32)) {
                g.fillOval((curX + 1) * 32, (curY - 2) * 32, 32, 32);
            }
        }else if(getPawn().equals("ele")){
                repaintCheckForEle(g);
        }
        else if(getPawn().equals("king")){
                if(!c.checkWh((curX-1) * 32, (curY + 1)*32)&&!c.checkBl((curX-1) * 32, (curY + (1)) * 32)){
                    g.fillOval((curX-1) * 32, (curY + (1)) * 32, 32, 32);
                }
            if(!c.checkWh((curX-1) * 32, (curY )*32)&&!c.checkBl((curX-1) * 32, (curY ) * 32)){
                g.fillOval((curX-1) * 32, (curY ) * 32, 32, 32);
            }if(!c.checkWh((curX-1) * 32, (curY - 1)*32)&&!c.checkBl((curX-1) * 32, (curY - (1)) * 32)){
                g.fillOval((curX-1) * 32, (curY - (1)) * 32, 32, 32);
            }if(!c.checkWh((curX) * 32, (curY + 1)*32)&&!c.checkBl((curX) * 32, (curY + (1)) * 32)){
                g.fillOval((curX) * 32, (curY + (1)) * 32, 32, 32);
            }if(!c.checkWh((curX) * 32, (curY - 1)*32)&&!c.checkBl((curX) * 32, (curY - (1)) * 32)){
                g.fillOval((curX) * 32, (curY - (1)) * 32, 32, 32);
            }if(!c.checkWh((curX+1) * 32, (curY + 1)*32)&&!c.checkBl((curX+1) * 32, (curY + (1)) * 32)){
                g.fillOval((curX+1) * 32, (curY + (1)) * 32, 32, 32);
            }if(!c.checkWh((curX+1) * 32, (curY )*32)&&!c.checkBl((curX+1) * 32, (curY ) * 32)){
                g.fillOval((curX+1) * 32, (curY ) * 32, 32, 32);
            }if(!c.checkWh((curX+1) * 32, (curY - 1)*32)&&!c.checkBl((curX-1) * 32, (curY - (1)) * 32)){
                g.fillOval((curX+1) * 32, (curY - (1)) * 32, 32, 32);
            }
        } else if(getPawn().equals("queen")){
                repaintCheckForEle(g);
                repaintCheckForTur(g);
            }
    }
    public boolean repaintWh(int x, int y){
        if(pawn =="horse"){
          return  repaintHorse(x,y);
        }
        else if(pawn =="pan"){
            return  repaintPawn(x,y);
        }
        else if(pawn == "pawnb"){
            return  repaintPawnBl(x,y);
        }
        else if(pawn == "tur"){
            return  repaintTour(x,y);
        }
        else if(pawn == "ele"){
            return  repaintElephant(x,y);
        }
        else if(pawn == "queen"){
            return  repaintQueen(x,y);
        }
        else if(pawn == "king"){
            return  repaintQueen(x,y);
        }
        return false;
    }
    public boolean repaintQueen(int x, int y){
        if(repaintTour(x,y)){
            return true;
        }else if(repaintElephant(x,y)){
            return true;
        }
        return false;
    }
    public boolean repaintElephant(int x,int y){
        int i=1;
        while(i<8){
            if(x==curX+i && y==curY+i){
                if(checkJumpEle("rightUp", x, y)) {
                    if (whoMoves == "White"&& !c.checkWh(x*32,y*32)) {
                        return elementOfWh(x, y, curX * 32, curY * 32);
                    } else if(whoMoves == "Black" && !c.checkBl(x*32,y*32)){
                        return elementOfBl(x, y, (curX) * 32, (curY) * 32);
                    }
                }
            } else if(x==curX-i && y==curY-i){
                if(checkJumpEle("leftDwn", x, y)) {
                    if (whoMoves == "White"&& !c.checkWh(x*32,y*32)) {
                        return elementOfWh(x, y, curX * 32, curY * 32);
                    } else if(whoMoves == "Black" && !c.checkBl(x*32,y*32)){
                        return elementOfBl(x, y, (curX) * 32, (curY) * 32);
                    }
                }
            }else if(x==curX+i && y==curY-i){
                if(checkJumpEle("rightDown", x, y)) {
                    if (whoMoves == "White"&& !c.checkWh(x*32,y*32)) {
                        return elementOfWh(x, y, curX * 32, curY * 32);
                    } else if(whoMoves == "Black" && !c.checkBl(x*32,y*32)){
                        return elementOfBl(x, y, (curX) * 32, (curY) * 32);
                    }
                }
            } else if(x==curX-i && y==curY+i){
                if(checkJumpEle("leftUp", x, y)) {
                    if (whoMoves == "White"&& !c.checkWh(x*32,y*32)) {
                        return elementOfWh(x, y, curX * 32, curY * 32);
                    } else if(whoMoves == "Black" && !c.checkBl(x*32,y*32)){
                        return elementOfBl(x, y, (curX) * 32, (curY) * 32);
                    }
                }
            }
            i++;
        }
        return false;
    }
    public boolean repaintTour(int x,int y){
        int i=1;
        while(i<8){
            if(x==curX && y==curY+i){
                if(checkJump("down", x, y)) {
                    if (whoMoves == "White" && !c.checkWh(x*32,y*32)) {
                        return elementOfWh(x, y, curX * 32, curY * 32);
                    } else if(whoMoves == "Black" && !c.checkBl(x*32,y*32)) {
                        return elementOfBl(x, y, (curX) * 32, (curY) * 32);
                    }
                }
            } else if(x==curX && y==curY-i){
                if(checkJump("up", x, y)) {
                    if (whoMoves == "White"&& !c.checkWh(x*32,y*32)) {
                        return elementOfWh(x, y, curX * 32, curY * 32);
                    } else if(whoMoves == "Black" && !c.checkBl(x*32,y*32)){
                        return elementOfBl(x, y, (curX) * 32, (curY) * 32);
                    }
                }
            }else if(x==curX+i && y==curY){
                if(checkJump("right", x, y)) {
                    if (whoMoves == "White"&& !c.checkWh(x*32,y*32)) {
                        return elementOfWh(x, y, curX * 32, curY * 32);
                    } else if(whoMoves == "Black" && !c.checkBl(x*32,y*32)){
                        return elementOfBl(x, y, (curX) * 32, (curY) * 32);
                    }
                }
            } else if(x==curX-i && y==curY){
                if(checkJump("left", x, y)) {
                    if (whoMoves == "White"&& !c.checkWh(x*32,y*32)) {
                        return elementOfWh(x, y, curX * 32, curY * 32);
                    } else if(whoMoves == "Black" && !c.checkBl(x*32,y*32)){
                        return elementOfBl(x, y, (curX) * 32, (curY) * 32);
                    }
                }
            }
            i++;
        }
        return false;
    }
    public boolean repaintPawnBl(int x,int y){
        if(x==curX-1 && y==curY+1&& c.checkWh((curX-1)*32,(curY+1)*32)){
            return elementOfBl(x, y, (curX) * 32, (curY) * 32);
        }
        else if((x==curX+1 && y==curY+1) && c.checkWh((curX+1)*32,(curY+1)*32)){
            return elementOfBl(x, y, (curX) * 32, (curY) * 32);
        }
        else if(x==curX && y==curY+1 && !c.checkBl((curX)*32,(curY+1)*32)&&!c.checkWh(curX*32,(curY+1)*32)){
            return elementOfBl(x, y, (curX) * 32, (curY) * 32);
        }
        else if(x==curX && y==curY+2 &&!c.checkBl((curX)*32,(curY+2)*32)&&!c.checkWh(curX*32,(curY+2)*32)){
            return elementOfBl(x, y, (curX) * 32, (curY) * 32);
        }
        return false;
    }
    public boolean repaintPawn(int x,int y){
        if(x==curX-1 && y==curY-1&& c.checkBl((x)*32,(y)*32)){
            return elementOfWh(x, y, (curX) * 32, (curY) * 32);
        }
        else if((x==curX+1 && y==curY-1) && c.checkBl((x)*32,(y)*32)){
            return elementOfWh(x, y, (curX) * 32, (curY) * 32);
        }
       else if(x==curX && y==curY-1 && !c.checkBl((curX)*32,(curY-1)*32)&&!c.checkWh(curX*32,(curY-1)*32)){
            return elementOfWh(x, y, (curX) * 32, (curY) * 32);
        }
        else if(x==curX && y==curY-2 &&!c.checkBl((curX)*32,(curY-2)*32)&&!c.checkWh(curX*32,(curY-2)*32)){
            return elementOfWh(x, y, (curX) * 32, (curY) * 32);
        }
        return false;
    }
    public boolean repaintHorse(int x, int y){

        if(x==(curX - 2)&&(curY - 1) ==y){
            if( whoMoves == "White" && !c.checkWh(x*32,y*32)) {
                return elementOfWh(x, y, curX * 32, curY * 32);
            }else if( whoMoves == "Black" && !c.checkBl(x*32,y*32)) {
                return elementOfBl(x, y, curX * 32, curY * 32);
            }
        }
        else  if((curX - 2)==x && (curY + 1)==y) {
            if (whoMoves == "White"&& !c.checkWh(x*32,y*32)) {
                return elementOfWh(x, y, curX * 32, curY * 32);
            } else if (whoMoves == "Black"&& !c.checkBl(x*32,y*32)) {
                return elementOfBl(x, y, curX * 32, curY * 32);
            }
        }
        else if((curX + 2)==x && (curY + 1)==y) {
            if (whoMoves == "White"&& !c.checkWh(x*32,y*32)) {
                return elementOfWh(x, y, curX * 32, curY * 32);
            } else if (whoMoves == "Black"&& !c.checkBl(x*32,y*32)) {
                return elementOfBl(x, y, curX * 32, curY * 32);
            }
        }
        else if((curX + 2)==x && (curY - 1)==y ) {
            if (whoMoves == "White"&& !c.checkWh(x*32,y*32)) {
                return elementOfWh(x, y, curX * 32, curY * 32);
            } else if (whoMoves == "Black"&& !c.checkBl(x*32,y*32)) {
                return elementOfBl(x, y, curX * 32, curY * 32);
            }
        }
        else if((curX - 1)==x && (curY - 2)==y) {
            if (whoMoves == "White"&& !c.checkWh(x*32,y*32)) {
                return elementOfWh(x, y, curX * 32, curY * 32);
            } else if (whoMoves == "Black"&& !c.checkBl(x*32,y*32)) {
                return elementOfBl(x, y, curX * 32, curY * 32);
            }
        }
        else if((curX - 1)==x && (curY +2)==y) {
            if (whoMoves == "White" && !c.checkWh(x*32,y*32)) {
                return elementOfWh(x, y, curX * 32, curY * 32);
            } else if (whoMoves == "Black"&& !c.checkBl(x*32,y*32)) {
                return elementOfBl(x, y, curX * 32, curY * 32);
            }
        }
        else if((curX + 1)==x && (curY +2)==y) {
            if (whoMoves == "White" && !c.checkWh(x*32,y*32)) {
                return elementOfWh(x, y, curX * 32, curY * 32);
            } else if (whoMoves == "Black"&& !c.checkBl(x*32,y*32)) {
                return elementOfBl(x, y, curX * 32, curY * 32);
            }
        }
        else if((curX + 1)==x && (curY -2)==y ) {
            if (whoMoves == "White" && !c.checkWh(x*32,y*32)) {
                return elementOfWh(x, y, curX * 32, curY * 32);
            } else if (whoMoves == "Black"&& !c.checkBl(x*32,y*32)) {
                return elementOfBl(x, y, curX * 32, curY * 32);
            }
        }
        return false;
    }
    public boolean checkJump(String str,int x,int y){
        int i=curY+1;
        if(str == "down"){
            while(i<y){
            if(c.checkBl(curX*32,(i)*32)||c.checkWh(curX*32,(i)*32)){
                    return false;
                }
                i++;
        }
        }
        else if(str == "up"){
            i=curY-1;
            while(i>y){
                if(c.checkBl(curX*32,(i)*32)||c.checkWh(curX*32,(i)*32)){
                    return false;
                }
                i--;
            }
        }
       else if(str == "right"){
            i=curX+1;
            while(i<x){
                if(c.checkBl((i)*32,(curY)*32)||c.checkWh((i)*32,(curY)*32)){
                    return false;
                }
                i++;
            }
        }
       else if(str == "left"){
            i=curX-1;
            while(i>x){
                if(c.checkBl((i)*32,(curY)*32)||c.checkWh((i)*32,(curY)*32)){
                    return false;
                }
                i--;
            }
        }
        return true;
    }
    public boolean checkJumpEle(String str,int x,int y){
        int i=curY+1;
        int i1=curX+1;
        if(str == "rightUp"){
            while(i<y ){
                if(c.checkBl(i1*32,(i)*32)||c.checkWh(i1*32,(i)*32)){
                    return false;
                }
                i++;
                i1++;
            }
        }
        else if(str == "leftDwn"){
            i=curY-1;
            i1=curX-1;
            while(i>y ){
                if(c.checkBl(i1*32,(i)*32)||c.checkWh(i1*32,(i)*32)){
                    return false;
                }
                i--;
                i1--;
            }
        }
        else if(str == "rightDown"){
            i=curX+1;
            i1=curY-1;
            while(i<x && i1>y){
                if(c.checkBl((i1)*32,(i)*32)||c.checkWh((i1)*32,(i)*32)){
                    return false;
                }
                i++;
                i1--;
            }
        }
        else if(str == "leftUp"){
            i=curX-1;
            i1=curY+1;
            while(i>x && i1<y){
                if(c.checkBl((i1)*32,(i)*32)||c.checkWh((i1)*32,(i)*32)){
                    return false;
                }
                i--;
                i1++;
            }
        }
        return true;
    }
    public void repaintCheckForEle(Graphics g){
        int  count1=1;
        while(curX-count1 >=0 && curY-count1>=0 && count1<8 && !c.checkWh((curX-count1) * 32, (curY - (count1)) * 32)&&!c.checkBl((curX-count1) * 32, (curY - (count1)) * 32)) {
            g.fillOval((curX-count1) * 32, (curY - (count1)) * 32, 32, 32);
            count1++;
        }
        count1=1;
        while(curX+count1 <8 && curY-count1>=0 && count1<8 && !c.checkWh((curX+count1) * 32, (curY - (count1)) * 32)&&!c.checkBl((curX+count1) * 32, (curY - (count1)) * 32)) {
            g.fillOval((curX+count1) * 32, (curY - (count1)) * 32, 32, 32);
            count1++;
        }
        count1=1;
        while(curX+count1 <8 && curY+count1<8 && count1<8 && !c.checkWh((curX+count1) * 32, (curY + (count1)) * 32)&&!c.checkBl((curX+count1) * 32, (curY + (count1)) * 32)) {
            g.fillOval((curX+count1) * 32, (curY + (count1)) * 32, 32, 32);
            count1++;
        }  count1=1;
        while(curX-count1 >=0&& curY+count1<8 && count1<8 && !c.checkWh((curX-count1) * 32, (curY + (count1)) * 32)&&!c.checkBl((curX-count1) * 32, (curY + (count1)) * 32)) {
            g.fillOval((curX-count1) * 32, (curY + (count1)) * 32, 32, 32);
            count1++;
        }
    }
    public void repaintCheckForTur(Graphics g){
        int  count1=1;
        while( curY- count1>=0&& !c.checkWh((curX )* 32,(curY - count1) * 32)&& !c.checkBl((curX )* 32,(curY - count1) * 32))
        {
            g.fillOval(curX * 32, (curY - (count1)) * 32, 32, 32);
            count1++;
        }
        count1=1;
        while( curY+count1<8 && !c.checkWh((curX )* 32,(curY + count1) * 32)&& !c.checkBl((curX )* 32,(curY + count1) * 32))
        {
            g.fillOval(curX * 32, (curY + (count1)) * 32, 32, 32);
            count1++;
        }
        count1=1;
        while(curX- count1>=0 && !c.checkWh((curX- count1 )* 32,(curY ) * 32)&& !c.checkBl((curX- count1 )* 32,(curY ) * 32))
        {
            g.fillOval((curX - count1)* 32, (curY ) * 32, 32, 32);
            count1++;
        }
        count1=1;
        while( curX+ count1<8 && !c.checkWh((curX+ count1 )* 32,(curY ) * 32)&& !c.checkBl((curX+ count1 )* 32,(curY ) * 32))
        {
            g.fillOval((curX+ count1) * 32, (curY ) * 32, 32, 32);
            count1++;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
         x= e.getX() / 32;
         y =e.getY() / 32;
        shine="";
//        System.out.println(pawn);
     if(whoMoves == "White" ) {
         if (repaintWh(x, y)) {
             whoMoves = "Black";
             pawn="";
         }
         else if(checkElementW(x, y)){
         shine="nothing";}
     }
         else if(whoMoves == "Black" ) {
         if (repaintWh(x, y)) {
             whoMoves = "White";
             pawn="";
         } else if(checkElementB(x, y)){
             shine="nothing";}
         }
         else{
             pawn = "";
         }
        c.repaint();
        curX = x;
        curY = y;
        f++;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX()/32;
        int y = e.getY()/32;
       c.xPoint = x*32;
        c.yPoint = y*32;
        c.repaint();
    }
}
