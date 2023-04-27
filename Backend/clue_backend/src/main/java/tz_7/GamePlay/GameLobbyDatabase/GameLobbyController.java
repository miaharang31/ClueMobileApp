package tz_7.GamePlay.GameLobbyDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Author: Mia Harang
 *  Rest Controller to control the GameLobby datatable
 *      Game lobby deals with the gathering of
 *      information to then turn into a game state
 */
@RestController
public class GameLobbyController {
    @Autowired
    GameLobbyRepository repo;
    @Autowired
    PlayerRepository playerRepo;

    /**
     * Post mapping to create a new lobby object
     *  Needs a json object if creating with http request
     * @param lobby
     *  The lobby object to save (json object)
     * @param hostid
     *  ID of the player that created the lobby
     * @return
     *  GameLobby object
     */
    @PostMapping(value = "/lobby/new/{hostid}", consumes = "application/json")
    public GameLobby newLobby(@RequestBody GameLobby lobby, @PathVariable Integer hostid) {
        Optional<Player> host = playerRepo.findById(hostid);
        lobby.setHost(host.get());
        host.get().setGameLobbyHost(lobby);
        repo.save(lobby);
        playerRepo.save(host.get());
        return lobby;
    }

    /**
     * Get Mapping to get all current lobby sessions
     * @return
     *  List of lobby sessions
     */
    @GetMapping("lobby")
    public ResponseEntity<List<GameLobby>> getAllLobbies() {
        return new ResponseEntity<List<GameLobby>>(repo.findAll(), HttpStatus.OK);
    }

    /**
     * Get Mapping to get all NON-PREMIUM lobby sessions
     * @return
     *  List of NON-PREMIUM lobby sessions
     */
    @GetMapping("lobby/notPremium")
    public ResponseEntity<List<GameLobby>> getAllNormalLobbies() {
        return new ResponseEntity<List<GameLobby>>(repo.findByIsPremium(false), HttpStatus.OK);
    }

    /**
     * Get Mapping to return a specific lobby object
     * based on the provided ID
     * @param id
     *  GameLobby ID
     * @return
     *  Requested GameLobby object
     */
    @GetMapping(value = "lobby/{id}", produces = "application/json")
    public GameLobby getLobbyById(@PathVariable int id) {
        return repo.findById(id).get();
    }

    /**
     * Put Mapping to have a player join an already deployed game
     * based on the game code provided
     *  (Cannot join if gamecode is not EXACTLY correct)
     * @param gamecode
     *  entered gamecode
     * @param playerID
     *  ID of player trying to join game
     * @return
     *  Entered lobby
     */
    @PutMapping(value = "lobby/join/{playerID}/code/{gamecode}")
    public GameLobby addPlayerByGameCode(@PathVariable String gamecode, @PathVariable Integer playerID) {
        Optional<GameLobby> tmp = repo.findByGameCode(gamecode);
        Optional<Player> player = playerRepo.findById(playerID);
        if(tmp.get().addPlayer(player.get())) {
            player.get().setGameLobby(tmp.get());
            repo.save(tmp.get());
            playerRepo.save(player.get());
        } else {
            //TODO: THROW ERROR OF SOME KIND
        }
        return tmp.get();
    }

    /**
     * Get Mapping to get all players in a lobby session
     * @param gameLobbyID
     *  GameLobby ID
     * @return
     *  Set of players (including the host)
     */
    @GetMapping(value = "lobby/players/{gameLobbyID}")
    public Set<Player> getAllPlayers(@PathVariable Integer gameLobbyID) {
        Optional<GameLobby> lobby = repo.findById(gameLobbyID);
        Set<Player> players = lobby.get().getPlayers();
        players.add(lobby.get().getHost());
        return players;
    }

    /**
     * Get Mapping to get players in a lobby session
     * @param gameLobbyID
     *  GameLobby ID
     * @return
     *  All players EXCEPT the host
     */
    @GetMapping(value = "lobby/nothost/{gameLobbyID}")
    public Set<Player> getNonHost(@PathVariable Integer gameLobbyID) {
        Optional<GameLobby> lobby = repo.findById(gameLobbyID);
        Set<Player> players = lobby.get().getPlayers();
        return players;
    }

    /**
     * Get Mapping to get the host of a lobby session
     * @param gameLobbyID
     *  GameLobby ID
     * @return
     *  host Player object
     */
    @GetMapping(value = "lobby/host/{gameLobbyID}")
    public Player getHost(@PathVariable Integer gameLobbyID) {
        Optional<GameLobby> lobby = repo.findById(gameLobbyID);
        return lobby.get().getHost();
    }

    /**
     * Delete Mapping to remove lobby session from database table
     * @param id
     *  GameLobby ID to delete
     */
    @DeleteMapping(value = "lobby/delete/{id}")
    public void deleteLobby(@PathVariable Integer id) {
        Optional<GameLobby> lobby = repo.findById(id);
        repo.delete(lobby.get());
    }
}
