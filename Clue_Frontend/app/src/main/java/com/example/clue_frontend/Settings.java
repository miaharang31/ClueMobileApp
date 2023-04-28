package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.clue_frontend.GamePlay.CardLayout;
import com.example.clue_frontend.GamePlay.Game;
import com.example.clue_frontend.Lobbies.JoinLobby;

import org.json.JSONException;
import org.json.JSONObject;

public class Settings extends AppCompatActivity {
    TextView firstname;
    TextView uname;
    Button password;
    Button username;
    TextView enteru;
    TextView enterp;
    EditText newusername;
    EditText newpassword;
    Button submitnewun;
    Button submitnewp;
    Button home;
    Button rules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        home = (Button) findViewById(R.id.account_button);
        rules = (Button) findViewById(R.id.rules_button);

        firstname = (TextView) findViewById(R.id.name);
        uname = (TextView) findViewById(R.id.username);



        MyApplication app = (MyApplication) getApplication();
        String url = "http://coms-309-038.class.las.iastate.edu:8080///getUser/username/" + app.getUserid();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {

                            response.get("username");
                            uname.setText(response.get("username").toString());
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Settings.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        url = "http://coms-309-038.class.las.iastate.edu:8080///getUser/firstname/" + app.getUserid();

        request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {

                            response.get("firstname");
                            firstname.setText(response.get("firstname").toString());
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Settings.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, Home.class);
                startActivity(intent);
            }
        });
        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, Rules.class);
                startActivity(intent);
            }
        });

        final boolean[] userclicked = {false};
        final boolean[] passclicked = {false};








        password = (Button) findViewById(R.id.changePassword);
        username = (Button) findViewById(R.id.changeUsername);
        enteru = (TextView) findViewById(R.id.enteru);
        enterp = (TextView) findViewById(R.id.enterp);
        newusername = (EditText) findViewById(R.id.newusername);
        newpassword = (EditText) findViewById(R.id.newpassword);
        submitnewun = (Button) findViewById(R.id.submitnewun);
        submitnewp = (Button) findViewById(R.id.submitnewp);
        enteru.setVisibility(View.INVISIBLE);
        enterp.setVisibility(View.INVISIBLE);
        newusername.setVisibility(View.INVISIBLE);
        newpassword.setVisibility(View.INVISIBLE);
        submitnewun.setVisibility(View.INVISIBLE);
        submitnewp.setVisibility(View.INVISIBLE);

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userclicked[0]) {
                    userclicked[0] = true;
                    password.setVisibility(View.INVISIBLE);
                    enteru.setVisibility(View.VISIBLE);
                    newusername.setVisibility(View.VISIBLE);
                    submitnewun.setVisibility(View.VISIBLE);
                }
                else {
                    userclicked[0] = false;
                    password.setVisibility(View.VISIBLE);
                    enteru.setVisibility(View.INVISIBLE);
                    newusername.setVisibility(View.INVISIBLE);
                    submitnewun.setVisibility(View.INVISIBLE);
                }


            }


        });
        String newu = newusername.getText().toString().trim();
        submitnewun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userclicked[0] = false;
                password.setVisibility(View.VISIBLE);
                enteru.setVisibility(View.INVISIBLE);
                newusername.setVisibility(View.INVISIBLE);
                submitnewun.setVisibility(View.INVISIBLE);
                if (newu.length() != 0) {
                    MyApplication app = (MyApplication) getApplication();
                    String url = "http://coms-309-038.class.las.iastate.edu:8080//changePassword/" + app.getUserid();
                    RequestQueue queue = Volley.newRequestQueue(Settings.this);
                    JSONObject body = null;
                    try {
                        body = new JSONObject();
                        body.put("username", newu);
                    } catch (JSONException exception) {
                        exception.printStackTrace();
                    }
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, body,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(Settings.this, "Your password is changed", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Settings.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                    queue.add(request);
                }
                else {
                    Toast.makeText(Settings.this, "You did not change password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!passclicked[0]) {
                    passclicked[0] = true;
                    username.setVisibility(View.INVISIBLE);
                    enterp.setVisibility(View.VISIBLE);
                    newpassword.setVisibility(View.VISIBLE);
                    submitnewp.setVisibility(View.VISIBLE);
                }
                else {
                    passclicked[0] = false;
                    username.setVisibility(View.VISIBLE);
                    enterp.setVisibility(View.INVISIBLE);
                    newpassword.setVisibility(View.INVISIBLE);
                    submitnewp.setVisibility(View.INVISIBLE);
                }

            }


        });
        String newp = newpassword.getText().toString().trim();
        submitnewp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passclicked[0] = false;
                username.setVisibility(View.VISIBLE);
                enterp.setVisibility(View.INVISIBLE);
                newpassword.setVisibility(View.INVISIBLE);
                submitnewp.setVisibility(View.INVISIBLE);
                if (newp.length() != 0) {
                    MyApplication app = (MyApplication) getApplication();
                    String url = "http://coms-309-038.class.las.iastate.edu:8080//changePassword/" + app.getUserid();
                    RequestQueue queue = Volley.newRequestQueue(Settings.this);
                    JSONObject body = null;
                    try {
                        body = new JSONObject();
                        body.put("password", newp);
                    } catch (JSONException exception) {
                        exception.printStackTrace();
                    }
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, body,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(Settings.this, "Your password is changed", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Settings.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                    queue.add(request);
                }
                else {
                    Toast.makeText(Settings.this, "You did not change password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    } //oncreatebundle

}