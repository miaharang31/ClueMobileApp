package tz_7.ScoreDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mia Harang
 */

@RestController
class ScoreController {
    @Autowired
    ScoreRepository scoreRepository;

    private final Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @RequestMapping(method = RequestMethod.POST, path = "/score/new")
    public String score(Score score) {
        scoreRepository.save(score);
        return "New User (id: " + score.getUserID() + ") added to Score table";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/score")
    public Optional<Score> getScoreByUserID(int id) {
        logger.info("Entered into Controller Layer");
        Optional<Score> res = scoreRepository.findById(id);
    }
}