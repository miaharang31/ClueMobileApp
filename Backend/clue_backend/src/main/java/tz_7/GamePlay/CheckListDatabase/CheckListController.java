package tz_7.GamePlay.CheckListDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tz_7.GamePlay.GameLobbyDatabase.GameLobbyRepository;

/**
 * Author: Mia Harang
 * Controller for the CheckList Databse
 */
@RestController
public class CheckListController {
    @Autowired
    CheckListRepository repo;

    private final Logger logger = LoggerFactory.getLogger(GameLobbyRepository.class);
    @PostMapping(value = "/checklist/new", consumes = "application/json")
    public CheckList newList(@RequestBody CheckList list) {
        return repo.save(list);
    }
}
