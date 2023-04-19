package tz_7.GamePlay.GameStateDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tz_7.CardDatabase.Card;
import tz_7.CardDatabase.CardRepository;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;
import tz_7.GamePlay.GameLobbyDatabase.GameLobbyRepository;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;

import java.util.*;

/**
 * Author: Mia Harang
 * This is a controller class that deals with everything
 * and anything to do with the GameState table of our database
 */

@RestController
public class GameStateController {
    @Autowired
    GameStateRepository repo;
    @Autowired
    PlayerRepository playerRepository;
    private final Logger logger = LoggerFactory.getLogger(GameStateRepository.class);
    @Autowired
    private GameLobbyRepository gameLobbyRepository;

    @Autowired
    private CardRepository cardRepository;

    /**
     * Post mapping that creates a new Game State
     * @param state
     *  The json params for a game state
     * @return
     *  GameState Entity
     */
    @PostMapping(value = "game/new", consumes = "application/json")
    public GameState newState(@RequestBody GameState state) {
        Iterator<Player> players = state.getTurnOrder().iterator();
        while(players.hasNext()) {
            players.next().setGameState(state);
        }
        repo.save(state);
        playerRepository.saveAll(state.getTurnOrder());
        return state;
    }

    @PutMapping(value = "/game/{id}/setcards/weapons", consumes = "application/json")
    public GameState addWeapons(@PathVariable Integer id, @RequestBody Set<Card> weapons) {
        GameState state = repo.findById(id).get();
        state.setWeapons(weapons);
        Iterator<Card> cards = weapons.iterator();
        while(cards.hasNext()) {
            cards.next().addGameState(state);
        }
        repo.save(state);
        cardRepository.saveAll(weapons);
        return state;
    }

    @PutMapping(value = "/game/{id}/setcards/suspects", consumes = "application/json")
    public GameState addSuspects(@PathVariable Integer id, @RequestBody Set<Card> suspects) {
        GameState state = repo.findById(id).get();
        state.setSuspects(suspects);
        Iterator<Card> cards = suspects.iterator();
        while(cards.hasNext()) {
            cards.next().addGameState(state);
        }
        repo.save(state);
        cardRepository.saveAll(suspects);
        return state;
    }

    @PutMapping(value = "/game/{id}/setcards/rooms", consumes = "application/json")
    public GameState addRooms(@PathVariable Integer id, @RequestBody Set<Card> rooms) {
        GameState state = repo.findById(id).get();
        state.setRooms(rooms);
        Iterator<Card> cards = rooms.iterator();
        while(cards.hasNext()) {
            cards.next().addGameState(state);
        }
        repo.save(state);
        cardRepository.saveAll(rooms);
        return state;
    }

    /**
     * Get mapping that gets all game states in session
     * @return
     *  A list of game state objects
     */
    @GetMapping("game")
    public ResponseEntity<List<GameState>> getAllLobbies() {
        return new ResponseEntity<List<GameState>>(repo.findAll(), HttpStatus.OK);
    }

    @GetMapping("game/{id}/players")
    public Set<Player> getPlayers(@PathVariable Integer id) {
        GameState state = repo.findById(id).get();
        return state.getTurnOrder();
    }


    /**
     * Get mapping to verify if the user's final guess is correct
     * @param guess
     *  Array of card ids
     * @return
     *  true - if all the ids match
     *  false - if the ids don't match
     */
//    @GetMapping(value = "game/checkGuess/{id}", consumes = "application/json")
//    public Boolean checkGuess (@RequestBody Set<Card> guess, @PathVariable Integer id) {
//        GameState state = repo.getById(id);
//        return state.checkFinalGuess(guess);
//    }

//    @GetMapping(value = "game/{id}/next")
//    public Player getNextPlayer (@PathVariable Integer id) {
//        Optional<GameState> state = repo.findById(id);
//        return state.get().getNextPlayer();
//    }

}
