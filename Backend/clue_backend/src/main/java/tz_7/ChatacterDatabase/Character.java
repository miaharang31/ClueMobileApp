package tz_7.ChatacterDatabase;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Character {
    @Id
    String name;
    String color; // use hex
    String photo; //link to photo
    String game; //the type of game

    public Character() {}
    public Character(String name, String color, String photo, String game) {
        this.name = name;
        this.color = color;
        this.photo = photo;
        this.game = game;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
