package tz_7.GamePlay.GameStateDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tz_7.CardDatabase.Card;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;
import tz_7.GamePlay.GameLobbyDatabase.GameLobbyRepository;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    /**
     * Post mapping that creates a new Game State
     * @param state
     *  The json params for a game state
     * @return
     *  GameState Entity
     */
    @PostMapping(value = "game/new/{lobbyid}", consumes = "application/json")
    public GameState newState(@RequestBody GameState state, @PathVariable Integer lobbyid) {
        GameLobby lobby = gameLobbyRepository.findById(lobbyid).get();
        state.setGameLobby(lobby);
        state.setFinalCards();
        lobby.setGameState(state);
        repo.save(state);
        gameLobbyRepository.save(lobby);
        return state;
    }

    /**
     * Get mapping that gets all game states in session
     * @return
     *  A list of game state objects
     */
    @GetMapping(value = "game")
    public List<GameState> getAllLobbies() {
        return repo.findAll();
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
