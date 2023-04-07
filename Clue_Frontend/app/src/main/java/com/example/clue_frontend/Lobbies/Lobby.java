package com.example.clue_frontend.Lobbies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.clue_frontend.GamePlay.StartGame;
import com.example.clue_frontend.MyApplication;
import com.example.clue_frontend.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    TextView max;
    TextView cur;

    JSONObject gameLobby;
    JSONObject gameHost;
    JSONArray players;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

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

        RequestQueue queue = Volley.newRequestQueue(Lobby.this);
        MyApplication app = (MyApplication) getApplication();
        String url = "http://coms-309-038.class.las.iastate.edu:8080/lobby/" + app.getGameid();
//        String url = "http://10.0.2.2:8080/lobby/" + app.getGameid();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                        try {
                            int gameID = response.getInt("id");
                            max.setText(response.get("maxPlayers").toString());
                            String url = "http://coms-309-038.class.las.iastate.edu:8080/lobby/host." + gameID;
//                            String url = "http://10.0.2.2:8080/lobby/host/" + gameID;
                            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                host.setText(response.getString("firstname") + " " + response.getString("lastname"));
                                            } catch (JSONException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
//                                            TODO: HANDLE ERROR RESPONSE
                                        }
                                    });
                                    queue.add(request);
//                                    TODO: FIGURE OUT HOW TO DO DYNAMICALLY
                                    cur.setText(response.get("numPlayers").toString());
                                    url = "http://coms-309-038.class.las.iastate.edu:8080/lobby/players/" + gameID;
//                                    url = "http://10.0.2.2:8080/lobby/players/" + gameID;
                                    JsonArrayRequest request1  = new JsonArrayRequest(Request.Method.GET, url, null,
                                            new Response.Listener<JSONArray>() {
                                                @Override
                                                public void onResponse(JSONArray response) {
                                                    for (int i = 0; i < response.length(); i++) {
                                                        try {
                                                            switch (i) {
                                                                case 0:
                                                                    player01.setVisibility(View.VISIBLE);
                                                                    player01.setText(response.getJSONObject(i).get("firstname") + " " + response.getJSONObject(i).get("lastname"));
                                                                    break;
                                                                case 1:
                                                                    player02.setVisibility(View.VISIBLE);
                                                                    player02.setText(response.getJSONObject(i).get("firstname") + " " + response.getJSONObject(i).get("lastname"));
                                                                    break;
                                                                case 2:
                                                                    player03.setVisibility(View.VISIBLE);
                                                                    player03.setText(response.getJSONObject(i).get("firstname") + " " + response.getJSONObject(i).get("lastname"));
                                                                    break;
                                                                case 3:
                                                                    player04.setVisibility(View.VISIBLE);
                                                                    player04.setText(response.getJSONObject(i).get("firstname") + " " + response.getJSONObject(i).get("lastname"));
                                                                    break;
                                                                case 4:
                                                                    player05.setVisibility(View.VISIBLE);
                                                                    player05.setText(response.getJSONObject(i).get("firstname") + " " + response.getJSONObject(i).get("lastname"));
                                                                    break;
                                                                case 5:
                                                                    player01.setVisibility(View.VISIBLE);
                                                                    player06.setText(response.getJSONObject(i).get("firstname") + " " + response.getJSONObject(i).get("lastname"));
                                                                    break;

                                                            }
                                                        } catch (JSONException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    }
                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            });
                                        queue.add(request1);
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

        startGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                TODO: create game state
//                String url = "http://10.0.2.2:8080/";
                Intent intent = new Intent(Lobby.this, StartGame.class);
                startActivity(intent);
            }
        });
    }


}