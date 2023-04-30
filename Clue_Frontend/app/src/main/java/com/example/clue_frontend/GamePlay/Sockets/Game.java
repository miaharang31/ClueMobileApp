package com.example.clue_frontend.GamePlay.Sockets;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clue_frontend.GamePlay.CardLayout;
import com.example.clue_frontend.GamePlay.Constraints;
import com.example.clue_frontend.GamePlay.Player.playerGuess;
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

    private View relativeLayout = findViewById(R.id.relative_layout);
    private SwipeListener swipeListener;
    private ImageView iv;

    ImageView imageView;
    Button send;
    EditText message;
    TextView chatBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constraints.SCREEN_WIDTH = dm.widthPixels;
        Constraints.SCREEN_HEIGHT = dm.heightPixels;



        setContentView(R.layout.socket_game);

        relativeLayout = findViewById(R.id.relative_layout);
        swipeListener = new SwipeListener(relativeLayout);
        send = (Button) findViewById(R.id.button);
        message = (EditText) findViewById(R.id.message);
        chatBox = (TextView) findViewById(R.id.chat_box);
        connectWebSocket();

        relativeLayout = findViewById(R.id.relative_layout);
        swipeListener = new com.example.clue_frontend.GamePlay.Sockets.SwipeListener(relativeLayout);
        iv = findViewById(R.id.open_checklist);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game.this, playerGuess.class);
                startActivity(intent);
            }
        });

        imageView = findViewById(R.id.open_cards);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, CardLayout.class);
                startActivity(intent);
            }
        });
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
                    chatBox.setText(s + message + "\n");

                    final int scrollAmount = chatBox.getLayout().getLineTop(chatBox.getLineCount()) - (chatBox.getHeight() + 100);
                    // if there is no need to scroll, scrollAmount will be <=0
                    if (scrollAmount > 0)
                        chatBox.scrollTo(0, scrollAmount);
                    else
                        chatBox.scrollTo(0, 0);
                }
                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                    if(APP.isHost()) {

                    }
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
                try {
                    CLIENT.send(message.getText().toString());
                } catch (Exception e) {
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }
            }
        });

    }
}

//class Test extends AppCompatActivity{
//    public Test() {
//        MyApplication app = (MyApplication) getApplication();
////        String url = "http://10.0.2.2:8080/";
//        String url = "http://coms-309-038.class.las.iastate.edu:8080/allUsers";
//
//        RequestQueue queue = Volley.newRequestQueue(Test.this);
//
//        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
////                        TODO: displaying all user
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
////                        TODO: TOAST
//                    }
//                });
//
//        queue.add(request);
//
//    }
//}

