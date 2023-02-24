package tz_7.GamePlay.GameLobbyDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameLobbyController {
    @Autowired
    GameLobbyRepository gameLobbyRepository;

    private final Logger logger = LoggerFactory.getLogger(GameLobbyRepository.class);

    @PostMapping("/lobby/new")
    public GameLobby newLobby(Integer max, String gameCode, Integer hostID, String type) {
        GameLobby tmp;
        if(type.equals("premium")) {
            tmp = new GameLobby(max, gameCode, hostID, true);
        } else {
            tmp = new GameLobby(max, gameCode, hostID, false);
        }
        gameLobbyRepository.save(tmp);
        return tmp;
    }

    @GetMapping("lobby")
    public ResponseEntity<List<GameLobby>> getAllLobbies() {
        return new ResponseEntity<List<GameLobby>>(gameLobbyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("lobby/notPremium")
    public ResponseEntity<List<GameLobby>> getNormalLobbies() {
        return new ResponseEntity<List<GameLobby>>(gameLobbyRepository.findByIsPremium(false), HttpStatus.OK);
    }

    @GetMapping("lobby/host")
    public ResponseEntity<List<GameLobby>> getLobbyByHost(@RequestParam("hostID") int hostID) {
        return new ResponseEntity<List<GameLobby>>(gameLobbyRepository.findByHostID(hostID), HttpStatus.OK);
    }

    @PostMapping("lobby/addplayer")
    public String addPlayerByGameCode(@RequestParam("gameCode") String gameCode, @RequestParam("playerID") Integer playerID) {
        ResponseEntity<List<GameLobby>> tmp = new ResponseEntity<>(gameLobbyRepository.findByGameCode(gameCode), HttpStatus.OK);
        if(tmp.hasBody()) {
            return "New Player Added";
        } else {
            return "No Game Found With That Code";
        }
    }
}
