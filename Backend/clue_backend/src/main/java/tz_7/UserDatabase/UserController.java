package tz_7.UserDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Grace Brickey
 */

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    //private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/enter/{hey}")
    public String openpage(@PathVariable("hey") String hey) {
        return "Hey girlie";
    }
    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User user) {
        repository.save(user);
        return "User saved:" + user.getUsername();
    }
    @GetMapping("/getUser/{id}")
    public Optional<User> getUserById(@PathVariable("id") Integer id) {
        //logger.info("Entered");
        Optional<User> user = repository.findById(id);
        return user;
    }

//    @GetMapping("/getUserByType/{type}")
//    public List<User> getUserByType(@PathVariable String type) {
//        return repository.findByType(type);
//    }
}
