package tz_7.WebSocket;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import jakarta.transaction.Transactional;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;
import tz_7.GamePlay.GameLobbyDatabase.GameLobbyController;
import tz_7.GamePlay.GameLobbyDatabase.GameLobbyRepository;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerController;
import tz_7.PlayerDatabase.PlayerRepository;
import tz_7.GamePlay.GameStateDatabase.GameState;
import tz_7.GamePlay.GameStateDatabase.GameStateRepository;
import tz_7.GamePlay.GameStateDatabase.GameStateController;

@ServerEndpoint("/websocket/game/{lobbyID}/player/{player}")
@Controller
public class GameSocket {
    private static Map< Session, Player> sessionPlayerMap = new Hashtable < > ();
    private static Map < Player, Session > playerSessionMap = new Hashtable< >();
    private static Map < Player, GameState > playerGameStateMap = new Hashtable<>();

    private static PlayerRepository playerRepository;
    private static GameStateRepository gameStateRepository;
    private static GameLobbyRepository gameLobbyRepository;
//    private static GameStateController gameStateController;

    private final Logger logger = LoggerFactory.getLogger(GameSocket.class);

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
    public void onOpen(Session session, @PathParam("player") Integer playerid, @PathParam("lobbyID") Integer lobbyID)
    throws IOException {
        logger.info("Entered into Open");

        Player player = playerRepository.findById(playerid).get();
        GameLobby lobby = gameLobbyRepository.findById(lobbyID).get();

//        GameState state = gameStateRepository.findByHostID(lobby.getHost().getId());

//        GameState state = gameStateController.newSocketState(lobby);

        sessionPlayerMap.put(session, player);
        playerSessionMap.put(player, session);
//        playerGameStateMap.put(player, state);

        String message = player.getUsername() + "has joined the game";
        broadcast(message);
//        if(lobby.getHost() == player) {
//
//        } else if (state != null) {
//            sessionPlayerMap.put(session, player);
//            playerSessionMap.put(player, session);
//            playerGameStateMap.put(player, state);
//
//            String message = player.getUsername() + "had joined the game";
//            broadcast(message);
//        }
    }

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

    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        Player player = sessionPlayerMap.get(session);
//        GameState state = playerGameStateMap.get(player);

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
        sessionPlayerMap.forEach((session, player) -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
