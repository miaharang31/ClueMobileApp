package tz_7.GamePlay.PlayerInfoDatabase;

import jakarta.persistence.*;
import tz_7.GamePlay.GameStateDatabase.GameState;
import tz_7.PlayerDatabase.Player;

@Entity
@Table(name = "PlayerInfo")
public class PlayerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer ID;

    @OneToOne
    @JoinColumn(name = "player")
    private Player player;

    @OneToOne
    @JoinColumn(name = "character")
    private Character character;

    @OneToOne
    @JoinColumn(name = "gamestate")
    private GameState gamestate;

    private Boolean turn;
}
