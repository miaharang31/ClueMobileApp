package tz_7.GamePlay.GameStateDatabase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import tz_7.CardDatabase.Card;
import tz_7.CardDatabase.CardController;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;
import tz_7.PlayerDatabase.Player;

import java.util.*;

/**
 * Author: Mia Harang
 * Database that holds the information of all the current games in session
 *  Deals with turn order and cards
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
    private Integer ID;

    @Column(name = "hostID")
    private Integer hostID;

    @Column(name = "turnNum")
    private Integer turnNum;

    @Column(name = "gameType")
    private String gameType;

    /**
     * JPA Relationships
     */
//    @Column(name = "versionID")
//    @NotFound(action = NotFoundAction.IGNORE)
////    TODO: create version database
//    private Integer versionID;

    @ManyToMany(mappedBy = "gameState")
    @JsonIgnore
    private Set<Card> finalCards;

    @ManyToMany(mappedBy = "gameState")
    @JsonIgnore
    private Set<Card> cards;

//    TODO: Combine weapons, rooms and suspects
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


    /**
     * Null constructor
     *  Initializes all sets and sets the turnNum to 0
     *  this will be used to determine the current player
     */
    public GameState() {
        turnOrder = new HashSet<>();
        weapons = new HashSet<>();
        suspects = new HashSet<>();
        rooms = new HashSet<>();
        finalCards = new HashSet<>();
        turnNum = 0;
    }

    /**
     * Argument Constructor
     *  Initializes all sets and sets the turnNum to 0
     *  Also sets the hostID and turn order based on the
     *  information in the GameLobby object provided
     * @param lobby
     *  Lobby object to create GameState object from
     */
    public GameState(GameLobby lobby) {
        turnOrder = new HashSet<>();
        weapons = new HashSet<>();
        suspects = new HashSet<>();
        rooms = new HashSet<>();
        finalCards = new HashSet<>();
        turnNum = 0;
        gameType = lobby.getHost().getType();
        hostID = lobby.getHost().getId();
        turnOrder.addAll(lobby.getPlayers());
        turnOrder.add(lobby.getHost());
    }

    /**
     * Gets the next player
     * @return
     */
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

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    /**
     * Sets the turnOrder
     * @param players
     *  Set of players to add to the game
     */
    public void setTurnOrder(Set<Player> players) {
        turnOrder = players;
    }

//    /**
//     * Sets the weapons for the game
//     *  Sets the final weapon card as well
//     * @param weapons
//     *  Set of weapon cards
//     */
//    TODO: could probably make this one method
//    public void setWeapons(Set<Card> weapons) {
//        this.weapons = weapons;
//        Random rand = new Random();
//        int n = rand.nextInt(weapons.size());
//        Card finalWeapon = weapons.toArray(new Card[weapons.size()])[n];
//        finalCards.add(finalWeapon);
//        weapons.remove(finalWeapon);
//    }

    public void setAllCards(String s) {
        CardController cardController = new CardController();
        if (s.equals("b")) {
            cards = cardController.getBasicCards();
        }
        else {
            cards = cardController.getPremiumCards();
        }
    }
    public void setFinalCards(String s) {
        Random rand = new Random();
        Card[] cards1 = (Card[]) cards.toArray();
        int num = rand.nextInt(cards1.length);
        while (!cards1[num].getCardType().equals("s")) {
            num = rand.nextInt(cards1.length);
        }
        finalCards.add(cards1[num]);
        num = rand.nextInt(cards1.length);
        while (!cards1[num].getCardType().equals("w")) {
            num = rand.nextInt(cards1.length);
        }
        finalCards.add(cards1[num]);
        num = rand.nextInt(cards1.length);
        while (!cards1[num].getCardType().equals("r")) {
            num = rand.nextInt(cards1.length);
        }
        finalCards.add(cards1[num]);
    }
//
//    /**
//     * Sets the rooms for the game
//     *  Sets the final weapon card as well
//     * @param rooms
//     *  Set of weapon cards
//     */
//    public void setRooms(Set<Card> rooms) {
//        this.rooms = rooms;
//        Random rand = new Random();
//        int n = rand.nextInt(rooms.size());
//        Card finalRoom = rooms.toArray(new Card[rooms.size()])[n];
//        finalCards.add(finalRoom);
//        rooms.remove(finalRoom);
//    }
//
//    /**
//     * Sets the suspects for the game
//     *  Sets the final weapon card as well
//     * @param suspects
//     *  Set of weapon cards
//     */
//    public void setSuspects(Set<Card> suspects) {
//        this.suspects = suspects;
//        Random rand = new Random();
//        int n = rand.nextInt(suspects.size());
//        Card finalSuspect = suspects.toArray(new Card[suspects.size()])[n];
//        finalCards.add(finalSuspect);
//        suspects.remove(finalSuspect);
//    }

    /**
     * Removes all of the players from
     * the game state
     */
    public void removePlayers() {
        Iterator<Player> players = turnOrder.iterator();
        while(players.hasNext()) {
            turnOrder.remove(players.next());
        }
    }
    public void removeCards() {
        Iterator<Card> cards1 = cards.iterator();
        while(cards1.hasNext()) {
            cards.remove(cards1.next());
        }
    }

    /**
     * Series of get methods for almost every variable
     * @return
     *  Different variables depending on the method
     */
    public Integer getID() {return ID;}
    public Set<Card> getFinalCards() {return finalCards;}
    public Set<Card> getWeapons() {return weapons;}
    public Set<Card> getSuspects() {return suspects;}
    public Set<Card> getRooms() {return rooms;}
    public Integer getHostID() {return hostID;}
    public Set<Player> getTurnOrder() {
        return turnOrder;
    }
}
