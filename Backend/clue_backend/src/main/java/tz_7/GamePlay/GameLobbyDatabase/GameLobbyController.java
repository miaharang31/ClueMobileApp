package tz_7.GamePlay.GameLobbyDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tz_7.GamePlay.GameLobbyDatabase.GameLobbyController;
import tz_7.GamePlay.GameLobbyDatabase.GameLobbyRepository;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;

import java.util.Optional;

@RestController
public class GameLobbyController {
    @Autowired
    GameLobbyRepository gameLobbyRepository;

    private final Logger logger = LoggerFactory.getLogger(GameLobbyRepository.class);

    @RequestMapping(method = RequestMethod.POST, path = "/lobby/new/{host_id}/{game_code}/{game_type}")
    public GameLobby lobby(@PathVariable("host_id") int host_id, @PathVariable("game_code") String game_code, @PathVariable("game_type") String game_type) {
        //TODO: Make new game lobby db and add it to db
        GameLobby tmp = new GameLobby();
        return tmp;
    }
}
