package tz_7.GamePlay.GameLobbyDatabase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tz_7.GamePlay.GameLobbyDatabase.GameLobby;

import java.util.List;

/**
 * @author Mia Harang
 */

@Repository
public interface GameLobbyRepository extends JpaRepository<GameLobby, Integer> {
    List<GameLobby> findByHostID(Integer hostID);
    List<GameLobby> findByGameCode(String gameCode);

    @Override
    List<GameLobby> findAll();

    List<GameLobby> findByIsPremium(Boolean isPremium);
}