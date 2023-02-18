package tz_7.ScoreDatabase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mia Harang
 */

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {

}
