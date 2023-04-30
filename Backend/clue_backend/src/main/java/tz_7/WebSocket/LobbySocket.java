package tz_7.WebSocket;

import org.springframework.stereotype.Component;
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
    private final PlayerRepository playerRepository;

    public LobbySocket(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("player") Integer playerid) throws IOException {
        Player player = playerRepository.findById(playerid).get();
        sessionPlayerMap.put(session, player);
        playerSessionMap.put(player, session);

    }

//    @OnClose
//    public void onClose()
}