package tz_7.GamePlay.PlayerInfoDatabase;

import jakarta.persistence.*;
import tz_7.CardDatabase.Card;
import tz_7.PlayerDatabase.Player;
import tz_7.RoleDatabase.Role;

import java.util.HashSet;
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
        @JoinColumn(name = "role")
    //    Selected character to play with (two players in the same
    //    gamestate cannot have the same character)
        private Role role;

        @OneToMany(mappedBy = "playerInfo")
        private Set<Card> cardHand;

    public PlayerInfo() {
        cardHand = new HashSet<>();
    }

    public PlayerInfo(boolean turn) {
        this.turn = turn;
        cardHand = new HashSet<>();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setRoll(Integer roll) {
        this.roll = roll;
    }
    public void changeTurn() {
        if(turn) {
            turn = false;
        } else {turn = true;}
    }
    public void setCards(Set<Card> cards) {
//        TODO: Set the cards
    }

    /**
     * All the get arguments for every variable
     * @return
     *  the variable that is needed
     */
    public Integer getID() {return ID;}
    public Boolean getTurn() {return turn;}
    public Player getPlayer() {return player;}
    public Role getRole() {return role;}
    public Integer getRoll() {return roll;}
}
