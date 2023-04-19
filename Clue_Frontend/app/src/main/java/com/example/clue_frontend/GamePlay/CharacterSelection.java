package com.example.clue_frontend.GamePlay;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.clue_frontend.Lobbies.Lobby;
import com.example.clue_frontend.MyApplication;

import com.example.clue_frontend.R;

import org.json.JSONException;
import org.json.JSONObject;

public class CharacterSelection extends AppCompatActivity {

    Button mustard;
    Button plum;
    Button green;
    Button peacock;
    Button scarlet;
    Button white;
    Button startGame;
    int totalPlayers;
    int numPlayers;
    String chosenChar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        mustard = findViewById(R.id.mustardSelect);
        plum = findViewById(R.id.plumSelect);
        green = findViewById(R.id.greenSelect);
        peacock = findViewById(R.id.peacockSelect);
        scarlet = findViewById(R.id.scarletSelect);
        white = findViewById(R.id.whiteSelect);
        startGame = findViewById(R.id.gameStart);

        mustard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mustard.setClickable(false);
                clicked(mustard);
                numPlayers++;
                chosenChar = "mustard";
            }
        });

        plum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                plum.setClickable(false);
                clicked(plum);
                numPlayers++;
                chosenChar = "plum";
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                green.setClickable(false);
                clicked(green);
                numPlayers++;
                chosenChar = "green";
                System.out.println("Green Selected");

            }
        });

        peacock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                peacock.setClickable(false);
                clicked(peacock);
                numPlayers++;
                chosenChar = "peacock";

            }
        });

        scarlet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicked(scarlet);
                numPlayers++;
                chosenChar = "scarlet";
            }
        });

        white.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicked(white);
                numPlayers++;
                chosenChar = "white";
            }
        });

        startGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//              if(numPlayers == totalPlayers) {
                RequestQueue queue = Volley.newRequestQueue(CharacterSelection.this);
                MyApplication app = (MyApplication) getApplication();
                Intent intent = new Intent(CharacterSelection.this, Game.class);
//              String url = "http://10.0.2.2:8080/info/" + app.getUserid();
                String url = "http://coms-309-038.class.las.iastate.edu:8080/info/" + app.getUserid();
                System.out.println("character url: " + url);
                JSONObject body = new JSONObject();
                try {
                    body.put("turn", false);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    app.setInfoid(response.getInt("id"));
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
//                              String url = "http://10.0.2.2:8080/info/"+app.getInfoid()+"/character/"+chosenChar;
                                String url = "http://coms-309-038.class.las.iastate.edu:8080/info/" + app.getInfoid() + "/role/" + chosenChar;
                                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, null,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                startActivity(intent);
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

//                                                    TODO: handle error
                                            }
                                        });
                                queue.add(request);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//                                    TODO: handle error
                            }
                        });
                queue.add(request);

            }
//            }
        });
    }

    private void clicked(Button characterName) {
        characterName.setClickable(false);
        characterName.setVisibility(View.INVISIBLE);
        characterName.setBackgroundColor(0x8A8787);
    }


}