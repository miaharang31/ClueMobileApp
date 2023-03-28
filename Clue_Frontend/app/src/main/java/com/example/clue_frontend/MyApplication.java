package com.example.clue_frontend;

import android.app.Application;

public class MyApplication extends Application {
    private int userid;
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
}
