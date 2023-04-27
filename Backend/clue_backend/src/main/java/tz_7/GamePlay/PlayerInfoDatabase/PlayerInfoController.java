package tz_7.GamePlay.PlayerInfoDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping(value = "info/player/{id}")
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
    @GetMapping(value = "info/player/{id}/role")
    public Role getRoleByPlayer(@PathVariable Integer id) {
        Player player = playerRepo.findById(id).get();
        PlayerInfo playerInfo = repo.findByPlayer(player);

        return playerInfo.getRole();
    }

    /**
     * Delete Mapping to remove the information based on the
     * player ID
     * @param id
     *  ID of player
     * @return
     *  String indication the information has been deleted
     */
    @DeleteMapping(value = "info/{id}/delete")
    public String deleteInfo(@PathVariable Integer id) {
        repo.deleteById(id);
        return "info deleted";
    }


}
