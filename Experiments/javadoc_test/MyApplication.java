package com.example.clue_frontend;

import android.app.Application;

public class MyApplication extends Application {
    private int userid;
    private int gameid;
    private int lobbyid;
    private int infoid;

    private int usersplaying;

    public int getLobbyid() {return lobbyid;}
    public int getGameid() {return gameid;}
    public int getUserid() {
        return userid;
    }
    public int getInfoid() {
        return infoid;
    }
    public void setInfoid(int infoid) {
        this.infoid = infoid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }
    public void setLobbyid(int lobbyid) {this.lobbyid = lobbyid;}

    public int getUsersplaying() {return usersplaying;}
    public void setUsersplaying(int usersplaying) { this.usersplaying = usersplaying; }
}
