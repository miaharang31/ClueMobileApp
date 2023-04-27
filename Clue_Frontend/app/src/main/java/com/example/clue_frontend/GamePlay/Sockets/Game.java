package com.example.clue_frontend.GamePlay.Sockets;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.clue_frontend.MyApplication;
import com.example.clue_frontend.R;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class Game extends AppCompatActivity {
    final MyApplication APP = (MyApplication) getApplication();
    private WebSocketClient CLIENT;

    private View relativeLayout = findViewById(R.id.relative_layout);
    private SwipeListener swipeListener;
    private ImageView iv;

    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://10.0.2.2:8080/websocket/game/" + APP.getGameid() + "/player/" + APP.getUserid());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        CLIENT = new WebSocketClient(uri) {

            @Override
            public void onOpen(ServerHandshake handshakedata) {

            }

            @Override
            public void onMessage(String message) {

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {

            }

            @Override
            public void onError(Exception ex) {

            }
        };
        CLIENT.connect();
    }
}

