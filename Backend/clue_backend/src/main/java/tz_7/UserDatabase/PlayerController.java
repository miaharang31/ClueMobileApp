package tz_7.UserDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tz_7.UserDatabase.PlayerRepository;
import tz_7.UserDatabase.Player;

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
    @PostMapping("/saveUser")
    public String saveUser(@RequestBody Player player) {
        repository.save(player);
        return "User saved:" + player.getUsername();
    }
    @PostMapping("/findUserUnP")
    public Optional<Player> getUserByUsernameAndPassword(@RequestBody String username, @RequestBody String password) {
        Optional<Player> user = repository.findByUsernameAndPassword(username, password);
        return user;
    }
    @GetMapping("/getUser/{id}")
    public Optional<Player> getUserById(@PathVariable("id") Integer id) {
        //logger.info("Entered");
        Optional<Player> user = repository.findById(id);
        return user;
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
