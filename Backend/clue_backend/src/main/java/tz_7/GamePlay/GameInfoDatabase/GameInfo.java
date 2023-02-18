package tz_7.GamePlay.GameInfoDatabase;

/**
 * Table in DB for all open games
 * Includes:
 *  Game type (ie. Premium or Normal)
 *
 */

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "GameInfo")
public class GameInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer id;

    @Column(name = "score_val")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer val;

    public GameInfo () {}

    public GameInfo(int id, int val) {
        this.id = id;
        this.val = val;
    }

    public Integer getUserID() {return id;}

    public Integer getScore() {return val;}

}