package cs309.ApiTutorial.api.controller;

import cs309.ApiTutorial.api.model.User;
import cs309.ApiTutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/user")
    public User getUser(@RequestParam Integer id) {
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            return (User) user.get();
        }
        return null;
    }

//    @PostMapping("/upgradeUser")
//    public void upgradeUser(@RequestBody Integer id) {
//            Optional user = userService.getUser(id);
//            if (user.isEmpty()) {
//                return;
//            }
//            else {
//                (User) user.setUsertype("Premium");
//            }
//
//     }

}
