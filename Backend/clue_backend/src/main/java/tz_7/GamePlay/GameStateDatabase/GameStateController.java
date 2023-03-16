package tz_7.GamePlay.GameStateDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameStateController {
    @Autowired
    GameStateRepository stateRepository;
    private final Logger logger = LoggerFactory.getLogger(GameStateRepository.class);

    @PostMapping(value = "game/new", consumes = "application/json")
    public GameState newGameState(@RequestBody GameState state) {
        return stateRepository.save(state);
    }
}
