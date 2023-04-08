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

    /**
     * Post mapping that creates a new Game State
     * @param state
     *  The json params for a game state
     * @return
     *  GameState Entity
     */
    @PostMapping(value = "game/new", consumes = "application/json")
    public GameState newState(@RequestBody GameState state) {
//        Card[] weapons = state.getWeapons().toArray(new Card[0]);
//        Card[] suspects = state.getSuspects().toArray(new Card[0]);
//        Card[] rooms = state.getRooms().toArray(new Card[0]);
//        System.out.println("Weapons size: " + state.getWeapons().size());
//        System.out.println("Suspects size: " + state.getSuspects().size());
//        System.out.println("Rooms size: " + state.getRooms().size());
        System.out.println("Players size: " + state.getTurnOrder().size());

        Iterator<Player> players = state.getTurnOrder().iterator();
        while(players.hasNext()) {
            players.next().setGameState(state);
        }
//        for(int i = 0; i < players.length; i++) {
//            System.out.println(players[i].getFirstname());
//            players[i].setGameState(state);
//        }
        repo.save(state);
        playerRepository.saveAll(state.getTurnOrder());
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
