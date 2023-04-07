package com.example.clue_frontend;

import android.app.Application;

public class MyApplication extends Application {
    private int userid;
    private int gameid;
    public int getGameid() {return gameid;}
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public void setGameid(int gameid) {
        this.gameid = gameid;
    }
}
