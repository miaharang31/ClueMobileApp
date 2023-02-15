package tz_7.UserDatabase;

import tz_7.UserDatabase.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByType(String type);
}
