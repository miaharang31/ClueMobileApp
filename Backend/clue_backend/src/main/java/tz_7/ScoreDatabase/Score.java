package tz_7.ScoreDatabase;


import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "score")
public class Score {
    @Id
    @Column(name = "user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer user_id;

    @Column(name = "score_score")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer score;

    public Score () {}

    public Score(int user_id, int score) {
        this.user_id = user_id;
        this.score = score;
    }

    public Integer getUserID() {return user_id;}

    public Integer getScore() {return score;}

    public void setID(int user_id) {this.user_id = user_id;}

    public void addWin() {score += 1;}
}