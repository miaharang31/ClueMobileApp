package tz_7.WebSocket;

import jakarta.websocket.OnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tz_7.GamePlay.GameLobbyDatabase.GameLobby;
import tz_7.GamePlay.GameLobbyDatabase.GameLobbyRepository;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;


@ServerEndpoint("/websocket/lobby/{id}/player/{player}")
@Component
public class LobbySocket {
    private static Map< Session, Player> sessionPlayerMap = new Hashtable < > ();
    private static Map < Player, Session > playerSessionMap = new Hashtable< >();
    private static PlayerRepository playerRepository;

    private static GameLobbyRepository lobbyRepository;
    @Autowired
    public void setLobbyRepository(GameLobbyRepository lobbyRepository) {

        this.lobbyRepository = lobbyRepository;
    }
    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("player") Integer playerid) throws IOException {
        Player player = playerRepository.findById(playerid).get();
        sessionPlayerMap.put(session, player);
        playerSessionMap.put(player, session);

        String message = player.getUsername() + " has joined";
        broadcast(message);

    }
    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
//        logger.info("Entered Message: " + message);
//        Player player = playerRepository.findByUsername(sessionPlayerMap.get(session).getUsername()).get();
        Player player = sessionPlayerMap.get(session);

        GameLobby lobby = null;
        if (message.startsWith("Joined: ")) {

            Player destPlayer = playerRepository.findById(Integer.valueOf(message.split(" ")[2])).get();
            lobby = lobbyRepository.findById(Integer.valueOf(message.split(" ")[1])).get();
            lobby.addPlayer(player);
            broadcast("Display: " + player.getUsername());
        }
        lobbyRepository.save(lobby);
    }
    @OnClose
    public void onClose(Session session) throws IOException {
        Player player = sessionPlayerMap.get(session);

        sessionPlayerMap.remove(session);
        playerSessionMap.remove(player);
        String message = player.getUsername() + " left the game...";
        broadcast(message);
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
    private void sendMessageToPArticularUser(Player player, String message) {
        try {
            playerSessionMap.get(player).getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @OnClose
//    public void onClose()
}
