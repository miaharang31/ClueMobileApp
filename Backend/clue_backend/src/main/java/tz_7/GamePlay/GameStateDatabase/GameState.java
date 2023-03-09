//package tz_7.GamePlay.GameStateDatabase;
//
//import jakarta.persistence.*;
//
//import java.util.ArrayList;
//
//@Entity
//@Table(name = "GameState")
//public class GameState {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID", unique = true)
//    private Integer ID;
//    @Column(name = "versionID")
//    private Integer versionID;
//    @Column(name = "lobbyID")
//    private Integer lobbyID;
//    @Column(name = "finalCardIDs")
//    private int[] finalCardIDs;
//    @Column(name = "turnOrder")
//    private ArrayList<Integer> turnOrder;
//    private int turnNum = 0;
//    @Column(name = "rolledNumber")
//    private int rolledNumber;
//    @Column(name = "playerHand")
//    private ArrayList<ArrayList<Integer>> playerHand;
//
//    public GameState() {
//        finalCardIDs = new int[3];
//        turnOrder = new ArrayList<>();
//        playerHand = new ArrayList<>();
//    }
//
//    public Integer getNextPlayer() {
//        Integer tmp = turnOrder.get(turnNum);
//        if(turnNum == turnOrder.size()-1) {turnNum = 0;}
//        else {turnNum += 1;}
//        return tmp;
//    }
//    public ArrayList<Integer> getPlayerHand(int playerID) {
//        for(int i = 0; i < turnOrder.size(); i++) {
//            if (turnOrder.get(i) == playerID) {
//                return playerHand.get(i);
//            }
//        }
//        return null;
//    }
//    public void setRolledNumber(int rolledNumber) {
//        this.rolledNumber = rolledNumber;
//    }
//    public Boolean checkFinalGuess(int[] guess) {
//        for (int i = 0; i < finalCardIDs.length; i++) {
//            if(guess[i] != finalCardIDs[i]) {return false;}
//        }
//        return true;
//    }
//
//    public Integer getRolledNumber() {return rolledNumber;}
//    public int[] getFinalCardIDs() {return finalCardIDs;}
//    public Integer getVersionID() {return versionID;}
//    public Integer getID() {return ID;}
//    public Integer getLobbyID() {return lobbyID;}
//}
