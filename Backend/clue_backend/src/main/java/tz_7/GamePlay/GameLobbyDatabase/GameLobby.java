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
    @Column(name = "game_id", unique = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer game_id;

    @Column(name = "max_players")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer max_players;

    @Column(name = "numPlayers")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer num_players;
    @Column(name = "gameCode")
    @NotFound(action = NotFoundAction.IGNORE)
    private String game_code;

    @Column(name = "player_ids")
    @NotFound(action = NotFoundAction.IGNORE)
    private ArrayList<Integer> player_ids;

    @Column(name = "host_id", unique = true)
    @NotFound(action = NotFoundAction.IGNORE)
    public Integer host_id;

    @Column(name = "isPremium")
    @NotFound(action = NotFoundAction.IGNORE)
    private Boolean is_premium;

    public GameLobby() {}

    public GameLobby(Integer max_players, String game_code, Integer host_id, Boolean is_premium) {
        this.max_players = max_players;
        this.game_code = game_code;
        this.host_id = host_id;
        this.is_premium = is_premium;

        player_ids = new ArrayList<Integer>();
        player_ids.add(host_id);
        num_players = 1;
    }

    public Integer getID() {return game_id;}
    public ArrayList<Integer> getCurPlayerIDs() {return player_ids;}
    public boolean canAddPlayer() {
        if(num_players+1 <= max_players) {return true;}
        return false;
    }
    public String getGameCode() {return game_code;}
    public Integer getHost() {return host_id;}
    public Integer getGameID() {return game_id;}
    public Boolean isPremiumGame() {return is_premium;}



}