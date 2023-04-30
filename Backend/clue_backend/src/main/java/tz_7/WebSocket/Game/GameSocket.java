package tz_7.WebSocket.Game;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;
import tz_7.GamePlay.GameLobbyDatabase.GameLobbyRepository;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;
import tz_7.GamePlay.GameStateDatabase.GameState;
import tz_7.GamePlay.GameStateDatabase.GameStateRepository;

@ServerEndpoint("/websocket/game/{gameid}/player/{playerid}")
@Controller
public class GameSocket {
    private static Map< Session, Player> sessionPlayerMap = new Hashtable < > ();
    private static Map < Player, Session > playerSessionMap = new Hashtable< >();
    private static Map < Player, GameState > playerGameStateMap = new Hashtable<>();

    private static PlayerRepository playerRepository;
    private static GameStateRepository gameStateRepository;
    private static GameLobbyRepository gameLobbyRepository;

    private final Logger logger = LoggerFactory.getLogger(GameChatSocket.class);

    @Autowired
    public void setPlayerRepository(PlayerRepository repo) {
        playerRepository = repo;
    }
    @Autowired
    public void setGameStateRepository(GameStateRepository repo) {
        gameStateRepository = repo;
    }
    @Autowired
    public void setGameLobbyRepository(GameLobbyRepository repo) {
        gameLobbyRepository = repo;
    }

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

                break;
            case "Show Card":
                logger.info("Entered into Close");
                break;
            default:
//                TODO: Deal with Card stuff
                if(message.startsWith(">")) {
//                    Giving a card
                } else if (message.startsWith("<")) {
//                    Recieving a card
                } else if (message.startsWith("-")) {
//                    Player
                }
//                TODO: Maybe another one for like if the player doesn't have a card to show
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
