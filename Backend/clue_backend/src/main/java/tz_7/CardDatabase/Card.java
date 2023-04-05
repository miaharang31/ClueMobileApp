package tz_7.CardDatabase;

import jakarta.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardID")
    private int cardID;
    private String name;
    private String description;
    private String type; //weapon, room, person
    private String color; //hex

    public Card() {}
    public Card(int cardID, String name, String description, String type, String color) {
        this.cardID = cardID;
        this.name = name;
        this.description = description;
        this.type = type;
        this.color = color;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
