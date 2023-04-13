package tz_7.GamePlay.PlayerInfoDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tz_7.RoleDatabase.Role;
import tz_7.RoleDatabase.RoleRepository;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;

@RestController
public class PlayerInfoController {
    @Autowired
    PlayerInfoRepository repo;
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    RoleRepository charRepo;

    @PostMapping(value = "info/{id}", consumes = "application/json")
    public PlayerInfo newInfo(@RequestBody PlayerInfo info, @PathVariable Integer id) {
        Player player = playerRepo.findById(id).get();
        info.setPlayer(player);
        player.setPlayerInfo(info);

        repo.save(info);
        playerRepo.save(player);
        return info;
    }

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
    @PostMapping(value = "/getInfo", consumes = "application/json")
    public PlayerInfo getInfo(@RequestBody PlayerInfo info) {
        PlayerInfo information = repo.getById(info.getID());
        return information;
    }


}
