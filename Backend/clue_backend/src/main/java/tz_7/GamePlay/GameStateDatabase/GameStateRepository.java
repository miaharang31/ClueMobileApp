package tz_7.GamePlay.GameStateDatabase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tz_7.PlayerDatabase.Player;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Mia Harang
 */

@Repository
public interface GameStateRepository extends JpaRepository<GameState, Integer> {
    List<GameState> findByHostID(Integer hostID);

}
