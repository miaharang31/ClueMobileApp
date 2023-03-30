package tz_7.GamePlay.GameStateDatabase;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: Mia Harang
 * Database that holds the information of all the current games in session
 */

@Entity
@Table(name = "GameState")
public class GameState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    @NotFound(action = NotFoundAction.IGNORE)
    /**
     * Game ID
     */
    private Integer ID;

    @Column(name = "versionID")
    @NotFound(action = NotFoundAction.IGNORE)
    /**
     * The ID of the version of game that it is
     * i.e basic or some sort of premium game (held in separate table)
     */
    private Integer versionID;

    @Column(name = "lobbyID")
    @NotFound(action = NotFoundAction.IGNORE)
    /**
     * ID of the lobby that everyone came from
     */
    private Integer lobbyID;

    @Column(name = "finalCardIDs")
    @NotFound(action = NotFoundAction.IGNORE)
    /**
     * IDs of the cards that are "put in the center"
     * Used to check if the final guess of a player is correct
     */
    private Integer[] finalCardIDs;

//    @Column(name = "abilityDeck")
//    @NotFound(action = NotFoundAction.IGNORE)
//    /**
//     * IDs of all special ability cards
//     *  if the cards are in a player's hand, then it is taken out of the deck
//     * FOR PREMIUM GAME ONLY
//     * FOR BASIC: is a non-initialized array errors thrown when trying to access it
//     */
//    private ArrayList<Integer> abilityDeck;

    @Column(name = "weapons")
    @NotFound(action = NotFoundAction.IGNORE)
    /**
     * Card IDs for the weapons
     */
    private ArrayList<Integer> weapons;

    @Column(name = "suspects")
    @NotFound(action = NotFoundAction.IGNORE)
    private ArrayList<Integer> suspects;

    @Column(name = "rooms")
    @NotFound(action = NotFoundAction.IGNORE)
    private ArrayList<Integer> rooms;

//    @Column(name = "turnOrder")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private ArrayList<Integer> turnOrder;
//    private ArrayList<Integer> deck;
    private Random rand;
    private Integer turnNum;

    public GameState() {
        finalCardIDs = new Integer[3];
//        turnOrder = new ArrayList<>();
        rand = new Random();
        weapons = new ArrayList<>();
        rooms = new ArrayList<>();
        suspects = new ArrayList<>();
        turnNum = 0;
    }

    public GameState(Integer versionID) {
        this.versionID = versionID;
    }

//    public Integer getNextPlayer() {
//        Integer tmp = turnOrder.get(turnNum);
//        if(turnNum == turnOrder.size()-1) {turnNum = 0;}
//        else {turnNum += 1;}
//        return tmp;
//    }

    public Boolean checkFinalGuess(Integer[] guess) {
        for (int i = 0; i < finalCardIDs.length; i++) {
            if(guess[i] != finalCardIDs[i]) {return false;}
        }
        return true;
    }

    public void setFinalCardIDs() {
        int weapon = rand.nextInt(weapons.size());
        int suspect = rand.nextInt(suspects.size());
        int room = rand.nextInt(rooms.size());

        finalCardIDs[0] = weapons.get(weapon);
        finalCardIDs[1] = suspects.get(suspect);
        finalCardIDs[2] = rooms.get(room);

        weapons.remove(weapon);
        suspects.remove(suspect);
        rooms.remove(room);

    }
    public Integer[] getFinalCardIDs() {return finalCardIDs;}
    public Integer getVersionID() {return versionID;}
    public Integer getID() {return ID;}
    public Integer getLobbyID() {return lobbyID;}
    public ArrayList<Integer> getWeapons() {return weapons;}
    public ArrayList<Integer> getSuspects() {return suspects;}
    public ArrayList<Integer> getRooms() {return rooms;}
}
