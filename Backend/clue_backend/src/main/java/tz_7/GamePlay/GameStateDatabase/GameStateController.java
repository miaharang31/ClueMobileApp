//package tz_7.GamePlay.GameStateDatabase;
//
//import jakarta.servlet.http.HttpServletResponse;
//import net.minidev.json.parser.JSONParser;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.io.FileReader;
//import java.util.List;
//import java.util.Objects;
//
//@RestController
//public class GameStateController {
//    @Autowired
//    GameStateRepository stateRepository;
//    private final Logger logger = LoggerFactory.getLogger(GameStateRepository.class);
//
//    @PostMapping(value = "game/new", consumes = "application/json")
//    public GameState newGameState(@RequestBody GameState state) {
//        return stateRepository.save(state);
//    }
//}
