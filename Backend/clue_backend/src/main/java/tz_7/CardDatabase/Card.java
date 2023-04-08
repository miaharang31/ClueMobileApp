package tz_7.CardDatabase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import tz_7.GamePlay.PlayerInfoDatabase.PlayerInfo;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardID")
    private int cardID;
    private String name;
    private String type; //weapon, room, person
    private String cardImage; 

    @ManyToOne
    @JoinColumn(name = "playerInfo")
    @JsonIgnore
    private PlayerInfo playerInfo;

    public Card() {}
    public Card(int cardID, String name, String type, String cardImage) {
        this.cardID = cardID;
        this.name = name;
        this.type = type;
        this.cardImage = cardImage;
    }

    public int getCardID() {
        return cardID;
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
}
