package com.example.clue_frontend;

import android.graphics.Bitmap;

public class Tile {
    private Bitmap bm;
    private int x ,y, width, height;

<<<<<<< HEAD
=======
    public Bitmap getBm() {
        return bm;
    }

    public int getTileX() {
        return x;
    }

    public int getTileY() {
        return y;
    }
>>>>>>> d1c6acab2a62f6baafff8147032537a14a6cdabf
    public Tile(Bitmap bm, int x, int y, int width, int height){
        this.bm = bm;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

<<<<<<< HEAD
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
=======

>>>>>>> d1c6acab2a62f6baafff8147032537a14a6cdabf
}

