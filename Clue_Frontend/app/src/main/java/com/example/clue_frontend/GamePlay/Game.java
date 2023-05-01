package com.example.clue_frontend.GamePlay;

import static com.example.clue_frontend.GamePlay.GameView.player;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.clue_frontend.GamePlay.Player.playerGuess;
import com.example.clue_frontend.HomeActivities.Home;
import com.example.clue_frontend.MainActivity;
import com.example.clue_frontend.MyApplication;

import com.example.clue_frontend.R;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;


public class Game extends AppCompatActivity {

    View relativeLayout;
    SwipeListener swipeListener;
    static String characterSelected;

    ImageView iv, imageView;
    MyApplication app;
    WebSocketClient chatClient, gameClient;

    Button send;
    EditText message;
    TextView chatBox;

    JSONObject gameState = new JSONObject();

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Line 37, In Game class");
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constraints.SCREEN_WIDTH = dm.widthPixels;
        Constraints.SCREEN_HEIGHT = dm.heightPixels;

        relativeLayout = findViewById(R.id.relative_layout);
        swipeListener = new SwipeListener(relativeLayout);

        setContentView(R.layout.board);
        queue = Volley.newRequestQueue(Game.this);

//        Dealing with chat
        send = (Button) findViewById(R.id.button);
        message = (EditText) findViewById(R.id.message);
        chatBox = (TextView) findViewById(R.id.chat_box);
        connectChat();

//        connectGame();

        relativeLayout = findViewById(R.id.relative_layout);
        swipeListener = new SwipeListener(relativeLayout);
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

    private void connectChat() {
        app = (MyApplication) getApplication();
        Draft[] drafts = {
                new Draft_6455()
        };

//        String w = "ws://echo.websocket.org";
        String w = "ws://10.0.2.2:8080/websocket/chat/"+app.getUserid();
        Log.d("Socket", w);
        try {
            Log.d("Socket:", "Trying socket");
            chatClient = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                @Override
                public void onMessage(String m) {
                    Log.d("", "run() returned: " + m);
                    message.getText().clear();
                    String s = chatBox.getText().toString();
                    chatBox.setText(s + m + "\n");
                    final int scrollAmount = chatBox.getLayout().getLineTop(chatBox.getLineCount()) - chatBox.getHeight();
                    if (scrollAmount > 0)
                        chatBox.scrollTo(0, scrollAmount);
                    else {
                        chatBox.scrollTo(0, 0);
                    }
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

        chatClient.connect();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    chatClient.send(message.getText().toString());
                } catch (Exception e) {
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }
            }
        });
    }

    private void connectGame() {
        app = (MyApplication) getApplication();
        Draft[] drafts = {
                new Draft_6455()
        };

        String w = "ws://10.0.2.2:8080/websocket/game/"+app.getGameid()+"/player/"+app.getUserid()+"";
        Log.d("Socket", w);
        try {
            Log.d("Socket:", "Trying socket");
            chatClient = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                @Override
                public void onMessage(String m) {
                    String url;
                    JsonObjectRequest objectRequest;
                    JsonArrayRequest arrayRequest;
                    Log.d("", "run() returned: " + m);
                    switch (m) {
//                        TODO: Handle message
//                            This will include many messages depending on what the game is doing
                        case "Game Deleted":
//                            Host has closed the game and deleted the game state, make all players leave game
                            Intent intent = new Intent(Game.this, Home.class);
                            startActivity(intent);
                            break;
                        case "Turn Ended" :
//                            A player has ended their turn, start turn if its their turn
                            url = "http://10.0.2.2:8080/info/player/"+app.getUserid();
                            objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                Boolean isTurn = response.getBoolean("turn");
                                                if(isTurn) {
                                                    String url = "http://10.0.2.2:8080/info/player/"+app.getUserid()+"/role";
                                                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                                                            new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {
                                                                    try {
                                                                        playTurn(response.getString("name"));
                                                                    } catch (JSONException e) {
                                                                        throw new RuntimeException(e);
                                                                    }
                                                                }
                                                            },
                                                            new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError error) {
                                                                    Toast.makeText(Game.this, "ERROR [get role]: " + error, Toast.LENGTH_SHORT).show();
                                                                    Log.d("ResponseError", error.toString());
                                                                }
                                                            });
                                                    queue.add(request);
                                                }
                                            } catch (JSONException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(Game.this, "ERROR [get info]: " + error, Toast.LENGTH_SHORT).show();
                                            Log.d("ResponseError", error.toString());
                                        }
                                    });
                            queue.add(objectRequest);
//                            TODO: idk yet i just feel like there should be something here (move piece?? that might be a different message)
                            break;
                        case "Show Card":
//                            TODO: pick card to show
//                             figure out how its getting back to other user
//                                IS DIFFERENT ON SERVER DON'T MESS WITH IT
                            break;
                        case "Game Ended":
//                            TODO: Handle ending screens
                            app.setGameid(0);
                            break;
                        case "Guess":
//                            TODO: When player enters room, make guess
                            break;
                        case "Final Guess":
//                            TODO: When player enters center room
//                                      - Prompt final checklist
//                                      - Store chosen cards in array
//                                      - Send request to check if correct
//                                      - End Game
                            JSONArray finalCards = null;
                            url = "http://10.0.2.2:8080/game/"+app.getGameid()+"/checkGuess";
                            break;
                        default:
//                            TODO: idk what to put here ngl
//                      TODO: Think of other things happening in the game
                    }
                }

                /**
                 * Gets the game state of the player
                 * @param handshake
                 *  The handshake of the websocket instance
                 */
                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                    String url = "http://10.0.2.2:8080/game/" + app.getGameid();
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    gameState = response;
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Game.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                                    Log.d("ResponseError", error.toString());
                                }
                            });
                }

                /**
                 * If the host disconnects, deletes the games state
                 * @param code
                 * @param reason
                 *  Additional information string
                 * @param remote
                 *  Returns whether or not the closing of the connection was initiated by the remote host.
                 */
                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                    if(app.isHost()) {
                        String url = "http://10.0.2.2:8080/game/" + app.getGameid() + "/delete";
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url, null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
//                                        TODO: Handle response
                                        sendMessage(gameClient, "Game Deleted");
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(Game.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                                        Log.d("ResponseError", error.toString());
                                    }
                                });
                    }
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

        gameClient.connect();
    }

    private void playTurn(String role) {
//        TODO: do whatever needs to be done turn wise

//        Tells the server a player has finished their turn
        sendMessage(gameClient, "Turn Ended");
    }

    private void sendMessage(WebSocketClient client, String message) {
        client.send(message);
    }

    public class SwipeListener implements View.OnTouchListener {
        GestureDetector gestureDetector;

        SwipeListener(View view) {
            int threshold = 100;
            int velocity_threshold = 100;

            GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                    float xDiff = e2.getX() - e1.getX();
                    float yDiff = e2.getY() - e1.getY();

                    try {
                        System.out.println("Line 101, In Game class, in try block");
                        if(GameView.moves > 0){
                            if (Math.abs(xDiff) > Math.abs(yDiff)) {
                                if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold) {
                                    if (xDiff > 0) {
                                        //Swiped right
                                        try {
                                            if ((GameView.arrBoard.get(player.getPlacement() + 1).getBm() != GameView.edge) && (player.getPlacement() % 23 != 22)) {
                                                GameView.TurnRight();
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    } else {
                                        //Swiped left
                                        try {
                                            if ((GameView.arrBoard.get(player.getPlacement() - 1).getBm() != GameView.edge) && (player.getPlacement() % 23 != 0)) {
                                                GameView.TurnLeft();
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                    return true;
                                }
                            } else {
                                if (Math.abs(yDiff) > threshold && Math.abs(velocityY) > velocity_threshold) {

                                    if (yDiff > 0) {
                                        //Swiped down
                                        try {
                                            if(GameView.arrBoard.get(player.getPlacement() + 22).getBm() != GameView.edge && player.getPlacement() < 462){
                                                GameView.MoveDown();
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }else {
                                        //Swiped up
                                        try {
                                            if(GameView.arrBoard.get(player.getPlacement() - 22).getBm() != GameView.edge && player.getPlacement() > 22){
                                                GameView.MoveUp();
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                    return true;
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return false;
                }
            };
            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
    }

}


