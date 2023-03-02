package tz_7.GamePlay.GameLobbyDatabase;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class GameLobbyController {
    @Autowired
    GameLobbyRepository gameLobbyRepository;

    private final Logger logger = LoggerFactory.getLogger(GameLobbyRepository.class);

    @PostMapping(value = "/lobby/new", consumes = "application/json")
    public GameLobby newLobby(@RequestBody GameLobby lobby) {
        return gameLobbyRepository.save(lobby);
    }

    @GetMapping("lobby")
    public ResponseEntity<List<GameLobby>> getAllLobbies() {
        return new ResponseEntity<List<GameLobby>>(gameLobbyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("lobby/notPremium")
    public ResponseEntity<List<GameLobby>> getAllNormalLobbies() {
        return new ResponseEntity<List<GameLobby>>(gameLobbyRepository.findByIsPremium(false), HttpStatus.OK);
    }

    @GetMapping("lobby/{id}")
    public ResponseEntity<List<GameLobby>> getLobbyByHost(@PathVariable("hostID") int hostID) {
        return new ResponseEntity<List<GameLobby>>(gameLobbyRepository.findByHostID(hostID), HttpStatus.OK);
    }

    @PutMapping("lobby/join/{userid}")
    public ResponseEntity<List<GameLobby>> addPlayerByGameCode(@RequestBody String gameCode, @PathVariable("userid") Integer playerID) {
        ResponseEntity<List<GameLobby>> tmp = new ResponseEntity<>(gameLobbyRepository.findByGameCode(gameCode), HttpStatus.OK);
        tmp.getBody().get(0).addPlayer(playerID);
        gameLobbyRepository.save(tmp.getBody().get(0));
        return tmp;
    }
}
