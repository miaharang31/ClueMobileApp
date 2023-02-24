package tz_7.ScoreDatabase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tz_7.ScoreDatabase.Score;

/**
 * @author Mia Harang
 */

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {

}
