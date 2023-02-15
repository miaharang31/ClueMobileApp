package tz_7.UserDatabase;

import tz_7.UserDatabase.UserRepository;
import tz_7.UserDatabase.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User user) {
        repository.save(user);
        return "User saved";
    }
    @GetMapping("/getAllUsers")
    public List<User> getAll() {
        return repository.findAll();
    }

    @GetMapping("/getUserByType/{type}")
    public List<User> getUserByType(@PathVariable String type) {
        return repository.findByType(type);
    }
}
