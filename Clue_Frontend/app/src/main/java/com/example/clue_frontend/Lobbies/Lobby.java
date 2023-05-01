package com.example.clue_frontend.Lobbies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.clue_frontend.GamePlay.Player.CharacterSelection;
import com.example.clue_frontend.GamePlay.Game;
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
import java.net.URL;
import java.util.Iterator;

public class Lobby extends AppCompatActivity {

    HostLobby newLobby;
    Button startGame;
    TextView host;
    TextView player01;
    TextView player02;
    TextView player03;
    TextView player04;
    TextView player05;
    TextView player06;
    CardView player01_box, player02_box, player03_box, player04_box, player05_box, player06_box;
    TextView max;
    TextView cur;
    WebSocketClient lobbyClient;

    JSONObject gameLobby;
    JSONObject gameHost;
    JSONArray players;

    private MyApplication app = (MyApplication) getApplication();
    private WebSocketClient CLIENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        app = (MyApplication) getApplication();

        try {
            connectLobby(); //connects to the lobby
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // startGame = (Button)findViewById(R.id.startGame);
//        host = findViewById(R.id.host);
        startGame = findViewById(R.id.startGame);
        host = findViewById(R.id.hostNameText);
        max = findViewById(R.id.max);
        cur = findViewById(R.id.cur);
        player01 = findViewById(R.id.player_01);
        player02 = findViewById(R.id.player_02);
        player03 = findViewById(R.id.player_03);
        player04 = findViewById(R.id.player_04);
        player05 = findViewById(R.id.player_05);
        player06 = findViewById(R.id.player_06);
        player01_box = findViewById(R.id.Player01);
        player02_box = findViewById(R.id.Player02);
        player03_box = findViewById(R.id.Player03);
        player04_box = findViewById(R.id.Player04);
        player05_box = findViewById(R.id.Player05);
        player06_box = findViewById(R.id.Player06);


        RequestQueue queue = Volley.newRequestQueue(Lobby.this);
        try {
            connectLobby();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
//        app = (MyApplication) getApplication();
//        String url = "http://coms-309-038.class.las.iastate.edu:8080/lobby/" + app.getLobbyid();
////        String url = "http://10.0.2.2:8080/lobby/" + app.getLobbyid();
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d("Response", response.toString());
//                        try {
//                            int gameID = response.getInt("id");
//
//                            max.setText(response.get("maxPlayers").toString());
////                            Get the host to display their name
//                            String url = "http://coms-309-038.class.las.iastate.edu:8080/lobby/host/" + gameID;
////                            String url = "http://10.0.2.2:8080/lobby/host/" + gameID;
//                            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                                    new Response.Listener<JSONObject>() {
//                                        @Override
//                                        public void onResponse(JSONObject response) {
//                                            try {
//                                                app = (MyApplication) getApplication();
//                                                host.setText("Host: " + response.getString("firstname") + " " + response.getString("lastname"));
//                                                if ((Integer)response.get("id") == app.getUserid()) {
//                                                    startGame.setVisibility(View.VISIBLE);
//                                                    startGame.setText("Start Game");
//                                                }
//                                                else {
//                                                    startGame.setVisibility(View.VISIBLE);
//                                                    startGame.setText("Join Game");
//                                                }
//                                            } catch (JSONException e) {
//                                                throw new RuntimeException(e);
//                                            }
//
//                                        }
//                                    },
//                                    new Response.ErrorListener() {
//                                        @Override
//                                        public void onErrorResponse(VolleyError error) {
//                                            Toast.makeText(Lobby.this, "ERROR: " + error, Toast.LENGTH_SHORT);
//                                            Log.d("Error.Response", error.toString());
//                                        }
//                                    });
//                            queue.add(request);
////                                    TODO: FIGURE OUT HOW TO DO DYNAMICALLY
//                            cur.setText(response.get("numPlayers").toString());
////                                    Display names of players in lobby
//                            url = "http://coms-309-038.class.las.iastate.edu:8080/lobby/nothost/" + gameID;
////                                    url = "http://10.0.2.2:8080/lobby/nothost/" + gameID;
//                            JsonArrayRequest request1  = new JsonArrayRequest(Request.Method.GET, url, null,
//                                    new Response.Listener<JSONArray>() {
//                                        @Override
//                                        public void onResponse(JSONArray response) {
//                                            for (int i = 0; i < response.length(); i++) {
//                                                try {
//                                                    switch (i) {
//                                                        case 0:
//                                                            player01_box.setVisibility(View.VISIBLE);
//                                                            player01.setText(response.getJSONObject(i).get("firstname") + " " + response.getJSONObject(i).get("lastname"));
//                                                            break;
//                                                        case 1:
//                                                            player02_box.setVisibility(View.VISIBLE);
//                                                            player02.setText(response.getJSONObject(i).get("firstname") + " " + response.getJSONObject(i).get("lastname"));
//                                                            break;
//                                                        case 2:
//                                                            player03_box.setVisibility(View.VISIBLE);
//                                                            player03.setText(response.getJSONObject(i).get("firstname") + " " + response.getJSONObject(i).get("lastname"));
//                                                            break;
//                                                        case 3:
//                                                            player04_box.setVisibility(View.VISIBLE);
//                                                            player04.setText(response.getJSONObject(i).get("firstname") + " " + response.getJSONObject(i).get("lastname"));
//                                                            break;
//                                                        case 4:
//                                                            player05_box.setVisibility(View.VISIBLE);
//                                                            player05.setText(response.getJSONObject(i).get("firstname") + " " + response.getJSONObject(i).get("lastname"));
//                                                            break;
//                                                        case 5:
//                                                            player06_box.setVisibility(View.VISIBLE);
//                                                            player06.setText(response.getJSONObject(i).get("firstname") + " " + response.getJSONObject(i).get("lastname"));
//                                                            break;
//
//                                                    }
//                                                } catch (JSONException e) {
//                                                    throw new RuntimeException(e);
//                                                }
//                                            }
//                                        }
//                                    },
//                                    new Response.ErrorListener() {
//                                        @Override
//                                        public void onErrorResponse(VolleyError error) {
//                                            Toast.makeText(Lobby.this, "ERROR: " + error, Toast.LENGTH_SHORT);
//                                            Log.d("Error.Response", error.toString());
//                                        }
//                                    });
//                            queue.add(request1);
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(Lobby.this, "ERROR: " + error, Toast.LENGTH_SHORT);
//                        Log.d("Error.Response", error.toString());
//                    }
//                }
//        );
//        queue.add(request);

        startGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Lobby.this, CharacterSelection.class);

                if(app.isHost()) {
                    String url = "http://10.0.2.2:8080/game/new/lobby/" + app.getLobbyid();
//                    String url = "http://coms-309-038.class.las.iastate.edu:8080/game/new/lobby/" + app.getLobbyid();
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        app.setGameid((int) response.get("id"));
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Lobby.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                                    Log.d("ResponseError", error.toString());
                                }
                            });
                    queue.add(request);
                }
                startActivity(intent);
            }

        });
    }
    private void connectLobby() throws URISyntaxException {
        app = (MyApplication) getApplication();
        Draft[] drafts = {
                new Draft_6455()
        };

//        String w = "ws://coms-309-038.class.las.iastate.edu:8080/websocket/lobby/" + app.getLobbyid() + "/player" + app.getUserid();
        String w = "ws://10.0.2.2:8080/websocket/lobby/" + app.getLobbyid() + "/player/" + app.getUserid();
        Log.d("Socket", w);
        try {
            Log.d("Socket:", "Trying socket");
            lobbyClient = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.d("Socket:", "On open socket");
                    //volleyrequest to display the lobby (as done above) so that it gets all the current users and displays them


                    RequestQueue queue = Volley.newRequestQueue(Lobby.this);
                    app = (MyApplication) getApplication();
//                    String url = "http://coms-309-038.class.las.iastate.edu:8080/lobby/" + app.getLobbyid();
                    String url = "http://10.0.2.2:8080/lobby/" + app.getLobbyid();
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("Response", response.toString());
                                    try {
                                        int gameID = response.getInt("id");

                                        max.setText(response.get("maxPlayers").toString());
//                            Get the host to display their name
//                                        String url = "http://coms-309-038.class.las.iastate.edu:8080/lobby/host/" + gameID;
                                        String url = "http://10.0.2.2:8080/lobby/host/" + gameID;
                                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                                                new Response.Listener<JSONObject>() {
                                                    @Override
                                                    public void onResponse(JSONObject response) {
                                                        try {
                                                            app = (MyApplication) getApplication();
                                                            host.setText("Host: " + response.getString("firstname") + " " + response.getString("lastname"));
                                                            if ((Integer)response.get("id") == app.getUserid()) {
                                                                startGame.setVisibility(View.VISIBLE);
                                                                startGame.setText("Start Game");
                                                            }
                                                            else {
                                                                startGame.setVisibility(View.VISIBLE);
                                                                startGame.setText("Join Game");
                                                            }
                                                        } catch (JSONException e) {
                                                            throw new RuntimeException(e);
                                                        }

                                                    }
                                                },
                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        Toast.makeText(Lobby.this, "ERROR: " + error, Toast.LENGTH_SHORT);
                                                        Log.d("Error.Response", error.toString());
                                                    }
                                                });
                                        queue.add(request);
//                                    TODO: FIGURE OUT HOW TO DO DYNAMICALLY


                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Lobby.this, "ERROR: " + error, Toast.LENGTH_SHORT);
                                    Log.d("Error.Response", error.toString());
                                }
                            }
                    );
                    queue.add(request);
//
//                    if (!app.isHost()) {
                        //if player joins lobby
                        lobbyClient.send("Joined: " + app.getLobbyid() + " " + app.getUserid());

//                    }

                }  // end of OnOpen

                @Override
                public void onMessage(String message) {
                    Log.d("onMessage", "In onMessage");
                    app = (MyApplication) getApplication();

                    if(message.startsWith("Display: ")) {

                        int num = app.getUsersplaying();
                        app.setUsersplaying(num + 1);
                        cur.setText(app.getUsersplaying());
                        String userN = message.split(" ")[1];
                        System.out.println(userN);
                        if (player01.getText().equals(null)) {
                            player01_box.setVisibility(View.VISIBLE);
                            player01.setText(userN);
                        }
                        else if (player02.getText().equals(null)) {
                            player02_box.setVisibility(View.VISIBLE);
                            player02.setText(userN);
                        }
                        else if (player03.getText().equals(null)) {
                            player03_box.setVisibility(View.VISIBLE);
                            player03.setText(userN);
                        }
                        else if (player04.getText().equals(null)) {
                            player04_box.setVisibility(View.VISIBLE);
                            player04.setText(userN);
                        }
                        else if (player05.getText().equals(null)) {
                            player05_box.setVisibility(View.VISIBLE);
                            player05.setText(userN);
                        }
                        else if (player06.getText().equals(null)) {
                            player06_box.setVisibility(View.VISIBLE);
                            player06.setText(userN);
                        }

                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {

                }

                @Override
                public void onError(Exception ex) {

                }
            };
        } catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
        lobbyClient.connect();
    }
}
