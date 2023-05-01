package tz_7.GamePlay.GameLobbyDatabase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "GameLobbyController", description = "Mia Harang - related to the GameLobby database. This is used when a player creates or joins a lobby.")
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
    private final Logger logger = LoggerFactory.getLogger(GameLobbyRepository.class);

    //TODO: add the new lobby using a lobby request body
    @Operation(summary = "Returns the GameLobby using the host id", description = "Using a post request a game lobby is returned from the id of the host")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PostMapping(value = "/lobby/new/{hostid}", consumes = "application/json")
    public GameLobby newLobby(@RequestBody GameLobby lobby, @PathVariable Integer hostid) {
        Player host = playerRepo.findById(hostid).get();
        GameLobby tmp = repo.findByHost(host);

//        If there is a lobby created by that player, delete it
//        then create a new one
        if(host.getGameLobbyHost() != null) {
            deleteLobby(host.getGameLobbyHost().getID());
        }

        lobby.setHost(host);
        host.setGameLobbyHost(lobby);

        repo.save(lobby);
        playerRepo.save(host);
        return lobby;
    }

    /**
     * Get Mapping to get all current lobby sessions
     * @return
     *  List of lobby sessions
     */
    @Operation(summary = "Return every lobby in the database", description = "Return all game lobbies using a get request")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping("lobby")
    public ResponseEntity<List<GameLobby>> getAllLobbies() {
        return new ResponseEntity<List<GameLobby>>(repo.findAll(), HttpStatus.OK);
    }

    /**
     * Get Mapping to get all NON-PREMIUM lobby sessions
     * @return
     *  List of NON-PREMIUM lobby sessions
     */
    @Operation(summary = "Return every basic lobby in the database", description = "Return all basic game lobbies using a get request")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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
    @Operation(summary = "Returns the lobby from the lobby id", description = "Using a get request, a lobby is retrieved based on the lobby id given")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "lobby/{id}", produces = "application/json")
    public GameLobby getLobbyById(@PathVariable int id) {
        return repo.findById(id).get();
    }


    @GetMapping(value = "lobby/find/host/{id}", produces = "application/json")
    public GameLobby getLobbyByHost(@PathVariable int id) {
        Player host = playerRepo.findById(id).get();
        return repo.findByHost(host);
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
    @Operation(summary = "Places a player in a given lobby", description = "Using a put request a player is placed in a given lobby through their given id")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PutMapping(value = "lobby/join/{playerID}/code/{gamecode}")
    public GameLobby addPlayerByGameCode(@PathVariable String gamecode, @PathVariable Integer playerID) {
    //        System.out.println(lobby.getGameCode());
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
     * PREPARING FOR DELETION
     *  Removes a single player from the lobby
     * @param playerID
     *  ID of player to remove
     * @param lobbyID
     *  ID of lobby
     * @return
     *  Updated lobby object
     */
    @PutMapping(value = "lobby/remove/{playerID}/lobby/{lobbyID}")
    public GameLobby removePlayer(@PathVariable Integer playerID, @PathVariable Integer lobbyID) {
        GameLobby lobby = repo.findById(lobbyID).get();
        Player player = playerRepo.findById(playerID).get();

        if(lobby.getHost() == player) {
            lobby.setHost(null);
            player.setGameLobbyHost(null);
        } else {
            lobby.removePlayer(player);
            player.setGameLobby(null);
        }

        repo.save(lobby);
        playerRepo.save(player);

        return lobby;
    }

    /**
     * Get Mapping to get all players in a lobby session
     * @param gameLobbyID
     *  GameLobby ID
     * @return
     *  Set of players (including the host)
     */
    @Operation(summary = "Returns all the players in a given lobby", description = "Using a get request all the players in a lobby are returned given the lobby id")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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
    @Operation(summary = "Returns all the players in a given lobby not including the host", description = "Using a get request all the players in a lobby not including the host are returned given the lobby id")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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
    @Operation(summary = "Returns the host in a given lobby", description = "Using a get request the host in a given lobby is returned given the lobby id")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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
    @Operation(summary = "Deletes a given lobby", description = "Using a delete request the given lobby id finds the lobby and it is deleted")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @DeleteMapping(value = "lobby/delete/{id}")
    public void deleteLobby(@PathVariable Integer id) {
        System.out.println(id);
        GameLobby lobby = repo.findById(id).get();
        lobby = removePlayer(lobby.getHost().getId(), lobby.getID());
        Set<Player> players = lobby.getPlayers();
        for (Player player : lobby.getPlayers()) {
            lobby = removePlayer(player.getId(), lobby.getID());
        }
        repo.delete(lobby);
        playerRepo.saveAll(players);
    }
}
