package com.example.clue_frontend;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Room {

    public Integer[] roomPlacements, doors;
    public  Object[][] border;

    public Object[][] getBorder() {
        return border;
    }
    public Room(Integer[] roomPlacements,  Object[][] border , Integer[] doors) {
        this.roomPlacements = roomPlacements;
        this.border = border;
        this.doors = doors;
    }
}
