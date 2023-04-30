package com.example.clue_frontend;

import android.app.Application;

import org.java_websocket.client.WebSocketClient;

public class MyApplication extends Application {
    private int userid;
    private int gameid;
    private int lobbyid;
    private int infoid;

    private String username;
    private String firstname;
    private String lastname;
    private String type;

    private boolean isHost;

    private int usersplaying;

    private boolean mustard;
    private boolean plum;
    private boolean green;
    private boolean peacock;
    private boolean scarlet;
    private boolean white;
    private boolean knife;
    private boolean candlestick;
    private boolean revolver;
    private boolean rope;
    private boolean leadpipe;
    private boolean wrench;
    private boolean hall;
    private boolean lounge;
    private boolean dining;
    private boolean kitchen;
    private boolean ballroom;
    private boolean conservatory;
    private boolean billiard;
    private boolean library;
    private boolean study;

    private WebSocketClient gameClient;
    private WebSocketClient lobbyClient;

    public void setGameClient(WebSocketClient gameClient) {
        this.gameClient = gameClient;
    }
    public WebSocketClient getGameClient() {
        return gameClient;
    }
    public void resetCards() {
        mustard = false;
        plum = false;
        green = false;
        peacock = false;
        white = false;
        scarlet = false;
        knife = false;
        candlestick = false;
        leadpipe = false;
        rope = false;
        revolver = false;
        wrench = false;
        hall = false;
        library = false;
        lounge = false;
        kitchen = false;
        dining = false;
        billiard = false;
        ballroom = false;
        conservatory = false;
        study = false;
    }
    public void endGame() {
        lobbyid = 0;
        gameid = 0;
        infoid = 0;
        isHost = false;
        usersplaying = 0;
        resetCards();
    }
    public void clear() {
        userid = 0;
        endGame();
    }


    public int getLobbyid() {return lobbyid;}
    public int getGameid() {return gameid;}
    public int getUserid() {
        return userid;
    }
    public int getInfoid() {
        return infoid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public boolean isMustard() {
        return mustard;
    }

    public void setMustard(boolean mustard) {
        this.mustard = mustard;
    }

    public boolean isPlum() {
        return plum;
    }

    public void setPlum(boolean plum) {
        this.plum = plum;
    }

    public boolean isGreen() {
        return green;
    }

    public void setGreen(boolean green) {
        this.green = green;
    }

    public boolean isPeacock() {
        return peacock;
    }

    public void setPeacock(boolean peacock) {
        this.peacock = peacock;
    }

    public boolean isScarlet() {
        return scarlet;
    }

    public void setScarlet(boolean scarlet) {
        this.scarlet = scarlet;
    }

    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isKnife() {
        return knife;
    }

    public void setKnife(boolean knife) {
        this.knife = knife;
    }

    public boolean isCandlestick() {
        return candlestick;
    }

    public void setCandlestick(boolean candlestick) {
        this.candlestick = candlestick;
    }

    public boolean isRevolver() {
        return revolver;
    }

    public void setRevolver(boolean revolver) {
        this.revolver = revolver;
    }

    public boolean isRope() {
        return rope;
    }

    public void setRope(boolean rope) {
        this.rope = rope;
    }

    public boolean isLeadpipe() {
        return leadpipe;
    }

    public void setLeadpipe(boolean leadpipe) {
        this.leadpipe = leadpipe;
    }

    public boolean isWrench() {
        return wrench;
    }

    public void setWrench(boolean wrench) {
        this.wrench = wrench;
    }

    public boolean isHall() {
        return hall;
    }

    public void setHall(boolean hall) {
        this.hall = hall;
    }

    public boolean isLounge() {
        return lounge;
    }

    public void setLounge(boolean lounge) {
        this.lounge = lounge;
    }

    public boolean isDining() {
        return dining;
    }

    public void setDining(boolean dining) {
        this.dining = dining;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    public boolean isBallroom() {
        return ballroom;
    }

    public void setBallroom(boolean ballroom) {
        this.ballroom = ballroom;
    }

    public boolean isConservatory() {
        return conservatory;
    }

    public void setConservatory(boolean conservatory) {
        this.conservatory = conservatory;
    }

    public boolean isBilliard() {
        return billiard;
    }

    public void setBilliard(boolean billiard) {
        this.billiard = billiard;
    }

    public boolean isLibrary() {
        return library;
    }

    public void setLibrary(boolean library) {
        this.library = library;
    }

    public boolean isStudy() {
        return study;
    }

    public void setStudy(boolean study) {
        this.study = study;
    }

    public void setHost(boolean host) {
        isHost = host;
    }

    public boolean isHost() {
        return isHost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
