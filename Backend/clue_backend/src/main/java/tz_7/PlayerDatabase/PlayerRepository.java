package tz_7.PlayerDatabase;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Grace Brickey
 */

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Optional<Player> findByUsernameAndPassword(String username, String password);

    Optional<Player> findByUsername(String username);
}
