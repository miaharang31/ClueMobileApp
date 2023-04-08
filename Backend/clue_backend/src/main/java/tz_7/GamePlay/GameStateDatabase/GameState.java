package tz_7.GamePlay.GameStateDatabase;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Autowired;
import tz_7.CardDatabase.Card;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;
import tz_7.CardDatabase.CardRepository;
import tz_7.PlayerDatabase.Player;

import java.util.*;

/**
 * Author: Mia Harang
 * Database that holds the information of all the current games in session
 */

@Entity
@Table(name = "GameState")
public class GameState {
    /**
     * JPA Database Columns
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
//    ID for the current game state
    private Integer ID;

    /**
     * JPA Relationships
     */
    @Column(name = "versionID")
    @NotFound(action = NotFoundAction.IGNORE)
//    TODO: create version database
    private Integer versionID;

//    @OneToMany(mappedBy = "gameState")
//    @JsonIgnore
//    private Set<Card> finalCards;
//
//    @OneToMany(mappedBy = "gameState")
//    @JsonIgnore
//    private Set<Card> weapons;
//
//    @OneToMany(mappedBy = "gameState")
//    @JsonIgnore
//    private Set<Card> suspects;
//
//    @OneToMany(mappedBy = "gameState")
//    @JsonIgnore
//    private Set<Card> rooms;

    @OneToMany
    private Set<Player> turnOrder;

    private Random rand;
    private Integer turnNum;

    /**
     * Overrides default constructor to initialize all
     * necessary variables
     */
    public GameState() {
        rand = new Random();
        turnOrder = new HashSet<>();
//        weapons = new HashSet<>();
//        suspects = new HashSet<>();
//        rooms = new HashSet<>();
//        finalCards = new HashSet<>();
        turnNum = 0;
    }

    public GameState(Integer versionID) {
        this.versionID = versionID;
    }

    public Player getNextPlayer() {
        Player[] players = turnOrder.toArray(new Player[0]);
        Player tmp = players[turnNum];
        if(turnNum == turnOrder.size()-1) {turnNum = 0;}
        else {turnNum += 1;}
        return tmp;
    }

    /**
     * Checks the final guess that a player makes
     * @param guess
     *  List of card IDs
     * @return
     *  true - if all IDs match
     *  false - if they don't
     */
//    public Boolean checkFinalGuess(Set<Card> guess) {
//        for (int i = 0; i < finalCards.size(); i++) {
//            if(!finalCards.containsAll(guess)) {return false;}
//        }
//        return true;
//    }

    /**
     * Randomly selects from weapons, suspects, and rooms
     * to set as the final cards, takes the cards from the
     * decks
     */
//    public Set<Card> setFinalCards() {
//        int weapon = rand.nextInt(weapons.size());
//        int suspect = rand.nextInt(suspects.size());
//        int room = rand.nextInt(rooms.size());
//
//        Card[] weaponstmp = weapons.toArray(new Card[0]);
//        Card[] suspectstmp = suspects.toArray(new Card[0]);
//        Card[] roomstmp = rooms.toArray(new Card[0]);
//
//        finalCards.add(weaponstmp[weapon]);
//        finalCards.add(suspectstmp[suspect]);
//        finalCards.add(roomstmp[room]);
//
//        weapons.remove(weaponstmp[weapon]);
//        suspects.remove(suspectstmp[suspect]);
//        rooms.remove(roomstmp[room]);
//
//        return finalCards;
//    }

    public void setTurnOrder(Set<Player> players) {
        turnOrder = players;
    }

//    public void setWeapons(Set<Card> weapons) {
//        this.weapons = weapons;
//    }
//
//    public void setRooms(Set<Card> rooms) {
//        this.rooms = rooms;
//    }
//
//    public void setSuspects(Set<Card> suspects) {
//        this.suspects = suspects;
//    }

    /**
     * Series of get methods for almost every variable
     * @return
     *  Different variables depending on the method
     */
    public Integer getVersionID() {return versionID;}
    public Integer getID() {return ID;}
//    public Set<Card> getFinalCards() {return finalCards;}
//    public Set<Card> getWeapons() {return weapons;}
//    public Set<Card> getSuspects() {return suspects;}
//    public Set<Card> getRooms() {return rooms;}

    public Set<Player> getTurnOrder() {
        return turnOrder;
    }
}
