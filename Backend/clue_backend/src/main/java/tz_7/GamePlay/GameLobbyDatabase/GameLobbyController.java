package tz_7.GamePlay.GameLobbyDatabase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;

import javax.swing.text.html.Option;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
@Tag(name = "GameLobbyController", description = "Mia Harang - related to the GameLobby database. This is used when a player creates or joins a lobby.")
@RestController
public class GameLobbyController {
    @Autowired
    GameLobbyRepository repo;
    @Autowired
    PlayerRepository playerRepo;

    private final Logger logger = LoggerFactory.getLogger(GameLobbyRepository.class);

    @Operation(summary = "Returns the GameLobby using the host id", description = "Using a post request a game lobby is returned from the id of the host")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PostMapping(value = "/lobby/new/{hostid}", consumes = "application/json")
    public GameLobby newLobby(@RequestBody GameLobby lobby, @PathVariable Integer hostid) {
        Optional<Player> host = playerRepo.findById(hostid);
        lobby.setHost(host.get());
        host.get().setGameLobbyHost(lobby);
        repo.save(lobby);
        playerRepo.save(host.get());
        return lobby;
    }

    @Operation(summary = "Return every lobby in the database", description = "Return all game lobbies using a get request")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping("lobby")
    public ResponseEntity<List<GameLobby>> getAllLobbies() {
        return new ResponseEntity<List<GameLobby>>(repo.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Return every basic lobby in the database", description = "Return all basic game lobbies using a get request")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping("lobby/notPremium")
    public ResponseEntity<List<GameLobby>> getAllNormalLobbies() {
        return new ResponseEntity<List<GameLobby>>(repo.findByIsPremium(false), HttpStatus.OK);
    }

    @Operation(summary = "Returns the lobby from the lobby id", description = "Using a get request, a lobby is retrieved based on the lobby id given")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "lobby/{id}", produces = "application/json")
    public GameLobby getLobbyById(@PathVariable int id) {
        return repo.findById(id).get();
    }


    @Operation(summary = "Places a player in a given lobby", description = "Using a put request a player is placed in a given lobby through their given id")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PutMapping(value = "lobby/join/{playerID}", consumes = "application/json")
    public GameLobby addPlayerByGameCode(@RequestBody GameLobby lobby, @PathVariable Integer playerID) {
//        System.out.println(lobby.getGameCode());
        Optional<GameLobby> tmp = repo.findByGameCode(lobby.getGameCode());
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

    @Operation(summary = "Deletes a given lobby", description = "Using a delete request the given lobby id finds the lobby and it is deleted")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @DeleteMapping(value = "lobby/delete/{id}")
    public void deleteLobby(@PathVariable Integer id) {
        Optional<GameLobby> lobby = repo.findById(id);
        repo.delete(lobby.get());
    }
}
