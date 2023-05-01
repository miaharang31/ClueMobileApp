package com.example.clue_frontend.GamePlay;

import static com.example.clue_frontend.GamePlay.GameView.player;
import static com.example.clue_frontend.GamePlay.GameView.scarlet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
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
import com.example.clue_frontend.GamePlay.Player.Player;
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
import java.util.Objects;
import java.util.Random;


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

    RequestQueue queue; // = Volley.newRequestQueue(Game.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);


        setContentView(R.layout.board);


//        Dealing with chat
        send = (Button) findViewById(R.id.button);
        message = (EditText) findViewById(R.id.message);
        chatBox = (TextView) findViewById(R.id.chat_box);
//        connectChat();

        connectGame();

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

    public String getCharacter(){
        MyApplication app = (MyApplication) getApplication();
        System.out.println("Line 105, In Game class, in characterSelected method ");

        String url = "http://coms-309-038.class.las.iastate.edu:8080/info/player/role/" + app.getUserid();
//        String url = "http://10.0.2.2:8080/info/player/role/" + app.getUserid();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            characterSelected = response.get("name").toString();
                            System.out.println("Line 117, In Game class, characterSelected: " + characterSelected);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
        return characterSelected;
    }

    private void connectChat() {
        app = (MyApplication) getApplication();
        Draft[] drafts = {
                new Draft_6455()
        };

//        String w = "ws://echo.websocket.org";
        //String w = "ws://10.0.2.2:8080/websocket/chat/"+app.getUserid();
        String w = "ws://coms-309-038.class.las.iastate.edu:8080/websocket/chat/"+app.getUserid();
        Log.d("Chat Socket", w);
        try {
            Log.d("Chat Socket:", "Trying socket");
            chatClient = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                @Override
                public void onMessage(String m) {
                    Log.d("CHAT", "run() returned: " + m);
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
                    Log.d("CHAT OPEN", "run() returned: " + "is connecting");

                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CHAT CLOSE", "onClose() returned: " + reason);
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
        queue = Volley.newRequestQueue(Game.this);
        app = (MyApplication) getApplication();
        Draft[] drafts = {
                new Draft_6455()
        };

        String w = "ws://10.0.2.2:8080/websocket/game/"+app.getGameid()+"/player/"+app.getUserid();
//        String w = "ws://coms-309-038.class.las.iastate.edu:8081/websocket/game/"+app.getGameid()+"/player/"+app.getUserid()+"";
        Log.d("Game Socket", w);
        try {
            Log.d("Game Socket:", "Trying socket");
            gameClient = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                @Override
                public void onMessage(String m) {
                    String url;
                    JsonObjectRequest objectRequest;
                    JsonArrayRequest arrayRequest;
                    Log.d("GAME", "run() returned: " + m);
                    switch (m) {
//                        TODO: Handle message
//                            This will include many messages depending on what the game is doing
                        case "Game Deleted":
//                            Host has closed the game and deleted the game state, make all players leave game
                            Log.d("Websocket", "Game Deleted, returning back to home");
                            Intent intent = new Intent(Game.this, Home.class);
                            startActivity(intent);
                            break;
                        case "Turn Ended":
                            Log.d("Websocket", "Entered Turn Completion");
//                            A player has ended their turn, start turn if its their turn
//                            url = "http://10.0.2.2:8080/info/player/"+app.getUserid();
                            url = "http://coms-309-038.class.las.iastate.edu:8080/info/player/" + app.getUserid();
                            objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                Boolean isTurn = response.getBoolean("turn");
                                                if (isTurn) {
//                                                    String url = "http://10.0.2.2:8080/info/player/"+app.getUserid()+"/role";
                                                    String url = "http://coms-309-038.class.las.iastate.edu:8080/info/player/"+app.getUserid()+"/role";
                                                    System.out.println("line 246, about to do request, url: " + url);
                                                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                                                            new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {
                                                                    try {
                                                                        System.out.println("line 252, about to do playTurn, response.getString(\"name\"): "
                                                                                + response.getString("name"));
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
//                            TODO: move piece of player that just ended the game (might need to add to default) "turnended: x, y"
                            break;
                        case "Game Ended":
//                            TODO: Handle ending screens
                            app.setGameid(0);
                            break;
                        case "Guess":
//                            TODO: Get room player is in
                            String room = null;
                            makeAGuess(room);
                            break;
                        case "Final Guess":
//                            TODO: When player enters center room
//                                --NOTE: Moving into the center room is
//                                    taken care of. IDK how to end the game
//                                      - Prompt final checklist
//                                      - Store chosen cards in array
//                                      - Send request to check if correct
//                                      - End Game
                            break;
                        default:
                            if(m.startsWith(">")) {
//                                Sending a card
                                Log.d("Websocket", "Sending Card: " + m.split(" ")[1] + " " + m.split(" ")[2] + " " + m.split(" ")[3]);
                                giveCard(m.split(" ")[0].substring(1), m.split(" ")[1], m.split(" ")[2], m.split(" ")[3]);
                            } else if(m.startsWith("<")) {
//                                Recieving card
                                Log.d("Websocket", "Recieving Card: " + m.split(" ")[0].substring(1));
                                showCard(Integer.parseInt(m.split(" ")[0].substring(1)));
                            }
                            break;
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
                    Log.d("GAME OPEN", "run() returned: " + "is connecting");
//                    String url = "http://10.0.2.2:8080/game/" + app.getGameid();
                    String url = "http://coms-309-038.class.las.iastate.edu:8080/game/" + app.getGameid();
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
                    queue.add(request);

                    startGame();
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
                    Log.d("GAME CLOSE", "onClose() returned: " + reason);
                    if(app.isHost()) {
//                        String url = "http://10.0.2.2:8080/game/" + app.getGameid() + "/delete";
                        String url = "http://coms-309-038.class.las.iastate.edu:8080/game/" + app.getGameid() + "/delete";
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url, null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        gameClient.send("Game Deleted");
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(Game.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                                        Log.d("ResponseError", error.toString());
                                    }
                                });
                        queue.add(request);
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


    private void startGame(){
        Log.d("Game", "Entered in Start Game");

//        String url = "http://coms-309-038.class.las.iastate.edu:8080/info/player/"+app.getGameid()+"/role";
//        System.out.println("line 246, about to do request, url: " + url);
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            System.out.println("line 252, about to do playTurn, response.getString(\"name\"): "
//                                    + response.getString("name"));
//                            playTurn(response.getString("name"));
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(Game.this, "ERROR [get role]: " + error, Toast.LENGTH_SHORT).show();
//                        Log.d("ResponseError", error.toString());
//                    }
//                });
//        queue.add(request);
        playTurn("scarlet");
    }

    private void playTurn(String role) {
        Log.d("Game", "Entered into player turn. Player: " + role);

        gameClient.send("Turn Ended");
//        TODO: do whatever needs to be done turn wise
//              - Move character:  Already done with the SwipeListener and MoveUp/Down/Left/Right functions in GameView
//              - If in room, make guess: Is this already going to be handled in the guess case, line 285???
//              - else, end turn: Handled below


//        System.out.println("line 392, in playturn, role: " + role);
//        if(Objects.equals(role, "scarlet")){
//            player = new Player(GameView.scarlet, 468, 0, 0);
//            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 3);
//            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 3);
//        } else if (Objects.equals(role, "white")) {
//            player = new Player(GameView.white, 476, 0, 0);
//            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 5);
//            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 3);
//        }else if (Objects.equals(role, "plum")) {
//            player = new Player(GameView.plum, 330, 0, 0);
//            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 4);
//            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 6);
//        }else if (Objects.equals(role, "mustard")) {
//            player = new Player(GameView.white, 476, 0, 0);
//            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 5);
//            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 3);
//        }else if (Objects.equals(role, "green")) {
//            player = new Player(GameView.green, 14, 0, 0);
//            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 3);
//            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 3);
//        }else if (Objects.equals(role, "peacock")) {
//            player = new Player(GameView.peacock, 7, 0, 0);
//            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 3);
//            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 5);
//        }
//
////      ending turn
//        if (GameView.moves == 0){
//            GameView.rand = new Random();
//            GameView.moves = GameView.rand.nextInt(23) + 1;
//            //        Tells the server a player has finished their turn
//            gameClient.send("Turn Ended");
//        }
    }

    private void makeFinalGuess() {
        Log.d("Game", "Entered in Final Guess");
//        TODO: Display final cards checklist, set the chosen cards in an array, check if guess is correct, end the game
        JSONArray finalCards = null;
//      String url = "http://10.0.2.2:8080/game/"+app.getGameid()+"/checkGuess";
        String url = "http://coms-309-038.class.las.iastate.edu:8080/game/" + app.getGameid() + "/checkGuess";
//        TODO: Figure out volley stuff for checkGuess
        gameClient.send("GAME");
    }

    /**
     * Method that when called pulls up the guess checklist
     *  The room will be set to the room the player is in
     *  User will then choose which suspect and weapon to guess
     *  A message will be sent to the server with the names of the three guesses and
     */
    private void makeAGuess(String room) {
        String weapon = null, suspect = null;

//        TODO: Pull up guess checklist
//                Set room to current room
//                let player choose suspect and weapon
//                send server that a guess has been made

        gameClient.send("Guess " + weapon + room + suspect);
    }

    /**
     * Displays card hand, player chooses card to show to the current player whose turn it is
     *  If there are no cards they want to show, they hit a button
     *  if a card is chosen to be show, it sends the server a message with the username of the player to send it to and the id of the chosen card
     *  else it will send the server that they couldnt show a card
     *      the server will choose the next user to run this method
     * @param username
     *  Username of the player who is making the guess
     * @param card1
     *  name of the cards they want
     * @param card2
     *  name of the cards they want
     * @param card3
     *  name of the cards they want
     */
    private void giveCard(String username, String card1, String card2, String card3) {
//        TODO: Pop up card hand, player select card, send id of selected card to server
//                create button to say no cards can be shown
        if("sends card" == "") {
            gameClient.send(">" + username + "id of selected card");
        } else if ("choose no cards button clicked" == "") {
//            TODO: Set on touch listener for button to send this and go back to gane
            gameClient.send("-" + username + card1 + card2 + card3);
        }
    }

    private void showCard(int cardID) {
        gameClient.send("Turn Ended");
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

    public static void checkInRoom(int i, Room element) {
        if(element.getRoom()[i][1] == "clue"){
            //TODO: Add the final guess here and store location as center
            System.out.println("Do the final guess");
        }else if (element.getRoom()[i][1] == "study") {
            //TODO: Add guess here and store location as study
            System.out.println("Do normal guess in study");
        }else if (element.getRoom()[i][1] == "library") {
            //TODO: Add guess here and store location as library
            System.out.println("Do normal guess in library");
        }else if (element.getRoom()[i][1] == "billiard") {
            //TODO: Add guess here and store location as billiard
            System.out.println("Do normal guess in billiard");
        }else if (element.getRoom()[i][1] == "conservatory") {
            //TODO: Add guess here and store location as conservatory
            System.out.println("Do normal guess in conservatory");
        }else if (element.getRoom()[i][1] == "billiard") {
            //TODO: Add guess here and store location as billiard
            System.out.println("Do normal guess in billiard");
        }else if (element.getRoom()[i][1] == "hall") {
            //TODO: Add guess here and store location as hall
            System.out.println("Do normal guess in hall");
        }else if (element.getRoom()[i][1] == "ball") {
            //TODO: Add guess here and store location as ball
            System.out.println("Do normal guess in ball");
        }else if (element.getRoom()[i][1] == "lounge") {
            //TODO: Add guess here:
            System.out.println("Do normal guess in lounge");
        }else if (element.getRoom()[i][1] == "dinning") {
            //TODO: Add guess here:
            System.out.println("Do normal guess in dinning");
        }else if (element.getRoom()[i][1] == "kitchen") {
            //TODO: Add guess here:
            System.out.println("Do normal guess in kitchen");
        }
    }

}

