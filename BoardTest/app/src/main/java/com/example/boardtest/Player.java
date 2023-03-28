package com.example.boardtest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player {
    public Bitmap bm, player;
    private int x, y;

    private boolean move_up, move_down, move_left, move_right;



    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rect getPlayer() {return new Rect(this.x,this.y,this.x+GameView.sizeOfMap,this.y+GameView.sizeOfMap);}

    public boolean isMove_up() {return move_up;}
    public void setMove_up(boolean move_up) {
        move();
        this.move_up = move_up;
    }

    public boolean isMove_down() {return move_down;}

    public void setMove_down(boolean move_down) {
        move();
        this.move_down = move_down;
    }

    public boolean isMove_left() {return move_left;}

    public void setMove_left(boolean move_left) {
        move();
        this.move_left = move_left;
    }

    public boolean isMove_right() {return move_right;}

    public void setMove_right(boolean move_right) {
        move();
        this.move_right = move_right;
    }

    public Player(Bitmap bm, int x, int y) {
        this.bm = bm;
        this.x = x;
        this.y = y;

        setMove_right(true);
    }

    public void move(){
        this.move_left = false;
        this.move_right = false;
        this.move_up = false;
        this.move_down = false;
    }

}
