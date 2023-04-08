package com.example.boardtest;

import android.graphics.Bitmap;

public class Room {
    private Bitmap bmRoom;
    private int x ,y, width, height;


    public Room(Bitmap bmRoom, int x, int y, int width, int height) {
        this.bmRoom = bmRoom;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Bitmap getBmRoom() {
        return bmRoom;
    }

    public void setBmRoom(Bitmap bmRoom) {
        this.bmRoom = bmRoom;
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
}
