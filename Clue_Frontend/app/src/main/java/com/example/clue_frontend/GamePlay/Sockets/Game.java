package com.example.clue_frontend.GamePlay.Sockets;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.clue_frontend.MyApplication;
import com.example.clue_frontend.R;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class Game extends AppCompatActivity {
    MyApplication APP;
    WebSocketClient CLIENT;

//    private View relativeLayout = findViewById(R.id.relative_layout);
    private SwipeListener swipeListener;
    private ImageView iv;

    Button send;
    EditText message;
    TextView chatBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socket_game);
        send = (Button) findViewById(R.id.button);
        message = (EditText) findViewById(R.id.message);
        chatBox = (TextView) findViewById(R.id.chat_box);
        connectWebSocket();
    }

    private void connectWebSocket() {
        APP = (MyApplication) getApplication();
        Draft[] drafts = {
            new Draft_6455()
        };

//        String w = "ws://echo.websocket.org";
        String w = "ws://10.0.2.2:8080/websocket/game/"+APP.getLobbyid()+"/player/"+APP.getUserid()+"";
        Log.d("Socket", w);
        try {
            Log.d("Socket:", "Trying socket");
            CLIENT = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                    String s = chatBox.getText().toString();
                    chatBox.setText(s + "\nServer: " + message);
                }
                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    Log.d("Exception:", ex.toString());
                }
            };
        } catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }

        CLIENT.connect();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
                    CLIENT.send(message.getText().toString());
//                } catch (Exception e) {
//                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
//                }
            }
        });

    }
}

