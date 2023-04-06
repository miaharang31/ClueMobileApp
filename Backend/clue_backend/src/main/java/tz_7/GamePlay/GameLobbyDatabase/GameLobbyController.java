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

    @PostMapping(value = "/lobby/new", consumes = "application/json")
    public GameLobby newLobby(@RequestBody GameLobby lobby) {
        return repo.save(lobby);
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
    public ResponseEntity<List<GameLobby>> getLobbyByHost(@PathVariable("id") int hostID) {
        return new ResponseEntity<List<GameLobby>>(repo.findByHostID(hostID), HttpStatus.OK);
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
        return lobby.get().getPlayers();
    }
}
