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

import java.io.FileReader;
import java.util.List;
import java.util.Objects;

@RestController
public class GameLobbyController {
    @Autowired
    GameLobbyRepository repo;

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

    @PutMapping(value = "lobby/join/{userid}", consumes = "application/json")
    public GameLobby addPlayerByGameCode(@RequestBody GameLobby lobby, @PathVariable("userid") Integer playerID) {
//        System.out.println(lobby.getGameCode());
        List<GameLobby> tmp = repo.findByGameCode(lobby.getGameCode());
        if(tmp.get(0).addPlayer(playerID)) {
            repo.save(tmp.get(0));
        } else {
            //TODO: THROW ERROR OF SOME KIND
        }
        return tmp.get(0);
    }
}
