package tz_7.GamePlay.PlayerInfoDatabase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import tz_7.RoleDatabase.Role;
import tz_7.RoleDatabase.RoleRepository;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;

/**
 * Author: Mia Harang
 *  Rest Controller to control the different actions of
 *  the player info database table
 *      PlayerInfo deals with the all the individual information
 *      of players in the game session
 */
@Tag(name = "CheckListController", description = "Mia Harang - related to the PlayerInfo entity. This will be used to know the players information during an individual game")
@RestController
public class PlayerInfoController {
    @Autowired
    PlayerInfoRepository repo;
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    RoleRepository charRepo;

    /**
     * Post Mapping to create a new player info instance based on the
     * ID of a player
     *  Deletes info if one already exists for that player
     *  (Prevents duplicates)
     * @param info
     *  (JSON OBJECT) New player info instance to edit and save into
     *  repository
     * @param id
     *  ID of player to create the info for
     * @return
     *  New Info object
     */
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

    /**
     * Put Mapping to put the players choice of role
     * into the player info instance
     * @param id
     *  ID of player
     * @param charid
     *  ID of the role to put into column
     * @return
     *  Update info object
     */
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

    /**
     * im ngl idk who created this
     * @param info
     * @return
     */
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

    /**
     * Get Mapping to get the info base on the player
     * associated with it
     * @param id
     *  ID of player
     * @return
     *  Info object if one is found
     */
    @Operation(summary = "Returns the players information", description = "Using a post request, this gets the players information")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "/info/player/{id}")
    public PlayerInfo getInfoByPlayerID(@PathVariable Integer id) {
        Player player = playerRepo.findById(id).get();
        PlayerInfo playerInfo = repo.findByPlayer(player);

        return playerInfo;
    }

    /**
     * Get Mapping to get the role chosen by a player based
     * on the player's ID
     * @param id
     *  ID of player
     * @return
     *  Role object that that player chose
     */
    @Operation(summary = "Returns the players character", description = "Using a post request, this gets the players character from the game")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @GetMapping(value = "/info/player/{id}/role")
    public Role getRoleByPlayer(@PathVariable Integer id) {
        Player player = playerRepo.findById(id).get();
        PlayerInfo playerInfo = repo.findByPlayer(player);

        return playerInfo.getRole();
    }


    @PutMapping("/player/{id}/x/{x}/y/{y}")
    public void setXandY(@PathVariable Integer id, @PathVariable Integer x, @PathVariable Integer y) {
        PlayerInfo player = repo.findById(id).get();
        player.setX(x);
        player.setY(y);
        repo.save(player);
    }

    @GetMapping("/player/coords/{id}")
    public Integer[] getXandY(@PathVariable Integer id) {
        PlayerInfo player = repo.findById(id).get();
        Integer[] xandy = {player.getX(), player.getY()};
        return xandy;
    }

    /**
     * Delete Mapping to remove the information based on the
     * player ID
     * @param id
     *  ID of player
     * @return
     *  String indication the information has been deleted
     */
    @Operation(summary = "Deletes the players information", description = "Using a delete request, this deletes the players information. This is done after a game")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @DeleteMapping(value = "/info/{id}/delete")
    public String deleteInfo(@PathVariable Integer id) {
        repo.deleteById(id);
        return "info deleted";
    }


}
