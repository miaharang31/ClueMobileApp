package tz_7.PlayerDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Grace Brickey
 */

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository repository;

    //private final Logger logger = LoggerFactory.getLogger(UserController.class);

//    @GetMapping("/enter/{hey}")
//    public String openpage(@PathVariable("hey") String hey) {
//        return "Hey girlie";
//    }
    @GetMapping("/allUsers")
    public List<Player> returnPlayers() {
        List<Player> list = repository.findAll();
        return list;
    }
    @PostMapping(value = "/register", consumes = "application/json") //was save user
    public Player saveUser(@RequestBody Player player) {
//        repository.save(player);
//        return "User saved:" + player.getUsername();
        return repository.save(player);
    }
    @PostMapping(value = "/login", consumes = "application/json")
    public Optional<Player> getUserByUsernameAndPassword(@RequestBody Player player) {
//        Optional<Player> user = repository.findByUsernameAndPassword(username, password);
//        return user;
        return repository.findByUsernameAndPassword(player.getUsername(), player.getPassword());
    }
    @GetMapping(value = "/getUser/{id}", produces = "application/json")
    public Optional<Player> getUserById(@PathVariable("id") Integer id) {
        //logger.info("Entered");
        Optional<Player> user = repository.findById(id);
        return user;
    }
    @GetMapping(value = "/getUserid/{username}", produces = "application/json")
    public int getUseridByUsername(@PathVariable("username") String username) {
        //logger.info("Entered");
        Player user = repository.findByUsername(username)
                .orElseThrow();
        return user.getId();
    }
    @PutMapping("/changePassword/{username}")
    public Player changePassword(@PathVariable("username") String username, @RequestBody String password) {
        //logger.info("Entered");
        Player player = repository.findByUsername(username)
                .orElseThrow();
        player.setPassword(password);
        return player;
    }

    @PutMapping("/updateType/{username}")
    public Player upgradeToPremium(@PathVariable("username") String username, @RequestBody String account) {
        //logger.info("Entered");
        Player player = repository.findByUsername(username)
                .orElseThrow();
        if (player.getType().equals("Basic")) {
            player.setType("Premium");
        }
        return player;
    }

//    @GetMapping("/getUserByType/{type}")
//    public List<User> getUserByType(@PathVariable String type) {
//        return repository.findByType(type);
//    }
}