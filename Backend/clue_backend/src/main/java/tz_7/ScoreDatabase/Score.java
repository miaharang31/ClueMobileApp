package tz_7.ScoreDatabase;


import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer id;

    @Column(name = "score_val")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer val;

    public Score () {}

    public Score(int id, int val) {
        this.id = id;
        this.val = val;
    }

    public Integer getUserID() {return id;}

    public Integer getScore() {return val;}

}