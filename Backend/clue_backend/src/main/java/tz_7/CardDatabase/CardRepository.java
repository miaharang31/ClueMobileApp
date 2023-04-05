package tz_7.CardDatabase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tz_7.UserDatabase.Player;
@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByCardID(int cardID);
}
