package tz_7.PlayerDatabase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Grace Brickey
 */

@Tag(name = "PlayerController", description = "Grace Brickey - rellated to player entity which is used for the player to log in to an account they have already created OR create a new account")
@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository repository;

    //private final Logger logger = LoggerFactory.getLogger(UserController.class);

//    @GetMapping("/enter/{hey}")
//    public String openpage(@PathVariable("hey") String hey) {
//        return "Hey girlie";
//    }
@Operation(summary = "Returns every player", description = "Using a get request it returns every player which will be used for the administer account")
@ApiResponse(responseCode = "404", description = "not found!")
@ApiResponse(responseCode = "403", description = "forbidden!")
@ApiResponse(responseCode = "401", description = "not authorized!")
@ApiResponse(responseCode = "200", description = "Success!")
@GetMapping("/allUsers")
    public List<Player> returnPlayers() {
        List<Player> list = repository.findAll();
        return list;
    }
    @Operation(summary = "Creates a new player", description = "Uses a post request this creates a new player when the user goes to the create new player page and logs information in")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PostMapping(value = "/register", consumes = "application/json") //was save user
    public Player saveUser(@RequestBody Player player) {
//        repository.save(player);
//        return "User saved:" + player.getUsername();
        return repository.save(player);
    }
    @Operation(summary = "Logs into a account", description = "Using a post request this checks if the username and password match any user in the database and if it does it will return the user")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PostMapping(value = "/login", consumes = "application/json")
    public Optional<Player> getUserByUsernameAndPassword(@RequestBody Player player) {
//        Optional<Player> user = repository.findByUsernameAndPassword(username, password);
//        return user;
        return repository.findByUsernameAndPassword(player.getUsername(), player.getPassword());
    }
    @Operation(summary = "Returns the user based off the the ID", description = "Uses a get request to get the user by its id")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "/getUser/{id}", produces = "application/json")
    public Optional<Player> getUserById(@PathVariable("id") Integer id) {
        //logger.info("Entered");
        Optional<Player> user = repository.findById(id);
        return user;
    }
    @Operation(summary = "Returns the username based off the the ID", description = "Uses a get request to get the user by its id")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "/getUser/username/{id}", produces = "application/json")
    public String getUsernameById(@PathVariable("id") Integer id) {
        //logger.info("Entered");
        Player user = repository.findById(id).get();
        return user.getUsername();
    }
    @Operation(summary = "Returns the user password based off the the ID", description = "Uses a get request to get the user by its id")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "/getUser/password/{id}", produces = "application/json")
    public String getPasswordById(@PathVariable("id") Integer id) {
        //logger.info("Entered");
        Player user = repository.findById(id).get();
        return user.getPassword();
    }
    @Operation(summary = "Returns the user first name based off the the ID", description = "Uses a get request to get the user by its id")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "/getUser/firstname/{id}", produces = "application/json")
    public String getNameById(@PathVariable("id") Integer id) {
        //logger.info("Entered");
        Player user = repository.findById(id).get();
        return user.getFirstname();
    }
    @Operation(summary = "Gets id from username", description = "Uses a get request to get the id using the username")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "/getUserid/{username}", produces = "application/json")
    public int getUseridByUsername(@PathVariable("username") String username) {
        //logger.info("Entered");
        Player user = repository.findByUsername(username)
                .orElseThrow();
        return user.getId();
    }
    @GetMapping(value = "/getPlayer/{username}", produces = "application/json")
    public Player getUserByUsername(@PathVariable("username") String username) {
        //logger.info("Entered");
        Player user = repository.findByUsername(username)
                .orElseThrow();
        return user;
    }

    @Operation(summary = "Changes the users password", description = "Uses a put request to change the users password through the username")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PutMapping("/changePassword/{id}/to/{password}")
    public Player changePassword(@PathVariable("id") int id, @PathVariable("password") String password) {
        //logger.info("Entered");
        Player player = repository.findById(id)
                .orElseThrow();
        player.setPassword(password);
        repository.save(player);
        return player;
    }
    @Operation(summary = "Changes the users username", description = "Uses a put request to change the users password through the username")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PutMapping("/changeUsername/{id}/to/{username}")
    public Player changeUsername(@PathVariable("id") int id, @PathVariable("username") String username) {
        //logger.info("Entered");
        Player player = repository.findById(id)
                .orElseThrow();
        player.setUsername(username);
        repository.save(player);
        return player;
    }

    @Operation(summary = "Changes the type of account a user has", description = "Uses a put request to change the type of the user")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PutMapping("/updateType/{id}")
    public Player upgradeToPremium(@PathVariable("id") int id) {
        //logger.info("Entered");
        Player player = repository.findById(id)
                .orElseThrow();
        if (player.getType().equals("b")) {
            player.setType("p");
        }
        repository.save(player);
        return player;
    }
    @Operation(summary = "Changes the type of account a user has", description = "Uses a put request to change the type of the user")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PutMapping("/degradeType/{id}")
    public Player degrade(@PathVariable("id") int id) {
        //logger.info("Entered");
        Player player = repository.findById(id)
                .orElseThrow();
        if (player.getType().equals("p")) {
            player.setType("b");
        }
        repository.save(player);
        return player;
    }

    @GetMapping(value = "/getUserType/{id}")
    public String returnType(@PathVariable int id) {
        Player player = repository.findById(id)
                .orElseThrow();
        return player.getType();
    }


//    @GetMapping("/getUserByType/{type}")
//    public List<User> getUserByType(@PathVariable String type) {
//        return repository.findByType(type);
//    }
}
