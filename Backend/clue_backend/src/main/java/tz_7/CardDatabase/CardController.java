package tz_7.CardDatabase;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class CardController {
    @Autowired
    private CardRepository repository;
    @GetMapping("/allCards")
    public List<Card> returnCards() {
        List<Card> list = repository.findAll();
        return list;
    }
    @PostMapping(value = "/getName", consumes = "application/json")
    public String getName(@RequestBody Card card) {
        Card car = repository.findByCardID(card.getCardID());
        return car.getName();
    }
    @PostMapping(value = "/getCardImage", consumes = "application/json")
    public String getDescription(@RequestBody Card card) {
        Card car = repository.findByCardID(card.getCardID());
        return car.getCardImage();
    }
    @PostMapping(value = "/getType", consumes = "application/json")
    public String getType(@RequestBody Card card) {
        Card car = repository.findByCardID(card.getCardID());
        return car.getType();
    }
    @PostMapping(value = "/createCard", consumes = "application/json")
    public Card createCard(@RequestBody Card card) {
        return repository.save(card);
    }

    @GetMapping(value = "/card/weapon")
    public Set<Card> getWeapons() {
        return repository.findByType("w");
    }

    @GetMapping(value = "/card/suspect")
    public Set<Card> getSuspects() {
        return repository.findByType("s");
    }

    @GetMapping(value = "/card/room")
    public Set<Card> getRooms() {
        return repository.findByType("r");
    }
}
