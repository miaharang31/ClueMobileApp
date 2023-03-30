package com.example.boardtest;

import android.graphics.Bitmap;

public class Board {
    private Bitmap bm;
    private int x ,y, width, height;

    public Bitmap getBm() {
        return bm;
    }

    public int getTileX() {
        return x;
    }

    public int getTileY() {
        return y;
    }
    public Board(Bitmap bm, int x, int y, int width, int height){
        this.bm = bm;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


}
