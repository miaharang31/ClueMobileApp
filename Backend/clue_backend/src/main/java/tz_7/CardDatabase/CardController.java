package tz_7.CardDatabase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Tag(name = "CardController", description = "Grace Brickey - related to the Card entity. This is used for the cards in the game that the players will receive.")
@RestController
public class CardController {
    @Autowired
    private CardRepository repository;

    @Operation(summary = "Return every card in the database", description = "Return all using a get request")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping("/allCards")
    public List<Card> returnCards() {
        List<Card> list = repository.findAll();
        return list;
    }

    @Operation(summary = "Return the name of the player by the id", description = "Return player name using a post request given the id")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PostMapping(value = "/getName", consumes = "application/json")
    public String getName(@RequestBody Card card) {
        Card car = repository.findByCardID(card.getCardID());
        return car.getName();
    }

    @Operation(summary = "Return the card image to set the drawable", description = "Uses a post request with the card id in order to fetch the card image that is needed")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PostMapping(value = "/getCardImage", consumes = "application/json")
    public String getDescription(@RequestBody Card card) {
        Card car = repository.findByCardID(card.getCardID());
        return car.getCardImage();
    }
    @Operation(summary = "Return the type of card according to its id", description = "Return the type of card usinga post request with the card id. This is used when you need the type of card")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PostMapping(value = "/getType", consumes = "application/json")
    public String getType(@RequestBody Card card) {
        Card car = repository.findByCardID(card.getCardID());
        return car.getType();
    }
    @Operation(summary = "Returns the entire card based off of its id", description = "Uses a post request to return a card based off of the id")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PostMapping(value = "/getCardById", consumes = "application/json")
    public Card getCardById(@RequestBody Card card) {
        return repository.findByCardID(card.getCardID());
    }
    @Operation(summary = "Creates a new card", description = "Creates a new card using a post request. This is needed for the administer page")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PostMapping(value = "/createCard", consumes = "application/json")
    public Card createCard(@RequestBody Card card) {
        return repository.save(card);
    }

    @Operation(summary = "Returns every card that is a weapon", description = "Returns every card that is a weapon using a get request")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "/card/weapon")
    public Set<Card> getWeapons() {
        return repository.findByType("w");
    }

    @Operation(summary = "Returns every card that is a suspect", description = "Returns every card that is a suspect using a get request")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "/card/suspect")
    public Set<Card> getSuspects() {
        return repository.findByType("s");
    }

    @Operation(summary = "Returns every card that is a room", description = "Returns every card that is a room using a get request")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "/card/room")
    public Set<Card> getRooms() {
        return repository.findByType("r");
    }
}
