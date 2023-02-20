package tz_7.GamePlay.GameInfoDatabase;

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
@Table(name = "GameInfo")
public class GameInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameID")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer id;

    @Column(name = "maxPlayers")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer max;

    @Column(name = "numPlayers")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer num;
    @Column(name = "gameCode")
    @NotFound(action = NotFoundAction.IGNORE)
    private String game_code;

    @Column(name = "currentPlayerIDs")
    @NotFound(action = NotFoundAction.IGNORE)
    private ArrayList<Integer> playerIDs;

    @Column(name = "hostID")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer host;

    @Column(name = "isPremium")
    @NotFound(action = NotFoundAction.IGNORE)
    private Boolean is_premium;

    public GameInfo () {}

    public GameInfo(int id, int max, String gameCode, int hostCode, boolean is_premium) {
        this.id = id;
        this.max = max;
        this.game_code = gameCode;
        this.host = hostCode;
        this.is_premium = is_premium;

        playerIDs = new ArrayList<Integer>();
        playerIDs.add(hostCode);
        num = 1;
    }

    public Integer getID() {return id;}
    public ArrayList<Integer> getCurPlayerIDs() {return playerIDs;}
    public boolean canAddPlayer() {
        if(num+1 <= max) {return true;}
        return false;
    }
    public String getGameCode() {return game_code;}
    public Integer getHost() {return host;}
    public Integer getGameID() {return id;}
    public Boolean isPremiumGame() {return is_premium;}


}