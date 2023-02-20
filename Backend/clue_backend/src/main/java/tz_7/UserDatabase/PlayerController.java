package tz_7.UserDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Grace Brickey
 */

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository repository;

    //private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/enter/{hey}")
    public String openpage(@PathVariable("hey") String hey) {
        return "Hey girlie";
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

//    @GetMapping("/getUserByType/{type}")
//    public List<User> getUserByType(@PathVariable String type) {
//        return repository.findByType(type);
//    }
}
