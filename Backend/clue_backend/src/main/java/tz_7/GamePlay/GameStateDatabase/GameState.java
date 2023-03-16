package tz_7.GamePlay.GameStateDatabase;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.ArrayList;

@Entity
@Table(name = "GameState")
public class GameState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer ID;
    @Column(name = "versionID")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer versionID;
    @Column(name = "lobbyID")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer lobbyID;
    @Column(name = "finalCardIDs")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer[] finalCardIDs;
    @Column(name = "turnOrder")
    @NotFound(action = NotFoundAction.IGNORE)
    private ArrayList<Integer> turnOrder;

//    TODO: FIND A NEW WAY TO DO PLAYER HAND
//    @Column(name = "playerHand")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private ArrayList<ArrayList<Integer>> playerHand;

    private int turnNum = 0;

    public GameState() {
        finalCardIDs = new Integer[3];
        turnOrder = new ArrayList<>();
//        playerHand = new ArrayList<>();
    }

    public GameState(Integer versionID) {
        this.versionID = versionID;
    }

    public Integer getNextPlayer() {
        Integer tmp = turnOrder.get(turnNum);
        if(turnNum == turnOrder.size()-1) {turnNum = 0;}
        else {turnNum += 1;}
        return tmp;
    }
//    public ArrayList<Integer> getPlayerHand(int playerID) {
//        for(int i = 0; i < turnOrder.size(); i++) {
//            if (turnOrder.get(i) == playerID) {
//                return playerHand.get(i);
//            }
//        }
//        return null;
//    }
    public Boolean checkFinalGuess(int[] guess) {
        for (int i = 0; i < finalCardIDs.length; i++) {
            if(guess[i] != finalCardIDs[i]) {return false;}
        }
        return true;
    }
    public Integer[] getFinalCardIDs() {return finalCardIDs;}
    public Integer getVersionID() {return versionID;}
    public Integer getID() {return ID;}
    public Integer getLobbyID() {return lobbyID;}
}
