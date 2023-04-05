package tz_7.GamePlay.GameLobbyDatabase;

/**
 * Author: Mia Harang
 * Table in DB for all game lobbies
 *  Starting the setup for the game as a whole
 */

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import tz_7.PlayerDatabase.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GameLobby")
public class GameLobby {
    /**
     * Database Columns
     */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID", unique = true)
        @NotFound(action = NotFoundAction.IGNORE)
    //    ID of the GameLobby
        private Integer ID;

        @Column(name = "maxPlayers")
        @NotFound(action = NotFoundAction.IGNORE)
    //    Max players allowed for the game
        private Integer maxPlayers;
        @Column(name = "numPlayers")
        @NotFound(action = NotFoundAction.IGNORE)
    //    Current number of players in the lobby
        private Integer numPlayers;

        @Column(name = "gameCode")
        @NotFound(action = NotFoundAction.IGNORE)
    //    Gamecode set by host
        private String gameCode;

        @Column(name = "isPremium")
        @NotFound(action = NotFoundAction.IGNORE)
    //    Indicates weather or not the game will be premium
        private Boolean isPremium;

    /**
     * JPA Relationships
     */
        @OneToMany(mappedBy = "gameLobby")
    //    Players in the lobby (not including the host)
        private Set<Player> players;

        @Column(name = "hostID", unique = true)
        @NotFound(action = NotFoundAction.IGNORE)
    //    Host that created the lobby
    //    TODO: CREATE A ONE TO ONE RELATIONSHIP
        private Integer hostID;

    /**
     * No-Argument constructor
     *  Initializes Set and starts number of players to 1
     */
    public GameLobby() {
        players = new HashSet<>();
        numPlayers = 1;
    }

    /**
     * Argument constructor
     *  Initializes Set and sets number of players to one
     *  Sets the related variables to given arguments
     * @param maxPlayers
     * @param gameCode
     * @param hostID
     * @param isPremium
     */
    public GameLobby(Integer maxPlayers, String gameCode, Integer hostID, Boolean isPremium) {
        this.maxPlayers = maxPlayers;
        this.gameCode = gameCode;
        this.hostID = hostID;
        this.isPremium = isPremium;

        players = new HashSet<>();
        numPlayers = 1;
    }

    /**
     * Checks if the current number of players is
     * less than the max players
     * @return
     *  true if it is (indicating you can add another player)
     *  false if it isn't
     */
    private boolean canAddPlayer() {
        if(numPlayers+1 <= maxPlayers) {return true;}
        return false;
    }

    /**
     * Adds the given player to the set of players in the lobby
     *  Checks if it is possible first
     * @param player
     *  The Player that is to be added
     * @return
     *  true if the player was added
     *  false if they couldn't be
     */
    public Boolean addPlayer(Player player) {
        if(canAddPlayer()) {
            this.players.add(player);
            numPlayers += 1;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes a player from the lobby
     *  Only does it if they are in the lobby
     *  and the number of players is more than 0
     * @param player
     *  Player to be removed
     * @return
     *  true if the player is removed
     *  false if they couldn't be
     */
    public Boolean removePlayer(Player player) {
        if(numPlayers > 0 && players.contains(player)) {
            players.remove(player);
            numPlayers--;
            return true;
        } else { return false;}
    }

    /**
     * All the get arguments for every variable
     * @return
     *  the variable that is needed
     */
    public Set<Player> getCurPlayerIDs() {return players;}
    public String getGameCode() {return gameCode;}
    public Integer getHostID() {return hostID;}
    public Integer getID() {return ID;}
    public Boolean getIsPremium() {return isPremium;}
    public Integer getMaxPlayers() {return maxPlayers;}
    public Integer getNumPlayers() {return numPlayers;}
    public Set<Player> getPlayers() {return players;}
}