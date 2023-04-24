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

    @ManyToMany(mappedBy = "gameState")
    @JsonIgnore
    private Set<Card> finalCards;

    @ManyToMany(mappedBy = "gameState")
    @JsonIgnore
    private Set<Card> weapons;

    @ManyToMany(mappedBy = "gameState")
    @JsonIgnore
    private Set<Card> suspects;

    @ManyToMany(mappedBy = "gameState")
    @JsonIgnore
    private Set<Card> rooms;

    @OneToMany
    private Set<Player> turnOrder;

    private Integer turnNum;

    /**
     * Overrides default constructor to initialize all
     * necessary variables
     */
    public GameState() {
        turnOrder = new HashSet<>();
        weapons = new HashSet<>();
        suspects = new HashSet<>();
        rooms = new HashSet<>();
        finalCards = new HashSet<>();
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
    public Boolean checkFinalGuess(Set<Card> guess) {
        for (int i = 0; i < finalCards.size(); i++) {
            if(!finalCards.containsAll(guess)) {return false;}
        }
        return true;
    }

    public void setTurnOrder(Set<Player> players) {
        turnOrder = players;
    }

    public void setWeapons(Set<Card> weapons) {
        this.weapons = weapons;
        Random rand = new Random();
        int n = rand.nextInt(weapons.size());
        Card finalWeapon = weapons.toArray(new Card[weapons.size()])[n];
        finalCards.add(finalWeapon);
        weapons.remove(finalWeapon);
    }

    public void setRooms(Set<Card> rooms) {
        this.rooms = rooms;
        Random rand = new Random();
        int n = rand.nextInt(rooms.size());
        Card finalRoom = rooms.toArray(new Card[rooms.size()])[n];
        finalCards.add(finalRoom);
        rooms.remove(finalRoom);
    }

    public void setSuspects(Set<Card> suspects) {
        this.suspects = suspects;
        Random rand = new Random();
        int n = rand.nextInt(suspects.size());
        Card finalSuspect = suspects.toArray(new Card[suspects.size()])[n];
        finalCards.add(finalSuspect);
        suspects.remove(finalSuspect);
    }

    /**
     * Series of get methods for almost every variable
     * @return
     *  Different variables depending on the method
     */
    public Integer getVersionID() {return versionID;}
    public Integer getID() {return ID;}
    public Set<Card> getFinalCards() {return finalCards;}
    public Set<Card> getWeapons() {return weapons;}
    public Set<Card> getSuspects() {return suspects;}
    public Set<Card> getRooms() {return rooms;}

    public Set<Player> getTurnOrder() {
        return turnOrder;
    }
}
