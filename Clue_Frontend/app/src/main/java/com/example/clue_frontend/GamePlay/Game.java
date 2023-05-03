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
import com.example.clue_frontend.GamePlay.Player.EndGameWinOrLose;
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

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class Game extends AppCompatActivity {

    View relativeLayout;
    SwipeListener swipeListener;

    ImageView iv, imageView;
    MyApplication app;
    WebSocketClient chatClient, gameClient;

    Button send, roll;
    EditText message;
    TextView chatBox, roll_num;

    JSONObject gameState = new JSONObject();

    ArrayList<Integer> turn_order = new ArrayList<>();
    int turn = 0;

    RequestQueue queue; // = Volley.newRequestQueue(Game.this);

    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MyApplication app = (MyApplication) getApplication();
        if(app.getUserid() == 1) {
            app.setInfoid(202);
        } else {
            app.setInfoid(203);
        }
        app.setGameid(98);

        System.out.println("Game: " + app.getGameid() + "\nInfo: " + app.getInfoid() + "\nUser: " + app.getUserid());

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);

        System.out.println("line 82 in game, About to start game");
        setupGame();
        System.out.println("line 84 in game, passed start game");

        setContentView(R.layout.board);

//        Dealing with chat
        send = (Button) findViewById(R.id.button);
        message = (EditText) findViewById(R.id.message);
        chatBox = (TextView) findViewById(R.id.chat_box);

        roll = (Button) findViewById(R.id.roll_button);
        roll_num = (TextView) findViewById(R.id.roll_num);

        roll.setVisibility(View.GONE);
        roll_num.setVisibility(View.GONE);

        //Uncomment soon
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
//      Uncomment soon

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

        try {
            Log.d("Game Socket:", "Trying socket");
            gameClient = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                @Override
                public void onMessage(String m) {
                    String url;
                    JsonObjectRequest objectRequest;
                    JsonArrayRequest arrayRequest;
                    Intent intent;
                    Log.d("GAME", "run() returned: " + m);
                    switch (m) {
                        case "Game Deleted":
//                            Host has closed the game or something went wrong and the gamestate was deleted, make all players leave game
                            Log.d("Websocket", "Game Deleted, returning back to home");
                            if (app.getGameid() != 0) {app.setGameid(0);}
                            intent = new Intent(Game.this, Home.class);
                            startActivity(intent);
                            break;
                        case "Turn Ended":
                            Log.d("Websocket", "Entered Turn Completion");
//                            TODO: move piece of player that just ended the game (might need to add to default) "turnended: x, y"
                            playTurn();
                            break;
                        case "Game Ended":
                            intent = new Intent(Game.this, EndGameWinOrLose.class);
//                            TODO: Handle ending screens
                            app.setGameid(0);
                            gameClient.close();
                            startActivity(intent);
                            break;
                        case "Guess":
//                            TODO: Get room player is in
                            String room = null;
                            makeAGuess(room);
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
                            } else if(m.startsWith("Turn Ended")) { //m = Turn Ended <player role> x, y
//                                TODO: Move piece to that x and y coord
//                                    Probably need something to save the coords (db?)

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
                    Log.d("GAME OPEN: ", "()loading");
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
//                    When the connection is closed, if the gameID has been reset, return home
                    if(app.getGameid() == 0) {
                        Intent intent = new Intent(Game.this, Home.class);
                        startActivity(intent);
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


    private void setupGame(){
        Log.d("Game", "Setting up game");
        app = (MyApplication) getApplication();
        queue = Volley.newRequestQueue(Game.this);

//        Getting the game state object and setting basics
//        String url = "http://10.0.2.2:8080/game/" + app.getGameid();
////        String url = "http://coms-309-038.class.las.iastate.edu:8080/game/" + app.getGameid();
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            Log.d("Game", "in response");
//                            JSONArray arr =  response.getJSONArray("turnOrder");
//                            GameView.number_of_players = arr.length();
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast .makeText(Game.this, "Error: " + error, Toast.LENGTH_SHORT).show();
//                        Log.d("ResponseError", error.toString());
//                    }
//                });
//        queue.add(request);
        GameView.number_of_players = 1;
        Log.d("Game", "Finished setting up game");
    }

    private void playTurn() {
        Log.d("Game", "Entered into player turn.");
        queue = Volley.newRequestQueue(Game.this);
//        String url = "http://10.0.2.2:8080/info/player/" + app.getUserid();
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
////                        Checks if it is that player's turn, if it is then it will show the roll button and start the turn process
//                        try {
//                            if((boolean) response.get("turn")) {
                                roll.setVisibility(View.VISIBLE); //showing the roll button
//                                String role = response.getString("role");
                                    String role = "scarlet";
                                roll.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        GameView.roll();
                                        roll_num.setText("Moves: " + GameView.moves);
                                        roll_num.setVisibility(View.VISIBLE); //showing the roll number
                                        movePlayer(role);
                                        if(true) { //TODO: if in room, make a guess
                                            makeAGuess("room");
                                        } else if (false) { //TODO: if in final room
                                            makeFinalGuess();
                                        }
                                    }
                                });

                                roll.setVisibility(View.GONE); //making the roll button disappear
                                roll_num.setVisibility(View.GONE); //making the roll button disappear
//                            }
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//        queue.add(request);

        Log.d("Game", "Exiting player turn.");
    }
    public void movePlayer(String role) {
//        Drawing players
        if(Objects.equals(role, "scarlet")){
            player = new Player(GameView.scarlet, 468, 0, 0);
            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 3);
            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 3);
        } else if (Objects.equals(role, "white")) {
            player = new Player(GameView.white, 476, 0, 0);
            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 5);
            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 3);
        }else if (Objects.equals(role, "plum")) {
            player = new Player(GameView.plum, 330, 0, 0);
            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 4);
            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 6);
        }else if (Objects.equals(role, "mustard")) {
            player = new Player(GameView.white, 476, 0, 0);
            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 5);
            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 3);
        }else if (Objects.equals(role, "green")) {
            player = new Player(GameView.green, 14, 0, 0);
            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 3);
            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 3);
        }else if (Objects.equals(role, "peacock")) {
            player = new Player(GameView.peacock, 7, 0, 0);
            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 3);
            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 5);
        }
    }

    public void endTurn() {
        int tmp = turn + 1;
        if(tmp >= turn_order.size()) {
            tmp = 0;
        }
        turn = tmp;
        roll.setVisibility(View.GONE);
        roll.setText("ROLL");
        gameClient.send("Turn Ended");
    }
    private void makeFinalGuess() {
        Log.d("Game", "Entered in Final Guess");
//        TODO: Display final cards checklist, set the chosen cards in an array, check if guess is correct, end the game
        JSONArray finalCards = null;
//      String url = "http://10.0.2.2:8080/game/"+app.getGameid()+"/checkGuess";
        String url = "http://coms-309-038.class.las.iastate.edu:8080/game/" + app.getGameid() + "/checkGuess";
//        TODO: Figure out volley stuff for checkGuess


//        Delete's the game state once a player makes a final guess,
//        url = "http://10.0.2.2:8080/game/" + app.getGameid() + "/delete";
        url = "http://coms-309-038.class.las.iastate.edu:8080/game/" + app.getGameid() + "/delete";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        gameClient.send("Game Deleted");
                        app.setGameid(0);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast .makeText(Game.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                        Log.d("ResponseError", error.toString());
                    }
                });
        queue.add(request);
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

