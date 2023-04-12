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
//    private ArrayList<Boolean> weapons;
//    private ArrayList<Boolean> suspects;
//    private ArrayList<Boolean> rooms;

//    TODO: figure out something idk
    public CheckList() {
//        weapons = new ArrayList<>();
//        suspects = new ArrayList<>();
//        rooms = new ArrayList<>();
    }

    public Integer getID() {return ID;}
//    public ArrayList<Boolean> getWeapons() {return weapons;}
//    public ArrayList<Boolean> getRooms() {return rooms;}
//    public ArrayList<Boolean> getSuspects() {return suspects;}
    public Player getPlayer() {return player;}
//    public Boolean isWeaponChecked(Integer weapon) {
//        return weapons.get(weapon);
//    }
//    public Boolean isSuspectChecked(Integer suspect) {
//        return suspects.get(suspect);
//    }
//    public Boolean isRoomChecked(Integer room) {
//        return rooms.get(room);
//    }
}
