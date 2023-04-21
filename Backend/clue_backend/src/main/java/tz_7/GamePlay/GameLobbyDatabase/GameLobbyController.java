package tz_7.GamePlay.GameLobbyDatabase;

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

@RestController
public class GameLobbyController {
    @Autowired
    GameLobbyRepository repo;
    @Autowired
    PlayerRepository playerRepo;

    private final Logger logger = LoggerFactory.getLogger(GameLobbyRepository.class);

    @PostMapping(value = "/lobby/new/{hostid}", consumes = "application/json")
    public GameLobby newLobby(@RequestBody GameLobby lobby, @PathVariable Integer hostid) {
        Optional<Player> host = playerRepo.findById(hostid);
        lobby.setHost(host.get());
        host.get().setGameLobbyHost(lobby);
        repo.save(lobby);
        playerRepo.save(host.get());
        return lobby;
    }

    @GetMapping("lobby")
    public ResponseEntity<List<GameLobby>> getAllLobbies() {
        return new ResponseEntity<List<GameLobby>>(repo.findAll(), HttpStatus.OK);
    }

    @GetMapping("lobby/notPremium")
    public ResponseEntity<List<GameLobby>> getAllNormalLobbies() {
        return new ResponseEntity<List<GameLobby>>(repo.findByIsPremium(false), HttpStatus.OK);
    }

    @GetMapping(value = "lobby/{id}", produces = "application/json")
    public GameLobby getLobbyById(@PathVariable int id) {
        return repo.findById(id).get();
    }


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

    @GetMapping(value = "lobby/players/{gameLobbyID}")
    public Set<Player> getAllPlayers(@PathVariable Integer gameLobbyID) {
        Optional<GameLobby> lobby = repo.findById(gameLobbyID);
        Set<Player> players = lobby.get().getPlayers();
        players.add(lobby.get().getHost());
        return players;
    }

    @GetMapping(value = "lobby/nothost/{gameLobbyID}")
    public Set<Player> getNonHost(@PathVariable Integer gameLobbyID) {
        Optional<GameLobby> lobby = repo.findById(gameLobbyID);
        Set<Player> players = lobby.get().getPlayers();
        return players;
    }

    @GetMapping(value = "lobby/host/{gameLobbyID}")
    public Player getHost(@PathVariable Integer gameLobbyID) {
        Optional<GameLobby> lobby = repo.findById(gameLobbyID);
        return lobby.get().getHost();
    }

    @DeleteMapping(value = "lobby/delete/{id}")
    public void deleteLobby(@PathVariable Integer id) {
        Optional<GameLobby> lobby = repo.findById(id);
        repo.delete(lobby.get());
    }
}
