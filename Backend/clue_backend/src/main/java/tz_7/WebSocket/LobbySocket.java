package tz_7.WebSocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@ServerEndpoint("/websocket/{name}")
@Component
public class LobbySocket {
    private static Map< Session, String > sessionUsernameMap = new Hashtable < > ();
    private static Map < String, Session > usernameSessionMap = new Hashtable< >();

    @OnOpen
    public void onOpen(Session session, @PathParam("name") String name) throws IOException {
        sessionUsernameMap.put(session, name);
        usernameSessionMap.put(name, session);

    }
}
