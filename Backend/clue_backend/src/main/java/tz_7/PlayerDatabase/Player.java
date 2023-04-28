package tz_7.PlayerDatabase;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;
import tz_7.GamePlay.GameStateDatabase.GameState;
import tz_7.GamePlay.PlayerInfoDatabase.PlayerInfo;

/**
 * @author Grace Brickey
 *  Stores the forever information of a user
 *  Used to log in the user and identify them
 */

@Entity
@Table
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    //@NotFound(action = NotFoundAction.IGNORE)
    private int id;
    @Column(name = "firstname")
    //@NotFound(action = NotFoundAction.IGNORE)
    private String firstname;
    @Column(name = "lastname")
    //@NotFound(action = NotFoundAction.IGNORE)
    private String lastname;
    @Column(name = "username")
    //@NotFound(action = NotFoundAction.IGNORE)
    private String username;
    @Column(name = "email")
    //@NotFound(action = NotFoundAction.IGNORE)
    private String email;
    @Column(name = "password")
    //@NotFound(action = NotFoundAction.IGNORE)
    private String password;
    @Column(name = "type")
    //@NotFound(action = NotFoundAction.IGNORE)
    private String type; //use type can be a for administer, b for basic
                            // and p for premium

    @ManyToOne
    @JoinColumn(name = "gameLobby")
    @JsonIgnore
    private GameLobby gameLobby;

    @OneToOne
    @JoinColumn(name = "gameLobbyHost")
    @JsonIgnore
    private GameLobby gameLobbyHost;

    @ManyToOne
    @JoinColumn(name = "gameState")
    @JsonBackReference
    private GameState gameState;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "playerInfo")
    @JsonIgnore
    private PlayerInfo playerInfo;

    public Player() {}

    public Player(String firstname, String lastname, String email, String username, String password, String type) {
//        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GameLobby getGameLobby () {return gameLobby;}

    public void setGameLobby(GameLobby lobby) {
        gameLobby = lobby;
    }
    public void setGameLobbyHost(GameLobby lobby) {
        gameLobbyHost = lobby;
    }

    public GameLobby getGameLobbyHost() {return gameLobbyHost;}

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }
}
