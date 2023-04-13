package tz_7.RoleDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    private RoleRepository repository;

    @GetMapping("/allRoles")
    public List<Role> findAllRoles() {
        List<Role> list = repository.findAll();
        return list;
    }

    @GetMapping("/findRolesIn/{game}")
    public List<Role> findRolesInGame(@PathVariable("game") String game) {
        List<Role> list = repository.findAllByGame(game);
        return list;
    }

    @PostMapping("/createRole")
    public String createRole(@RequestBody Role role) {
        repository.save(role);
        return "Role saved: " + role.getName();
    }
//    @PostMapping("/getCharacterFromId") {
//
//    }
}
