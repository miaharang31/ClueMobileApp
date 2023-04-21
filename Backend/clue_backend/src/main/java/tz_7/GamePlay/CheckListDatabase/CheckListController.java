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

    @PostMapping(value = "/changePlum", consumes = "application/json")
    public CheckList changePlum(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isPlum()) {
            newList.setPlum(false);
        }
        else {
            newList.setPlum(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeMustard", consumes = "application/json")
    public CheckList changeMustard(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isMustard()) {
            newList.setMustard(false);
        }
        else {
            newList.setMustard(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeGreen", consumes = "application/json")
    public CheckList changeGreen(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isGreen()) {
            newList.setGreen(false);
        }
        else {
            newList.setGreen(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeScarlet", consumes = "application/json")
    public CheckList changeScarlet(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isScarlet()) {
            newList.setScarlet(false);
        }
        else {
            newList.setScarlet(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeWhite", consumes = "application/json")
    public CheckList changeWhite(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isWhite()) {
            newList.setWhite(false);
        }
        else {
            newList.setWhite(true);
        }
        return newList;
    }

    @PostMapping(value = "/changePeacock", consumes = "application/json")
    public CheckList changePeacock(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isPeacock()) {
            newList.setPeacock(false);
        }
        else {
            newList.setPeacock(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeKnife", consumes = "application/json")
    public CheckList changeKnife(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isKnife()) {
            newList.setKnife(false);
        }
        else {
            newList.setKnife(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeCandle", consumes = "application/json")
    public CheckList changeCandle(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isCandlestick()) {
            newList.setCandlestick(false);
        }
        else {
            newList.setCandlestick(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeLead", consumes = "application/json")
    public CheckList changeLead(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isLeadpipe()) {
            newList.setLeadpipe(false);
        }
        else {
            newList.setLeadpipe(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeRevolver", consumes = "application/json")
    public CheckList changeRevolver(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isRevolver()) {
            newList.setRevolver(false);
        }
        else {
            newList.setRevolver(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeRope", consumes = "application/json")
    public CheckList changeRope(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isRope()) {
            newList.setRope(false);
        }
        else {
            newList.setRope(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeWrench", consumes = "application/json")
    public CheckList changeWrench(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isWrench()) {
            newList.setWrench(false);
        }
        else {
            newList.setWrench(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeHall", consumes = "application/json")
    public CheckList changeHall(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isHall()) {
            newList.setHall(false);
        }
        else {
            newList.setHall(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeStudy", consumes = "application/json")
    public CheckList changeStudy(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isStudy()) {
            newList.setStudy(false);
        }
        else {
            newList.setStudy(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeLibrary", consumes = "application/json")
    public CheckList changeLibrary(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isLibrary()) {
            newList.setLibrary(false);
        }
        else {
            newList.setLibrary(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeLounge", consumes = "application/json")
    public CheckList changeLounge(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isLounge()) {
            newList.setLounge(false);
        }
        else {
            newList.setLounge(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeConservatory", consumes = "application/json")
    public CheckList changeConservatory(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isConservatory()) {
            newList.setConservatory(false);
        }
        else {
            newList.setConservatory(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeDining", consumes = "application/json")
    public CheckList changeDining(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isDining()) {
            newList.setDining(false);
        }
        else {
            newList.setDining(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeBilliard", consumes = "application/json")
    public CheckList changeBilliard(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isBilliard()) {
            newList.setBilliard(false);
        }
        else {
            newList.setBilliard(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeKitchen", consumes = "application/json")
    public CheckList changeKitchen(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isKitchen()) {
            newList.setKitchen(false);
        }
        else {
            newList.setKitchen(true);
        }
        return newList;
    }

    @PostMapping(value = "/changeBall", consumes = "application/json")
    public CheckList changeBall(@RequestBody CheckList list) {
        CheckList newList = repo.findByID(list.getID());
        if (newList.isBallroom()) {
            newList.setBallroom(false);
        }
        else {
            newList.setBallroom(true);
        }
        return newList;
    }

    

}
