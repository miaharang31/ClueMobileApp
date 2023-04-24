package com.example.clue_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class JoinLobby extends AppCompatActivity {
    Button joinLobby;
    EditText gameCode;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_joinlobby);

        joinLobby = (Button) findViewById(R.id.joinbtn);
        joinLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinLobby.this, Lobby.class);
                gameCode = findViewById(R.id.gameCode);
                String gameCodeData = gameCode.getText().toString();
                if(gameCodeData.isEmpty()) {
                    gameCode.setError("Game Code cannot be empty!");
                } else {
//                    String url = "http://10.0.2.2:8080/lobby/join/1";
                    String url = "http://coms-309-038.class.las.iastate.edu:8080/lobby/join/1";
                    RequestQueue queue = Volley.newRequestQueue(JoinLobby.this);
                    JSONObject body = null;
                    try {
                        body = new JSONObject();
                        body.put("gameCode", gameCodeData);
                    } catch (JSONException exception) {
                        exception.printStackTrace();
                    }

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, body,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    startActivity(intent);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(JoinLobby.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                    queue.add(request);
                }

            }
        });
    }
}
