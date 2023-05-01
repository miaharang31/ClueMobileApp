package tz_7.WebSocket.Game;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.stereotype.Controller;
import tz_7.CardDatabase.Card;
import tz_7.CardDatabase.CardRepository;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;
import tz_7.GamePlay.GameLobbyDatabase.GameLobbyRepository;
import tz_7.GamePlay.PlayerInfoDatabase.PlayerInfo;
import tz_7.GamePlay.PlayerInfoDatabase.PlayerInfoRepository;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;
import tz_7.GamePlay.GameStateDatabase.GameState;
import tz_7.GamePlay.GameStateDatabase.GameStateRepository;

@ServerEndpoint("/websocket/game/{gameid}/player/{playerid}")
@Controller
public class GameSocket {
//    @LocalServerPort
//    int port = 8081;

    private static Map< Session, Player> sessionPlayerMap = new Hashtable < > ();
    private static Map < Player, Session > playerSessionMap = new Hashtable< >();
    private static Map < Player, GameState > playerGameStateMap = new Hashtable<>();

    private static PlayerRepository playerRepository;
    private static GameStateRepository gameStateRepository;
    private static GameLobbyRepository gameLobbyRepository;
    private static PlayerInfoRepository playerInfoRepository;
    private static CardRepository cardRepository;

    private final Logger logger = LoggerFactory.getLogger(GameChatSocket.class);

    private int playerIterator;

    @Autowired
    public void setPlayerRepository(PlayerRepository repo) {
        playerRepository = repo;
    }

    @Autowired
    public void setCardRepository(CardRepository repo) {
        cardRepository = repo;
    }
    @Autowired
    public void setGameStateRepository(GameStateRepository repo) {
        gameStateRepository = repo;
    }
    @Autowired
    public void setGameLobbyRepository(GameLobbyRepository repo) {
        gameLobbyRepository = repo;
    }
    @Autowired
    public void setPlayerInfoRepository(PlayerInfoRepository repo) {playerInfoRepository = repo;}

    @OnOpen
    public void onOpen(Session session, @PathParam("playerid") Integer playerid, @PathParam("gameid") Integer gameid)
            throws IOException {
        logger.info("Entered into Open");

        Player player = playerRepository.findById(playerid).get();
        GameState state = gameStateRepository.findById(gameid).get();

        sessionPlayerMap.put(session, player);
        playerSessionMap.put(player, session);
        playerGameStateMap.put(player, state);
    }

    /**
     * Handles where to send the
     * @param session
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        logger.info("Entered Message: " + message);
        switch (message) {
//            TODO: Handle different game mechanics
            case "Game Deleted":
                logger.info("Entered Game Deletion");
                broadcast("Game Deleted");
                break;
            case "Turn Ended":
                logger.info("Enter Turn Ending");
//                Player curPlayer = sessionPlayerMap.get(session);
//                GameState state = playerGameStateMap.remove(curPlayer);
//
////                Switching the db information on if it is their turn or not
//                PlayerInfo info = playerInfoRepository.findByPlayer(curPlayer);
//                info.setTurn(false);
//                playerInfoRepository.save(info);
//
//                Player nextPlayer = state.getNextPlayer();
//                state = gameStateRepository.findById(state.getID()).get();
//                gameStateRepository.save(state);
//                info = playerInfoRepository.findByPlayer(nextPlayer);
//                info.setTurn(true);
//                playerInfoRepository.save(info);

//                Telling everyone but the current player that a turn has ended
                broadcast("Turn Ended");
//                Set<Player> players = state.getTurnOrder();
//                for(Player player : players) {
//                    if(player != curPlayer) {
//                        sendMessageToPArticularUser(player, "Turn Ended");
//                    }
//                }
                break;
            case "Show Card":
                logger.info("Entered into Close");
                break;
            case "Game Ended":
                break;
            default:
                if(message.startsWith(">")) {
//                    Giving a card [message formated : ">test 2" i.e ">username cardId cardname1 cardname2 cardname3"
                    String username = message.split(" ")[0].substring(1);
                    Player destPlayer = playerRepository.findByUsername(username).get();
                    Player cur = sessionPlayerMap.get(session);
//                          Sending card id to player
                        sendMessageToPArticularUser(destPlayer, "<" + message.split(" ")[1]);
                } else if (message.startsWith("-")) {
//                    Player had no card to give
                    Player player = sessionPlayerMap.get(session);
                    GameState game = playerGameStateMap.get(player);

                    Player dest = game.getNextPlayer();
                    sendMessageToPArticularUser(dest, ">" + message.split(" ")[0].substring(1) + " " + message.split(" ")[1] + " " + message.split(" ")[2] + " " + message.split(" ")[3]);

                } else if (message.startsWith("Guess")) {
//                    guessing card [message formated : ">test 2" i.e "Guess cardname1 cardname2 cardname3"
                    Player player = sessionPlayerMap.get(session);
                    GameState game = playerGameStateMap.get(player);

                    Player dest = game.getNextPlayer();
                    sendMessageToPArticularUser(dest, ">" + player.getUsername() + " " + message.split(" ")[1] + " " + message.split(" ")[2] + " " + message.split(" ")[3]);
                } else if (message.startsWith("Final")) {
//                    guessing card [message formated : ">test 2" i.e "Final cardname1 cardname2 cardname3"
//                    Player player = sessionPlayerMap.get(session);
//                    GameState game = playerGameStateMap.get(player);
//
//                    Player dest = game.getNextPlayer();
//                    sendMessageToPArticularUser(dest, ">" + player.getUsername() + " " + message.split(" ")[1] + " " + message.split(" ")[2] + " " + message.split(" ")[3]);
                    broadcast("Game Ended");
                }
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        Player player = sessionPlayerMap.get(session);

        sessionPlayerMap.remove(session);
        playerSessionMap.remove(player);
        playerGameStateMap.remove(player);
    }

    /**
     * Sends message to specific user in server
     * @param player
     *  Player to send message to
     * @param message
     *  message to send
     */
    private void sendMessageToPArticularUser(Player player, String message) {
        try {
            playerSessionMap.get(player).getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends message to all players in server
     * @param message
     *  message to send
     */
    private void broadcast(String message) {
        sessionPlayerMap.forEach((session, player) -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
