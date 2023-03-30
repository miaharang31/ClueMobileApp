package tz_7.GamePlay.GameStateDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;

import java.util.List;

/**
 * Author: Mia Harang
 * This is a controller class that deals with everything
 * and anything to do with the GameState table of our database
 */

@RestController
public class GameStateController {
    @Autowired
    GameStateRepository stateRepository;
    private final Logger logger = LoggerFactory.getLogger(GameStateRepository.class);

    /**
     * Post mapping that creates a new Game State
     * @param state
     *  The json params for a game state
     * @return
     *  GameState Entity
     */
    @PostMapping(value = "game/new", consumes = "application/json")
    public GameState newState(@RequestBody GameState state) {
        state.setFinalCardIDs();
        return stateRepository.save(state);
    }

    /**
     * Get mapping that gets all game states in session
     * @return
     *  A list of game state objects
     */
    @GetMapping(value = "game")
    public ResponseEntity<List<GameState>> getAllLobbies() {
        return new ResponseEntity<List<GameState>>(stateRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Get mapping to verify if the user's final guess is correct
     * @param guess
     *  Array of card ids
     * @return
     *  true - if all the ids match
     *  false - if the ids don't match
     */
    @GetMapping(value = "game/checkGuess", consumes = "application/json")
    public Boolean checkGuess (@RequestBody Integer[] guess, @RequestBody Integer gameID) {
        GameState state = stateRepository.getById(gameID);
        return state.checkFinalGuess(guess);
    }

}
