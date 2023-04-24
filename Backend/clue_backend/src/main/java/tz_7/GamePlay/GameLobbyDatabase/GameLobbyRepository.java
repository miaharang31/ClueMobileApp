package tz_7.GamePlay.GameLobbyDatabase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tz_7.PlayerDatabase.Player;

import java.util.List;
import java.util.Optional;

/**
 * @author Mia Harang
 */

@Repository
public interface GameLobbyRepository extends JpaRepository<GameLobby, Integer> {
    GameLobby findByHost(Player host);
    Optional<GameLobby> findByGameCode(String gameCode);

    @Override
    List<GameLobby> findAll();

    List<GameLobby> findByIsPremium(Boolean isPremium);

    Player findHostByID(int lobbyid);
}