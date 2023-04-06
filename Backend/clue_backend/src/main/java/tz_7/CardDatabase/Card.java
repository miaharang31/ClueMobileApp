package tz_7.CardDatabase;

import jakarta.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardID")
    private int cardID;
    private String name;
    private String type; //weapon, room, person
    private String cardImage; //hex

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
}
