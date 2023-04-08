package tz_7.GamePlay.PlayerInfoDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tz_7.CharacterDatabase.Character;
import tz_7.CharacterDatabase.CharacterRepository;
import tz_7.GamePlay.GameStateDatabase.GameState;
import tz_7.GamePlay.GameStateDatabase.GameStateRepository;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;

@RestController
public class PlayerInfoController {
    @Autowired
    PlayerInfoRepository repo;
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    CharacterRepository charRepo;

    @PostMapping(value = "info/{id}", consumes = "application/json")
    public PlayerInfo newInfo(@RequestBody PlayerInfo info, @PathVariable Integer id) {
        Player player = playerRepo.findById(id).get();
        info.setPlayer(player);
        player.setPlayerInfo(info);

        repo.save(info);
        playerRepo.save(player);
        return info;
    }

    @PutMapping(value = "info/{id}/character/{charid}")
    public PlayerInfo chooseCharacter(@PathVariable Integer id, @PathVariable String charid) {
        Character character = charRepo.findByName(charid);
        PlayerInfo info = repo.findById(id).get();

        info.setCharacter(character);
        character.setPlayerInfo(info);

        repo.save(info);
        charRepo.save(character);
        return info;
    }


}
