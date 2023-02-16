package tz_7.UserDatabase;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Grace Brickey
 */

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
