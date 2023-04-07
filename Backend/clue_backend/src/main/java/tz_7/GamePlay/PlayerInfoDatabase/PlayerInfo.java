package tz_7.GamePlay.PlayerInfoDatabase;

import jakarta.persistence.*;
import tz_7.CardDatabase.Card;
import tz_7.GamePlay.GameStateDatabase.GameState;
import tz_7.PlayerDatabase.Player;
import tz_7.CharacterDatabase.Character;

import java.util.Set;

/**
 * Author: Mia Harang
 * Database that stores the game information for each player
 */

@Entity
@Table(name = "PlayerInfo")
public class PlayerInfo {
    /**
     * JPA Database Generic Columns
     */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true)
    //    ID for the player info
        private Integer ID;

        private Integer roll;

        private Boolean turn;

    /**
     * JPA Relationships
     */
        @OneToOne
        @JoinColumn(name = "player")
    //    Player that the info is about
        private Player player;

        @OneToOne
        @JoinColumn(name = "character")
    //    Selected character to play with (two players in the same
    //    gamestate cannot have the same character)
        private Character character;

        @OneToOne
        @JoinColumn(name = "gamestate")
    //    The game state that they are in
        private GameState gamestate;

        @OneToMany(mappedBy = "playerInfo")
        private Set<Card> cardHand;

    public PlayerInfo() {

    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setCharacter(Character character) {
        this.character = character;
    }
    public void setGamestate(GameState gamestate) {
        this.gamestate = gamestate;
    }
    public void setRoll(Integer roll) {
        this.roll = roll;
    }
    public void changeTurn() {
        if(turn) {
            turn = false;
        } else {turn = true;}
    }

    /**
     * All the get arguments for every variable
     * @return
     *  the variable that is needed
     */
    public Integer getID() {return ID;}
    public Boolean getTurn() {return turn;}
    public Player getPlayer() {return player;}
    public Character getCharacter() {return character;}
    public GameState getGamestate() {return gamestate;}
    public Integer getRoll() {return roll;}
}