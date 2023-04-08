package tz_7.GamePlay.GameStateDatabase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Autowired;
import tz_7.CardDatabase.Card;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;
import tz_7.CardDatabase.CardRepository;
import tz_7.PlayerDatabase.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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

    @OneToOne
    @JsonIgnore
//    Game Lobby the players were coming from
    private GameLobby gameLobby;

    @OneToMany(mappedBy = "gameStateFinal")
    private Set<Card> finalCards;

    @OneToMany(mappedBy = "gameStateWeapons")
    private Set<Card> weapons;

    @OneToMany(mappedBy = "gameStateSuspects")
    private Set<Card> suspects;

    @OneToMany(mappedBy = "gameStateRooms")
    private Set<Card> rooms;

    @OneToMany(mappedBy = "gameState")
    private Set<Player> turnOrder;

    private Random rand;
    private Integer turnNum;

//    @Autowired
//    private CardRepository cardRepository;

    /**
     * Overrides default constructor to initialize all
     * necessary variables
     */
    public GameState() {
        finalCards = new HashSet<>();
        rand = new Random();
        weapons = new HashSet<>();
        rooms = new HashSet<>();
        suspects = new HashSet<>();
        turnOrder = new HashSet<>();
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

    /**
     * Randomly selects from weapons, suspects, and rooms
     * to set as the final cards, takes the cards from the
     * decks
     */
    public void setFinalCards() {
        int weapon = rand.nextInt(weapons.size());
        int suspect = rand.nextInt(suspects.size());
        int room = rand.nextInt(rooms.size());

        Card[] weaponstmp = weapons.toArray(new Card[0]);
        Card[] suspectstmp = suspects.toArray(new Card[0]);
        Card[] roomstmp = rooms.toArray(new Card[0]);

        finalCards.add(weaponstmp[weapon]);
        finalCards.add(suspectstmp[suspect]);
        finalCards.add(roomstmp[room]);

        weaponstmp[weapon].setGameStateFinal(this);
        suspectstmp[suspect].setGameStateFinal(this);
        roomstmp[room].setGameStateFinal(this);

//        cardRepository.save(weaponstmp[weapon]);
//        cardRepository.save(suspectstmp[suspect]);
//        cardRepository.save(roomstmp[room]);

        weapons.remove(weaponstmp[weapon]);
        suspects.remove(suspectstmp[suspect]);
        rooms.remove(roomstmp[room]);
    }

    public void setGameLobby(GameLobby lobby) {
        gameLobby = lobby;
    }

    /**
     * Series of get methods for almost every variable
     * @return
     *  Different variables depending on the method
     */
    public Set<Card> getFinalCards() {return finalCards;}
    public Integer getVersionID() {return versionID;}
    public Integer getID() {return ID;}
    public GameLobby getLobby() {return gameLobby;}
    public Set<Card> getWeapons() {return weapons;}
    public Set<Card> getSuspects() {return suspects;}
    public Set<Card> getRooms() {return rooms;}

    public Set<Player> getTurnOrder() {
        return turnOrder;
    }
}
