package tz_7.WebSocket;

import org.springframework.stereotype.Component;
import tz_7.PlayerDatabase.Player;
import tz_7.PlayerDatabase.PlayerRepository;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@ServerEndpoint("/websocket/lobby/{id}/player/{player}")
@Component
public class GameSocket {
    private static Map< Session, Player> sessionPlayerMap = new Hashtable < > ();
    private static Map < Player, Session > playerSessionMap = new Hashtable< >();
    private final PlayerRepository playerRepository;

    public GameSocket(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("player") Integer playerid) throws IOException {
        Player player = playerRepository.findById(playerid).get();
        sessionPlayerMap.put(session, player);
        playerSessionMap.put(player, session);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        Player user = playerRepository.findByUsername(sessionPlayerMap.get(session).getUsername()).get();

        if(message.startsWith("@")) {
            Player destUser = playerRepository.findByUsername(message.split(" ")[0].substring(1)).get();
            sendMessageToPArticularUser(destUser, "[DM] " + user + ": " + message);
            sendMessageToPArticularUser(user, "[DM] " + user + ": " + message);
        } else {

        }
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
//    @OnClose
//    public void onClose()
}
