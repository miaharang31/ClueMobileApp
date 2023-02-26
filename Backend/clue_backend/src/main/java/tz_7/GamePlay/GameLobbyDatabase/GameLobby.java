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
    @Column(name = "gameID", unique = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer gameID;
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

    public GameLobby() {}

    public GameLobby(Integer maxPlayers, String gameCode, Integer hostID, Boolean isPremium) {
        this.maxPlayers = maxPlayers;
        this.gameCode = gameCode;
        this.hostID = hostID;
        this.isPremium = isPremium;

        playerIDs = new ArrayList<Integer>();
        playerIDs.add(hostID);
        numPlayers = 1;
    }

    public Integer getID() {return gameID;}
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
    public Integer getHost() {return hostID;}
    public Integer getGameID() {return gameID;}
    public Boolean isPremiumGame() {return isPremium;}



}