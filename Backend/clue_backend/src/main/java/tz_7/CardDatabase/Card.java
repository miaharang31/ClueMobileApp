package tz_7.CardDatabase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import tz_7.GamePlay.GameStateDatabase.GameState;
import tz_7.GamePlay.PlayerInfoDatabase.PlayerInfo;

import java.util.Set;

@Entity
@Table
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardID")
    private Integer cardID;
    private String cardType;
    private String name;
    private String type; //weapon, room, person
    private String cardImage; 

    @ManyToOne
    @JoinColumn(name = "playerInfo")
    @JsonIgnore
    private PlayerInfo playerInfo;

    @ManyToOne
    @JoinColumn(name = "gameState")
    @JsonIgnore
    private GameState gameState;

    public Card() {}
    public Card(int cardID, String cardType, String name, String type, String cardImage) {
        this.cardID = cardID;
        this.cardType = cardType;
        this.name = name;
        this.type = type;
        this.cardImage = cardImage;
    }

    public int getCardID() {
        return cardID;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }
    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public GameState getGameState() {
        return gameState;
    }
}
