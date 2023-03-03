package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class HostLobby extends AppCompatActivity{
    Integer numPlayers = 0;
    Button three;
    Button four;
    Button five;
    Button six;
    Button createLobby;

    EditText gameCode;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_hostlobby);

        three = (Button) findViewById(R.id.button3);
        four = (Button) findViewById(R.id.button4);
        five = (Button) findViewById(R.id.button5);
        six = (Button) findViewById(R.id.button6);
        createLobby = (Button) findViewById(R.id.lobbyCreator);

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButtons();
                v.setBackgroundColor(Color.GRAY);
                numPlayers = 3;
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButtons();
                v.setBackgroundColor(Color.GRAY);
                numPlayers = 4;
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButtons();
                v.setBackgroundColor(Color.GRAY);
                numPlayers = 5;
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButtons();
                v.setBackgroundColor(Color.GRAY);
                numPlayers = 6;
            }
        });

        createLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HostLobby.this, Lobby.class);
                gameCode = findViewById(R.id.gameCode);
                String gameCodeData = gameCode.getText().toString();
                if(gameCodeData.isEmpty()) {
                    gameCode.setError("Please Enter A Game Code!");
                } else if(numPlayers == 0) {
                    gameCode.setError("Please Select Maximum Players");
                } else {
//                    String url = "http://10.0.2.2:8080/lobby/new";
                    String url = "http://coms-309-038.class.las.iastate.edu:8080/lobby/new";
                    RequestQueue queue = Volley.newRequestQueue(HostLobby.this);
                    JSONObject body = null;
                    try {
                        body = new JSONObject();
                        body.put("maxPlayers", numPlayers);
                        body.put("gameCode", gameCodeData);
                        body.put("hostID", 0);
                        body.put("isPremium", false);
                    } catch (JSONException exception) {
                        exception.printStackTrace();
                    }

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    startActivity(intent);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(HostLobby.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                    queue.add(request);
                }
            }
        });
    }
    private void resetButtons() {
        three.setBackgroundColor(Color.parseColor("#08403E"));
        four.setBackgroundColor(Color.parseColor("#08403E"));
        five.setBackgroundColor(Color.parseColor("#08403E"));
        six.setBackgroundColor(Color.parseColor("#08403E"));

    }

}
