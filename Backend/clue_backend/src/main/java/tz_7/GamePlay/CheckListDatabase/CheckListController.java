package tz_7.GamePlay.CheckListDatabase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "CheckListController", description = "Grace Brickey - related to the checklist entity. This will be used when the player guesses to end the game")
@RestController
public class CheckListController {
    @Autowired
    CheckListRepository repo;

    private final Logger logger = LoggerFactory.getLogger(GameLobbyRepository.class);

    @Operation(summary = "Creates a new list", description = "Creates a new list using a post request")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
    @PostMapping(value = "/checklist/new", consumes = "application/json")
    public CheckList newList(@RequestBody CheckList list) {
        return repo.save(list);
    }

    @Operation(summary = "Changes plum based off of its current state", description = "Uses a post request to change plums state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes mustard based off of its current state", description = "Uses a post request to change mustard state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes green based off of its current state", description = "Uses a post request to change green state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes scarlet based off of its current state", description = "Uses a post request to change scarlet state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes white based off of its current state", description = "Uses a post request to change white state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes peacock based off of its current state", description = "Uses a post request to change peacock state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes knife based off of its current state", description = "Uses a post request to change knife state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes candlestick based off of its current state", description = "Uses a post request to change candlestick state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes leadpipe based off of its current state", description = "Uses a post request to change leadpipe state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes revolver based off of its current state", description = "Uses a post request to change revolver state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes rope based off of its current state", description = "Uses a post request to change rope state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes wrench based off of its current state", description = "Uses a post request to change wrench state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes hall based off of its current state", description = "Uses a post request to change hall state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes study based off of its current state", description = "Uses a post request to change study state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes library based off of its current state", description = "Uses a post request to change library state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes lounge based off of its current state", description = "Uses a post request to change lounge state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes conservatory based off of its current state", description = "Uses a post request to change conservatory state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes dining room based off of its current state", description = "Uses a post request to change dining room state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes billiard room based off of its current state", description = "Uses a post request to change billiard room state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes kitchen based off of its current state", description = "Uses a post request to change kitchen state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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

    @Operation(summary = "Changes ballroom based off of its current state", description = "Uses a post request to change ballroom state")
    @ApiResponse(responseCode = "404", description = "not found!")
    @ApiResponse(responseCode = "403", description = "forbidden!")
    @ApiResponse(responseCode = "401", description = "not authorized!")
    @ApiResponse(responseCode = "200", description = "Success!")
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
