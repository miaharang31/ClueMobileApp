package tz_7.GamePlay.CheckListDatabase;

import jakarta.persistence.*;
import tz_7.PlayerDatabase.Player;

import java.util.ArrayList;

/**
 * Author: Mia Harang
 * Database that stores the player's checklist
 *  Used if player exits and re-enters game
 */

@Entity
@Table(name = "GameLobby")
public class CheckList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @OneToOne
    @JoinColumn(name = "playerID")
    private Player player;
    boolean mustard;
    boolean plum;
    boolean green;
    boolean peacock;
    boolean scarlet;
    boolean white;
    boolean knife;
    boolean candlestick;
    boolean revolver;
    boolean rope;
    boolean leadpipe;
    boolean wrench;
    boolean hall;
    boolean lounge;
    boolean dining;
    boolean kitchen;
    boolean ballroom;
    boolean conservatory;
    boolean billiard;
    boolean library;
    boolean study;
//    private ArrayList<Boolean> weapons;
//    private ArrayList<Boolean> suspects;
//    private ArrayList<Boolean> rooms;

//    TODO: figure out something idk
    public CheckList(int id, Player player, boolean mustard, boolean plum, boolean green, boolean peacock,boolean scarlet, boolean white, boolean knife,boolean candlestick, boolean revolver, boolean rope,boolean leadpipe, boolean wrench, boolean hall, boolean lounge,boolean dining,boolean kitchen, boolean ballroom,  boolean conservatory, boolean billiard, boolean library,boolean study) {
        this.ID =id;
        this.player = player;
        this.plum =plum;
        this.green = green;
        this.peacock = peacock;
        this.scarlet = scarlet;
        this.white = white;
        this.knife = knife;
        this.candlestick = candlestick;
        this.revolver = revolver;
        this.rope = rope;
        this.leadpipe = leadpipe;
        this.wrench = wrench;
        this.hall = hall;
        this.lounge = lounge;
        this.dining = dining;
        this.kitchen = kitchen;
        this.ballroom = ballroom;
        this.conservatory = conservatory;
        this.billiard = billiard;
        this.library = library;
        this.study = study;
    }

    public Integer getID() {return ID;}
//    public ArrayList<Boolean> getWeapons() {return weapons;}
//    public ArrayList<Boolean> getRooms() {return rooms;}
//    public ArrayList<Boolean> getSuspects() {return suspects;}
    public Player getPlayer() {return player;}

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isMustard() {
        return mustard;
    }

    public void setMustard(boolean mustard) {
        this.mustard = mustard;
    }
}
