package tz_7.CharacterDatabase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import tz_7.GamePlay.PlayerInfoDatabase.PlayerInfo;

@Entity
@Table
public class Character {
    @Id
    String name;
    String color; // use hex
    String photo; //link to photo
    String game; //the type of game

    @OneToOne
    @JoinColumn(name = "playerInfo")
    @JsonIgnore
    private PlayerInfo playerInfo;



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

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }
}
