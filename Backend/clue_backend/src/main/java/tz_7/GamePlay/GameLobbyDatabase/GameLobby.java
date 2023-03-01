package tz_7.GamePlay.GameLobbyDatabase;

/**
 * Table in DB for all open games
 * Includes:
 *  Game type (ie. Premium or Normal)
 *
 */

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.ArrayList;

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
    @Column(name = "playerIDs")
    @NotFound(action = NotFoundAction.IGNORE)
    private ArrayList<Integer> playerIDs;

    @Column(name = "hostID", unique = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer hostID;
    @Column(name = "isPremium")
    @NotFound(action = NotFoundAction.IGNORE)
    private Boolean isPremium;

    public GameLobby() {
        playerIDs = new ArrayList<Integer>();
        numPlayers = 1;
    }

    public GameLobby(Integer maxPlayers, String gameCode, Integer hostID, Boolean isPremium) {
        this.maxPlayers = maxPlayers;
        this.gameCode = gameCode;
        this.hostID = hostID;
        this.isPremium = isPremium;

        playerIDs = new ArrayList<Integer>();
        numPlayers = 1;
    }

    public ArrayList<Integer> getCurPlayerIDs() {return playerIDs;}
    private boolean canAddPlayer() {
        if(numPlayers+1 <= maxPlayers) {return true;}
        return false;
    }

    public Boolean addPlayer(Integer playerID) {
        if(canAddPlayer()) {
            playerIDs.add(playerID);
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
}