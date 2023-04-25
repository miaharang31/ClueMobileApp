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

    /**
     * Sets the cards based on type for that game state
     * @param id
     *  ID of the game state
     * @param type
     *  Type of cards to add (i.e. w, s, r)
     * @return
     *  updated gamestate object
     */
    @PutMapping(value = "/game/{id}/setcards/{type}")
    public GameState addWeapons(@PathVariable Integer id, @PathVariable String type) {
        GameState state = repo.findById(id).get();
        Set<Card> cards = cardRepository.findByType(type);
        switch(type){
            case "w" :
                state.setWeapons(cards);
                break;
            case "s" :
                state.setSuspects(cards);
                break;
            case "r" :
                state.setRooms(cards);
                break;
        }

        Iterator<Card> tmp = cards.iterator();
        while(tmp.hasNext()) {
            tmp.next().addGameState(state);
        }
        repo.save(state);
        cardRepository.saveAll(cards);
        return state;
    }

    /**
     * Get mapping that gets all game states in session
     * @return
     *  A list of game state objects
     */
    @GetMapping("game")
    public ResponseEntity<List<GameState>> getAllStates() {
        return new ResponseEntity<List<GameState>>(repo.findAll(), HttpStatus.OK);
    }

    /**
     * Gets mapping the provides the caller with all the players in the game
     * @param id
     *  ID of the game state
     * @return
     *  Set of player objects
     */
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
    @GetMapping(value = "game/checkGuess/{id}", consumes = "application/json")
    public Boolean checkGuess (@RequestBody Set<Card> guess, @PathVariable Integer id) {
        GameState state = repo.getById(id);
        return state.checkFinalGuess(guess);
    }

    @GetMapping(value = "game/{id}/next")
    public Player getNextPlayer (@PathVariable Integer id) {
        Optional<GameState> state = repo.findById(id);
        return state.get().getNextPlayer();
    }

}
