package tz_7.GamePlay.GameStateDatabase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tz_7.CardDatabase.Card;
import tz_7.CardDatabase.CardRepository;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;
import tz_7.GamePlay.PlayerInfoDatabase.PlayerInfo;
import tz_7.GamePlay.PlayerInfoDatabase.PlayerInfoRepository;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;

import java.util.*;

/**
 * Author: Mia Harang
 * This is a controller class that deals with everything
 * and anything to do with the GameState table of our database
 *  GameState deals with the information of different game sessions
 *      i.e. instances of games
 */
@Tag(name = "GameStateController", description = "Mia Harang - related to the GameState entity. This is used for the game and how to know whose turn it is and the kind of game we are playing")
@RestController
public class GameStateController {
    @Autowired
    GameStateRepository repo;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    PlayerInfoRepository playerInfoRepository;
    @Autowired
    CardRepository cardRepository;

    /**
     * Post mapping that creates a new Game State
     * @param state
     *  The json params for a game state
     * @return
     *  GameState Entity
     */
    @Operation(summary = "Creates a new game state", description = "Creates a new game using a post request")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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
     * Creates a game based on the lobby
     * @param lobby
     *  Lobby to create from (JSON FORMAT)
     */
    @PostMapping(value = "game/lobby/new", consumes = "application/json")
    public GameState newSocketState(@RequestBody GameLobby lobby) {
        GameState state = repo.findByHostID(lobby.getHost().getId());
        if(state != null) {
            deleteGame(state.getID());
        }

        state = new GameState(lobby);
        state = newState(state);
        state = addCards(state.getID(), "w");
        state = addCards(state.getID(), "s");
        state = addCards(state.getID(), "r");
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
    @Operation(summary = "Places the cards in the game", description = "Using a put request it places the cards we are playing with in the game")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PutMapping(value = "/game/{id}/setcards/{type}")
    public GameState addCards(@PathVariable Integer id, @PathVariable String type) {
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

    @PutMapping(value = "game/{id}/distributeCards")
    public GameState distributeCards(@PathVariable Integer id) {
        GameState state = repo.findById(id).get();
        Set<Player> players = state.getTurnOrder();
        Set<PlayerInfo> infos = new HashSet<>();
        Iterator<Player> tmp = players.iterator();
        while (tmp.hasNext()) {
            infos.add(playerInfoRepository.findByPlayer(tmp.next()));
        }

//  TODO: Make iterator (for infos) and get the weapons, rooms and suspects
//        Iterate through the infos and and set the cards for each player

        repo.save(state);
        playerInfoRepository.saveAll(//TODO: put info iterator here);
        return state;
    }

    /**
     * PREPARING FOR STATE DELETION
     *  Put mapping that removes the cards from the game state
     *  by type
     * @param id
     *  GameState ID
     * @param type
     *  type of card (i.e. w, s, r)
     * @return
     *  new GameState object
     */
    @PutMapping(value = "game/{id}/removeCards/{type}")
    public GameState removeCards(@PathVariable Integer id, @PathVariable String type) {
        GameState state = repo.findById(id).get();
        Set<Card> cards = cardRepository.findByType(type);
        switch(type){
            case "w" :
                state.setWeapons(null);
                break;
            case "s" :
                state.setSuspects(null);
                break;
            case "r" :
                state.setRooms(null);
                break;
        }

        Iterator<Card> tmp = cards.iterator();
        while(tmp.hasNext()) {
            tmp.next().removeGameState(state);
        }
        repo.save(state);
        cardRepository.saveAll(cards);
        return state;
    }

    /**
     * PREPARING FOR STATE DELETION
     *  Removes the players from the game state
     *  and sets those player's game state to null
     * @param id
     *  GameState ID
     * @return
     *  update state object
     */
    @PutMapping(value = "game/{id}/removePlayers")
    public GameState removePlayers(@PathVariable Integer id) {
        GameState state = repo.findById(id).get();

        Iterator<Player> players = state.getTurnOrder().iterator();
        while(players.hasNext()) {
            players.next().setGameState(null);
        }
        state.removePlayers();

        repo.save(state);
        playerRepository.saveAll(state.getTurnOrder());
        return state;
    }

    /**
     * Get mapping that gets all game states in session
     * @return
     *  A list of game state objects
     */
//    TODO: Not Completely Working
    @Operation(summary = "Returns all the games", description = "Using a get request this returns all the current games being played and the games that have been played")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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
    @Operation(summary = "Returns the players that are in the game", description = "Using a get request this returns the players that are in a game given the game id")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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
    @Operation(summary = "Checks if the given cards are the solution", description = "Using a get request, we check if the cards that the player guesses are the correct cards to win the game")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "game/checkGuess/{id}", consumes = "application/json")
    public Boolean checkGuess (@RequestBody Set<Card> guess, @PathVariable Integer id) {
        GameState state = repo.getById(id);
        return state.checkFinalGuess(guess);
    }
    /**
     * Get mapping to get a state by ID
     * @return
     *  true - if all the ids match
     *  false - if the ids don't match
     */
    @GetMapping(value = "game/{id}")
    public GameState getGameByID (@PathVariable Integer id) {
        GameState state = repo.getById(id);
        return state;
    }

    /**
     * Get mapping to get the next player in rotation
     * @param id
     *  GameState ID
     * @return
     *  Player object
     */
    @Operation(summary = "Returns the next player", description = "Using a get request, this returns the next player from the id of the current player")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "game/{id}/next")
    public Player getNextPlayer (@PathVariable Integer id) {
        Optional<GameState> state = repo.findById(id);
        return state.get().getNextPlayer();
    }

    /**
     * Delete Mapping that removes all relationships then
     * deletes the object itself
     * @param id
     *  GameState ID
     */
    @DeleteMapping(value = "game/{id}/delete")
    public void deleteGame(@PathVariable Integer id) {
        GameState state = repo.findById(id).get();
        state = removeCards(state.getID(), "w");
        state = removeCards(state.getID(), "s");
        state = removeCards(state.getID(), "r");
        state = removePlayers(state.getID());
        repo.delete(state);
    }

}
