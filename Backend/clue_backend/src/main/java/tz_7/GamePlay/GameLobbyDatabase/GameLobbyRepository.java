package tz_7.GamePlay.GameLobbyDatabase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mia Harang
 */

@Repository
public interface GameLobbyRepository extends JpaRepository<GameLobby, Integer> {

}