package tz_7.GamePlay.PlayerInfoDatabase;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
import tz_7.RoleDatabase.Role;
import tz_7.RoleDatabase.RoleRepository;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;

@Tag(name = "CheckListController", description = "Mia Harang - related to the PlayerInfo entity. This will be used to know the players information during an individual game")
@RestController
public class PlayerInfoController {
    @Autowired
    PlayerInfoRepository repo;
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    RoleRepository charRepo;

    @Operation(summary = "Creates a information database for a player during a game", description = "Using post request, this creates a new set of information for a player in a certain game")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PostMapping(value = "info/{id}", consumes = "application/json")
    public PlayerInfo newInfo(@RequestBody PlayerInfo info, @PathVariable Integer id) {
        Player player = playerRepo.findById(id).get();
        info.setPlayer(player);
        player.setPlayerInfo(info);

        PlayerInfo tmp = repo.findByPlayer(player);
        if(tmp != null) {
            deleteInfo(tmp.getID());
        }

        repo.save(info);
        playerRepo.save(player);
        return info;
    }

    @Operation(summary = "Selects the players character for the game", description = "Using a put request, this selects the character that the player is going to be in the specific game")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PutMapping(value = "info/{id}/role/{charid}")
    public PlayerInfo chooseRole(@PathVariable Integer id, @PathVariable String charid) {
        Role role = charRepo.findByName(charid);
        PlayerInfo info = repo.findById(id).get();

        info.setRole(role);
        role.setPlayerInfo(info);

        repo.save(info);
        charRepo.save(role);
        return info;
    }
    @Operation(summary = "Returns the players information", description = "Using a post request, this gets the players information")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PostMapping(value = "/getInfo", consumes = "application/json")
    public PlayerInfo getInfo(@RequestBody PlayerInfo info) {
        PlayerInfo information = repo.getById(info.getID());
        return information;
    }

    @Operation(summary = "Returns the players information", description = "Using a post request, this gets the players information")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "info/player/{id}")
    public PlayerInfo getInfoByPlayerID(@PathVariable Integer id) {
        Player player = playerRepo.findById(id).get();
        PlayerInfo playerInfo = repo.findByPlayer(player);

        return playerInfo;
    }

    @Operation(summary = "Returns the players character", description = "Using a post request, this gets the players character from the game")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "info/player/{id}/role")
    public Role getRoleByPlayer(@PathVariable Integer id) {
        Player player = playerRepo.findById(id).get();
        PlayerInfo playerInfo = repo.findByPlayer(player);

        return playerInfo.getRole();
    }

    @Operation(summary = "Deletes the players information", description = "Using a delete request, this deletes the players information. This is done after a game")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @DeleteMapping(value = "info/{id}/delete")
    public String deleteInfo(@PathVariable Integer id) {
        repo.deleteById(id);
        return "info deleted";
    }


}
