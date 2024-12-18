package tz_7.GamePlay.GameLobbyDatabase;

/**
 * Author: Mia Harang
 * Table in DB for all game lobbies
 *  Starting the setup for the game as a whole
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.repository.cdi.Eager;
import tz_7.GamePlay.GameStateDatabase.GameState;
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
        @OneToMany(mappedBy = "gameLobby", fetch = FetchType.EAGER)
    //    Players in the lobby (not including the host)
        private Set<Player> players;

        @OneToOne
        @JsonIgnore
//        Host that creates the lobby
        private Player host;

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
     * @param host
     * @param isPremium
     */
    public GameLobby(Integer maxPlayers, String gameCode, Player host, Boolean isPremium) {
        this.maxPlayers = maxPlayers;
        this.gameCode = gameCode;
        this.host = host;
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
            numPlayers = players.size() + 1;
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
            numPlayers = players.size() + 1;
            return true;
        } else { return false;}
    }

    public void setHost(Player host) {
        this.host = host;
    }

    /**
     * All the get arguments for every variable
     * @return
     *  the variable that is needed
     */
    public String getGameCode() {return gameCode;}
    public Integer getID() {return ID;}
    public Integer getMaxPlayers() {return maxPlayers;}
    public Integer getNumPlayers() {return numPlayers;}
    public Set<Player> getPlayers() {return players;}
    public Player getHost() {return host;}
}