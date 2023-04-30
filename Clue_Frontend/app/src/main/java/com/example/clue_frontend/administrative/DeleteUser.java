package com.example.clue_frontend.administrative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.clue_frontend.R;

import org.json.JSONArray;

public class DeleteUser extends AppCompatActivity {


    EditText deleteUser;
    EditText lobbyText;
    String username;
    String lobbyID;
    Button submit;
    Button exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);


        deleteUser = findViewById(R.id.usernameDelete);
        lobbyText = findViewById(R.id.lobbyText);
        submit = findViewById(R.id.submitUsername);
        exit = findViewById(R.id.exitDelete);

        username = deleteUser.getText().toString();
        lobbyID = lobbyText.getText().toString();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // call to player database to get player id from player username
                // call to lobby database
                String url = "http://coms-309-038.class.las.iastate.edu:8080/lobby";

                RequestQueue queue = Volley.newRequestQueue(DeleteUser.this);

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                // TODO: display all lobbies
                            }
                        },
                        new Response.ErrorListener() {

                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(DeleteUser.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                            }
                        });
                queue.add(request);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DeleteUser.this, AdminSettings.class);
                startActivity(intent);
            }
        });
    }
}