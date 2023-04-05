package tz_7.GamePlay.GameLobbyDatabase;

/**
 * Author: Mia Harang
 * Table in DB for all open games
 * Includes:
 *  Game type (ie. Premium or Normal)
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer ID;
    @Column(name = "maxPlayers")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer maxPlayers;
    @Column(name = "numPlayers")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer numPlayers;
    @Column(name = "gameCode")
    @NotFound(action = NotFoundAction.IGNORE)
    private String gameCode;
    @OneToMany(mappedBy = "gameLobby")
    private Set<Player> players;
    @Column(name = "hostID", unique = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer hostID;
    @Column(name = "isPremium")
    @NotFound(action = NotFoundAction.IGNORE)
    private Boolean isPremium;

    public GameLobby() {
        players = new HashSet<>();
        numPlayers = 1;
    }

    public GameLobby(Integer maxPlayers, String gameCode, Integer hostID, Boolean isPremium) {
        this.maxPlayers = maxPlayers;
        this.gameCode = gameCode;
        this.hostID = hostID;
        this.isPremium = isPremium;

        players = new HashSet<>();
        numPlayers = 1;
    }

    public Set<Player> getCurPlayerIDs() {return players;}
    private boolean canAddPlayer() {
        if(numPlayers+1 <= maxPlayers) {return true;}
        return false;
    }

    public Boolean addPlayer(Player player) {
        if(canAddPlayer()) {
            this.players.add(player);
            numPlayers += 1;
            return true;
        } else {
            return false;
        }
    }
    public String getGameCode() {return gameCode;}
    public Integer getHostID() {return hostID;}
    public Integer getID() {return ID;}
    public Boolean getIsPremium() {return isPremium;}
    public Integer getMaxPlayers() {return maxPlayers;}
    public Integer getNumPlayers() {return numPlayers;}
    public Set<Player> getPlayers() {return players;}
}