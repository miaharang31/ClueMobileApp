package com.example.clue_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Lobby extends AppCompatActivity {

    HostLobby newLobby;
    String hostName;
//    EditText host;
//    String[] playerNames = new String[newLobby.numPlayers];
//    EditText[] names = new EditText[newLobby.numPlayers];
    Button startGame;
//    int count;

    int max;
    TextView maxPlayers;
    TextView currentPlayers;
    JSONObject gameLobby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

       // startGame = (Button)findViewById(R.id.startGame);
//        host = findViewById(R.id.host);
        startGame = findViewById(R.id.startGame);
        maxPlayers = findViewById(R.id.maxplayertxt);
        currentPlayers = findViewById(R.id.currentplayertxt);
//        String currentName;
//        int topPadding = 150;

        RequestQueue queue = Volley.newRequestQueue(Lobby.this);
        String url = "http://coms-309-038.class.las.iastate.edu:8080/lobby/1";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response", response.toString());
                        try {
                            max = (int) response.getJSONObject(0).get("maxPlayers");
                            maxPlayers.setText("Max Players: " + max);
                            currentPlayers.setText("Current Players" + response.getJSONObject(0).get("numPlayers") + "/" + max);
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

        System.out.println("hi");

        // insert host name
//        host.setText(hostName);



        // create text boxes with each player's name
//        for(int i = 0; i <= playerNames.length; i++){
//
//            currentName = playerNames[i];
//            names[i].setText(currentName);
//            names[i].setPadding(45, topPadding, 45, 100);
//            //names[i].setBackgroundColor(#);
//            topPadding+= 50;
//            count++;
//        }

        startGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Lobby.this, StartGame.class);
                startActivity(intent);
            }
        });
    }


}
