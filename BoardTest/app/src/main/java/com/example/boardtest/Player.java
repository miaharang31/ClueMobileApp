package com.example.boardtest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player {

    public Bitmap bm;
    private int placement, x, y;

    public Bitmap getBm() {
        return bm;
    }

    public int getPlacement() {
        return placement;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
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

    public Player(Bitmap bm, int placement, int x, int y) {
        this.bm = bm;
        this.placement = placement;
        this.x = x;
        this.y = y;
    }
}
