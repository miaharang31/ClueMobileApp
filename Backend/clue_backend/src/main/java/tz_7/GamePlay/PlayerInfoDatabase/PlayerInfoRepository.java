package tz_7.GamePlay.PlayerInfoDatabase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tz_7.PlayerDatabase.Player;

@Repository
public interface PlayerInfoRepository extends JpaRepository<PlayerInfo, Integer> {
    PlayerInfo findByPlayer(Player player);
}
