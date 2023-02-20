package tz_7.ScoreDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Mia Harang
 *  Controller for the score data table
 */

@RestController
class ScoreController {
    @Autowired
    ScoreRepository scoreRepository;

    private final Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @RequestMapping(method = RequestMethod.POST, path = "/score/new/{user_id}")
    public String score(@PathVariable("user_id") int user_id) {
        Score score = new Score(user_id, 0);
        scoreRepository.save(score);
        return "New score added for user with id " + user_id;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/score/{user_id}")
    public Optional<Score> getScoreByUserID(@PathVariable("user_id") int user_id) {
        logger.info("Entered into Controller Layer");
        Optional<Score> res = scoreRepository.findById(user_id);
        return res;
    }

//    @RequestMapping(method = RequestMethod.POST, path = "/score/{user_id}/win")
//    public String addWin(@PathVariable("user_id") int user_id) {
//        logger.info("Entered into controller layer");
//        Optional<Score> res = scoreRepository.findById(user_id);
//        int tmp = res.get().getScore();
//        res.get().addWin();
////        TODO: Figure out how to update score value
//        return "Score updated from " + tmp + " to " + res.get().getScore();
//    }
}