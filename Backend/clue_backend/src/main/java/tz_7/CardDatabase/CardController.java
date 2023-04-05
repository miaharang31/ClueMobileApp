package tz_7.CardDatabase;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping(value = "/getDescription", consumes = "application/json")
    public String getDescription(@RequestBody Card card) {
        Card car = repository.findByCardID(card.getCardID());
        return car.getDescription();
    }
    @PostMapping(value = "/getColor", consumes = "application/json")
    public String getColor(@RequestBody Card card) {
        Card car = repository.findByCardID(card.getCardID());
        return car.getColor();
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
}
