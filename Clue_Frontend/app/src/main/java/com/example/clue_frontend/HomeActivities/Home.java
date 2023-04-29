package com.example.clue_frontend.HomeActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.clue_frontend.Lobbies.HostLobby;
import com.example.clue_frontend.Lobbies.JoinLobby;
import com.example.clue_frontend.MainActivity;
import com.example.clue_frontend.MyApplication;
import com.example.clue_frontend.R;

import org.json.JSONObject;

public class Home extends AppCompatActivity /*implements NavigationView.OnNavigationItemSelectedListener*/ {

    Button join;
    Button host;
    Button buypremium;
    Button rules;
    Button settings;
    Button degrade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        join = (Button) findViewById(R.id.join);
        host = (Button) findViewById(R.id.host);
        buypremium = (Button) findViewById(R.id.premium_button);
        degrade = (Button) findViewById(R.id.downgrade);
        rules = (Button) findViewById(R.id.rules_button);
        settings = (Button) findViewById(R.id.setting_btn);

        MyApplication app = (MyApplication) getApplication();
        System.out.println(app.getType());

        if (app.getType().equals("b")) {
            buypremium.setVisibility(View.VISIBLE);
            degrade.setVisibility(View.INVISIBLE);
        }
        else {
            buypremium.setVisibility(View.INVISIBLE);
            degrade.setVisibility(View.VISIBLE);
        }

        join.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Home.this, JoinLobby.class);
                startActivity(intent);
            }
        });
        host.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Home.this, HostLobby.class);
                startActivity(intent);
            }
        });
        buypremium.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(Home.this, MainActivity.class);
                MyApplication app = (MyApplication) getApplication();
                String url = "http://coms-309-038.class.las.iastate.edu:8080/updateType/" + app.getUserid();
//                String url = "http://10.0.2.2:8080/updateType/" + app.getUserid();
                RequestQueue queue = Volley.newRequestQueue(Home.this);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(Home.this, "You have updated your account", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Home.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                queue.add(request);
                app.clear();
                startActivity(intent);
            }
        });
        degrade.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(Home.this, MainActivity.class);
                MyApplication app = (MyApplication) getApplication();
                String url = "http://coms-309-038.class.las.iastate.edu:8080/degradeType/" + app.getUserid();
//                String url = "http://10.0.2.2:8080/degradeType/" + app.getUserid();
                RequestQueue queue = Volley.newRequestQueue(Home.this);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(Home.this, "You have degraded your account", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Home.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                queue.add(request);
                app.clear();
                startActivity(intent);
            }
        });

        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Rules.class);
                startActivity(intent);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Settings.class);
                startActivity(intent);
            }
        });


    }
}