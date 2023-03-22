package tz_7.CharacterDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CharacterController {
    @Autowired
    private CharacterRepository repository;

    @GetMapping("/allCharacters")
    public List<Character> findAllCharacters() {
        List<Character> list = repository.findAll();
        return list;
    }

    @GetMapping("/findCharactersIn/{game}")
    public List<Character> findCharsInGame(@PathVariable("game") String game) {
        List<Character> list = repository.findAllByGame(game);
        return list;
    }

    @PostMapping("/createCharacter")
    public String createChar(@RequestBody Character character) {
        repository.save(character);
        return "Character saved: " + character.getName();
    }
}
