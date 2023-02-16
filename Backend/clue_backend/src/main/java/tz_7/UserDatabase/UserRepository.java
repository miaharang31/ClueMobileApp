package tz_7.UserDatabase;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Grace Brickey
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
