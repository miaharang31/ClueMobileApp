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
    @PostMapping(value = "/getCardById", consumes = "application/json")
    public Card getCardById(@RequestBody Card card) {
        return repository.findByCardID(card.getCardID());
    }
    @PostMapping(value = "/createCard", consumes = "application/json")
    public Card createCard(@RequestBody Card card) {
        return repository.save(card);
    }
}
