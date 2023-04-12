package tz_7.ChatacterDatabase;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {
    List<Character> findAllByGame(String game);
}
