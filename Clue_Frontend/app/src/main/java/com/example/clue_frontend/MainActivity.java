package com.example.clue_frontend;

import static com.example.clue_frontend.GamePlay.GameView.turn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.clue_frontend.GamePlay.CharacterSelection;
import com.example.clue_frontend.GamePlay.Constraints;
import com.example.clue_frontend.GamePlay.Game;
import com.example.clue_frontend.GamePlay.GameView;
import com.example.clue_frontend.Lobbies.HostLobby;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    /*for scroll outside override*/
//    TextView textView;
//    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constraints.SCREEN_WIDTH = dm.widthPixels;
        Constraints.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_main); //activity_main


        Button submitButton = (Button) findViewById(R.id.submitButton);
        TextView newPlayerLink = findViewById(R.id.NewPlayerLink);
        //When the sign in button is clicked and data is entered for usename and password
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            Intent intent = new Intent(MainActivity.this, Home.class);
                Intent intent = new Intent(MainActivity.this, Home.class);
//            startActivity(intent);
                EditText username = findViewById(R.id.loginUsername);
                EditText password = findViewById(R.id.loginPassword);
                String usernameData = username.getText().toString();
                String passwordData = password.getText().toString();
                String firstNameData = " ";
                String lastNameData = " ";
                String emailData = " ";
                boolean checkUsername, checkPassword;

                if (usernameData.isEmpty()) {
                    username.setError("Username cannot be empty");
                    checkUsername = false;
                } else {
                    username.setError(null);
                    checkUsername = true;
                }
                if(usernameData.isEmpty()){
                    username.setError("Username cannot be empty");
                    checkUsername = false;
                }else{
                    username.setError(null);
                    checkUsername = true;

                    if(passwordData.isEmpty()){
                        password.setError("Password cannot be empty");
                        checkPassword = false;
                    }else{
                        password.setError(null);
                        checkPassword = true;
                    }

                    // if all textboxes are correct, all data will be added to the SecondActivity (main page for either regular/premium users) and will start
                    if (checkUsername == true && checkPassword == true){
                        String url = "http://coms-309-038.class.las.iastate.edu:8080/login";
                        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                        JSONObject json = null;
                        try {
                            json = new JSONObject();
                            json.put("username", usernameData);
                            json.put("password", passwordData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (passwordData.isEmpty()) {
                            password.setError("Password cannot be empty");
                            checkPassword = false;
                        } else {
                            password.setError(null);
                            checkPassword = true;
                        }

                        // if all textboxes are correct, all data will be added to the SecondActivity (main page for either regular/premium users) and will start
                        if (checkUsername == true && checkPassword == true) {
                            try {
                                json = new JSONObject();
                                json.put("username", usernameData);
                                json.put("password", passwordData);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if (passwordData.isEmpty()) {
                                password.setError("Password cannot be empty");
                                checkPassword = false;
                            } else {
                                password.setError(null);
                                checkPassword = true;
                            }

                            // if all textboxes are correct, all data will be added to the SecondActivity (main page for either regular/premium users) and will start
                            JsonObjectRequest jreq = null;
                            if (checkUsername == true && checkPassword == true) {
                                try {
                                    json = new JSONObject();
                                    json.put("username", usernameData);
                                    json.put("password", passwordData);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                jreq = new JsonObjectRequest(Request.Method.POST, url, json,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                MyApplication app = (MyApplication) getApplication();
                                                try {
                                                    app.setUserid((Integer) response.get("id"));
                                                } catch (JSONException e) {
                                                    throw new RuntimeException(e);
                                                }
                                                startActivity(intent);
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(MainActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                                                Log.d("ResponseError", error.toString());
                                            }
                                        });

                                queue.add(jreq);
                            }


                            queue.add(jreq);
                        }

                    }
                };
            }
        });

        //when the new player link is clicked, go to th SignUpActivity
        newPlayerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, EndGameWinOrLose.class);

                startActivity(intent);

            }
        });


    }
}
