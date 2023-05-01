package tz_7.CardDatabase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByCardID(int cardID);

    Set<Card> findByType(String type);

    Set<Card> getCardsByCardType(String p);
}
