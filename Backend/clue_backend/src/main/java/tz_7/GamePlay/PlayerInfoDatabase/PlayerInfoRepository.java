package tz_7.GamePlay.PlayerInfoDatabase;

import org.springframework.data.jpa.repository.JpaRepository;
import tz_7.PlayerDatabase.Player;

public interface PlayerInfoRepository extends JpaRepository<PlayerInfo, Integer> {
    PlayerInfo findByPlayer(Player player);
}
