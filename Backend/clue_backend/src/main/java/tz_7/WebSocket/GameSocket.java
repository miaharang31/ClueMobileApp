package tz_7.WebSocket;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;
import tz_7.GamePlay.GameLobbyDatabase.GameLobbyController;
import tz_7.GamePlay.GameLobbyDatabase.GameLobbyRepository;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;
import tz_7.GamePlay.GameStateDatabase.GameState;
import tz_7.GamePlay.GameStateDatabase.GameStateRepository;
import tz_7.GamePlay.GameStateDatabase.GameStateController;

@ServerEndpoint("/websocket/game/{lobbyID}/player/{player}")
@Component
public class GameSocket extends GameStateController{
    private static Map< Session, Player> sessionPlayerMap = new Hashtable < > ();
    private static Map < Player, Session > playerSessionMap = new Hashtable< >();
    private static Map < Player, GameState > playerGameStateMap = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(GameSocket.class);

    private final PlayerRepository playerRepository;
    private final GameStateRepository gameStateRepository;

    private Session session;
    private final GameLobbyRepository gameLobbyRepository;

    public GameSocket(PlayerRepository playerRepository, GameStateRepository gameStateRepository,
                      GameLobbyRepository gameLobbyRepository) {
        this.playerRepository = playerRepository;
        this.gameStateRepository = gameStateRepository;
        this.gameLobbyRepository = gameLobbyRepository;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("player") Integer playerid, @PathParam("lobbyID") Integer lobbyID) throws IOException {
        logger.info("Entered into Open");

        Player player = playerRepository.findById(playerid).get();
        GameLobby lobby = gameLobbyRepository.findById(lobbyID).get();
        GameState state = gameStateRepository.findByHostID(lobby.getHost().getId());
        if(lobby.getHost() == player) {
            state = newSocketState(lobby);

            sessionPlayerMap.put(session, player);
            playerSessionMap.put(player, session);
            playerGameStateMap.put(player, state);
        } else if (state != null) {
            sessionPlayerMap.put(session, player);
            playerSessionMap.put(player, session);
            playerGameStateMap.put(player, state);

            String message = player.getUsername() + "had joined the game";
            broadcast(message);
        }
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        logger.info("Entered Message: " + message);
        Player user = playerRepository.findByUsername(sessionPlayerMap.get(session).getUsername()).get();

        if(message.startsWith("@")) {
            Player destUser = playerRepository.findByUsername(message.split(" ")[0].substring(1)).get();
            sendMessageToPArticularUser(destUser, "[DM] " + user + ": " + message);
            sendMessageToPArticularUser(user, "[DM] " + user + ": " + message);
        } else {
            broadcast(user.getUsername() + ": " + message);
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        Player player = sessionPlayerMap.get(session);
        GameState state = playerGameStateMap.get(player);

        sessionPlayerMap.remove(session);
        playerSessionMap.remove(player);
        playerGameStateMap.remove(player);

        String message = player.getUsername() + " disconnected";
        broadcast(message);
    }

    private void sendMessageToPArticularUser(Player player, String message) {
        try {
            playerSessionMap.get(player).getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void broadcast(String message) {
        sessionPlayerMap.forEach((session, username) -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
