package tz_7.GamePlay.GameLobbyDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameLobbyController {
    @Autowired
    GameLobbyRepository gameLobbyRepository;

    private final Logger logger = LoggerFactory.getLogger(GameLobbyRepository.class);

    @PostMapping("/lobby/new")
    public GameLobby newLobby(Integer max, String gameCode, Integer host_id, String type) {
        GameLobby tmp;
        if(type.equals("premium")) {
            tmp = new GameLobby(max, gameCode, host_id, true);
        } else {
            tmp = new GameLobby(max, gameCode, host_id, false);
        }
        gameLobbyRepository.save(tmp);
        return tmp;
    }

//    @GetMapping("lobby/host")
//    public ResponseEntity<List<GameLobby>> getLobbyByHost(@RequestParam("host_id") int host_id) {
//        return new ResponseEntity<List<GameLobby>>(gameLobbyRepository.findByHostID(host_id), HttpStatus.OK);
//    }
}
