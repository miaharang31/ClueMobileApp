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
import tz_7.GamePlay.GameLobbyDatabase.GameLobbyRepository;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;

@ServerEndpoint("/websocket/chat/{playerid}")
@Controller
public class GameChatSocket {
    private static Map< Session, Player> sessionPlayerMap = new Hashtable < > ();
    private static Map < Player, Session > playerSessionMap = new Hashtable< >();

    private static PlayerRepository playerRepository;
    private static GameLobbyRepository gameLobbyRepository;

    private final Logger logger = LoggerFactory.getLogger(GameChatSocket.class);

    @Autowired
    public void setPlayerRepository(PlayerRepository repo) {
        playerRepository = repo;
    }
    @Autowired
    public void setGameLobbyRepository(GameLobbyRepository repo) {
        gameLobbyRepository = repo;
    }

    /**
     * Maps players and sessions when a new connection is established
     * @param session
     *  Client session
     * @param playerid
     *  ID of joining player
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("playerid") Integer playerid)
    throws IOException {
        logger.info("Entered into Open");

        Player player = playerRepository.findById(playerid).get();

        sessionPlayerMap.put(session, player);
        playerSessionMap.put(player, session);

        String message = player.getUsername() + "has joined the game";
        broadcast(message);
    }

    /**
     * Sends a message to the chat
     *  Can send to everyone or a specific player
     * @param session
     *  Client session
     * @param message
     *  message to send
     * @throws IOException
     */
    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        logger.info("Entered Message: " + message);
        Player player = playerRepository.findByUsername(sessionPlayerMap.get(session).getUsername()).get();

        if(message.startsWith("@")) {
            Player destPlayer = playerRepository.findByUsername(message.split(" ")[0].substring(1)).get();
            sendMessageToPArticularUser(destPlayer, "[DM] " + player + ": " + message);
            sendMessageToPArticularUser(player, "[DM] " + player + ": " + message);
        } else {
            broadcast(player.getUsername() + ": " + message);
        }
    }

    /**
     * Removes players from the server
     * @param session
     *  Client session
     * @throws IOException
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        Player player = sessionPlayerMap.get(session);

        sessionPlayerMap.remove(session);
        playerSessionMap.remove(player);

        String message = player.getUsername() + " disconnected";
        broadcast(message);
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
